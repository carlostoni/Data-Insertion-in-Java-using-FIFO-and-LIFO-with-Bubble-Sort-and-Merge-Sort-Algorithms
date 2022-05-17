package fila;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;


public class Fila {
	public static void main(String args[]) throws SQLException, IOException, InterruptedException {
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
		// Lista para armazenar os itens da fila 
		Queue<String> fila=new LinkedList<String>();

		fila.addAll(x);//Pega os dados e adiciona na fila 
		System.out.println(fila.toString()+"\n"); // Mostra a fila

		FileWriter pw = new FileWriter("C:/Users/carlo/Desktop/fila.txt",true); // Escreve os dados no txt

		pw.write(x+"\n");
		pw.flush();
		pw.close();	
		Process pro = Runtime.getRuntime().exec("cmd.exe /c C:/Users/carlo/Desktop/fila.txt"); // Abre o arquivo txt
		pro.waitFor();
	}


}
