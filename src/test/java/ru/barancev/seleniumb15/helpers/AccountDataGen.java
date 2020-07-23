package ru.barancev.seleniumb15.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.time.ZonedDateTime;
import java.util.Date;

public class AccountDataGen {
    //Date date = new Date();
    public long var = ZonedDateTime.now().toInstant().toEpochMilli();
    public String firstname = "Vova" + var;
    public String lastname = "Putya" + var;

    public long getVar() {
        return var;
    }

    public String getLastname() {
        return lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getEmail() {
        return email;
    }

    public String email = "VovaPutya"+var+"@hren.vam";


}