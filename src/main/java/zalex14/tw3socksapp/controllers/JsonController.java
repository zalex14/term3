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
 * CRUD-методы (JSON)
 */
@RestController
@RequestMapping("/api/json")
@Tag(name = "API склада носков", description = "Регистрация: поступление на склад, отпуск со склада, " +
        "складской остаток, списание брака")
@AllArgsConstructor
public class JsonController {
    private final StockService stockService;

    /**
     * Регистрирует приход товара на склад (JSON)
     */
    @PostMapping
    @Operation(summary = "Приход товара на склад (JSON)")
    @ApiResponse(responseCode = "200", description = "ОК. Удалось добавить приход")
    @ApiResponse(responseCode = "400", description = "Параметры запроса отсутствуют или имеют некорректный формат")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<Stock> shipment(@RequestBody Stock stock) {
        stockService.shipment(stock);
        return ResponseEntity.ok(stock);
    }

    /**
     * Регистрирует отпуск носков со склада (JSON)
     */
    @PutMapping
    @Operation(summary = "Отпуск товара со склада (JSON)")
    @ApiResponse(responseCode = "200", description = "ОК. Удалось произвести отпуск носков со склада")
    @ApiResponse(responseCode = "400", description = "Товара нет на складе в нужном количестве " +
            "или параметры запроса имеют некорректный формат")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<Stock> release(@RequestBody Stock stock) {
        stockService.release(stock);
        return ResponseEntity.ok(stock);
    }

    /**
     * Регистрирует списание испорченных (бракованных) носков (JSON)
     */
    @DeleteMapping
    @Operation(summary = "Списание бракованного товара (JSON)")
    @ApiResponse(responseCode = "200", description = "Запрос выполнен, товар списан со склада")
    @ApiResponse(responseCode = "400", description = "Параметры запроса отсутствуют или имеют некорректный формат")
    @ApiResponse(responseCode = "409", description = "Запрос не может быть выполнен. Параметр запроса не существует")
    @ApiResponse(responseCode = "500", description = "Произошла ошибка, не зависящая от вызывающей стороны")
    public ResponseEntity<Stock> registration(@RequestBody Stock stock) {
        stockService.release(stock);
        return ResponseEntity.ok(stock);
    }
}