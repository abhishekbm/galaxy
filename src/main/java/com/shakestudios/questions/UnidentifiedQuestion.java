package com.shakestudios.questions;

import com.shakestudios.Merchant;

public class UnidentifiedQuestion implements IQuestion{

	public void answerQuestion(Merchant merchant, String questionString) {
		merchant.answerUnIdentifiedQuestion(questionString);
	}

}
