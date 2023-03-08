package com.usu.A02340346.Repository;

import android.content.Context;
import androidx.room.Room;
import com.usu.A02340346.Model.AppDataBase;
import com.usu.A02340346.Model.ContactModel;
import java.util.ArrayList;
import javax.inject.Inject;
import javax.inject.Singleton;
import dagger.hilt.android.qualifiers.ApplicationContext;

@Singleton
public class Repository {
    private ArrayList<ContactModel> uiDataBase;
    private final AppDataBase db;

    @Inject
    public Repository(@ApplicationContext Context context) {
        db = Room.databaseBuilder(context, AppDataBase.class, "contacts-database").build();
    }

    public void saveContactInformation(
            String firstName, String lastName, String company,
            String phoneNumber, String email, String address, String birthday){

        ContactModel dataBase = new ContactModel();

        //This makes sure the data base is not empty the UI component
        if(uiDataBase.size() > 0){dataBase.id = uiDataBase.get(uiDataBase.size() - 1).id + 1;}
        else {dataBase.id = 1;}
        dataBase.firstName = firstName;
        dataBase.lastName = lastName;
        dataBase.company = company;
        dataBase.phoneNumber = phoneNumber;
        dataBase.email = email;
        dataBase.address = address;
        dataBase.birthday = birthday;

        // save to database and update the UI component
        db.getContactDAO().createContact(dataBase);
        uiDataBase.add(dataBase);
    }

    public void deleteContactInformation(ContactModel contact){
        db.getContactDAO().deleteContact(contact);
        uiDataBase.remove(contact);
    }

    public void updateContactInformation(ContactModel contact){
        db.getContactDAO().updateContact(contact);
    }

    public ArrayList<ContactModel> getContactInformation(){
        if(uiDataBase == null) {
            uiDataBase = (ArrayList<ContactModel>) db.getContactDAO().getContacts();
        }
        return uiDataBase;
    }
}
