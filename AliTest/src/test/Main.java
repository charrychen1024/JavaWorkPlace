package test;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Main {

/** 请完成下面这个函数，实现题目要求的功能 **/
 /** 当然，你也可以不按照这个模板来作答，完全按照自己的想法来 ^-^  **/
    static double leartCurve(double mu1, double sigma1, double mu2, double sigma2) {
    	int times = 1000;
    	double x[] = new double[times];
    	double y[] = new double[times];
    	
    	java.util.Random r = new java.util.Random();
    	
    	int n = 0;
    	for(int i = 0;i < times;i++){
    		//生成随机点
    		x[i] = Math.sqrt(sigma1)*r.nextGaussian()+mu1;
    		System.out.println("x = "+x[i]);
    		y[i] = Math.sqrt(sigma2)*r.nextGaussian()+mu2;
    		System.out.println("y = "+y[i]);
    		//求概率
    		double j = Math.sqrt((Math.sqrt(x[i])+Math.sqrt(y[i])-1))+Math.sqrt(x[i])*Math.sqrt(y[i]);
    		System.out.println("j = "+j);
    		if(j>0)
    			n++;
    	}
    	System.out.println("n = "+n);
    	DecimalFormat df = new DecimalFormat("0.0");
    	
    	return (double)n/times;
    	

    }

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        double res;
    
        double _mu1;
        _mu1 = Double.parseDouble(in.nextLine().trim());

        double _sigma1;
        _sigma1 = Double.parseDouble(in.nextLine().trim());

        double _mu2;
        _mu2 = Double.parseDouble(in.nextLine().trim());

        double _sigma2;
        _sigma2 = Double.parseDouble(in.nextLine().trim());
        

  
        res = leartCurve(_mu1, _sigma1, _mu2, _sigma2);
        System.out.println(String.valueOf(res));
    }
}

