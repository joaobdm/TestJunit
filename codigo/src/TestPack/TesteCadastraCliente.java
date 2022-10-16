package TestPack;
import mainApplication.*;
import org.junit.Assert;
import org.junit.Test;

public class TesteCadastraCliente {
    
	
	@Test
	public void testCadastraClient() {		
		
		
		Loja loja = new Loja(new Data(01, 01, 2021));
		Assert.assertTrue(loja.cadastraCliente("Jonatan", "12345"));
	}
	
	
	@Test
	public void testCadastraClient2() {		
		
		
		Loja loja = new Loja(new Data(01, 01, 2021));		
		Assert.assertTrue(loja.cadastraCliente("", ""));
	}
	
	@Test
	public void testCadastraClient3() {		
		
		
		Loja loja = new Loja(new Data(01, 01, 2021));		
		Assert.assertTrue(loja.cadastraCliente("Jonatan", ""));
	}
	
	@Test
	public void testCadastraClient4() {		
		
		
		Loja loja = new Loja(new Data(01, 01, 2021));		
		Assert.assertTrue(loja.cadastraCliente("", "1234566"));
	}
    
}
