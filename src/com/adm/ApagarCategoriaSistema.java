package com.adm;


import java.util.Random;
import com.auxiliares.Auxiliares;
import com.bottom.BottomImg;
import com.litebase.LitebasePack;

import litebase.ResultSet;
import totalcross.ui.Button;
import totalcross.ui.ComboBox;
import totalcross.sys.Convert;
import totalcross.ui.Edit;
import totalcross.ui.ImageControl;
import totalcross.ui.Label;
import totalcross.ui.event.ControlEvent;
import totalcross.ui.event.Event;
import totalcross.ui.gfx.Color;
import totalcross.ui.image.Image;

public class ApagarCategoriaSistema extends totalcross.ui.Window {

	public Label 				lblCategoria;
	public Label 				lblUsuario;
	public Label				lblCodigo;
	
	public Edit					editCodigo;
	public Edit					editCategoria;
	
	public Button		   		btnApagar;
	public Button				btnGerarCodigo;
	public Button 				btnVoltar;
	
	private ComboBox			cmbCategoria;
	
	public ImageControl		    imgApagarCategoria;

	public ApagarCategoriaSistema() {
		setBackColor(0x1c355d);
		initUI();
	}

	public void initUI() {

		try {
			
			imgApagarCategoria = new ImageControl(new Image("img/cadastrarCategoria.png"));
			imgApagarCategoria.scaleToFit = true;
			imgApagarCategoria.centerImage = true;
			add(imgApagarCategoria, CENTER, TOP - 40, SCREENSIZE + 40, SCREENSIZE + 40);

			cmbCategoria = new ComboBox();
			add(cmbCategoria);
			cmbCategoria.setRect(CENTER, AFTER - 20, SCREENSIZE + 80, SCREENSIZE + 4, imgApagarCategoria);
			
			btnApagar = BottomImg.imageWithText(new Image("img/apagarProdutos.png"), "Apagar", Button.BOTTOM);
			add(btnApagar, LEFT + 5, BOTTOM, SCREENSIZE - 4, PREFERRED + 15);
			btnApagar.setBackColor(0x1c355d);
			btnApagar.setForeColor(Color.WHITE);

			btnVoltar = BottomImg.imageWithText(new Image("img/voltar.png"), "Voltar", Button.BOTTOM);
			add(btnVoltar, RIGHT - 2, BOTTOM, SCREENSIZE + 25, SCREENSIZE + 10);
			btnVoltar.setBackColor(0x1c355d);
			btnVoltar.setForeColor(Color.WHITE);
			
			carregaCmbCategoria();
			
			reposition();
			
		} catch (Exception e) {
			Auxiliares.messagebox("ERRO", "Erro ao construir o menu CadastrarCategoriaSistema\n" + e);

		}

	}

	public void onEvent(Event evt) {

		try {
			switch (evt.type) {
			case ControlEvent.PRESSED:

				if (evt.target == btnVoltar) {
					unpop();

				} else if (evt.target == btnApagar) {
					String[] ArtButtonArray = { "Sim", "Não" };

					if (cmbCategoria.getSelectedItem() == null) {
						Auxiliares.messagebox("SOLUCAO", "Por favor selecione uma categoria!");
						return;
					}

					int i = Auxiliares.messageBox("SOLUCAO", "Deseja apagar essa categoria do sistema?",
							ArtButtonArray);

					if (i == 1) {
						return;

					} else {
						apagaCategoriaSistema();
						Auxiliares.messagebox("SOLUCAO", "Categoria apagada com sucesso!");
						unpop();

					}
				} else if (evt.target == btnGerarCodigo) {
					Random random = new Random();
					int codigo = random.nextInt(900);
					editCodigo.setText(Convert.toString(codigo));

				}

			}

		} catch (Exception e) {
			Auxiliares.messagebox("SOLUCAO", "Erro na validação do menu CadastrarCategoriaSistema\n " + e);
		}

	}
	
	public void carregaCmbCategoria() {
		{
			String sql = "";
			LitebasePack lb = null;
			ResultSet rs = null;

			try {
				try {
					cmbCategoria.removeAll();
					lb = new LitebasePack();
					sql = " SELECT DESCRICAO FROM CATEGORIA";

					rs = lb.executeQuery(sql);
					rs.first();
					for (int i = 0; rs.getRowCount() > i; i++) {
						String[] b = new String[1];
						b[0] = rs.getString("DESCRICAO");
						cmbCategoria.add(b);
						rs.next();
					}
				} finally {
					if (lb != null)
						lb.closeAll();

				}
			} catch (Exception e) {
				Auxiliares.messagebox("ERRO", "Erro ao carregaCmbCategoria\n" + e);

			}

		}
	}
	
	public void apagaCategoriaSistema() {
		String sql = "";
		LitebasePack lb = null;

		try {

			try {
				lb = new LitebasePack();

				sql = "DELETE FROM CATEGORIA WHERE DESCRICAO = '" + cmbCategoria.getSelectedItem() + "'";

				lb.executeUpdate(sql);

			} finally {
				if (lb != null)
					lb.closeAll();
			}

		} catch (Exception e) {
			Auxiliares.messagebox("ERRO", "Erro ao buscar apagaCategoriaSistema\n" + e);

			return;
		}
	}

}
