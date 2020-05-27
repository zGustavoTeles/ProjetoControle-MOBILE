package com.artMessageBox;

import totalcross.sys.Convert;
import totalcross.sys.InvalidNumberException;
import totalcross.sys.Settings;
import totalcross.sys.SpecialKeys;
import totalcross.ui.Button;
import totalcross.ui.Container;
import totalcross.ui.Grid;
import totalcross.ui.Label;
import totalcross.ui.Ruler;
import totalcross.ui.Window;
import totalcross.ui.event.ControlEvent;
import totalcross.ui.event.KeyEvent;
import totalcross.ui.event.KeyListener;
import totalcross.ui.event.PressListener;
import totalcross.ui.gfx.Color;

public class ArtMessageBox extends Window {
	Conteudo conteudo;
	int resposta = 0;

	public ArtMessageBox(String titulo, String message) {
		try {
			conteudo = new Conteudo(titulo, message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArtMessageBox(String titulo, String message, String[] captions) {
		try {
			conteudo = new Conteudo(titulo, message, captions);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArtMessageBox(String titulo, Grid grid, String ultimoPreco) {
		try {
			conteudo = new Conteudo(titulo, grid, ultimoPreco);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	class Conteudo extends Container {
		Label lblTitulo;
		Label lblMessage;
		Grid grid;
		Ruler rl;
		Button btnOk;
		Button btnSim, btnNao;
		boolean isCaptions = false;

		public Conteudo(String titulo, String message) throws Exception {
			isCaptions = false;
			lblTitulo = new Label(titulo);
			lblMessage = new Label(message);
			lblMessage.setForeColor(0x345D7E);
			btnOk = new Button("OK");
			rl = new Ruler();
			rl.setForeColor(0x345D7E);
			if (titulo.equalsIgnoreCase("CONTROLE")) {
				lblTitulo.setForeColor(0x345D7E);
				btnOk.setForeColor(Color.WHITE);
				btnOk.setBackColor(0x04B431);
				
			} else if (titulo.equalsIgnoreCase("ERRO")) {
				lblTitulo.setForeColor(0xDF0101);
				btnOk.setForeColor(Color.WHITE);
				btnOk.setBackColor(0xDF0101);

				
			} else {
				lblTitulo.setForeColor(0x345D7E);
				btnOk.setForeColor(Color.WHITE);
				btnOk.setBackColor(0x04B431);
			}
			setBorderStyle(BORDER_SIMPLE);
			btnSim = new Button("");
			btnNao = new Button("");

		}

		public Conteudo(String titulo, String message, String[] captions) throws Exception {
			btnOk = new Button("");
			isCaptions = true;
			lblTitulo = new Label(titulo);
			lblTitulo.setForeColor(0x345D7E);
			lblMessage = new Label(message);
			lblMessage.setForeColor(0x345D7E);
			btnNao = new Button(captions[1]);
			btnSim = new Button(captions[0]);
			rl = new Ruler();
			rl.setForeColor(0x345D7E);
			setBorderStyle(BORDER_SIMPLE);
			btnSim.setBackColor(0x04B431);
			btnNao.setBackColor(0xDF0101);
			btnSim.setForeColor(Color.WHITE);
			btnNao.setForeColor(Color.WHITE);

		}

		public Conteudo(String titulo, Grid grid, String ultimoPreco) throws Exception {
			isCaptions = false;
			lblTitulo = new Label(titulo);

			this.grid = grid;

			lblMessage = new Label("");
			lblMessage.setForeColor(Color.WHITE);
			btnOk = new Button("OK");

			rl = new Ruler();
			if (titulo.equalsIgnoreCase("CONTROLE")) {
				lblTitulo.setForeColor(Color.ORANGE);
				btnOk.setForeColor(Color.ORANGE);

			} else if (titulo.equalsIgnoreCase("Erro")) {
				lblTitulo.setForeColor(Color.RED);
				btnOk.setForeColor(Color.RED);

			} else {
				lblTitulo.setForeColor(0x345D7E);
				btnOk.setForeColor(0x345D7E);
			}
			setBorderStyle(BORDER_SIMPLE);
			btnSim = new Button("");
			btnNao = new Button("");

		}

		public void initUI() {
			super.initUI();
			add(lblTitulo, LEFT + 6, TOP + 6, PREFERRED, PREFERRED);
			add(rl, LEFT, AFTER + 2, PREFERRED, 1);

			if (grid != null) {
				add(grid, LEFT + 3, AFTER + 3, FILL, PREFERRED);
				this.grid.requestFocus();
			}

			add(lblMessage, LEFT + 6, AFTER + 6, PREFERRED, PREFERRED);
			
			if (isCaptions) {
				add(btnSim, LEFT + 6, BOTTOM - 6, SCREENSIZE + 20, PREFERRED + 8);
				add(btnNao, RIGHT - 6, BOTTOM - 6, SCREENSIZE + 20, PREFERRED + 8);
			} else {
				add(btnOk, CENTER,  BOTTOM - 6, SCREENSIZE + 20, PREFERRED + 8);
			}

		}

	}

	public void initUI() {
		super.initUI();
		fadeOtherWindows = Settings.fadeOtherWindows;
		add(conteudo, LEFT, TOP, FILL, FILL);
		conteudo.btnOk.addPressListener(new PressListener() {
			public void controlPressed(ControlEvent arg0) {
				resposta = -1;
				unpop();
			}
		});
		conteudo.btnSim.addPressListener(new PressListener() {
			public void controlPressed(ControlEvent e) {
				resposta = 0;
				unpop();
			}
		});

		conteudo.btnNao.addPressListener(new PressListener() {
			public void controlPressed(ControlEvent e) {
				resposta = 1;
				unpop();
			}
		});

		if (conteudo.grid != null) {
			conteudo.grid.addKeyListener(new KeyListener() {

				public void specialkeyPressed(KeyEvent arg0) {
					if (((KeyEvent) arg0).key == SpecialKeys.ENTER) {
						try {
							if (conteudo.grid.getSelectedIndex() != -1) {
								resposta = Convert.toInt(conteudo.grid.getSelectedItem()[0].toString());
								unpop();
							}
						} catch (InvalidNumberException e1) {
						}
					}
				}

				public void keyPressed(KeyEvent arg0) {

				}

				public void actionkeyPressed(KeyEvent arg0) {

				}
			});
		}

	}

	public int getPressedButonIndex() {
		return resposta;
	}

	// @Override
	public void popup() {
		// TODO Auto-generated method stub
		super.popup();
	}

}
