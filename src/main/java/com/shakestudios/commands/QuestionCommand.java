package com.shakestudios.commands;

import com.shakestudios.Merchant;

public class QuestionCommand implements ICommand{

	public void executeCommand(Merchant merchant, String commandString) {
		merchant.delegateQuestion(commandString);
	}

}
