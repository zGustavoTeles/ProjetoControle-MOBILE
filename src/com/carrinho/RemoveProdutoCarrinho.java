package com.carrinho;

import com.auxiliares.Auxiliares;
import com.litebase.LitebasePack;
import com.venda.Venda;

import litebase.ResultSet;
import nx.componentes.ArtButton;
import totalcross.sys.Convert;
import totalcross.ui.Edit;
import totalcross.ui.ImageControl;
import totalcross.ui.Label;
import totalcross.ui.dialog.MessageBox;
import totalcross.ui.event.ControlEvent;
import totalcross.ui.event.Event;
import totalcross.ui.gfx.Color;
import totalcross.ui.image.Image;

public class RemoveProdutoCarrinho extends totalcross.ui.Window {

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
	public ArtButton		    btnRemover;
	public ArtButton 			btnVoltar;
	private ImageControl		imgRemoverCarrinho;
	public String				valorProdutoSt;
	public int					totalCarrinho = 0;
	public int  				quantidadeTemp = 0;
	public double				total = 0.0;

	public RemoveProdutoCarrinho() {
		setBackColor(0x003366);
		initUI();
	}

	public void initUI() {

		try {
			
			imgRemoverCarrinho = new ImageControl(new Image("img/removerCarrinho.png"));
			imgRemoverCarrinho.scaleToFit = true;
			imgRemoverCarrinho.centerImage = true;
			add(imgRemoverCarrinho, CENTER, TOP - 20, SCREENSIZE + 15, SCREENSIZE + 30);
			
			lblProduto = new Label("PRODUTO:      ");
			add(lblProduto);
			lblProduto.setRect(LEFT + 90, AFTER + 10, PREFERRED, PREFERRED, imgRemoverCarrinho);
			lblProduto.setBackColor(0x003366);
			lblProduto.setForeColor(Color.WHITE);

			editProduto = new Edit();
			add(editProduto);
			editProduto.setRect(AFTER, SAME, FILL - 80, PREFERRED, lblProduto);
			editProduto.setBackColor(Color.WHITE);
			editProduto.setForeColor(0x003366);
			editProduto.setEditable(false);
			
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
			
			lblQntCarrinho = new Label("CARRINHO:     ");
			add(lblQntCarrinho);
			lblQntCarrinho.setRect(LEFT + 90, AFTER + 15, PREFERRED, PREFERRED, editCodigo);
			lblQntCarrinho.setBackColor(0x003366);
			lblQntCarrinho.setForeColor(Color.WHITE);

			editQntCarrinho = new Edit();
			add(editQntCarrinho);
			editQntCarrinho.setRect(AFTER, SAME, FILL - 80, PREFERRED, lblQntCarrinho);
			editQntCarrinho.setBackColor(Color.WHITE);
			editQntCarrinho.setForeColor(0x003366);
			editQntCarrinho.setEditable(false);
			editQntCarrinho.setText(Carrinho.quantidadeProduto);
			
			lblQuantidade = new Label("QUANTIDADE: ");
			add(lblQuantidade);
			lblQuantidade.setRect(LEFT + 90, AFTER + 15, PREFERRED, PREFERRED, editQntCarrinho);
			lblQuantidade.setBackColor(0x003366);
			lblQuantidade.setForeColor(Color.WHITE);
			
			editQuantidade = new Edit();
			add(editQuantidade);
			editQuantidade.setRect(AFTER, SAME, FILL - 80, PREFERRED, lblQuantidade);
			editQuantidade.setBackColor(Color.WHITE);
			editQuantidade.setForeColor(0x003366);
			editQuantidade.setValidChars("0 1 2 3 4 5 6 7 8 9");
			
			lblTotal = new Label("TOTAL:            ");
			add(lblTotal);
			lblTotal.setRect(LEFT + 90, AFTER + 15, PREFERRED, PREFERRED, editQuantidade);
			lblTotal.setBackColor(0x003366);
			lblTotal.setForeColor(Color.WHITE);	

			editTotal = new Edit();
			add(editTotal);
			editTotal.setRect(AFTER, SAME, FILL - 80, PREFERRED, lblTotal);
			editTotal.setBackColor(Color.WHITE);
			editTotal.setForeColor(0x003366);
			editTotal.setText(Carrinho.totalProduto);
			editTotal.setEditable(false);
			
			btnRemover = new ArtButton("REMOVER");
			add(btnRemover);
			btnRemover.setRect(LEFT, BOTTOM, SCREENSIZE - 4, PREFERRED + 15);
			btnRemover.setBackColor(0xDF0101);
			btnRemover.setForeColor(Color.WHITE);

			btnVoltar = new ArtButton("VOLTAR");
			add(btnVoltar);
			btnVoltar.setRect(RIGHT, BOTTOM, SCREENSIZE - 4, PREFERRED + 15);
			btnVoltar.setBackColor(0x003366);
			btnVoltar.setForeColor(Color.WHITE);
			
			reposition();
			
			carregaInfoItem();
			
			editQuantidade.requestFocus();
			
		} catch (Exception e) {
			Auxiliares.artMsgbox("ERRO","Erro ao construir a tela removerProduto carrinho\n" + e);

		}

	}

	public void onEvent(Event evt) {

		try {
			switch (evt.type) {
			case ControlEvent.PRESSED:

				if (evt.target == btnVoltar) {
					unpop();
				}
				
				if (evt.target == btnRemover) {

					if (editQuantidade.getText().equals("")) {
						Auxiliares.artMsgbox("CONTROLE", "Por favor digite uma quantidade!");

						return;

					} else if (Convert.toInt(editQuantidade.getText()) == 0) {
						Auxiliares.artMsgbox("CONTROLE", "Quantidade inválida!");
						editQuantidade.setText("");
						return;

					} else if (Convert.toInt(editQntCarrinho.getText()) < 0) {
						Auxiliares.artMsgbox("CONTROLE", "Quantidade inserida maior que a quantidade no carrinho!");
						
						editQuantidade.setText("");
						editQntCarrinho.setText(Carrinho.quantidadeProduto);
						editTotal.setText(Carrinho.totalProduto);
						
						editQuantidade.requestFocus();

						return;

					}else {

						String[] ArtButtonArray = { "Sim", "Não" };

						int i = Auxiliares.artMsgbox("CONTROLE", "Deseja remover " + editQuantidade.getText()
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
									Auxiliares.artMsgbox("CONTROLE", "Produto removido do carrinho!");

									unpop();
									unpop();
								} else {
									Auxiliares.artMsgbox("CONTROLE", "Produto removido do carrinho!\nCarrinho vazio!");
									Venda.btnCarrinho.setEnabled(false);
									unpop();
									unpop();
								}

							} else {
								Auxiliares.artMsgbox("CONTROLE", "Produto removido do carrinho!");

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
			Auxiliares.artMsgbox("ERRO", "Erro na validação da tela removerProduto carrinho\n" + e);
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
			Auxiliares.artMsgbox("ERRO", "Erro deletaProdutoCarrinho\n" + e);
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
			Auxiliares.artMsgbox("ERRO", "Erro removeProdutoCarrinho\n" + e);
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
			Auxiliares.artMsgbox("ERRO", "Erro no calculo do produto\n" + e);
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
			Auxiliares.artMsgbox("ERRO", "Erro carregaInfoItem\n" + e);

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
				Auxiliares.artMsgbox("ERRO", "Erro ao carregaCmbMarca\n" + e);

				return existeProdutoCarrinho;
			}

		}
	}

}
