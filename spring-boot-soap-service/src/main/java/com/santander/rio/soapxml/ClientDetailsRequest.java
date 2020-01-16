package com.santander.rio.soapxml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"name", "surname", "dni", "cbu", "accountNumber", "currency", "amount"})
@XmlRootElement(name = "ClientDetailsRequest")
public class ClientDetailsRequest {

    @XmlElement(required = true)
    private String name;
    @XmlElement(required = true)
    private String surname;
    @XmlElement(required = true)
    private String dni;
    @XmlElement(required = true)
    private String cbu;
    @XmlElement(required = true)
    private String accountNumber;
    @XmlElement(required = true)
    private String currency;
    @XmlElement(required = true)
    private Double amount;

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getDni() {
        return dni;
    }

    public String getCbu() {
        return cbu;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getCurrency() {
        return currency;
    }

    public Double getAmount() {
        return amount;
    }
}
