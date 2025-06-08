package logico;

import java.util.ArrayList;

public class Album {

    private static ArrayList<Album> todosAlbums = new ArrayList<>();

    private String nome;
    private Artista artista;
    private int anoLancamento;
    private ArrayList <Musica> musicas;

    Album(String nome) {
        this.nome = nome;
        this.musicas = new ArrayList<>();

        todosAlbums.add(this);
    }

    void addMusica (String nomeMusica) {
        Musica songParaAdd = Musica.buscaPorTituloM(nomeMusica);

        if (songParaAdd != null) {
            this.musicas.add(songParaAdd);
        }
    }

    void removerAlbum(String nome) {
        boolean removida = Album.todosAlbums.removeIf((album) -> album.getNome().equals(nome));
        if (removida) {
            System.out.println("Album removido " + nome);
        } else {
            System.out.println("Album nao encontrado");
        }

    }

    void exibirAlbum() {
        if (this.musicas.isEmpty()) {
            System.out.println("o Album está vazio.");
        } else {
            System.out.println("\n--- Album: " + this.nome + " ---");

            for(Musica m : this.musicas) {
                m.exibirInfo();
                System.out.println();
            }
        }
    }

    public static void exibirTodosAlbums() {
        int contador = 0;
        for(Album album : todosAlbums){
            System.out.println(album.nome);
            contador++;
        }
        if(contador == 0){
            System.out.println("Albums vazio.");
        }
    }

    public static void listarTodosAlbums(){
        if(todosAlbums.isEmpty()){
            System.out.println("Nenhum album encontrado");
        }else {
            System.out.println("--- Albums ---");
            for(Album p: todosAlbums){
                System.out.println(p.getNome());
            }

        }
    }
        public static Album buscaAlbumPorNome(String nome) {
        if (todosAlbums.isEmpty()) {
            return null;
        } else {
            for (Album p : todosAlbums) {
                if (p.getNome().equalsIgnoreCase(nome)) {
                    return p;
                }
            }

            return null;
        }
    }

    public static void deletarAlbum (Album album) {
        if (album != null) {
            boolean removido = todosAlbums.remove(album);

            if (removido) {
                System.out.println("A Playlist foi removida com sucesso");
            }
        } else {
            System.out.println("Não foi encontrado playlist com esse nome");
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


    public ArrayList<Musica> getMusicas() {
        return this.musicas;
    }

    public void setMusicas(ArrayList<Musica> musicas) {
        this.musicas = musicas;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Artista getArtista() {
        return this.artista;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }

    public int getAnoLancamento() {
        return this.anoLancamento;
    }

    public void setAnoLancamento(int anoLancamento) {
        this.anoLancamento = anoLancamento;
    }
}
