package mainApplication;
import java.util.Arrays;
import java.util.Comparator;

public class ListaJogos {

    private int MAXJOGOS;
    private Jogo[] jogos;
    private StringBuilder sb;

    public Jogo[] getJogos() {
        return jogos;
    }

    public ListaJogos(int tamanho) {
        MAXJOGOS = tamanho;
        jogos = new Jogo[tamanho];
    }

    /**
     * Através do nome e plataforma, detecta se o jogo já existe no vetor em
     * questão.
     * 
     * @param nomeDoJogo
     * @param plataforma
     * @return
     */
    public boolean existeJogo(String nomeDoJogo, String plataforma) {

        for (int i = 0; i < jogos.length; i++) {

            if (jogos[i] == null)
                return false;
            if (jogos[i].getNome().toLowerCase().equals(nomeDoJogo.toLowerCase())
                    && jogos[i].getPlataforma().toLowerCase().equals(plataforma.toLowerCase())) {
                return true;
            }

        }
        return false;
    }

    /**
     * Método sobre carregado, retornando simplesmente se o jogo existe,
     * independente da plataforma
     */
    public boolean existeJogo(String nomeDoJogo) {
        if (existeJogo(nomeDoJogo, "Computador"))
            return true;
        if (existeJogo(nomeDoJogo, "Playstation"))
            return true;
        if (existeJogo(nomeDoJogo, "Xbox"))
            return true;
        else
            return false;
    }

    /**
     * Adiciona um Jogo j ao vetor caso já não exista o mesmo jogo nesse vetor.
     * 
     * @param j Jogo a ser inserido no vetor.
     * @return true se a operação foi feita com sucesso, false caso contrário.
     */
    public boolean adicionarJogo(Jogo j) {

        if (vetorVazio()) {
            this.jogos[0] = j;
            return true;
        }

        else if (!existeJogo(j.getNome(), j.getPlataforma()) && !vetorCheio()) {
            for (int i = 0; i < jogos.length; i++) {
                if (jogos[i] == null) {
                    jogos[i] = j;
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Remove um jogo do vetor e diminui uma posição de cada jogo posterior do
     * vetor, se houver.
     * 
     * @param posi Posição do vetor onde o jogo se encontra
     * @return Jogo removido
     */
    public Jogo removerJogo(int posi) {

        Jogo aux = jogos[posi];

        // "Zerando" a posição removida
        jogos[posi] = null;

        // Reordenando o vetor
        for (int i = posi + 1; i < jogos.length; i++) {
            this.jogos[posi] = this.jogos[i];
            posi++;
        }

        return aux;
    }

    private boolean vetorVazio() {

        if (this.jogos[0] == null)
            return true;

        else
            return false;
    }

    private boolean vetorCheio() {
        if (this.jogos[MAXJOGOS - 1] != null)
            return true;

        else
            return false;
    }

    public Jogo buscaJogo(String nomeDoJogo, String plataforma) throws Exception {

        Jogo buscado = null;

        try {
            for (int i = 0; i < jogos.length; i++) {

                if (jogos[i].getNome().toLowerCase().equals(nomeDoJogo.toLowerCase())
                        && jogos[i].getPlataforma().toLowerCase().equals(plataforma.toLowerCase())) {
                    buscado = jogos[i];
                }

            }
        } catch (Exception e0) {
        }
        return buscado;

    }

    public double precoTotal() {
        double preco = 0;

        for (int i = 0; i < jogos.length; i++) {
            if (jogos[i] != null) {
                preco += jogos[i].getPreco();
            } else
                break;
        }

        return preco;
    }

    /**
     * ordena o vetor em ordem alfabética primeiro comprarando as plataformas, se
     * ouver um "empate", o desempate será entre o nome do jogo
     * 
     * @param o1 jogo que é reorganizado
     * @param o2 jogo que é comparado para reorganização
     */
    public void ordenaPorPlatf() {
        Arrays.sort(this.jogos, new Comparator<Jogo>() {
            public int compare(Jogo o1, Jogo o2) {
                if (o1 == null || o2 == null)
                    return 0;

                if (o1.getPlataforma().equals(o2.getPlataforma())) {
                    String a = (String) o1.getNome();
                    String b = (String) o2.getNome();
                    return a.compareTo(b);
                } else {
                    String a = (String) o1.getPlataforma();
                    String b = (String) o2.getPlataforma();
                    return a.compareTo(b);
                }

            }
        });
    }

    /**
     * ordena do jogo mais velho ao mais novo (ainda não foi feito o metodo que irá
     * calcular o desconto de acordo com a "idade")
     */
    public void ordenaPorCond() {
        Arrays.sort(this.jogos, new Comparator<Jogo>() {
            public int compare(Jogo o1, Jogo o2) {
                if (o1 == null || o2 == null)
                    return 0;

                Integer a = (Integer) o1.getDataDeLanc().getAno();
                Integer b = (Integer) o2.getDataDeLanc().getAno();
                return a.compareTo(b);
            }
        });
    }

    @Override
    public String toString() {
        sb = new StringBuilder();
        for (int i = 0; i < jogos.length; i++) {
            if (jogos[i] != null) {
                sb.append(jogos[i].toString());
            }
        }

        return sb.toString();
    }

}
