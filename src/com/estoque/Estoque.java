package com.estoque;

import com.auxiliares.Auxiliares;
import com.carrinho.AlterarProdutoCarrinho;
import com.inserir.Inserir;
import com.litebase.LitebasePack;
import litebase.ResultSet;
import nx.componentes.ArtButton;
import totalcross.sys.Convert;
import totalcross.ui.ComboBox;
import totalcross.ui.Container;
import totalcross.ui.Edit;
import totalcross.ui.Grid;
import totalcross.ui.Label;
import totalcross.ui.dialog.MessageBox;
import totalcross.ui.event.ControlEvent;
import totalcross.ui.event.Event;
import totalcross.ui.event.GridEvent;
import totalcross.ui.gfx.Color;

public class Estoque extends totalcross.ui.Window{
	
	private Label 							lblBuscar;
	private Label						    lblProduto;
	private ArtButton 						btnVoltar;
	private ArtButton						btnBuscar;
	private ArtButton						btnRemover;
	private ArtButton						btnAlterar;
	private	ComboBox						cmbCategoria;
	private Edit							editBuscar;
	private Grid							gridProdutos;
	
	public static String					codigo = "";
	public static String					quantidade = "";
	public static String					produto = "";
	
	public Estoque(){
		 setBackColor(0x003366);
		 initUI();
	}
	
	public void initUI() {
		
		try {
			
			lblProduto = new Label("CATEGORIA: ");
			add(lblProduto);
			lblProduto.setRect(LEFT,  TOP + 5, PREFERRED, PREFERRED);
			lblProduto.setBackColor(0x003366);
			lblProduto.setForeColor(Color.WHITE);
			
			cmbCategoria = new ComboBox();
			add(cmbCategoria);
			cmbCategoria.setRect(LEFT, AFTER + 5, FILL + 5, PREFERRED, lblProduto);
			
			lblBuscar = new Label("BUSCAR");
			add(lblBuscar);
			lblBuscar.setRect(LEFT,AFTER + 5, PREFERRED, PREFERRED, cmbCategoria);
			lblBuscar.setBackColor(0x003366);
			lblBuscar.setForeColor(Color.WHITE);
			
			editBuscar = new Edit();
			add(editBuscar);
			editBuscar.capitalise = (Edit.ALL_UPPER);
			editBuscar.setRect(LEFT, AFTER + 5, width - 160, PREFERRED, lblBuscar);
			editBuscar.setBackColor(Color.WHITE);
			editBuscar.setForeColor(0x003366);

			btnBuscar = new ArtButton("BUSCAR");
			add(btnBuscar);
			btnBuscar.setRect(AFTER + 1, SAME, SCREENSIZE - 7, PREFERRED, editBuscar);
			btnBuscar.setBackColor(0x003366);
	        btnBuscar.setForeColor(Color.WHITE);
	        
	        btnRemover = new ArtButton("REMOVER");
            add(btnRemover);
            btnRemover.setRect(LEFT, BOTTOM, SCREENSIZE - 5, PREFERRED + 15);
            btnRemover.setBackColor(0xDF0101);
            btnRemover.setForeColor(Color.WHITE);
            
            btnAlterar = new ArtButton("ALTERAR");
            add(btnAlterar);
            btnAlterar.setRect(AFTER + 5, SAME, SCREENSIZE - 5, PREFERRED + 15, btnRemover);
            btnAlterar.setBackColor(0xDF7401);
            btnAlterar.setForeColor(Color.WHITE);
	            
            btnVoltar = new ArtButton("VOLTAR");
            add(btnVoltar);
            btnVoltar.setRect(RIGHT, BOTTOM, SCREENSIZE - 4, PREFERRED + 15);
            btnVoltar.setBackColor(0x003366);
            btnVoltar.setForeColor(Color.WHITE);
            
            int gridWidths[] = new int[8];
			gridWidths[0] = 5;
			gridWidths[1] = 400;
			gridWidths[2] = 100;
			gridWidths[3] = 300;
			gridWidths[4] = 190;
			gridWidths[5] = 100;
			gridWidths[6] = 140;
			gridWidths[7] = 120;

		String[] caps = { "COD.", "PRODUTO", "DESC", "MARCA", "CATEGORIA","QNT", " VALOR", "ENTRADA"};
		int[] aligns = { Grid.LEFT, Grid.CENTER, Grid.LEFT, Grid.LEFT, Grid.LEFT, Grid.LEFT, Grid.LEFT, Grid.LEFT};
		gridProdutos = new Grid(caps, gridWidths, aligns, false);
		add(gridProdutos);
		gridProdutos.setBackColor(Color.WHITE);
		gridProdutos.setForeColor(0x003366);
		gridProdutos.transparentBackground = false;
		gridProdutos.setBorderStyle(totalcross.ui.Container.BORDER_NONE);
		gridProdutos.verticalLineStyle = totalcross.ui.Grid.VERT_LINE;
		gridProdutos.drawCheckBox = true;
		gridProdutos.disableSort = false;
		gridProdutos.canClickSelectAll = true;
		gridProdutos.boldCheck = false;
		gridProdutos.enableColumnResize = false;
		gridProdutos.setRect(Container.LEFT + 1, Container.AFTER + 10,
				Container.FILL - 1, Container.FIT, btnBuscar);
		
		reposition();
		editBuscar.requestFocus();
		
		} catch (Exception e) {
			Auxiliares.artMsgbox("ERRO","Erro ao construir a tela Estoque\n" + e);

		}
		
	}
	
