package zalex14.tw3socksapp.services.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import zalex14.tw3socksapp.exception.IllegalArgumentException;
import zalex14.tw3socksapp.model.Color;
import zalex14.tw3socksapp.model.Size;
import zalex14.tw3socksapp.model.Socks;
import zalex14.tw3socksapp.model.Stock;
import zalex14.tw3socksapp.services.StockService;
import zalex14.tw3socksapp.services.ValidationService;

import java.util.Map;

/**
 * Движение товара на складе
 */
@Service
@AllArgsConstructor
public class StockServiceImpl implements StockService {
    public Map<Socks, Long> stockMap;
    private final ValidationService validationService;

    /**
     * Приход товара на склад
     *
     * @param color, size , cottonPart, quantity
     */
    @Override
    public void shipment(String color, int size, int cottonPart, long quantity) {
        if (!validationService.validate(color, size, cottonPart, quantity)) {
            throw new IllegalArgumentException("StockService: shipment недопустимый аргумент ");
        }
        Socks socks = new Socks(Color.valueOf(color), Size.valueOf("GROUP" + size), cottonPart);
        if (stockMap.containsKey(socks)) {
            stockMap.replace(socks, stockMap.get(socks) + quantity);
        } else {
            stockMap.put(socks, quantity);
        }
    }

    @Override
    public void shipment(Stock stock) {
        if (!validationService.validate(stock)) {
            throw new IllegalArgumentException("StockService: shipment недопустимый аргумент ");
        }
        Socks socks = stock.getSocks();
        if (stockMap.containsKey(socks)) {
            stockMap.replace(socks, stockMap.get(socks) + stock.getQuantity());
        } else {
            stockMap.put(socks, stock.getQuantity());
        }
    }

    /**
     * Отпуск товара со склада
     *
     * @param color, size , cottonPart, quantity
     */
    @Override
    public void release(String color, int size, int cottonPart, long quantity) {
        if (!validationService.validate(color, size, cottonPart, quantity)) {
            throw new IllegalArgumentException("StockService: release недопустимый аргумент ");
        }
        Socks socks = new Socks(Color.valueOf(color), Size.valueOf("GROUP" + size), cottonPart);
        if (stockMap.containsKey(socks)) {
            if (stockMap.get(socks) > quantity) {
                stockMap.replace(socks, stockMap.get(socks) - quantity);
            } else {
                stockMap.remove(socks);
            }
        }
    }

    @Override
    public void release(Stock stock) {
        if (!validationService.validate(stock)) {
            throw new IllegalArgumentException("StockService: release недопустимый аргумент ");
        }
        Socks socks = stock.getSocks();
        if (stockMap.containsKey(socks)) {
            if (stockMap.get(socks) > stock.getQuantity()) {
                stockMap.replace(socks, stockMap.get(socks) - stock.getQuantity());
            } else {
                stockMap.remove(socks);
            }
        }
    }

    /**
     * Поиск складских запасов по параметрам
     *
     * @param color, size , cottonPart, quantity
     */
    @Override
    public int warehouse(String color, int size, int cottonMin, int cottonMax) {
        if (!validationService.validate(color, size, cottonMin, cottonMax)) {
            throw new IllegalArgumentException("StockService: warehouse недопустимый аргумент ");
        }
        int countSocks = 0;
        for (Map.Entry<Socks, Long> socksType : stockMap.entrySet()) {
            Socks socks = socksType.getKey();
            if (socks.getColor().equals(Color.valueOf(color))
                    && socks.getSize().equals(Size.valueOf("GROUP" + size))
                    && socks.getCottonPercent() >= cottonMin
                    && socks.getCottonPercent() <= cottonMax) {
                countSocks += socksType.getValue();
            }
        }
        return countSocks;
    }

    /**
     * Парсинг движения товара
     */
    @Override
    public void parsing(Stock stock) {
    }

    /**
     * Складские остатки
     */
    @Override
    public Map<Socks, Long> getAll() {
        return stockMap;
    }
}