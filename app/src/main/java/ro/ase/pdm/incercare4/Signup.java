package ro.ase.pdm.incercare4;

import android.app.Activity;
import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class Signup extends AppCompatActivity {
    //here i need to declare aaaall my signup variables.... pfff

    private EditText LastNameEditText = null;
    private EditText FirstameEditText = null;
    private EditText UserEdittext;
    private EditText PhoneEditText = null;
    private RadioGroup SexRadioGroup;
    private DatePicker BirthDayDatePicker;
    private DatePicker RegistrationDate;
    private EditText PasswordEdittext;
    private EditText PlaceOfBirthEditText;
    private Spinner spinnerEducation;
    private Button btn_signUp;
    ////toate atributele din SignUpPage...Pun si CNP mai incolo


    public void initWidgets() {
        LastNameEditText = (EditText) findViewById(R.id.id_EditText_LastName);
        FirstameEditText = (EditText) findViewById(R.id.id_EditText_FirstName);
        PhoneEditText = (EditText) findViewById(R.id.id_Phone_EditText);
        UserEdittext = (EditText) findViewById(R.id.username_et);
        //PasswordEdittext = (EditText) findViewById(R.id.id_password_editText);
        PlaceOfBirthEditText = (EditText) findViewById(R.id.id_EditText_PlaceOfBirth);
        SexRadioGroup = (RadioGroup) findViewById(R.id.id_radiogroup_sex);
        BirthDayDatePicker = (DatePicker) findViewById(R.id.id_datepicker_birthdate);
        btn_signUp = (Button) findViewById(R.id.id_btn_SignUp);

        spinnerEducation = (Spinner) findViewById(R.id.id_spinnner_education);
        spinnerEducation.setAdapter(new ArrayAdapter<Education>(this, R.layout.support_simple_spinner_dropdown_item, Education.values()));
    }

    public void createUser() {


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //de completat asta ca la seminar
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        initWidgets();


    }

}