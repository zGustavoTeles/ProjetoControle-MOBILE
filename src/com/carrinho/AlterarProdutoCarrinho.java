package com.carrinho;

import java.util.Random;

import com.auxiliares.Auxiliares;
import com.litebase.LitebasePack;
import com.teclado.Teclado;
import com.venda.Venda;
import totalcross.ui.ComboBox;
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
import totalcross.util.Date;

public class AlterarProdutoCarrinho extends totalcross.ui.Window {

	private Label				 lblProduto;
	private Label 				 lblEstoque;
	private Label 			     lblValor;
	private Label 				 lblQuantidade;
	private Label                lblAviso;
	private Label				 lblTotal;
	private Label   			 lblCodigo;
	private Label				 lblCategoria;
	private Label 				 lblMarca;
	private Label 				 lblDescricao;
	private Edit				 editCategoria;
	private Edit				 editMarca;
	private Edit				 editDescricao;
	private Edit 				 editCodigo;
	private Edit 				 editProduto;
	private Edit 				 editEstoque;
	private Edit 				 editValor;
	private Edit				 editQuantidade;
	private Edit 				 editTotal;
	private ArtButton 			 btnAlterar;
	private ArtButton 			 btnVoltar;
	private ComboBox			 cmbTipoPagamento;
	private ImageControl		 imgAlterar;
	public double 				 valorProduto = 0.0;
	public double				 total = 0.0;
	public int				     qntEstoqueFinal = 0;
	public int 				     quantidade = 0;
	public int 					 quantidadeEstoque = 0;
	public int 					 qntEstoqueCalculo = 0;
	public String 				 quantidadeVendida = "";
	public String 				 dataEntrada = "";
	public String				 estoque = "";
	public String 				 quantidadeInserida = "";
	
	public String				valorTemp = "";
	public int					quantidadeTemp = 0;

	public Teclado teclado;

	public AlterarProdutoCarrinho() {
		setBackColor(0x003366);
		initUI();
	}

