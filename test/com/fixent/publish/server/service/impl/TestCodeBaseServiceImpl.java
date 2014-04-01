package com.fixent.publish.server.service.impl;

public class TestCodeBaseServiceImpl {

	public static void generateCode() {

		CodeBaseServiceImpl impl = new CodeBaseServiceImpl();
		String code = impl.generateCode("CHF");
		System.out.println(code);
	}

	public static void main(String[] args) {

		TestCodeBaseServiceImpl.generateCode();
	}

}
