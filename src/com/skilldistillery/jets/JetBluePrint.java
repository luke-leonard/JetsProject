package com.skilldistillery.jets;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JetBluePrint{
	private Map<String, String> jetCharacteristics;

	public JetBluePrint() {
		super();
		this.jetCharacteristics = new HashMap<>();
	}
	
	public JetBluePrint(String inputString) {
		super();
		this.jetCharacteristics = new HashMap<>();
		String[] arguments = inputString.split(",");
		String regex = "\"(\\w+)\":(.*)";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher;

		for(String field: arguments) {
			matcher = pattern.matcher(field);
			if(matcher.find()) {
				jetCharacteristics.put(matcher.group(1), matcher.group(2));
			}
		}
	}

	public void setJetCharacteristics(Map<String, String> jetCharacteristics) {
		this.jetCharacteristics = jetCharacteristics;
	}
	
	public Map<String, String> getJetCharacteristics() {
		return jetCharacteristics;
	}
	
	public void addChacteristic(String name, String feature) {
		jetCharacteristics.put(name, feature);
	}
	public void addChacteristic(String name, Integer feature) {
		jetCharacteristics.put(name, feature.toString());
	}
	public void addChacteristic(String name, Double feature) {
		jetCharacteristics.put(name, feature.toString());
	}
	public void addChacteristic(String name, Long feature) {
		jetCharacteristics.put(name, feature.toString());
	}
	public void addChacteristic(String name, Pilot feature) {
		jetCharacteristics.put(name, feature.getName());
	}

	public String saveToString() {
		StringBuilder sb = new StringBuilder();
		Set<String> keys = jetCharacteristics.keySet();
		for(String key:keys) {
			sb.append("\"" + key + "\":" + jetCharacteristics.get(key)+",");
		}
		return sb.toString();
	}


}
