package mainApplication;
public class Diamante implements IFidelidade {
	
	private static final double PERCENTUALDESCONTO = 0.3;

	@Override
	public double checaFidelidade() 
	{
		return PERCENTUALDESCONTO;
	}
}
