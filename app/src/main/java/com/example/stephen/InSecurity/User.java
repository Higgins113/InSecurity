package com.example.stephen.InSecurity;

/**
 * Created by Stephen on 20/03/2016.
 */
public class User
{
    String name, username, password, address;

    public User (String name, String username, String password, String address)
    {
        this.name = name;
        this.username = username;
        this.password = password;
        this.address = address;
    }

   public User (String username, String password)
    {
        this.username = username;
        this.password = password;
    }

    public String getName()
    {
        return name;
    }

    public String getUsername()
    {
        return username;
    }

    public String getPassword()
    {
        return password;
    }

    public String getAddress()
    {
        return address;
    }

}
