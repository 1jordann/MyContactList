package com.example.mycontactlist;

import android.location.Address;
import android.location.Geocoder;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;

public class ContactMapActivity {
    private void initGetLocationButton() {
        Button locationButton = (Button) findViewById(R.id.buttonGetLocation);
        locationButton.setOnClicklistener(new View.OnClicklistener() {
            @Override
            public void onClick(View view) {
                EditText editAddress = (EditText) findViewById(R.id.editAddress);
                EditText editCity = (EditText) findViewById(R.id.editCity);
                EditText editState = (EditText) findViewById(R.id.editState);
                EditText editZipCode = (EditText) findViewById(R.id.editZipCode);

                String address = editAddress.getText().toString() + ", " + editCity.getText().toString() + ", " +
                        editState.getText().toString() + ", " +
                        editZipCode.getText().toString();


                List<Address> addresses = null;
                Geocoder geo = new Geocoder(ContactMapActivity.this);
                try {
                    addresses = geo.getFromLocationName(address, 1);
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                TextView txtLatitude = (TextView) findViewById(R.id.textLatitude);
                TextView txtLongitude = (TextView) findViewById(R.id.textLongitude);

                txtLatitude.setText(String.valueOf(addresses.get(0).getLatitude()));
                txtLongitude.setText(String.valueOf(addresses.get(0).getLongitude()));

            }
        });
    }
}
