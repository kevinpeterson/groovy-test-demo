package demo.order

import org.junit.Test
import static org.junit.Assert.*

class OrderFormTest {

    @Test
    void "Test something not very interesting"(){
        def orderForm = new OrderForm()

        assertNotNull orderForm
    }

    @Test
    void "Test check item size"(){
        def orderForm = new OrderForm(items:[new OrderForm.Item()])

        assertEquals 1, orderForm.items.size
    }

    @Test
    void "Test check order total price -- need to mock something here!"(){
        def orderForm = new OrderForm(
                items:[
                    new OrderForm.Item(quantity:10, price:9.99)
                ]
        )

        assertEquals 99.90, orderForm.totalOrderPrice, 0.0
    }

    @Test
    void "Test check order total price with closure mock"(){
        def orderForm = new OrderForm(
                items:[
                        new OrderForm.Item(quantity:10, price:9.99),
                        new OrderForm.Item(quantity:1, price:0.10)
                ],
                taxCalculator:[
                    addTax: { price -> price }
                ] as TaxCalculator
        )

        assertEquals 100.00, orderForm.totalOrderPrice, 0.0
    }

    @Test
    void "Test check order form validate"(){

        //some conditions I want to check
        def checks = [
            {item -> assertNotNull item.price },
            {item -> assertTrue item.quantity > 0 }
        ]

        def orderForm = new OrderForm(
                items:[
                        new OrderForm.Item(quantity:10, price:9.99),
                        new OrderForm.Item(quantity:1, price:0.10)
                ]
        )

        //combine all items with all conditions
        [orderForm.items,checks].combinations().each {item,check->check(item)}

        //or use nested loops
        orderForm.items.each {item ->
            checks.each { check ->
                check(item)
            }
        }
    }
}
