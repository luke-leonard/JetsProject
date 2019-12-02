package com.skilldistillery.extrautil;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MenuFromList extends Menu implements Menuable {
	private static String userSelection;
	private List<String> stringList;
	private Runnable function;
	
	public static String getSelection() {
		return userSelection;
	}
	
	public MenuFromList(String name, List<String> stringList, Runnable function) {
		super(name);
		this.function = function;
		this.stringList = stringList;
		for(String string : stringList) {
			this.addItem(new MenuItem(string, this::MenuFromListFunction));
		}
		
	}

	public void MenuFromListFunction() {
		userSelection = stringList.get(userChoice);
		function.run();
	}
	
	

}
