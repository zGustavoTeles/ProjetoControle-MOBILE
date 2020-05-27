package com.configuracoes;

import com.agenda.Agenda;
import com.auxiliares.Auxiliares;
import com.litebase.LitebasePack;
import com.venda.Venda;
import litebase.ResultSet;
import nx.componentes.ArtButton;
import totalcross.sys.Convert;
import totalcross.ui.Button;
import totalcross.ui.Container;
import totalcross.ui.Edit;
import totalcross.ui.Grid;
import totalcross.ui.Label;
import totalcross.ui.dialog.MessageBox;
import totalcross.ui.event.ControlEvent;
import totalcross.ui.event.Event;
import totalcross.ui.event.GridEvent;
import totalcross.ui.event.PenEvent;
import totalcross.ui.gfx.Color;
import totalcross.util.Date;
import totalcross.sys.Settings;

public class ApagarVenda extends totalcross.ui.Window{
	
	private Label							lblData;
	private Label							lblA;
	private Edit							editQuantidade;
	private Edit							editTotal;
	private Edit							editProduto;
	private Edit							editMarca;
	private Edit							editDescricao;
	private Edit							editQntVendida;
	private Edit							editDataUm;
	private Edit							editDataDois;
	private Grid							gridProdutos;
	private ArtButton 						btnVoltar;
	private ArtButton						btnBuscar;
	private ArtButton						btnApagar;
	
	public String							dataI;
	public String							dataII;
	
	public static String					codigoVenda = "";
	public static String					produto = "";
	public static String					quantidade = "";
	public static String				    valor = "";
	public static String					dataVenda = "";
	public static String					marca = "";
	public static String  					descricao;
	public static String  					tipoPagamento;
	public static String  					categoria;
	public static String  					codigoProduto;
	
	public ApagarVenda(){
		 setBackColor(0x003366);
		 initUI();
	}
	
	public void initUI() {
		
		try {

			lblData = new Label("DATA: ");
			add(lblData);
			lblData.setRect(LEFT, TOP + 5, PREFERRED, PREFERRED);
			lblData.setBackColor(0x003366);
			lblData.setForeColor(Color.WHITE);

			editDataUm = new Edit();
			add(editDataUm);
			editDataUm.setRect(AFTER + 2, SAME, SCREENSIZE - 4, PREFERRED, lblData);
			editDataUm.setBackColor(Color.WHITE);
			editDataUm.setForeColor(0x003366);

			lblA = new Label(" Á ");
			add(lblA);
			lblA.setRect(AFTER + 2, SAME, PREFERRED, PREFERRED, editDataUm);
			lblA.setBackColor(0x003366);
			lblA.setForeColor(Color.WHITE);

			editDataDois = new Edit();
			add(editDataDois);
			editDataDois.setRect(AFTER + 2, SAME, SCREENSIZE - 4, PREFERRED, lblA);
			editDataDois.setBackColor(Color.WHITE);
			editDataDois.setForeColor(0x003366);

			btnBuscar = new ArtButton("BUSCAR");
			add(btnBuscar);
			btnBuscar.setRect(AFTER + 5 , SAME, SCREENSIZE - 5, PREFERRED, editDataDois);
			btnBuscar.setBackColor(0x003366);
			btnBuscar.setForeColor(Color.WHITE);
			
			btnVoltar = new ArtButton("VOLTAR");
			add(btnVoltar);
			btnVoltar.setRect(RIGHT, BOTTOM, SCREENSIZE - 4, PREFERRED + 15);
			btnVoltar.setBackColor(0x003366);
			btnVoltar.setForeColor(Color.WHITE);
			
			btnApagar = new ArtButton("APAGAR");
			add(btnApagar);
			btnApagar.setRect(LEFT, BOTTOM, SCREENSIZE - 4, PREFERRED + 15);
			btnApagar.setBackColor(0xDF0101);
			btnApagar.setForeColor(Color.WHITE);
			
			editProduto = new Edit();
			add(editProduto);
			editProduto.setEditable(false);
			editProduto.setRect(LEFT + 2, BEFORE - 40, SCREENSIZE - 2, PREFERRED, btnApagar);
			editProduto.setBackColor(0x003366);
			editProduto.setForeColor(Color.BLACK);
			editProduto.setText("PRODUTO:");
			
			editMarca = new Edit();
			add(editMarca);
			editMarca.setEditable(false);
			editMarca.setRect(RIGHT - 5, BEFORE - 40, SCREENSIZE - 2, PREFERRED, btnVoltar);
			editMarca.setBackColor(0x003366);
			editMarca.setForeColor(Color.BLACK);
			editMarca.setText("MARCA:");
			
			editQuantidade = new Edit();
			add(editQuantidade);
			editQuantidade.setEditable(false);
			editQuantidade.setRect(LEFT + 2, AFTER, SCREENSIZE - 2, PREFERRED, editProduto);
			editQuantidade.setBackColor(0x003366);
			editQuantidade.setForeColor(Color.BLACK);
			editQuantidade.setText("QUANTIDADE:");
			
			editTotal = new Edit();
			add(editTotal);
			editTotal.setEditable(false);
			editTotal.setRect(RIGHT - 5, AFTER, SCREENSIZE - 2, PREFERRED, editMarca);
			editTotal.setBackColor(0x003366);
			editTotal.setForeColor(Color.BLACK);
			editTotal.setText("TOTAL: R$:");

			int gridWidths[] = new int[10];
			gridWidths[0] = 120;
			gridWidths[1] = 5;
			gridWidths[2] = 400;
			gridWidths[3] = 300;
			gridWidths[4] = 100;
			gridWidths[5] = 100;
			gridWidths[6] = 170;
			gridWidths[7] = 190;
			gridWidths[8] = 140;
			gridWidths[9] = 0;

			String[] caps = { "DATA", "COD.", "PRODUTO", "MARCA", "DESC.", "QNT", "TIPO.PAG.", "CATEGORIA", "VALOR", "COD.PROD." };
			int[] aligns = { Grid.LEFT, Grid.CENTER, Grid.LEFT, Grid.LEFT, Grid.LEFT, Grid.LEFT, Grid.LEFT, Grid.LEFT, Grid.LEFT, Grid.LEFT };
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
			gridProdutos.setRect(Container.LEFT + 1, Container.AFTER + 10, Container.FILL - 1, Container.FIT, lblData);
			
			reposition();

			
		} catch (Exception e) {
			Auxiliares.artMsgbox("ERRO","Erro ao construir a tela ApagarVenda\n" + e);

		}
		
	}

