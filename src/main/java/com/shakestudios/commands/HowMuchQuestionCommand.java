package com.shakestudios.commands;

import com.shakestudios.Merchant;


public class HowMuchQuestionCommand implements ICommand {

	public void executeCommand(Merchant merchant, String questionString) {
		merchant.answerHowMuchQuestion(questionString);
	}

}
