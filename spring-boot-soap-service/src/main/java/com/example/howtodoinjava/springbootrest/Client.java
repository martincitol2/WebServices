package com.example.howtodoinjava.springbootrest;

import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;

@Entity(name = "cliente")
@NamedStoredProcedureQueries(value = {@NamedStoredProcedureQuery(name = "procedure-one", procedureName = "findAllClients"),
        @NamedStoredProcedureQuery(name = "procedure-two", procedureName = "transferir", parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "cbu_llegada", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "cbu_salida", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "importe", type = Double.class)
        })
})
public class Client {

    private String name;

    private String surname;

    private String dni;

    @Id
    private String cbu;

    @Column(name = "account_number")
    private String accountNumber;

    private String currency;

    private Double amount;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getCbu() {
        return cbu;
    }

    public void setCbu(String cbu) {
        this.cbu = cbu;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Double getAmount() {
        return amount;
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
