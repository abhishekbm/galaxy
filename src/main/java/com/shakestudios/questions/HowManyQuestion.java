package com.shakestudios.questions;

import com.shakestudios.Merchant;

public class HowManyQuestion implements IQuestion {

	public void answerQuestion(Merchant merchant, String questionString) {
		merchant.answerHowManyQuestion(questionString);
	}

}
