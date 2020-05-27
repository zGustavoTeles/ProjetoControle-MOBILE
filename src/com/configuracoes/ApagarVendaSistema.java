package com.configuracoes;

import com.auxiliares.Auxiliares;
import com.litebase.LitebasePack;
import com.teclado.Teclado;
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

public class ApagarVendaSistema extends totalcross.ui.Window {

	private Label				 lblProduto;
	private Label 				 lblDataVenda;
	private Label				 lblTotal;
	private Label   			 lblTipoPagamento;
	private Label				 lblCategoria;
	private Label 				 lblMarca;
	private Label 				 lblDescricao;
	private Label 				 lblQuantidade;
	private Label 				 lblVendido;
	private Label 				 lblCodigo;
	private Edit				 editCodigo;
	private Edit				 editVendido;
	private Edit				 editQuantidade;
	private Edit				 editCategoria;
	private Edit				 editMarca;
	private Edit				 editDescricao;
	private Edit 				 editTipoPagamento;
	private Edit 				 editProduto;
	private Edit 				 editDataVenda;
	private Edit 				 editTotal;
	private ArtButton 			 btnVoltar;
	private ArtButton			 btnApagar;
	private ImageControl		 imgVenda;
	public double				 totalVenda = 0.0;
	public int				     qntEstoqueFinal = 0;
	public int				     total = 0;
	public int 				     quantidade = 0;
	public int 					 quantidadeEstoque = 0;
	public int 					 qntEstoqueCalculo = 0;
	public String 				 quantidadeVendida = "";
	public String 				 dataEntrada = "";
	public String				 estoque = "";
	public String 				 quantidadeInserida = "";
	public String 				 valorProduto = "";
	
	public Teclado teclado;

	public ApagarVendaSistema() {
		setBackColor(0x003366);
		initUI();
	}

