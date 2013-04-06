package demo.order;

import java.math.BigDecimal;
import java.util.List;

public class OrderForm {

    private TaxCalculator taxCalculator = new DefaultTaxCalculator();

    private List<Item> items;

    public void addItem(Item item){
        items.add(item);
    }

    public BigDecimal getTotalOrderPrice(){
        BigDecimal total = new BigDecimal(0);
        for(Item item : items){
            total = item.getPrice().multiply(new BigDecimal(item.getQuantity())).add(total);
        }

        return taxCalculator.addTax(total);
    }

    public TaxCalculator getTaxCalculator() {
        return taxCalculator;
    }

    public void setTaxCalculator(TaxCalculator taxCalculator) {
        this.taxCalculator = taxCalculator;
    }

    public static class Item {
        private int quantity;
        private BigDecimal price;

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public BigDecimal getPrice() {
            return price;
        }

        public void setPrice(BigDecimal price) {
            this.price = price;
        }
    }
}
