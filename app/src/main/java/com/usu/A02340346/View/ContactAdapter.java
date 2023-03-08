package com.usu.A02340346.View;

import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;
import androidx.recyclerview.widget.RecyclerView;
import com.usu.A02340346.Model.ContactModel;
import com.usu.A02340346.R;
import java.util.Collections;
import java.util.Comparator;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {
    private final ObservableArrayList<ContactModel> contactModels;
    private final OnContactCardClicked listener;
    private final OnDelteButtonClicked deleteListener;

    public ContactAdapter(ObservableArrayList<ContactModel> contactModels, OnContactCardClicked listener, OnDelteButtonClicked deleteListener){
        this.deleteListener = deleteListener;
        this.contactModels = contactModels;
        this.listener = listener;

        contactModels.addOnListChangedCallback(new ObservableList.OnListChangedCallback<ObservableList<ContactModel>>() {
            @Override
            public void onChanged(ObservableList<ContactModel> sender) {
                notifyDataSetChanged();
            }

            @Override
            public void onItemRangeChanged(ObservableList<ContactModel> sender, int positionStart, int itemCount) {
                notifyItemRangeChanged(positionStart, itemCount);
            }

            @Override
            public void onItemRangeInserted(ObservableList<ContactModel> sender, int positionStart, int itemCount) {
                notifyItemRangeInserted(positionStart, itemCount);
            }

            @Override
            public void onItemRangeMoved(ObservableList<ContactModel> sender, int fromPosition, int toPosition, int itemCount) {
                notifyItemMoved(fromPosition, toPosition);
            }

            @Override
            public void onItemRangeRemoved(ObservableList<ContactModel> sender, int positionStart, int itemCount) {
                notifyItemRangeRemoved(positionStart, itemCount);
            }
        });
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ContactViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_card, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        // sort the observable array list using the comparator class
        Comparator<ContactModel> contactCompare = Comparator.comparing(ContactModel::getFirstName);
        Collections.sort(contactModels, contactCompare);
        ContactModel contactModel = contactModels.get(position);

        TextView firstName = holder.itemView.findViewById(R.id.contactCardFirstName);
        TextView lastName = holder.itemView.findViewById(R.id.contactCardLastName);
        TextView phoneNumber = holder.itemView.findViewById(R.id.contactCardPhoneNumber);
        TextView background = holder.itemView.findViewById(R.id.background);
        TextView options = holder.itemView.findViewById(R.id.optionsButton);
        TextView profile = holder.itemView.findViewById(R.id.profile);
        TextView delete = holder.itemView.findViewById(R.id.deleteContact);
        TextView backButton = holder.itemView.findViewById(R.id.cancelContact);
        ConstraintLayout layout = holder.itemView.findViewById(R.id.layout);

        char firstLetter = contactModel.firstName.charAt(0);
        profile.setText(firstLetter + "");
        firstName.setText(contactModel.firstName);
        lastName.setText(contactModel.lastName);
        phoneNumber.setText(contactModel.phoneNumber);

        background.setOnClickListener(button -> {
            listener.onClick(contactModel);
            background.setElevation(0);
        });

        options.setOnClickListener(button -> {
            TransitionManager.beginDelayedTransition(layout);
            options.setVisibility(View.GONE);
            layout.setVisibility(View.VISIBLE);
            delete.setOnClickListener(onClick -> {
                deleteListener.onClick(contactModel);
            });
        });

        backButton.setOnClickListener(onClick -> {
            TransitionManager.beginDelayedTransition(layout);
            options.setVisibility(View.VISIBLE);
            layout.setVisibility(View.GONE);
        });
    }

    @Override
    public int getItemViewType(int position) { return position; }

    @Override
    public int getItemCount() {
        return contactModels.size();
    }

    public interface OnContactCardClicked{ void onClick(ContactModel contact);}
    public interface OnDelteButtonClicked{ void onClick(ContactModel contact);}

    class ContactViewHolder extends RecyclerView.ViewHolder {
        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
        }}
}

