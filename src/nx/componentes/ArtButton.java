package nx.componentes;

import totalcross.ui.Button;
import totalcross.ui.gfx.Color;
import totalcross.ui.image.Image;

public class ArtButton extends Button { 
	
	
	public int defaultBackColor = Color.getRGB(11,43,149);
	public int defaultForeColor = Color.WHITE;
	public int lstSelectedColor = Color.getRGB(89,85,253);
	public int defaultSelectedButton = Color.getRGB(229,132,3);
	
	public ArtButton(String text, Image img, int textPosition, int gap){
		super(text, img, textPosition, gap);
		
		this.defaultBackColor=Color.getRGB(101,127,154);
		this.defaultForeColor=Color.getRGB(54,54,65);
		this.border =totalcross.ui.Button.BORDER_3D_VERTICAL_GRADIENT;
		this.borderColor3DG = Color.getRGB(50, 85,131);
		this.topColor3DG = totalcross.ui.gfx.Color.getRGB(255, 255, 255);
		this.bottomColor3DG = totalcross.ui.gfx.Color.getRGB(255, 255, 255);
		this.borderWidth3DG = 2;
		
	}

	public ArtButton(String text) {
		super(text);
		  defaultBackColor = Color.getRGB(11,43,149);
		  defaultForeColor = Color.WHITE;
		  lstSelectedColor = Color.getRGB(89,85,253);
		  defaultSelectedButton = Color.getRGB(229,132,3);
		  
		  this.backColor = defaultBackColor;
		  this.foreColor = defaultForeColor;
	}

	public ArtButton(Image img) {
		super(img);
	}
	
	public void setBackColor(Color C){
		super.setBackColor(defaultBackColor);
	}
	
	public void setForeColor(Color C){
		super.setForeColor(defaultForeColor);
	}
	
	public void setColorSelectedButton(){
		super.setBackColor(defaultSelectedButton);
		super.setForeColor(defaultForeColor);
		//this.backColor = defaultSelectedButton;
		//this.foreColor = defaultForeColor;
	}
	
	public void setColorNormalButton(){
		super.setBackColor(defaultBackColor);
		super.setForeColor(defaultForeColor);
		//this.backColor = defaultBackColor;
		//this.foreColor = defaultForeColor;
	}
	
	public void setColorDarkGreen(){
		super.setBackColor(Color.darker(Color.GREEN));
		super.setForeColor(Color.WHITE);
	}

}
