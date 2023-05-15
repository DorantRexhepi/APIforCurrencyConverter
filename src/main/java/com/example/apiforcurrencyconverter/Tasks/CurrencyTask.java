package com.example.apiforcurrencyconverter.Tasks;

import com.example.apiforcurrencyconverter.Models.Currency;
import com.example.apiforcurrencyconverter.Models.CurrencyDTO;
import com.example.apiforcurrencyconverter.Repositories.CurrencyRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;



@Component
public class CurrencyTask {

    @Autowired
    private CurrencyRepository currencyRepository;

    @Value("${fixer.io.apiKey}")
    private String fixerIoApiKey;

    @Scheduled(fixedRate = 5 * 1000 * 60 * 60)
    private void getRatesTask() {
        try {
            RestTemplate restTemplate = new RestTemplate();
            CurrencyDTO forObject = restTemplate.getForObject("http://data.fixer.io/api/latest?access_key=0a5f28039a528338c606d27c78c40e3b", CurrencyDTO.class);
            forObject.getRates().forEach((key, value) -> {
                Currency currency = new Currency(key, value);
                this.currencyRepository.save(currency);
            });
        } catch (RestClientException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
