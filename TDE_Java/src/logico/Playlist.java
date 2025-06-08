package logico;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Playlist {
	private static ArrayList<Playlist> playlists = new ArrayList<>();
	private static ArrayList<Playlist> todasPlaylists = new ArrayList<>();
	
	private String nome;
	private ArrayList<Musica> musicas = new ArrayList<>();
	
	Playlist(String nome) {
		this.nome = nome;
		
		playlists.add(this);
	}
	
	void addMusica(Musica musica) {
		this.musicas.add(musica);
		System.out.println("Música " + musica.getTitulo() + " adcionada a playlist " + this.nome);
	}
	public void exibirMusicas() {
        if (musicas == null || musicas.isEmpty()) {
            System.out.println("Nenhuma música na playlist.");
        } else {
            for (int i = 0; i < musicas.size(); i++) {
                System.out.println((i + 1) + ". " + musicas.get(i));
            }
        }
    }

	void removerMusica(Musica musicaR){
	    boolean removida = musicas.removeIf(musica -> musica.equals(musicaR));
		if(removida){
			System.out.println("Musica removida da Playlist");
		} else {
			System.out.println("Musica não encontrada na playlist");
		}
	}

	void exibirPlaylist() {
        if (musicas.isEmpty()) {
            System.out.println("A playlist está vazia.");
        } else {
            System.out.println("\n--- Playlist: " + this.nome + " ---");
            for (Musica m : musicas) {
                m.exibirInfo();
                System.out.println();
            }
        }
    }
	
	public static void listaTodasPlaylists () {
		if (todasPlaylists.isEmpty()) {
        System.out.println("Nenhuma playlist cadastrada.");
    } else {
        for (Playlist p : todasPlaylists) {
            System.out.println("- " + p.getNome() + " (" + p.getMusicas().size() + " músicas)");
        }
    }
}
	
	public static Playlist buscaPlaylistPorNome(String nome) {
	   for (Playlist p : todasPlaylists) {
        if (p.getNome().equalsIgnoreCase(nome)) {
            return p;
        }
    }
    return null;
}
	
	public static void deletarPlaylist (Playlist playlist) {
		if (playlist != null) {
			boolean removido = playlists.remove(playlist);
			
			if (removido) {
				System.out.println("A Playlist foi removida com sucesso");
			}
		} else {
			System.out.println("Não foi encontrado playlist com esse nome");
		}
	}
	
	public ArrayList<Musica> getMusicas() {
		return musicas;
	}
	
	public void setMusicas(ArrayList<Musica> musicas) {
		this.musicas = musicas;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
public static void adicionarPlaylist(Playlist playlist) {
    todasPlaylists.add(playlist);
}
public void ordenarPorTitulo() {
    Collections.sort(musicas, Comparator.comparing(Musica::getTitulo, String.CASE_INSENSITIVE_ORDER));
}

public void ordenarPorArtista() {
    Collections.sort(musicas, Comparator.comparing(m -> m.getArtista().getNome(), String.CASE_INSENSITIVE_ORDER));
}

public void ordenarPorDuracao() {
    Collections.sort(musicas, Comparator.comparingDouble(Musica::getDuracao));
	
}



}
