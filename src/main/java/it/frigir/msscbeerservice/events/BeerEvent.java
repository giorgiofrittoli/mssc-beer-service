package it.frigir.msscbeerservice.events;

import it.frigir.msscbeerservice.web.model.BeerDto;
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
