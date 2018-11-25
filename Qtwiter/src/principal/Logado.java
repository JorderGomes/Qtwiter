package principal;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

import folow.Folow;
import folow.Folow_dao;
import postagem.Postagem;
import postagem.Postagem_dao;
import usuario.Usuario;
import usuario.Usuario_dao;

public class Logado {
	private Postagem post;
	private Postagem_dao post_dao = new Postagem_dao();
	private Usuario_dao us_dao = new Usuario_dao();
	private Folow_dao folow_dao = new Folow_dao();
	private Folow folow;
	private Scanner input;

	public void ativo(Usuario user) throws ParseException {
		String texto;
		boolean run = true, result_querys;
		ArrayList<Postagem> listpost = new ArrayList<Postagem>();
		ArrayList<Usuario> list_user = new ArrayList<Usuario>();
		while (run) {
			System.out.println("|  1  | Postar");
			System.out.println("|  2  | Ver suas postagens");
			System.out.println("|  3  | Ver seus seguidores");
			System.out.println("|  4  | Ver quem voce segue");
			System.out.println("|  5  | Seguir Alguém");
			System.out.println("|  6  | Deixar de seguir alguém");
			System.out.println("|  7  | Mudar seu nome"); // <-------------------------------------------
			System.out.println("|  8  | Mudar sua senha");
			System.out.println("|  9  | Mudar seu email");
			System.out.println("|  10 | Ver feed");
			System.out.println("|  11 | Pesquisar por postagem");
			System.out.println("|  12 | Apagar postagem");
			System.out.println("|  13 | Alterar postagem");
			System.out.println("|  14 | Apagar conta");
			System.out.println("|  0  | Deslogar");

			input = new Scanner(System.in);
			String comando = input.nextLine();
			switch (comando) {
			case "0":
				System.out.println("Deslogado.");
				run = false;
				return;

			case "1":
				System.out.println("Digite sua postagem:");
				texto = input.nextLine();
				post = new Postagem(texto, user.getEmail());
				result_querys = post_dao.insercaoPostagem(post);
				if(result_querys) System.out.println("Sua postagem foi publicada!");
				else System.out.println("Erro ao publicar");
				break;

			case "2":
				listpost = post_dao.list_my_Posts(user.getEmail());
				System.out.println(listpost);
				break;
			case "3":
				list_user = us_dao.seguidores(user.getEmail());
				System.out.println(list_user);
				break;
				
			case "4":
				list_user = us_dao.seguidos(user.getEmail());
				System.out.println(list_user);
				break;
			case "5":
				System.out.println("Digite o email de quem você deseja seguir:");
				texto = input.nextLine();
				folow = new Folow( user.getEmail(), texto);
				result_querys =  folow_dao.seguir(folow);
				if(result_querys) System.out.println("Você já está seguindo " + texto);
				else System.out.println("Erro ao seguir.");
				break;
			case "6":
				System.out.println("Digite o email de quem você deseja parar de seguir:");
				texto = input.nextLine();
				folow = new Folow(user.getEmail(), texto);
				result_querys =  folow_dao.pararDeSeguir(folow);
				if(result_querys) System.out.println("Você deixou de seguir " + texto);
				else System.out.println("Erro");
				break;
				
			case "7":
				System.out.println("Qual é o seu novo nome?");
				texto = input.nextLine();
				result_querys = us_dao.alterUser(user, texto) ;
				if(result_querys) System.out.println("Nome alterado!");
				else System.out.println("Erro ao alterar");
				break;
				
			case "8":
				System.out.println("Qual é a sua nova senha?");
				texto = input.nextLine();
				result_querys = us_dao.alterSenhaUser(user, texto) ;
				if(result_querys) System.out.println("Senha alterada!");
				else System.out.println("Erro ao alterar");
				break;
			case "9":
				System.out.println("Qual é o seu novo email?");
				texto = input.nextLine();
				result_querys = us_dao.alterEUser(user, texto) ;
				if(result_querys) System.out.println("Email alterado!");
				else System.out.println("Erro ao alterar");
				break;
			case "10":
				listpost = post_dao.feed(user.getEmail());
				System.out.println(listpost);
				break;
			case "11":
				System.out.println("O que você disse?");
				texto = input.nextLine();
				listpost = post_dao.pesqPostByTex(texto, user.getEmail());
				System.out.println(listpost);
				break;
			case "12":
				System.out.println("O que você disse?");
				texto = input.nextLine();
				result_querys = post_dao.deletarPostagem(texto, user.getEmail());
				if(result_querys) System.out.println("Apagada!");
				else System.out.println("Erro ao apagar");
				break;
			case "13":
				System.out.println("O que você disse?");
				texto = input.nextLine();
				System.out.println("O que você quer dizer?");
				String novoTexto = input.nextLine();
				result_querys = post_dao.alterTextPost(texto, novoTexto, user.getEmail());
				if(result_querys) System.out.println("Alterada!");
				else System.out.println("Texto nao publicado");
				break;
			case "14":
				 result_querys = us_dao.deleteUser(user.getEmail());
				if (result_querys) {
					System.out.println("Conta apagada com sucesso!");
					return;
				} else {
					System.out.println("Falha ao apagar!");
				}
				break;
				
			default:
				System.out.println("comando inválido");
				break;
			}
		}
	}
}














