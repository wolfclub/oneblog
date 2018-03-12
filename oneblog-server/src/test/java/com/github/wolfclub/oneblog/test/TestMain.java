package com.github.wolfclub.oneblog.test;

import java.math.BigDecimal;

/**
 * @author liuchan
 * @date 19:29 2018/3/6
 */
public class TestMain {


	public static void main(String[] args){
		BigDecimal m = new BigDecimal(122333333333333333333333333333333333333333333333333332.0);
//		System.out.println(m.intValue());
//		System.out.println(m.doubleValue());
		System.out.println(m.scale());
		System.out.println(m.precision());
	}

}