	public void initUI() {

		try {
			
			imgVenda = new ImageControl(new Image("img/apagarVendaSistema.png"));
			imgVenda.scaleToFit = true;
			imgVenda.centerImage = true;
			add(imgVenda, CENTER, TOP - 10, SCREENSIZE + 20, SCREENSIZE + 30);
			
			lblProduto = new Label("PRODUTO:       ");
			add(lblProduto);
			lblProduto.setRect(LEFT + 90, AFTER - 10, PREFERRED, PREFERRED, imgVenda);
			lblProduto.setBackColor(0x003366);
			lblProduto.setForeColor(Color.WHITE);

			editProduto = new Edit();
			add(editProduto);
			editProduto.setRect(AFTER, SAME, FILL - 100, PREFERRED, lblCategoria);
			editProduto.setBackColor(Color.WHITE);
			editProduto.setForeColor(0x003366);
			editProduto.setText(ApagarVenda.produto);
			editProduto.setEditable(false);
			
			lblMarca = new Label("MARCA:            ");
			add(lblMarca);
			lblMarca.setRect(LEFT + 90, AFTER + 15, PREFERRED, PREFERRED, editProduto);
			lblMarca.setBackColor(0x003366);
			lblMarca.setForeColor(Color.WHITE);

			editMarca = new Edit();
			add(editMarca);
			editMarca.setRect(AFTER, SAME, FILL - 100, PREFERRED, lblMarca);
			editMarca.setBackColor(Color.WHITE);
			editMarca.setForeColor(0x003366);
			editMarca.setText(ApagarVenda.marca);
			editMarca.setEditable(false);
			
			lblDescricao = new Label("DESCRIÇÃO:    ");
			add(lblDescricao);
			lblDescricao.setRect(LEFT + 90, AFTER + 15, PREFERRED, PREFERRED, editMarca);
			lblDescricao.setBackColor(0x003366);
			lblDescricao.setForeColor(Color.WHITE);

			editDescricao = new Edit();
			add(editDescricao);
			editDescricao.setRect(AFTER, SAME, FILL - 100, PREFERRED, lblDescricao);
			editDescricao.setBackColor(Color.WHITE);
			editDescricao.setForeColor(0x003366);
			editDescricao.setText(ApagarVenda.descricao);
			editDescricao.setEditable(false);		

			lblCategoria = new Label("CATEGORIA:    ");
			add(lblCategoria);
			lblCategoria.setRect(LEFT + 90, AFTER + 15, PREFERRED, PREFERRED, editDescricao);
			lblCategoria.setBackColor(0x003366);
			lblCategoria.setForeColor(Color.WHITE);

			editCategoria = new Edit();
			add(editCategoria);
			editCategoria.setRect(AFTER, SAME, FILL - 100, PREFERRED, lblCategoria);
			editCategoria.setBackColor(Color.WHITE);
			editCategoria.setForeColor(0x003366);
			editCategoria.setText(ApagarVenda.categoria);
			editCategoria.setEditable(false);

			lblTipoPagamento = new Label("PAGAMENTO   ");
			add(lblTipoPagamento);
			lblTipoPagamento.setRect(LEFT + 90, AFTER + 15, PREFERRED, PREFERRED, editCategoria);
			lblTipoPagamento.setBackColor(0x003366);
			lblTipoPagamento.setForeColor(Color.WHITE);

			editTipoPagamento = new Edit();
			add(editTipoPagamento);
			editTipoPagamento.setRect(AFTER, SAME, FILL - 100, PREFERRED, lblTipoPagamento);
			editTipoPagamento.setBackColor(Color.WHITE);
			editTipoPagamento.setForeColor(0x003366);
			editTipoPagamento.setText(ApagarVenda.tipoPagamento);
			editTipoPagamento.setEditable(false);

			lblDataVenda = new Label("DATA:               ");
			add(lblDataVenda);
			lblDataVenda.setRect(LEFT + 90, AFTER + 15, PREFERRED, PREFERRED, editTipoPagamento);
			lblDataVenda.setBackColor(0x003366);
			lblDataVenda.setForeColor(Color.WHITE);

			editDataVenda = new Edit();
			add(editDataVenda);
			editDataVenda.setRect(AFTER, SAME, FILL - 100, PREFERRED, lblDataVenda);
			editDataVenda.setBackColor(Color.WHITE);
			editDataVenda.setForeColor(0x003366);
			editDataVenda.setText(ApagarVenda.dataVenda);
			editDataVenda.setEditable(false);

			lblCodigo = new Label("CÓDIGO:           ");
			add(lblCodigo);
			lblCodigo.setRect(LEFT + 90, AFTER + 15, PREFERRED, PREFERRED, lblDataVenda);
			lblCodigo.setBackColor(0x003366);
			lblCodigo.setForeColor(Color.WHITE);
			
			editCodigo = new Edit();
			add(editCodigo);
			editCodigo.setRect(AFTER, SAME, FILL - 100, PREFERRED, lblCodigo);
			editCodigo.setBackColor(Color.WHITE);
			editCodigo.setForeColor(0x003366);
			editCodigo.setText(ApagarVenda.codigoVenda);
			editCodigo.setEditable(false);
			
			lblVendido = new Label("VENDIDO:         ");
			add(lblVendido);
			lblVendido.setRect(LEFT + 90, AFTER + 15, PREFERRED, PREFERRED, lblCodigo);
			lblVendido.setBackColor(0x003366);
			lblVendido.setForeColor(Color.WHITE);

			editVendido = new Edit();
			add(editVendido);
			editVendido.setRect(AFTER, SAME, FILL - 100, PREFERRED, lblVendido);
			editVendido.setBackColor(Color.WHITE);
			editVendido.setForeColor(0x003366);
			editVendido.setText(ApagarVenda.quantidade);
			editVendido.setEditable(false);

			lblTotal = new Label("TOTAL:             ");
			add(lblTotal);
			lblTotal.setRect(LEFT + 90, AFTER + 15, PREFERRED, PREFERRED, lblVendido);
			lblTotal.setBackColor(0x003366);
			lblTotal.setForeColor(Color.WHITE);

			editTotal = new Edit();
			add(editTotal);
			editTotal.setRect(AFTER, SAME, FILL - 100, PREFERRED, lblTotal);
			editTotal.setBackColor(Color.WHITE);
			editTotal.setForeColor(0x003366);
			editTotal.setText(ApagarVenda.valor);
			editTotal.setEditable(false);

			lblQuantidade = new Label("QUANTIDADE: ");
			add(lblQuantidade);
			lblQuantidade.setRect(LEFT + 90, AFTER + 15, PREFERRED, PREFERRED, lblTotal);
			lblQuantidade.setBackColor(0x003366);
			lblQuantidade.setForeColor(Color.WHITE);

			editQuantidade = new Edit();
			add(editQuantidade);
			editQuantidade.setRect(AFTER, SAME, FILL - 100, PREFERRED, lblQuantidade);
			editQuantidade.setBackColor(Color.WHITE);
			editQuantidade.setForeColor(0x003366);
			editQuantidade.setValidChars("0 1 2 3 4 5 6 7 8 9");
			
			btnApagar = new ArtButton("APAGAR");
			add(btnApagar);
			btnApagar.setRect(LEFT, BOTTOM, SCREENSIZE - 4, PREFERRED + 15);
			btnApagar.setBackColor(0xDF0101);
			btnApagar.setForeColor(Color.WHITE);

			btnVoltar = new ArtButton("VOLTAR");
			add(btnVoltar);
			btnVoltar.setRect(RIGHT, BOTTOM, SCREENSIZE - 5, PREFERRED + 13);
			btnVoltar.setBackColor(0x003366);
			btnVoltar.setForeColor(Color.WHITE);
			
			editQuantidade.requestFocus();
			
			reposition();
			
		} catch (Exception e) {
			Auxiliares.artMsgbox("ERRO","Erro ao construir a tela ApagarVendaSistema\n" + e);

		}

	}

