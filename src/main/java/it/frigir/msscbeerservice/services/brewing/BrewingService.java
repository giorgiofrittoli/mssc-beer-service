package it.frigir.msscbeerservice.services.brewing;

import it.frigir.msscbeerservice.config.JmsConfig;
import it.frigir.msscbeerservice.domain.Beer;
import it.frigir.msscbeerservice.events.BrewBeerEvent;
import it.frigir.msscbeerservice.repositories.BeerRepository;
import it.frigir.msscbeerservice.services.inventory.BeerInventoryService;
import it.frigir.msscbeerservice.web.mappers.BeerMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BrewingService {

    private final BeerRepository beerRepository;
    private final BeerInventoryService beerInventoryService;
    private final JmsTemplate jmsTemplate;
    private final BeerMapper beerMapper;

    @Async
    @Scheduled(fixedRate = 5000)
    public void checkForLowInventory() {

        List<Beer> beers = beerRepository.findAll();

        log.error("Checking for low inventory");

        beers.forEach(beer -> {

            Integer invQOH = beerInventoryService.getOnHandInventory(beer.getId());

            log.debug("Min on hand " + beer.getMinOnHand());
            log.debug("Inventory is " + invQOH);

            if (beer.getMinOnHand() >= invQOH) {
                jmsTemplate.convertAndSend(
                        JmsConfig.BREWING_REQUEST_QUEUE,
                        new BrewBeerEvent(beerMapper.beerToBeerDto(beer))
                );
            }
        });
    }

}
