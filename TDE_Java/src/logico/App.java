package logico;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class App {

	class TestaEntrada {
		public static void main(String[] args) throws IOException {
			InputStream is = new FileInputStream("arquivo.txt");

			int b;
			while ((b = is.read()) != -1) {
				System.out.print((char) b); 
			}

			is.close(); 
		}
	}

	public static void main(String[] args) {
		carregarPlaylistsDeArquivos();
		carregarMusicasDeArquivo(); 

		menu();
	}

	public static void menu() {
		Scanner entrada = new Scanner(System.in);

		System.out.println("Bem vindo ao Unitfy");
		System.out.println("-> Escolha uma menu");
		System.out.println("[1] - Músicas");
		System.out.println("[2] - Playlists");
		System.out.println("[3] - Albums");

		int escolha = entrada.nextInt();
		entrada.nextLine();

		switch (escolha) {
			case 1:
				menuMusicas();
				break;
			case 2:
				menuPlaylist();
				break;
			case 3:
				menuAlbum();
		}

		entrada.close();

	}

	public static void menuMusicas() {
		Scanner entrada = new Scanner(System.in);

		System.out.println("Menu Música");
		System.out.println("-> Escolha uma opção");
		System.out.println("[1] - Ver músicas dísponíveis");
		System.out.println("[2] - Adicionar música");
		System.out.println("[3] - Remover música");
		System.out.println("[4] - Retornar ao menu anterior");

		int escolha = entrada.nextInt();
		entrada.nextLine();

		switch (escolha) {
			case 1:
				submenuMusicasDisponiveis();
				break;
			case 2:
				System.out.print("Digite o Nome da música: ");
				String nomeMusica = entrada.nextLine();

				System.out.print("Digite o Artista Responsável pela música: ");
				String nomeArtista = entrada.nextLine();
				Artista artistaUsado = Artista.criarAtista(nomeArtista);

				System.out.print("Digite a duração da música: ");
				double duracaoMusica = entrada.nextDouble();
				entrada.nextLine();

				System.out.print("Digite o gênero da música: ");
				String generoMusica = entrada.nextLine();

				System.out.print("Digite o ano de lançamento da música: ");
				int anoLancamento = entrada.nextInt();

				Musica novaMusica = new Musica(nomeMusica, artistaUsado, duracaoMusica, generoMusica, anoLancamento);
				novaMusica.exibirInfo();
				salvarMusicaEmArquivo(novaMusica);
				System.out.println("Música adicionada com sucesso!");
				System.out.println("Insira qualquer número para voltar ao menu anterior");

				menuMusicas();
				break;
			case 3:
				String titulo = entrada.nextLine();
				Musica.buscaPorTitulo(titulo);
				break;
			case 4:
				menu();
				break;
		}
		entrada.close();
	}

	public static void submenuMusicasDisponiveis() {
		Scanner entrada = new Scanner(System.in);

		System.out.println("Submenu Música");
		System.out.println("-> Escolha uma opção");
		System.out.println("[1] - Ver todas as músicas");
		System.out.println("[2] - Busca por título: ");
		System.out.println("[3] - Busca por nome do artista:");
		System.out.println("[4] - Busca por gênero:");
		System.out.println("[5] - Busca por ano de lançamento:");
		System.out.println("[6] - Voltar ao menu anterior");

		int escolha = entrada.nextInt();
		entrada.nextLine();

		switch (escolha) {
			case 1:
				Musica.listarTodasMusicas();
				break;
			case 2:
				System.out.println("Digite o Nome da musica a ser procurada: ");
				String titulo = entrada.nextLine();
				Musica.buscaPorTitulo(titulo);

				break;
			case 3:
				System.out.println("Digite o Nome da artista a ser procurado: ");
				String artista = entrada.nextLine();
				Musica.listaMusicaPorArtista(artista);
				break;
			case 4:
				System.out.println("Digite o Genero da musica a ser procurada: ");
				String genero = entrada.nextLine();
				Musica.listaMusicaPorGenero(genero);
				break;
			case 5:
				System.out.println("Digite o ano da musica a ser procurada: ");

				int anoLancamento = entrada.nextInt();
				entrada.nextLine();
				Musica.listaMusicaPorAnoLancamento(anoLancamento);
				break;
			case 6:
				menuMusicas();
				break;
		}

		System.out.print("\n");
		System.out.println("Insira qualquer número para voltar ao menu anterior");

		entrada.nextDouble();

		submenuMusicasDisponiveis();
		entrada.close();
	}

	public static void menuPlaylist() {
		Scanner entrada = new Scanner(System.in);

		System.out.println("Menu Playlist");
		System.out.println("-> Escolha uma opção");
		System.out.println("[1] - Ver playlists dísponíveis");
		System.out.println("[2] - Criar playlist");
		System.out.println("[3] - Deletar playlist");
		System.out.println("[4] - Editar playlist");
		System.out.println("[5] - Salvar playlist");
		System.out.println("[6] - Voltar ao menu anterior");

		int escolha = entrada.nextInt();
		entrada.nextLine();

		switch (escolha) {
			case 1:
				Playlist.listaTodasPlaylists();
				break;
			// salvarplaylist
			case 2:
				System.out.println("Digite o nome da sua nova playlist");

				String nomePlaylistCriar = entrada.nextLine();
				Playlist novaPlaylist = new Playlist(nomePlaylistCriar);
				salvarPlaylistEmArquivo(novaPlaylist);
				Playlist.adicionarPlaylist(novaPlaylist);

				System.out.println("Playlist " + novaPlaylist.getNome() + " criada com successo");
				break;

			case 3:
				System.out.println("Digite o nome da playlist a ser deletada");

				String nomePlaylistDeletar = entrada.nextLine();
				Playlist selecionarPlaylistDeletar = Playlist.buscaPlaylistPorNome(nomePlaylistDeletar);
				Playlist.deletarPlaylist(selecionarPlaylistDeletar);

				break;
			case 4:
				System.out.println("Digite o nome da playlist a ser editada");

				String nomePlaylistEditar = entrada.nextLine();
				Playlist selecionarPlaylistEditar = Playlist.buscaPlaylistPorNome(nomePlaylistEditar);

				if (selecionarPlaylistEditar == null) {
					System.out.println("Não existe playlist com esse nome");
				} else {
					submenuPlaylistEditar(selecionarPlaylistEditar);
				}
				break;
			case 5:
				System.out.println("Digite o nome da playlist que deseja salvar:");
				String nomePlaylistSalvar = entrada.nextLine();
				Playlist playlistSalvar = Playlist.buscaPlaylistPorNome(nomePlaylistSalvar);

				if (playlistSalvar != null) {
					salvarPlaylistEmArquivo(playlistSalvar);
				} else {
					System.out.println("Playlist não encontrada.");
				}
				break;
			case 6:
				menu();
				break;

		}

		System.out.print("\n");
		System.out.println("Insira qualquer número para voltar ao menu anterior");

		entrada.nextDouble();
		menuPlaylist();
		entrada.close();

	}

	public static void submenuPlaylistEditar(Playlist playlist) {
		Scanner entrada = new Scanner(System.in);
		int escolha;

		do {
			System.out.println("\nSubmenu Playlist");
			System.out.println("-> Escolha uma opção");
			System.out.println("[1] - Consultar playlist");
			System.out.println("[2] - Adicionar música");
			System.out.println("[3] - Remover música");
			System.out.println("[4] - Editar nome playlist");
			System.out.println("[5] - Ordenar músicas na playlist");
			System.out.println("[6] - Voltar ao menu anterior");

			escolha = entrada.nextInt();
			entrada.nextLine(); 

			switch (escolha) {
				case 1:
					playlist.exibirPlaylist();
					break;
				case 2:
					System.out.println("Digite o nome da música:");
					String nomeMusicaAdd = entrada.nextLine();
					Musica musicaAdd = Musica.buscaPorTituloM(nomeMusicaAdd);

					if (musicaAdd != null) {
						playlist.addMusica(musicaAdd);
						salvarPlaylistEmArquivo(playlist); 
						System.out.println("Música adicionada com sucesso!");
					} else {
						System.out.println("Essa música não está cadastrada no sistema.");
					}
					break;

				case 3:
					System.out.println("Digite o nome da música:");
					String nomeMusicaRem = entrada.nextLine();
					Musica musicaRem = Musica.buscaPorTituloM(nomeMusicaRem);

					if (musicaRem != null) {
						playlist.removerMusica(musicaRem);
						salvarPlaylistEmArquivo(playlist); 
						System.out.println("Música removida com sucesso!");
					} else {
						System.out.println("Essa música não está cadastrada no sistema.");
					}
					break;

				case 4:
					System.out.println("Digite o novo nome da playlist:");
					String novoNome = entrada.nextLine();
					playlist.setNome(novoNome);
					salvarPlaylistEmArquivo(playlist); 
					System.out.println("Nome atualizado com sucesso!");
					break;

				case 5:
					System.out.println("Escolha o critério de ordenação:");
					System.out.println("[1] - Título");
					System.out.println("[2] - Artista");
					System.out.println("[3] - Duração");
					int criterio = entrada.nextInt();
					entrada.nextLine();

					switch (criterio) {
						case 1:
							playlist.ordenarPorTitulo();
							break;
						case 2:
							playlist.ordenarPorArtista();
							break;
						case 3:
							playlist.ordenarPorDuracao();
							break;
						default:
							System.out.println("Opção inválida.");
							break;
					}
					salvarPlaylistEmArquivo(playlist); 
					System.out.println("Playlist ordenada com sucesso!");
					playlist.exibirPlaylist();
					break;

				case 6:
					System.out.println("Voltando ao menu anterior...");
					menuPlaylist();

					break;
				default:
					System.out.println("Opção inválida. Tente novamente.");
			}
		} while (escolha != 5);

		System.out.print("\n");
		System.out.println("Insira qualquer número para voltar ao menu anterior");

		entrada.nextDouble();
		submenuPlaylistEditar(playlist);
		entrada.close();

	}

	public static void menuAlbum() {
		Scanner entrada = new Scanner(System.in);

		System.out.println("Menu Album");
		System.out.println("-> Escolha uma opção");
		System.out.println("[1] - Ver albuns dísponíveis");
		System.out.println("[2] - Criar album");
		System.out.println("[3] - Deletar album");
		System.out.println("[4] - Editar album");
		System.out.println("[5] - Voltar ao menu anterior");

		int escolha = entrada.nextInt();
		entrada.nextLine();

		switch (escolha) {
			case 1:
				Album.exibirTodosAlbums();
				break;
			case 2:
				System.out.println("Digite o nome para seu novo album: ");
				String nomeAlbumCriar = entrada.nextLine();

				Album novoAlbum = new Album(nomeAlbumCriar);

				System.out.println("\n Album " + novoAlbum.getNome() + "Criado com sucesso");
				break;
			case 3:
				System.out.println("Digite o nome do Album a ser deletado: ");

				String nomeAlbumDeletar = entrada.nextLine();
				Album selecionarAlbumDeletar = Album.buscaAlbumPorNome(nomeAlbumDeletar);
				Album.deletarAlbum(selecionarAlbumDeletar);
				break;
			case 4:
				System.out.println("Digite o nome do Album a ser editado: ");

				String nomeAlbumEditar = entrada.nextLine();
				Album selecionarAlbumEditar = Album.buscaAlbumPorNome(nomeAlbumEditar);

				if (selecionarAlbumEditar == null) {
					System.out.println("Não existe playlist com esse nome");
				} else {
					submenuAlbumEditar(selecionarAlbumEditar);
				}
				break;
			case 5:
				menu();
				break;
		}
		System.out.print("\n");
		System.out.println("Insira qualquer número para voltar ao menu anterior");

		entrada.nextDouble();

		menuAlbum();
		entrada.close();
	}

	public static void submenuAlbumEditar(Album album) {
		Scanner entrada = new Scanner(System.in);

		System.out.println("Submenu Album");
		System.out.println("-> Escolha uma opção");
		System.out.println("[1] - Consultar album");
		System.out.println("[2] - Adicionar música");
		System.out.println("[3] - Remover música");
		System.out.println("[4] - Editar nome album");
		System.out.println("[5] - Voltar ao menu anterior");

		int escolha = entrada.nextInt();
		entrada.nextLine();

		switch (escolha) {
			case 1:
				album.exibirAlbum();
				break;
			case 2:
				System.out.println("Digite o nome da música");

				String nomeMusicaAdd = entrada.nextLine();
				Musica musicaAdd = Musica.buscaPorTituloM(nomeMusicaAdd);

				if (musicaAdd != null) {
					System.out.println("Musica adicionada com Sucesso");
					album.addMusica(nomeMusicaAdd);
				} else {
					System.out.println("Essa música não está cadastrada no sistema");
				}
				break;
			case 3:
				System.out.println("Digite o nome da música");

				String nomeMusicaRem = entrada.nextLine();
				Musica musicaRem = Musica.buscaPorTituloM(nomeMusicaRem);

				if (musicaRem != null) {
					album.removerMusica(musicaRem);
				} else {
					System.out.println("Essa música não está cadastrada no sistema ");
				}
				break;
			case 4:
				System.out.println("Digite o novo nome do album");

				String novoNome = entrada.nextLine();
				album.setNome(novoNome);
				break;
			case 5:
				menuAlbum();
				break;
		}

		System.out.print("\n");
		System.out.println("Insira qualquer número para voltar ao menu anterior");

		entrada.nextDouble();
		submenuAlbumEditar(album);
		;
		entrada.close();

	}

	public static void salvarPlaylistEmArquivo(Playlist playlist) {
		String caminho = "playlists/" + playlist.getNome() + ".txt";

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminho))) {
			writer.write("Playlist: " + playlist.getNome());
			writer.newLine();
			writer.write("--------------------------------------------");
			writer.newLine();

			for (Musica musica : playlist.getMusicas()) {
				writer.write("Nome: " + musica.getTitulo());
				writer.newLine();
				writer.write("Gênero: " + musica.getGenero());
				writer.newLine();
				writer.write("Ano de lançamento: " + musica.getAnoLancamento());
				writer.newLine();
				writer.write("Duração: " + musica.getDuracao() + " min");
				writer.newLine();
				writer.write("Artista: " + musica.getArtista().getNome());
				writer.newLine();
				writer.write("--------------------------------------------");
				writer.newLine();
			}

			System.out.println("Playlist salva no arquivo: " + caminho);

		} catch (IOException e) {
			System.out.println("Erro ao salvar a playlist: " + e.getMessage());
		}
	}

	public static void carregarPlaylistsDeArquivos() {
		File pasta = new File("playlists");

		if (!pasta.exists() || !pasta.isDirectory()) {
			System.out.println("Pasta 'playlists' não encontrada.");
			return;
		}

		File[] arquivos = pasta.listFiles((dir, name) -> name.toLowerCase().endsWith(".txt"));
		if (arquivos == null || arquivos.length == 0) {
			System.out.println("Nenhuma playlist salva encontrada.");
			return;
		}

		for (File arquivo : arquivos) {
			try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
				String linhaNome = reader.readLine();
				if (linhaNome == null || !linhaNome.startsWith("Playlist: ")) {
					System.out.println("Formato inválido no arquivo: " + arquivo.getName());
					continue;
				}
				String nomePlaylist = linhaNome.replace("Playlist: ", "").trim();

				Playlist playlist = new Playlist(nomePlaylist);

				String linha;
				String titulo = null, genero = null, artista = null;
				int ano = 0;
				double duracao = 0.0;

				while ((linha = reader.readLine()) != null) {
					if (linha.startsWith("Nome: ")) {
						titulo = linha.replace("Nome: ", "").trim();
					} else if (linha.startsWith("Gênero: ")) {
						genero = linha.replace("Gênero: ", "").trim();
					} else if (linha.startsWith("Ano de lançamento: ")) {
						ano = Integer.parseInt(linha.replace("Ano de lançamento: ", "").trim());
					} else if (linha.startsWith("Duração: ")) {
						duracao = Double.parseDouble(linha.replace("Duração: ", "").replace(" min", "").trim());
					} else if (linha.startsWith("Artista: ")) {
						artista = linha.replace("Artista: ", "").trim();

					
						Artista objArtista = Artista.criarAtista(artista);
						Musica musica = new Musica(titulo, objArtista, duracao, genero, ano);
						playlist.addMusica(musica);
					}
				}

			
				Playlist.adicionarPlaylist(playlist);
				System.out.println("\nPlaylists carregadas:  " + playlist.getNome());

			} catch (IOException | NumberFormatException e) {
				System.out.println("Erro ao ler playlist do arquivo " + arquivo.getName() + ": " + e.getMessage());
			}
		}
	}

	public static void salvarMusicaEmArquivo(Musica musica) {
		File pasta = new File("musicas");
		if (!pasta.exists()) {
			pasta.mkdir(); 
		}

		File arquivo = new File(pasta, "musicas.txt");

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(arquivo, true))) {
			writer.write("Nome: " + musica.getTitulo());
			writer.newLine();
			writer.write("Gênero: " + musica.getGenero());
			writer.newLine();
			writer.write("Ano de lançamento: " + musica.getAnoLancamento());
			writer.newLine();
			writer.write("Duração: " + musica.getDuracao() + " min");
			writer.newLine();
			writer.write("Artista: " + musica.getArtista().getNome());
			writer.newLine();
			writer.write("--------------------------------------------");
			writer.newLine();
		} catch (IOException e) {
			System.out.println("Erro ao salvar música: " + e.getMessage());
		}
	}

	public static void carregarMusicasDeArquivo() {
		File arquivo = new File("musicas/musicas.txt");

		try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
			String linha;
			String titulo = null, genero = null, artista = null;
			int ano = 0;
			double duracao = 0.0;

			while ((linha = reader.readLine()) != null) {
				if (linha.startsWith("Nome: ")) {
					titulo = linha.replace("Nome: ", "").trim();
				} else if (linha.startsWith("Gênero: ")) {
					genero = linha.replace("Gênero: ", "").trim();
				} else if (linha.startsWith("Ano de lançamento: ")) {
					ano = Integer.parseInt(linha.replace("Ano de lançamento: ", "").trim());
				} else if (linha.startsWith("Duração: ")) {
					duracao = Double.parseDouble(linha.replace("Duração: ", "").replace(" min", "").trim());
				} else if (linha.startsWith("Artista: ")) {
					artista = linha.replace("Artista: ", "").trim();

					
					Artista objArtista = Artista.criarAtista(artista);
					Musica musica = new Musica(titulo, objArtista, duracao, genero, ano);
				}
			}

			System.out.println("Músicas carregadas com sucesso!\n");
		} catch (IOException | NumberFormatException e) {
			System.out.println("Erro ao carregar músicas: " + e.getMessage());
		}
	}

}
