package com.example.mad;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UserProfile extends AppCompatActivity {

    DBHelper myDb;
    EditText profUsername, profPassword;
    Button btnview,btnupdate,btndelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        myDb = new DBHelper(this);

        profUsername = (EditText) findViewById(R.id.profUsername);
        profPassword = (EditText) findViewById(R.id.profPassword);

        btnview = findViewById(R.id.btnview);
        btnupdate = findViewById(R.id.btnupdate);
        btndelete = findViewById(R.id.btndelete);


        viewAll();
    }

    public void viewAll() {
        btnview.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View v) {
                                           Cursor res = myDb.getdata();
                                           if(res.getCount() == 0) {
                                               // show message
                                               showMessage("Error","No data found");
                                               return;
                                           }

                                           StringBuilder buffer = new StringBuilder();
                                           while (res.moveToNext()) {
                                               buffer.append("username  :"+ res.getString(0)+"\n");
                                               buffer.append("password :"+ res.getString(1)+"\n");
                                               buffer.append("quantity :"+ res.getString(2)+"\n");
                                               buffer.append("Regfee :"+ res.getString(3)+"\n\n");


                                           }

                                           // Show all data
                                           showMessage("Data",buffer.toString());
                                       }
                                   }
        );
    }
    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

}