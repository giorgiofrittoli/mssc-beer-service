package it.frigir.msscbeerservice.web.controller;

import it.frigir.msscbeerservice.services.BeerService;
import it.frigir.brewery.model.BeerDto;
import it.frigir.brewery.model.BeerPagedList;
import it.frigir.brewery.model.BeerStyleEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * Created by jt on 2019-05-12.
 */
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/beer")
@RestController
public class BeerController {

    private static final Integer DEFAULT_PAGE_NUMBER = 0;
    private static final Integer DEFAULT_PAGE_SIZE = 25;

    private final BeerService beerService;

    @GetMapping(produces = "application/json")
    public ResponseEntity<BeerPagedList> listBeers(@RequestParam(value = "pageNumber", required = false) Integer pageNumber,
                                                   @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                                   @RequestParam(value = "beerName", required = false) String beerName,
                                                   @RequestParam(value = "beerStyle", required = false) BeerStyleEnum beerStyleEnum,
                                                   @RequestParam(value = "addQuantityOnHands", required = false) Boolean showInventoryOnHand) {

        if (showInventoryOnHand == null)
            showInventoryOnHand = false;

        if (pageNumber == null)
            pageNumber = this.DEFAULT_PAGE_NUMBER;

        if (pageSize == null)
            pageSize = this.DEFAULT_PAGE_SIZE;

        BeerPagedList beerPagedList = beerService.listBeers(beerName, beerStyleEnum, PageRequest.of(pageNumber, pageSize), showInventoryOnHand);

        return new ResponseEntity<>(beerPagedList, HttpStatus.OK);

    }

    @GetMapping("/{beerId}")
    public ResponseEntity<BeerDto> getBeerById(@PathVariable("beerId") UUID beerId,
                                               @RequestParam(value = "quantityOnHands", required = false, defaultValue = "false") Boolean showInventoryOnHand) {

        if (showInventoryOnHand == null)
            showInventoryOnHand = false;

        return new ResponseEntity<>(beerService.getById(beerId, showInventoryOnHand), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity saveNewBeer(@RequestBody @Validated BeerDto beerDto) {
        return new ResponseEntity<>(beerService.saveNewBeer(beerDto), HttpStatus.CREATED);
    }

    @PutMapping("/{beerId}")
    public ResponseEntity updateBeerById(@PathVariable("beerId") UUID beerId, @RequestBody @Validated BeerDto beerDto) {
        return new ResponseEntity<>(beerService.updateBeer(beerId, beerDto), HttpStatus.NO_CONTENT);
    }

}
