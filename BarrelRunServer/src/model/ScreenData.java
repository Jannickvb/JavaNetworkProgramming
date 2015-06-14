package model;

import java.util.ArrayList;
import java.util.List;

public class ScreenData<T>{
	
	private List<T> data;
	
	public ScreenData(){
		data = new ArrayList<>();
	}
	
	public void add(T t){
		data.add(t);
	}
	
	public void add(Integer index, T element){
		data.add(index, element);
	}
	
	public List<T> getAll(){
		return data;
	}
}
