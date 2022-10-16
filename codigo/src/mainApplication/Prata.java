package mainApplication;
public class Prata implements IFidelidade{

	
	private static final double PERCENTUALDESCONTO = 0.1;
	
    @Override
    public double checaFidelidade() 
    {
        return PERCENTUALDESCONTO;
    }
	
}
