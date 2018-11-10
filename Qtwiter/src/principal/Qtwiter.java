package principal;

import java.util.ArrayList;
import java.util.Scanner;

import usuario.Usuario;
import usuario.Usuario_dao;

//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//import usuario.Usuario;
//import usuario.Usuario_dao;

public class Qtwiter {
	public static void main(String[] args) {
		boolean run = true;
		while (run) {
			Scanner input = new Scanner(System.in);
			String comando = input.nextLine();
			String[] comandos = comando.split(" ");
			
			switch (comandos[0]) {
			case "logar":
				Usuario_dao userdao= new Usuario_dao();
				ArrayList<Usuario> listaUsuarios = userdao.getListUser();
				System.out.println(listaUsuarios);
				break;
				
			case "deslogar":
				break;
				
			case "sair":
				System.out.println("Programa encerrado!");
				input.close();
				run = false;
				break;
			default:
				System.out.println("comando inválido");
				break;
			}

		}	
	}
}
