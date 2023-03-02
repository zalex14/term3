package zalex14.tw3socksapp.services;

import zalex14.tw3socksapp.model.Socks;
import zalex14.tw3socksapp.model.Stock;

import java.util.Map;

/**
 * Движение товара на складе
 */
public interface StockService {
    // учёт прихода и отпуска носков
    void shipment(String color, int size, int cottonPart, long quantity);

    void shipment(Stock stock);

    void release(String color, int size, int cottonPart, long quantity);

    void release(Stock stock);

    // общее количество носков определенного цвета и состава в данный момент времени
    int warehouse(String color, int size, int cottonMin, int cottonMax);

    // парсинг данных по товару
    void parsing(Stock stock);

    // Складские остатки
    Map<Socks, Long> getAll();
}