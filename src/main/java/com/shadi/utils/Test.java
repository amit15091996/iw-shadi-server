package com.shadi.utils;

public class Test {
	public static void main(String[] args) {
//		System.out.println(100 + 100 + "data"); // -> 200data
//
//		System.out.println("data" + 100 + 100); // --> data100100
//
//		Integer num1 = 100;
//		Integer num2 = 100;
//		Integer num3 = 500;
//		Integer num4 = 500;
//
//		if (num1 == num2) {
//			System.out.println("num1 == num2");
//		} else {
//			System.out.println("num1 != num2");
//		}
//		if (num3 == num4) {
//			System.out.println("num3 == num4");
//		} else {
//			System.out.println("num3 != num4");
//		}

		String s1 = new String("Java");
		String s2 = new String("Java");
		String s3 = s1.intern();
		System.out.println(s1 == s2); // true
		System.out.println(s1.equals(s2)); // false
		System.out.println(s1 == s3); // true
		System.out.println(s1.equals(s3)); // true
	}
}
