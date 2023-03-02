package zalex14.tw3socksapp.model;

import lombok.NonNull;
import lombok.experimental.FieldNameConstants;

import javax.validation.constraints.NotBlank;

/**
 * цвет носков
 */
@FieldNameConstants
@NonNull
public enum Color {
    BLACK("Черные"),
    RED("Красные"),
    YELLOW("Желтые");

    Color(@NotBlank String rusColor) {
    }
}