	public void onEvent(Event evt) {
		try {
			switch (evt.type) {
			case ControlEvent.PRESSED:

				if (evt.target == btnVoltar) {
					unpop();

				} else if (evt.target == btnRemover) {

					if (gridProdutos.getSelectedItem() != null) {

						boolean prodAdicionado = false;
						prodAdicionado = validaProdutoCarrinho(prodAdicionado);

						if (prodAdicionado == false) {
							gridProdutos.removeAllElements();
							RemoverProduto removerProduto = new RemoverProduto();
							removerProduto.popup();
						} else {

							Auxiliares.artMsgbox("CONTROLE",
									"Esse produto encontra-se no carrinho. Por favor finalize a venda para remove-lo do estoque!");
						}

					} else {
						Auxiliares.artMsgbox("CONTROLE", "Deve-se selecionar um item!");
					}

				} else if (evt.target == btnBuscar) {

					gridProdutos.removeAllElements();
					carregaGridProdutosBusca();

				} else if (evt.target == cmbCategoria) {
					if (cmbCategoria.getItems() != null) {
						editBuscar.setText("");
						gridProdutos.removeAllElements();
						carregaGridProdutos();
					} else {
						return;
					}
				} else if (evt.target == btnAlterar) {

					if (gridProdutos.getSelectedItem() != null) {

						boolean prodAdicionado = false;
						prodAdicionado = validaProdutoCarrinho(prodAdicionado);

						if (prodAdicionado == false) {
							gridProdutos.removeAllElements();
							AlterarProduto alterarProduto = new AlterarProduto();
							alterarProduto.popup();
						} else {

							Auxiliares.artMsgbox("CONTROLE",
									"Esse produto encontra-se no carrinho. Por favor finalize a venda para altera-lo do estoque!");
						}

					} else {
						Auxiliares.artMsgbox("CONTROLE", "Deve-se selecionar um item!");
					}

				}
				break;

			case GridEvent.SELECTED_EVENT:
				if (evt.target == gridProdutos) {

					try {

						codigo = gridProdutos.getSelectedItem()[0];
						produto = gridProdutos.getSelectedItem()[1];
						quantidade = gridProdutos.getSelectedItem()[5];

					} catch (Exception e) {
						Auxiliares.artMsgbox("CONTROLE", "Clique em um Item!");
					}

				}
				break;
			case ControlEvent.FOCUS_IN:
				if (evt.target == cmbCategoria) {
					cmbCategoria.removeAll();
					carregaCmbCategoria();
				}
			}

		} catch (Exception e) {
			Auxiliares.artMsgbox("ERRO", "Erro na validação da tela Estoque\n" + e);
		}

	}
	
