package logico;

import java.util.ArrayList;
import java.util.Objects;

public class Musica  {
	
    private static ArrayList<Musica> todasMusicas = new ArrayList<>();
	private String titulo;
	private Artista artista;
	private double duracao;
	private String genero;
	private int anoLancamento;
	

	Musica(String titulo, Artista artista, double duracao, String genero, int anoLancamento) {
		this.titulo = titulo;
		this.artista = artista;
		this.duracao = duracao;
		this.genero = genero;
		this.anoLancamento = anoLancamento;
		
        todasMusicas.add(this);
	}
	
	public void exibirInfo() {
		System.out.printf("Título: %s, Artista: %s, Duração: %.2f, Gênero: %s, Ano de Lançamento: %d ", this.titulo, this.artista.getNome(), this.duracao, this.genero, this.anoLancamento);
	}
	
	public static void buscaPorTitulo(String nome) {
		int contador = 0;
		for (Musica musica : todasMusicas) {
			if (musica.titulo.equalsIgnoreCase(nome)) {
				musica.exibirInfo();
				contador++;
				break;
			}
		}
		if (contador == 0) {
			System.out.println("Não tem músicas listadas para o título: " + nome);
		}
	}
	
	public static Musica buscaPorTituloM(String titulo) {
    for (Musica m : todasMusicas) {
        if (m.getTitulo().equalsIgnoreCase(titulo)) {
            return m;
        }
    }
    return null;
}


	
	public static void listarTodasMusicas() {
		int contador = 0;

		for (Musica musica : todasMusicas) {
			musica.exibirInfo();
			System.out.println();
			contador++;
		}
		
		if (contador == 0) {
			System.out.println("Não tem músicas registradas");
		}
	}
	
	public static void listaMusicaPorArtista (String nomeArtista) {
		int contador = 0;
		for (Musica musica : todasMusicas) {
			if (nomeArtista.equalsIgnoreCase(musica.artista.getNome())) {
				musica.exibirInfo();
				System.out.println();
				contador++;
			}
		}
		
		if(contador == 0) {
			System.out.println("Não tem músicas listadas para o cantor " + nomeArtista);
		}
	}
	
	public static void listaMusicaPorGenero (String genero) {
		int contador = 0;
		for (Musica musica : todasMusicas) {
			if (musica.genero.equalsIgnoreCase(genero)) {
				musica.exibirInfo();
				System.out.println();
				contador++;
			}
		}
		if(contador == 0) {
			System.out.println("Não tem músicas listadas para o gênero: " + genero);
		}
	}
	
	public static void listaMusicaPorAnoLancamento(int anoLancamento) {
		int contador = 0;
		for (Musica musica : todasMusicas) {
			if (musica.anoLancamento == anoLancamento) {
				musica.exibirInfo();
				System.out.println();
				contador++;
			}
		}
		
		if (contador == 0) {
			System.out.println("Não tem músicas listadas no ano " + anoLancamento);
		}
	}
	@Override
public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Musica musica = (Musica) o;
    return titulo.equals(musica.titulo) && artista.equals(musica.artista);
}

@Override
public int hashCode() {
    return Objects.hash(titulo, artista);
}

	public String getTitulo() {
		return titulo;
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public Artista getArtista() {
		return artista;
	}
	
	public void setArtista(Artista artista) {
		this.artista = artista;
	}
	
	public double getDuracao() {
		return duracao;
	}
	
	public void setDuracao(double duracao) {
		this.duracao = duracao;
	}
	
	public String getGenero() {
		return genero;
	}
	
	public void setGenero(String genero) {
		this.genero = genero;
	}
	
	public int getAnoLancamento() {
		return anoLancamento;
	}
	
	public void setAnoLancamento(int anoLancamento) {
		this.anoLancamento = anoLancamento;
	}
}
