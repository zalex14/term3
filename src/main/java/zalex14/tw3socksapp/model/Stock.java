package zalex14.tw3socksapp.model;

import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Cкладской остаток
 */
@Data
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class Stock {
    private @NotNull Socks socks;
    /**
     * цвет носков (enum)
     */
    private @NotNull @Valid Color color;
    /**
     * размер носков (enum)
     */
    private @NotNull @Valid Size size;
    /**
     * состав носков в % соотношение хлопка в составе от 0 до 100
     */

    private @NotNull @Min(0) @Max(100) int cottonPercent;
    /**
     * складской остаток
     */
    private @NotNull @Min(1) long quantity;
}