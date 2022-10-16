package mainApplication;
public class Xbox extends Jogo {

    private static final double MULTIPLICADORXB = 1.2;

    Xbox(){
        super.ajustaPreco(this.preco*MULTIPLICADORXB);
        super.plataforma = "Xbox";
    }

    Xbox(String nome, double preco, Data data) {
        super(nome, preco, data);
        super.ajustaPreco(this.preco*MULTIPLICADORXB);
        super.plataforma = "Xbox";
    }

    
}
