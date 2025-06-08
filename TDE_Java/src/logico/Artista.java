package logico;

import java.util.ArrayList;

public class Artista {

	private String nome;

	public static ArrayList<Artista> todosArtistas = new ArrayList<>();
	
	Artista(String nome) {
		this.nome = nome;

		todosArtistas.add(this);
	}

	public static Artista buscaArtistaPorNome(String nome) {
		boolean encontrado = false;
		
		for (Artista artista: todosArtistas) {
			if (artista.getNome().equalsIgnoreCase(nome)) {
				System.out.println(artista.nome);			
				encontrado = true;
				return artista;
			}
		}
		
		if (!encontrado) {
			return null;
		}
		
		return null;
	}
	
	public static void excluirArtista(String nome) {
		Artista removerArtista = null;
		
		for(Artista artista: todosArtistas) {
			if (artista.getNome().equalsIgnoreCase(nome)) {
				removerArtista = artista;
				break;
			}
		}
		if (removerArtista != null) {
			todosArtistas.remove(removerArtista);
			System.out.println(nome);
		}
		
	}
	
	public static Artista criarAtista (String nome) {
		
		Artista artista = Artista.buscaArtistaPorNome(nome);
		
		if (artista == null) {	
			artista = new Artista(nome);
		}
		
		return artista;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	
}
