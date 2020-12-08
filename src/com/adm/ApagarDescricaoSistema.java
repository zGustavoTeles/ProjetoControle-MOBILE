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

public class ApagarDescricaoSistema extends totalcross.ui.Window {

	public Label 				lblCategoria;
	public Label 				lblUsuario;
	public Label				lblCodigo;
	
	public Edit					editCodigo;
	public Edit					editCategoria;
	
	public Button		   		btnApagar;
	public Button				btnGerarCodigo;
	public Button 				btnVoltar;
	
	private ComboBox			cmbDescricao;
	
	public ImageControl		    imgApagarDescricaoSistema;

	public ApagarDescricaoSistema() {
		setBackColor(0x1c355d);
		initUI();
	}

	public void initUI() {

		try {
			
			imgApagarDescricaoSistema = new ImageControl(new Image("img/cadastrarDescricao.png"));
			imgApagarDescricaoSistema.scaleToFit = true;
			imgApagarDescricaoSistema.centerImage = true;
			add(imgApagarDescricaoSistema, CENTER, TOP - 40, SCREENSIZE + 40, SCREENSIZE + 40);

			cmbDescricao = new ComboBox();
			add(cmbDescricao);
			cmbDescricao.setRect(CENTER, AFTER - 20, SCREENSIZE + 80, SCREENSIZE + 4, imgApagarDescricaoSistema);
			
			btnApagar = BottomImg.imageWithText(new Image("img/apagarProdutos.png"), "Apagar", Button.BOTTOM);
			add(btnApagar, LEFT + 5, BOTTOM, SCREENSIZE - 4, PREFERRED + 15);
			btnApagar.setBackColor(0x1c355d);
			btnApagar.setForeColor(Color.WHITE);

			btnVoltar = BottomImg.imageWithText(new Image("img/voltar.png"), "Voltar", Button.BOTTOM);
			add(btnVoltar, RIGHT - 2, BOTTOM, SCREENSIZE + 25, SCREENSIZE + 10);
			btnVoltar.setBackColor(0x1c355d);
			btnVoltar.setForeColor(Color.WHITE);
			
			carregaCmbDescricao();
			
			reposition();
			
		} catch (Exception e) {
			Auxiliares.messagebox("ERRO", "Erro ao construir o menu ApagarDescricaoSistema\n" + e);

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

					if (cmbDescricao.getSelectedItem() == null) {
						Auxiliares.messagebox("SOLUCAO", "Por favor selecione uma descrição!");
						return;
					}

					int i = Auxiliares.messageBox("SOLUCAO", "Deseja apagar essa Descrição do sistema?",
							ArtButtonArray);

					if (i == 1) {
						return;

					} else {
						apagaDescricaoSistema();
						Auxiliares.messagebox("SOLUCAO", "Descrição apagada com sucesso!");
						unpop();

					}
				} else if (evt.target == btnGerarCodigo) {
					Random random = new Random();
					int codigo = random.nextInt(900);
					editCodigo.setText(Convert.toString(codigo));

				}

			}

		} catch (Exception e) {
			Auxiliares.messagebox("SOLUCAO", "Erro na validação do menu CadastrarDescricaoSistema\n " + e);
		}

	}
	
	public void carregaCmbDescricao() {
		{
			String sql = "";
			LitebasePack lb = null;
			ResultSet rs = null;

			try {
				try {
					lb = new LitebasePack();

						sql = " SELECT DESCRICAO FROM DESCRICAO ";

						rs = lb.executeQuery(sql);
						rs.first();
						for (int i = 0; rs.getRowCount() > i; i++) {
							String[] b = new String[1];
							b[0] = rs.getString("DESCRICAO");
							cmbDescricao.add(b);
							rs.next();
						}

						sql = " SELECT DESCRICAO FROM DESCRICAOPESO ";

						rs = lb.executeQuery(sql);
						rs.first();
						for (int i = 0; rs.getRowCount() > i; i++) {
							String[] b = new String[1];
							b[0] = rs.getString("DESCRICAO");
							cmbDescricao.add(b);
							rs.next();
						}
					
				} finally {
					if (lb != null)
						lb.closeAll();

				}
			} catch (Exception e) {
				Auxiliares.messagebox("ERRO", "Erro ao carregaCmbDescricao\n" + e);

			}

		}
	}
	
	public void apagaDescricaoSistema() {
		String sql = "";
		LitebasePack lb = null;

		try {

			try {
				lb = new LitebasePack();

				sql = "DELETE FROM DESCRICAO WHERE DESCRICAO = '" + cmbDescricao.getSelectedItem() + "'";

				lb.executeUpdate(sql);
				
				sql = "DELETE FROM DESCRICAOPESO WHERE DESCRICAO = '" + cmbDescricao.getSelectedItem() + "'";

				lb.executeUpdate(sql);

			} finally {
				if (lb != null)
					lb.closeAll();
			}

		} catch (Exception e) {
			Auxiliares.messagebox("ERRO", "Erro ao buscar apagaDescricaoSistema\n" + e);

			return;
		}
	}

}
