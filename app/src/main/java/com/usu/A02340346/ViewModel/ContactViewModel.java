package com.usu.A02340346.ViewModel;

import android.os.Handler;
import androidx.databinding.ObservableArrayList;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.usu.A02340346.Model.ContactModel;
import com.usu.A02340346.Repository.Repository;
import java.util.ArrayList;
import javax.inject.Inject;
import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class ContactViewModel extends ViewModel {
    private final Repository repository;
    private final ObservableArrayList<ContactModel> contacts = new ObservableArrayList<>();
    private MutableLiveData<ContactModel> currentContact = new MutableLiveData<>();
    private final Handler handler = new Handler();

    @Inject
    public ContactViewModel(Repository repository){ this.repository = repository; }

    public ObservableArrayList<ContactModel> getContactInformation(){
        this.contacts.clear();
        new Thread(()->{
            ArrayList<ContactModel> repoContacts = this.repository.getContactInformation();
            handler.post(()->{
                this.contacts.addAll(repoContacts);
            });
        }).start();
        return this.contacts;
    }

    public void deleteContact(ContactModel contact){
        new Thread(()->{
            repository.deleteContactInformation(contact);
            handler.post(()->{
                contacts.remove(contact);
            });
        }).start();
    }

    public void setContact(ContactModel contact){
        currentContact.setValue(contact);
    }

    public void updateContact(
            int id, String firstName, String lastName, String company,
            String phoneNumber, String email, String address, String birthday) {

        new Thread(() -> {
            ContactModel update = currentContact.getValue();
            update.id = id;
            update.firstName = firstName;
            update.lastName = lastName;
            update.company = company;
            update.phoneNumber =phoneNumber;
            update.email = email;
            update.birthday = birthday;
            update.address = address;
            repository.updateContactInformation(update);
            currentContact.postValue(update);
        }).start();
    }

    public LiveData<ContactModel> getContact(){
        return currentContact;
    }
}
