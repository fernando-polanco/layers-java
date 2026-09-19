package domain.exception;

public class InsufficientStockException extends RuntimeException {
    public InsufficientStockException(int quantity, int availableStock) {
        super("solicitado: " + quantity + ", disponible: " + availableStock);
    }
}
