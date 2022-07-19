package it.testsv4.testsv4.utils;

import org.springframework.http.HttpHeaders;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;

public final class Utils {

    public static final String SCHEMA = "Auth-Schema";
    public static final String API_KEY = "Api-Key";


    public static HttpHeaders getHeaders(String authSchema, String apiKey) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(Utils.SCHEMA, authSchema);
        headers.set(Utils.API_KEY, apiKey);
        headers.set("X-Time-Zone", "Europe/Rome");
        return headers;
    }

    public static  String getUrl(String urlContext, String endpoint, Long accountId) {
        UriComponents uriComponents = UriComponentsBuilder.fromUriString(urlContext + endpoint)
                .build();
        return uriComponents.expand(Collections.singletonMap("accountId", accountId)).toUriString();
    }

    public static  String getUrlTransactionWithParam(String urlContext, String endpoint, Long accountId, String fromAccountingDate, String toAccountingDate) {
        UriComponents uriComponents = UriComponentsBuilder.fromUriString(urlContext + endpoint)
                .queryParam("fromAccountingDate",fromAccountingDate)
                .queryParam("toAccountingDate",toAccountingDate)
                .build();

        return uriComponents.expand(Collections.singletonMap("accountId", accountId)).toUriString();
    }

}
