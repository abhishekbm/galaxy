package com.shakestudios.questions;

import com.shakestudios.Merchant;


public class HowMuchQuestion implements IQuestion {

	public void answerQuestion(Merchant merchant, String questionString) {
		merchant.answerHowMuchQuestion(questionString);
	}

}
