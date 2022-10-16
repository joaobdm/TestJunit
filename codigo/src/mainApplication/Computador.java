package mainApplication;
public class Computador extends Jogo {

    private static final double MULTIPLICADORPC = 1;

    Computador(){
        super.ajustaPreco(this.preco*MULTIPLICADORPC);
        super.plataforma = "Computador";
    }

    public Computador(String nome, double preco, Data data) {
        super(nome, preco, data);
        super.ajustaPreco(this.preco*MULTIPLICADORPC);
        super.plataforma = "Computador";
    }

    
}
