package com.api.test;

import java.io.File;
import java.util.Arrays;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.junit.Test;

import scala.Tuple2;

public class WorcCountTest {

	@Test
	public void test() {
		String inputPath = "input";
		String outputPath = "result";
		SparkConf conf = new SparkConf().setMaster("spark://192.168.1.222:7077").setAppName("WordCount");
		JavaSparkContext context = new JavaSparkContext(conf);
		JavaRDD<String> input = context.textFile(inputPath); // 读取文件
		JavaRDD<String> words = input.flatMap(item -> Arrays.asList(item.split(" ")).iterator()); // 切分单词
		JavaPairRDD<String, Integer> counts = words.mapToPair(item -> new Tuple2<>(item, 1)).reduceByKey((x, y) -> x + y); // 转换成键值对并计数
		System.out.println(counts.collect()); // 输出统计结果
		File dir = new File(outputPath); // 删除输出目录
		File[] files = dir.listFiles();
		if (files != null) {
			for (File file : files) {
				file.delete();
			}
		}
		dir.delete();
		counts.saveAsTextFile(outputPath); // 将统计结果写入结果文件
		context.close();
	}

}
