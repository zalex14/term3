package zalex14.tw3socksapp.model;

import lombok.experimental.FieldNameConstants;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * размер носков
 */
@FieldNameConstants
@NotNull
public enum Size {
    GROUP22(22, 34, 4),
    GROUP23(23, 36, 5),
    GROUP24(24, 37, 6),
    GROUP25(25, 38, 7),
    GROUP26(26, 40, 8),
    GROUP27(27, 42, 9),
    GROUP28(28, 44, 11),
    GROUP29(29, 46, 14);

    Size(@Min(20) @Max(31) int euroSize, @Min(32) @Max(48) int rusSize, @Min(1) @Max(16) int usSize) {
    }
}