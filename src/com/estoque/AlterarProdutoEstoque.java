package com.estoque;

import com.auxiliares.Auxiliares;
import com.bottom.BottomImg;
import com.litebase.LitebasePack;
import totalcross.sys.Convert;
import totalcross.ui.Button;
import totalcross.ui.Edit;
import totalcross.ui.ImageControl;
import totalcross.ui.Label;
import totalcross.ui.event.ControlEvent;
import totalcross.ui.event.Event;
import totalcross.ui.gfx.Color;
import totalcross.ui.image.Image;

public class AlterarProdutoEstoque extends totalcross.ui.Window {

	public Label                lblAviso;
	public Label 				lblProduto;
	public Label 				lblEstoque;
	public Label				lblQuantidade;
	public Label				lblCodigo;
	public Edit					editCodigo;
	public Edit					editQuantidade;
	public Edit					editEstoque;
	public Edit 				editProduto;
	public Button		   	    btnAlterarProdutoEstoque;
	public Button 				btnVoltar;
	private ImageControl		imgAlterarProdutoEstoque;

	public AlterarProdutoEstoque() {
		setBackColor(0x1c355d);
		initUI();
	}

	public void initUI() {

		try {
			
			imgAlterarProdutoEstoque = new ImageControl(new Image("img/alterarProduto.png"));
			imgAlterarProdutoEstoque.scaleToFit = true;
			imgAlterarProdutoEstoque.centerImage = true;
			add(imgAlterarProdutoEstoque, CENTER, TOP - 30, SCREENSIZE + 30, SCREENSIZE + 30);
			
//			lblProduto = new Label("PRODUTO:      ");
//			add(lblProduto);
//			lblProduto.setRect(LEFT + 5, AFTER + 60, PREFERRED, PREFERRED, lblAviso);
//			lblProduto.setBackColor(0x1c355d);
//			lblProduto.setForeColor(Color.WHITE);

			editProduto = new Edit();
			add(editProduto);
			editProduto.setRect(CENTER, AFTER - 30, SCREENSIZE + 80, SCREENSIZE + 4, imgAlterarProdutoEstoque);
			editProduto.setBackColor(Color.WHITE);
			editProduto.setForeColor(0x1c355d);
			editProduto.setEditable(false);
			editProduto.setText(Estoque.produto);
			
//			lblCodigo = new Label("CÓDIGO:          ");
//			add(lblCodigo);
//			lblCodigo.setRect(LEFT + 5, AFTER + 15, PREFERRED, PREFERRED, editProduto);
//			lblCodigo.setBackColor(0x1c355d);
//			lblCodigo.setForeColor(Color.WHITE);

			editCodigo = new Edit();
			add(editCodigo);
			editCodigo.setRect(CENTER, AFTER, SCREENSIZE + 80, SCREENSIZE + 4, editProduto);
			editCodigo.setBackColor(Color.WHITE);
			editCodigo.setForeColor(0x1c355d);
			editCodigo.setEditable(false);
			editCodigo.setText(Estoque.codigo);
			
//			lblEstoque = new Label("ESTOQUE:       ");
//			add(lblEstoque);
//			lblEstoque.setRect(LEFT + 5, AFTER + 15, PREFERRED, PREFERRED, editCodigo);
//			lblEstoque.setBackColor(0x1c355d);
//			lblEstoque.setForeColor(Color.WHITE);
			
			editEstoque = new Edit();
			add(editEstoque);
			editEstoque.setRect(CENTER, AFTER, SCREENSIZE + 80, SCREENSIZE + 4, editCodigo);
			editEstoque.setBackColor(Color.WHITE);
			editEstoque.setForeColor(0x1c355d);
			editEstoque.setEditable(false);
			editEstoque.setText(Estoque.quantidade);
			
//			lblQuantidade = new Label("QUANTIDADE: ");
//			add(lblQuantidade);
//			lblQuantidade.setRect(LEFT + 5, AFTER + 15, PREFERRED, PREFERRED, editEstoque);
//			lblQuantidade.setBackColor(0x1c355d);
//			lblQuantidade.setForeColor(Color.WHITE);
			
			editQuantidade = new Edit();
			add(editQuantidade);
			editQuantidade.setRect(CENTER, AFTER, SCREENSIZE + 80, SCREENSIZE + 4, editEstoque);
			editQuantidade.setBackColor(Color.WHITE);
			editQuantidade.setForeColor(0x1c355d);
			editQuantidade.setValidChars("0 1 2 3 4 5 6 7 8 9");
			
			btnAlterarProdutoEstoque = BottomImg.imageWithText(new Image("img/alterar.png"), "Alterar", Button.BOTTOM);
			add(btnAlterarProdutoEstoque, LEFT + 5, BOTTOM, SCREENSIZE - 4, PREFERRED + 15);
			btnAlterarProdutoEstoque.setBackColor(0x1c355d);
			btnAlterarProdutoEstoque.setForeColor(Color.WHITE);

			btnVoltar = BottomImg.imageWithText(new Image("img/voltar.png"), "Voltar", Button.BOTTOM);
			add(btnVoltar, RIGHT - 2, BOTTOM, SCREENSIZE + 25, SCREENSIZE + 10);
			btnVoltar.setBackColor(0x1c355d);
			btnVoltar.setForeColor(Color.WHITE);
			
			reposition();
			
			editQuantidade.requestFocus();
			
		} catch (Exception e) {
			Auxiliares.messagebox("ERRO","Erro ao construir a tela AlterarProduto\n" + e);

		}

	}

	public void onEvent(Event evt) {

		try {
			switch (evt.type) {
			case ControlEvent.PRESSED:

				if (evt.target == btnVoltar) {
					unpop();

				}
				if (evt.target == btnAlterarProdutoEstoque) {

					if (editQuantidade.getText().equals("")) {
						Auxiliares.messagebox("SOLUCAO", "Por favor digite uma quantidade!");

						return;

					} else if (Convert.toInt(editQuantidade.getText()) == 0) {
						Auxiliares.messagebox("SOLUCAO", "Quantidade inválida!");
						editQuantidade.setText("");

						return;

					} else {

						String[] ArtButtonArray = { "Sim", "Não" };

						int i = Auxiliares.messageBox("SOLUCAO", "Deseja alterar inserindo " + editQuantidade.getText()
								+ " unidade(s) deste\nproduto no estoque?", ArtButtonArray);

						if (i == 1) {
							return;

						} else {

							alteraProdutoEstoque();

							if (Convert.toInt(editEstoque.getText()) == 0 || Convert.toInt(editEstoque.getText()) < 0) {

								deletaProdutoEstoque();
							}

							Auxiliares.messagebox("SOLUCAO", "Produto alterado com sucesso!");

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
			Auxiliares.messagebox("ERRO", "Erro na validação da tela AlterarProduto\n" + e);
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
			Auxiliares.messagebox("ERRO", "Erro deletaProdutoEstoque\n" + e);
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
			Auxiliares.messagebox("ERRO", "Erro removeProdutoEstoque\n" + e);
		}

	}
	
	public void calculaQuantidade() {

		try {

			int total = 0;
			total = Convert.toInt(editEstoque.getText()) + Convert.toInt(editQuantidade.getText());
			editEstoque.setText(Convert.toString(total));

		} catch (Exception e) {
			Auxiliares.messagebox("ERRO", "Erro ao calcularQuantidade\n" + e);
		}

	}

}
