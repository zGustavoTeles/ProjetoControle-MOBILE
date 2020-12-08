package principal;

import com.auxiliares.Auxiliares;
import com.bottom.BottomImg;
import com.cadastrarUsuario.CadastrarUsuario;
import com.litebase.LitebasePack;
import com.menu.Menu;
import com.tabelas.Tabelas;
import litebase.ResultSet;
import totalcross.sys.Convert;
import totalcross.sys.Settings;
import totalcross.ui.Button;
import totalcross.ui.Container;
import totalcross.ui.Edit;
import totalcross.ui.ImageControl;
import totalcross.ui.Label;
import totalcross.ui.MainWindow;
import totalcross.ui.Radio;
import totalcross.ui.RadioGroupController;
import totalcross.ui.event.ControlEvent;
import totalcross.ui.event.Event;
import totalcross.ui.gfx.Color;
import totalcross.ui.image.Image;

public class Solucao extends MainWindow{
	
		public static Edit				editEmpresa;
		public static Edit				editCnpj;
		public static Edit              editUsuario;
		public static Edit				editCodigo;
	
		public static  String			email = "";
		public static  String   		senha = "";
		public static  String   		salvarSenha = "";
			
		public static Edit 				editEmail;
		public static Edit 				editSenha;

		public Button 					btnEntrar;
		public Button 					btnCadastrar;
		public Button 					btnSair;

		public ImageControl 			imgAlcateia;

		public Label 					lblSalvarSenha;
		public Radio 			   	    radioSim, radioNao;
		public RadioGroupController		radioGrupo;
		
//		public ArtButton       		    btnAdm;
		public ImageControl				imgHome;

		
		
		public static Container[] screens = new Container[1];
		
		
	public Solucao() {
		
		super("Solucao", TAB_ONLY_BORDER);
		
		setBackColor(0x1c355d);
		Tabelas tabelas = new Tabelas();
		tabelas.criaTabelas();
		
		Settings.applicationId   = "SLTC";
		Settings.appDescription  = "Solucao";
		Settings.appVersion 	 = Auxiliares.VERSAO;
		Settings.useNewFont 	 = Settings.fingerTouch = true;		
		Settings.dateFormat 	 = Settings.DATE_DMY;
		
//		Settings.resizableWindow = true;
		
	}
		
		public void initUI(){
			
		try {
			
			radioGrupo = new RadioGroupController();
			
			imgAlcateia = new ImageControl(new Image("img/alcateia.png"));
			imgAlcateia.scaleToFit = true;
			imgAlcateia.centerImage = true;
			add(imgAlcateia, CENTER, TOP + 5, SCREENSIZE + 30, SCREENSIZE + 30);

			editEmail = new Edit();
			add(editEmail);
			editEmail.setRect(LEFT + 20, AFTER - 20, SCREENSIZE + 90, SCREENSIZE + 4);
			editEmail.setBackColor(Color.WHITE);
			editEmail.setForeColor(0x1c355d);
			editEmail.setText("E-mail");

			editSenha = new Edit();
			add(editSenha);
			editSenha.setRect(LEFT + 20, AFTER + 10, SCREENSIZE + 90, SCREENSIZE + 4, editEmail);
			editSenha.setBackColor(Color.WHITE);
			editSenha.setForeColor(0x1c355d);
			editSenha.setMode(Edit.PASSWORD_ALL);
			editSenha.setText("Senha");

			lblSalvarSenha = new Label("Salvar senha? ");
			add(lblSalvarSenha);
			lblSalvarSenha.setRect(LEFT + 60, AFTER + 50, PREFERRED, PREFERRED, editSenha);
			lblSalvarSenha.setForeColor(Color.WHITE);
			
			radioSim = new Radio("Sim",radioGrupo);
		    add(radioSim);
			radioSim.setRect(AFTER + 10, SAME, PREFERRED, PREFERRED, lblSalvarSenha);
			radioSim.setForeColor(Color.WHITE);
			radioSim.setFont(Auxiliares.getFontGridBold());

			radioNao = new Radio("Não",radioGrupo);
			add(radioNao);
			radioNao.setRect(AFTER + 30, SAME, PREFERRED, PREFERRED, radioSim);	
			radioNao.setForeColor(Color.WHITE);
			radioNao.setFont(Auxiliares.getFontGridBold());

			btnEntrar = BottomImg.imageWithText(new Image("img/entrar.png"), "Entrar", Button.BOTTOM);
			add(btnEntrar, LEFT + 2, BOTTOM, SCREENSIZE + 25, PREFERRED + 10);
			btnEntrar.setBackColor(0x1c355d);
			btnEntrar.setForeColor(Color.WHITE);

			btnCadastrar = BottomImg.imageWithText(new Image("img/cadastrar.png"), "Cadastrar", Button.BOTTOM);
			add(btnCadastrar, AFTER + 3, SAME, SCREENSIZE + 25, PREFERRED + 10, btnEntrar);
			btnCadastrar.setBackColor(0x1c355d);
			btnCadastrar.setForeColor(Color.WHITE);

			btnSair = BottomImg.imageWithText(new Image("img/voltar.png"), "Sair", Button.BOTTOM);
			add(btnSair, RIGHT - 2, BOTTOM, SCREENSIZE + 25, PREFERRED + 10);
			btnSair.setBackColor(0x1c355d);
			btnSair.setForeColor(Color.WHITE);
			
			reposition();
			
			buscaUsuarioCadastrado();
			
		} catch (Exception e) {
			Auxiliares.messagebox("ERRO", "Erro ao construir a tela principal\n" + e);
		}	
		
	}
		
