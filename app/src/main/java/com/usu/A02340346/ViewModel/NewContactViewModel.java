package com.usu.A02340346.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.usu.A02340346.Repository.Repository;
import javax.inject.Inject;
import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class NewContactViewModel extends ViewModel {
    Repository repository;
    MutableLiveData<Boolean> nameIsNotEmpty = new MutableLiveData<>();

    @Inject
    public NewContactViewModel(Repository repository){
        nameIsNotEmpty.setValue(false);
        this.repository = repository;
    }

    public void checkName(String firstName){
        new Thread(() ->{
            nameIsNotEmpty.postValue(false);
            if(!firstName.isEmpty()){
                nameIsNotEmpty.postValue(true);
            }
        }).start();
    }

    public MutableLiveData<Boolean> getNameIsEmpty() { return nameIsNotEmpty; }

    public void saveContact(String firstName, String lastName, String company, String phoneNumber, String email, String address, String birthday){
        new Thread(()->{
            this.repository.saveContactInformation(firstName, lastName, company, phoneNumber, email, address, birthday);
        }).start();
    }
}
