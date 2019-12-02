package com.skilldistillery.extrautil;

import java.util.ArrayList;
import java.util.List;

public class MenuStrArrBuilder implements Menuable {
	List<StrArrBuilderElement> inputs;
	String name;
	String[] returnString;
	boolean returnStringIsValid;
	
	public String[] getReturnString() {
		return returnString;
	}
	
	public MenuStrArrBuilder(String name) {
		this.name = name;
		inputs = new ArrayList<>();
		returnStringIsValid = false;
	}
	
	@Override
	public String menuString() {
		return name;
	}
	
	public void addElement(StrArrBuilderElement input) {
		inputs.add(input);
		returnStringIsValid = false;
	}

	@Override
	public void execute() {
		returnString = new String[inputs.size()];
		
		for(int i = 0; i < inputs.size();i++) {
			boolean invalidResponse = true;
			while(invalidResponse) {
				System.out.println(inputs.get(i).getPrompt());
				String userInput = Menu.input.nextLine();
				for(int j = 0; j < inputs.get(i).getValidInputs().length;j++) {
					if(inputs.get(i).getValidInputs()[j].equalsIgnoreCase(userInput)) {
						returnString[i] = userInput.toUpperCase();
						invalidResponse = false;
						break;
					}
				}
			}
		}
		returnStringIsValid = true;
	}

}
