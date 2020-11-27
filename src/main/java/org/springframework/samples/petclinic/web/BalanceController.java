package org.springframework.samples.petclinic.web;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.service.BalanceService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/balances")
public class BalanceController {
    public static final String BALANCE_LISTING="clientes/ClientsListing";
    
    @Autowired
    BalanceService balanceService;
    

}
