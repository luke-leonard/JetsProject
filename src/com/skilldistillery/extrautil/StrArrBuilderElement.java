package com.skilldistillery.extrautil;

public class StrArrBuilderElement {
	private String prompt;
	private String[] validInputs;
	
	StrArrBuilderElement(String prompt, String ... validInputs){
		this.prompt = prompt;
		this.validInputs = validInputs;
	}
	
	public String getPrompt() {
		return prompt;
	}
	
	public String[] getValidInputs() {
		return validInputs;
	}
	
}
