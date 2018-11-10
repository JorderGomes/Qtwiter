package postagem;

import java.util.Calendar;

public class Postagem {
	private Calendar data;
	private String texto;
	private String email_usuario;
	
	@Override
	public String toString() {
		return "Postagem [data=" + data + ", texto=" + texto + ", email_usuario=" + email_usuario + "]";
	}
	
	public Postagem(Calendar data, String texto, String email_usuario) {
		super();
		this.data = data;
		this.texto = texto;
		this.email_usuario = email_usuario;
	}
	public Calendar getData() {
		return data;
	}
	public void setData(Calendar data) {
		this.data = data;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	public String getEmail_usuario() {
		return email_usuario;
	}
	public void setEmail_usuario(String email_usuario) {
		this.email_usuario = email_usuario;
	}
	
	
	
}
