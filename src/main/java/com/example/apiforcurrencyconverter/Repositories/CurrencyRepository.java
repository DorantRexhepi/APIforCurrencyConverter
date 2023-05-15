package com.example.apiforcurrencyconverter.Repositories;

import com.example.apiforcurrencyconverter.Models.Currency;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface CurrencyRepository extends CrudRepository<Currency, String> {
    @Override
    List<Currency> findAll();
}
