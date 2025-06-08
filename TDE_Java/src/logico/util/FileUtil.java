package logico.util;
import logico.Artista;
import logico.Musica;   
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import logico.Musica;
import logico.Playlist;

public class FileUtil {

    @SuppressWarnings("unchecked")
    public static List<Musica> carregarMusicas(String nomeArquivo) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nomeArquivo))) {
            return (List<Musica>) ois.readObject();
        } catch (FileNotFoundException e) {
            return new ArrayList<>(); // Nenhum arquivo ainda? Lista vazia.
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao carregar músicas: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public static void salvarMusicas(List<Musica> musicas, String nomeArquivo) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nomeArquivo))) {
            oos.writeObject(musicas);
        } catch (IOException e) {
            System.out.println("Erro ao salvar músicas: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public static Map<String, Playlist> carregarPlaylists(String nomeArquivo) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nomeArquivo))) {
            return (Map<String, Playlist>) ois.readObject();
        } catch (FileNotFoundException e) {
            return new HashMap<>();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao carregar playlists: " + e.getMessage());
            return new HashMap<>();
        }
    }

    public static void salvarPlaylists(Map<String, Playlist> playlists, String nomeArquivo) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nomeArquivo))) {
            oos.writeObject(playlists);
        } catch (IOException e) {
            System.out.println("Erro ao salvar playlists: " + e.getMessage());
        }
    }
}