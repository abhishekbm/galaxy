package com.shakestudios;

import java.util.*;

import com.shakestudios.commands.ICommand;
import com.shakestudios.exceptions.RuleViolationException;
import com.shakestudios.exceptions.UnknownSymbolException;
import com.shakestudios.knowledge.Dictionary;
import com.shakestudios.knowledge.ItemValue;
import com.shakestudios.knowledge.Symbols;
import com.shakestudios.parser.CommandParser;
import com.shakestudios.parser.QuestionParser;
import com.shakestudios.questions.IQuestion;
import com.shakestudios.questions.QuestionTypes;

public class Merchant {

	private Symbols merchantSymbols;

	private Answers answersByMerchant;

	private Dictionary dictionary;

	private ItemValue itemValue;

	private SymbolRules symbolRules;

	String result;
	
	public Merchant(Symbols merchantSymbols) {
		this.merchantSymbols = merchantSymbols;
		this.dictionary = new Dictionary();
		this.itemValue = new ItemValue();
		this.answersByMerchant = new Answers();
		this.symbolRules = new SymbolRules();
		addToDictionary();
	}

	public Answers trade(String commandString) {

			ICommand merchantCommand = new CommandParser(commandString.toUpperCase(),
					dictionary).toCommand(this.getMerchantSymbols());
			merchantCommand.executeCommand(this, commandString.toUpperCase());

		Iterator it = this.answersByMerchant.getQuestionAnswerMap().entrySet().iterator();
		while (it.hasNext()) {
	        Map.Entry pairs = (Map.Entry)it.next();
	        System.out.println(pairs.getKey() + " : " + pairs.getValue());
	        // avoids a ConcurrentModificationException
	    }

		return answersByMerchant;
	}

	public void addToDictionary() {
		// add new words to dictionary with the corresponding value from Symbols
		List<String> list = Arrays.asList("glob is I","prok is V","pish is X","tegj is L");
		for(String str : list )
		{
			String[] commands = str.trim().split(" ");

			if (dictionary.getSymbolForWord(commands[0]) == null) {
				dictionary.addCommandAndValue(commands[0], commands[2]);
			} else {
				informReDefinition(commands[0]);
			}
			System.out.println(commands[0]
					+ " added to Dictionary. Value is : "
					+ this.merchantSymbols.getSymbolValueByName(dictionary
					.getSymbolForWord(commands[0])));

		}

	}

	private void informReDefinition(String name) {
		System.err.println("Redefined for " + name + ". Ignoring!");
	}

	public void addToItemValue(String commandString) {
		// add the value of the items to ItemValue
		System.out.println("Adding to ItemValue");
		
		// 1. Parse the input string.
		// 2. Get the amount from the dictionary.
		// 3. Learn a new word.
		// 4. Set the amount of single unit into ItemValue
		
		String[] words = commandString.split(" ");
		int i = 0;
		List<String> symbolsList = new ArrayList<String>();
		//get the valid words from dictionary
		while(dictionary.getSymbolForWord(words[i]) != null){
			symbolsList.add(words[i]);
			i++;
		}
		String itemName = words[i];
		
		//TODO: handle space.
		Long totalValue = Long.valueOf(words[i+2]);
		try{
			Long itemCount = this.symbolRules.calculateValue(this.merchantSymbols, convertToRoman(symbolsList));
			this.itemValue.addCommandAndValue(itemName, totalValue/itemCount);
		}catch(UnknownSymbolException usEx){
			System.err.println("Unknown symbol found during processing the command "+ commandString);
		}catch (RuleViolationException rvEx) {
			System.err.println("Symbol rule violated during processing the command "+ commandString);
		}
		

	}

	public void informUnknownCommand() {
		System.err.println("I dont knw...");
	}

	public void answerHowMuchQuestion(String questionString) {
		List<String> words = parseQuestion(questionString, QuestionTypes.HOW_MUCH_QUESTION);
		try{
			Long value = this.symbolRules.calculateValue(this.merchantSymbols, convertToRoman(words));
			if(value != null){
				answersByMerchant.addAnswer(questionString, "" +value);
			}else{
				answersByMerchant.addAnswer(questionString, "Something went wrong!!");
			}
		}catch(UnknownSymbolException usEx){
			System.err.println("Unknown symbol found during processing the question "+ questionString);
		}catch (RuleViolationException rvEx) {
			System.err.println("Symbol rule violated during processing the command "+ questionString);
		}

	}

	private List<String> parseQuestion(String questionString, QuestionTypes questionType) {
		String[] questionSymbol = questionString
				.split(questionType.getQuestionString());
		List<String> wordList = new ArrayList<String>();
		
		if (questionSymbol.length != 2) {
			answersByMerchant.addAnswer(questionString, "Something went wrong");
			return null;
		} else {
			String[] words = questionSymbol[1].replaceAll("\\?", "").split(" ");
			for(String word: words){
				wordList.add(word);
			}
			wordList.remove("");
			return wordList;
		}
	}
	
	private String convertToRoman(List<String> words) {
		String romanString = "";
		for(String word: words){
			if(dictionary.getSymbolForWord(word) != null){
				romanString += dictionary.getSymbolForWord(word);
			}else{
				System.err.println("No valid symbol found for : " + word);
				return null;
			}
		}
		return romanString;
	}

	public void answerHowManyQuestion(String questionString) {
		try{
			List<String> words = parseQuestion(questionString, QuestionTypes.HOW_MANY_QUESTION);
			int i = 0;
			while(dictionary.getSymbolForWord(words.get(i)) != null){
				i++;
			}
			String itemName = words.get(i);
			words = words.subList(0, i);
			Long itemCount = this.symbolRules.calculateValue(this.merchantSymbols, convertToRoman(words));
	
			if(itemValue.getKnownItems().contains(itemName)){
				answersByMerchant.addAnswer(questionString,
						""+itemValue.getValueOfItem(itemName, itemCount));
			}else{
				answersByMerchant.addAnswer(questionString,
						"No item definition found");
			}
		}catch(UnknownSymbolException usEx){
			System.err.println("Unknown symbol found during processing the command "+ questionString);
		}catch (RuleViolationException rvEx) {
			System.err.println("Symbol rule violated during processing the command "+ questionString);
		}
		
	}

	public void answerUnIdentifiedQuestion(String questionString) {
		System.err.println("answerUnIdentifiedQuestion");
		answersByMerchant.addAnswer(questionString,
				"No idea what you are talking about!");
	}

	public void delegateQuestion(String questionString) {
		IQuestion questionCommand = new QuestionParser(questionString)
				.toQuestion();
		questionCommand.answerQuestion(this, questionString);
	}

	public Symbols getMerchantSymbols() {
		return this.merchantSymbols;
	}

}
