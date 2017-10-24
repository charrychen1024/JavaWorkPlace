/*
 * @(#)ApplicationFrame.java 11:07:25 AM
 * 
 * Copyright 2012 Lzj.Liu, Inc. All rights reserved. ForYou
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.you.frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.text.DecimalFormat;
import java.util.Enumeration;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LookAndFeel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.FontUIResource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.htmlparser.Parser;
import org.htmlparser.util.ParserException;

import com.you.constant.SystemConstant;
import com.you.service.SpiderService;
import com.you.util.StringUtil;

/**
 * <p>Title: ApplicationFrame.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2015/4</p>
 * <p>Company: Co.,Ltd</p>
 * @author cjr
 * @version 1.0
 */
public class ApplicationFrame extends JFrame implements ChangeListener,
		ActionListener
{
	private static final long serialVersionUID = -9045178185692518100L;

	private static final Log loger = LogFactory.getLog(ApplicationFrame.class);

	private JTextField webPathField = new JTextField();//网页地址输入框

	private JButton selectDir = new JButton("浏览...");//选择网页保存目录按钮

	private JTextField dirPathField = new JTextField();//网页保存文本框

	private JTextArea textArea = new JTextArea();//系统操作明细显示

	private JButton startRun = new JButton("开始运行");//运行按钮

	private JProgressBar process = new JProgressBar();//进度条

	private JLabel show = new JLabel("已完成：0%", JLabel.CENTER);//完成进度显示

	private JTextField jThreadField = new JTextField();//线程数量

	private JTextField jDeepField = new JTextField();//爬行深度

	private JTextField jKeyField = new JTextField();//关键字

	private JComboBox lafComboBox;//样式

	private Vector<SupportedLaF> supportedLaFs = new Vector<SupportedLaF>(); //主题风格

	private Timer timer;

	/**
	 * 构造器
	 */
	public ApplicationFrame()
	{
		this.initialize();
	}

	static class SupportedLaF//窗口风格
	{
		String name;
		LookAndFeel laf;

		SupportedLaF(String name, LookAndFeel laf)
		{
			this.name = name;
			this.laf = laf;
		}

		public String toString()
		{
			return name;
		}
	}

	/**
	 * 窗口初始化
	 */
	@SuppressWarnings("unchecked")
	public void initialize()
	{
		//ApplicationFrame.class.
		//初始化系统主题
		UIManager.LookAndFeelInfo[] installedLafs = UIManager
				.getInstalledLookAndFeels();
		for (UIManager.LookAndFeelInfo lafInfo : installedLafs)
		{
			try
			{
				Class lnfClass = Class.forName(lafInfo.getClassName());
				LookAndFeel laf = (LookAndFeel) (lnfClass.newInstance());
				if (laf.isSupportedLookAndFeel())
				{
					String name = lafInfo.getName();
					supportedLaFs.add(new SupportedLaF(name, laf));
				}
			}
			catch (Exception e)
			{ // If ANYTHING weird happens, don't add it
				continue;
			}
		}
		//窗口关闭事件监听
		this.addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				closeWindow();
			}
		});
		this.setResizable(false);
		this.setTitle("Java-网络爬虫管理系统");
		this.setLayout(null);//使用绝对定位要先设置布局管理器为null
		//this.getContentPane().setBackground(Color.white);
		Toolkit toolKit = Toolkit.getDefaultToolkit();
		this.setWindowCenter(toolKit);//设置窗口大小及居中
		this.initWebTextField();
		this.initDirTextField();
		this.initTextArea();
		this.initSystemOptions();
		initGlobalFontSetting(SystemConstant.FONT_NAME, Font.PLAIN,
				SystemConstant.FONT_SIZE);
		this.lafComboBox.setSelectedIndex(1);//设置默认窗口风格
		this.setVisible(true);
	}

	@SuppressWarnings("unchecked")
	public void initGlobalFontSetting(String fontName, int style, int size)
	{
		Font fnt = new Font(fontName, style, size);
		FontUIResource fontRes = new FontUIResource(fnt);
		for (Enumeration keys = UIManager.getDefaults().keys(); keys
				.hasMoreElements();)
		{
			Object key = keys.nextElement();
			Object value = UIManager.get(key);
			if (value instanceof FontUIResource)
				UIManager.put(key, fontRes);
		}
	}

	public void initWebTextField()
	{
		int offHeight = 10;//离顶部的距离
		int height = 30;//元素高度
		Container container = this.getContentPane();
		Font font = new Font(SystemConstant.ELE_FONT_NAME, Font.PLAIN,
				SystemConstant.FONT_SIZE);
		//文本标签
		JLabel jLabel = new JLabel("请输入您需要分析的网址：");
		jLabel.setFont(font);
		jLabel.setBounds(10, offHeight, 250, height);
		container.add(jLabel);
		//文本输入框
		webPathField.setBounds(170, offHeight, 450, height);
		webPathField.setEditable(true);
		webPathField.setText(SystemConstant.PROTOL);
		container.add(webPathField);
		//操作提示标签
		JLabel tipLabel = new JLabel("（注意：网页地址必须正确、有效）");
		tipLabel.setFont(new Font(SystemConstant.ELE_FONT_NAME, Font.PLAIN,
				SystemConstant.FONT_SIZE));
		tipLabel.setBounds(630, offHeight, 250, height);
		tipLabel.setForeground(Color.blue);
		container.add(tipLabel);
	}

	public void initDirTextField()
	{
		int offHeight = 50;//离顶部的距离
		int height = 30;//元素高度
		Container container = this.getContentPane();
		Font font = new Font(SystemConstant.ELE_FONT_NAME, Font.PLAIN,
				SystemConstant.FONT_SIZE);
		//文本标签
		JLabel jLabel = new JLabel("请选择网页文件保存目录：");
		jLabel.setFont(font);
		jLabel.setBounds(10, offHeight, 250, height);
		container.add(jLabel);
		//文本输入框
		dirPathField.setBounds(170, offHeight, 450, height);
		dirPathField.setText(SystemConstant.DOWN_URL);
		dirPathField.setEditable(false);
		dirPathField.addMouseListener(new MouseAdapter()
		{
			public void mousePressed(MouseEvent e)
			{
				String fileName = selectDirectory();
				if (StringUtils.isNotBlank(fileName))
					dirPathField.setText(fileName);
			}
		});
		container.add(dirPathField);
		//文件选择按钮
		selectDir.setFont(font);
		selectDir.setBounds(622, offHeight, 90, height);
		selectDir.addActionListener(this);
		container.add(selectDir);
		//操作提示标签
		JLabel tipLabel = new JLabel("（注意：请选择正确的网页保存目录）");
		tipLabel.setFont(new Font(SystemConstant.ELE_FONT_NAME, Font.PLAIN,
				SystemConstant.FONT_SIZE));
		tipLabel.setBounds(720, offHeight, 250, height);
		tipLabel.setForeground(Color.blue);
		container.add(tipLabel);
	}

	/**
	 * 操作选项
	 */
	public void initSystemOptions()
	{
		Container container = this.getContentPane();
		Font font = new Font(SystemConstant.ELE_FONT_NAME, Font.PLAIN,
				SystemConstant.FONT_SIZE);
		//线程数量
		JLabel jThread = new JLabel("请输入线程数量（整数）：");
		jThread.setFont(font);
		jThread.setBounds(10, 90, 160, 30);
		container.add(jThread);
		jThreadField.setBounds(170, 90, 150, 30);
		jThreadField.setText(SystemConstant.THREAD_NUM);
		jThreadField.setForeground(Color.blue);
		container.add(jThreadField);
		//爬行深度
		JLabel jDeep = new JLabel("请输入爬行深度（整数）：");
		jDeep.setFont(font);
		jDeep.setBounds(325, 90, 160, 30);
		container.add(jDeep);
		jDeepField.setBounds(470, 90, 150, 30);
		jDeepField.setText(SystemConstant.SPIDER_DEEP);
		jDeepField.setForeground(Color.blue);
		container.add(jDeepField);
		//爬行关键字
		JLabel jKey = new JLabel("请输入爬行关键字内容：");
		jKey.setFont(font);
		jKey.setBounds(10, 130, 160, 30);
		container.add(jKey);
		jKeyField.setBounds(170, 130, 150, 30);
		jKeyField.setForeground(Color.blue);
		container.add(jKeyField);
		//窗口主题风格
		JLabel lafLabel = new JLabel("窗口主题：");
		lafLabel.setFont(font);
		lafLabel.setBounds(325, 130, 120, 30);
		container.add(lafLabel);
		lafComboBox = new JComboBox(supportedLaFs);
		lafComboBox.setFont(font);
		lafComboBox.setBounds(390, 130, 130, 30);
		lafComboBox.setEditable(false);
		lafComboBox.addActionListener(this);
		container.add(lafComboBox);
		//开始运行
		startRun.setFont(font);
		startRun.setBounds(530, 130, 90, 30);
		startRun.addActionListener(this);
		container.add(startRun);
	}

	/**
	 * 文本区、进度条
	 */
	public void initTextArea()
	{
		Container container = this.getContentPane();
		JLabel tipLabel = new JLabel("系统操作明细：");
		tipLabel.setFont(new Font(SystemConstant.ELE_FONT_NAME, Font.PLAIN,
				SystemConstant.FONT_SIZE));
		tipLabel.setBounds(10, 170, 250, 30);
		container.add(tipLabel);

		textArea.setBounds(0, 0, 10, 10);
		textArea.setEditable(false);
		textArea.setForeground(Color.blue);
		JScrollPane scrollPane = new JScrollPane(textArea);
		//scrollPane.setBorder(BorderFactory.createTitledBorder("构造一般的JTextArea"));
		scrollPane.setBounds(10, 205, 900, 330);
		container.add(scrollPane, BorderLayout.CENTER);
		//进度条
		process.setBounds(10, 545, 900, 30);
		process.setOrientation(JProgressBar.HORIZONTAL);
		process.setMinimum(0);
		process.setMaximum(100);
		process.setValue(0);
		process.setStringPainted(true);
		process.addChangeListener(this);
		process.setPreferredSize(new Dimension(200, 20));
		process.setBorderPainted(true);
		process.setBackground(Color.white);
		timer = new Timer(0, this);
		container.add(process, BorderLayout.CENTER);
		show.setFont(new Font(SystemConstant.ELE_FONT_NAME, Font.PLAIN,
				SystemConstant.FONT_SIZE));
		show.setBounds(360, 585, 250, 30);
		show.setForeground(Color.blue);
		show.setVisible(false);
		container.add(show);
	}

	/**
	 * 窗口居中
	 * @param toolKit Toolkit
	 */
	public void setWindowCenter(Toolkit toolKit)
	{
		int winWidth = SystemConstant.windowWidth;//窗口实际宽度
		int winHeight = SystemConstant.windowHeight;//窗口实际高度
		Dimension screenSize = toolKit.getScreenSize();
		int screenWidth = screenSize.width; //获取屏幕的宽
		int screenHeight = screenSize.height; //获取屏幕的高
		//高度、宽度处理
		if (screenWidth < SystemConstant.windowWidth)
			winWidth = screenWidth;
		if (screenHeight < SystemConstant.windowHeight)
			winHeight = screenHeight;
		this.setSize(winWidth, winHeight);//设置窗口大小
		this.setLocation(screenWidth / 2 - winWidth / 2, screenHeight / 2
				- winHeight / 2);
	}

	/**
	 * 窗口关闭处理
	 */
	public void closeWindow()
	{
		String[] options = { "确 定", "取 消" };
		JOptionPane pane2 = new JOptionPane("你确定要退出系统吗？",
				JOptionPane.QUESTION_MESSAGE, JOptionPane.YES_NO_OPTION, null,
				options, options[0]);
		JDialog dialog = pane2.createDialog(this, "系统提示");
		dialog.setVisible(true);
		Object selectedValue = pane2.getValue();
		if (selectedValue == null || selectedValue == options[1])
		{
			setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // 这个是关键
		}
		else if (selectedValue == options[0])
		{
			setDefaultCloseOperation(EXIT_ON_CLOSE);
		}
	}

	/**
	 * 选择保存目录
	 * @return String 选择的目录
	 */
	private String selectDirectory()
	{
		String fileName = null;
		JFrame jFrame = new JFrame();
		jFrame.setLocation(jFrame.getLocation());

		JFileChooser imageChooser = new JFileChooser();
		imageChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		imageChooser.setToolTipText("选择目录");
		imageChooser.setDialogTitle("请选择网页保存目录");
		int result = imageChooser.showOpenDialog(jFrame);
		if (result == JFileChooser.APPROVE_OPTION)
		{
			fileName = imageChooser.getSelectedFile().getAbsolutePath();
		}
		return fileName;
	}

	public void actionPerformed(ActionEvent e)
	{
		Object object = e.getSource();

		//点击选择存储目录按钮
		if (object == this.selectDir)
		{
			String dir = this.selectDirectory();
			if (StringUtils.isNotBlank(dir))
				this.dirPathField.setText(dir);
		}
		//窗口主题
		if (object == this.lafComboBox)
		{
			SupportedLaF supportedLaF = ((SupportedLaF) lafComboBox
					.getSelectedItem());
			LookAndFeel laf = supportedLaF.laf;
			try
			{
				UIManager.setLookAndFeel(laf);
				SwingUtilities.updateComponentTreeUI(this);
			}
			catch (UnsupportedLookAndFeelException exc)
			{
				((DefaultComboBoxModel) lafComboBox.getModel())
						.removeElement(supportedLaF);
			}
		}
		if (object == this.startRun)
		{
			String webAddress = webPathField.getText();
			//网页地址验证
			if (StringUtils.isBlank(webAddress)
					|| !StringUtil.isUrl(webAddress))
			{
				JOptionPane.showMessageDialog(null, "警告：请输入您需要分析的正确有效的网页地址！",
						"错误提示", JOptionPane.ERROR_MESSAGE);
				webPathField.grabFocus();
				return;
			}
			//保存目录验证
			String saveDir = dirPathField.getText();
			File directoryFile = new File(saveDir);
			if(!directoryFile.exists())
				directoryFile.mkdirs();
			if (StringUtils.isBlank(saveDir) || !directoryFile.exists()
					|| !directoryFile.isDirectory())
			{
				JOptionPane.showMessageDialog(null,
						"警告：目录不存在或您选择的地址不是一个文件目录，请选择正确的网页保存目录！", "错误提示",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			//线程数量验证
			int threadNum = 0;
			String threadNums = jThreadField.getText();
			try
			{
				threadNum = Integer.parseInt(threadNums);
			}
			catch (NumberFormatException ex)
			{
				JOptionPane.showMessageDialog(null,
						"警告：线程数量不是一个有效的整数，请输入整数格式的线程数量！", "错误提示",
						JOptionPane.ERROR_MESSAGE);
				jThreadField.setText(SystemConstant.THREAD_NUM);
				jThreadField.grabFocus();
				return;
			}
			//爬行深度验证
			int spiderNum = 0;
			String deepNum = jDeepField.getText();
			try
			{
				spiderNum = Integer.parseInt(deepNum);
			}
			catch (NumberFormatException ex)
			{
				JOptionPane.showMessageDialog(null,
						"警告：爬行深度不是一个有效的整数，请输入整数格式的爬行深度！", "错误提示",
						JOptionPane.ERROR_MESSAGE);
				jDeepField.setText(SystemConstant.SPIDER_DEEP);
				jDeepField.grabFocus();
				return;
			}
			//关键字内容
			String keyWord = jKeyField.getText();
			if (StringUtils.isBlank(keyWord))
			{
				int iconfirm = JOptionPane.showConfirmDialog(null,
						"警告：不输入关键字同内容吗？不输入将下载所有网页内容，请确认！", "信息确认",
						JOptionPane.YES_NO_OPTION);
				if (iconfirm == 1)
					return;
			}
			this.textArea.setText("");
			this.process.setValue(0);//进度条重置
			String encode = SystemConstant.ENCODE_UT;
			try
			{
				Parser myParser = new Parser(webAddress);
				encode = myParser.getEncoding();
			}
			catch (ParserException ex)
			{
				loger.error(ex);
			}
			timer.start();
			this.show.setVisible(true);
			final DownloadThread download = new DownloadThread();//多线程
			download.setWebAddress(webAddress);
			download.setDirectory(saveDir);
			download.setThreadNum(threadNum);
			download.setSpiderNum(spiderNum);
			download.setKeyWord(keyWord);
			download.setCharset(encode);
			new Thread(download).start();
		}
	}

	// @Override
	public void stateChanged(ChangeEvent e)
	{
		int value = process.getValue();
		if (e.getSource() == process)
		{
			this.show.setText("当前完成进度：" + Integer.toString(value) + " %");
			this.show.setForeground(Color.blue);
		}
	}

	//模拟一个耗时的任务  
	class DownloadThread implements Runnable
	{
		private static final long serialVersionUID = 1441268445283293647L;

		private String webAddress;

		private String directory;

		private int threadNum;

		private int spiderNum;

		private String keyWord;

		private String charset;

		public String getCharset()
		{
			return charset;
		}

		public void setCharset(String charset)
		{
			this.charset = charset;
		}

		public String getWebAddress()
		{
			return webAddress;
		}

		public void setWebAddress(String webAddress)
		{
			this.webAddress = webAddress;
		}

		public String getDirectory()
		{
			return directory;
		}

		public void setDirectory(String directory)
		{
			this.directory = directory;
		}

		public int getThreadNum()
		{
			return threadNum;
		}

		public void setThreadNum(int threadNum)
		{
			this.threadNum = threadNum;
		}

		public int getSpiderNum()
		{
			return spiderNum;
		}

		public void setSpiderNum(int spiderNum)
		{
			this.spiderNum = spiderNum;
		}

		public String getKeyWord()
		{
			return keyWord;
		}

		public void setKeyWord(String keyWord)
		{
			this.keyWord = keyWord;
		}

		//run方法代表不断完成任务的过程  
		public void run()
		{
			SpiderService service = new SpiderService();
			service.setWebAddress(webAddress);
			service.setDirectory(directory);
			service.setThreadNum(threadNum);
			service.setDeepNum(spiderNum);
			service.setKeyWord(keyWord);
			service.setCharset(charset);
			DecimalFormat df = new DecimalFormat("###.##");
			List<String> list = service.listAllLinkFromUrl(webAddress);
			if (null != list && !list.isEmpty())
			{
				int length = list.size();
				/*for (int j = 0; j < length; j++)
				{
					String url = list.get(j);
					textArea.append(j + "->" + url + SystemConstant.SPARE_LINE);
					textArea.paintImmediately(textArea.getBounds());
				}*/
				for (int i = 0; i < length; i++)//进度条进度提示
				{
					String url = list.get(i);
					String leave = df.format((double) i / length);
					double leaveProcess = Double.parseDouble(leave);
					int intLeave = (int) (leaveProcess * 100);
					//System.out.println(intLeave + "===" + leaveProcess + "==="
							//+ leave);
					int value = process.getValue();
					textArea.append(i + "->" + url + SystemConstant.SPARE_LINE);
					textArea.paintImmediately(textArea.getBounds());//进度条刷新
					if (value <= 100)
					{
						value++;
						process.setValue(intLeave);
						if (i == length - 1)
							process.setValue(100);
					}
					else
					{
						timer.stop();
					}
					service.setWebAddress(url);
					service.getWebByHomePage();
				}
			}
		}
	}

	public static void main(String[] args)
	{
		new ApplicationFrame(); 
	}
}
