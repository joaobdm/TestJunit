package TestPack;
import mainApplication.*;
import org.junit.Assert;
import org.junit.Test;

public class TesteRemoverJogo {
	@Test
	public void testeRemoveJogo1(){
		ListaJogos listaJg = new ListaJogos(5);
		listaJg.adicionarJogo(new Computador("Overwatch", 250.00, new Data(01, 01, 2021)));
		Computador testeJogo = new Computador("Overwatch", 250.00, new Data(01, 01, 2021));
		Assert.assertEquals(testeJogo, listaJg.removerJogo(0));
	}
	
	@Test
	public void testeRemoveJogo2(){
		ListaJogos listaJg = new ListaJogos(5);
		listaJg.adicionarJogo(new Computador("Overwatch", 250.00, new Data(01, 01, 2021)));
		Computador testeJogo = new Computador("Overwatch", 251.00, new Data(01, 01, 2021));
		Assert.assertEquals(testeJogo, listaJg.removerJogo(0));
	}
}
