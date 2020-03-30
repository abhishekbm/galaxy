package com.shakestudios.knowledge;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Dictionary {
	
	private Map<String, String> dictionaryMap = new HashMap<String, String>();
	
	public void addCommandAndValue(String commandName, String correspondingCommand){
		this.dictionaryMap.put(commandName.toUpperCase(), correspondingCommand);
	}
	
	public Set<String> getKnownWords(){
		return this.dictionaryMap.keySet();
	}
	
	public String getSymbolForWord(String name){
		return dictionaryMap.get(name);
	}
	
}
