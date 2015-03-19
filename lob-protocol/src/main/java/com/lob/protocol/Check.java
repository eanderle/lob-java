package com.lob.protocol;

import java.util.Map;
import com.lob.exception.APIConnectionException;
import com.lob.exception.APIException;
import com.lob.exception.AuthenticationException;
import com.lob.exception.InvalidRequestException;
import com.lob.net.APIResource;
import com.lob.protocol.response.Address;

public class Check extends APIResource {
    String id;
    String name;
    String check_number;
    Bank_account bank_account;
    Address to;
    Double amount;
    String message;
    String memo;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCheck_number() {
        return check_number;
    }

    public Bank_account getBank_account() {
        return bank_account;
    }

    public Address getTo() {
        return to;
    }

    public Double getAmount() {
        return amount;
    }

    public String getMessage() {
        return message;
    }

    public String getMemo() {
        return memo;
    }

    public static Check retrieve(String id, String apiKey)
            throws AuthenticationException, InvalidRequestException,
            APIConnectionException, APIException {
        return APIResource.request(
            RequestMethod.GET, APIResource.instanceURL(Check.class, id), null,
            Check.class, apiKey);
    }

    public static Check create(Map<String, java.lang.Object> params, String apiKey)
            throws AuthenticationException, InvalidRequestException,
            APIConnectionException, APIException {
            return APIResource.request(
                RequestMethod.POST, APIResource.classURL(Check.class), params,
                Check.class, apiKey);
    }

    public static CheckCollection all(Map<String, java.lang.Object> params,
            String apiKey) throws AuthenticationException,
            InvalidRequestException, APIConnectionException,
            APIException {
        return APIResource.request(
            RequestMethod.GET, APIResource.classURL(Check.class), params,
            CheckCollection.class, apiKey);
    }

}
