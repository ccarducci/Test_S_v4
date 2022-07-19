package it.testsv4.testsv4.service;

import it.testsv4.testsv4.model.AccountBalanceResponse;
import it.testsv4.testsv4.model.CreatMoneyTransferRequest;
import it.testsv4.testsv4.model.CreatMoneyTransferResponse;
import it.testsv4.testsv4.model.TransactionsResponse;
import it.testsv4.testsv4.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class ApiService {

    @Value("${fabrick.urlRoot}")
    private String urlRoot;

    @Value("${fabrick.urlGetBalance}")
    private String urlGetBalance;

    @Value("${fabrick.urlMoneyTransfers}")
    private String urlMoneyTransfers;

    @Value("${fabrick.urlGetTransactions}")
    private String urlGetTransactions;

    @Value("${fabrick.Auth-Schema}")
    private String authSchema;

    @Value("${fabrick.Api-Key}")
    private String apiKey;

    private RestTemplate restTemplate;

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<AccountBalanceResponse> getAccountBalance(Long accountId) {
        ResponseEntity<AccountBalanceResponse> response = null;

        String url = Utils.getUrl(urlRoot, urlGetBalance, accountId);
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity request = new HttpEntity(Utils.getHeaders(authSchema, apiKey));

        try {
            response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    request,
                    AccountBalanceResponse.class
            );
        } catch (HttpClientErrorException exClient) {
            return new ResponseEntity<>(exClient.getStatusCode());
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response.getBody(), HttpStatus.OK);
    }

    public ResponseEntity<TransactionsResponse> getTransactions(Long accountId, String fromAccountingDate, String toAccountingDate) {
        ResponseEntity<TransactionsResponse> response = null;

        String url = Utils.getUrlTransactionWithParam(urlRoot, urlGetTransactions, accountId, fromAccountingDate, toAccountingDate);
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity request = new HttpEntity(Utils.getHeaders(authSchema, apiKey));

        try {

            response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    request,
                    TransactionsResponse.class
            );

        } catch (HttpClientErrorException exClient) {
            return new ResponseEntity<>(exClient.getStatusCode());
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }


        return new ResponseEntity<>(response.getBody(), HttpStatus.OK);
    }

    public ResponseEntity<CreatMoneyTransferResponse> executeTransaction(CreatMoneyTransferRequest creatMoneyTransferRequest) {
        CreatMoneyTransferResponse response = new CreatMoneyTransferResponse();
        response.setCode("API000");
        response.setDescription("Errore tecnico  La condizione BP049 non e' prevista per il conto id " + creatMoneyTransferRequest.getAccountId());
        return new ResponseEntity<CreatMoneyTransferResponse>(response, HttpStatus.OK);
    }
}
