package test.parser;

import org.junit.Assert;
import org.junit.Test;

import com.shakestudios.commands.ICommand;
import com.shakestudios.commands.QuestionCommand;
import com.shakestudios.knowledge.Dictionary;
import com.shakestudios.knowledge.PlanetSymbols;
import com.shakestudios.knowledge.Symbols;
import com.shakestudios.parser.CommandParser;

public class CommandParserTest {
	
	@Test
	public void doesIdentifyQuestionCommandCorrectly(){
		
		Dictionary dictionary = new Dictionary();
		
		CommandParser parser = new CommandParser("how much is pish tegj glob glob ?", dictionary);
		
		PlanetSymbols planetSymbols = new PlanetSymbols();
		Symbols symbols = new Symbols(planetSymbols.getPlanetSymbolMap());

        ICommand questionTypes = parser.toCommand(symbols);

        Assert.assertTrue(questionTypes instanceof QuestionCommand);
	}	

}
