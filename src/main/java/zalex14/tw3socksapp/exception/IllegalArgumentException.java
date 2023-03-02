package zalex14.tw3socksapp.exception;

/**
 * Ошибка некорректных данных
 */
public class IllegalArgumentException extends RuntimeException {
    public IllegalArgumentException(String message) {
        super(" Недопустимое значение " + message);
    }
}