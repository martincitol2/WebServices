package com.santander.rio.soapxml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"descripcion", "codigoError"})
@XmlRootElement(name = "ClientDetailsResponse")
public class ClientDetailsResponse {

    private String descripcion;

    private String codigoError;

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setCodigoError(String codigoError) {
        this.codigoError = codigoError;
    }
}
