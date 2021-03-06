package test.knowledge;

import junit.framework.Assert;

import org.junit.Test;

import com.shakestudios.knowledge.Dictionary;
import com.shakestudios.knowledge.PlanetSymbols;
import com.shakestudios.knowledge.Symbols;

public class DictionaryTest {

	@Test
	public void canGetValidWordFromDictionary(){
		
		PlanetSymbols planetSymbols = new PlanetSymbols();
		
		Symbols symbols = new Symbols(planetSymbols.getPlanetSymbolMap());

		Dictionary dictionary = new Dictionary();
		
		dictionary.addCommandAndValue("glob", "I");
		
		Long value = symbols.getSymbolValueByName(dictionary.getSymbolForWord("glob"));
		
		Assert.assertEquals("1", value.toString());
		
	}
	
	@Test
	public void canIgnoreInValidWordFromDictionary(){
		
		PlanetSymbols planetSymbols = new PlanetSymbols();
		
		Symbols symbols = new Symbols(planetSymbols.getPlanetSymbolMap());
		
		Dictionary dictionary = new Dictionary();
		
		dictionary.addCommandAndValue("glob", "P");
		
		Long value = symbols.getSymbolValueByName(dictionary.getSymbolForWord("glob"));
		
		Assert.assertNull(value);
		
	}
	
}
