package com.example.h.sidraeffendi;



import android.app.ListActivity;
import android.content.ContentValues;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.h.sidraeffendi.TableData;
import com.example.h.sidraeffendi.DatabaseOperations;


/**
 * Created by h on 6/25/2015.
 */
public class CourseActivity extends ListActivity {

    Button Done;
    EditText editText,editText2,editText3,editText4;

    private DatabaseOperations helper;
    public SimpleCursorAdapter listAdapter;

   @Override
    protected void onCreate(Bundle savedInstanceState) {

       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_course);
       Log.i("my main", "works");

       Done = (Button) findViewById(R.id.Done);
       editText = (EditText) findViewById(R.id.editText);
       editText2 = (EditText) findViewById(R.id.editText2);
       editText3 = (EditText) findViewById(R.id.editText3);
       editText4 = (EditText) findViewById(R.id.editText4);


       // when the button is clicked values are inserted in the database
       Done.setOnClickListener(new View.OnClickListener() {
           public void onClick(View v) {
               String course = editText.getText().toString();
               Log.d("CourseActivity", course);

               helper = new DatabaseOperations(CourseActivity.this);
               SQLiteDatabase db = helper.getWritableDatabase();
               ContentValues values = new ContentValues();

               values.clear();
               values.put(TableData.Columns.COURSE, course);
               values.put(TableData.Columns.PROFESSOR, editText2.getText().toString());
               values.put(TableData.Columns.VENUE, editText3.getText().toString());
               values.put(TableData.Columns.CODE, editText4.getText().toString());

               Log.d("CourseActivity", "new data");

               db.insertWithOnConflict(TableData.TABLE1, null, values,
                       SQLiteDatabase.CONFLICT_IGNORE);
               Intent myIntent = new Intent(CourseActivity.this, MainActivity.class);
               CourseActivity.this.startActivity(myIntent);
               //updateUI();
               db.close();

           }


       });
   }
    public void updateUI(){
        helper = new DatabaseOperations(CourseActivity.this);
        SQLiteDatabase sqlDB = helper.getReadableDatabase();
        Cursor cursor = sqlDB.query(TableData.TABLE1,
                new String[] { TableData.Columns.COURSE,
                        TableData.Columns.PROFESSOR,  TableData.Columns.VENUE, TableData.Columns.CODE },
                null,null,null,null,null);

        Log.i("my Main", "updating");

        listAdapter = new SimpleCursorAdapter(
                this,
                R.layout.table,
                cursor,
                new String[] { TableData.Columns.COURSE, TableData.Columns.PROFESSOR, TableData.Columns.VENUE, TableData.Columns.CODE},
                new int[] { R.id.textView6,R.id.textView7,R.id.textView8,R.id.textView9},
                0
        );
        this.setListAdapter(listAdapter);
    }




}