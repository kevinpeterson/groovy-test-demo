package demo.order;

import java.math.BigDecimal;

public class DefaultTaxCalculator implements TaxCalculator {

    public BigDecimal addTax(BigDecimal price){
        return new BigDecimal(0.08).multiply(price).add(price);
    }

}
