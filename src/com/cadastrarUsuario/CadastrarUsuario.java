package com.cadastrarUsuario;

import com.auxiliares.Auxiliares;
import com.bottom.BottomImg;
import com.litebase.LitebasePack;
import com.usuarios.Usuarios;

import principal.Solucao;
import totalcross.ui.Button;
import totalcross.ui.Edit;
import totalcross.ui.Grid;
import totalcross.ui.ImageControl;
import totalcross.ui.Label;
import totalcross.ui.event.ControlEvent;
import totalcross.ui.event.Event;
import totalcross.ui.gfx.Color;
import totalcross.ui.image.Image;
import totalcross.util.Random;

public class CadastrarUsuario extends totalcross.ui.Window{
	
    private Button							btnCadastrar;
    private Button							btnUsuarios;
    private Button							btnVoltar;
    public Grid							    gridProdutos;
	
	public Label 						    lblEmail;
	public Label 						    lblSenha;
	public Label 						    lblConfirmarSenha;
	public Label 						    lblNomeCompleto;
	public Label 						    lblEndereco;
	public Label 						    lblQuadra;
	public Label 						    lblLote;
	public Label 						    lblNumero;
	public Label 						    lblTelefone;

	public static Edit 			            editEmail;
	public static Edit 			            editSenha;
	public static Edit 			            editConfirmarSenha;
	public static Edit 			            editNomeCompleto;
	public static Edit 			            editEndereco;
	public static Edit 			            editQuadra;
	public static Edit 			            editLote;
	public static Edit 			            editNumero;
	public static Edit 			            editTelefone;
	
	public ImageControl 		            imgCadastrarUsuario;

	public String[] 						button = { "OK"};
	
	public CadastrarUsuario(){ 
		 setBackColor(0x1c355d);
		 initUI();
		 
	}
	
