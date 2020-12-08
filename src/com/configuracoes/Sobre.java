package com.configuracoes;

import com.auxiliares.Auxiliares;
import com.bottom.BottomImg;

import totalcross.ui.Button;
import totalcross.ui.Edit;
import totalcross.ui.Grid;
import totalcross.ui.ImageControl;
import totalcross.ui.Label;
import totalcross.ui.dialog.MessageBox;
import totalcross.ui.event.ControlEvent;
import totalcross.ui.event.Event;
import totalcross.ui.event.GridEvent;
import totalcross.ui.gfx.Color;
import totalcross.ui.image.Image;

public class Sobre extends totalcross.ui.Window{
	
    private Button							btnVoltar;
    public Grid							    gridProdutos;
    public ImageControl 		            imgSobre;

    private Label 							lblNome;
    private Label 							lblVersaoSistema;
    private Label 							lblUsuario;
    
	public Sobre(){
		 setBackColor(0x1c355d);
		 initUI();
		 
	}
	
	public void initUI() {
		
		try {

            imgSobre = new ImageControl(new Image("img/sobreCapa.png"));
			imgSobre.scaleToFit = true;
			imgSobre.centerImage = true;
            add(imgSobre, CENTER, TOP, SCREENSIZE + 50, SCREENSIZE + 50);

			btnVoltar = BottomImg.imageWithText(new Image("img/voltar.png"), "Voltar", Button.BOTTOM);
			add(btnVoltar, RIGHT - 2, BOTTOM, SCREENSIZE + 25, PREFERRED + 10);
			btnVoltar.setBackColor(0x1c355d);
			btnVoltar.setForeColor(Color.WHITE);

			lblNome = new Label("Nome: " + Auxiliares.NOMESISTEMA);
			add(lblNome);
			lblNome.setRect(CENTER, CENTER, PREFERRED, PREFERRED);
			lblNome.setBackColor(0x1c355d);
            lblNome.setForeColor(Color.WHITE);

            lblVersaoSistema = new Label("Versão: " + Auxiliares.VERSAO);
			add(lblVersaoSistema);
			lblVersaoSistema.setRect(CENTER, AFTER + 10, PREFERRED, PREFERRED);
			lblVersaoSistema.setBackColor(0x1c355d);
            lblVersaoSistema.setForeColor(Color.WHITE);

            lblUsuario = new Label("Usuário: " + Auxiliares.NOMEUSUARIO);
			add(lblUsuario);
			lblUsuario.setRect(CENTER, AFTER + 10, PREFERRED, PREFERRED);
			lblUsuario.setBackColor(0x1c355d);
            lblUsuario.setForeColor(Color.WHITE);
            
			reposition();


		} catch (Exception e) {

			Auxiliares.messagebox("Erro", "Erro " + e);
		}
		
	}
	
	
	public void onEvent(Event evt) {
		try {
			switch (evt.type) {
			case ControlEvent.PRESSED:

				 if(evt.target == btnVoltar){
                    unpop();
                }
			switch (evt.type) {
			case GridEvent.SELECTED_EVENT:
			}

		}
	 } catch (Exception e) {
			/*
			Auxiliares.artMsgbox("ERRO", "Erro na validação da tela carrinho\n" + e);
			*/
		}

	}
	
}