	public void onEvent(Event evt) {
		try {
			switch (evt.type) {
			case ControlEvent.PRESSED:

				if (evt.target == btnEntrar) {

					if (editEmail.getText().equals("E-mail") || editEmail.getText().equals("")
							|| editSenha.getText().equals("Senha") || editSenha.getText().equals("")) {

						Auxiliares.messagebox("SOLUCAO", "Usuário(a) incorreto!");
					} else {

						if (validaUsuarioCadastrado()) {
							Menu home = new Menu();
							home.popup();

						} else {
							Auxiliares.messagebox("SOLUCAO",
									"Usuário(a) incorreto!");

						}
					}
				} else if (evt.target == btnCadastrar) {
					CadastrarUsuario cadastrarUsuario = new CadastrarUsuario();
					cadastrarUsuario.popup();

				} else if (evt.target == btnSair) {

					String[] ArtButtonArray = { "Sim", "Não" };

					int i = Auxiliares.messageBox("SOLUCAO", "Deseja sair do sistema?", ArtButtonArray);

					if (i == 1) {
						return;

					} else {
						Solucao.exit(0);

					}
				}
				
			  case ControlEvent.FOCUS_IN:
				
				if (evt.target == editEmail){

					if(editEmail.getText().equals("E-mail")){
						editEmail.setText("");

					}


				} else if(evt.target == editSenha){

					if(editSenha.getText().equals("Senha")){
						editSenha.setText("");

					}
				} else if(evt.target == radioSim) {
					atualizaTabelaUsuario("S");
				} else if(evt.target == radioNao) {
					atualizaTabelaUsuario("N");
				}
			}

		} catch (Exception e) {
			Auxiliares.messagebox("ERRO", "Erro na validação da tela principal\n" + e);
		}

	}
	
	public static void buscaUsuarioCadastrado(){

		String sql 				 = "";
		LitebasePack lb 	     = null;
		ResultSet rs			 = null;

		try {

			try {
				lb = new LitebasePack();
				sql = "SELECT * FROM USUARIO ";

				rs = lb.executeQuery(sql);

				if (rs.getRowCount() > 0) {

					if(rs.getString("SALVAR_SENHA").equals("S")){
						Auxiliares.NOMEUSUARIO = rs.getString("NOME");
						editEmail.setText(rs.getString("Email"));
						editSenha.setText(rs.getString("senha"));

					}else{
						Auxiliares.NOMEUSUARIO = rs.getString("NOME");
						email = rs.getString("Email");
						senha = rs.getString("senha");
					}
				}else{
					Auxiliares.messagebox("SOLUCAO", "Não existe usuário(a) cadastrado!");
					editEmail.setText("E-mail");
					return;
				}

			} finally {

				if (lb != null)
				lb.closeAll();

				if(rs != null)
				rs.close();
			}

		} catch (Exception e) {
			Auxiliares.messagebox("Erro", "Erro buscaUsuarioCadastrado" + e);
			return;
		}
	}

		public boolean validaUsuarioCadastrado(){

			String sql 				     = "";
			LitebasePack lb 		     = null;
			ResultSet rs			     = null;
	
			try {
	
				try {
					lb = new LitebasePack();
					sql = "SELECT * FROM USUARIO ";
	
					rs = lb.executeQuery(sql);
	
					if (rs.getRowCount() > 0) {
						
						if(editEmail.getText().equals(rs.getString("EMAIL")) && 
						editSenha.getText().equals(rs.getString("SENHA"))){
						 return true;
 
					 }else{

						 return false;
					 }

					}else{
						return false;
					}
					
				

				} finally {
	
					if (lb != null)
					lb.closeAll();
	
					if(rs != null)
					rs.close();
				}
	
			} catch (Exception e) {
				Auxiliares.messagebox("Erro", "Erro validaUsuarioCadastrado");
				return false;
			}
		}
		
