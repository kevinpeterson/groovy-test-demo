package demo.order;

import java.math.BigDecimal;

public interface TaxCalculator {

    public BigDecimal addTax(BigDecimal price);

}
