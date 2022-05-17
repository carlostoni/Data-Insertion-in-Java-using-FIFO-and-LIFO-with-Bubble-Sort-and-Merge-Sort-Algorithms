package ordenacao;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class Merge {
	public static void main(String[] args) throws Exception {
		ArrayList<String> x = new ArrayList<String>();
		// Conecta no Banco
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost/DadosInpe?useTimezone=true&serverTimezone=America/Sao_Paulo", "root", "root");
		PreparedStatement stmt = con.prepareStatement("select * from DadosInpe");

		// executar o select
		ResultSet rs = stmt.executeQuery();

		// repete o ResultSet
		while (rs.next()) {
			// Dados que vão ser retirados do Banco 
			String ano= rs.getString("ano");
			String mes = rs.getString("mes");
			String km = rs.getString("km");
			// Coloca todos em uma unica String
			x.add(km + " Km²" + " Ano: " + ano + " Mes: " + mes);

		}
		// Converte um ArrayList para uma Array
		String[] elemento=(String[])x.toArray(new String[x.size()]);
		
		mergeSort(elemento, 0, elemento.length - 1);

		FileWriter pw = new FileWriter("C:/Users/carlo/Desktop/mergee.txt",true);// Escreve os dados no txt

		pw.write(Arrays.toString(elemento).replace(",", "\n")); // Adiciona os dados no txt trocando as virgulas por pular linha 
		pw.flush();
		pw.close();	
		Process pro = Runtime.getRuntime().exec("cmd.exe /c C:/Users/carlo/Desktop/mergee.txt"); // Abre o arquivo txt
		pro.waitFor();
	}

	public static void mergeSort(String[] a, int from, int to) throws Exception {
		if (from == to) {
			return;
		}
		int mid = (from + to) / 2;
		// classificar o primeiro e o segundo semestre
		mergeSort(a, from, mid);
		mergeSort(a, mid + 1, to);
		merge(a, from, mid, to);
	}


	public static void merge(String[] a, int from, int mid, int to) throws Exception {
		int n = to - from + 1;           // tamanho do intervalo a ser mesclado
		String[] b = new String[n];     // mesclar as duas metades em uma array temporária b
		int i1 = from;                 // próximo elemento a considerar no primeiro intervalo
		int i2 = mid + 1;             // próximo elemento a considerar no segundo intervalo
		int j = 0;                   // próxima posição aberta em b

		// desde que nem i1 nem i2 tenham passado o final, mova o menor para b
		while (i1 <= mid && i2 <= to) {
			if (a[i1].compareTo(a[i2]) < 0) {
				b[j] = a[i1];
				i1++;
			} else {
				b[j] = a[i2];
				i2++;
			}
			j++;
		}

		// Observe que apenas um dos dois loops while abaixo é executado
		// copie todas as entradas restantes da primeira metade
		while (i1 <= mid) {
			b[j] = a[i1];
			i1++;
			j++;
		}

		// copie todas as entradas restantes da segunda metade
		while (i2 <= to) {
			b[j] = a[i2];
			i2++;
			j++;
		}

		// copiar de volta da array temporária
		for (j = 0; j < n; j++) {
			a[from + j] = b[j];
		}
	}
}


