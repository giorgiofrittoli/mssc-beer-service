package it.frigir.brewery.model.event;

import it.frigir.brewery.model.BeerOrderDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidateBeerOrderRequest {
    private BeerOrderDto beerOrder;
}
