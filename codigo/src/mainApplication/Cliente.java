package mainApplication;

public class Cliente {

	private String nome;
	private String cpf;
	private ListaJogos historicoCompras[];
	private double valorComprado;
	private IFidelidade classificacao;

	Cliente() {
		nome = null;
		cpf = null;
		historicoCompras = new ListaJogos[500];
		valorComprado = 0;
		classificacao = null;

		this.atualizaFidelidade();
	}

	Cliente(String nome, String cpf) {
		this.nome = nome;
		this.cpf = cpf;
		historicoCompras = new ListaJogos[500];
		valorComprado = 0;
		classificacao = null;

		this.atualizaFidelidade();
	}

	public void setNome(String name) {
		nome = name;
	}

	public String getNome() {
		return nome;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getCpf() {
		return cpf;
	}

	public void setHistoricoDeCompras(ListaJogos vet[]) {
		historicoCompras = vet;
	}

	public ListaJogos[] getHistoricoCompras() {
		return historicoCompras;
	}

	public void setValorComprado(double valorGasto) {
		valorComprado = valorGasto;
	}

	public double getValorComprado() {
		return valorComprado;
	}

	public boolean adicionarPedido(ListaJogos pedidos) {
		boolean ok = false;

		for (int i = 0; i < historicoCompras.length; i++) {
			if (historicoCompras[i] == null && ok == false) {
				historicoCompras[i] = pedidos;

				ok = true;
			}
		}
		return ok;
	}

	/**
	 * Esse método depende da chamada, prévia, do método "atualizaFidelidade".
	 * 
	 * @return retorna o percentual do desconto que o cliente tem direito de acordo
	 *         com sua categoria.
	 */
	public double checaFidelidade() {
		atualizaFidelidade();
		return classificacao.checaFidelidade();
	}

	/**
	 * Esse m�todo atualiza a classe de fidelidade com base no hist�rico de compras.
	 * Justamente por ser tão relevante, ele � chamado inclusive no construtor dessa
	 * classe.
	 *
	 */
	public void atualizaFidelidade() {
		double valorGastoEmCompras = 0.0;

		for (int i = 0; i < this.historicoCompras.length; i++) {
			ListaJogos carrinhoAtual;
			carrinhoAtual = historicoCompras[i];

			if (carrinhoAtual != null) {
				Jogo vetorDeJogos[];
				vetorDeJogos = carrinhoAtual.getJogos();

				for (int j = 0; j < vetorDeJogos.length; j++) {
					Jogo jogoAtual;
					jogoAtual = vetorDeJogos[j];

					if (jogoAtual != null) {
						valorGastoEmCompras = valorGastoEmCompras + jogoAtual.getPreco();
					}
				}
			}

		}

		if (valorGastoEmCompras < 600.00) {
			// regular
			this.classificacao = new Regular();
		} else if (valorGastoEmCompras >= 600.00 && valorGastoEmCompras < 1200.00) {
			// prata
			this.classificacao = new Prata();
		} else if (valorGastoEmCompras >= 1200.00 && valorGastoEmCompras < 2000.00) {
			// ouro
			this.classificacao = new Ouro();
		} else if (valorGastoEmCompras >= 2000.00) {
			// diamante
			this.classificacao = new Diamante();
		}
	}

	@Override
	public String toString() {
		String s;
		s = String.format("Nome: %s\nCPF: %s\nHistorico: %s\nValor Comprado: %.2f", nome, cpf,
				historicoCompras.toString(), valorComprado);
		return s;

	}

}
