package com.example.apiforcurrencyconverter.Models;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("Currency")
public class Currency {
    @Id
    private String name;
    private double valueInEuros;

    public Currency(){
    }

    public Currency(String name, double valueInUsd) {
        this.name = name;
        this.valueInEuros = valueInUsd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getValueInEuros() {
        return valueInEuros;
    }

    public void setValueInEuros(double valueInEuros) {
        this.valueInEuros = valueInEuros;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "name='" + name + '\'' +
                ", valueInUsd=" + valueInEuros +
                '}';
    }
}