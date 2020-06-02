package it.frigir.msscbeerservice.web.controller;

import it.frigir.msscbeerservice.services.BeerService;
import it.frigir.msscbeerservice.web.model.BeerDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/beerUpc")
@RestController
public class BeerUpcController {

    private final BeerService beerService;

    @GetMapping("/{upc}")
    public ResponseEntity<BeerDto> getBeerByUPC(@PathVariable("upc") String upc,
                                                @RequestParam(value = "addQuantityOnHands", required = false) Boolean showInventoryOnHand) {

        log.debug("Get by upc " + upc);

        if (showInventoryOnHand == null)
            showInventoryOnHand = false;

        return new ResponseEntity<>(beerService.getByUpc(upc, showInventoryOnHand), HttpStatus.OK);

    }

}
