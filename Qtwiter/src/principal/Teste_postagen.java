package principal;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
//import java.sql.Date;
import java.util.Date;
import java.util.Scanner;

import postagem.Postagem;
import postagem.Postagem_dao;

public class Teste_postagen {
	public static void main(String[] args) throws ParseException {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Scanner input = new Scanner(System.in);
		String data_texto = input.nextLine();
		Date data = (Date) dateFormat.parse(data_texto);
		System.out.println(data);
		
	}


	public void Test_insertion_data() throws ParseException {
		// TETSE DE INSERÇÃO DE DATA
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Date data = new Date();
		String dat = dateFormat.format(data);
		data = (Date) dateFormat.parse(dat);
		System.out.println(dateFormat.format(data));
		Postagem post = new Postagem("e te jooj", "j@eaple.com");
		Postagem_dao post_dao = new Postagem_dao();
		post_dao.insercaoPostagem(post);
	}

}
