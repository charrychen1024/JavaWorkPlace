package com.hellohadoop;
import java.io.*;
import java.util.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
public class TokenizerMapper extends Mapper<LongWritable,Text,Text,IntWritable>{
	private final static IntWritable one = new IntWritable(1);
	private Text word = new Text();
	public void map(LongWritable key,Text value,Context context) throws IOException, InterruptedException{
		StringTokenizer itr = new StringTokenizer(value.toString());
		while(itr.hasMoreElements()){
			word.set(itr.nextToken());
			context.write(word, one);
		}
	}

}