	public void onEvent(Event evt) {

		try {
			switch (evt.type) {
			case ControlEvent.PRESSED:

				if (evt.target == btnVoltar) {
					unpop();

				}
				if (evt.target == btnApagar) {

					if (editQuantidade.getText().equals("")) {
						Auxiliares.artMsgbox("CONTROLE", "Por favor digite uma quantidade!");

						return;

					} else if (Convert.toInt(editQuantidade.getText()) == 0) {
						Auxiliares.artMsgbox("CONTROLE", "Quantidade inválida!");
						editQuantidade.setText("");

						return;

					} else if (Convert.toInt(editQuantidade.getText()) > Convert.toInt(ApagarVenda.quantidade)) {
						Auxiliares.artMsgbox("CONTROLE", "Quantidade inserida maior que a quantidade\nda venda!");
						editQuantidade.setText("");
						editVendido.setText(ApagarVenda.quantidade);
						editTotal.setText(ApagarVenda.valor);

						return;

					} else {

						String[] ArtButtonArray = { "Sim", "Não" };
						int i = 0;

						if (Convert.toInt(editVendido.getText()) == 0 || Convert.toInt(editVendido.getText()) < 0) {
							
							i = Auxiliares.artMsgbox("CONTROLE",
									"Deseja apagar essa venda?",
									ArtButtonArray);

						} else {
							i = Auxiliares.artMsgbox("CONTROLE",
									"Deseja apagar " + editQuantidade.getText() + " unidade(s) desta venda?",
									ArtButtonArray);
						}

						if (i == 1) {
							return;

						} else {

							apagarVenda();

							if (Convert.toInt(editVendido.getText()) == 0 || Convert.toInt(editVendido.getText()) < 0) {
								Auxiliares.artMsgbox("CONTROLE", "Venda apagada com sucesso!");
							} else {
								Auxiliares.artMsgbox("CONTROLE", editQuantidade.getText()
										+ " Unidade(s) desta venda foram\napagada(s) com sucesso!");
							}
							unpop();
						}
					}
				}
				if (editQuantidade.getText().length() == 0) {
					
					valorProduto = buscaValorProduto();
					editTotal.setText(valorProduto);
					editVendido.setText(ApagarVenda.quantidade);
					
				}

				if (editQuantidade.getText().length() > 0) {
					
					valorProduto = buscaValorProduto();
					editTotal.setText("");
					editTotal.setText(valorProduto);
					
					editVendido.setText("");
					editVendido.setText(ApagarVenda.quantidade);	
					calculaQuantidade();

				}

			}
		} catch (Exception e) {
			Auxiliares.artMsgbox("ERRO", "Erro na validação da tela DetalharVenda\n" + e);
		}

	}
	
