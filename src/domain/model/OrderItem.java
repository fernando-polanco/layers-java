package domain.model;

import java.math.BigDecimal;

public record OrderItem(String productName, int quantity, BigDecimal unitPrice) {
    public BigDecimal subtotal() {
        return unitPrice.multiply(BigDecimal.valueOf(quantity));
    }
}
