package com.adm;


import java.util.Random;
import com.auxiliares.Auxiliares;
import com.bottom.BottomImg;
import com.litebase.LitebasePack;
import litebase.ResultSet;
import totalcross.ui.Button;
import principal.Solucao;
import totalcross.sys.Convert;
import totalcross.ui.ComboBox;
import totalcross.ui.Edit;
import totalcross.ui.ImageControl;
import totalcross.ui.Label;
import totalcross.ui.dialog.MessageBox;
import totalcross.ui.event.ControlEvent;
import totalcross.ui.event.Event;
import totalcross.ui.gfx.Color;
import totalcross.ui.image.Image;

public class CadastrarProdutoSistema extends totalcross.ui.Window {

	public Label                lblAviso;
	public Label 				lblProduto;
	public Label 				lblCnpj;
	public Label 				lblUsuario;
	public Label				lblCodigo;
	public Edit					editCodigo;
	public Edit 				editUsuario;
	public Edit 				editSenha;
	public Edit					editProduto;
	public Edit					editCnpj;
	private Label 				lblCategoria;
	private ComboBox			cmbCategoria;
	public Button		    btnCadastrar;
	public Button			btnGerarCodigo;
	public Button 			btnVoltar;
	public ImageControl		    imgCadastrarEmpresa;

	public CadastrarProdutoSistema() {
		setBackColor(0x1c355d);
		initUI();
		carregaCmbCategoria();
	}

	public void initUI() {

		try {
			
			imgCadastrarEmpresa = new ImageControl(new Image("img/cadastrarProduto.png"));
			imgCadastrarEmpresa.scaleToFit = true;
			imgCadastrarEmpresa.centerImage = true;
			add(imgCadastrarEmpresa, CENTER, TOP + 10, SCREENSIZE + 20, SCREENSIZE + 20,lblAviso);
			
			lblProduto = new Label("PRODUTO: ");
			add(lblProduto);
			lblProduto.setBackColor(0x1c355d);
			lblProduto.setForeColor(Color.WHITE);
			lblProduto.setRect(LEFT, AFTER + 30, PREFERRED, PREFERRED, imgCadastrarEmpresa);

			add(editProduto = new Edit(), LEFT, AFTER, PREFERRED, PREFERRED, lblProduto);
			editProduto.setBackColor(Color.WHITE);
			editProduto.capitalise = (Edit.ALL_UPPER);
			editProduto.setForeColor(0x1c355d);

			lblCategoria = new Label("CATEGORIA:");
			add(lblCategoria);
			lblCategoria.setRect(LEFT, AFTER + 40, PREFERRED, PREFERRED, editProduto);
			lblCategoria.setBackColor(0x1c355d);
			lblCategoria.setForeColor(Color.WHITE);
			
			cmbCategoria = new ComboBox();
			add(cmbCategoria);
			cmbCategoria.setRect(LEFT, AFTER, FILL + 5, PREFERRED, lblCategoria);
			
			btnGerarCodigo = new Button("CÓDIGO");
			add(btnGerarCodigo);
			btnGerarCodigo.setRect(LEFT, AFTER + 40, SCREENSIZE - 5, PREFERRED, cmbCategoria);
			btnGerarCodigo.setBackColor(0x1c355d);
			btnGerarCodigo.setForeColor(Color.WHITE);	
			
			add(editCodigo = new Edit(), LEFT, AFTER + 10, PREFERRED, PREFERRED);
			editCodigo.setBackColor(Color.WHITE);
			editCodigo.setForeColor(0x1c355d);
			editCodigo.setEditable(false);
			
			btnCadastrar = BottomImg.imageWithText(new Image("img/cadastrarProdutoSistema.png"), "Cadastrar", Button.BOTTOM);
			add(btnCadastrar, LEFT + 5, BOTTOM, SCREENSIZE - 4, PREFERRED + 15);
			btnCadastrar.setBackColor(0x1c355d);
			btnCadastrar.setForeColor(Color.WHITE);

			btnVoltar = BottomImg.imageWithText(new Image("img/voltar.png"), "Voltar", Button.BOTTOM);
			add(btnVoltar, RIGHT - 2, BOTTOM, SCREENSIZE + 25, SCREENSIZE + 10);
			btnVoltar.setBackColor(0x1c355d);
			btnVoltar.setForeColor(Color.WHITE);

			reposition();
			editProduto.requestFocus();
			
		} catch (Exception e) {
			Auxiliares.messagebox("ERRO", "Erro ao construir o menu CadastrarProdutoSistema\n" + e);

		}

	}

	public void onEvent(Event evt) {

		try {
			switch (evt.type) {
			case ControlEvent.PRESSED:

				if (evt.target == btnVoltar) {
					unpop();

				} else if (evt.target == btnCadastrar) {
					String[] ArtButtonArray = { "Sim", "Não" };

					if (editProduto.getText().equals("") || editCodigo.getText().equals("")
							|| cmbCategoria.getSelectedItem() == "") {
						Auxiliares.messagebox("SOLUCAO", "Preencha todos os campos!");
						return;
					}

					int i = Auxiliares.messageBox("SOLUCAO", "Deseja cadastrar esse produto no sistema?", ArtButtonArray);

					if (i == 1) {
						return;

					} else {
						cadastrarProdutoSistema();
						Auxiliares.messagebox("SOLUCAO", "Produto cadastrado com sucesso!");
						unpop();

					}
				} else if (evt.target == btnGerarCodigo) {
					Random random = new Random();
					int codigo = random.nextInt(900);
					editCodigo.setText(Convert.toString(codigo));

				}

			}

		} catch (Exception e) {
			Auxiliares.messagebox("SOLUCAO", "Erro na validação do menu CadastrarProdutoSistema\n " + e);
		}

	}
	
	public void cadastrarProdutoSistema() {
		String sql = "";
		LitebasePack lb = null;

		try {

			try {
				lb = new LitebasePack();
				sql = " INSERT INTO produto (codigo, produto, categoria)" + " VALUES " + "(" + "'"
						+ editCodigo.getText() + "'," + "'" + editProduto.getText() + "'," + "'"
						+ cmbCategoria.getSelectedItem() + "'" + ")";

				lb.executeUpdate(sql);

			} finally {
				if (lb != null)
					lb.closeAll();
			}

		} catch (Exception e) {
			Auxiliares.messagebox("ERRO", "Erro ao buscar cadastrarProdutoSistema\n" + e);

			return;
		}
	}
	
	public void carregaCmbCategoria() {
		{
			String sql = "";
			LitebasePack lb = null;
			ResultSet rs = null;

			try {
				try {
					cmbCategoria.removeAll();
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
				Auxiliares.messagebox("ERRO", "Erro ao carregaCmbCategoria\n" + e);

			}

		}
	}

}
