package com.usu.A02340346.View;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.usu.A02340346.R;
import com.usu.A02340346.ViewModel.ContactViewModel;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ContactFragment extends Fragment{

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ContactViewModel viewModel = new ViewModelProvider(getActivity()).get(ContactViewModel.class);
        View view = inflater.inflate(R.layout.contact_fragment, container, false);
        NavController controller = NavHostFragment.findNavController(this);

        view.findViewById(R.id.button).setOnClickListener(button -> { controller.navigate(R.id.action_mainFragment_to_newContactFragment); });

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setAdapter(new ContactAdapter(
                viewModel.getContactInformation(),
                contact -> {
                    viewModel.setContact(contact);
                    controller.navigate(R.id.action_mainFragment_to_contactInformationCard);},
                contact -> {
                    viewModel.deleteContact(contact);
                }));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }
}

