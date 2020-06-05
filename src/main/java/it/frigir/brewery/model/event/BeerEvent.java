package it.frigir.brewery.model.event;

import it.frigir.brewery.model.BeerDto;
import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class BeerEvent implements Serializable {

    static final long serialVersionUID = -60807397918427415L;

    private BeerDto beerDto;
}
