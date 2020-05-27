package com.email;

import com.auxiliares.Auxiliares;
import nx.componentes.ArtButton;
import totalcross.net.mail.Address;
import totalcross.net.mail.MailSession;
import totalcross.net.mail.Message;
import totalcross.net.mail.Part;
import totalcross.net.mail.Transport;
import totalcross.util.Properties;
import totalcross.net.mail.Message.RecipientType;
import totalcross.sys.Convert;
import totalcross.ui.MultiEdit;
import totalcross.ui.dialog.ProgressBox;
import totalcross.ui.event.ControlEvent;
import totalcross.ui.event.Event;
import totalcross.ui.gfx.Color;
import totalcross.ui.image.Image;
import totalcross.ui.ImageControl;
import totalcross.ui.Label;

public class Email extends totalcross.ui.Window{

	private  ArtButton		    btnEnviarEmail;
	private  ArtButton 			btnVoltar;
	private  MultiEdit       	multInfoEmail;
	private	 Label				lblInfoEmail;
	private  ImageControl		imgSuporte;
	
	
	private static  String SMTP = "smtp.gmail.com";
	private static  String USUARIO = "controle.system.app@gmail.com";
	private static  String SENHA = "controle@2031";
	private static  int PORTA = 465;

	
	public Email() throws Exception {
		
		setBackColor(0x003366);
		initUI();
		SMTP = "smtp.gmail.com";
		USUARIO = "controle.system.app@gmail.com";
		SENHA = "controle@2031";
		PORTA = 465;
	}
	
	public void initUI() {

		try {
			
			lblInfoEmail = new Label("POR FAVOR INSIRA UMA MENSAGEM\nHÁ SER ENVIADA PARA O SUPORTE: ");
			add(lblInfoEmail);
			lblInfoEmail.setBackColor(0x003366);
			lblInfoEmail.setForeColor(Color.WHITE);
			lblInfoEmail.setRect(CENTER + 2, TOP, PREFERRED, PREFERRED);
			
			imgSuporte = new ImageControl(new Image("img/suporte.png"));
			imgSuporte.scaleToFit = true;
			imgSuporte.centerImage = true;
			add(imgSuporte, CENTER, AFTER + 5, PREFERRED - 40, PREFERRED - 40, lblInfoEmail);

			multInfoEmail = new MultiEdit();
			add(multInfoEmail);
			multInfoEmail.setValidChars("1234567890abcdefghijklmnopqrstuvwxyz. ,:{}[]\\/!@#$%&()_-+*|^´`");
			multInfoEmail.setBackColor(Color.WHITE);
			multInfoEmail.setForeColor(0x003366);
			multInfoEmail.setRect(LEFT + 2, AFTER + 10, SCREENSIZE + 100, SCREENSIZE + 50, imgSuporte);

			btnEnviarEmail = new ArtButton("ENVIAR");
			add(btnEnviarEmail);
			btnEnviarEmail.setRect(LEFT, BOTTOM, SCREENSIZE - 4, PREFERRED + 15);
			btnEnviarEmail.setBackColor(0x009933);
			btnEnviarEmail.setForeColor(Color.WHITE);
			
			btnVoltar = new ArtButton("VOLTAR");
			add(btnVoltar);
			btnVoltar.setRect(RIGHT, BOTTOM, SCREENSIZE - 4, PREFERRED + 15);
			btnVoltar.setBackColor(0x003366);
			btnVoltar.setForeColor(Color.WHITE);

			reposition();
			
			multInfoEmail.requestFocus();
			
		} catch (Exception e) {
			Auxiliares.artMsgbox("ERRO","Erro ao construir a tela configuracoes\n" + e);

		}

	}
	
	public void onEvent(Event evt) {

		try {
			switch (evt.type) {
			case ControlEvent.PRESSED:

				if (evt.target == btnVoltar) {
					unpop();

				} else if (evt.target == btnEnviarEmail) {

					if (multInfoEmail.getText().equals("")) {
						Auxiliares.artMsgbox("CONTROLE", "Deve-se inscrever algo na mensagem!");
						return;
					}

					String[] ArtButtonArray = { "Sim", "Não" };

					int i = Auxiliares.artMsgbox("CONTROLE", "Deseja enviar a mensagem para o suporte?",
							ArtButtonArray);

					if (i == 1) {
						return;

					} else {
						enviaEmail();

						Auxiliares.artMsgbox("CONTROLE",
								"Mensagem enviado com sucesso!\nEmbreve entraremos em contato.\nPor favor aguarde...;)");
						unpop();
					}
				}
			}

		} catch (Exception e) {
			Auxiliares.artMsgbox("ERRO", "Erro na validação da tela configuracoes\n" + e);
		}

	}
	

