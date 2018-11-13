package postagem;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
//import java.sql.Date;
import java.util.Date;

public class Teste_postagen {
	public static void main(String[] args) throws ParseException {
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Date data = new Date();
		String dat =  dateFormat.format(data);		
		data = (Date) dateFormat.parse(dat);
		
		
		System.out.println(dateFormat.format(data));
		
		
		Postagem post = new Postagem("e te jooj", "j@eaple.com");
		Postagem_dao post_dao = new Postagem_dao();
		post_dao.insercaoPostagem(post);
	}

}
