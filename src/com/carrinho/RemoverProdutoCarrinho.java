package com.carrinho;

import com.auxiliares.Auxiliares;
import com.bottom.BottomImg;
import com.litebase.LitebasePack;
import com.venda.Loja;

import litebase.ResultSet;
import totalcross.sys.Convert;
import totalcross.ui.Button;
import totalcross.ui.Edit;
import totalcross.ui.ImageControl;
import totalcross.ui.Label;
import totalcross.ui.dialog.MessageBox;
import totalcross.ui.event.ControlEvent;
import totalcross.ui.event.Event;
import totalcross.ui.gfx.Color;
import totalcross.ui.image.Image;

public class RemoverProdutoCarrinho extends totalcross.ui.Window {

	public Label                lblAviso;
	public Label 				lblProduto;
	public Label				lblQuantidade;
	public Label				lblCodigo;
	public Label				lblQntCarrinho;
	public Label				lblTotal;
	public Edit 				editTotal;
	public Edit					editQntCarrinho;
	public Edit					editCodigo;
	public Edit					editQuantidade;
	public Edit 				editProduto;
	public Button			    btnRemoverProdutoCarrinho;
	public Button	 			btnVoltar;
	private ImageControl		imgRemoverProdutoCarrinho;
	public String				valorProdutoSt;
	public int					totalCarrinho = 0;
	public int  				quantidadeTemp = 0;
	public double				total = 0.0;

	public RemoverProdutoCarrinho() {
		setBackColor(0x1c355d);
		initUI();
	}

	public void initUI() {

		try {
			
			imgRemoverProdutoCarrinho = new ImageControl(new Image("img/removerProduto.png"));
			imgRemoverProdutoCarrinho.scaleToFit = true;
			imgRemoverProdutoCarrinho.centerImage = true;
			add(imgRemoverProdutoCarrinho, CENTER, TOP - 20, SCREENSIZE + 20, SCREENSIZE + 20);
			
//			lblProduto = new Label("PRODUTO:      ");
//			add(lblProduto);
//			lblProduto.setRect(LEFT + 5, AFTER + 10, PREFERRED, PREFERRED, imgRemoverProdutoCarrinho);
//			lblProduto.setBackColor(0x1c355d);
//			lblProduto.setForeColor(Color.WHITE);

			editProduto = new Edit();
			add(editProduto);
			editProduto.setRect(CENTER, AFTER, SCREENSIZE + 80, SCREENSIZE + 4, imgRemoverProdutoCarrinho);
			editProduto.setBackColor(Color.WHITE);
			editProduto.setForeColor(0x1c355d);
			editProduto.setEditable(false);
			
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
			
//			lblQntCarrinho = new Label("CARRINHO:     ");
//			add(lblQntCarrinho);
//			lblQntCarrinho.setRect(LEFT + 5, AFTER + 15, PREFERRED, PREFERRED, editCodigo);
//			lblQntCarrinho.setBackColor(0x1c355d);
//			lblQntCarrinho.setForeColor(Color.WHITE);

			editQntCarrinho = new Edit();
			add(editQntCarrinho);
			editQntCarrinho.setRect(CENTER, AFTER, SCREENSIZE + 80, SCREENSIZE + 4, editCodigo);
			editQntCarrinho.setBackColor(Color.WHITE);
			editQntCarrinho.setForeColor(0x1c355d);
			editQntCarrinho.setEditable(false);
			editQntCarrinho.setText(Carrinho.quantidadeProduto);
			
//			lblQuantidade = new Label("QUANTIDADE: ");
//			add(lblQuantidade);
//			lblQuantidade.setRect(LEFT + 5, AFTER + 15, PREFERRED, PREFERRED, editQntCarrinho);
//			lblQuantidade.setBackColor(0x1c355d);
//			lblQuantidade.setForeColor(Color.WHITE);
			
			editQuantidade = new Edit();
			add(editQuantidade);
			editQuantidade.setRect(CENTER, AFTER, SCREENSIZE + 80, SCREENSIZE + 4, editQntCarrinho);
			editQuantidade.setBackColor(Color.WHITE);
			editQuantidade.setForeColor(0x1c355d);
			editQuantidade.setValidChars("0 1 2 3 4 5 6 7 8 9");
			
//			lblTotal = new Label("TOTAL:            ");
//			add(lblTotal);
//			lblTotal.setRect(LEFT + 5, AFTER + 15, PREFERRED, PREFERRED, editQuantidade);
//			lblTotal.setBackColor(0x1c355d);
//			lblTotal.setForeColor(Color.WHITE);	

			editTotal = new Edit();
			add(editTotal);
			editTotal.setRect(CENTER, AFTER, SCREENSIZE + 80, SCREENSIZE + 4, editQuantidade);
			editTotal.setBackColor(Color.WHITE);
			editTotal.setForeColor(0x1c355d);
			editTotal.setText(Carrinho.totalProduto);
			editTotal.setEditable(false);
			
			btnRemoverProdutoCarrinho = BottomImg.imageWithText(new Image("img/remover.png"), "Alterar", Button.BOTTOM);
			add(btnRemoverProdutoCarrinho, LEFT + 5, BOTTOM, SCREENSIZE - 4, PREFERRED + 15);
			btnRemoverProdutoCarrinho.setBackColor(0x1c355d);
			btnRemoverProdutoCarrinho.setForeColor(Color.WHITE);

			btnVoltar = BottomImg.imageWithText(new Image("img/voltar.png"), "Voltar", Button.BOTTOM);
			add(btnVoltar, RIGHT - 2, BOTTOM, SCREENSIZE + 25, SCREENSIZE + 10);
			btnVoltar.setBackColor(0x1c355d);
			btnVoltar.setForeColor(Color.WHITE);
			
			reposition();
			
			carregaInfoItem();
			
			editQuantidade.requestFocus();
			
		} catch (Exception e) {
			Auxiliares.messagebox("ERRO","Erro ao construir a tela removerProduto carrinho\n" + e);

		}

	}

