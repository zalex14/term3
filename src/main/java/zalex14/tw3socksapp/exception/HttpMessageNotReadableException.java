package zalex14.tw3socksapp.exception;

/**
 * Ошибка чтения JSON
 */
public class HttpMessageNotReadableException extends RuntimeException {
    public HttpMessageNotReadableException(String message) {
        super(" Недопустимая группа товара " + message);
    }
}