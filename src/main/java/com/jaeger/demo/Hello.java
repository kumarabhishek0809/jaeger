package com.jaeger.demo;

public class Hello {
	public static void main(String[] args) {

		if (args.length != 1) {
			throw new IllegalArgumentException("One Arguement Is Mandatory !!!");
		}
		String helloServiceArgs = args[0];
		new Hello().sayHello(helloServiceArgs);
	}

	private void sayHello(String helloServiceArgs) {
		// TODO Auto-generated method stub
		String format = String.format("Hello, %s !", helloServiceArgs);
		System.out.println(format);
	}

}
