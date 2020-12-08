package com.menu;

import com.auxiliares.Auxiliares;
import com.cadastrar.CadastrarProdutoEstoque;
import com.cellcontroller6.CellController6;
import com.configuracoes.Configuracoes;
import com.configuracoes.Perfil;
import com.estoque.Estoque;
import com.litebase.LitebasePack;
import com.relatorio.Relatorio;
import com.venda.Loja;
import litebase.ResultSet;
import principal.Solucao;
import totalcross.sys.Convert;
import totalcross.sys.Settings;
import totalcross.ui.Button;
import totalcross.ui.Grid;
import totalcross.ui.ImageControl;
import totalcross.ui.event.ControlEvent;
import totalcross.ui.event.Event;
import totalcross.ui.gfx.Color;
import totalcross.ui.image.Image;
import totalcross.util.Date;

import com.bottom.BottomImg;

public class Menu extends totalcross.ui.Window {
	
	private Button						 btnLoja;
	private Button 						 btnRelatorio;
	private Button						 btnVoltar;
	private Button						 btnEstoque;
	private Button						 btnConfig;
	
	public ImageControl 		         imgMenu;
	
	
	
	public static Grid							 gridVendas;

	public Menu() {
		setBackColor(0x1c355d);
		initUI();

	}

	public void initUI() {

		try {
			
			imgMenu = new ImageControl(new Image("img/menuCapa.png"));
			imgMenu.scaleToFit = true;
			imgMenu.centerImage = true;
	        add(imgMenu, CENTER, TOP - 40, SCREENSIZE + 40, SCREENSIZE + 40);
	            
			btnLoja = BottomImg.imageWithText(new Image("img/compra.png"), "Loja", Button.BOTTOM);
			add(btnLoja, LEFT, BOTTOM, SCREENSIZE + 18, SCREENSIZE + 15);
			btnLoja.setBackColor(0x1c355d);
			btnLoja.setForeColor(Color.WHITE);
			
			btnEstoque = BottomImg.imageWithText(new Image("img/estoque.png"), "Estoque", Button.BOTTOM);
			add(btnEstoque, AFTER + 8, BOTTOM, SCREENSIZE + 18, SCREENSIZE + 15);
			btnEstoque.setBackColor(0x1c355d);
			btnEstoque.setForeColor(Color.WHITE);
			
			btnRelatorio = BottomImg.imageWithText(new Image("img/relatorios.png"), "Relatório", Button.BOTTOM);
			add(btnRelatorio, AFTER + 8, BOTTOM, SCREENSIZE + 18, SCREENSIZE + 15);
			btnRelatorio.setBackColor(0x1c355d);
			btnRelatorio.setForeColor(Color.WHITE);
			
			btnConfig = BottomImg.imageWithText(new Image("img/config.png"), "Config", Button.BOTTOM);
			add(btnConfig, AFTER + 8, BOTTOM, SCREENSIZE + 18, SCREENSIZE + 15);
			btnConfig.setBackColor(0x1c355d);
			btnConfig.setForeColor(Color.WHITE);

			btnVoltar = BottomImg.imageWithText(new Image("img/voltar.png"), "Voltar", Button.BOTTOM);
			add(btnVoltar, RIGHT - 2, BOTTOM, SCREENSIZE + 18, SCREENSIZE + 15);
			btnVoltar.setBackColor(0x1c355d);
			btnVoltar.setForeColor(Color.WHITE);
			
			int gridWidths[] = new int[9];
			gridWidths[0] = 120;
			gridWidths[1] = 5;
			gridWidths[2] = 400;
			gridWidths[3] = 300;
			gridWidths[4] = 100;
			gridWidths[5] = 100;
			gridWidths[6] = 170;
			gridWidths[7] = 190;
			gridWidths[8] = 140;

			String[] caps = { "DATA", "COD.", "PRODUTO", "MARCA", "DESC.", "QNT", "TIPO.PAG.", "CATEGORIA", "VALOR" };
			int[] aligns = { Grid.LEFT, Grid.CENTER, Grid.LEFT, Grid.LEFT, Grid.LEFT, Grid.LEFT, Grid.LEFT, Grid.LEFT, Grid.LEFT };
			gridVendas = new Grid(caps, gridWidths, aligns, false);
			add(gridVendas);
			gridVendas.enableColumnResize = true;
			gridVendas.secondStripeColor = Color.getRGB(255, 255, 255);
			gridVendas.setBackColor(Auxiliares.backColorGridNovo);
			gridVendas.setForeColor(Auxiliares.defaultForeColorGridNovo);
			gridVendas.transparentBackground = false;
			gridVendas.setBorderStyle(totalcross.ui.Container.BORDER_NONE);
			gridVendas.verticalLineStyle = totalcross.ui.Grid.VERT_LINE;
			gridVendas.drawCheckBox = true;
			gridVendas.disableSort = false;
			gridVendas.boldCheck = false;
			gridVendas.captionsBackColor = Auxiliares.captionsBackColorNovo;
			gridVendas.checkColor = Auxiliares.checkColorNovo;
			gridVendas.firstStripeColor = Auxiliares.firstStripeColorNovo;
			gridVendas.enableColumnResize = false;
			gridVendas.setFont(Auxiliares.getFontNormal().asBold());
			gridVendas.secondStripeColor = Auxiliares.secondStripeColorNovo;
			gridVendas.setCellController(new CellController6());
			gridVendas.setRect(Grid.CENTER + 1, BEFORE - 5, SCREENSIZE + 100, SCREENSIZE + 60, btnVoltar);
			
			carregaGridVendas();
			
			reposition();
			
		} catch (Exception e) {
			Auxiliares.messagebox("ERRO", "Erro ao construir tela menu\n" + e);

		}

	}

