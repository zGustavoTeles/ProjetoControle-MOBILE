package com.configuracoes;

import com.adm.Administrador;
import com.adm.Cadastrar;
import com.adm.ValidaAdministrador;
import com.auxiliares.Auxiliares;
import com.email.Email;
import com.informacao.Informacao;

import nx.componentes.ArtButton;
import totalcross.ui.dialog.MessageBox;
import totalcross.ui.event.ControlEvent;
import totalcross.ui.event.Event;
import totalcross.ui.gfx.Color;

public class Configuracoes extends totalcross.ui.Window {

	private ArtButton		    btnInformacoes;
	private ArtButton 			btnApagarDados;
	private ArtButton			btnConfigEmpresa;
	private ArtButton			btnSuporte;
	private ArtButton			btnAdministrador;
	private ArtButton 			btnVoltar;

	public Configuracoes() {
		setBackColor(0x003366);
		initUI();
	}

	public void initUI() {

		try {

			btnInformacoes = new ArtButton("INFORMAÇÕES");
			add(btnInformacoes);
			btnInformacoes.setRect(CENTER, TOP + 30, width - 200, SCREENSIZE + 17);
			btnInformacoes.setBackColor(0x003366);
			btnInformacoes.setForeColor(Color.WHITE);
			
			btnConfigEmpresa = new ArtButton("EMPRESA");
			add(btnConfigEmpresa);
			btnConfigEmpresa.setRect(CENTER, AFTER, width - 200, SCREENSIZE + 17);
			btnConfigEmpresa.setBackColor(0x003366);
			btnConfigEmpresa.setForeColor(Color.WHITE);

			btnApagarDados = new ArtButton("APAGAR VENDA");
			add(btnApagarDados);
			btnApagarDados.setRect(CENTER, AFTER, width - 200, SCREENSIZE + 17);
			btnApagarDados.setBackColor(0x003366);
			btnApagarDados.setForeColor(Color.WHITE);
			
			btnSuporte = new ArtButton("SUPORTE");
			add(btnSuporte);
			btnSuporte.setRect(CENTER, AFTER, width - 200, SCREENSIZE + 17);
			btnSuporte.setBackColor(0x003366);
			btnSuporte.setForeColor(Color.WHITE);
			
			btnAdministrador = new ArtButton("ADMINISTRADOR");
			add(btnAdministrador);
			btnAdministrador.setRect(CENTER, AFTER, width - 200, SCREENSIZE + 17);
			btnAdministrador.setBackColor(0x003366);
			btnAdministrador.setForeColor(Color.WHITE);

			btnVoltar = new ArtButton("VOLTAR");
			add(btnVoltar);
			btnVoltar.setRect(RIGHT, BOTTOM, SCREENSIZE - 4, PREFERRED + 15);
			btnVoltar.setBackColor(0x003366);
			btnVoltar.setForeColor(Color.WHITE);
			
			reposition();
			
		} catch (Exception e) {
			Auxiliares.artMsgbox("ERRO","Erro ao construir a tela configuracoes\n" + e);

		}

	}

	public void onEvent(Event evt) {

		try {
			switch (evt.type) {
			case ControlEvent.PRESSED:

				if (evt.target == btnVoltar) {
					unpop();

				} else if (evt.target == btnInformacoes) {
					Informacao informacao = new Informacao();
					informacao.popup();

				} else if (evt.target == btnApagarDados) {
					ApagarVenda apagarDados = new ApagarVenda();
					apagarDados.popup();
					
				} else if (evt.target == btnConfigEmpresa) {
					InfoEmpresa alterarEmpresa = new InfoEmpresa();
					alterarEmpresa.popup();
					
				}else if (evt.target == btnSuporte) {
//					Email email = new Email();
//					email.popup();
					Auxiliares.artMsgbox("CONTROLE", "Esse módulo encontra-se indisponível\nCaso queira utilizá-lo entre em contato\nNúmero: (62) 9 8163-5089\nEmail: gmateus.dev@gmail.com");
					
				}else if (evt.target == btnAdministrador) {
//					ValidaAdministrador validaAdministrador = new ValidaAdministrador();
//					validaAdministrador.popup();
//					Administrador administrador = new Administrador();
//					administrador.popup();
					
					Administrador adm = new Administrador();
					adm.popup();
				}

			}

		} catch (Exception e) {
			Auxiliares.artMsgbox("ERRO", "Erro na validação da tela configuracoes\n" + e);
		}

	}

}
