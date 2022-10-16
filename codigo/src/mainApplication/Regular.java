package mainApplication;
public class Regular implements IFidelidade {
    
	  private static final double PERCENTUALDESCONTO = 0.05;

    @Override
    public double checaFidelidade() {
        return PERCENTUALDESCONTO;
    }
}
