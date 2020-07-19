package it.frigir.msscbeerservice.services.inventory;

import it.frigir.brewery.model.BeerInventoryDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Component
public class InventoryFailoverService implements InventoryServiceFeignClient {
    private final InventoryFailoverFeignClient inventoryFailoverFeignClient;

    @Override
    public ResponseEntity<List<BeerInventoryDto>> getOnhandInventory(UUID beerId) {
        log.debug("Calling Inventory Failover for Beer Id: " + beerId);
        return inventoryFailoverFeignClient.getOnhandInventory();
    }
}
