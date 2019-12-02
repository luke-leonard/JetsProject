package com.skilldistillery.extrautil;

public class MenuItem implements Menuable {
	public static void waitOneSec() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	private String prompt;
	private Runnable function;
	private Runnable function2;
	
	
	
	public MenuItem(String prompt, Runnable function) {
		super();
		this.prompt = prompt;
		this.function = function;
		this.function2 = MenuItem::waitOneSec;
	}	
	public MenuItem(String prompt, Runnable function, Runnable function2) {
		super();
		this.prompt = prompt;
		this.function = function;
		this.function2 = function2;
	}

	@Override
	public String menuString() {
		return prompt;
	}
	
	@Override
	public void execute() {
		function.run();
		function2.run();
	}
	
	
}
