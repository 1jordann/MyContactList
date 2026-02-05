package com.example.mycontactlist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class ContactSettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_settings);

        initSettings();
        initSortByClick();
        initSortOrderClick();
    }

    // Listing 5.1 style: initialize the activity based on saved settings
    private void initSettings() {

        String sortBy = getSharedPreferences("MyContactListPreferences",
                Context.MODE_PRIVATE).getString("sortfield", "contactname");

        String sortOrder = getSharedPreferences("MyContactListPreferences",
                Context.MODE_PRIVATE).getString("sortorder", "ASC");

        RadioButton rbName = findViewById(R.id.radioName);
        RadioButton rbCity = findViewById(R.id.radioCity);
        RadioButton rbBirthDay = findViewById(R.id.radioBirthday);

        if (sortBy.equalsIgnoreCase("contactname")) {
            rbName.setChecked(true);
        }
        else if (sortBy.equalsIgnoreCase("city")) {
            rbCity.setChecked(true);
        }
        else {
            rbBirthDay.setChecked(true);
        }

        RadioButton rbAscending = findViewById(R.id.radioAscending);
        RadioButton rbDescending = findViewById(R.id.radioDescending);

        if (sortOrder.equalsIgnoreCase("ASC")) {
            rbAscending.setChecked(true);
        }
        else {
            rbDescending.setChecked(true);
        }
    }

    // Listing 5.2 style: save sort field choice
    private void initSortByClick() {

        RadioGroup rgSortBy = findViewById(R.id.radioGroupSortBy);

        rgSortBy.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                RadioButton rbName = findViewById(R.id.radioName);
                RadioButton rbCity = findViewById(R.id.radioCity);

                if (rbName.isChecked()) {
                    getSharedPreferences("MyContactListPreferences", Context.MODE_PRIVATE)
                            .edit()
                            .putString("sortfield", "contactname")
                            .apply();
                }
                else if (rbCity.isChecked()) {
                    getSharedPreferences("MyContactListPreferences", Context.MODE_PRIVATE)
                            .edit()
                            .putString("sortfield", "city")
                            .apply();
                }
                else {
                    getSharedPreferences("MyContactListPreferences", Context.MODE_PRIVATE)
                            .edit()
                            .putString("sortfield", "birthday")
                            .apply();
                }
            }
        });
    }

    // Listing 5.2 style: save sort order choice
    private void initSortOrderClick() {

        RadioGroup rgSortOrder = findViewById(R.id.radioGroupSortOrder);

        rgSortOrder.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                RadioButton rbAscending = findViewById(R.id.radioAscending);

                if (rbAscending.isChecked()) {
                    getSharedPreferences("MyContactListPreferences", Context.MODE_PRIVATE)
                            .edit()
                            .putString("sortorder", "ASC")
                            .apply();
                }
                else {
                    getSharedPreferences("MyContactListPreferences", Context.MODE_PRIVATE)
                            .edit()
                            .putString("sortorder", "DESC")
                            .apply();
                }
            }
        });
    }
}
