package test.parser;

import org.junit.Assert;
import org.junit.Test;

import com.shakestudios.parser.QuestionParser;
import com.shakestudios.questions.HowManyQuestion;
import com.shakestudios.questions.HowMuchQuestion;
import com.shakestudios.questions.IQuestion;
import com.shakestudios.questions.UnidentifiedQuestion;

public class QuestionParserTest {
	
	@Test
	public void doesAnswerHowMuchQuestionCorrect(){
		
		QuestionParser parser = new QuestionParser("How much is something of something?");

        IQuestion questionTypes = parser.toQuestion();

        Assert.assertTrue(questionTypes instanceof HowMuchQuestion);
	}
	
	@Test
	public void doesAnswerHowManyQuestionCorrect(){
		
		QuestionParser parser = new QuestionParser("  How Many credits is gold?");

        IQuestion questionTypes = parser.toQuestion();

        Assert.assertTrue(questionTypes instanceof HowManyQuestion);
	}
	
	@Test
	public void doesAnswerUnIdentifiedQuestionCorrect(){
		
		QuestionParser parser = new QuestionParser("Do you know something?");

        IQuestion questionTypes = parser.toQuestion();

        Assert.assertTrue(questionTypes instanceof UnidentifiedQuestion);

	}
	
	

}
