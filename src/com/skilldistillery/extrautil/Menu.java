package com.skilldistillery.extrautil;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu implements Menuable {
	public static Scanner input;
	
	private List<Menuable> items;
	private Menuable exit;
	private String menuName;
	private boolean executing;
	
	protected int userChoice;
	
	public static void doNothing() {}

	public Menu(String menuName) {
		this.menuName = menuName;
		this.items = new ArrayList<>();
		this.exit = new MenuItem("Back",this::stopExecuting);
	}

	public void addItem(Menuable item) {
		items.add(item);
	}
	

	public void printMenu() {
		System.out.println(menuName + ":");
		for (int i = 0; i < items.size(); i++) {
			System.out.println("\t" + (i + 1) + ") " + items.get(i).menuString());
		}
		System.out.println("\t" + (items.size() + 1) + ") " + exit.menuString());

	}

	@Override
	public String menuString() {
		return menuName;
	}

	@Override
	public void execute() {
		executing = true;
		while (executing) {
			printMenu();
			getChoice();
			if(userChoice == items.size()) {
				exit.execute();
				break;
			}else {
				items.get(userChoice).execute();
			}
		}
		executing = false;
	}

	public void getChoice() {
		userChoice = -1;
		while (userChoice == -1) {
			String userInput = input.nextLine();
			try {
				userChoice = Integer.parseInt(userInput);
				if (userChoice < 1 || userChoice > items.size() + 1) {
					System.out.println(userChoice + " is not a valid choice.");
					userChoice = -1;
				}
			} catch (NumberFormatException e) {
				System.out.println("\'" + userInput + "\' is not a number.");
			}
		}
		userChoice -=1;
	}

	public void stopExecuting() {
		this.executing = false;
	}
	
	public void overrideExit(Menuable exit) {
		this.exit = exit;
	}

}
