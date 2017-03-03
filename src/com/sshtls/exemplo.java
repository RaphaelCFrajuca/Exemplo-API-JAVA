package com.sshtls;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import org.json.JSONObject;


public class exemplo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// Definir API
		
		String api = "http://api.sshtls.com.br/?ip=104.197.253.130&port=22&user=root&pass=12&command=" + "echo_BadGuy"; // Dica: caso o espaço não esteja funcionando na string de comandos, use um "_" ao envés do espaço, ele sera substituido automaticamente por um espaço.
		
		// Iniciar conexão com o Bloco try
		
		try {
			URL url = new URL(api);
			URLConnection connection = url.openConnection();
			connection.connect();
			
			// Receber resposta
			
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String input;

			input=in.readLine();
		
			// Tratar resposta em JSON
			
			JSONObject json = new JSONObject(input);
			System.out.println("IP: " + json.getString("IP"));
			System.out.println("PORTA: " + json.getInt("port"));
			System.out.println("USUARIO: " + json.getString("user"));
			boolean ok = json.getBoolean("CONNECT");
			if (ok!=true) {
				System.out.println("Conexão Mal Sucedida!");
				return;
			}
			System.out.println("Conexão Bem Sucedida!");
			System.out.println("COMANDO: " + json.getString("command"));
			System.out.println("SAIDA: " + json.getString("OUT:"));
		    in.close();
			
		} catch (Exception e) {
			
			// Mostrar erros caso algum exista
			
			System.out.println("Erro ao Conectar no Servidor!" + System.lineSeparator() + "Erro: ");
		e.getMessage();
		}
		// Fim!
	}
}
	
