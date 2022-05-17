package table;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import entity.DadosInpe;

public class DadosInpeModel extends AbstractTableModel {

	private static final int COL_ID = 0;   // variável para representar a posição na tabela
	private static final int COL_ANO = 1; // variável para representar a posição na tabela
	private static final int COL_MES = 2; // variável para representar a posição na tabela
	private static final int COL_KM = 3;  // variável para representar a posição na tabela

	private List<DadosInpe> valores; // Uma lista para passar variável local 

	public DadosInpeModel(List<DadosInpe> valores) {
		this.valores = valores;
	}
	public int getRowCount() {

		return valores.size();
	}
	public int getColumnCount() {
		return 4;
	}


	public Object getValueAt(int rowIndex, int columnIndex) { // vai representar as posicoes das colunas
		DadosInpe dadosinpe = valores.get(rowIndex);
		if(columnIndex == COL_ID) {
			return dadosinpe.getId();
		}
		else if (columnIndex == COL_ANO) {
			return dadosinpe.getAno();
		}
		else if (columnIndex == COL_MES) {
			return dadosinpe.getMes();
		}
		else if (columnIndex == COL_KM) {
			return dadosinpe.getKm();
		}
		return null;
	}
	 @Override
	public String getColumnName(int column) { // Nome das colunas
		String coluna ="";
		switch(column) {
		case COL_ID:
			coluna = "Id";
			break;
		case COL_ANO:
			coluna = "Ano";
			 break;
		case COL_MES:
			coluna = "Mês";
			 break;
		case COL_KM:
			coluna = "Km²";
			 break;
		default:
			throw new IllegalArgumentException("Coluna Invalida:");
		}
		return coluna;
	}
	 @Override
	public Class<?> getColumnClass(int ColumnIndex){ // Para representar cada tipo de dado para sua respectiva coluna
		if (ColumnIndex == COL_ID) {
			return Long.class;
		}else if (ColumnIndex == COL_ANO) {
			return String.class;
		}
		else if (ColumnIndex == COL_MES) {
			return String.class;
		}
		else if (ColumnIndex == COL_KM) {
			return String.class;
		}
		return null;
	}

	public DadosInpe get (int row) { // Para saber em qual linha está usando para ter o retorno que ela contém 
		return valores.get(row);
	}
}
