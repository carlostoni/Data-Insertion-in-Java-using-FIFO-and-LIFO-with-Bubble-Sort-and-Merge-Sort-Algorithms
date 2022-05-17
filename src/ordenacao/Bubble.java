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
import java.util.Scanner;

public class Bubble {
	  public static void main(String[] args) throws SQLException, IOException, InterruptedException {
		  ArrayList<String> x = new ArrayList<String>(); // ArrayList para armazenar os dados do banco

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
	        
	        String tempStr; //String Temporaria 
	             
	        String[] t1;  // Vai receber os dados
	        
	        t1 = elemento; 
	       
	        // Verificando a ordem dos elementos dois a dois, e trocando-os de lugar se necessário
	        for (int t = 0; t < t1.length - 1; t++) {
	            for (int i= 0; i < t1.length - t -1; i++) {
	                if(t1[i+1].compareTo(t1[i])<0) {
	                    tempStr = t1[i];
	                    t1[i] = t1[i + 1];
	                    t1[i + 1] = tempStr;
	                }
	            }
	        }
	        for (int i = 0; i < t1.length; i++) {
	        	FileWriter pw = new FileWriter("C:/Users/carlo/Desktop/bubble.txt",true); // Escreve os dados no txt

	    		pw.write(t1[i]+"\n");
	    		pw.flush();
	    		pw.close();	
	    		
	            System.out.println(t1[i]); // Mostra os dados Ordenados
	        }
	        Process pro = Runtime.getRuntime().exec("cmd.exe /c C:/Users/carlo/Desktop/bubble.txt"); // Abre o arquivo txt
    		pro.waitFor();
	   }

}
