package com.carregatabelas;

import com.litebase.LitebasePack;

import litebase.ResultSet;
import totalcross.ui.ListBox;

public class CarregaTabelas {
	
	public static int INT = 0;
	public static int STRING = 1;
	public static int DOUBLE = 2;
	
	public void carregaLstBox(ListBox lst, String sql, String[] colunas, int[] tamanhos, int[] tipos) throws Exception {
		LitebasePack lb = null;
		ResultSet rs = null;
		try {
			lb = new LitebasePack();
			lst.removeAll();

			rs = lb.executeQuery(sql);

			for (int x = 0; x < rs.getRowCount(); x++) {
				String Aux = "";
				for (int y = 0; y < colunas.length; y++) {
					if (tipos[y] == INT) {
						Aux += "   |   ";
						Aux += String.valueOf(rs.getInt(colunas[y]));
					} else if (tipos[y] == STRING) {
						Aux += rs.getString(colunas[y]);
					} else {
						throw new Exception("Tipo de Dado em carregaLstBox Inexistente");
					}
				}
				lst.add(Aux);
				rs.next();
			}
		} finally {
			if (rs != null)
				rs.close();
			if (lb != null)
				lb.closeAll();
		}
	}

}
