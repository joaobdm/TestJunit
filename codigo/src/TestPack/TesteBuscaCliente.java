package TestPack;
import mainApplication.*;
import org.junit.Assert;
import org.junit.Test;

public class TesteBuscaCliente {
    
	
	@Test
	public void testCadastraCliente() {		
		
		
		Loja loja = new Loja(new Data(01, 01, 2021));
		loja.cadastraCliente("Jonatan", "12345");
		Cliente clienteEsperado = loja.buscaCliente("12345");
		Cliente clienteDeTeste = loja.buscaCliente("34563456");
		Assert.assertEquals(clienteDeTeste, clienteEsperado);
	}
	
	
	@Test
	public void testCadastraCliente2() {		
		
		
		Loja loja = new Loja(new Data(01, 01, 2021));
		loja.cadastraCliente("Jonatan", "12345");
		Cliente clienteEsperado = loja.buscaCliente("12345");
		Cliente clienteDeTeste = loja.buscaCliente("12345");
		Assert.assertEquals(clienteDeTeste, clienteEsperado);
	}
    
	@Test
	public void testCadastraCliente3() {		
		
		
		Loja loja = new Loja(new Data(01, 01, 2021));
		loja.cadastraCliente("Jonatan", "12345");
		Cliente clienteEsperado = loja.buscaCliente("12345");
		Cliente clienteDeTeste = loja.buscaCliente("");
		Assert.assertEquals(clienteDeTeste, clienteEsperado);
	}
}
