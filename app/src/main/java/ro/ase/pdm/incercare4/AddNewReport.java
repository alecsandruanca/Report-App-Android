package ro.ase.pdm.incercare4;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.BitmapDrawable;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.GridView;
import android.widget.ImageView;

import android.content.Intent;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.widget.AdapterView.OnItemClickListener;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import android.widget.ListAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;

import java.io.InputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static android.app.ProgressDialog.show;
import static android.webkit.ConsoleMessage.MessageLevel.LOG;

public class AddNewReport extends AppCompatActivity implements Serializable{

    private final int PICK_IMAGE_CODE = 20;
    private ArrayList<Bitmap> list;
    private EditText titleEditText = null;
    private EditText descriptionEditText = null;
    private EditText locationEdittext=null;
    private DepartmentResponsible departmentResponsible;
    private DatePicker dtp;
    private Spinner spinner;
    private Button btnUploadImage;
    private Button submitReport;
    private ImageView poza;
    public  DbHelper helper;


//you should be able to input the data in the form, and get the report saved
    //you should also validate the data



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_report);

         helper=new DbHelper(this);

        Log.d("SQL","does sth?");

        initialiseWidgets(savedInstanceState);


        submitReport=findViewById(R.id.btn_submitReport);
        AddData();





    }

    public void AddData(){
        submitReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Report temp=new Report();

                if(titleEditText!=null){

                    if(titleEditText.getText()==null||titleEditText.getText().toString().equals("")){
                        AlertDialog.Builder dialogBuilder=new AlertDialog.Builder(AddNewReport.this);
                        dialogBuilder.setMessage("Title cannot be left empty. Please fill in the form appropriately..whatever");
                        dialogBuilder.setPositiveButton("OK",null);
                        dialogBuilder.show();
                    }
                    else{

                        temp.setTitle(titleEditText.getText().toString());
                        if(!descriptionEditText.getText().toString().equals("")){
                            temp.setDescription(descriptionEditText.getText().toString());
                            if(!locationEdittext.getText().toString().equals("")){
                                temp.setLocation(locationEdittext.getText().toString());
                                temp.setDepartment((DepartmentResponsible)(spinner.getSelectedItem()));
                                //set the department

                                temp.setDepartment((DepartmentResponsible)spinner.getSelectedItem());

                                //set the date in temp

                                int day = dtp.getDayOfMonth();
                                int month = dtp.getMonth() + 1;
                                int year = dtp.getYear();
                                SimpleDateFormat dateFormat=new SimpleDateFormat("MM-dd-yyyy");
                                Date d=new Date(year,month,day);
                                temp.setDate(d);



                                boolean isInserted=helper.insertData(temp.getTitle(),temp.getDescription(),temp.getLocation());
                                if(isInserted==true){
                                    Toast.makeText(AddNewReport.this,"Data Inserted",Toast.LENGTH_LONG).show();
                                    Log.d("SQL", "isInserted");
                                }else{
                                    Toast.makeText(AddNewReport.this,"Data not Inserted",Toast.LENGTH_LONG).show();
                                }

                                GoToRecentReportsAdded(temp);
//                                if(poza.getDrawable()!=null){
//                                    temp.setImage(((BitmapDrawable)poza.getDrawable()).getBitmap());
//                                    //send report to the View recent reports added activity
//
//                                    Log.d("TAG","Crapa in Validari? ");
//
//                                }

                            }else{
                                AlertDialog.Builder alertLocation=new AlertDialog.Builder(AddNewReport.this);
                                alertLocation.setMessage("This Location field cannot be left empty. please fill it in!!");
                                alertLocation.setPositiveButton("ok ok",null);
                                alertLocation.show();
                            }
                        }else{
                            AlertDialog.Builder dialogBuilderDescription=new AlertDialog.Builder(AddNewReport.this);
                            dialogBuilderDescription.setMessage("Descripton cannot be left empty");
                            dialogBuilderDescription.setPositiveButton("OK OK",null);
                            dialogBuilderDescription.show();
                        }
                    }

                }




            }
        });
    }


    public  void GoToRecentReportsAdded(Report report){
        Intent intent=new Intent(AddNewReport.this,ViewRecentReports.class);
        Bundle bundle=new Bundle();
        bundle.putSerializable("report",report);
        intent.putExtras(bundle);
        startActivity(intent);
       // Toast.makeText(AddNewReport.this,"Congrats, you just added a report for the dep "+ report.getDepartment() + "pam pam",Toast.LENGTH_LONG).show();
        Log.d("TAG","Crapa la trimitere catre activitatea urmatoare?");
        finish();
    }
    public void initialiseWidgets(Bundle b){
        titleEditText=(EditText)findViewById(R.id.et_title);
        descriptionEditText=(EditText)findViewById(R.id.et_description);
        locationEdittext=(EditText)findViewById(R.id.et_location);
        poza=(ImageView)findViewById(R.id.added_image);
        dtp=(DatePicker)findViewById(R.id.dtp_date_happened);

        btnUploadImage=(Button)findViewById(R.id.btn_add_picture);
        submitReport=(Button)findViewById(R.id.btn_submitReport);


        spinner = (Spinner) findViewById(R.id.spinner_department_responsible);
        spinner.setPrompt("Choose one");
        spinner.setAdapter(new ArrayAdapter<DepartmentResponsible>(this, R.layout.support_simple_spinner_dropdown_item, DepartmentResponsible.values()));



    }

    public void ClickAddImagesFromGallery(View view){
    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
    startActivityForResult(intent,PICK_IMAGE_CODE);
    Log.d("TAG","Problema deschis galerie");
}

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case PICK_IMAGE_CODE:
                if(resultCode==RESULT_OK){
                    Uri uri=data.getData();
                    String[] projection={MediaStore.Images.Media.DATA};

                    Cursor cursor=getContentResolver().query(uri,projection,null,null,null);
                    cursor.moveToFirst();

                    int columnIndex=cursor.getColumnIndex(projection[0]);
                    String filePath=cursor.getString(columnIndex);
                    cursor.close();
                    Bitmap selectedImage=BitmapFactory.decodeFile(filePath);

                    poza.setImageBitmap(selectedImage);

                }
             break;
             default: break;
        }
    }
}


