package com.example.delivery;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DeliveryStates extends AppCompatActivity {

    EditText username,StatesAdd;
    Button sub,update,delete,Viewlist;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_states);

        username = (EditText) findViewById(R.id.username);
        StatesAdd = (EditText) findViewById(R.id.StatesAdd);
        sub = (Button) findViewById(R.id.sub);
        update = (Button) findViewById(R.id.update);
        delete = (Button) findViewById(R.id.delete);
        Viewlist = (Button) findViewById(R.id.Viewlist);
        DB = new DBHelper(this);

        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String uname = username.getText().toString();
                String sta = StatesAdd.getText().toString();


                if(uname.equals("")||sta.equals(""))
                    Toast.makeText(DeliveryStates.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                    Boolean insert = DB.AddStates(uname, sta);
                    if(insert==true){
                        Toast.makeText(DeliveryStates.this, "States Submitted", Toast.LENGTH_SHORT).show();
                                /*Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                                startActivity(intent);*/
                    }else{
                        Toast.makeText(DeliveryStates.this, "States failed", Toast.LENGTH_SHORT).show();
                    }
                }



            }
        });
        ViewList();
        DeleteStates();
        StatesUpdate();

    }

    public void StatesUpdate() {
        update.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View v) {


                                             boolean isUpdate = DB.StatesUpdate( username.getText().toString(),StatesAdd .getText().toString());



                                             if(isUpdate == true)
                                                 Toast.makeText(DeliveryStates.this,"Data Update",Toast.LENGTH_LONG).show();
                                             else
                                                 Toast.makeText(DeliveryStates.this,"Data not Updated",Toast.LENGTH_LONG).show();
                                         }
                                     }
        );
    }
    public void DeleteStates() {
        delete.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View v) {

                                             Integer deletedRows = DB.DeleteStates(username.getText().toString());
                                             if(deletedRows > 0)
                                                 Toast.makeText(DeliveryStates.this,"Data Deleted",Toast.LENGTH_LONG).show();
                                             else
                                                 Toast.makeText(DeliveryStates.this,"Data not Deleted",Toast.LENGTH_LONG).show();
                                         }
                                     }
        );
    }

    public void ViewList() {
        Viewlist.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {
                                              Cursor res = DB.ViewDeliveryList();
                                              if(res.getCount() == 0) {
                                                  // show message
                                                  delivery("Sorry","No states found");
                                                  return;
                                              }
                                              StringBuilder buffer = new StringBuilder();
                                              while (res.moveToNext()) {
                                                  buffer.append("username  :"+ res.getString(0)+"\n");
                                                  buffer.append("Location :"+ res.getString(1)+"\n");
                                                  buffer.append("ContactNumber :"+ res.getString(2)+"\n");
                                                  buffer.append("\n");
                                              }
                                              delivery("Data",buffer.toString());
                                          }
                                      }
        );
    }
    public void delivery(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }



}