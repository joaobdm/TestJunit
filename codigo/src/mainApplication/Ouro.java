package mainApplication;
public class Ouro implements IFidelidade{
	
	private static final double PERCENTUALDESCONTO = 0.2;
	
    @Override
    public double checaFidelidade() 
    {
        return PERCENTUALDESCONTO;
    }
}