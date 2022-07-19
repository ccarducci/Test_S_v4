package it.testsv4.testsv4.controller;


import it.testsv4.testsv4.model.AccountBalanceResponse;
import it.testsv4.testsv4.model.CreatMoneyTransferRequest;
import it.testsv4.testsv4.model.CreatMoneyTransferResponse;
import it.testsv4.testsv4.model.TransactionsResponse;
import it.testsv4.testsv4.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@RestController
@RequestMapping("/api/v1.0/accounts")
public class ApiRestController {

    private ApiService apiService;

    @Autowired
    public void setApiService(ApiService apiService) {
        this.apiService = apiService;
    }

    @GetMapping(value = "/{accountId}/balance", produces = {"application/json"})
    public ResponseEntity<AccountBalanceResponse> getAccountBalance(@PathVariable Long accountId) {
        return apiService.getAccountBalance(accountId);
    }

    @GetMapping(value = "/{accountId}/transactions", produces = {"application/json"})
    public ResponseEntity<TransactionsResponse> getTransactions(@PathVariable Long accountId, @RequestParam String fromAccountingDate, @RequestParam String toAccountingDate) {
        try {
            LocalDate date1 = LocalDate.parse(fromAccountingDate);
            LocalDate date2 = LocalDate.parse(toAccountingDate);
        } catch (DateTimeParseException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return apiService.getTransactions(accountId, fromAccountingDate, toAccountingDate);
    }

    @PostMapping("/{accountId}/transfers")
    public ResponseEntity<CreatMoneyTransferResponse> transfers(@RequestBody @Valid CreatMoneyTransferRequest creatMoneyTransferRequest) {
        try {
            LocalDate date1 = LocalDate.parse(creatMoneyTransferRequest.getExecutionDate());
        } catch (DateTimeParseException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return apiService.executeTransaction(creatMoneyTransferRequest);
    }

}
