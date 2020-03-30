package com.shakestudios.commands;

import com.shakestudios.Merchant;

public class HowManyQuestionCommand implements ICommand {

	public void executeCommand(Merchant merchant, String questionString) {
		merchant.answerHowManyQuestion(questionString);
	}

}
