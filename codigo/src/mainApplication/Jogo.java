package mainApplication;
public abstract class Jogo {

    protected String nome;
    protected double precoBase;
    protected double preco;
    protected String plataforma;
    protected Data dataDeLanc;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }

    public Data getDataDeLanc() {
        return dataDeLanc;
    }

    public void setDataDeLanc(Data dataDeLanc) {
        this.dataDeLanc = dataDeLanc;
    }

    public double getPrecoBase() {
        return precoBase;
    }

    Jogo() {
        nome = "";
        preco = 0.0;
        precoBase = 0.0;
        plataforma = "";
        dataDeLanc = new Data();
    }

    Jogo(String nome, double preco, Data data) {
        this.nome = nome;
        this.preco = preco;
        dataDeLanc = data;
        precoBase = preco;
    }

    @Override
    public String toString() {
        System.out.println("Nome: " + nome);
        System.out.printf("Preço: %.2f\n", preco);
        System.out.println("Plataforma: " + plataforma);
        System.out.println("Data Lançamento: " + dataDeLanc.toString() + "\n");
        return super.toString();
    }

    protected void ajustaPreco(double novoPreco) {
        this.preco = novoPreco;
    }
}
