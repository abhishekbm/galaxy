package com.shakestudios.commands;

import com.shakestudios.Merchant;

public interface ICommand {
	public void executeCommand(final Merchant merchant, String commandString);
}
