package com.usu.A02340346.View;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import com.usu.A02340346.R;
import com.usu.A02340346.ViewModel.NewContactViewModel;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class NewContactFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        NewContactViewModel viewModel = new ViewModelProvider(getActivity()).get(NewContactViewModel.class);
        View view = inflater.inflate(R.layout.new_contact_fragment, container, false);

        // All elements to save
        EditText firstName = view.findViewById(R.id.name);
        EditText lastName = view.findViewById(R.id.lastName);
        EditText company = view.findViewById(R.id.company);
        EditText phoneNumber = view.findViewById(R.id.phone);
        EditText email = view.findViewById(R.id.email);
        EditText address = view.findViewById(R.id.address);
        EditText birthday = view.findViewById(R.id.birthday);

        // This checks if the user is typing in a name
        firstName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().trim().length() == 0) {
                    viewModel.checkName(firstName.getText().toString());
                }
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                viewModel.checkName(firstName.getText().toString());
            }
            @Override
            public void afterTextChanged(Editable editable) {
                viewModel.checkName(firstName.getText().toString());
            }});

        // If the first name is empty, then the button will be disabled
        viewModel.getNameIsEmpty().observe(this, state -> {
            if(state) {
                TextView textView = view.findViewById(R.id.done);
                Drawable d = getResources().getDrawable(R.drawable.done_button);
                textView.setBackground(d);

                // Done button
                view.findViewById(R.id.done).setOnClickListener(done -> {
                    viewModel.saveContact(
                            firstName.getText().toString(), lastName.getText().toString(),
                            company.getText().toString(), phoneNumber.getText().toString(),
                            email.getText().toString(), address.getText().toString(),
                            birthday.getText().toString()
                    );
                    textView.setElevation(0);
                    NavHostFragment.findNavController(this).popBackStack();
                });
            }else{
                TextView textView = view.findViewById(R.id.done);
                Drawable d = getResources().getDrawable(R.drawable.done_not_completed);
                textView.setBackground(d);
                view.findViewById(R.id.done).setOnClickListener(done -> {/* Button is disabled*/});
            }});

        // Cancel button
        view.findViewById(R.id.cancel).setOnClickListener(cancel -> {
            TextView textView = view.findViewById(R.id.cancel);
            textView.setElevation(0);
            NavHostFragment.findNavController(this).popBackStack();
        });

        return view;
    }
}