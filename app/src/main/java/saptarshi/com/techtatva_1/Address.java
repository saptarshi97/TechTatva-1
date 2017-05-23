package saptarshi.com.techtatva_1;

import io.realm.RealmObject;

public class Address extends RealmObject{
    private String street;
    public String getStreet() {
        return street;
    }

    private String suite;
    public String getSuite() {
        return suite;
    }

    private String city;
    public String getCity() {
        return city;
    }

    private String zipcode;
    public String getZipcode() {
        return zipcode;
    }

}