package mainApplication;
public class Playstation extends Jogo {

    private static final double MULTIPLICADORPS = 1.3;

    Playstation() {
        super.ajustaPreco(this.preco * MULTIPLICADORPS);
        super.plataforma = "Playstation";
    }

    Playstation(String nome, double preco, Data data) {
        super(nome, preco, data);
        super.ajustaPreco(this.preco * MULTIPLICADORPS);
        super.plataforma = "Playstation";
    }

}
