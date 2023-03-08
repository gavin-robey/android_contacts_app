package com.usu.A02340346.View;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import com.usu.A02340346.R;
import com.usu.A02340346.ViewModel.ContactViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class editContact extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        ContactViewModel viewModel = new ViewModelProvider(getActivity()).get(ContactViewModel.class);
        View view = inflater.inflate(R.layout.edit_contact_fragment, container, false);

        // All elements to save
        EditText firstName = view.findViewById(R.id.name);
        EditText lastName = view.findViewById(R.id.lastName);
        EditText company = view.findViewById(R.id.company);
        EditText phoneNumber = view.findViewById(R.id.phone);
        EditText email = view.findViewById(R.id.email);
        EditText address = view.findViewById(R.id.address);
        EditText birthday = view.findViewById(R.id.birthday);
        TextView done = view.findViewById(R.id.done);

        // Cancel button
        view.findViewById(R.id.cancel).setOnClickListener(cancel -> {
            TextView textView = view.findViewById(R.id.cancel);
            textView.setElevation(0);
            NavHostFragment.findNavController(this).popBackStack();
        });

        viewModel.getContact().observe(this, contact -> {
            if(!contact.firstName.equals("")){ firstName.setText(contact.firstName); }
            if(!contact.lastName.equals("")){ lastName.setText(contact.lastName); }
            if(!contact.phoneNumber.equals("")){ phoneNumber.setText(contact.phoneNumber); }
            if(!contact.email.equals("")){ email.setText(contact.email); }
            if(!contact.company.equals("")){ company.setText(contact.company); }
            if(!contact.address.equals("")){ address.setText(contact.address); }
            if(!contact.birthday.equals("")){ birthday.setText(contact.birthday); }

            // update the data base
            done.setOnClickListener(button -> {
                viewModel.updateContact(
                        contact.id, firstName.getText().toString(), lastName.getText().toString(),
                        company.getText().toString(), phoneNumber.getText().toString(),
                        email.getText().toString(), address.getText().toString(), birthday.getText().toString()
                );
                NavHostFragment.findNavController(this).popBackStack();
            });
        });
        return view;
    }
}