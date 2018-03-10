package ro.ase.pdm.incercare4;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStreamWriter;

public class DatabaseRetrieval extends AppCompatActivity {

    Button btnSeeDbReports;
    Button btnUpdate;
    Button btnSave;
    Button btn_SeeSpecificReports;
    Button btnDelete;
    DbHelper mydb;
    EditText editTextId;

    public void  initWidget(){
        btn_SeeSpecificReports=(Button)findViewById(R.id.btn_see_specific_reports);
        btnSeeDbReports=(Button)findViewById(R.id.btn_see_db_reports);
        btnSave=(Button)findViewById(R.id.btn_save);
        btnDelete=(Button)findViewById(R.id.btnDelete);
        btnUpdate=(Button)findViewById(R.id.btnUpdate);
        editTextId = (EditText)findViewById(R.id.editText_id);
    }



    public void SaveToFile(){
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String filename = "myfile";
                String string = "Hello world!";
                FileOutputStream outputStream;
                Log.d("SAVE","SaveTo FILE");

                try {
                    outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
                    outputStream.write(string.getBytes());
                    Toast.makeText(DatabaseRetrieval.this, "Data saved to file", Toast.LENGTH_SHORT).show();
                    outputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        });
    }
    public void SeeSpecificReports(){
        btn_SeeSpecificReports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });
    }


    public void showMessage(String title,String Message){

        AlertDialog.Builder builde=new AlertDialog.Builder(this);
        builde.setCancelable(true);
        builde.setTitle(title);
        builde.setMessage(Message);
        builde.show();

    }
    public void SeeAllDbReports(){
        btnSeeDbReports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res=mydb.getAllReports();

                if(res.getCount()==0){
                    //show message
                    showMessage("Error","Nothing found ");
                    return;
                }else{
                    StringBuffer buffer=new StringBuffer();
                    while(res.moveToNext()){
                        buffer.append("Id: "+ res.getString(0)+"\n");
                        buffer.append("Title: "+ res.getString(1)+"\n");
                        buffer.append("Desc: "+ res.getString(2)+"\n\n");

                    }
                    showMessage("Data",buffer.toString());
                }
            }

        });
    }


    //apelare insert update delete

    public void DeleteData() {
        btnDelete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Integer deletedRows = mydb.deleteData(editTextId.getText().toString());
                        if(deletedRows > 0)
                            Toast.makeText(DatabaseRetrieval.this,"Data Deleted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(DatabaseRetrieval.this,"Data not Deleted",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

//    public void UpdateData() {
//        btnUpdate.setOnClickListener(
//                new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
//
//                        Intent in=new Intent(DatabaseRetrieval.this, AddNewReport.class);
//                        startActivityForResult();
//                        boolean isUpdate = mydb.updateData(editTextId.getText().toString(),
//                                editName.getText().toString(),
//                                editSurname.getText().toString(),editMarks.getText().toString());
//                        if(isUpdate == true)
//                            Toast.makeText(MainActivity.this,"Data Update",Toast.LENGTH_LONG).show();
//                        else
//                            Toast.makeText(MainActivity.this,"Data not Updated",Toast.LENGTH_LONG).show();
//                    }
//                }
//        );
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_retrieval);

        initWidget();

        mydb=new DbHelper(this);
        SeeAllDbReports();
        DeleteData();

    }
}
