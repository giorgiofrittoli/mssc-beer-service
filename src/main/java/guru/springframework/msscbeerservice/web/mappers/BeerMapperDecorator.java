package guru.springframework.msscbeerservice.web.mappers;


import guru.springframework.msscbeerservice.domain.Beer;
import guru.springframework.msscbeerservice.services.inventory.BeerInventoryService;
import guru.springframework.msscbeerservice.web.model.BeerDto;
import org.springframework.beans.factory.annotation.Autowired;

//decorate int beer mapper to add beer qty
public abstract class BeerMapperDecorator implements BeerMapper {

    private BeerInventoryService beerInventoryService;
    private BeerMapper beerMapper;

    @Override
    public BeerDto beerToBeerDto(Beer beer) {
        return beerToBeerDto(beer, false);
    }

    @Override
    public BeerDto beerToBeerDto(Beer beer, Boolean showInventoryOnHand) {
        //map to dto with gen mapper
        BeerDto dto = beerMapper.beerToBeerDto(beer);
        if (showInventoryOnHand) {
            //call inv service to retrieve qty
            dto.setQuantityOnHand(beerInventoryService.getOnHandInventory(beer.getId()));
        }
        return dto;
    }

    @Override
    public Beer beerDtoToBeer(BeerDto dto) {
        return beerMapper.beerDtoToBeer(dto);
    }

    //to inject generated mapper
    @Autowired
    public void setBeerMapper(BeerMapper beerMapper) {
        this.beerMapper = beerMapper;
    }

    //to inject service
    @Autowired
    public void setBeerInventoryService(BeerInventoryService beerInventoryService) {
        this.beerInventoryService = beerInventoryService;
    }
}
