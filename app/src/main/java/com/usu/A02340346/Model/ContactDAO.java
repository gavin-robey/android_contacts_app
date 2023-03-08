package com.usu.A02340346.Model;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;

@Dao
public interface ContactDAO {

    // Create
    @Insert
    public void createContact(ContactModel contact);

    // Read
    @Query("SELECT * FROM contactmodel")
    public List<ContactModel> getContacts();

    //Update
    @Update
    public void updateContact(ContactModel contact);

    // Delete
    @Delete
    public void deleteContact(ContactModel contact);

}
