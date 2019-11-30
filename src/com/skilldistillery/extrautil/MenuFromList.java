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
	
	public MenuFromList(String name, String fileName, Runnable function) {
		super(name);
		this.function = function;
		this.stringList = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			String line = "";
			while ((line = br.readLine()) != null) {
				stringList.add(line);
				addItem(new MenuItem(line, function));
			}
		} catch (FileNotFoundException e) {
			System.out.println(e);
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println(e);
			e.printStackTrace();
		}
		
	}

	public void MenuFromListFunction() {
		userSelection = stringList.get(userChoice);
		function.run();
	}
	
	

}
