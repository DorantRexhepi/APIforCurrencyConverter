package com.example.apiforcurrencyconverter.Services;
import com.example.apiforcurrencyconverter.Models.ConversionCurrency;
import com.example.apiforcurrencyconverter.Models.Currency;
import com.example.apiforcurrencyconverter.Repositories.CurrencyRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;


@Service
public class CurrencyService {

    private CurrencyRepository currencyRepository;

    public CurrencyService(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

//    public List<Currency> getAllCurrencies() {
//        List<Currency> currencyList = this.currencyRepository.findAll();
//        currencyList.sort(Comparator.comparing(Currency::getName));
//        return currencyList;
//    }
    public List<Currency> getAllCurrencies() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            InputStream inputStream = TypeReference.class.getResourceAsStream("/json/currencies.json");
            JsonNode node = mapper.readValue(inputStream, JsonNode.class);
            JsonNode rates = node.get("rates");
            List<Currency> currencyList = new ArrayList<>();
            Iterator<String> fieldNames = rates.fieldNames();
            while (fieldNames.hasNext()) {
                String name = fieldNames.next();
                double rate = rates.get(name).asDouble();
                currencyList.add(new Currency(name, rate));
            }
            return currencyList;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<Double> convert(ConversionCurrency conversionCurrency) {
        Optional<Currency> toOptional = this.currencyRepository.findById(conversionCurrency.getTo().toUpperCase());
        Optional<Currency> fromOptional = this.currencyRepository.findById(conversionCurrency.getFrom().toUpperCase());

        if (toOptional.isPresent() && fromOptional.isPresent()) {

            if (conversionCurrency.getValue() < 0) {
                return Optional.empty();
            }
            Currency to = toOptional.get();
            Currency from = fromOptional.get();
            Double toValue = to.getValueInEuros();
            Double fromValue = from.getValueInEuros();

            Double result = toValue * conversionCurrency.getValue() / fromValue;

            return Optional.of(result);
        }
        return Optional.empty();
    }
}
