package com.auxiliares;

import com.artMessageBox.MessageBox;
import totalcross.sys.Convert;
import totalcross.sys.Settings;
import totalcross.ui.Container;
import totalcross.ui.MainWindow;
import totalcross.ui.font.Font;
import totalcross.ui.gfx.Color;
import totalcross.ui.image.Image;

public class Auxiliares {
	
	
	public static final String NOMEAPP       = "Solucao";
	public static final String VERSAO		 = "1.0";
	public static final String TELEFONE 	 = "(62) 9259-7360";
	public static final String NOMESISTEMA   = "SOLUCAO-MOBILE - BETA";
	public static final String DESCRICAO     = "Copyright©2020-Todos os direitos reservados";
	public static final String ADM			 = "true";
	public static final String SENHAADM		 = "solucao@2031";

	public static  String NOMEUSUARIO		 = "";

	public static int codigoVendedor;
	public static int fonteSizeTab = Font.NORMAL_SIZE;
	public static int fonteSizeGrid = Font.NORMAL_SIZE;
//	private MenuBar mbar;
	public static int defaultBackColor = Color.getRGB(11, 43, 149);
	public static int defaultForeColor = Color.WHITE;
	public static int lstSelectedColor = Color.getRGB(89, 85, 253);
	public static int defaultSelectedButton = Color.getRGB(229, 132, 3);
	public static int defaultForeColorButton = Color.getRGB(54, 54, 65);// Color.getRGB(11,43,149);

	public static String usuario, nomeVendedor;
	public static int codigoUnidade;

	public static int secondStripeColor = Color.getRGB(173, 216, 230);
	public static int firstStripeColor = Color.getRGB(255, 255, 255);
	public static int highlightColor = Color.getRGB(0, 191, 255);
	public static int checkColor = Color.getRGB(0, 0, 0);
	public static int captionsBackColor = Color.getRGB(176, 224, 230);
	public static int backColorGrid = Color.getRGB(176, 224, 230);
	public static int borderColor3DG = Color.getRGB(50, 85, 131);
	public static int defaultBackColorButton = Color.getRGB(101, 127, 154);
	public static int fonteSize = Font.NORMAL_SIZE;
	public static String CODIGOVENDEDOR;
	//Novo 
	public static int defaultBackColorNovo = Color.getRGB(11, 43, 149);
	public static int defaultForeColorNovo = 16777215;
	public static int secondStripeColorNovo = Color.getRGB(240, 240, 240);
	public static int firstStripeColorNovo = Color.getRGB(255, 255, 255);
	public static int highlightColorNovo = Color.getRGB(0, 191, 255);
	public static int checkColorNovo = Color.getRGB(0, 0, 0);
	public static int captionsBackColorNovo = Color.getRGB(64, 95, 140);
	public static int backColorGridNovo = Color.getRGB(176, 224, 230);
	public static int defaultSelectedButtonNovo = Color.getRGB(191, 191, 191);
	public static int defaultBackColorButtonNovo = Color.getRGB(251, 251, 251);
	public static int defaultForeColorButtonNovo = Color.getRGB(83, 102, 113);
	public static int defaultForeColorGridNovo = Color.getRGB(211, 211, 211);

	public static int messagebox(String titulo, String msg) {
		msg = Convert.insertLineBreak(2 * (Settings.screenWidth / 4), MainWindow.getMainWindow().fm, msg);
		MessageBox amb = new MessageBox(titulo, msg);
		amb.setRect(Container.CENTER, Container.CENTER, Container.SCREENSIZE + 70, Container.SCREENSIZE + 40);
		amb.popup();
		return amb.getPressedButonIndex();
	}

	public static int messageBox(String titulo, String msg, String[] captions) {
		msg = Convert.insertLineBreak(2 * (Settings.screenWidth / 4), MainWindow.getMainWindow().fm, msg);
		MessageBox amb = new MessageBox(titulo, msg, captions);
		amb.setRect(Container.CENTER, Container.CENTER, Container.SCREENSIZE + 70, Container.SCREENSIZE + 40);
		amb.popup();
		return amb.getPressedButonIndex();
	}
	
	public static Image getLogo() {

		try {
			int w = Settings.screenWidth;

			if (w >= 240 && w < 320)
				return new Image("img/cadastrarProdutoEstoque.png");
			else if (w >= 320)
				return new Image("img/cadastrarProdutoEstoque.png");
			else
				return new Image("img/cadastrarProdutoEstoque.png");
		} catch (Exception e) {
			return null;
		}

	}

	public static Font getFontDetalhe() {
		return Font.getFont(Font.DEFAULT, false, Auxiliares.getFontSize() - 5);
	}

	public static Font getFontDetalheCelulaCima() {
		return Font.getFont(Font.DEFAULT, false, getFontSize());
	}

	public static Font getFontDetalheCelulaAbaixo() {
		return Font.getFont(Font.DEFAULT, true, getFontSize());
	}

	public static Font getFontDetalheBold() {
		return Font.getFont(Font.DEFAULT, true, getFontSize());// aarq
	}

	public static Font getFontPequenaBold() {
		return Font.getFont(Font.DEFAULT, true, getFontSize() - 10);// aarq
	}

	public static int getFontSize() {
		return fonteSize;
	}

	public static void setFontSize(int f) {
		fonteSize = f;
	}

	public static void setFontSizeTab(int f) {
		fonteSizeTab = f;
	}

	public static void setFontSizeGrid(int f) {
		fonteSizeGrid = f;
	}

	public static int getFontSizeTab() {
		return fonteSizeTab;
	}

	public static int getFontSizeGrid() {
		return fonteSizeGrid;
	}

	public static Font getFontBold() {
		return getFontNormal().asBold();
	}

	public static Font getFontNormal() {
		return Font.getFont(Font.DEFAULT, false, Auxiliares.getFontSize());

	}

	public static Font getFontGrande() {
		return Font.getFont(Font.DEFAULT, false, Auxiliares.getFontSize() + 10);

	}

	public static Font getFontPequena() {
		return Font.getFont(Font.DEFAULT, false, Auxiliares.getFontSize() - 4);

	}

	public static Font getFontGrid() {

		return Font.getFont(Font.DEFAULT, false, Auxiliares.getFontSizeGrid());
	}
	
	public static Font getFontGridBold() {
		return Font.getFont(Font.DEFAULT, true, Auxiliares.getFontSizeGrid());
	}

	public static Font getFontTab() {
		return Font.getFont(Font.DEFAULT, false, Auxiliares.getFontSizeTab());
	}

}
