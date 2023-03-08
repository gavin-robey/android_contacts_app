package com.usu.A02340346.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Locale;

@Entity
public class ContactModel {

    @PrimaryKey
    public int id;

    @ColumnInfo
    public String firstName;

    @ColumnInfo
    public String lastName;

    @ColumnInfo
    public String company;

    @ColumnInfo
    public String phoneNumber;

    @ColumnInfo
    public String email;

    @ColumnInfo
    public String address;

    @ColumnInfo
    public String birthday;

    public String getFirstName(){
        return firstName.toLowerCase(Locale.ROOT);
    }
}
