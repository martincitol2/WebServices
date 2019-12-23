package com.howtodoinjava.xml.school;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {

    public ObjectFactory() {
    }

    public ClientDetailsRequest createClientDetailsRequest() {
        return new ClientDetailsRequest();
    }

    public ClientDetailsResponse createClientDetailsResponse() {
        return new ClientDetailsResponse();
    }

    public Client createClient() {
        return new Client();
    }


}