	public void initUI() {
		
		try {
			
			imgCadastrarUsuario = new ImageControl(new Image("img/perfilCapa.png"));
			imgCadastrarUsuario.scaleToFit = true;
			imgCadastrarUsuario.centerImage = true;
            add(imgCadastrarUsuario, CENTER, TOP - 40, SCREENSIZE + 30, SCREENSIZE + 30);
			
			lblNomeCompleto = new Label("NOME COMPLETO: ");
			add(lblNomeCompleto);
			lblNomeCompleto.setRect(LEFT + 5, AFTER - 70, PREFERRED, PREFERRED, imgCadastrarUsuario);
			lblNomeCompleto.setForeColor(Color.WHITE);

            editNomeCompleto = new Edit();
			add(editNomeCompleto);
			editNomeCompleto.setRect(LEFT + 5, AFTER + 2, SCREENSIZE + 100, SCREENSIZE + 4, lblEmail);
			editNomeCompleto.setBackColor(Color.WHITE);
			editNomeCompleto.setForeColor(0x003366);

			lblEmail = new Label("E-MAIL: ");
			add(lblEmail);
			lblEmail.setRect(LEFT + 5, AFTER + 5, PREFERRED, PREFERRED, editNomeCompleto);
			lblEmail.setForeColor(Color.WHITE);

            editEmail = new Edit();
			add(editEmail);
			editEmail.setRect(LEFT + 5, AFTER + 2, SCREENSIZE + 100, SCREENSIZE + 4, lblEmail);
			editEmail.setBackColor(Color.WHITE);
			editEmail.setForeColor(0x003366);

			lblSenha = new Label("SENHA: ");
			add(lblSenha);
			lblSenha.setRect(LEFT + 5, AFTER + 5, PREFERRED, PREFERRED, editEmail);
			lblSenha.setForeColor(Color.WHITE);

            editSenha = new Edit();
			add(editSenha);
			editSenha.setRect(LEFT + 5, AFTER + 2, SCREENSIZE + 100, SCREENSIZE + 4, lblSenha);
			editSenha.setBackColor(Color.WHITE);
			editSenha.setForeColor(0x003366);
			editSenha.setMode(Edit.PASSWORD_ALL);

			lblConfirmarSenha = new Label("CONFIRMAR SENHA: ");
			add(lblConfirmarSenha);
			lblConfirmarSenha.setRect(LEFT + 5, AFTER + 5, PREFERRED, PREFERRED, editSenha);
			lblConfirmarSenha.setForeColor(Color.WHITE);

            editConfirmarSenha = new Edit();
			add(editConfirmarSenha);
			editConfirmarSenha.setRect(LEFT + 5, AFTER + 2, SCREENSIZE + 100, SCREENSIZE + 4, lblConfirmarSenha);
			editConfirmarSenha.setBackColor(Color.WHITE);
			editConfirmarSenha.setForeColor(0x003366);
			editConfirmarSenha.setMode(Edit.PASSWORD_ALL);

			lblTelefone = new Label("TELEFONE: ");
			add(lblTelefone);
			lblTelefone.setRect(LEFT + 5, AFTER + 5, PREFERRED, PREFERRED, editConfirmarSenha);
			lblTelefone.setForeColor(Color.WHITE);

			editTelefone = new Edit();
			add(editTelefone);
			editTelefone.setRect(LEFT + 5, AFTER + 2, SCREENSIZE + 100, SCREENSIZE + 4, lblTelefone);
			editTelefone.setBackColor(Color.WHITE);
			editTelefone.setForeColor(0x003366);
			editTelefone.setValidChars("0 1 2 3 4 5 6 7 8 9");

            lblEndereco = new Label("ENDEREÇO: ");
			add(lblEndereco);
			lblEndereco.setRect(LEFT + 5, AFTER + 5, PREFERRED, PREFERRED, editTelefone);
			lblEndereco.setForeColor(Color.WHITE);

            editEndereco = new Edit();
			add(editEndereco);
			editEndereco.setRect(LEFT + 5, AFTER + 5, SCREENSIZE + 100, SCREENSIZE + 4, lblEndereco);
			editEndereco.setBackColor(Color.WHITE);
			editEndereco.setForeColor(0x003366);

			btnCadastrar = BottomImg.imageWithText(new Image("img/cadastrar.png"), "Cadastrar", Button.BOTTOM);
			add(btnCadastrar, LEFT + 2, BOTTOM, SCREENSIZE + 25, PREFERRED + 10);
			btnCadastrar.setBackColor(0x1c355d);
			btnCadastrar.setForeColor(Color.WHITE);
			
			btnUsuarios = BottomImg.imageWithText(new Image("img/perfil.png"), "Usuários", Button.BOTTOM);
			add(btnUsuarios, AFTER + 3, SAME, SCREENSIZE + 25, PREFERRED + 10, btnCadastrar);
			btnUsuarios.setBackColor(0x1c355d);
			btnUsuarios.setForeColor(Color.WHITE);

			btnVoltar = BottomImg.imageWithText(new Image("img/voltar.png"), "Voltar", Button.BOTTOM);
			add(btnVoltar, RIGHT - 2, BOTTOM, SCREENSIZE + 25, PREFERRED + 10);
			btnVoltar.setBackColor(0x1c355d);
            btnVoltar.setForeColor(Color.WHITE);

			reposition();
			editNomeCompleto.requestFocus();


		} catch (Exception e) {

			Auxiliares.messagebox("Erro", "construir a tela CadastrarUsuario");
		}
		
	}
	
	
	public void onEvent(Event evt) {
		try {
			switch (evt.type) {
			case ControlEvent.PRESSED:

				if (evt.target == btnCadastrar) {

					if (editEmail.getText().equals("") || editSenha.getText().equals("")
							|| editConfirmarSenha.getText().equals("") || editTelefone.getText().equals("")
							|| editEndereco.getText().equals("")) {

						Auxiliares.messagebox("SOLUCAO", "Preencha todos os campos!");
					} else {

						cadastraUsuario();
						Auxiliares.messagebox("SOLUCAO", "Usuário(a) cadastrado com sucesso!");

						editNomeCompleto.setText("");
						editEmail.setText("");
						editSenha.setText("");
						editConfirmarSenha.setText("");
						editTelefone.setText("");
						editEndereco.setText("");
						unpop();

					}

				} else if (evt.target == btnVoltar) {
					unpop();

				} else if (evt.target == btnUsuarios) {
					Usuarios usuarios = new Usuarios();
					usuarios.popup();
				}

			}
		} catch (Exception e) {
			/*
			Auxiliares.artMsgbox("ERRO", "Erro na validação da tela carrinho\n" + e);
			*/
		}

	}
	
	public void cadastraUsuario() {

		String sql 					= "";
		LitebasePack lb 		= null;

		try {

			try {
				lb = new LitebasePack();

				Random random = new Random();
				int codigo = random.nextInt(900);

				String endereco = editEndereco.getText();
				
				sql = "INSERT INTO USUARIO " + "(" + " CODIGO, NOME, ENDERECO, TELEFONE, EMAIL, "
					+ " SENHA, SALVAR_SENHA " + ")" + " VALUES " + "( '" + codigo + "' , '" + editNomeCompleto.getText() 
					+ "', '" + endereco + "', '" +  editTelefone.getText() + "', '" + editEmail.getText() + "', '"  
					+ editSenha.getText() + "', '" + "N" + "'" + ")";
				
				lb.executeUpdate(sql);


			} finally {
				if (lb != null)
				lb.closeAll();
			}

		} catch (Exception e) {
			Auxiliares.messagebox("ERRO", "Erro cadastraUsuario\n" + e);
		}
	}
}