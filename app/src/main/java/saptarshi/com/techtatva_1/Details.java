package saptarshi.com.techtatva_1;

import io.realm.RealmObject;

public class Details extends RealmObject
{
    public int id;
    private String name;
    private String username;
    private String email;
    private String phone;
    private String website;
    private Address address;

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getUsername() {
        return username;
    }
    public String getEmail() {
        return email;
    }
    public String getPhone() {
        return phone;
    }
    public String getWebsite() {return website;}

    public Address getAddress() {return address;}

}
