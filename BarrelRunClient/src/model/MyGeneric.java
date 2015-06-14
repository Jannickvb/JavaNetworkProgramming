package model;

import java.util.ArrayList;
import java.util.List;


public class MyGeneric<T extends Number> extends ArrayList<T>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	List<? extends T> values;

	public MyGeneric(List<? extends T> values) {
		super();
		this.values = values;
	}
		
}
