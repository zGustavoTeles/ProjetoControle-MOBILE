package com.configuracoes;


import com.auxiliares.Auxiliares;
import com.litebase.LitebasePack;
import litebase.ResultSet;
import nx.componentes.ArtButton;
import totalcross.sys.Convert;
import totalcross.ui.Edit;
import totalcross.ui.ImageControl;
import totalcross.ui.Label;
import totalcross.ui.event.ControlEvent;
import totalcross.ui.event.Event;
import totalcross.ui.gfx.Color;
import totalcross.ui.image.Image;

public class InfoEmpresa extends totalcross.ui.Window {

	public Label                lblInformacao;
	public Label 				lblEmpresa;
	public Label 				lblCnpj;
	public Label				lblUsuario;
	public Label				lblCodigo;
	public Edit 				editCodigo;
	public Edit					editUsuario;
	public Edit 				editEmpresa;
	public Edit 				editCnpj;
	public ImageControl			imgInfo;
	public ArtButton 			btnVoltar;

	public InfoEmpresa() {
		setBackColor(0x003366);
		initUI();
	}

	public void initUI() {

		try {
			
			lblInformacao = new Label("ATUALMENTE A EMPRESA CADASTRADA É: ");
			add(lblInformacao);
			lblInformacao.setRect(CENTER, TOP + 2, PREFERRED, PREFERRED);
			lblInformacao.setBackColor(0x003366);
			lblInformacao.setForeColor(Color.WHITE);
			
			imgInfo = new ImageControl(new Image("img/info.png"));
			imgInfo.scaleToFit = true;
			imgInfo.centerImage = true;
			add(imgInfo,  CENTER, AFTER + 30, PREFERRED - 70, PREFERRED - 70, lblInformacao);
			
			lblEmpresa = new Label("EMPRESA: ");
			add(lblEmpresa);
			lblEmpresa.setBackColor(0x003366);
			lblEmpresa.setForeColor(Color.WHITE);
			lblEmpresa.setRect(LEFT, AFTER + 30, PREFERRED, PREFERRED, imgInfo);

			add(editEmpresa = new Edit(), LEFT, AFTER + 10, PREFERRED, PREFERRED, lblEmpresa);
			editEmpresa.setBackColor(Color.WHITE);
			editEmpresa.setForeColor(0x003366);
			editEmpresa.setEditable(false);

			lblCnpj = new Label("CNPJ: ");
			add(lblCnpj);
			lblCnpj.setBackColor(0x003366);
			lblCnpj.setForeColor(Color.WHITE);
			lblCnpj.setRect(LEFT, AFTER + 10, PREFERRED, PREFERRED, editEmpresa);

			add(editCnpj = new Edit(), LEFT, AFTER + 10, PREFERRED, PREFERRED, lblCnpj);
			editCnpj.setBackColor(Color.WHITE);
			editCnpj.setForeColor(0x003366);
			editCnpj.setEditable(false);
			
			lblUsuario = new Label("USUÁRIO: ");
			add(lblUsuario);
			lblUsuario.setBackColor(0x003366);
			lblUsuario.setForeColor(Color.WHITE);
			lblUsuario.setRect(LEFT, AFTER + 10, PREFERRED, PREFERRED, editCnpj);
			
			add(editUsuario = new Edit(), LEFT, AFTER + 10, PREFERRED, PREFERRED);
			editUsuario.setBackColor(Color.WHITE);
			editUsuario.setForeColor(0x003366);
			editUsuario.setEditable(false);
			
			lblCodigo = new Label("CÓDIGO: ");
			add(lblCodigo);
			lblCodigo.setBackColor(0x003366);
			lblCodigo.setForeColor(Color.WHITE);
			lblCodigo.setRect(LEFT, AFTER + 10, PREFERRED, PREFERRED, editUsuario);
			
			add(editCodigo = new Edit(), LEFT, AFTER + 10, PREFERRED, PREFERRED);
			editCodigo.setBackColor(Color.WHITE);
			editCodigo.setForeColor(0x003366);
			editCodigo.setEditable(false);

			btnVoltar = new ArtButton("VOLTAR");
			add(btnVoltar);
			btnVoltar.setRect(RIGHT, BOTTOM, SCREENSIZE - 4, PREFERRED + 15);
			btnVoltar.setBackColor(0x003366);
			btnVoltar.setForeColor(Color.WHITE);
			
			reposition();
			
			consultarEmpresa();

		} catch (Exception e) {
			Auxiliares.artMsgbox("ERRO","Erro ao construir a tela infoEmpresa\n" + e);

		}

	}

	public void onEvent(Event evt) {

		try {
			switch (evt.type) {
			case ControlEvent.PRESSED:

				if (evt.target == btnVoltar) {
					unpop();

				}

			}

		} catch (Exception e) {
			Auxiliares.artMsgbox("ERRO", "Erro na validação da tela infoEmpresa\n" + e);
		}

	}
	
	public void consultarEmpresa() {
		String sql = "";
		LitebasePack lb = null;
		ResultSet rs = null;
		
		try {
			
			try {

				lb = new LitebasePack();

				sql = " SELECT * FROM EMPRESA ";

				rs = lb.executeQuery(sql);
				
				editEmpresa.setText(rs.getString("NOME"));
				editCnpj.setText(rs.getString("CNPJ"));
				editUsuario.setText(rs.getString("USUARIO"));
				editCodigo.setText(Convert.toString(rs.getInt("CODIGO")));

			} finally {
				if(lb != null) {
					lb.closeAll();	
				}
			}
			
			
		} catch (Exception e) {
			Auxiliares.artMsgbox("ERRO", "Erro consultarEmpresa\n" + e);
		}
	}

}
