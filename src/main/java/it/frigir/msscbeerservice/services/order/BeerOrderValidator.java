package it.frigir.msscbeerservice.services.order;

import it.frigir.brewery.model.BeerOrderDto;
import it.frigir.brewery.model.BeerOrderLineDto;
import it.frigir.msscbeerservice.domain.Beer;
import it.frigir.msscbeerservice.repositories.BeerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class BeerOrderValidator {

    private final BeerRepository beerRepository;

    public Boolean validateBeerOrder(BeerOrderDto beerOrderDto) {
        List<String> lUpc = beerRepository.findAll().stream().map(Beer::getUpc).collect(Collectors.toList());

        List<String> lBeerOrderUpc = beerOrderDto
                .getBeerOrderLines().stream()
                .map(BeerOrderLineDto::getUpc).collect(Collectors.toList());

        lBeerOrderUpc.removeAll(lUpc);

        return lBeerOrderUpc.isEmpty();

    }


}
