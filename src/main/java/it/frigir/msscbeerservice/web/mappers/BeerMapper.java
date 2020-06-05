package it.frigir.msscbeerservice.web.mappers;

import it.frigir.msscbeerservice.domain.Beer;
import it.frigir.brewery.model.BeerDto;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

/**
 * Created by jt on 2019-05-25.
 */
@Mapper(uses = {DateMapper.class})
@DecoratedWith(BeerMapperDecorator.class)
public interface BeerMapper {

    BeerDto beerToBeerDto(Beer beer);

    BeerDto beerToBeerDto(Beer beer, Boolean showInventoryOnHand);

    Beer beerDtoToBeer(BeerDto dto);
}
