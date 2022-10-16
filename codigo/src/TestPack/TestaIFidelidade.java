package TestPack;
import mainApplication.*;
import org.junit.Assert;
import org.junit.Test;

public class TestaIFidelidade {
	@Test
	public void testaOuro() throws Exception {
		Loja loja = new Loja(new Data(01, 01, 2021));
		loja.cadastraCliente("Jonatan", "12345");
		Cliente cliente = loja.buscaCliente("12345");
		loja.adicionaAoCarrinho("Black", "Xbox");
		loja.adicionaAoCarrinho("The Crew", "Xbox");
		loja.adicionaAoCarrinho("Bloodborne", "Playstation");
		loja.adicionaAoCarrinho("Hollow Knight", "Computador");
		loja.adicionaAoCarrinho("Dead By Daylight", "Xbox");
		loja.adicionaAoCarrinho("Forza Motorsport 6", "Xbox");
		loja.adicionaAoCarrinho("Forza Motorsport 7", "Xbox");
		loja.adicionaAoCarrinho("Mortal Kombat 11", "Playstation");
		loja.adicionaAoCarrinho("Devil May Cry 5", "Xbox");
		loja.fechaPedido(cliente);
		Assert.assertEquals(0.2, cliente.checaFidelidade(), 0.0001);		
	}
	
	@Test
	public void testaPrata() throws Exception{
		Loja loja = new Loja(new Data(01, 01, 2021));
		loja.cadastraCliente("Jonatan", "12345");
		Cliente cliente = loja.buscaCliente("12345");
		loja.adicionaAoCarrinho("Black", "Xbox");
		loja.adicionaAoCarrinho("The Crew", "Xbox");
		loja.adicionaAoCarrinho("Bloodborne", "Playstation");
		loja.adicionaAoCarrinho("Hollow Knight", "Computador");
		loja.adicionaAoCarrinho("Dead By Daylight", "Xbox");
		loja.adicionaAoCarrinho("Forza Motorsport 6", "Xbox");
		loja.adicionaAoCarrinho("Forza Motorsport 7", "Xbox");
		loja.fechaPedido(cliente);
		Assert.assertEquals(0.1, cliente.checaFidelidade(), 0.0001);		
	}
	
	@Test
	public void testaDiamante() throws Exception{
		Loja loja = new Loja(new Data(01, 01, 2021));
		loja.cadastraCliente("Jonatan", "12345");
		Cliente cliente = loja.buscaCliente("12345");
		loja.adicionaAoCarrinho("Black", "Xbox");
		loja.adicionaAoCarrinho("The Crew", "Xbox");
		loja.adicionaAoCarrinho("Bloodborne", "Playstation");
		loja.adicionaAoCarrinho("Hollow Knight", "Computador");
		loja.adicionaAoCarrinho("Dead By Daylight", "Xbox");
		loja.adicionaAoCarrinho("Forza Motorsport 6", "Xbox");
		loja.adicionaAoCarrinho("Forza Motorsport 7", "Xbox");
		loja.fechaPedido(cliente);
		Assert.assertEquals(0.3, cliente.checaFidelidade(), 0.0001);		
	}
}
