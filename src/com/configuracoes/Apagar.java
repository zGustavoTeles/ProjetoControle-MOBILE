package com.configuracoes;

import com.adm.ApagarCategoriaSistema;
import com.adm.ApagarDescricaoSistema;
import com.adm.ApagarMarcaSistema;
import com.adm.ApagarProdutoSistema;
import com.auxiliares.Auxiliares;
import totalcross.ui.Button;
import totalcross.ui.Grid;
import totalcross.ui.ImageControl;
import totalcross.ui.event.ControlEvent;
import totalcross.ui.event.Event;
import totalcross.ui.gfx.Color;
import totalcross.ui.image.Image;
import com.bottom.BottomImg;

public class Apagar extends totalcross.ui.Window {
	
	private Button						 btnCadastrarCategoria;
	private Button						 btnVoltar;
	private Button						 btnCadastrarMarca;
	private Button						 btnCadastrarProduto;
	private Button						 btnCadastrarDescricao;
	
	public ImageControl 		         imgMenu;
	
	
	
	public static Grid							 gridVendas;

	public Apagar() {
		setBackColor(0x1c355d);
		initUI();

	}

	public void initUI() {

		try {
	            
			btnCadastrarCategoria = BottomImg.imageWithText(new Image("img/cadastrarCatBtn.png"), "Apagar Categoria", Button.BOTTOM);
			add(btnCadastrarCategoria, CENTER, TOP + 100, SCREENSIZE + 90, SCREENSIZE + 10);
			btnCadastrarCategoria.setBackColor(0x202b4b);
			btnCadastrarCategoria.setForeColor(Color.WHITE);
			
			btnCadastrarProduto = BottomImg.imageWithText(new Image("img/cadastrarProdBtn.png"), "Apagar Produto", Button.BOTTOM);
			add(btnCadastrarProduto, CENTER, AFTER + 5, SCREENSIZE + 90, SCREENSIZE + 10);
			btnCadastrarProduto.setBackColor(0x202b4b);
			btnCadastrarProduto.setForeColor(Color.WHITE);
			
			btnCadastrarMarca = BottomImg.imageWithText(new Image("img/cadastrarMarcBtn.png"), "Apagar Marca", Button.BOTTOM);
			add(btnCadastrarMarca, CENTER, AFTER + 5, SCREENSIZE + 90, SCREENSIZE + 10);
			btnCadastrarMarca.setBackColor(0x202b4b);
			btnCadastrarMarca.setForeColor(Color.WHITE);
			
			btnCadastrarDescricao = BottomImg.imageWithText(new Image("img/cadastrarDescBtn.png"), "Apagar Descrição", Button.BOTTOM);
			add(btnCadastrarDescricao, CENTER, AFTER + 5, SCREENSIZE + 90, SCREENSIZE + 10);
			btnCadastrarDescricao.setBackColor(0x202b4b);
			btnCadastrarDescricao.setForeColor(Color.WHITE);

			btnVoltar = BottomImg.imageWithText(new Image("img/voltar.png"), "Voltar", Button.BOTTOM);
			add(btnVoltar, RIGHT - 2, BOTTOM, SCREENSIZE + 18, SCREENSIZE + 15);
			btnVoltar.setBackColor(0x1c355d);
			btnVoltar.setForeColor(Color.WHITE);
			
			reposition();
			
		} catch (Exception e) {
			Auxiliares.messagebox("ERRO", "Erro ao construir tela menu\n" + e);

		}

	}

	public void onEvent(Event evt) {
		try {
			switch (evt.type) {
			case ControlEvent.PRESSED:

				if (evt.target == btnCadastrarCategoria) {
					ApagarCategoriaSistema apagarCategoria = new ApagarCategoriaSistema();
					apagarCategoria.popup();

				} else if (evt.target == btnCadastrarProduto) {
					ApagarProdutoSistema apagarProduto = new ApagarProdutoSistema();
					apagarProduto.popup();

				} else if (evt.target == btnCadastrarMarca) {
					ApagarMarcaSistema apagarMarca = new ApagarMarcaSistema();
					apagarMarca.popup();

				} else if (evt.target == btnCadastrarDescricao) {
					ApagarDescricaoSistema apagarDescricao = new ApagarDescricaoSistema();
					apagarDescricao.popup();

				} else if (evt.target == btnVoltar) {
					unpop();
				}

			}
		} catch (Exception e) {
			Auxiliares.messagebox("ERRO", "Erro na validação do menu\n" + e);
		}

	}
}
