package model;

import java.util.ArrayList;
import java.util.List;


public class MyGeneric<T extends Number>{

	private T t;
	
	public void change(T t){
		this.t = t;
	}
	
	public T get(){
		return t;
	}
}