		public void atualizaTabelaUsuario(String salvarSenha){

			String sql 					 = "";
			LitebasePack lb 	     = null;
	
			try {
	
				try {
					lb = new LitebasePack();
					sql = " UPDATE USUARIO "
					    + " SET SALVAR_SENHA = '" + salvarSenha + "'";
	
					lb.executeUpdate(sql);

				} finally {
	
					if (lb != null)
					lb.closeAll();
				}
	
			} catch (Exception e) {
				Auxiliares.messagebox("Erro", "Erro validaUsuarioCadastrado");
			}
		}
		
		public static Container swapTo(String[] screen) {
			try {
				int position = Convert.toInt(screen[2]);

				if (screens[position] == null) {
					try {
						screens[position] = (Container) Class.forName(screen[0])
								.newInstance();
					} catch (Exception e) {
						screens[position] = (Container) Class.forName(screen[0])
								.newInstance();
						throw new RuntimeException("Classe " + screen[0]
								+ " não pode ser inicializada! \n" + e.getMessage());
					}
				}

				MainWindow.getMainWindow().swap(screens[position]);

				return screens[position];

			} catch (Exception e) {
				e.printStackTrace();
				throw new IllegalArgumentException("swapTo()|" + e.getMessage());

			}
		}

	/*
	public void buscaEmpresaCadastrada(){
		String sql = "";
		LitebasePack lb = null;
		ResultSet rs = null;

		try {

			try {
				lb = new LitebasePack();
				sql = "SELECT * FROM EMPRESA ";

				rs = lb.executeQuery(sql);
				rs.first();

				if (rs.getRowCount() == 0) {
					Auxiliares.artMsgbox("CONTROLE", "Sistema não possui empresa cadastrada!");
					return;
				}

				editEmpresa.setText(rs.getString("NOME"));
				editCnpj.setText(rs.getString("CNPJ"));
				editUsuario.setText(rs.getString("USUARIO"));
				editCodigo.setText(Convert.toString(rs.getInt("CODIGO")));

			} finally {
				if (lb != null)
					lb.closeAll();
			}

		} catch (Exception e) {
			Auxiliares.artMsgbox("ERRO", "Erro ao buscar empresa\n" + e);
			return;
		}
	}
	
	public boolean validaEmpresa(boolean empresaCadastrada) {
		String sql = "";
		LitebasePack lb = null;
		ResultSet rs = null;

		try {

			try {
				lb = new LitebasePack();
				
				if (!lb.exists("EMPRESA")) {

					sql = " create table empresa ( " + "codigo int," + " nome char(40), " + " cnpj char(30), "
						+ " usuario char(40) " + ")";

					lb.execute(sql);
					lb.execute("create index empresa01 ON empresa(codigo,nome)");
				}
				
				sql = "SELECT * FROM EMPRESA "
				    + " WHERE CODIGO = " + editCodigo.getText();

				rs = lb.executeQuery(sql);
				rs.first();

				if (rs.getRowCount() == 0) {
					Auxiliares.artMsgbox("CONTROLE", "Sistema não possui empresa cadastrada!");
					editEmpresa.setText("");
					editCnpj.setText("");
					editUsuario.setText("");
					editCodigo.setText("");
					return empresaCadastrada = false;
				}
				
				return empresaCadastrada = true;
				
			} finally {
				if (lb != null)
					lb.closeAll();
			}

		} catch (Exception e) {
			Auxiliares.artMsgbox("ERRO", "Erro ao buscar empresa\n" + e);
			return empresaCadastrada;
		}
		
	}
	*/
	
//------------> TITLE APP <-------------	
//	super("ARTVENDAS_IN", TAB_ONLY_BORDER);
//	 //super("ARTVENDAS_IN_II", TAB_ONLY_BORDER);
//	LitebasePack lb = null;
//	try {
//		lb = new LitebasePack();
//		lb.CheckAllTables();
//	} catch (Exception e) {
//		Auxiliares.artMsgbox("ERRO" + e.getMessage());
//	} finally {
//		if (lb != null)
//			lb.closeAll();
//	}
//
//	setBackColor(Color.WHITE);
//	UIColors.controlsBack = Color.WHITE;
//
//	// Instantiate the screens
//
//	UIColors.controlsBack = Color.WHITE;
//	Settings.uiAdjustmentsBasedOnFontHeight = true;
			
}
