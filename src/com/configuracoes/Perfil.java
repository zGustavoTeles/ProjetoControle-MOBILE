package com.configuracoes;

import com.adm.Administrador;
import com.adm.ValidaAdministrador;
import com.auxiliares.Auxiliares;
import com.email.Email;
import com.informacao.Informacao;
import com.litebase.LitebasePack;

import litebase.ResultSet;
import totalcross.ui.dialog.MessageBox;
import totalcross.ui.event.ControlEvent;
import totalcross.ui.event.Event;
import totalcross.ui.gfx.Color;
import totalcross.ui.image.Image;
import totalcross.util.Random;

import com.bottom.BottomImg;

import totalcross.sys.Convert;
import totalcross.ui.Button;
import totalcross.ui.Edit;
import totalcross.ui.Grid;
import totalcross.ui.ImageControl;
import totalcross.ui.Label;

public class Perfil extends totalcross.ui.Window {

	private Button							btnVoltar;
	private Button							btnCadastrar;
	private Button							btnAtualizarCadastro;
    public Grid							    gridProdutos;
	
	public Label 						    lblEmail;
	public Label 						    lblSenha;
	public Label 						    lblCodigo;
	public Label 						    lblNomeCompleto;
	public Label 						    lblEndereco;
	public Label 						    lblQuadra;
	public Label 						    lblLote;
	public Label 						    lblNumero;
	public Label 						    lblTelefone;

	public static Edit 			            editEmail;
	public static Edit 			            editSenha;
	public static Edit 			            editCodigoUsuario;
	public static Edit 			            editNomeCompleto;
	public static Edit 			            editEndereco;
	public static Edit 			            editQuadra;
	public static Edit 			            editLote;
	public static Edit 			            editNumero;
	public static Edit 			            editTelefone;
	
	public ImageControl 		            imgPerfil;

	public Perfil() {
		setBackColor(0x1c355d);
		initUI();
	}

	public void initUI() {

		try {
			
			imgPerfil = new ImageControl(new Image("img/perfilCapa.png"));
			imgPerfil.scaleToFit = true;
			imgPerfil.centerImage = true;
            add(imgPerfil, CENTER, TOP - 40, SCREENSIZE + 30, SCREENSIZE + 30);
			
			lblNomeCompleto = new Label("NOME COMPLETO: ");
			add(lblNomeCompleto);
			lblNomeCompleto.setRect(LEFT + 5, AFTER - 70, PREFERRED, PREFERRED, imgPerfil);
			lblNomeCompleto.setForeColor(Color.WHITE);

            editNomeCompleto = new Edit();
			add(editNomeCompleto);
			editNomeCompleto.setRect(LEFT + 5, AFTER + 2, SCREENSIZE + 100, SCREENSIZE + 4, lblEmail);
			editNomeCompleto.setBackColor(Color.WHITE);
			editNomeCompleto.setForeColor(0x003366);
			editNomeCompleto.setEnabled(false);

			lblEmail = new Label("E-MAIL: ");
			add(lblEmail);
			lblEmail.setRect(LEFT + 5, AFTER + 5, PREFERRED, PREFERRED, editNomeCompleto);
			lblEmail.setForeColor(Color.WHITE);

            editEmail = new Edit();
			add(editEmail);
			editEmail.setRect(LEFT + 5, AFTER + 2, SCREENSIZE + 100, SCREENSIZE + 4, lblEmail);
			editEmail.setBackColor(Color.WHITE);
			editEmail.setForeColor(0x003366);
			editEmail.setEnabled(false);

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
			editSenha.setEnabled(false);

			lblCodigo = new Label("CÓDIGO USUÁRIO: ");
			add(lblCodigo);
			lblCodigo.setRect(LEFT + 5, AFTER + 5, PREFERRED, PREFERRED, editSenha);
			lblCodigo.setForeColor(Color.WHITE);

            editCodigoUsuario = new Edit();
			add(editCodigoUsuario);
			editCodigoUsuario.setRect(LEFT + 5, AFTER + 2, SCREENSIZE + 100, SCREENSIZE + 4, lblCodigo);
			editCodigoUsuario.setBackColor(Color.WHITE);
			editCodigoUsuario.setForeColor(0x003366);
			editCodigoUsuario.setEnabled(false);

			lblTelefone = new Label("TELEFONE: ");
			add(lblTelefone);
			lblTelefone.setRect(LEFT + 5, AFTER + 5, PREFERRED, PREFERRED, editCodigoUsuario);
			lblTelefone.setForeColor(Color.WHITE);

			editTelefone = new Edit();
			add(editTelefone);
			editTelefone.setRect(LEFT + 5, AFTER + 2, SCREENSIZE + 100, SCREENSIZE + 4, lblTelefone);
			editTelefone.setBackColor(Color.WHITE);
			editTelefone.setForeColor(0x003366);
			editTelefone.setValidChars("0 1 2 3 4 5 6 7 8 9");
			editTelefone.setEnabled(false);

            lblEndereco = new Label("ENDEREÇO: ");
			add(lblEndereco);
			lblEndereco.setRect(LEFT + 5, AFTER + 5, PREFERRED, PREFERRED, editTelefone);
			lblEndereco.setForeColor(Color.WHITE);

            editEndereco = new Edit();
			add(editEndereco);
			editEndereco.setRect(LEFT + 5, AFTER + 5, SCREENSIZE + 100, SCREENSIZE + 4, lblEndereco);
			editEndereco.setBackColor(Color.WHITE);
			editEndereco.setForeColor(0x003366);
			editEndereco.setEnabled(false);

			btnAtualizarCadastro = BottomImg.imageWithText(new Image("img/atualizar.png"), "Atualizar", Button.BOTTOM);
			add(btnAtualizarCadastro, LEFT + 2, BOTTOM, SCREENSIZE + 25, PREFERRED + 10);
			btnAtualizarCadastro.setBackColor(0x1c355d);
			btnAtualizarCadastro.setForeColor(Color.WHITE);

			btnCadastrar = BottomImg.imageWithText(new Image("img/cadastrar.png"), "Cadastrar", Button.BOTTOM);
			add(btnCadastrar, AFTER + 2, SAME, SCREENSIZE + 25, PREFERRED + 10);
			btnCadastrar.setBackColor(0x1c355d);
			btnCadastrar.setForeColor(Color.WHITE);
			btnCadastrar.setVisible(false);

			btnVoltar = BottomImg.imageWithText(new Image("img/voltar.png"), "Voltar", Button.BOTTOM);
			add(btnVoltar, RIGHT - 2, BOTTOM, SCREENSIZE + 25, PREFERRED + 10);
			btnVoltar.setBackColor(0x1c355d);
			btnVoltar.setForeColor(Color.WHITE);
			
			buscaUsuarioCadastrado();

			reposition();
			
		} catch (Exception e) {
			Auxiliares.messagebox("ERRO","Erro ao construir a tela configuracoes\n" + e);

		}

	}

