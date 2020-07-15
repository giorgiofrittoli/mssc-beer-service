package it.frigir.msscbeerservice.services.inventory;

import it.frigir.brewery.model.BeerInventoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class InventoryServiceFeignClientFailover implements InventoryFailoverFeignClient {

    private final InventoryFailoverFeignClient inventoryFailoverFeignClient;

    @Override
    public ResponseEntity<List<BeerInventoryDto>> getOnHandInventory() {
        return inventoryFailoverFeignClient.getOnHandInventory();
    }
}
