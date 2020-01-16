package com.santander.rio.soapxml;

import org.apache.commons.lang3.StringUtils;

public class Client {

    private String name;

    private String surname;

    private String dni;

    private String cbu;

    private String accountNumber;

    private String currency;

    private Double amount;

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setCbu(String cbu) {
        this.cbu = cbu;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String stringAmount() {
        String str = Double.toString(amount);
        String st = StringUtils.leftPad(str, 20, "0");
        st = st.replace(".", "0");
        return st;
    }

    @Override
    public String toString() {
        return name + ";" + surname + ";" + dni + ";" + cbu + ";" + accountNumber + ";" + currency + ";" + stringAmount();
    }
}
