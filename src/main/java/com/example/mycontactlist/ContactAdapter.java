package com.example.mycontactlist;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter{
    private ArrayList<String> contactData;
    public class ContactViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewContact;

        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewContact = itemView.findViewById(R.id.textViewName):
        }

        public TextView getContactTextView() {
            return textViewContact;
        }
    }
    public ContactAdapter (ArrayList<String> arraylist){
        contactData = arraylist;
    }
        @NonNull
        @Override
