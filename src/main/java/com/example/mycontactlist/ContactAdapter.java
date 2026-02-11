package com.example.mycontactlist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {

    private ArrayList<Contact> contactData;
    private View.OnClickListener mOnItemClickListener;

    public class ContactViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewContact;
        public TextView textPhone;
        public Button deleteButton;

        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewContact = itemView.findViewById(R.id.textViewName);
            textPhone = itemView.findViewById(R.id.textPhoneNumber);
            deleteButton = itemView.findViewById(R.id.buttonDeleteContact);

            itemView.setTag(this);
            itemView.setOnClickListener(mOnItemClickListener);
        }

        public TextView getContactTextView() {
            return textViewContact;
        }

        public TextView getPhoneTextView() {
            return textPhone;
        }

        public Button getDeleteButton() {
            return deleteButton;
        }
    }

    public ContactAdapter(ArrayList<Contact> arrayList) {
        contactData = arrayList;
    }

    public void setOnItemClickListener(View.OnClickListener itemClickListener) {
        mOnItemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);

        return new ContactViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {

        holder.getContactTextView().setText(contactData.get(position).getContactName());
        holder.getPhoneTextView().setText(contactData.get(position).getPhoneNumber());


    }

    @Override
    public int getItemCount() {
        return contactData.size();
    }
}