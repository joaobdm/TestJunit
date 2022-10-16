package TestPack;
import mainApplication.*;
import org.junit.Assert;
import org.junit.Test;
public class TesteBuscaJogo {
	
	@Test
	public void testBuscaJogo1() {
		Loja loja = new Loja(new Data(01, 01, 2021));		
		Assert.assertTrue(loja.buscaJogo("Bla", "Xintendo"));
	}
	
	
	@Test
	public void testBuscaJogo2() {
		Loja loja = new Loja(new Data(01, 01, 2021));		
		Assert.assertTrue(loja.buscaJogo("Bla", "Xbox"));
	}
	
	@Test
	public void testBuscaJogo3() {
		Loja loja = new Loja(new Data(01, 01, 2021));		
		Assert.assertTrue(loja.buscaJogo("Black", "XBox"));
	}
	
	@Test
	public void testBuscaJogo4() {
		Loja loja = new Loja(new Data(01, 01, 2021));		
		Assert.assertTrue(loja.buscaJogo("black", "Xbox"));
	}
	
	@Test
	public void testBuscaJogo5() {
		Loja loja = new Loja(new Data(01, 01, 2021));		
		Assert.assertTrue(loja.buscaJogo("Black", "Xintendo"));
	}

}
