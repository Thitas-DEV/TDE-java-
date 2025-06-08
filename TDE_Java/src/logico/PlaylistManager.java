package logico;

import java.util.*;
import logico.util.FileUtil;

public class PlaylistManager {
    private List<Musica> musicasDisponiveis;
    private Map<String, Playlist> playlists;

    private static final String MUSICAS_ARQUIVO = "musicas.ser";
    private static final String PLAYLISTS_ARQUIVO = "playlists.ser";

    public PlaylistManager() {
        this.musicasDisponiveis = FileUtil.carregarMusicas(MUSICAS_ARQUIVO);
        this.playlists = FileUtil.carregarPlaylists(PLAYLISTS_ARQUIVO);
    }

    public void adicionarMusica(Musica musica) {
        musicasDisponiveis.add(musica);
        FileUtil.salvarMusicas(musicasDisponiveis, MUSICAS_ARQUIVO);
        System.out.println("Música adicionada e salva com sucesso!");
    }

    public void listarMusicasDisponiveis() {
        if (musicasDisponiveis.isEmpty()) {
            System.out.println("Nenhuma música disponível.");
        } else {
            for (int i = 0; i < musicasDisponiveis.size(); i++) {
                System.out.println((i + 1) + ". " + musicasDisponiveis.get(i));
            }
        }
    }

    public void criarPlaylist(String nome) {
        if (playlists.containsKey(nome)) {
            System.out.println("Já existe uma playlist com esse nome.");
            return;
        }
        playlists.put(nome, new Playlist(nome));
        salvarPlaylists();
    }

    public void adicionarMusicaNaPlaylist(String nomePlaylist, int indiceMusica) {
        Playlist playlist = playlists.get(nomePlaylist);
        if (playlist == null) {
            System.out.println("Playlist não encontrada.");
            return;
        }

        if (indiceMusica < 1 || indiceMusica > musicasDisponiveis.size()) {
            System.out.println("Índice inválido.");
            return;
        }

        playlist.addMusica(musicasDisponiveis.get(indiceMusica - 1));
        salvarPlaylists();
        System.out.println("Música adicionada na playlist!");
    }

    public void listarPlaylists() {
        if (playlists.isEmpty()) {
            System.out.println("Nenhuma playlist criada.");
        } else {
            for (String nome : playlists.keySet()) {
                System.out.println(playlists.get(nome));
            }
        }
    }

    public void exibirPlaylist(String nome) {
        Playlist playlist = playlists.get(nome);
        if (playlist != null) {
            playlist.exibirMusicas();
        } else {
            System.out.println("Playlist não encontrada.");
        }
    }

    public void ordenarPlaylist(String nome, String criterio) {
        Playlist playlist = playlists.get(nome);
        if (playlist == null) {
            System.out.println("Playlist não encontrada.");
            return;
        }

        switch (criterio.toLowerCase()) {
            case "titulo":
                playlist.ordenarPorTitulo();
                break;
            case "artista":
                playlist.ordenarPorArtista();
                break;
            case "duracao":
                playlist.ordenarPorDuracao();
                break;
            default:
                System.out.println("Critério inválido.");
                return;
        }

        salvarPlaylists();
        System.out.println("Playlist ordenada por " + criterio + ".");
    }

    private void salvarPlaylists() {
        FileUtil.salvarPlaylists(playlists, PLAYLISTS_ARQUIVO);
    }
}