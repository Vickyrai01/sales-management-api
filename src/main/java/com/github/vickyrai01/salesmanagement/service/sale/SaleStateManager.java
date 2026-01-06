package com.github.vickyrai01.salesmanagement.service.sale;

import com.github.vickyrai01.salesmanagement.exception.InvalidSaleStateException;
import com.github.vickyrai01.salesmanagement.model.Sale;
import com.github.vickyrai01.salesmanagement.model.enums.SaleState;
import org.springframework.stereotype.Component;

@Component
public class SaleStateManager {

    public Sale confirmSale(Sale sale){
        if (sale.getState() != SaleState.CREATED) throw new InvalidSaleStateException("Sale is not in CREATED state");
        sale.setState(SaleState.CONFIRMED);
        return sale;
    }

    public Sale paySale(Sale sale){
        if (sale.getState() != SaleState.CONFIRMED) throw new InvalidSaleStateException("Sale is not in CONFIRMED state");
        sale.setState(SaleState.PAID);
        return sale;
    }

    public Sale cancelSale(Sale sale){
        if (sale.getState() != SaleState.CREATED && sale.getState() != SaleState.CONFIRMED) {
            throw new InvalidSaleStateException("Sale must be in CREATED or CONFIRMED state to be cancelled");
        }
        sale.setState(SaleState.CANCELLED);
        return sale;
    }
}
