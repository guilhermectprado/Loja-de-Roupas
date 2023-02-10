package utils;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import model.dominio.Fornecedor;
import model.dominio.ItemVenda;

public class Tabela extends AbstractTableModel {
	private ArrayList linhas = null;
	private String[] colunas = null;

	public Tabela (ArrayList lin, String[] col) {
		setLinhas(lin);
		setColunas(col);
	}
	
	private void setLinhas(ArrayList dados) {
		linhas = dados;
	}
	
	public ArrayList getLinhas() {
		return linhas;
	}
	
	private void setColunas(String[] nomes) {
		colunas = nomes;
		
	}

	public String[] getColunas() {
		return colunas;
	}

	@Override
	public int getRowCount() {
		return linhas.size();
	}

	@Override
	public int getColumnCount() {
		return colunas.length;
	}
	
	public String getColumnName(int numCol) {
		return colunas[numCol];
	}

	@Override
	public Object getValueAt(int numLin, int numCol) {
		Object[] linha = (Object[])getLinhas().get(numLin);
		return linha[numCol];
	}
	
}
