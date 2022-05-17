package pilha;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class PilhaTxt {

	public void DadosinpePilha() throws SQLException, IOException, InterruptedException {
		String x = null ;
		Pilha p = new Pilha(); // Chama a classe Pilha
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
			x = ("Ano: " + ano + " Mes: " + mes + " Km²: " + km + "" );

			p.push(x); // Puxa os dados para pilha
		}
		while(!p.isEmpty()) {

			String elemento = p.pop();
			FileWriter pw = new FileWriter("C:/Users/carlo/Desktop/pilha.txt",true); // Escreve os dados no txt

			pw.write(elemento+"\n");
			pw.flush();
			pw.close();	
			
		} 
		
		Process pro = Runtime.getRuntime().exec("cmd.exe /c C:/Users/carlo/Desktop/pilha.txt"); // Abre o arquivo txt
		pro.waitFor();
	}
	
	
}
