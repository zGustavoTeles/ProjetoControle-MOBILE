package com.bottom;

import totalcross.ui.Button;
import totalcross.ui.image.Image;

public class BottomImg {

	public static Button imageOnly(Image img) {
		Button btn = new Button(img);
		btn.setBorder(Button.BORDER_NONE);
		return btn;
	}
	
	
	public static Button imageWithText(Image img,String text,int position) {
		Button btn = new Button(text, img, position, 0);
		btn.setBorder(Button.BORDER_NONE);
		return btn;
	}
	
	public static Button DefaultButton(String text) {
		Button btn = new Button(text);		
		return btn;
	}

}
