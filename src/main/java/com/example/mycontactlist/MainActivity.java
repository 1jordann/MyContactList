package com.example.mycontactlist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity
        implements DatePickerDialog.SaveDateListener {


    private Contact currentContact;
    final int PERMISSION_REQUEST_PHONE = 102;
    final int PERMISSION_REQUEST_CAMERA = 103;
    final int CAMERA_REQUEST = 1888;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        currentContact = new Contact();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int contactID = extras.getInt("contactID");
            initContact(contactID);
        }


        initListButton();
        initMapButton();
        initSettingsButton();
        initToggleButton();
        initChangeDateButton();
        initCallFunction();
        initImageButton();


        initSaveButton();

        // attach TextWatchers so typing updates currentContact
        initTextChangedEvents();

        setForEditing(false);

    }


    private void initListButton() {
        ImageButton ibList = findViewById(R.id.imageButtonList);

        ibList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ContactListActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    private void initMapButton() {
        ImageButton ibMap = findViewById(R.id.imageButtonMap);

        ibMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ContactMapActivity.class);
                if (currentContact.getContactID() == 1) {
                    Toast.makeText(getBaseContext(), "Contact must be saved before it can be mapped", Toast.LENGTH_LONG).show();
                } else {
                    intent.putExtra("contactid", currentContact.getContactID());
                }
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    private void initSettingsButton() {
        ImageButton ibSettings = findViewById(R.id.imageButtonSettings);

        ibSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ContactSettingsActivity.class);
                startActivity(intent);
            }
        });
    }

    // TOOLBAR BUTTONS

    private void initToggleButton() {
        final ToggleButton editToggle = findViewById(R.id.toggleButtonEdit);

        editToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setForEditing(editToggle.isChecked());
            }
        });
    }

    private void initChangeDateButton() {
        Button changeDate = findViewById(R.id.buttonBirthday);

        changeDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getSupportFragmentManager();
                DatePickerDialog datePickerDialog = new DatePickerDialog();
                datePickerDialog.show(fm, "DatePick");
            }
        });
    }


    // for da SAVE BUTTON

    private void initSaveButton() {
        Button saveButton = findViewById(R.id.buttonSave);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean wasSuccessful;
                ContactDataSource ds = new ContactDataSource(MainActivity.this);

                try {
                    ds.open();

                    if (currentContact.getContactID() == -1) {
                        wasSuccessful = ds.insertContact(currentContact);

                        if (wasSuccessful) {
                            int newId = ds.getLastContactID();
                            currentContact.setContactID(newId);
                        }
                    } else {
                        wasSuccessful = ds.updateContact(currentContact);
                    }

                    ds.close();
                } catch (Exception e) {
                    wasSuccessful = false;
                }

                if (wasSuccessful) {
                    ToggleButton editToggle = findViewById(R.id.toggleButtonEdit);
                    editToggle.toggle();
                    setForEditing(false);
                }
            }
        });
    }


    private void initTextChangedEvents() {

        final EditText etContactName = findViewById(R.id.editName);
        etContactName.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
                currentContact.setContactName(etContactName.getText().toString());
            }

            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                // Auto-generated method stub
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Auto-generated method stub
            }

        });

        final EditText etStreetAddress = findViewById(R.id.editAddress);
        etStreetAddress.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
                currentContact.setStreetAddress(etStreetAddress.getText().toString());
            }

            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                // Auto-generated method stub
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Auto-generated method stub
            }

        });

        final EditText etCity = findViewById(R.id.editCity);
        etCity.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
                currentContact.setCity(etCity.getText().toString());
            }

            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                // Auto-generated method stub
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Auto-generated method stub
            }

        });

        final EditText etState = findViewById(R.id.editState);
        etState.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
                currentContact.setState(etState.getText().toString());
            }

            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                // Auto-generated method stub
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Auto-generated method stub
            }

        });

        final EditText etZipCode = findViewById(R.id.editZipCode);
        etZipCode.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
                currentContact.setZipCode(etZipCode.getText().toString());
            }

            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                // Auto-generated method stub
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Auto-generated method stub
            }

        });

        final EditText etPhoneNumber = findViewById(R.id.editHome);
        etPhoneNumber.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
                currentContact.setPhoneNumber(etPhoneNumber.getText().toString());
            }

            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                // Auto-generated method stub
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Auto-generated method stub
            }

        });

        final EditText etCellNumber = findViewById(R.id.editCell);
        etCellNumber.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
                currentContact.setCellNumber(etCellNumber.getText().toString());
            }

            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                // Auto-generated method stub
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Auto-generated method stub
            }

        });

        final EditText etEmail = findViewById(R.id.editEMail);
        etEmail.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
                currentContact.setEmail(etEmail.getText().toString());
            }

            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                // Auto-generated method stub
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Auto-generated method stub
            }

        });
    }


    //toggle for edit ENABLE / DISABLE

    private void setForEditing(boolean enabled) {

        EditText editName = findViewById(R.id.editName);
        EditText editAddress = findViewById(R.id.editAddress);
        EditText editCity = findViewById(R.id.editCity);
        EditText editState = findViewById(R.id.editState);
        EditText editZipCode = findViewById(R.id.editZipCode);
        EditText editPhone = findViewById(R.id.editHome);
        EditText editCell = findViewById(R.id.editCell);
        EditText editEmail = findViewById(R.id.editEMail);

        Button buttonChange = findViewById(R.id.buttonBirthday);
        Button buttonSave = findViewById(R.id.buttonSave);

        editName.setEnabled(enabled);
        editAddress.setEnabled(enabled);
        editCity.setEnabled(enabled);
        editState.setEnabled(enabled);
        editZipCode.setEnabled(enabled);

        editEmail.setEnabled(enabled);

        buttonChange.setEnabled(enabled);
        buttonSave.setEnabled(enabled);
        ImageButton picture = findViewById(R.id.imageContact);
        picture.setEnabled(enabled);


        if (enabled) {
            editName.requestFocus();
            editPhone.setInputType(InputType.TYPE_CLASS_PHONE);
            editCell.setInputType(InputType.TYPE_CLASS_PHONE);
        } else {
            editPhone.setInputType(InputType.TYPE_NULL);
            editCell.setInputType(InputType.TYPE_NULL);
        }
    }


    @Override
    public void didFinishDatePickerDialog(Calendar selectedTime) {
        currentContact.setBirthday(selectedTime);

        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.US);
        String dateString = sdf.format(selectedTime.getTime());

        TextView tvBirthday = findViewById(R.id.textBirthday);
        tvBirthday.setText(dateString);
    }

    private void initContact(int id) {

        ContactDataSource ds = new ContactDataSource(MainActivity.this);

        try {
            ds.open();
            currentContact = ds.getSpecificContact(id);
            ds.close();
        } catch (Exception e) {
            Toast.makeText(this, "Load Contact Failed", Toast.LENGTH_LONG).show();
        }

        EditText editName = findViewById(R.id.editName);
        EditText editAddress = findViewById(R.id.editAddress);
        EditText editCity = findViewById(R.id.editCity);
        EditText editState = findViewById(R.id.editState);
        EditText editZipCode = findViewById(R.id.editZipCode);
        EditText editPhone = findViewById(R.id.editHome);
        EditText editCell = findViewById(R.id.editCell);
        EditText editEmail = findViewById(R.id.editEMail);
        TextView birthDay = findViewById(R.id.textBirthday);

        editName.setText(currentContact.getContactName());
        editAddress.setText(currentContact.getStreetAddress());
        editCity.setText(currentContact.getCity());
        editState.setText(currentContact.getState());
        editZipCode.setText(currentContact.getZipCode());

        editPhone.setText(currentContact.getPhoneNumber());
        editCell.setText(currentContact.getCellNumber());
        editEmail.setText(currentContact.getEmail());

        birthDay.setText(DateFormat.format("MM/dd/yyyy", currentContact.getBirthday().getTimeInMillis()).toString());
        ImageButton picture = findViewById(R.id.imageContact);
        if (currentContact.getPicture() != null) {
            picture.setImageBitmap(currentContact.getPicture());
        }
        else {
            picture.setImageResource(R.drawable.photoicon);
        }
    }

    private void initCallFunction() {
        EditText editPhone = (EditText) findViewById(R.id.editHome);
        editPhone.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View arg0) {
                checkPhonePermission(currentContact.getPhoneNumber());
                return false;
            }
        });
        EditText editCell = (EditText) findViewById(R.id.editCell);
        editCell.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View arg0) {
                checkPhonePermission(currentContact.getCellNumber());
                return false;
            }
        });


    }

    private void checkPhonePermission(String phoneNumber) {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ContextCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, android.Manifest.permission.CALL_PHONE)) {
                    Snackbar.make(findViewById(R.id.activity_main), "MyContactList requires this permission to place a call from the app.",
                                    Snackbar.LENGTH_INDEFINITE).setAction("OK", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{android.Manifest.permission.CALL_PHONE},
                                            PERMISSION_REQUEST_PHONE);
                                }
                            })
                            .show();
                } else {
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{android.Manifest.permission.CALL_PHONE}, PERMISSION_REQUEST_PHONE);
                }
            } else {
                callContact(phoneNumber);
            }
        } else {
            callContact(phoneNumber);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_PHONE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(MainActivity.this, "You may now call from this app.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "You will not be able to make calls " + "from this app.", Toast.LENGTH_LONG).show();
                }
            }
            case PERMISSION_REQUEST_CAMERA: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    takePhoto();
                } else {
                    Toast.makeText(MainActivity.this, "You will not be able to save contact picture from this app.", Toast.LENGTH_LONG).show();
                }
                return;
            }
        }
    }

    public void takePhoto() {
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMERA_REQUEST);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST) {
            if (resultCode == RESULT_OK) {
                Bitmap photo = (Bitmap) data.getExtras().get("data");
                Bitmap scaledPhoto = Bitmap.createScaledBitmap(photo, 144, 144, true);
                ImageButton imageContact = (ImageButton) findViewById(R.id.imageContact);
                imageContact.setImageBitmap(scaledPhoto);
                currentContact.setPicture(scaledPhoto);
            }
        }
    }

    private void callContact(String phoneNumber){
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        if ( Build.VERSION.SDK_INT >= 23 && ContextCompat.checkSelfPermission(getBaseContext(), android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
            return;
        }
        else{
            startActivity(intent);
        }
    }
    private void initImageButton() {
        ImageButton iB = findViewById(R.id.imageContact);
        iB.setOnClickListener (new View.OnClickListener () {
            public void onClick (View v) {
                if (Build.VERSION.SDK_INT >= 23) {
                    if (ContextCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.CAMERA) !=
                            PackageManager.PERMISSION_GRANTED) {
                        if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, android.Manifest.permission.CAMERA)) {
                            Snackbar.make(findViewById(R.id.activity_main), "The app needs permission to take pictures.", Snackbar.LENGTH_INDEFINITE).setAction("Ok", new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            ActivityCompat.requestPermissions(MainActivity.this, new String[]
                                                    {android.Manifest.permission.CAMERA}, PERMISSION_REQUEST_CAMERA);
                                        }
                                    })
                                    .show();
                        } else {
                            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CAMERA}, PERMISSION_REQUEST_CAMERA);
                        }
                    }
                    else {
                        takePhoto();
                    }
                } else {
            takePhoto();
        }
            }
        });
    }
}
