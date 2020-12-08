package com.estoque;

import com.auxiliares.Auxiliares;
import com.bottom.BottomImg;
import com.carrinho.AlterarProdutoCarrinho;
import com.cellcontroller6.CellController6;
import com.inserir.InserirProdutoCarrinho;
import com.litebase.LitebasePack;
import litebase.ResultSet;
import totalcross.sys.Convert;
import totalcross.ui.Button;
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
import totalcross.ui.image.Image;

public class Estoque extends totalcross.ui.Window{
	
	private Label 							lblBuscar;
	private Label						    lblProduto;
	private Button						btnBuscar;
	private Button 							btnVoltar;
	private Button							btnRemover;
	private Button							btnAlterar;
	private	ComboBox						cmbCategoria;
	private Edit							editBuscar;
	private Grid							gridProdutos;
	
	public static String					codigo = "";
	public static String					quantidade = "";
	public static String					produto = "";
	
	public Estoque(){
		 setBackColor(0x1c355d);
		 initUI();
	}
	
	public void initUI() {
		
		try {
			
			lblProduto = new Label("CATEGORIA: ");
			add(lblProduto);
			lblProduto.setRect(LEFT,  TOP + 5, PREFERRED, PREFERRED);
			lblProduto.setBackColor(0x1c355d);
			lblProduto.setForeColor(Color.WHITE);
			
			cmbCategoria = new ComboBox();
			add(cmbCategoria);
			cmbCategoria.setRect(LEFT, AFTER + 5, SCREENSIZE + 100, PREFERRED, lblProduto);
			
			lblBuscar = new Label("BUSCAR");
			add(lblBuscar);
			lblBuscar.setRect(LEFT,AFTER + 5, PREFERRED, PREFERRED, cmbCategoria);
			lblBuscar.setBackColor(0x1c355d);
			lblBuscar.setForeColor(Color.WHITE);
			
			editBuscar = new Edit();
			add(editBuscar);
			editBuscar.capitalise = (Edit.ALL_UPPER);
			editBuscar.setRect(LEFT, AFTER + 5, SCREENSIZE + 80, PREFERRED, lblBuscar);
			editBuscar.setBackColor(Color.WHITE);
			editBuscar.setForeColor(0x1c355d);

			btnBuscar = new Button("BUSCAR");
			add(btnBuscar);
			btnBuscar.setRect(AFTER + 1, SAME, SCREENSIZE + 20, PREFERRED, editBuscar);
			btnBuscar.setBackColor(0x1c355d);
	        btnBuscar.setForeColor(Color.WHITE);
	        
	        btnRemover = new Button("REMOVER");
            add(btnRemover);
            btnRemover.setRect(LEFT, BOTTOM, SCREENSIZE - 5, PREFERRED + 15);
            btnRemover.setBackColor(0x1c355d);
            btnRemover.setForeColor(Color.WHITE);
            
            btnVoltar = BottomImg.imageWithText(new Image("img/voltar.png"), "Voltar", Button.BOTTOM);
			add(btnVoltar, RIGHT - 2, BOTTOM, SCREENSIZE + 25, PREFERRED + 10);
			btnVoltar.setBackColor(0x1c355d);
			btnVoltar.setForeColor(Color.WHITE);
            
            btnRemover = BottomImg.imageWithText(new Image("img/remover.png"), "Remover", Button.BOTTOM);
			add(btnRemover, LEFT, BOTTOM, SCREENSIZE + 25, PREFERRED + 10);
			btnRemover.setBackColor(0x1c355d);
			btnRemover.setForeColor(Color.WHITE);
            
			btnAlterar = BottomImg.imageWithText(new Image("img/alterar.png"), "Alterar", Button.BOTTOM);
			add(btnAlterar, AFTER + 8, BOTTOM, SCREENSIZE + 25, PREFERRED + 10);
			btnAlterar.setBackColor(0x1c355d);
			btnAlterar.setForeColor(Color.WHITE);
            
            int gridWidths[] = new int[7];
			gridWidths[0] = 5;
			gridWidths[1] = 400;
			gridWidths[2] = 100;
			gridWidths[3] = 300;
			gridWidths[4] = 190;
			gridWidths[5] = 100;
			gridWidths[6] = 140;

			String[] caps = { "COD.", "PRODUTO", "DESC", "MARCA", "CATEGORIA", "ESTOQUE", " PREÇO" };
			int[] aligns = { Grid.LEFT, Grid.CENTER, Grid.LEFT, Grid.LEFT, Grid.LEFT, Grid.LEFT, Grid.LEFT };
			gridProdutos = new Grid(caps, gridWidths, aligns, false);
			add(gridProdutos);
			gridProdutos.enableColumnResize = true;
			gridProdutos.secondStripeColor = Color.getRGB(255, 255, 255);
			gridProdutos.setBackColor(Auxiliares.backColorGridNovo);
			gridProdutos.setForeColor(Auxiliares.defaultForeColorGridNovo);
			gridProdutos.transparentBackground = false;
			gridProdutos.setBorderStyle(totalcross.ui.Container.BORDER_NONE);
			gridProdutos.verticalLineStyle = totalcross.ui.Grid.VERT_LINE;
			gridProdutos.drawCheckBox = true;
			gridProdutos.disableSort = false;
			gridProdutos.boldCheck = false;
			gridProdutos.captionsBackColor = Auxiliares.captionsBackColorNovo;
			gridProdutos.checkColor = Auxiliares.checkColorNovo;
			gridProdutos.firstStripeColor = Auxiliares.firstStripeColorNovo;
			gridProdutos.enableColumnResize = false;
			gridProdutos.setFont(Auxiliares.getFontNormal().asBold());
			gridProdutos.secondStripeColor = Auxiliares.secondStripeColorNovo;
			gridProdutos.setCellController(new CellController6());
			gridProdutos.setRect(Grid.CENTER + 1, BEFORE - 5, SCREENSIZE + 100, SCREENSIZE + 75, btnVoltar);
		
		reposition();
		editBuscar.requestFocus();
		
		} catch (Exception e) {
			Auxiliares.messagebox("ERRO","Erro ao construir a tela Estoque\n" + e);

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
							RemoverProdutoEstoque removerProduto = new RemoverProdutoEstoque();
							removerProduto.popup();
						} else {

							Auxiliares.messagebox("SOLUCAO",
									"Esse produto encontra-se no carrinho. Por favor finalize a venda para remove-lo do estoque!");
						}

					} else {
						Auxiliares.messagebox("SOLUCAO", "Deve-se selecionar um item!");
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
							AlterarProdutoEstoque alterarProduto = new AlterarProdutoEstoque();
							alterarProduto.popup();
						} else {

							Auxiliares.messagebox("SOLUCAO",
									"Esse produto encontra-se no carrinho. Por favor finalize a venda para altera-lo do estoque!");
						}

					} else {
						Auxiliares.messagebox("SOLUCAO", "Deve-se selecionar um item!");
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
						Auxiliares.messagebox("SOLUCAO", "Clique em um Item!");
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
			Auxiliares.messagebox("ERRO", "Erro na validação da tela Estoque\n" + e);
		}

	}
	
		String sql = "";
		public void carregaGridProdutos() {
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
			Auxiliares.messagebox("ERRO", "Erro carregaGridProdutos\n" + e);

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
				Auxiliares.messagebox("ERRO", "Erro carregaCmbCategoria\n" + e);

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
			Auxiliares.messagebox("ERRO", "Erro ao carregaGridProdutosBusca\n" + e);

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
			Auxiliares.messagebox("ERRO", "Erro validaProdutoCarrinho\n" + e);
		}
		return prodAdicionado;

	}
}
