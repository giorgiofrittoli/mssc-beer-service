package it.frigir.brewery.model.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ValidateBeerOrderResult {
    private UUID beerOrderId;
    private Boolean valid;
}
