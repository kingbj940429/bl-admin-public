package blaunch.bladmin.dto;

import blaunch.bladmin.entity.business.SalesBusiness;
import lombok.Getter;

public class SalesBusinessDto {

    @Getter
    public static class FindSalesBusinessRes {
        private String period;
        private double salesPrice;
        private double income;

        public FindSalesBusinessRes(SalesBusiness salesBusiness) {
            this.period = salesBusiness.getPeriod();
            this.salesPrice = salesBusiness.getSalesPrice();
            this.income = salesBusiness.getIncome();
        }
    }
}
