package com.litebase;

import litebase.LitebaseConnection;
import litebase.PreparedStatement;
import litebase.ResultSet;
import totalcross.ui.dialog.MessageBox;
import totalcross.ui.gfx.Color;

public class LitebasePack {

	private LitebaseConnection pdbdriver = null;

	{

		try {
			// if (pdbdriver == null)
			pdbdriver = LitebaseConnection.getInstance("NXNX");
		} catch (Exception e) {
			MessageBox msg = new MessageBox("Aviso!","Erro no bdb");
			msg.setBackColor(Color.WHITE);
			msg.setForeColor(0x424242);
			msg.popup();
		}
	}

	public boolean exists(String nomeTabela) throws Exception {
		boolean res = pdbdriver.exists(nomeTabela);
		convert(nomeTabela);
		return res;
	}

	public void convert(String nomeTabela) {
		try {
			// if (pdbdriver.exists(nomeTabela))
			// pdbdriver.convert(nomeTabela);
		} catch (Exception e) {

		}
	}

	public int purge(String nomeTabela) throws Exception {
		return pdbdriver.purge(nomeTabela);
	}

	public ResultSet executeQuery(String sql) throws Exception {
		ResultSet res = pdbdriver.executeQuery(sql);
		res.first();
		return res;
	}

	public PreparedStatement getPreparedStatement(String sql) throws Exception {
		return pdbdriver.prepareStatement(sql);
	}

	public void executeUpdate(String sql) throws Exception {
		pdbdriver.executeUpdate(sql);
	}

	public void execute(String sql) throws Exception {
		pdbdriver.execute(sql);
	}

	public void closeReally() throws Exception {
		if (pdbdriver != null)
			pdbdriver.closeAll();
	}

	public void closeAll() throws Exception {
		if (pdbdriver != null)
			pdbdriver.closeAll();

	}

	public void executeUpdateWhioutException(String sql) {
		try {
			pdbdriver.executeUpdate(sql);
		} catch (Exception e) {
		}
	}

	public void executeWhioutException(String sql) {
		try {
			pdbdriver.execute(sql);
		} catch (Exception e) {
		}
	}

	private void repairTable(String nomeTabela) throws Exception {
		try {
			pdbdriver.recoverTable(nomeTabela);
		} catch (Exception e) {
		}
	}
}