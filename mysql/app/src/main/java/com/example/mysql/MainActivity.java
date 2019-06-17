package com.example.mysql;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

//import com.example.a2019springlab09.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "minkj1992.db";
    private static final int DATABASE_VERSION = 1;

    public DBHelper(Context context) {
        // invoke super constructor.
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        // create two tables, vertices and triangles
        db.execSQL("create table vertices(_id INTEGER PRIMARY KEY AUTOINCREMENT,"
        +"x FLOAT,y FLOAT,z FLOAT);");

        db.execSQL("create table triangles(_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                +"v0 INTEGER,v1 INTEGER,v2 INTEGER);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // drop the two tables, vertices and triangles
        db.execSQL("drop table if exists vertices");
        db.execSQL("drop table if exists triangles");
        onCreate(db);
    }
}

public class MainActivity extends AppCompatActivity {
    private SQLiteDatabase  db;
    private DBHelper helper;
    Button sphereBtn,queryBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sphereBtn = findViewById(R.id.sphereBtn);
        queryBtn = findViewById(R.id.queryBtn);

        // create a DBHelper instance
        // obtain a database object from the DBHelper instance.
        helper = new DBHelper(this);
        try {

            db = helper.getWritableDatabase();
            Toast.makeText(getApplicationContext(), "getWritableDatabase",Toast.LENGTH_SHORT).show();
        } catch (SQLiteException ex) {

            db = helper.getReadableDatabase();
            Toast.makeText(getApplicationContext(), "getReadableDatabase",Toast.LENGTH_SHORT).show();
        }


        sphereBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readSphere(v);
            }
        });
        queryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                queryDatabase(v);
                Toast.makeText(getApplicationContext(), "queryDatabase",Toast.LENGTH_SHORT).show();
            }
        });

    }
//    public void readSphere(View view) {
//        // delete all the records from vertices.
//        // delete all the records from triangles.
////        db.execSQL("drop table if exists vertices");
////        db.execSQL("drop table if exists triangles");
//
//        // open the file R.raw.sphere and get InputStream instance
//        // Create a BufferedReader instance
//        InputStream inputStream = getResources().openRawResource(R.raw.sphere);
//        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
//
//        String line;
//
//        // Using the readLine() method, read each line from the file and
//        // insert the data into the associated table. That is,
//        // "v 0.1 0.3 2.5" --> insert into vertices values (null, 0.1, 0.3, 2.5);
//        // "f 10 3 23" --> insert into triangles value (null, 10, 3, 23);
//
//    }
//

    public void readSphere(View view) {
        // delete all the records from vertices.
        // delete all the records from triangles.
        String sql = "delete from vertices;";
        db.execSQL(sql);
        sql = "delete from triangles;";
        db.execSQL(sql);

        // open the file R.raw.sphere and get InputStream instance
        // Create a BufferedReader instance
        InputStream inputStream = getResources().openRawResource(R.raw.sphere);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        String line;
        while(true) {
            try {
                line = reader.readLine();
                if(line == null) {
                    break;
                }
                Log.i("TEST", line);
                String[] words = line.split("\\s");
                if(words[0].equals("v")) {
                    db.execSQL("INSERT INTO vertices VALUES (null, '" + Float.parseFloat(words[1]) + "',  '" + Float.parseFloat(words[2]) + "',  '" + Float.parseFloat(words[3]) + "');");
                } else if(words[0].equals("f")){
                    db.execSQL("INSERT INTO triangles VALUES (null,  '" + Integer.parseInt(words[1]) + "',  '" + Integer.parseInt(words[2]) + "',  '" + Integer.parseInt(words[3]) + "');");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Log.i("TEST", "Read finish");

        // Using the readLine() method, read each line from the file and
        // insert the data into the associated table. That is,
        // "v 0.1 0.3 2.5" --> insert into vertices values (null, 0.1, 0.3, 2.5);
        // "f 10 3 23" --> insert into triangles value (null, 10, 3, 23);

    }
    public void queryDatabase(View view) {
        Log.v("mink", "queryDatabase activate");
        Cursor cursor;

        // select vertices with x less than -6 from the table vertices
        // take the three values, x, y, z from each record and output them with Log.v().
        cursor = db.rawQuery("SELECT x,y,z FROM vertices WHERE x<-6;",null);
        Log.v("mink", String.valueOf(cursor.getCount()));


        while (cursor.moveToNext()) {
            String x = cursor.getString(0);
            String y = cursor.getString(1);
            String z = cursor.getString(2);
//            Toast.makeText(getApplicationContext(), "run",Toast.LENGTH_SHORT).show();
            Log.v("mink", x+' '+y+' '+z);

        }
        cursor.close();

    }

}