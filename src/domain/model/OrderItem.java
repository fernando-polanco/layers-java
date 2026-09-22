package domain.model;

import java.math.BigDecimal;

public record OrderItem(Product product, int quantity, BigDecimal unitPrice) {
    public BigDecimal subtotal() {
        return unitPrice.multiply(BigDecimal.valueOf(quantity));
    }
}
