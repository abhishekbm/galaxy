package com.shakestudios.commands;

import com.shakestudios.Merchant;

public class UnknownCommand implements ICommand {

	public void executeCommand(Merchant merchant, String commandString) {
		merchant.informUnknownCommand();
	}

}