	public void onEvent(Event evt) {

		try {
			switch (evt.type) {
			case ControlEvent.PRESSED:

				if (evt.target == btnVoltar) {
					unpop();
				}
				
				if (evt.target == btnRemoverProdutoCarrinho) {

					if (editQuantidade.getText().equals("")) {
						Auxiliares.messagebox("SOLUCAO", "Por favor digite uma quantidade!");

						return;

					} else if (Convert.toInt(editQuantidade.getText()) == 0) {
						Auxiliares.messagebox("SOLUCAO", "Quantidade inválida!");
						editQuantidade.setText("");
						return;

					} else if (Convert.toInt(editQntCarrinho.getText()) < 0) {
						Auxiliares.messagebox("SOLUCAO", "Quantidade inserida maior que a quantidade no carrinho!");
						
						editQuantidade.setText("");
						editQntCarrinho.setText(Carrinho.quantidadeProduto);
						editTotal.setText(Carrinho.totalProduto);
						
						editQuantidade.requestFocus();

						return;

					}else {

						String[] ArtButtonArray = { "Sim", "Não" };

						int i = Auxiliares.messageBox("SOLUCAO", "Deseja remover " + editQuantidade.getText()
								+ " unidade(s) deste produto\ndo carrinho?", ArtButtonArray);

						if (i == 1) {
							return;

						} else {

							removeProdutoCarrinho();

							if (Convert.toInt(editQntCarrinho.getText()) == 0
									|| Convert.toInt(editQntCarrinho.getText()) < 0) {

								deletaProdutoCarrinho();

								boolean existeProdutoCarrinho = false;
								existeProdutoCarrinho = existeProdutoCarrinho(existeProdutoCarrinho);

								if (existeProdutoCarrinho) {
									Auxiliares.messagebox("SOLUCAO", "Produto removido do carrinho!");

									unpop();
									unpop();
								} else {
									Auxiliares.messagebox("SOLUCAO", "Produto removido do carrinho!\nCarrinho vazio!");
									Loja.btnCarrinho.setEnabled(false);
									unpop();
									unpop();
								}

							} else {
								Auxiliares.messagebox("SOLUCAO", "Produto removido do carrinho!");

								unpop();
								unpop();
							}

						}

					}
				}
				
				if (editQuantidade.getText().length() == 0 || editQuantidade.getText().equals("") ) {
					editQntCarrinho.setText(Carrinho.quantidadeProduto);
					editTotal.setText(Carrinho.totalProduto);
				}
				
				if (editQntCarrinho.getText().length() == 0) {
					editTotal.setText(Carrinho.totalProduto);
				}

				if (editQuantidade.getText().length() > 0) {
					calculaQuantidade();

				}

			}			

		} catch (Exception e) {
			Auxiliares.messagebox("ERRO", "Erro na validação da tela removerProduto carrinho\n" + e);
		}

	}