	public void carregaGridProdutos() {
		String sql = "";
		LitebasePack lb = null;
		ResultSet rs = null;

		try {
			try {
				lb = new LitebasePack();
				sql = " SELECT * FROM ESTOQUE WHERE CATEGORIA = " + "'" + cmbCategoria.getSelectedItem() + "'";

				rs = lb.executeQuery(sql);
				rs.first();
				for (int i = 0; rs.getRowCount() > i; i++) {
					String[] b = new String[8];
					b[0] = Convert.toString(rs.getInt("CODIGO"));
					b[1] = rs.getString("PRODUTO");
					b[2] = rs.getString("DESCRICAO");
					b[3] = rs.getString("MARCA");
					b[4] = rs.getString("CATEGORIA");
					b[5] = Convert.toString(rs.getInt("QUANTIDADE"));
					b[6] = rs.getString("VALOR");
					b[7] = rs.getString("DATAENTRADA");
					gridProdutos.add(b);
					rs.next();
	
				}
			} finally {
				if (lb != null)
					lb.closeAll();

			}
		} catch (Exception e) {
			Auxiliares.artMsgbox("ERRO", "Erro carregaGridProdutos\n" + e);

		}

	}

	public void carregaCmbCategoria() {
		{
			String sql = "";
			LitebasePack lb = null;
			ResultSet rs = null;

			try {
				try {
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
				Auxiliares.artMsgbox("ERRO", "Erro carregaCmbCategoria\n" + e);

			}

		}
	}
	
	public void carregaGridProdutosBusca() {
		String sql = "";
		LitebasePack lb = null;
		ResultSet rs = null;

		try {
			try {
				lb = new LitebasePack();
				sql = " SELECT * FROM ESTOQUE WHERE PRODUTO LIKE " 
					    + "'%" + editBuscar.getText() + "%'"
					    + "OR MARCA LIKE "  + "'%" + editBuscar.getText() + "%'"
	 					+ "OR CATEGORIA LIKE "  + "'%" + editBuscar.getText() + "%'"
					    + "OR DESCRICAO LIKE "  + "'%" + editBuscar.getText() + "%'";

				rs = lb.executeQuery(sql);
				rs.first();
				for (int i = 0; rs.getRowCount() > i; i++) {
					String[] b = new String[8];
					b[0] = Convert.toString(rs.getInt("CODIGO"));
					b[1] = rs.getString("PRODUTO");
					b[2] = rs.getString("DESCRICAO");
					b[3] = rs.getString("MARCA");
					b[4] = rs.getString("CATEGORIA");
					b[5] = Convert.toString(rs.getInt("QUANTIDADE"));
					b[6] = rs.getString("VALOR");
					b[7] = rs.getString("DATAENTRADA");
					gridProdutos.add(b);
					rs.next();
				}
			} finally {
 				if (lb != null)
					lb.closeAll();

			}
		} catch (Exception e) {
			Auxiliares.artMsgbox("ERRO", "Erro ao carregaGridProdutosBusca\n" + e);

		}

	}
	
	public boolean validaProdutoCarrinho(boolean prodAdicionado) {
 		String sql = "";
		LitebasePack lb = null;
		ResultSet rs = null;

		try {

			try {

				lb = new LitebasePack();

				sql = "SELECT * FROM VENDAPRODUTOTEMP WHERE CODIGOPROD = " + codigo;

				rs = lb.executeQuery(sql);
				rs.first();

				if (rs.getRowCount() == 0) {
					return prodAdicionado = false;

				} else {
					
					return prodAdicionado = true;
				}

			}
			finally {
				if (lb != null) {
					lb.closeAll();
				}
			}

		} 
		catch (Exception e) {
			Auxiliares.artMsgbox("ERRO", "Erro validaProdutoCarrinho\n" + e);
		}
		return prodAdicionado;

	}
}