	private void enviaEmail() {

		try {
			
			ProgressBox pb = new ProgressBox("CONTROLE", "Enviando...", null);
			pb.setBackColor(Color.WHITE);
			pb.setForeColor(0x003366);
			pb.popupNonBlocking();

			MailSession session = MailSession.getInstance();
			session.put(MailSession.SMTP_HOST, new Properties.Str(SMTP));
			session.put(MailSession.SMTP_USER, new Properties.Str(USUARIO));
			session.put(MailSession.SMTP_PASS, new Properties.Str(SENHA));
			session.put(MailSession.SMTP_CONNECTIONTIMEOUT, new Properties.Int(60000));
			session.put(MailSession.SMTP_TIMEOUT, new Properties.Int(60000));
			session.put(MailSession.SMTP_STARTTLS, new Properties.Boolean(true));
			session.put(MailSession.SMTP_PORT, new Properties.Int(PORTA));

			String[] email = Convert.tokenizeString("gmateus.dev@gmail.com", ",");
			Message myMessage = new Message();

			Address[] addresses = new Address[email.length];
			for (int i = 0; i < email.length; i++) {
				addresses[i] = new Address(email[i], "");
			}

			myMessage.addFrom(new Address[] { new Address(USUARIO, Auxiliares.NOMESISTEMA) });
			myMessage.addHeader("CONTROLE", "CONTROLE");
			myMessage.subject = "Email";
			myMessage.addRecipients(RecipientType.TO, addresses);

			/**
			 * Cria a mensagem de email com os campos dos atributos do cliente cadastrado.
			 */
			StringBuffer sb = null;
			sb = constroiEmail();

			/**
			 * Seta o conteudo da mensagem enviada.
			 */
			myMessage.setContent(sb.toString(), Part.HTML);

			try {

				Transport.send(myMessage, session);

			} catch (Exception e) {
				Auxiliares.artMsgbox("ERRO", "Email não enviado\n" + e);
			}
			
			pb.unpop();

		} catch (Exception e) {
			Auxiliares.artMsgbox("ERRO", "Email enviaEmail\n" + e);
		}
	}

	
	private StringBuffer constroiEmail() throws Exception {

		StringBuffer sb = new StringBuffer();

		sb.append("<html>");
		sb.append("<head>");
		sb.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"/>");
		sb.append("<title>CONTROLE</title>");
		sb.append("</head>");
		sb.append("<body>");
		sb.append(
				"<table width=\"1200\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" align=\"center\" style=\"font-family:Arial,Helvetica,sans-serif\">");
		sb.append("<tbody>");
		sb.append("<tr>");
		sb.append(
				"<td colspan=\"3\" style=\"border:1px solid #c1c1c1;border-bottom:0px;padding:20px 20px 10px 20px;font-size:12px;color:#333333;line-height:18px\">");

		sb.append(
				"<table style=\"font-size:12px;color:#333333;line-height:25px;margin-top:20px\" align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">");
		sb.append("<tbody>");
		sb.append("<tr>");
		sb.append("<td align=\"right\" valign=\"top\" width=\"10%\"></td>");
		sb.append("<td valign=\"top\" width=\"90% \">");
		sb.append("<table border=\"0\" width=\"95% \">");
		sb.append("<tr>");
		sb.append("<td><b>" + multInfoEmail.getText() + "</b></td>");
		sb.append("<td><b></b></td>");
		sb.append("<td><b></b></td>");
		sb.append("<td align=\"center\"><b></b></td>");
		sb.append("<td align=\"center\"><b></b></td>");
		sb.append("<td><b></b></td>");
		sb.append("</tr>");
		sb.append("<tr>");
		sb.append("<td></td>");
		sb.append("<td align=\"center\"><b></b></td>");
		sb.append("</tr>");
		sb.append("</table>");
		sb.append("</td>");
		sb.append("</tr>");
		sb.append("</tbody>");
		sb.append("</table>");
		sb.append("<p>");
		sb.append("&nbsp;");
		sb.append("</p>");
		sb.append("<p align=\"center\">");
		sb.append("Copyright©2020-Todos os direitos reservados ");
		sb.append("</p></td>");
		sb.append("</tr>");
		sb.append("<tr>");
		sb.append("<td height=\"9\" style=\"font-size:0;border:1px solid #c1c1c1;border-top:0\" colspan=\"3\"></td>");
		sb.append("</tr>");
		sb.append("<tr>");
		sb.append(
				"<td colspan=\"3\" style=\"font-size:11px;color:#666666;text-align:center;padding-top:15px\">N&atilde;o responda a este e-mail.</td>");
		sb.append("</tr>");
		sb.append("</tbody>");
		sb.append("</table>");
		sb.append("</body>");
		sb.append("</html>");

		return sb;
	}
}