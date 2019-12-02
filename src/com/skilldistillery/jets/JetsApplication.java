package com.skilldistillery.jets;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.skilldistillery.extrautil.Menu;
import com.skilldistillery.extrautil.MenuFromList;
import com.skilldistillery.extrautil.MenuItem;

public class JetsApplication {
	private Scanner input;
	private AirField airfield;
	private Menu startMenu;
	private Menu mainMenu;
	
	private static final String saveFile = "Assets/SavedGames.txt";
	
	private List<String> savedGames = new ArrayList<>();

	public static void main(String[] args) {
		JetsApplication app = new JetsApplication();

		app.run();
		app.close();
	}

	public JetsApplication() {
		super();
		this.input = new Scanner(System.in);
		this.airfield = new AirField();

		Menu.input = this.input;
		String line = "";
		
		try (BufferedReader br = new BufferedReader(new FileReader(JetsApplication.saveFile))) {
			while ((line = br.readLine()) != null) {
				savedGames.add(line);
			}
			
		} catch (FileNotFoundException e) {
			System.out.println(e);
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println(e);
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(line);
			e.printStackTrace();
		}
		generateMenus();
	}
	
	public void save() {
		System.out.println("Save as what");
		String fileName = "Assets/" +input.nextLine() + ".txt";
		airfield.save(fileName);
		savedGames.add(fileName);
		generateMenus();
	}
	public void runMainMenu() {
		generateMenus();
		this.mainMenu.execute();
	}
	
	public void createNew() {
		printInstructions();
		this.airfield = new AirField();
		runMainMenu();
	}
	public void load() {
		String selection = MenuFromList.getSelection();
		airfield = new AirField(selection);
		runMainMenu();
	}
	public void loadDefault() {
		airfield = new AirField("Assets/defaultGame.txt");
		runMainMenu();
	}

	public void printInstructions() {
		System.out.println("Welcome!");
	}
	
	public void run() {
		startMenu.execute();
	}

	public String userInput(String prompt) {
		System.out.println(prompt);
		return userInput();
	}

	public String userInput() {
		return input.next();
	}

	public void close() {
		input.close();
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(JetsApplication.saveFile))) {
			for(String string:this.savedGames) {
				bw.write(string + "\n");
			}
			

		} catch (FileNotFoundException e) {
			System.out.println(e);
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println(e);
			e.printStackTrace();
		}
	}

	public AirField getAirfield() {
		return airfield;
	}

	public void setAirfield(AirField airfield) {
		this.airfield = airfield;
	}

	public Menu getStartMenu() {
		return startMenu;
	}

	public void setStartMenu(Menu startMenu) {
		this.startMenu = startMenu;
	}

	public Menu getMainMenu() {
		return mainMenu;
	}

	public void setMainMenu(Menu mainMenu) {
		this.mainMenu = mainMenu;
	}
	public void addJet() {
		Jet a = new CargoPlane("C17", 100.0, 322, 10000000L, 5);

		airfield.getGroundFleet().addJet(a);
	}

	public void generateMenus() {
		this.startMenu = new Menu("Start menu");
		startMenu.addItem(new MenuItem("Create New", this::createNew));
		startMenu.addItem(new MenuItem("Load Default Game", this::loadDefault));		
		startMenu.addItem(new MenuFromList("load saved game", savedGames, this::load));
		startMenu.overrideExit(new MenuItem("Quit", Menu::doNothing));

		Fleet groundFleet = airfield.getGroundFleet();
		Fleet airFleet = airfield.getAirFleet();
		Fleet kiaFleet = airfield.getKIAFleet();

		this.mainMenu = new Menu("Main menu");
		
		mainMenu.addItem(new MenuItem("See Money",airfield::printUserMoney));

		Menu listingSubMenu = new Menu("List Fleets");
		listingSubMenu.addItem(new MenuItem("List ground fleet", groundFleet::printFleet));
		listingSubMenu.addItem(new MenuItem("List air fleet", airFleet::printFleet));
		listingSubMenu.addItem(new MenuItem("List kia fleet", kiaFleet::printFleet));
		mainMenu.addItem(listingSubMenu);

		mainMenu.addItem(new MenuItem("Fly all Jets", airfield::scramble));

		Menu fastestJetsSubMenu = new Menu("View fastest jets");
		fastestJetsSubMenu.addItem(new MenuItem("All Time Fastest Jet", airfield::printAllTimeFastestJet));
		fastestJetsSubMenu.addItem(new MenuItem("Ground Fleet Fastest Jets", groundFleet::printFastestJets));
		fastestJetsSubMenu.addItem(new MenuItem("Air Fleet Fastest Jets", airFleet::printFastestJets));
		fastestJetsSubMenu.addItem(new MenuItem("KIA Fleet Fastest Jets", kiaFleet::printFastestJets));
		mainMenu.addItem(fastestJetsSubMenu);
		
		Menu longestRangeJetsSubMenu = new Menu("View jets with longest range");
		longestRangeJetsSubMenu.addItem(new MenuItem("All Time Longest Range Jet", airfield::printAllTimeLongestRangeJet));
		longestRangeJetsSubMenu.addItem(new MenuItem("Ground Fleet Longest Range Jets", groundFleet::printLongestRangeJets));
		longestRangeJetsSubMenu.addItem(new MenuItem("Air Fleet Longest Range Jets", airFleet::printLongestRangeJets));
		longestRangeJetsSubMenu.addItem(new MenuItem("KIA Fleet Longest Range Jets", kiaFleet::printLongestRangeJets));
		mainMenu.addItem(longestRangeJetsSubMenu);
		
		
		mainMenu.addItem(new MenuItem("Build Jet",airfield::jetBuilder));
		mainMenu.addItem(new MenuItem("Fight for payload",airfield::causeAction));

		
		Menu mainExitMenu = new Menu("Quit Game");
		mainExitMenu.addItem(new MenuItem("Save and Quit", this::save,mainExitMenu::stopExecuting));
		mainExitMenu.overrideExit(new MenuItem("Quit", Menu::doNothing));
		mainMenu.overrideExit(mainExitMenu);
	}
	
}
