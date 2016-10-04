package com.kjellvos.twod.components;

import com.badlogic.ashley.core.Component;

public class TalkComponent implements Component {
	public String textToSay;
	
	public TalkComponent(String textToSay){
		this.textToSay = textToSay;
	}
}
