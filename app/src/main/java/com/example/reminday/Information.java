package com.example.reminday;

import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Information extends AppCompatActivity {

    EditText name, dob, phoneNo, email, address;
    Button insert, update, delete, view;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        name = findViewById(R.id.name);
        dob = findViewById(R.id.dob);
        phoneNo = findViewById(R.id.phoneNo);
        email = findViewById(R.id.email);
        address = findViewById(R.id.address);

        insert = findViewById(R.id.insertBtn);
        update = findViewById(R.id.updateBtn);
        delete = findViewById(R.id.deleteBtn);
        view = findViewById(R.id.viewBtn);
        DB = new DBHelper(this);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = name.getText().toString();
                String dobTXT = dob.getText().toString();
                String phoneTXT = phoneNo.getText().toString();
                String emailTXT = email.getText().toString();
                String addressTXT = address.getText().toString();

                Boolean checkinsertdata = DB.insertuserdata(nameTXT, dobTXT, phoneTXT, emailTXT, addressTXT);
                if(checkinsertdata==true)
                    Toast.makeText(Information.this, "New entry inserted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(Information.this, "New entry not inserted", Toast.LENGTH_SHORT).show();
            }        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = name.getText().toString();
                String dobTXT = dob.getText().toString();
                String phoneTXT = phoneNo.getText().toString();
                String emailTXT = email.getText().toString();
                String addressTXT = address.getText().toString();

                Boolean checkupdatedata = DB.updateuserdata(nameTXT, dobTXT, phoneTXT, emailTXT, addressTXT);
                if(checkupdatedata==true)
                    Toast.makeText(Information.this, "Entry updated", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(Information.this, "New entry not updated", Toast.LENGTH_SHORT).show();
            }        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = name.getText().toString();
                Boolean checkdeletedata = DB.deletedata(nameTXT);
                if(checkdeletedata==true)
                    Toast.makeText(Information.this, "Entry deleted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(Information.this, "Entry not deleted", Toast.LENGTH_SHORT).show();
            }        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.getdata();
                if(res.getCount()==0){
                    Toast.makeText(Information.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("Name : "+res.getString(0)+"\n");
                    buffer.append("Date of Birth  : "+res.getString(1)+"\n");
                    buffer.append("Phone No : "+res.getString(2)+"\n");
                    buffer.append("Email  : "+res.getString(3)+"\n");
                    buffer.append("Address : "+res.getString(4)+"\n\n\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(Information.this);
                builder.setCancelable(true);
                builder.setTitle("Friend's information");
                builder.setMessage(buffer.toString());
                builder.show();
            }        });

    }
}