	public void onEvent(Event evt) {
		try {
			switch (evt.type) {
			case ControlEvent.PRESSED:

				if (evt.target == btnVoltar) {
					unpop();

				} else if (evt.target == btnBuscar) {
					if (editDataUm.getText().equals("") || editDataDois.getText().equals("")) {

						Auxiliares.artMsgbox("CONTROLE", "Preencha todos os campos de data à serem pesquisados!");
						return;

					} else {
						pesquisaVendasPorPeriodo();
					}
				} else if (evt.target == btnApagar) {
					if (gridProdutos.getSelectedItem() == null) {
						Auxiliares.artMsgbox("CONTROLE", "Deve-se selecionar uma venda!");
						return;
					} else {
						
						gridProdutos.removeAllElements();
						editProduto.setText("PRODUTO:");
						editMarca.setText("MARCA:");
						editQuantidade.setText("QUANTIDADE:");
						editTotal.setText("TOTAL: R$ ");
						
						ApagarVendaSistema apagarVendaSistema = new ApagarVendaSistema();
						apagarVendaSistema.popup();
					}
				}

				break;
			case PenEvent.PEN_DOWN:

				if (evt.target == editDataUm) {
					Agenda.setDateByCalendarBox(editDataUm);

				} else if (evt.target == editDataDois) {
					Agenda.setDateByCalendarBox(editDataDois);
				}
				break;
			case GridEvent.SELECTED_EVENT:
				if (evt.target == gridProdutos) {

					try {

						dataVenda = gridProdutos.getSelectedItem()[0];
						codigoVenda = gridProdutos.getSelectedItem()[1];
						produto = gridProdutos.getSelectedItem()[2];
						marca = gridProdutos.getSelectedItem()[3];
						descricao = gridProdutos.getSelectedItem()[4];
						quantidade = gridProdutos.getSelectedItem()[5];
						tipoPagamento = gridProdutos.getSelectedItem()[6];
						categoria = gridProdutos.getSelectedItem()[7];
						valor = gridProdutos.getSelectedItem()[8];
						codigoProduto = gridProdutos.getSelectedItem()[9];
						
						editProduto.setText("PRODUTO: "+ produto);
						editMarca.setText("MARCA: "+ marca);
						editQuantidade.setText("QUANTIDADE: "+ quantidade);						
						editTotal.setText( "TOTAL: " + Convert.toCurrencyString(valor, 2).replace(".", " "));
						
					} catch (Exception e) {
						Auxiliares.artMsgbox("CONTROLE", "Clique em um Item!");
					}

				}

			}

		} catch (Exception e) {
			Auxiliares.artMsgbox("ERRO", "Erro na validação da tela relatorio\n" + e);
		}

	}
	
	public void pesquisaVendasPorPeriodo() throws Exception {
		String sql		   = "";
		LitebasePack lb    = null;
		ResultSet rs	   = null;

		try {
			
			try {
				gridProdutos.removeAllElements();
				
				dataI    = new Date(editDataUm.getText()).toString(Settings.DATE_YMD);
				dataII   = new Date(editDataDois.getText()).toString(Settings.DATE_YMD);
				
				lb = new LitebasePack();
				sql = " SELECT * FROM VENDAPRODUTO "
					+ " WHERE DATASAIDA >= " + "'" + dataI + "'"
					+ " AND DATASAIDA<= " + "'" + dataII + "'";

				rs = lb.executeQuery(sql);
				rs.first();
				for (int i = 0; rs.getRowCount() > i; i++) {
					String[] b = new String[10];
					b[0] = rs.getString("DATASAIDA");
					b[1] = Convert.toString(rs.getInt("CODIGO"));
					b[2] = rs.getString("PRODUTO");
					b[3] = rs.getString("MARCA");
					b[4] = rs.getString("DESCRICAO");
					b[5] = Convert.toString(rs.getInt("QUANTIDADE"));
					b[6] = rs.getString("TIPOPAGAMENTO");
					b[7] = rs.getString("CATEGORIA");
					b[8] = "R$ " + rs.getString("VALOR");
					b[9] = rs.getString("CODIGOPROD");
					gridProdutos.add(b);
					
					rs.next();
				}

				
			} finally {
				if (lb != null) {
					lb.closeAll();
				}
			}

		} catch (Exception e) {
			Auxiliares.artMsgbox("ERRO", "Erro ao pesquisaVendasPorPeriodo\n" + e);

		}

	}	
	
}