	public void onEvent(Event evt) {
		try {
			switch (evt.type) {
			case ControlEvent.PRESSED:

				if (evt.target == btnLoja) {
					Loja loja = new Loja();
					loja.popup();

				} else if (evt.target == btnConfig) {
					Configuracoes configuracoes = new Configuracoes();
					configuracoes.popup();

				} else if (evt.target == btnEstoque) {
					Estoque estoque = new Estoque();
					estoque.popup();

				} else if (evt.target == btnRelatorio) {
					Relatorio relatorio = new Relatorio();
					relatorio.popup();

				} else if (evt.target == btnVoltar) {
					unpop();
				}

			}
		} catch (Exception e) {
			Auxiliares.messagebox("ERRO", "Erro na validação do menu\n" + e);
		}

	}
	
	public static void  carregaGridVendas() throws Exception {
		
		String sql		   	 = "";
		LitebasePack lb   	 = null;
		ResultSet rs	  	 = null;

		try {
			
			try {
				gridVendas.removeAllElements();
				
				lb = new LitebasePack();
				sql = " SELECT * FROM VENDAPRODUTO ";

				rs = lb.executeQuery(sql);
				rs.first();
				for (int i = 0; rs.getRowCount() > i; i++) {
					String[] b = new String[9];
					b[0] = rs.getString("DATASAIDA");
					b[1] = Convert.toString(rs.getInt("CODIGO"));
					b[2] = rs.getString("PRODUTO");
					b[3] = rs.getString("MARCA");
					b[4] = rs.getString("DESCRICAO");
					b[5] = Convert.toString(rs.getInt("QUANTIDADE"));
					b[6] = rs.getString("TIPOPAGAMENTO");
					b[7] = rs.getString("CATEGORIA");
					b[8] = "R$ " + rs.getString("VALOR");
					gridVendas.add(b);
					rs.next();
				}
				
			} finally {
				if (lb != null) {
					lb.closeAll();
				}
			}

		} catch (Exception e) {
			Auxiliares.messagebox("ERRO", "Erro ao pesquisaVendasPorPeriodo\n" + e);

		}

	}
}
