package com.net.domain;

public class ObjectFactory {
	
private static ObjectFactory instance = new ObjectFactory();
	
	private ObjectFactory(){}
	
	public static ObjectFactory getInstance(){
	      return instance;
	}
	
	public Student createStudent() {
		return new Student();
	}

}
