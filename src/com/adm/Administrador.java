package com.adm;


import com.auxiliares.Auxiliares;
import com.configuracoes.Apagar;
import com.configuracoes.Cadastrar;

import totalcross.ui.event.ControlEvent;
import totalcross.ui.event.Event;
import totalcross.ui.gfx.Color;
import totalcross.ui.Button;

public class Administrador extends totalcross.ui.Window {

	private Button		    btnCadastrarEmpresa;
	private Button 			btnApagarEmpresa;
	private Button 			btnVoltar;

	public Administrador() {
		setBackColor(0x003366);
		initUI();
	}

	public void initUI() {

		try {

			btnCadastrarEmpresa = new Button("CADASTRAR");
			add(btnCadastrarEmpresa);
			btnCadastrarEmpresa.setRect(CENTER, TOP + 150, width - 200, SCREENSIZE + 20);
			btnCadastrarEmpresa.setBackColor(0x003366);
			btnCadastrarEmpresa.setForeColor(Color.WHITE);
			
			btnApagarEmpresa = new Button("APAGAR");
			add(btnApagarEmpresa);
			btnApagarEmpresa.setRect(CENTER, AFTER, width - 200, SCREENSIZE + 20);
			btnApagarEmpresa.setBackColor(0x003366);
			btnApagarEmpresa.setForeColor(Color.WHITE);

			btnVoltar = new Button("VOLTAR");
			add(btnVoltar);
			btnVoltar.setRect(RIGHT, BOTTOM, SCREENSIZE - 4, PREFERRED + 15);
			btnVoltar.setBackColor(0x003366);
			btnVoltar.setForeColor(Color.WHITE);
			
			reposition();
			
		} catch (Exception e) {
			Auxiliares.messagebox("ERRO", "Erro ao construir o menu cadastro\n" + e);

		}

	}

	public void onEvent(Event evt) {

		try {
			switch (evt.type) {
			case ControlEvent.PRESSED:

				if (evt.target == btnVoltar) {
					unpop();

				}else if (evt.target == btnCadastrarEmpresa) {
					Cadastrar administrador = new Cadastrar();
					administrador.popup();

				} else if(evt.target == btnApagarEmpresa) {
					Apagar apagar = new Apagar();
					apagar.popup();
				}

			}

		} catch (Exception e) {
			Auxiliares.messagebox("CONTROLE", "Erro na validação do menu cadastro\n " + e);
		}

	}

}
