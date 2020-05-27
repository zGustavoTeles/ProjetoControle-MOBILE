package com.auxiliares;

import com.artMessageBox.ArtMessageBox;
import totalcross.sys.Convert;
import totalcross.sys.Settings;
import totalcross.ui.Container;
import totalcross.ui.Grid;
import totalcross.ui.MainWindow;
import totalcross.ui.dialog.MessageBox;
import totalcross.ui.gfx.Color;

public class Auxiliares {
	
	
	public static final String NOMEAPP       = "CONTROLE";
	public static final String VERSAO		 = "1.05";
	public static final String TELEFONE 	 = "(62) 98163-5089";
//	public static final String NOMESISTEMA   = "CONTROLE_MOBILE - BETA";
//	public static final String NOMESISTEMA   = "CONTROLE_PC - BETA";
	public static final String NOMESISTEMA   = "CONTROLE_PC - VENDAS";
	public static final String DESCRICAO     = "Copyright©2020-Todos os direitos reservados";
	public static final String ADM			 = "true";
	public static final String SENHAADM		 = "CONTROLE@2031";
	

	public static int artMsgbox(String titulo, String msg) {
		msg = Convert.insertLineBreak(2 * (Settings.screenWidth / 4), MainWindow.getMainWindow().fm, msg);
		ArtMessageBox amb = new ArtMessageBox(titulo, msg);
		amb.setRect(Container.CENTER, Container.CENTER, Container.SCREENSIZE + 50, Container.SCREENSIZE + 50);
		amb.popup();
		return amb.getPressedButonIndex();
	}

	public static int artMsgbox(String titulo, String msg, String[] captions) {
		msg = Convert.insertLineBreak(2 * (Settings.screenWidth / 4), MainWindow.getMainWindow().fm, msg);
		ArtMessageBox amb = new ArtMessageBox(titulo, msg, captions);
		amb.setRect(Container.CENTER, Container.CENTER, Container.SCREENSIZE + 50, Container.SCREENSIZE + 50);
		amb.popup();
		return amb.getPressedButonIndex();
	}

//	public static int artMsgbox(String titulo, Grid grid, String ultimoPreco) {
//		ArtMessageBox amb = new ArtMessageBox(titulo, grid, ultimoPreco);
//		amb.setRect(Container.CENTER, Container.CENTER, Container.SCREENSIZE + 80,
//				titulo.equalsIgnoreCase("info") ? Container.SCREENSIZE + 75 : Container.SCREENSIZE + 90);
//		amb.transitionEffect = Container.TRANSITION_NONE;
//		amb.transparentBackground = true;
//		amb.popup();
//		return amb.getPressedButonIndex();
//	}

}
