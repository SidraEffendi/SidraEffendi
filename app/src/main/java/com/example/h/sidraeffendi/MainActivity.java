package com.example.h.sidraeffendi;


import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.support.v4.widget.SimpleCursorAdapter;
import android.widget.Toast;



public class MainActivity extends ListActivity {

    Button click;
    private DatabaseOperations helper;
    private SimpleCursorAdapter listAdapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("my main", " is work");
        Toast.makeText(getApplicationContext(), "Hello World", Toast.LENGTH_SHORT).show();


        updateUI();


        click = (Button)findViewById(R.id.Add);

        // when the button is clicked list appears
        click.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final AlertDialog.Builder menuAleart = new AlertDialog.Builder(MainActivity.this);
                final String[] menuList = {"Add a Course", "Import from SD Card"};
                menuAleart.setTitle("Select");
                menuAleart.setItems(menuList, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        switch (item) {
                            case 0:
                                Intent myIntent = new Intent(MainActivity.this, CourseActivity.class);
                                MainActivity.this.startActivity(myIntent);
                                break;
                            case 1:
                                // function 2 code here
                                break;
                        }
                    }
                });
                AlertDialog menuDrop = menuAleart.create();
                menuDrop.show();

            }


        });



    }

    private void updateUI(){
        helper = new DatabaseOperations(MainActivity.this);
        SQLiteDatabase sqlDB = helper.getReadableDatabase();
        Cursor cursor = sqlDB.query(TableData.TABLE1,
                new String[] {TableData.Columns._ID, TableData.Columns.COURSE,
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
        Log.i("my Main", "is updating");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return true;

    }
}
