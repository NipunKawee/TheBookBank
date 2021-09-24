package com.example.thebookbank;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PaymentHandling extends AppCompatActivity {

    DBHelper myDb;
    EditText editMemberID, editAmount;
    Button button,button1,button2;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_handling);


        myDb = new DBHelper(this);

        editMemberID = (EditText) findViewById(R.id.ID);
        editAmount = (EditText) findViewById(R.id.am);

        button = findViewById(R.id.button5);
        button1 = findViewById(R.id.button6);
        button2 = findViewById(R.id.button7);
        viewAll();
        Updatedata();
        DeleteData();
    }
    public void Updatedata() {
        button1.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isUpdate = myDb.Updatedata( editMemberID.getText().toString(),editAmount .getText().toString());
                        if(isUpdate == true)
                            Toast.makeText(PaymentHandling.this,"Data Update",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(PaymentHandling.this,"Data not Updated",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void DeleteData() {
        button2.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Integer deletedRows = myDb.deleteData(editMemberID.getText().toString());
                        if(deletedRows > 0)
                            Toast.makeText(PaymentHandling.this,"Data Deleted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(PaymentHandling.this,"Data not Deleted",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void viewAll() {
        button.setOnClickListener(new View.OnClickListener() {
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
                            buffer.append("memberid  :"+ res.getString(0)+"\n");
                            buffer.append("contactno :"+ res.getString(1)+"\n");
                            buffer.append("email :"+ res.getString(2)+"\n");
                            buffer.append("amount :"+ res.getString(3)+"\n\n");


                        }

                        // Show all data
                        showMessage("PaymentDetails",buffer.toString());
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