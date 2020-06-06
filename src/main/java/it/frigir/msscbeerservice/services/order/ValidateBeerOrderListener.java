package it.frigir.msscbeerservice.services.order;

import it.frigir.brewery.model.event.ValidateBeerOrderRequest;
import it.frigir.brewery.model.event.ValidateBeerOrderResult;
import it.frigir.msscbeerservice.config.JmsConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class ValidateBeerOrderListener {

    private final JmsTemplate jmsTemplate;
    private final BeerOrderValidator beerOrderValidator;

    @Transactional
    @JmsListener(destination = JmsConfig.VALIDATE_ORDER_QUEUE)
    public void validateBeerOrder(ValidateBeerOrderRequest validateBeerOrderRequest) {

        jmsTemplate.convertAndSend(JmsConfig.VALIDATE_ORDER_RESPONSE_QUEUE,
                ValidateBeerOrderResult.builder()
                        .beerOrderId(validateBeerOrderRequest.getBeerOrder().getId())
                        .valid(beerOrderValidator.validateBeerOrder(validateBeerOrderRequest.getBeerOrder())).build());

    }

}
