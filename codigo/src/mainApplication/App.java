package mainApplication;

public class App {
    public static void main(String[] args) throws Exception {

        Loja loja = new Loja(new Data(01, 01, 2021));

        loja.menuInterativo();

    }
}