	public void initUI() {

		try {
			
			imgAlterar = new ImageControl(new Image("img/alterar.png"));
			imgAlterar.scaleToFit = true;
			imgAlterar.centerImage = true;
			add(imgAlterar, CENTER, TOP - 20, SCREENSIZE + 15, SCREENSIZE + 30);
			
			lblCategoria = new Label("CATEGORIA:   ");
			add(lblCategoria);
			lblCategoria.setRect(LEFT + 90, AFTER - 10, PREFERRED, PREFERRED, imgAlterar);
			lblCategoria.setBackColor(0x003366);
			lblCategoria.setForeColor(Color.WHITE);

			editCategoria = new Edit();
			add(editCategoria);
			editCategoria.setRect(AFTER, SAME, FILL - 80, PREFERRED, lblCategoria);
			editCategoria.setBackColor(Color.WHITE);
			editCategoria.setForeColor(0x003366);
			editCategoria.setEditable(false);
			
			lblMarca = new Label("MARCA:           ");
			add(lblMarca);
			lblMarca.setRect(LEFT + 90, AFTER + 15, PREFERRED, PREFERRED, editCategoria);
			lblMarca.setBackColor(0x003366);
			lblMarca.setForeColor(Color.WHITE);

			editMarca = new Edit();
			add(editMarca);
			editMarca.setRect(AFTER, SAME, FILL - 80, PREFERRED, lblMarca);
			editMarca.setBackColor(Color.WHITE);
			editMarca.setForeColor(0x003366);
			editMarca.setEditable(false);
			
			lblDescricao = new Label("DESCRICÃO:   ");
			add(lblDescricao);
			lblDescricao.setRect(LEFT + 90, AFTER + 15, PREFERRED, PREFERRED, editMarca);
			lblDescricao.setBackColor(0x003366);
			lblDescricao.setForeColor(Color.WHITE);

			editDescricao = new Edit();
			add(editDescricao);
			editDescricao.setRect(AFTER, SAME, FILL - 80, PREFERRED, lblDescricao);
			editDescricao.setBackColor(Color.WHITE);
			editDescricao.setForeColor(0x003366);
			editDescricao.setEditable(false);		

			lblProduto = new Label("PRODUTO:      ");
			add(lblProduto);
			lblProduto.setRect(LEFT + 90, AFTER + 15, PREFERRED, PREFERRED, editDescricao);
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

			lblQuantidade = new Label("QUANTIDADE: ");
			add(lblQuantidade);
			lblQuantidade.setRect(LEFT + 90, AFTER + 15, PREFERRED, PREFERRED, lblEstoque);
			lblQuantidade.setBackColor(0x003366);
			lblQuantidade.setForeColor(Color.WHITE);

			editQuantidade = new Edit();
			add(editQuantidade);
			editQuantidade.setRect(AFTER, SAME, FILL - 80, PREFERRED, lblQuantidade);
			editQuantidade.setBackColor(Color.WHITE);
			editQuantidade.setForeColor(0x003366);
			editQuantidade.setValidChars("0 1 2 3 4 5 6 7 8 9");
			editQuantidade.setText(Carrinho.quantidadeProduto);

			lblValor = new Label("VALOR:            ");
			add(lblValor);
			lblValor.setRect(LEFT + 90, AFTER + 15, PREFERRED, PREFERRED, lblQuantidade);
			lblValor.setBackColor(0x003366);
			lblValor.setForeColor(Color.WHITE);

			editValor = new Edit();
			add(editValor);
			editValor.setRect(AFTER, SAME, FILL - 80, PREFERRED, lblValor);
			editValor.setBackColor(Color.WHITE);
			editValor.setForeColor(0x003366);
			editValor.setEditable(false);

			lblTotal = new Label("TOTAL:            ");
			add(lblTotal);
			lblTotal.setRect(LEFT + 90, AFTER + 15, PREFERRED, PREFERRED, editValor);
			lblTotal.setBackColor(0x003366);
			lblTotal.setForeColor(Color.WHITE);

			editTotal = new Edit();
			add(editTotal);
			editTotal.setRect(AFTER, SAME, FILL - 80, PREFERRED, lblTotal);
			editTotal.setBackColor(Color.WHITE);
			editTotal.setForeColor(0x003366);
			editTotal.setEditable(false);
			editTotal.setText(Carrinho.totalProduto);
			
			cmbTipoPagamento = new ComboBox();
			add(cmbTipoPagamento);
			cmbTipoPagamento.setRect(LEFT + 150, AFTER + 15, FILL - 140, PREFERRED, editTotal);
			
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
			carregaCmbTipoPagamento();
			
			if (Carrinho.tipoPagamentoProduto.equals("DINHEIRO")) {
				cmbTipoPagamento.setSelectedIndex(0);
			} else {
				cmbTipoPagamento.setSelectedIndex(1);
			}
			
			carregaInfoItem();
			
			calculaTotalProduto();
			
		} catch (Exception e) {
			Auxiliares.artMsgbox("ERRO","Erro ao construir a tela alterarItem\n" + e);

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

					if (editQuantidade.getText().length() == 0) {
						Auxiliares.artMsgbox("CONTROLE", "Por favor insira uma quantidade!");
						return;
					}

					if (editQuantidade.getText().length() > 0) {

						estoque = editEstoque.getText();
						quantidadeInserida = editQuantidade.getText();

						if (Convert.toInt(quantidadeInserida) > quantidadeTemp) {
							Auxiliares.artMsgbox("CONTROLE",
									"Quantidade inserida maior que a quantidade em estoque!");
							
							editQuantidade.requestFocus();
							return;

						}

						String[] ArtButtonArray = { "Sim", "Não" };

						int i = Auxiliares.artMsgbox("CONTROLE", "Deseja alterar este produto inserindo "
								+ editQuantidade.getText() + "\nunidade(s) no carrinho?", ArtButtonArray);

						if (i == 1) {
							return;

						} else {

							alteraProdutoCarrinho();

							Auxiliares.artMsgbox("CONTROLE", "Produto do carrinho alterado!");

							unpop();
							unpop();
						}
					}

				}
				if (editQuantidade.getText().length() > 0) {
					calculaTotalProduto();

				} else if (editQuantidade.getText().length() == 0) {
					editEstoque.setText(Convert.toString(quantidadeTemp));
					editTotal.setText("");
				}
			}
		} catch (Exception e) {
			Auxiliares.artMsgbox("ERRO", "Erro na validação da tela alterarItem\n" + e);
		}

	}

	public void calculaTotalProduto() {

		try {

			estoque = Convert.toString(quantidadeTemp);
			quantidadeInserida = editQuantidade.getText();
			qntEstoqueFinal = Convert.toInt(estoque) - Convert.toInt(quantidadeInserida);

			String quantidadeString = "";
			quantidadeString = editQuantidade.getText();
			valorProduto = Convert.toDouble(valorTemp.replace(".", "."));
			quantidade = Convert.toInt(quantidadeString);

			total = valorProduto * quantidade;
			editTotal.setText(Convert.toString(total, 2));
			editEstoque.setText(Convert.toString(qntEstoqueFinal));

		} catch (Exception e) {
			Auxiliares.artMsgbox("CONTROLE", "Erro no calculo do produto" + e);
		}

	}
	
	public void alteraProdutoCarrinho() {

		String sql = "";
		LitebasePack lb = null;
		ResultSet rs = null;

		try {
			try {
				
				lb = new LitebasePack();
				
				sql ="SELECT CODIGO, CODIGOPROD, PRODUTO, DESCRICAO, MARCA FROM VENDAPRODUTOTEMP"
					+" WHERE PRODUTO =   " + "'" + editProduto.getText() + "'"
					+" AND DESCRICAO = " + "'" + editDescricao.getText() + "'"
					+" AND MARCA =   " + "'" + editMarca.getText() + "'";;
					    
				rs = lb.executeQuery(sql);
				rs.first();
									
					int codigo = rs.getInt("CODIGO");
					int codigoProduto = rs.getInt("CODIGOPROD");
					
					sql = "DELETE VENDAPRODUTOTEMP "
						+ " WHERE CODIGOPROD = " + codigoProduto;
					
					lb.executeUpdate(sql);

					String dataVenda;
					Date year = new Date();
					Date month = new Date();
					Date day = new Date();

					dataVenda = Date.formatDate(year.getYear(), month.getMonth(), day.getDay());

					sql = "INSERT INTO VENDAPRODUTOTEMP " + "(" + " CODIGO, PRODUTO, VALOR, QUANTIDADE, CODIGOPROD, "
							+ " CATEGORIA, MARCA, DESCRICAO, TIPOPAGAMENTO, DATASAIDA " + ")" + " VALUES " 
							+ "( '" + codigo + "' , '" + editProduto.getText()
	                        + "', '" + editTotal.getText() + "', '"
							+ editQuantidade.getText() + "', '" + editCodigo.getText() + "','" + editCategoria.getText() + "','" 
							+ editMarca.getText() + "', '"+ editDescricao.getText() 
							+ "', '" + cmbTipoPagamento.getSelectedItem() + "', '" + dataVenda + "'" + ")";

					lb.executeUpdate(sql);

			} finally {
				if (lb != null)
					lb.closeAll();
			}

		} catch (Exception e) {
			Auxiliares.artMsgbox("ERRO", "Erro alteraProdutoCarrinho\n" + e);
		}

	}
	
	public void carregaCmbTipoPagamento() {
		{
			String sql = "";
			LitebasePack lb = null;
			ResultSet rs = null;

			try {
				try {
					lb = new LitebasePack();
					sql = " SELECT DESCRICAO FROM TIPOPAGAMENTO";

					rs = lb.executeQuery(sql);
					rs.first();
					for (int i = 0; rs.getRowCount() > i; i++) {
						String[] b = new String[1];
						b[0] = rs.getString("DESCRICAO");
						cmbTipoPagamento.add(b);
						rs.next();
					}
				} finally {
					if (lb != null)
						lb.closeAll();

				}
			} catch (Exception e) {
				Auxiliares.artMsgbox("ERRO", "Erro carregaCmbTipoPagamento\n" + e);

			}

		}
	}
	
	public void carregaInfoItem() {
		String sql = "";
		LitebasePack lb = null;
		ResultSet rs = null;

		try {
			try {
				lb = new LitebasePack();
				sql = " SELECT * FROM VENDAPRODUTOTEMP " 
				    + " WHERE CODIGOPROD = " + Carrinho.codigoProduto;

				rs = lb.executeQuery(sql);
				rs.first();

				editCategoria.setText(rs.getString("CATEGORIA"));
				editCodigo.setText(Convert.toString(rs.getInt("CODIGOPROD")));
				editDescricao.setText(rs.getString("DESCRICAO"));
				editEstoque.setText(Convert.toString(rs.getInt("QUANTIDADE")));
				editMarca.setText(rs.getString("MARCA"));
				editProduto.setText(rs.getString("PRODUTO"));
				

				sql = " SELECT * FROM ESTOQUE "
				    + " WHERE CODIGO = " + Carrinho.codigoProduto;

				rs = lb.executeQuery(sql);
				rs.first();

				valorTemp = rs.getString("VALOR");
				quantidadeTemp = rs.getInt("QUANTIDADE");
				editValor.setText(rs.getString("VALOR"));

			} finally {
				if (lb != null)
					lb.closeAll();

			}
		} catch (Exception e) {
			Auxiliares.artMsgbox("ERRO", "Erro carregaInfoItem\n" + e);

		}

	}

}
