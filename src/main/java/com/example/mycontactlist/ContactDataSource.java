package com.example.mycontactlist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.SQLException;

public class ContactDataSource {

    private SQLiteDatabase database;
    private ContactDBHelper dbHelper;

    public ContactDataSource(Context context) {
        dbHelper = new ContactDBHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    // INSERT
    public boolean insertContact(Contact c) {

        boolean didSucceed = false;

        try {

            ContentValues initialValues = new ContentValues();

            initialValues.put("contactname", c.getContactName());
            initialValues.put("streetaddress", c.getStreetAddress());
            initialValues.put("city", c.getCity());
            initialValues.put("state", c.getState());
            initialValues.put("zipcode", c.getZipCode());
            initialValues.put("phonenumber", c.getPhoneNumber());
            initialValues.put("cellnumber", c.getCellNumber());
            initialValues.put("email", c.getEmail());

            initialValues.put("birthday",
                    String.valueOf(c.getBirthday().getTimeInMillis()));

            didSucceed = database.insert(
                    "contact",
                    null,
                    initialValues) > 0;

        }
        catch (Exception e) {
            didSucceed = false;
        }

        return didSucceed;
    }


    public boolean updateContact(Contact c) {

        boolean didSucceed = false;

        try {

            Long rowId = (long) c.getContactID();

            ContentValues updateValues = new ContentValues();

            updateValues.put("contactname", c.getContactName());
            updateValues.put("streetaddress", c.getStreetAddress());
            updateValues.put("city", c.getCity());
            updateValues.put("state", c.getState());
            updateValues.put("zipcode", c.getZipCode());
            updateValues.put("phonenumber", c.getPhoneNumber());
            updateValues.put("cellnumber", c.getCellNumber());
            updateValues.put("email", c.getEmail());

            updateValues.put("birthday",
                    String.valueOf(c.getBirthday().getTimeInMillis()));

            didSucceed = database.update(
                    "contact",
                    updateValues,
                    "_id=" + rowId,
                    null) > 0;

        }
        catch (Exception e) {
            didSucceed = false;
        }

        return didSucceed;
    }


    public int getLastContactID() {

        int lastId;

        try {

            String query = "SELECT MAX(_id) FROM contact";

            Cursor cursor = database.rawQuery(query, null);

            if (cursor.moveToFirst()) {
                lastId = cursor.getInt(0);
            }
            else {
                lastId = -1;
            }

            cursor.close();
        }
        catch (Exception e) {
            lastId = -1;
        }

        return lastId;
    }
}
