package com.estoque;

import com.auxiliares.Auxiliares;
import com.litebase.LitebasePack;
import nx.componentes.ArtButton;
import totalcross.sys.Convert;
import totalcross.ui.Edit;
import totalcross.ui.ImageControl;
import totalcross.ui.Label;
import totalcross.ui.event.ControlEvent;
import totalcross.ui.event.Event;
import totalcross.ui.gfx.Color;
import totalcross.ui.image.Image;

public class AlterarProduto extends totalcross.ui.Window {

	public Label                lblAviso;
	public Label 				lblProduto;
	public Label 				lblEstoque;
	public Label				lblQuantidade;
	public Label				lblCodigo;
	public Edit					editCodigo;
	public Edit					editQuantidade;
	public Edit					editEstoque;
	public Edit 				editProduto;
	public ArtButton		    btnAlterar;
	public ArtButton 			btnVoltar;
	private ImageControl		imgRemoverEstoque;

	public AlterarProduto() {
		setBackColor(0x003366);
		initUI();
	}

	public void initUI() {

		try {
			
			imgRemoverEstoque = new ImageControl(new Image("img/alterarProduto.png"));
			imgRemoverEstoque.scaleToFit = true;
			imgRemoverEstoque.centerImage = true;
			add(imgRemoverEstoque, CENTER, TOP - 30, SCREENSIZE + 20, SCREENSIZE + 40);
			
			lblProduto = new Label("PRODUTO:      ");
			add(lblProduto);
			lblProduto.setRect(LEFT + 90, AFTER + 60, PREFERRED, PREFERRED, lblAviso);
			lblProduto.setBackColor(0x003366);
			lblProduto.setForeColor(Color.WHITE);

			editProduto = new Edit();
			add(editProduto);
			editProduto.setRect(AFTER, SAME, FILL - 80, PREFERRED, lblProduto);
			editProduto.setBackColor(Color.WHITE);
			editProduto.setForeColor(0x003366);
			editProduto.setEditable(false);
			editProduto.setText(Estoque.produto);
			
			lblCodigo = new Label("CÓDIGO:          ");
			add(lblCodigo);
			lblCodigo.setRect(LEFT + 90, AFTER + 15, PREFERRED, PREFERRED, editProduto);
			lblCodigo.setBackColor(0x003366);
			lblCodigo.setForeColor(Color.WHITE);

			editCodigo = new Edit();
			add(editCodigo);
			editCodigo.setRect(AFTER, SAME, FILL - 80, PREFERRED, lblCodigo);
			editCodigo.setBackColor(Color.WHITE);
			editCodigo.setForeColor(0x003366);
			editCodigo.setEditable(false);
			editCodigo.setText(Estoque.codigo);
			
			lblEstoque = new Label("ESTOQUE:       ");
			add(lblEstoque);
			lblEstoque.setRect(LEFT + 90, AFTER + 15, PREFERRED, PREFERRED, editCodigo);
			lblEstoque.setBackColor(0x003366);
			lblEstoque.setForeColor(Color.WHITE);
			
			editEstoque = new Edit();
			add(editEstoque);
			editEstoque.setRect(AFTER, SAME, FILL - 80, PREFERRED, lblEstoque);
			editEstoque.setBackColor(Color.WHITE);
			editEstoque.setForeColor(0x003366);
			editEstoque.setEditable(false);
			editEstoque.setText(Estoque.quantidade);
			
			lblQuantidade = new Label("QUANTIDADE: ");
			add(lblQuantidade);
			lblQuantidade.setRect(LEFT + 90, AFTER + 15, PREFERRED, PREFERRED, editEstoque);
			lblQuantidade.setBackColor(0x003366);
			lblQuantidade.setForeColor(Color.WHITE);
			
			editQuantidade = new Edit();
			add(editQuantidade);
			editQuantidade.setRect(AFTER, SAME, FILL - 80, PREFERRED, lblQuantidade);
			editQuantidade.setBackColor(Color.WHITE);
			editQuantidade.setForeColor(0x003366);
			editQuantidade.setValidChars("0 1 2 3 4 5 6 7 8 9");
			
			btnAlterar = new ArtButton("ALTERAR");
			add(btnAlterar);
			btnAlterar.setRect(LEFT, BOTTOM, SCREENSIZE - 4, PREFERRED + 15);
			btnAlterar.setBackColor(0x009933);
			btnAlterar.setForeColor(Color.WHITE);

			btnVoltar = new ArtButton("VOLTAR");
			add(btnVoltar);
			btnVoltar.setRect(RIGHT, BOTTOM, SCREENSIZE - 4, PREFERRED + 15);
			btnVoltar.setBackColor(0x003366);
			btnVoltar.setForeColor(Color.WHITE);
			
			reposition();
			
			editQuantidade.requestFocus();
			
		} catch (Exception e) {
			Auxiliares.artMsgbox("ERRO","Erro ao construir a tela AlterarProduto\n" + e);

		}

	}

