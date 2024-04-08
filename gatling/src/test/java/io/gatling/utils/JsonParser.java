package io.gatling.utils;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import io.gatling.model.Customer;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class JsonParser {
    public static List<Customer> parseJsonToList(String jsonString) {
        Gson gson = new Gson();
        Customer[] peopleArray = gson.fromJson(jsonString, Customer[].class);
        List<Customer> peopleList = Arrays.asList(peopleArray);
        return peopleList;
    }
    public static Customer parseJsonToCustomer(String jsonString){
        Gson gson = new Gson();
        Customer customer = gson.fromJson(jsonString, Customer.class);
        return customer;
    }
}
