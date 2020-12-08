package com.usuarios;

import com.auxiliares.Auxiliares;
import com.bottom.BottomImg;
import com.cellcontroller6.CellController6;
import com.litebase.LitebasePack;

import litebase.ResultSet;
import principal.Solucao;
import totalcross.sys.Convert;
import totalcross.ui.Button;
import totalcross.ui.Grid;
import totalcross.ui.event.ControlEvent;
import totalcross.ui.event.Event;
import totalcross.ui.event.GridEvent;
import totalcross.ui.gfx.Color;
import totalcross.ui.image.Image;

public class Usuarios extends totalcross.ui.Window{
	
    private Button							btnVoltar;
    private Button							btnApagarUsuario;
    public Grid							    gridUsuarios;
    
    public String codigoUsuario				= "";
    
	public Usuarios(){
		 setBackColor(0x1c355d);
		 initUI();
		 
	}
	
	public void initUI() {
		
		try {
			
			btnApagarUsuario = BottomImg.imageWithText(new Image("img/apagarUsuario.png"), "Apagar", Button.BOTTOM);
			add(btnApagarUsuario, LEFT, BOTTOM, SCREENSIZE + 25, PREFERRED + 10);
			btnApagarUsuario.setBackColor(0x1c355d);
			btnApagarUsuario.setForeColor(Color.WHITE);
			
			btnVoltar = BottomImg.imageWithText(new Image("img/voltar.png"), "Voltar", Button.BOTTOM);
			add(btnVoltar, RIGHT - 2, BOTTOM, SCREENSIZE + 25, PREFERRED + 10);
			btnVoltar.setBackColor(0x1c355d);
			btnVoltar.setForeColor(Color.WHITE);

			int gridWidths[] = new int[3];
			gridWidths[0] = 10;
			gridWidths[1] = 300;
			gridWidths[2] = 300;

			String[] caps = { "CODIGO", "NOME", "EMAIL"};
			int[] aligns = { Grid.LEFT, Grid.CENTER, Grid.LEFT};
			gridUsuarios = new Grid(caps, gridWidths, aligns, false);
			add(gridUsuarios);
			gridUsuarios.enableColumnResize = true;
			gridUsuarios.secondStripeColor = Color.getRGB(255, 255, 255);
			gridUsuarios.setBackColor(Auxiliares.backColorGridNovo);
			gridUsuarios.setForeColor(Auxiliares.defaultForeColorGridNovo);
			gridUsuarios.transparentBackground = false;
			gridUsuarios.setBorderStyle(totalcross.ui.Container.BORDER_NONE);
			gridUsuarios.verticalLineStyle = totalcross.ui.Grid.VERT_LINE;
			gridUsuarios.drawCheckBox = true;
			gridUsuarios.disableSort = false;
			gridUsuarios.boldCheck = false;
			gridUsuarios.captionsBackColor = Auxiliares.captionsBackColorNovo;
			gridUsuarios.checkColor = Auxiliares.checkColorNovo;
			gridUsuarios.firstStripeColor = Auxiliares.firstStripeColorNovo;
			gridUsuarios.enableColumnResize = false;
			gridUsuarios.setFont(Auxiliares.getFontNormal().asBold());
			gridUsuarios.secondStripeColor = Auxiliares.secondStripeColorNovo;
			gridUsuarios.setCellController(new CellController6());
			gridUsuarios.setRect(Grid.CENTER + 1, TOP + 100, SCREENSIZE + 100, SCREENSIZE + 50, btnVoltar);
			
			carregaGridUsuarios();
			
			reposition();


		} catch (Exception e) {

			Auxiliares.messagebox("ERRO", "Erro na construção da tela Usuarios" + e);
		}
		
	}
	
	
	public void onEvent(Event evt) {
		try {
			switch (evt.type) {
			case ControlEvent.PRESSED:

				if (evt.target == btnVoltar) {
					unpop();

				} else if (evt.target == btnApagarUsuario) {
					if (gridUsuarios.getSelectedItem() == null) {
						Auxiliares.messagebox("SOLUCAO", "Por favor selecione um usuário(a)!");
						return;
					} else {

						String[] ArtButtonArray = { "Sim", "Não" };

						int i = Auxiliares.messageBox("SOLUCAO", "Deseja apagar esse usuário?", ArtButtonArray);

						if (i == 1) {
							return;

						} else {
							apagaUsuarioSistema();
							Auxiliares.messagebox("SOLUCAO", "Usuário(a) apagado com sucesso!");
							unpop();

						}

					}
				}

				break;
			case GridEvent.SELECTED_EVENT:

				if (evt.target == gridUsuarios) {

					try {

						codigoUsuario = gridUsuarios.getSelectedItem()[0];

					} catch (Exception e) {
						Auxiliares.messagebox("SOLUCAO", "Por favor selecione um usuário(a)!");
					}

				}
			}

		} catch (Exception e) {
		 Auxiliares.messagebox("ERRO", "Erro nos evetenos da tela Usuarios" + e);
		}

	}
	
public void  carregaGridUsuarios() throws Exception {
		
		String sql		   	 = "";
		LitebasePack lb   	 = null;
		ResultSet rs	  	 = null;

		try {
			
			try {
				gridUsuarios.removeAllElements();
				
				lb = new LitebasePack();
				sql = " SELECT * FROM USUARIO ";

				rs = lb.executeQuery(sql);
				rs.first();
				for (int i = 0; rs.getRowCount() > i; i++) {
					String[] b = new String[9];
					b[0] = Convert.toString(rs.getInt("CODIGO"));
					b[1] = rs.getString("NOME");
					b[2] = rs.getString("EMAIL");
					gridUsuarios.add(b);
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

public void  apagaUsuarioSistema() throws Exception {
	
	String sql		   	 = "";
	LitebasePack lb   	 = null;

	try {
		
		try {
			
			lb = new LitebasePack();
			sql = " DELETE FROM USUARIO "
				+ " WHERE CODIGO = " + codigoUsuario;

			lb.executeUpdate(sql);
			
			
		} finally {
			if (lb != null) {
				lb.closeAll();
			}
		}

	} catch (Exception e) {
		Auxiliares.messagebox("ERRO", "Erro ao apagaUsuarioSistema\n" + e);

	}

}
	
}