package com.menu;

import com.auxiliares.Auxiliares;
import com.cadastrar.CadastrarProduto;
import com.configuracoes.Configuracoes;
import com.estoque.Estoque;
import com.relatorio.Relatorio;
import com.venda.Venda;
import nx.componentes.ArtButton;
import totalcross.ui.dialog.MessageBox;
import totalcross.ui.event.ControlEvent;
import totalcross.ui.event.Event;
import totalcross.ui.gfx.Color;

public class Menu extends totalcross.ui.Window {
	
	private ArtButton			 btnVenda;
	private ArtButton 		     btnCadastrar;
	private ArtButton		     btnEstoque;
	private ArtButton    		 btnRelatorio;
	private ArtButton 			 btnConfiguracao;
	private ArtButton			 btnVoltar;

	public Menu() {
		setBackColor(0x003366);
		initUI();

	}

	public void initUI() {

		try {

			btnVenda = new ArtButton("VENDA");
			add(btnVenda);
			btnVenda.setRect(CENTER, TOP + 5, width - 400, SCREENSIZE + 16);
			btnVenda.setBackColor(0x003366);
			btnVenda.setForeColor(Color.WHITE);

			btnCadastrar = new ArtButton("CADASTRAR");
			add(btnCadastrar);
			btnCadastrar.setBackColor(0x003366);
			btnCadastrar.setForeColor(Color.WHITE);
			btnCadastrar.setRect(CENTER, AFTER, width - 400, SCREENSIZE + 16, btnVenda);

			btnEstoque = new ArtButton("ESTOQUE");
			add(btnEstoque);
			btnEstoque.setBackColor(0x003366);
			btnEstoque.setForeColor(Color.WHITE);
			btnEstoque.setRect(CENTER, AFTER, width - 400, SCREENSIZE + 16, btnCadastrar);

			btnRelatorio = new ArtButton("RELATÓRIO");
			add(btnRelatorio);
			btnRelatorio.setBackColor(0x003366);
			btnRelatorio.setForeColor(Color.WHITE);
			btnRelatorio.setRect(CENTER, AFTER, width - 400, SCREENSIZE + 16, btnEstoque);
			
			btnConfiguracao = new ArtButton("CONFIGURAÇÃO");
			add(btnConfiguracao);
			btnConfiguracao.setBackColor(0x003366);
			btnConfiguracao.setForeColor(Color.WHITE);
			btnConfiguracao.setRect(CENTER, AFTER, width - 400, SCREENSIZE + 16, btnRelatorio);
			
			btnVoltar = new ArtButton("VOLTAR");
			add(btnVoltar);
			btnVoltar.setBackColor(0x003366);
			btnVoltar.setForeColor(Color.WHITE);
			btnVoltar.setRect(CENTER, AFTER, width - 400, SCREENSIZE + 16, btnConfiguracao);

			reposition();
			
		} catch (Exception e) {
			Auxiliares.artMsgbox("ERRO", "Erro ao construir tela menu\n" + e);

		}

	}

	public void onEvent(Event evt) {
		try {
			switch (evt.type) {
			case ControlEvent.PRESSED:

				if (evt.target == btnVenda) {
					Venda personagens = new Venda();
					personagens.popup();

				} else if (evt.target == btnCadastrar) {
					CadastrarProduto cartas = new CadastrarProduto();
					cartas.popup();

				} else if (evt.target == btnEstoque) {
					Estoque estoque = new Estoque();
					estoque.popup();

				} else if (evt.target == btnRelatorio) {
					Relatorio relatorio = new Relatorio();
					relatorio.popup();

				} else if (evt.target == btnConfiguracao) {
					Configuracoes configuracoes = new Configuracoes();
					configuracoes.popup();

				} else if (evt.target == btnVoltar) {
					unpop();
				}

			}
		} catch (Exception e) {
			Auxiliares.artMsgbox("ERRO", "Erro na validação do menu\n" + e);
		}

	}
}
