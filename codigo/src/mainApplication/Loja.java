package mainApplication;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Loja {

	private ArrayList<Cliente> clientesCadastrados;
	private ListaJogos bibliotecaJogos;
	private ListaJogos carrinhoCompras;
	private Data dataDeHoje;

	public Loja(Data hoje) {

		clientesCadastrados = new ArrayList<Cliente>();

		try {
			carregaBiblioteca();
		} catch (FileNotFoundException e) {
			System.out.println("Arquivo contendo jogos disponíveis na loja não encontrado");
		} catch (NumberFormatException e0) {
			System.out.println("Conteúdo do arquivo não condiz com o esperado: Formatação de número");
		} catch (NullPointerException e1) {
			System.out.println("Conteúdo do arquivo não condiz com o esperado");
		} catch (Exception e2) {
			System.out.println("Ocorreu um erro inesperado");
		}

		dataDeHoje = hoje;

		esvaziaCarrinho();
	}

	/**
	 * Esse método calcula desconto de uma determinada compra. Para isso, ele soma
	 * todos os valores dos jogos do carrinho. Logo ap�s, multimplica pelo desconto
	 * referente a categoria do cliente.
	 * 
	 * @param cliente - cliente que est� realizando a compra.
	 * @return valor me reais do desconto da compra.
	 */
	private double calculaDesconto(Cliente cliente) {
		double descontoCategoria = cliente.checaFidelidade();

		double descontoProdutos;

		descontoProdutos = carrinhoCompras.precoTotal() * descontoCategoria;

		return descontoProdutos;
	}

	public boolean cadastraCliente(String nome, String cpf) {
		if (cpf.equals("")) {
			System.out.println("O CPF informado não pode estar em branco");
			return false;
		}
			
		else if (!clienteJaCadastrado(cpf)) {
			Cliente novoCliente = new Cliente(nome, cpf);
			clientesCadastrados.add(novoCliente);
			System.out.println("Cliente cadastrado com sucesso");
			return true;
		} else
			System.out.println("CPF já consta no banco de cadastro");
		return false;
	}

	public void cadastraCliente() throws IOException {
		System.out.print("Digite o nome do cliente que deseja cadastrar: ");
		String nome = teclado();
		System.out.print("\nDigite o CPF do cliente que deseja cadastrar: ");
		String cpf = teclado();
		System.out.println();
		cadastraCliente(nome, cpf);
	}

	public Cliente buscaCliente(String cpf) {

		for (int i = 0; i < clientesCadastrados.size(); i++) {
			if (clientesCadastrados.get(i).getCpf().equals(cpf)) {
				return clientesCadastrados.get(i);
			}
		}

		System.out.println("Cliente não encontrado");
		return null;

	}

	public void esvaziaCarrinho() {
		carrinhoCompras = new ListaJogos(100);
	}

	public void fechaPedido(Cliente clienteAtual) {
		double descontoNaCompraAtual = 0;

		try {
			clienteAtual.adicionarPedido(carrinhoCompras);

			descontoNaCompraAtual = this.calculaDesconto(clienteAtual);

			double valorPagar;
			valorPagar = carrinhoCompras.precoTotal() - descontoNaCompraAtual;

			System.out.printf("Valor a pagar: %.2f", valorPagar);
			System.out.println();
			System.out.printf("Seu desconto nessa compra foi de: %.2f", descontoNaCompraAtual);
			System.out.println();
			System.out.println("Obrigado!");

			clienteAtual.atualizaFidelidade();

			this.esvaziaCarrinho();
		} catch (NullPointerException e0) {
			System.out.println("Pedido não pode ser fechado sem um cliente");
		}
	}

	public void fechaPedido() throws IOException {
		if (carrinhoCompras.precoTotal() == 0) {
			System.out.println("Pedido não pode ser fechado com um carrinho vazio");
		} else {
			Cliente clienteAtual;
			System.out.print("Digite o CPF do cliente (Somente números): ");
			String cpf = teclado();
			clienteAtual = buscaCliente(cpf);
			fechaPedido(clienteAtual);
		}
	}

	public boolean buscaJogo(String nome, String plataforma) {

		return bibliotecaJogos.existeJogo(nome, plataforma);

	}

	public void buscaJogo() throws IOException {
		System.out.print("Digite o nome do jogo que deseja buscar: \n");
		String plataforma = "", nome = teclado();
		System.out.println("\nSelecione a plataforma desejada: ");
		System.out.println("[1] Computador");
		System.out.println("[2] Playstation");
		System.out.println("[3] XBox");
		int opcao = Integer.parseInt(teclado());
		boolean found = false;
		switch (opcao) {
			case 1:
				plataforma = "Computador";
				break;
			case 2:
				plataforma = "Playstation";
				break;
			case 3:
				plataforma = "XBox";
				break;

			default:
				break;
		}
		found = buscaJogo(nome, plataforma);
		if (found) {
			System.out.println("Jogo encontrado !");
			System.out.println("Deseja adicionar ao carrinho?");
			System.out.println("[1] Sim");
			System.out.println("[2] Não");
			opcao = Integer.parseInt(teclado());
			switch (opcao) {
				case 1:
					try {
						adicionaAoCarrinho(nome, plataforma);
					} catch (Exception e) {
						e.printStackTrace();
					}
					break;

				default:
					System.out.println("Nada foi adicionado ao carrinho");
					break;
			}
		} else
			System.out.println("Infelizmente não temos esse jogo :(");
	}

	public void adicionaAoCarrinho(String jogoEscolhido, String plataforma) throws Exception {
		try {
			Jogo aux = cloneJogoComDesconto(bibliotecaJogos.buscaJogo(jogoEscolhido, plataforma));

			carrinhoCompras.adicionarJogo(aux);

		} catch (NullPointerException e0) {
			System.out.println("Jogo buscado para plataforma escolhida não foi encontrado");
		} catch (ArrayIndexOutOfBoundsException e1) {
			System.out.println("Carrinho de compras cheio");
		}

	}

	private Jogo cloneJogoComDesconto(Jogo j) {
		Jogo copia;
		if (j.getPlataforma().equals("Computador")) {
			copia = new Computador(j.getNome(), j.getPrecoBase(), j.getDataDeLanc());
		} else if (j.getPlataforma().equals("Playstation")) {
			copia = new Playstation(j.getNome(), j.getPrecoBase(), j.getDataDeLanc());
		} else {
			copia = new Xbox(j.getNome(), j.getPrecoBase(), j.getDataDeLanc());
		}

		copia.setPreco(copia.getPreco() * descontData(copia));
		return copia;

	}

	private boolean clienteJaCadastrado(String cpf) {
		boolean resp = false;

		for (int i = 0; i < clientesCadastrados.size(); i++) {
			if (clientesCadastrados.get(i).getCpf().equals(cpf)) {
				resp = true;
				return resp;
			}
		}
		return resp;
	}

	private void carregaBiblioteca() throws Exception {

		BufferedReader reader = new BufferedReader(new FileReader("codigo/gamesLibrary.csv"));
		String aux;
		String[] divided, dateString;
		Jogo jogo;
		Data dataLanc;
		int tamanho = Integer.parseInt(reader.readLine());
		bibliotecaJogos = new ListaJogos(tamanho);

		for (int i = 0; i < tamanho; i++) {

			aux = reader.readLine();
			divided = aux.split(",");

			dateString = divided[3].split("-");
			dataLanc = new Data(Integer.parseInt(dateString[0]), Integer.parseInt(dateString[1]),
					Integer.parseInt(dateString[2]));

			if (divided[2].equals("Computador"))
				jogo = new Computador(divided[0], Double.parseDouble(divided[1]), dataLanc);
			else if (divided[2].equals("Playstation"))
				jogo = new Playstation(divided[0], Double.parseDouble(divided[1]), dataLanc);
			else
				jogo = new Xbox(divided[0], Double.parseDouble(divided[1]), dataLanc);

			bibliotecaJogos.adicionarJogo(jogo);
		}

		reader.close();

	}

	private int comparaData(Jogo j) {
		return dataDeHoje.diferencaDias(j.getDataDeLanc());
	}

	private double descontData(Jogo j) {

		int diferencaDias = comparaData(j);

		if (diferencaDias < 365) {
			return 1;
		}

		if (diferencaDias > 365 && diferencaDias < 730) {
			return 0.85;
		}

		else {
			return 0.75;
		}
	}

	public void menuInterativo() {
		System.out.println("\n@@@@@@@@@@ PRAISEGABEN GAMESTORE @@@@@@@@@@\n");
		System.out.println(
				"⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\n⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠛⠉⠉⠄⠄⠄⠉⠉⠛⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿\n⣿⣿⣿⣿⣿⣿⣿⣿⣿⠏⠄⢀⣠⣶⣶⣶⣶⣤⡀⠄⠄⠹⣿⣿⣿⣿⣿⣿⣿⣿\n⣿⣿⣿⣿⣿⣿⣿⣿⡏⠄⠄⣾⣿⢿⣿⣿⡿⢿⣿⡆⠄⠄⢻⣿⣿⣿⣿⣿⣿⣿\n⣿⣿⣿⣿⣿⣿⣿⡿⠃⠄⠄⢿⣇⣸⣿⣿⣇⣸⡿⠃⠄⠄⠸⣿⣿⣿⣿⣿⣿⣿\n⣿⣿⣿⣿⣿⡿⠋⠄⠄⠄⠄⠄⠉⠛⠛⠛⠛⠉⠄⠄⠄⠄⠄⠄⠙⣿⣿⣿⣿⣿\n⣿⣿⣿⣿⡟⠁⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠈⢿⣿⣿⣿\n⣿⣿⣿⡟⠄⠄⠄⠠⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠈⢿⣿⣿\n⣿⣿⡟⠄⠄⠄⢠⣆⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⣧⠄⠄⠄⠈⢿⣿\n⣿⣿⡇⠄⠄⠄⣾⣿⡀⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⢰⣿⣧⠄⠄⠄⠘⣿\n⣿⣿⣇⠄⣰⣶⣿⣿⣿⣦⣀⡀⠄⠄⠄⠄⠄⠄⠄⢀⣠⣴⣿⣿⣿⣶⣆⠄⢀⣿\n⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠏⠄⠄⢸⣿⠇⠄⠄⠹⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\n⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣦⣤⣴⣾⣿⣶⣤⣤⣴⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\n⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\n");
		int opcao = 0;
		try {
			do {
				imprimeMenu();
				opcao = Integer.parseInt(teclado());
				switch (opcao) {
					case 0:
						System.out.println("Obrigado e volte sempre !!! :D");
						break;
					case 1:
						cadastraCliente();
						break;
					case 2:
						esvaziaCarrinho();
						System.out.println("Vamos começar as compras !");
						break;
					case 3:
						buscaJogo();
						break;
					case 4:
						mostraCarrinho();
						break;
					case 5:
						fechaPedido();
						break;
					case 6:
						bibliotecaJogos.ordenaPorPlatf();
						mostraLojaCompleta();
						break;
					default:
						System.out.println("Opção deve ser um número de 0-5");
						break;
				}

			} while (opcao != 0);
		} catch (NumberFormatException nfe) {
			System.out.println("Escolha um número de opção VÁLIDO !");
			menuInterativo();
		} catch (Exception e) {
		}
	}

	private void imprimeMenu() {
		System.out.println("\n########## MENU DA LOJA ##########\n");
		System.out.println("Selecione a opção desejada:");
		System.out.println("[1] Cadastrar Cliente");
		System.out.println("[2] Esvaziar Carrinho");
		System.out.println("[3] Buscar Jogo na biblioteca");
		System.out.println("[4] Mostrar carrinho");
		System.out.println("[5] Fechar Pedido");
		System.out.println("[6] Mostrar todos os jogos");
		System.out.println("[0] Sair");
	}

	private String teclado() throws IOException {
		String s = new String();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		s = br.readLine();
		return s;
	}

	public void mostraLojaCompleta() {
		bibliotecaJogos.toString();
	}

	public void mostraCarrinho() {
		if (carrinhoCompras.getJogos()[0] == null)
			System.out.println("Carrinho está vazio ! Deixe de ser pão duro");
		else
			carrinhoCompras.toString();
	}
}