	public void apagarVenda() {

		LitebasePack lb           = null;
		String sql                = "";
		ResultSet rs 		      = null;
		int quantidadeEstoque     = 0;
		int quantidadeInserida    = 0;

		try {

			try {

				lb = new LitebasePack();
				
				
				sql = " SELECT QUANTIDADE FROM ESTOQUE "
				    + " WHERE CODIGO = " + ApagarVenda.codigoProduto;

				rs = lb.executeQuery(sql);
				rs.first();
				
				quantidadeEstoque = Convert.toInt(rs.getString("QUANTIDADE"));
				quantidadeInserida = quantidadeEstoque + Convert.toInt(editQuantidade.getText());
			
				sql = " UPDATE ESTOQUE " 
				    + " SET QUANTIDADE = " + quantidadeInserida 
				    + " WHERE CODIGO = " + ApagarVenda.codigoProduto;

				lb.executeUpdate(sql);
				
//--------------atualizo a venda---------------------------------------------------------------------------------------				
				
				if (Convert.toInt(editVendido.getText()) == 0 || Convert.toInt(editVendido.getText()) < 0) {

					sql = " DELETE VENDAPRODUTO " 
					    + " WHERE CODIGOPROD = " + ApagarVenda.codigoProduto;

					lb.executeUpdate(sql);
				} else {

					sql = " UPDATE VENDAPRODUTO " 
					    + " SET QUANTIDADE = " + editVendido.getText() 
					    + " WHERE CODIGOPROD = " + ApagarVenda.codigoProduto;

					lb.executeUpdate(sql);
					
					sql = " UPDATE VENDAPRODUTO " 
						+ " SET VALOR = " + editTotal.getText()
					    + " WHERE CODIGOPROD = " + ApagarVenda.codigoProduto;

						lb.executeUpdate(sql);
				}

			} finally {
				if (lb != null)
					lb.closeAll();
			}

		} catch (Exception e) {
			Auxiliares.artMsgbox("ERRO", "Erro apagarVenda()\n" + e);
		}

	}
	
	public void calculaQuantidade() {

		try {

			total = Convert.toInt(editVendido.getText()) - Convert.toInt(editQuantidade.getText());
			editVendido.setText(Convert.toString(total));
			
			totalVenda = Convert.toDouble(valorProduto) * Convert.toInt(editVendido.getText());
			editTotal.setText(Convert.toString(totalVenda, 2));

		} catch (Exception e) {
			Auxiliares.artMsgbox("ERRO", "Erro ao calcularQuantidade\n" + e);
		}

	}
	
	public String buscaValorProduto() {

		LitebasePack lb       = null;
		String sql            = "";
		ResultSet rs 		  = null;
		String valorProduto   = "";

		try {
			
			try {
				
				lb = new LitebasePack();
				
				sql = "SELECT VALOR FROM ESTOQUE "
					+ " WHERE CODIGO = " + ApagarVenda.codigoProduto;

				rs = lb.executeQuery(sql);
				rs.first();
				
				valorProduto = rs.getString("VALOR");
				
				return valorProduto;
				
			} finally {
				if(lb != null) {
					lb.closeAll();
				}
			}		
			
		} catch (Exception e) {
			Auxiliares.artMsgbox("ERRO", "Erro ao buscaValorProduto\n" + e);
			return valorProduto;
		}
	}

}
