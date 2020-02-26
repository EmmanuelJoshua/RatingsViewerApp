package com.example.spark.ratingsapp;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class login extends AppCompatActivity {

    public int dbuserID;

    public static int userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public boolean validateLogin(String username, String password) {
        String TABLE_NAME = "users";
     String COLUMN_NAME_USERID = "userid";
     String COLUMN_NAME_USERNAME = "username";
     String COLUMN_NAME_PASSWORD = "password";

     Uri uri = Uri.parse("content://com.example.spark.myapplication.PROVIDER/users");

        String[] projection = {
                COLUMN_NAME_USERID,
                COLUMN_NAME_USERNAME,
                COLUMN_NAME_PASSWORD
        };

        String selection = COLUMN_NAME_USERNAME + "=?" + " AND " +COLUMN_NAME_PASSWORD + " =?";

        String[] selectionArgs = {username, password};

        ContentResolver contentResolver = getContentResolver();

        Cursor cursor = contentResolver.query(uri,projection,selection,selectionArgs,null);

        int userIDColumnIndex = cursor.getColumnIndex(COLUMN_NAME_USERID);
        int userColumnIndex = cursor.getColumnIndex(COLUMN_NAME_USERNAME);
        int passColumnIndex = cursor.getColumnIndex(COLUMN_NAME_PASSWORD);
        boolean flag = false;
        try {
            while (cursor.moveToNext()) {
                String dbuser = cursor.getString(userColumnIndex);
                String dbpass = cursor.getString(passColumnIndex);

                if (!username.equals(dbuser) || !password.equals(dbpass)) {
                    flag = false;
                } else if (username.equals(dbuser) || password.equals(dbpass)) {
                    dbuserID = cursor.getInt(userIDColumnIndex);
                    flag = true;
                }
            }
        } finally {
            cursor.close();
        }
        return flag;
    }

    public void login1(View view){

        TextInputEditText userField = findViewById(R.id.username);

        TextInputEditText passField = findViewById(R.id.password);

        String user = userField.getText().toString().trim();

        String pass = passField.getText().toString().trim();


        if (validateLogin(user, pass)) {
            userID = dbuserID;
            Log.i("@RatingsApp: ","User ID - "+dbuserID);
            Intent intent = new Intent(login.this,MainActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(login.this, "Login Failed", Toast.LENGTH_SHORT).show();
        }


    }
}
