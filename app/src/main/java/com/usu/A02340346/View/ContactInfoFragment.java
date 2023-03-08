package com.usu.A02340346.View;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.usu.A02340346.R;
import com.usu.A02340346.ViewModel.ContactViewModel;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ContactInfoFragment extends Fragment {
    private static final int REQUEST_CALL = 1;
    private TextView phoneNumber, email;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ContactViewModel viewModel = new ViewModelProvider(getActivity()).get(ContactViewModel.class);
        View view = inflater.inflate(R.layout.contact_info_fragment, container, false);
        NavController controller = NavHostFragment.findNavController(this);

        // check permissions
        if(ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
        }

        // Text components
        TextView fullName = view.findViewById(R.id.fullName);
        TextView nameTag = view.findViewById(R.id.nameTag);
        phoneNumber = view.findViewById(R.id.phone);
        email = view.findViewById(R.id.emailView);
        TextView company = view.findViewById(R.id.companyView);
        TextView address = view.findViewById(R.id.addressView);
        TextView birthday = view.findViewById(R.id.birthdayView);

        // button components
        TextView back = view.findViewById(R.id.cancel);
        TextView phoneButton = view.findViewById(R.id.phoneButton);
        TextView textButton = view.findViewById(R.id.textButton);
        TextView emailButton = view.findViewById(R.id.emailButton);
        TextView editButton = view.findViewById(R.id.editButton);

        phoneButton.setOnClickListener(button -> {
            String number = phoneNumber.getText().toString();
            if(!number.equals("No Phone Number")) {
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel: " + number));
                startActivity(intent);
            }else{
                Toast.makeText(getActivity(), "No phone number given", Toast.LENGTH_SHORT).show();
            }
        });

        textButton.setOnClickListener(button -> {
            String number = phoneNumber.getText().toString();
            if(!number.equals("No Phone Number")) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", number, null));
                intent.putExtra("sms_body", "");
                startActivity(intent);
            }else{
                Toast.makeText(getActivity(), "No Phone Number Given", Toast.LENGTH_SHORT).show();
            }
        });

        emailButton.setOnClickListener(button -> {
            String emailText = email.getText().toString();
            if(!emailText.equals("No Email")){
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{emailText});
                intent.setData(Uri.parse("mailto:"));
                if(intent.resolveActivity(getContext().getPackageManager()) != null){
                    startActivity(intent);
                }else{
                    Toast.makeText(getActivity(), "Email not available", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(getActivity(), "No Email Given", Toast.LENGTH_SHORT).show();
            }
        });

        editButton.setOnClickListener(button ->{ controller.navigate(R.id.action_contactInformationCard_to_editContactFragment); });
        back.setOnClickListener(button ->{ NavHostFragment.findNavController(this).popBackStack(); });


        viewModel.getContact().observe(this, contact -> {
            char firstLetter = contact.firstName.charAt(0);
            String name = contact.firstName + " " + contact.lastName;
            fullName.setText(name);
            if(!contact.lastName.equals("")){
                char lastLetter = contact.lastName.charAt(0);
                nameTag.setText(firstLetter + "" + lastLetter);
            }
            else{ nameTag.setText(firstLetter + "");}
            if(!contact.phoneNumber.equals("")){ phoneNumber.setText(contact.phoneNumber); }
            if(!contact.email.equals("")){ email.setText(contact.email); }
            if(!contact.company.equals("")){ company.setText(contact.company); }
            if(!contact.address.equals("")){ address.setText(contact.address); }
            if(!contact.birthday.equals("")){ birthday.setText(contact.birthday); }
        });
        return view;
    }
}