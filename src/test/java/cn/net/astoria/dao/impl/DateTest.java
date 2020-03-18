package cn.net.astoria.dao.impl;

import org.junit.Test;

import java.text.SimpleDateFormat;

/**
 * @ClassName DateTest
 * @Description TODO
 * @Author Astoria
 * @Date 2020/3/10 17:13
 * @Version 1.0
 */
public class DateTest {
	@Test
	public void test01(){
		long date = System.currentTimeMillis();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
		for(int i=0;i<15;++i){
			System.out.println(simpleDateFormat.format(date - (i*1000*3600*24)));
		}
	}
}