	public void onEvent(Event evt) {

		try {
			switch (evt.type) {
			case ControlEvent.PRESSED:

				if (evt.target == btnVoltar) {
					unpop();

				}
				if (evt.target == btnAlterar) {

					if (editQuantidade.getText().equals("")) {
						Auxiliares.artMsgbox("CONTROLE", "Por favor digite uma quantidade!");

						return;

					} else if (Convert.toInt(editQuantidade.getText()) == 0) {
						Auxiliares.artMsgbox("CONTROLE", "Quantidade inválida!");
						editQuantidade.setText("");

						return;

					} else {

						String[] ArtButtonArray = { "Sim", "Não" };

						int i = Auxiliares.artMsgbox("CONTROLE", "Deseja alterar inserindo " + editQuantidade.getText()
								+ " unidade(s) deste\nproduto no estoque?", ArtButtonArray);

						if (i == 1) {
							return;

						} else {

							alteraProdutoEstoque();

							if (Convert.toInt(editEstoque.getText()) == 0 || Convert.toInt(editEstoque.getText()) < 0) {

								deletaProdutoEstoque();
							}

							Auxiliares.artMsgbox("CONTROLE", "Produto alterado com sucesso!");

							unpop();
						}
					}
				}

				if (editQuantidade.getText().length() == 0) {
					editEstoque.setText(Estoque.quantidade);
				}

				if (editQuantidade.getText().length() > 0) {
					editEstoque.setText("");
					editEstoque.setText(Estoque.quantidade);
					calculaQuantidade();

				}

			}			

		} catch (Exception e) {
			Auxiliares.artMsgbox("ERRO", "Erro na validação da tela AlterarProduto\n" + e);
		}

	}

	public void deletaProdutoEstoque() {
		LitebasePack lb = null;
		String sql = "";

		try {

			try {
				
				lb = new LitebasePack();
				sql = " DELETE FROM ESTOQUE " 
					+ " WHERE CODIGO = " + editCodigo.getText();
					
					lb.executeUpdate(sql);

			} finally {
				if (lb != null)
					lb.closeAll();
			}

		} catch (Exception e) {
			Auxiliares.artMsgbox("ERRO", "Erro deletaProdutoEstoque\n" + e);
		}

	}
	
	public void alteraProdutoEstoque() {

		LitebasePack lb = null;
		String sql = "";

		try {

			try {

				lb = new LitebasePack();
				sql = " UPDATE ESTOQUE " 
				    + " SET QUANTIDADE = " + editEstoque.getText() 
				    + " WHERE CODIGO = " + editCodigo.getText();

				lb.executeUpdate(sql);

			} finally {
				if (lb != null)
					lb.closeAll();
			}

		} catch (Exception e) {
			Auxiliares.artMsgbox("ERRO", "Erro removeProdutoEstoque\n" + e);
		}

	}
	
	public void calculaQuantidade() {

		try {

			int total = 0;
			total = Convert.toInt(editEstoque.getText()) + Convert.toInt(editQuantidade.getText());
			editEstoque.setText(Convert.toString(total));

		} catch (Exception e) {
			Auxiliares.artMsgbox("ERRO", "Erro ao calcularQuantidade\n" + e);
		}

	}

}
