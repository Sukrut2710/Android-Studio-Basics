package com.example.student.login_sqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    EditText editsearch, editempname, editempmail, editage;
    SQLiteDatabase sqlitedb;
    Button Add, Delete, Update, Searchall, Search;
    String name, mailid, age, search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Create database EmployeeDB database name
        sqlitedb = openOrCreateDatabase("EmployeeDB", Context.MODE_PRIVATE, null);
        //create table empRegistration
        sqlitedb.execSQL("CREATE TABLE IF NOT EXISTS EmpRegistration(EmpId INTEGER PRIMARY KEY AUTOINCREMENT, EmpName VARCHAR(255), EmpMail VARCHAR(255), EmpAge VARCHAR(255));");
        editsearch = (EditText) findViewById(R.id.et);
        editempname = (EditText) findViewById(R.id.etName);
        editempmail = (EditText) findViewById(R.id.etmailid);
        editage = (EditText) findViewById(R.id.etage);

        Add = (Button) findViewById(R.id.btnsave);
        Delete = (Button) findViewById(R.id.btndel);
        Update = (Button) findViewById(R.id.btnupdate);
        Searchall = (Button) findViewById(R.id.btnselall);
        Search = (Button) findViewById(R.id.btnsel);

        Add.setOnClickListener(this);
        Delete.setOnClickListener(this);
        Update.setOnClickListener(this);
        Search.setOnClickListener(this);
        Searchall.setOnClickListener(this);
    }
    @Override
    public void onClick(View v){
        if(v.getId()==R.id.btnsave)
        {
            name = editempname.getText().toString().trim();
            mailid = editempmail.getText().toString().trim();
            age = editage.getText().toString().trim();
            if(name.equals("")||mailid.equals("")||age.equals("")){
                Toast.makeText(this,"Field cannot be empty", Toast.LENGTH_SHORT).show();
                return;
            }
            else
            {
                sqlitedb.execSQL("Insert Into EmpRegistration(EmpName, EmpMail, EmpAge)VALUES('" + name + "', '" + mailid + "', '" + age + "');");
                Toast.makeText(this, "Record Saved", Toast.LENGTH_SHORT).show();
            }
        }
        else if(v.getId()==R.id.btnselall)
        {

            Cursor c = sqlitedb.rawQuery("Select * From EmpRegistration", null);
            if(c.getCount()==0)
            {
                Toast.makeText(this,"Database is empty", Toast.LENGTH_SHORT).show();
                return;
            }
            StringBuffer buffer = new StringBuffer();
            while(c.moveToNext())
            {
                buffer.append("Employee Name:"+c.getString(1)+"\n");
                buffer.append("Employee Mail:"+c.getString(2)+"\n");
                buffer.append("Employee Age:"+c.getString(3)+"\n");
            }
            Toast.makeText(this,buffer.toString(), Toast.LENGTH_SHORT).show();

        }
        else if(v.getId()==R.id.btnsel)
        {
            search = editsearch.getText().toString().trim();
            if(search.equals(""))
            {
                Toast.makeText(this,"Enter Employee Name", Toast.LENGTH_SHORT).show();
                return;
            }
            Cursor c = sqlitedb.rawQuery("Select * From EmpRegistration Where EmpName='" + search + "'", null);
            if(c.moveToFirst())
            {
                editempname.setText(c.getString(1));
                editempmail.setText(c.getString(2));
                editage.setText(c.getString(3));
            }
            else
            {
                Toast.makeText(this,"Data Not Found", Toast.LENGTH_SHORT).show();

            }

        }
        else if(v.getId()==R.id.btnupdate)
        {
            search = editsearch.getText().toString().trim();
            name = editempname.getText().toString().trim();
            mailid = editempmail.getText().toString().trim();
            age = editage.getText().toString().trim();
            if(search.equals(""))
            {
                Toast.makeText(this,"Please Enter Employee Name To Update", Toast.LENGTH_SHORT).show();
                return;
            }

            Cursor cursorupdate = sqlitedb.rawQuery("Select * From EmpRegistration Where EmpName='" + search + "'", null);
            if (cursorupdate.moveToFirst())
            {
                if(name.equals("")||mailid.equals("")||age.equals("")){
                    Toast.makeText(this,"Field cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                else
                {
                    sqlitedb.execSQL("Update EmpRegistration Set EmpName = '"+ name +"', EmpMail = '"+ mailid +"', EmpAge = '"+ age +"' Where EmpName = '"+ search +"';");
                    Toast.makeText(this,"Record Modified", Toast.LENGTH_SHORT).show();

                }
            }
            else
            {
                Toast.makeText(this,"Data Not Found", Toast.LENGTH_SHORT).show();

            }

        }
        else if(v.getId()==R.id.btndel)
        {
            search = editsearch.getText().toString().trim();
            if(search.equals(""))
            {
                Toast.makeText(this,"Please Eneter Employee Name to Delete", Toast.LENGTH_SHORT).show();
                return;
            }
            Cursor cursordel = sqlitedb.rawQuery("Select * From EmpRegistration Where EmpName='" + search + "'", null);
            if (cursordel.moveToFirst())
            {
                sqlitedb.execSQL("Delete From EmpRegistration Where EmpName = '" + search + "';");
                Toast.makeText(this,"Record Deleted", Toast.LENGTH_SHORT).show();

            }
            else
            {
                Toast.makeText(this,"Data Not Found", Toast.LENGTH_SHORT).show();
            }
        }

    }
}
