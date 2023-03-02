package zalex14.tw3socksapp.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zalex14.tw3socksapp.model.Stock;
import zalex14.tw3socksapp.services.StockService;

/**
 * CRUD-методы
 */
@RestController
@RequestMapping("/api/socks")
@Tag(name = "API склада носков", description = "Регистрация: поступление на склад, отпуск со склада, " +
        "складской остаток, списание брака")
@AllArgsConstructor
public class StockController {
    private final StockService stockService;

    /**
     * Возвращает общее количество носков на складе, соответствующих переданным в параметрах критериям запроса
     * Пример запроса GET /api/socks?color=red&36&cottonMin=90
     * должен вернуть общее количество красных носков 36-го размера с долей хлопка более 90%
     * Пример запроса GET /api/socks?color=red&36&cottonMax=10
     * должен вернуть общее количество красных носков 36-го размера с долей хлопка менее 10%
     */
    @GetMapping
    @Operation(summary = "Товарные остатки на складе, " +
            "соответствующих переданным в параметрах критериям запроса")
    @ApiResponse(responseCode = "200", description = "Запрос выполнен, результат в теле ответа " +
            "в виде строкового представления целого числа")
    @ApiResponse(responseCode = "400", description = "Параметры запроса отсутствуют или имеют некорректный формат")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<Integer> warehouse(@RequestParam String color,
                                             @RequestParam int size,
                                             @RequestParam int cottonMin,
                                             @RequestParam int cottonMax) {
        int amountSocks = stockService.warehouse(color, size, cottonMin, cottonMax);
        return ResponseEntity.ok(amountSocks);
    }

    /**
     * Регистрирует приход товара на склад
     * Пример запроса POST /api/socks?color=red&36&cottonPart=40&quantity=5
     * добавляет на склад пять пар носков красного цвета 36-го размера с долей хлопка в составе 40%
     */
    @PostMapping
    @Operation(summary = "Приход товара на склад")
    @ApiResponse(responseCode = "200", description = "ОК. Удалось добавить приход")
    @ApiResponse(responseCode = "400", description = "Параметры запроса отсутствуют или имеют некорректный формат")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<Stock> shipment(@RequestParam String color,
                                          @RequestParam int size,
                                          @RequestParam int cottonPart,
                                          @RequestParam long quantity) {
        stockService.shipment(color, size, cottonPart, quantity);
        return ResponseEntity.ok(new Stock());
    }

    /**
     * Регистрирует отпуск носков со склада
     * Пример запроса PUT /api/socks?color=red&36&cottonPart=40&quantity=5
     * регистрирует отпуск со склада пяти пар носков красного цвета 36-го размера с долей хлопка в составе 40%
     */
    @PutMapping
    @Operation(summary = "Отпуск товара со склада")
    @ApiResponse(responseCode = "200", description = "ОК. Удалось произвести отпуск носков со склада")
    @ApiResponse(responseCode = "400", description = "Товара нет на складе в нужном количестве " +
            "или параметры запроса имеют некорректный формат")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<Stock> release(@RequestParam String color,
                                         @RequestParam int size,
                                         @RequestParam int cottonPart,
                                         @RequestParam long quantity) {
        stockService.release(color, size, cottonPart, quantity);
        return ResponseEntity.ok(new Stock());
    }

    /**
     * Регистрирует списание испорченных (бракованных) носков
     * Пример запроса DELETE /api/socks?color=red&36&cottonPart=40&quantity=5
     * регистрирует списание брака со склада в количестве пяти пар носков красного цвета 36-го размера с долей
     * хлопка в составе 40%
     */
    @DeleteMapping
    @Operation(summary = "Списание испорченного (бракованног) товара")
    @ApiResponse(responseCode = "200", description = "Запрос выполнен, товар списан со склада")
    @ApiResponse(responseCode = "400", description = "Параметры запроса отсутствуют или имеют некорректный формат")
    @ApiResponse(responseCode = "409", description = "Запрос не может быть выполнен. Параметр запроса не существует")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<Stock> registration(@RequestParam String color,
                                              @RequestParam int size,
                                              @RequestParam int cottonPart,
                                              @RequestParam long quantity) {
        stockService.release(color, size, cottonPart, quantity);
        return ResponseEntity.ok(new Stock());
    }
}