	public void deletaProdutoCarrinho() {
		LitebasePack lb = null;
		String sql = "";

		try {

			try {
				
				lb = new LitebasePack();
				sql = " DELETE FROM VENDAPRODUTOTEMP " 
					+ " WHERE CODIGOPROD = " + editCodigo.getText();
					
					lb.executeUpdate(sql);

			} finally {
				if (lb != null)
					lb.closeAll();
			}

		} catch (Exception e) {
			Auxiliares.messagebox("ERRO", "Erro deletaProdutoCarrinho\n" + e);
		}

	}
	
	public void removeProdutoCarrinho() {

		LitebasePack lb = null;
		String sql = "";

		try {

			try {

				lb = new LitebasePack();
				sql = " UPDATE VENDAPRODUTOTEMP " 
				    + " SET QUANTIDADE = " + editQntCarrinho.getText() 
				    + " WHERE CODIGOPROD = " + editCodigo.getText();

				lb.executeUpdate(sql);
				
				sql = " UPDATE VENDAPRODUTOTEMP " 
					+ " SET VALOR = " + editTotal.getText()
				    + " WHERE CODIGOPROD = " + editCodigo.getText();

					lb.executeUpdate(sql);
					

			} finally {
				if (lb != null)
					lb.closeAll();
			}

		} catch (Exception e) {
			Auxiliares.messagebox("ERRO", "Erro removeProdutoCarrinho\n" + e);
		}

	}
	
	public void calculaQuantidade() {

		try {
			
			double valorTotalProduto = 0;
			String quantidadeInserida;
			
			quantidadeInserida = editQuantidade.getText();			
			valorTotalProduto = Convert.toDouble(valorProdutoSt.replace(".", "."));
			
			totalCarrinho = Convert.toInt(Carrinho.quantidadeProduto) - Convert.toInt(quantidadeInserida);
			total = valorTotalProduto * totalCarrinho;
			
			editTotal.setText(Convert.toString(total, 2));
			editQntCarrinho.setText(Convert.toString(totalCarrinho));
						
		} catch (Exception e) {
			Auxiliares.messagebox("ERRO", "Erro no calculo do produto\n" + e);
		}

	}
	
	public void carregaInfoItem() {
		String sql = "";
		LitebasePack lb = null;
		ResultSet rs = null;

		try {
			try {
				lb = new LitebasePack();
				sql = " SELECT CODIGOPROD, PRODUTO FROM VENDAPRODUTOTEMP " 
				    + " WHERE CODIGOPROD = " + Carrinho.codigoProduto;

				rs = lb.executeQuery(sql);
				rs.first();

				editCodigo.setText(Convert.toString(rs.getInt("CODIGOPROD")));
				editProduto.setText(rs.getString("PRODUTO"));

				sql = " SELECT QUANTIDADE, VALOR FROM ESTOQUE " 
				    + " WHERE CODIGO = " + Carrinho.codigoProduto;

				rs = lb.executeQuery(sql);
				rs.first();

				quantidadeTemp = rs.getInt("QUANTIDADE");
				valorProdutoSt = rs.getString("VALOR");

			} finally {
				if (lb != null)
					lb.closeAll();

			}
		} catch (Exception e) {
			Auxiliares.messagebox("ERRO", "Erro carregaInfoItem\n" + e);

		}

	}
	
	public boolean existeProdutoCarrinho(boolean existeProdutoCarrinho) {
		{
			String sql = "";
			LitebasePack lb = null;
			ResultSet rs = null;

			try {
				try {
					lb = new LitebasePack();

					sql = "SELECT * FROM VENDAPRODUTOTEMP";

					rs = lb.executeQuery(sql);
					rs.first();

					if (rs.getRowCount() == 0) {
						return existeProdutoCarrinho = false;

					} else {

						return existeProdutoCarrinho = true;
					}

				} finally {
					if (lb != null)
						lb.closeAll();

				}
			} catch (Exception e) {
				Auxiliares.messagebox("ERRO", "Erro ao carregaCmbMarca\n" + e);

				return existeProdutoCarrinho;
			}

		}
	}

}
