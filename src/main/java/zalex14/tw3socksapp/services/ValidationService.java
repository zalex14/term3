package zalex14.tw3socksapp.services;

import zalex14.tw3socksapp.model.Stock;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Проверка корректности данных
 */
public interface ValidationService {
    boolean validate(@NotBlank String color, @NotNull int size, @NotNull @Min(0) @Max(100) int cottonPart, @NotNull long quantity);

    boolean validate(@NotBlank String color, @NotNull int size, @NotNull @Min(0) @Max(100) int cottonMin, @NotNull @Min(0) @Max(100) int cottonMax);

    boolean validate(@NotBlank String color, @NotNull @Min(0) @Max(100) int size);

    boolean validate(@NotNull @Min(0) @Max(100) int cottonPercent);

    boolean validate(@NotNull @Min(1) long quantity);

    boolean validate(@Valid Stock stock);
}