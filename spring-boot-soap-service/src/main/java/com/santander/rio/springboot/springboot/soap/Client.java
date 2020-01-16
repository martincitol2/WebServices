package com.santander.rio.springboot.springboot.soap;

import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;

@Entity(name = "cliente")
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
