package com.configuracoes;


import com.auxiliares.Auxiliares;
import com.cadastrar.CadastrarProdutoEstoque;
import com.cellcontroller6.CellController6;
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

public class Configuracoes extends totalcross.ui.Window {
	
	private Button						 btnCadastrar;
	private Button    					 btnRelatorio;
	private Button 						 btnSobre;
	private Button						 btnVoltar;
	private Button						 btnSuporte;
	private Button						 btnApagar;
	private Button						 btnPerfil;
	
	public ImageControl 		         imgMenu;
	
	
	
	public static Grid							 gridVendas;

	public Configuracoes() {
		setBackColor(0x1c355d);
		initUI();

	}

	public void initUI() {

		try {
	            
			btnCadastrar = BottomImg.imageWithText(new Image("img/cadastrarProdutoSistema.png"), "Cadastrar", Button.BOTTOM);
			add(btnCadastrar, CENTER, TOP + 100, SCREENSIZE + 90, SCREENSIZE + 10);
			btnCadastrar.setBackColor(0x202b4b);
			btnCadastrar.setForeColor(Color.WHITE);
			
			btnApagar = BottomImg.imageWithText(new Image("img/apagarProdutoSistema.png"), "Apagar", Button.BOTTOM);
			add(btnApagar, CENTER, AFTER + 5, SCREENSIZE + 90, SCREENSIZE + 10);
			btnApagar.setBackColor(0x202b4b);
			btnApagar.setForeColor(Color.WHITE);
			
			btnSuporte = BottomImg.imageWithText(new Image("img/suporte.png"), "Suporte", Button.BOTTOM);
			add(btnSuporte, CENTER, AFTER + 5, SCREENSIZE + 90, SCREENSIZE + 10);
			btnSuporte.setBackColor(0x202b4b);
			btnSuporte.setForeColor(Color.WHITE);
			
			btnPerfil = BottomImg.imageWithText(new Image("img/perfil.png"), "Perfil", Button.BOTTOM);
			add(btnPerfil, CENTER, AFTER + 5, SCREENSIZE + 90, SCREENSIZE + 10);
			btnPerfil.setBackColor(0x202b4b);
			btnPerfil.setForeColor(Color.WHITE);
			
			btnSobre = BottomImg.imageWithText(new Image("img/sobre.png"), "Sobre", Button.BOTTOM);
			add(btnSobre, CENTER, AFTER + 5, SCREENSIZE + 90, SCREENSIZE + 10);
			btnSobre.setBackColor(0x202b4b);
			btnSobre.setForeColor(Color.WHITE);

			btnVoltar = BottomImg.imageWithText(new Image("img/voltar.png"), "Voltar", Button.BOTTOM);
			add(btnVoltar, RIGHT - 2, BOTTOM, SCREENSIZE + 18, SCREENSIZE + 15);
			btnVoltar.setBackColor(0x1c355d);
			btnVoltar.setForeColor(Color.WHITE);
			
			reposition();
			
		} catch (Exception e) {
			Auxiliares.messagebox("ERRO", "Erro ao construir tela menu\n" + e);

		}

	}

	public void onEvent(Event evt) {
		try {
			switch (evt.type) {
			case ControlEvent.PRESSED:

				if (evt.target == btnCadastrar) {
					Cadastrar cadastrar = new Cadastrar();
					cadastrar.popup();

				} else if (evt.target == btnApagar) {
					Apagar apagar = new Apagar();
					apagar.popup();

				} else if (evt.target == btnSuporte) {
					Auxiliares.messagebox("SOLUCAO",
							"Esse módulo encontra-se indisponível no mometo.\nPara habilitá-lo entre em contato:\nWhatsApp (62) 9259-7360\nE-mail: alcateiaservicosonline@gmail.com");

				} else if (evt.target == btnPerfil) {
					Perfil perfil = new Perfil();
					perfil.popup();

				} else if (evt.target == btnSobre) {
					Sobre sobre = new Sobre();
					sobre.popup();

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
