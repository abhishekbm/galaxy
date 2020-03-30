package com.shakestudios.commands;

import com.shakestudios.Merchant;

/**
 * Class to Declare the initial value of the new item.
 *
 */
public class DeclarationCommand implements ICommand {

	public void executeCommand(Merchant merchant, String commandString) {
		merchant.addToItemValue(commandString);
		
	}

}
