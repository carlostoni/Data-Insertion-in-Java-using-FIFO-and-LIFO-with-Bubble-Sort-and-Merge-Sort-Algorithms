package com.mballem.app;

import dao.DBConnection;
import dao.DadosInpeDAO;
import form.DadosInpeForm;

public class Main { 

	public static void main(String[] args) {
		DBConnection.createTable(); // Inicia o Banco
		new DadosInpeForm(); // Para Iniciar a aplicação 

	}

}
