package ua.com.alevel.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@ApiModel(value = "CreditRequestDto", description = "CreditRequestDto")
public class CreditRequestDto {

    @DecimalMin(value = "100.0", inclusive = false, message = "value must be more when 100.00")
    @Digits(integer = 7, fraction = 2)
    @NotNull(message = "sum can not be empty")
    @ApiModelProperty(notes = "parameter sum, errors: {sum can not be empty}")
    private BigDecimal sum;
}
