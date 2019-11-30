package com.skilldistillery.extrautil;

public class MenuItem implements Menuable {
	private String prompt;
	private Runnable function;
	
	
	
	public MenuItem(String prompt, Runnable function) {
		super();
		this.prompt = prompt;
		this.function = function;
	}

	@Override
	public String menuString() {
		return prompt;
	}
	
	@Override
	public void execute() {
		function.run();
	}
	
	
}