	public void onEvent(Event evt) {

		try {
			switch (evt.type) {
			case ControlEvent.PRESSED:

				if (evt.target == btnVoltar) {
					unpop();

				} else if (evt.target == btnAtualizarCadastro) {
					btnCadastrar.setVisible(true);
					editNomeCompleto.setEnabled(true);
					editEmail.setEnabled(true);
					editSenha.setEnabled(true);
					editCodigoUsuario.setEnabled(true);
					editTelefone.setEnabled(true);
					editEndereco.setEnabled(true);

				} else if (evt.target == btnCadastrar) {

					if (editEmail.getText().equals("") || editSenha.getText().equals("")
							|| editCodigoUsuario.getText().equals("") || editTelefone.getText().equals("")
							|| editEndereco.getText().equals("")) {

						Auxiliares.messagebox("Alerta!", "Preencha todos os campos!");

					} else {

						cadastraUsuario();
						Auxiliares.messagebox("Alerta!", "Usuário cadastrado com sucesso!");
						
						editNomeCompleto.setText("");
						editEmail.setText("");
						editSenha.setText("");
						editCodigoUsuario.setText("");
						editTelefone.setText("");
						editEndereco.setText("");

					}
				}

			}

		} catch (Exception e) {
			Auxiliares.messagebox("ERRO", "Erro na validação da tela configuracoes\n" + e);
		}

	}
	
	public void cadastraUsuario() {

		String sql 					= "";
		LitebasePack lb 		= null;

		try {

			try {
				lb = new LitebasePack();
				
				sql = "INSERT INTO USUARIO " + "(" + " CODIGO, NOME, ENDERECO, TELEFONE, EMAIL, "
					+ " SENHA, SALVAR_SENHA " + ")" + " VALUES " + "( '" + editCodigoUsuario.getText() + "' , '" + editNomeCompleto.getText() 
					+ "', '" + editEndereco.getText() + "', '" +  editTelefone.getText() + "', '" + editEmail.getText() + "', '"  
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
	
	public static void buscaUsuarioCadastrado() {

		String sql 				= "";
		LitebasePack lb 		= null;
		ResultSet rs 			= null;

		try {

			try {
				lb = new LitebasePack();
				sql = "SELECT * FROM USUARIO ";

				rs = lb.executeQuery(sql);

				if (rs.getRowCount() > 0) {
					
					editNomeCompleto.setText(rs.getString("NOME"));
					editEmail.setText(rs.getString("EMAIL"));
					editSenha.setText(rs.getString("SENHA"));
					editCodigoUsuario.setText(Convert.toString(rs.getInt("CODIGO")));
					editTelefone.setText(rs.getString("TELEFONE"));
					editEndereco.setText(rs.getString("ENDERECO"));
					
					
				} else {
					Auxiliares.messagebox("Alerta!", "Sistema não possui usuário cadastrado!");
					return;
				}

			} finally {

				if (lb != null)
					lb.closeAll();

				if (rs != null)
					rs.close();
			}

		} catch (Exception e) {
			Auxiliares.messagebox("Erro", "Erro buscaUsuarioCadastrado" + e);
			return;
		}
	}

}
