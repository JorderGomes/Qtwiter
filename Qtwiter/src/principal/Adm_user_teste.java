package principal;
import java.util.ArrayList;
//import java.util.ArrayList;
import java.util.Scanner;
import usuario.Usuario;
import usuario.Usuario_dao;
public class Adm_user_teste {
	public static void main(String[] args) {
		boolean run = true;
		Usuario_dao userdao= new Usuario_dao();
		Usuario user;
		boolean res = false;
		ArrayList<Usuario> listaUsuarios ;
		while (run) {
			
			Scanner input = new Scanner(System.in);
			String comando = input.nextLine();
			String[] comandos = comando.split(" ");
			
			switch (comandos[0]) {
			case "Adduser":
				user = new Usuario( comandos[1] , comandos[2], comandos[3]);
				res = userdao.addUser(user);
				if (res) {
					System.out.println("Cadastro efetivado com sucesso!");
				} else {
					System.out.println("Falha ao cadastrar!");
				}
				break;
			case "deluser":
				res = userdao.deleteUser(comandos[1]);
				if (res) {
					System.out.println("Conta apagada com sucesso!");
				} else {
					System.out.println("Falha ao apagar!");
				}
				break;
				
			case "showAllusers":
				listaUsuarios = userdao.getListUser();
				System.out.println(listaUsuarios);
				break;
			case "User_na":
				System.out.println("Digite o nome do usuario:");
				String nome = input.nextLine();
				listaUsuarios = userdao.getListUserNome(nome);	
				System.out.println(listaUsuarios);
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
