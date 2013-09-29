package com.cheng.bsh;

import java.util.Date;

import bsh.Interpreter;

public class QuickStart {
	public static void main(String[] args) throws Exception {
		Interpreter i = new Interpreter();
		i.set("foo", 5);
		i.set("date", new Date());
		
		Date date = (Date)i.get("date");
		
		i.eval("bar = foo*10");
		
		System.out.println(i.get("global.bar"));
		System.out.println(date);
		
	}
}
