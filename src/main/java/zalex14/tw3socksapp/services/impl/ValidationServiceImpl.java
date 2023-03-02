package zalex14.tw3socksapp.services.impl;

import lombok.Data;
import org.apache.commons.lang3.EnumUtils;
import org.springframework.stereotype.Service;
import zalex14.tw3socksapp.exception.HttpMessageNotReadableException;
import zalex14.tw3socksapp.exception.IllegalArgumentException;
import zalex14.tw3socksapp.model.Color;
import zalex14.tw3socksapp.model.Size;
import zalex14.tw3socksapp.model.Stock;
import zalex14.tw3socksapp.services.ValidationService;

import java.util.Arrays;

/**
 * Проверка корректности данных (реализация)
 */
@Data
@Service
public class ValidationServiceImpl implements ValidationService {
    @Override
    public boolean validate(String color, int size, int cottonPart, long quantity) {
        return validate(color, size) && validate(cottonPart) && validate(quantity);
    }

    @Override
    public boolean validate(String color, int size, int cottonMin, int cottonMax) {
        return validate(color, size) && validate(cottonMin) && validate(cottonMax);
    }

    @Override
    public boolean validate(String color, int size) {
        if (color.isBlank() || !EnumUtils.isValidEnum(Color.class, color)) {
            throw new HttpMessageNotReadableException("color: " + color + " Допустимые значения " + Arrays.asList(Color.values()));
        } else if (size < 22 || size > 29 || !EnumUtils.isValidEnum(Size.class, "GROUP" + size)) {
            throw new HttpMessageNotReadableException("size: " + size + " Допустимые значения " + Arrays.asList(Size.values()));
        } else return true;
    }

    @Override
    public boolean validate(int cottonPercent) {
        if (cottonPercent < 0 || cottonPercent > 100) {
            throw new IllegalArgumentException("cotton: " + cottonPercent + " Допустимые значения от 0 до 100%");
        }
        return true;
    }

    @Override
    public boolean validate(long quantity) {
        if (quantity < 1) {
            throw new IllegalArgumentException("quantity: " + quantity + " Допустимые значения больше 0");
        }
        return true;
    }

    @Override
    public boolean validate(Stock stock) {
        return stock.getSocks() != null
                && stock.getColor().getDeclaringClass().isEnum()
                && stock.getSize().getDeclaringClass().isEnum()
                && validate(stock.getCottonPercent()) && validate(stock.getQuantity());
    }
}