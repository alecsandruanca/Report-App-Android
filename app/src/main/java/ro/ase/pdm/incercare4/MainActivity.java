package ro.ase.pdm.incercare4;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

import java.net.URI;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(isServicesOK()) {
            init();
        }
    }


    public void CallAuthorities(View view){
        Intent intentCall=new Intent(Intent.ACTION_DIAL);
        intentCall.setData(Uri.parse("tel:0757486601"));
        startActivity(intentCall);
    }
    public void AddReport(View view)
    {
            Intent intentAddReport=new Intent(MainActivity.this,AddNewReport.class);
            startActivity(intentAddReport);
    }


    public void SeeDatabaseData(View view){

        Intent newIntent=new Intent(MainActivity.this,DatabaseRetrieval.class);
        startActivity(newIntent);


    }
    public void ShowContactList(View view){
        Intent intentShowContactList=new Intent(MainActivity.this,ContactList.class);
        startActivity(intentShowContactList);
    }

    public void GoToRecentReports(View view){
        Intent intentToViewRecent=new Intent(MainActivity.this,ViewRecentReports.class);
        startActivity(intentToViewRecent);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;




    }


    private void init(){
        Button btnMap = (Button) findViewById(R.id.btn_map);
        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MapActivity.class);
                startActivity(intent);
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.id_login){
            //go to login page

            // mesaj pentru user
            Toast.makeText(MainActivity.this, "Hy there!Login and SignUp partially avaible now. Press the Submit/login button without judging :))",
                    Toast.LENGTH_LONG).show();
            Intent intentLogin=new Intent(MainActivity.this, Login.class);
            startActivity(intentLogin);
            return true;
        }
        if(id==R.id.id_settings){
            //go to settings page
            Log.i("MainActivitity", "Sunt aici.");
            Intent intentSettings=new Intent(MainActivity.this,Settings.class);
            startActivity(intentSettings);
            return true;

        }
        if(id==R.id.id_signup){
            //go to signup form
            Intent intentSignup=new Intent(MainActivity.this,Signup.class);
            startActivity(intentSignup);
            return true;

        }
        if(id==R.id.id_profile){
            //go to profile view
            Intent intentProfile=new Intent(MainActivity.this,Profile.class);
            startActivity(intentProfile);
            return true;
        }
        return  true;
    }





    private static final int ERROR_DIALOG_REQUEST=9001;


    public boolean isServicesOK(){
        Log.d("MAP", "isServicesOK: checking google services version");

        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(MainActivity.this);

        if(available == ConnectionResult.SUCCESS){
            //everything is fine and the user can make map requests
            Log.d("MAP", "isServicesOK: Google Play Services is working");
            return true;
        }
        else if(GoogleApiAvailability.getInstance().isUserResolvableError(available)){
            //an error occured but we can resolve it
            Log.d("MAP", "isServicesOK: an error occured but we can fix it");
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(MainActivity.this, available, ERROR_DIALOG_REQUEST);
            dialog.show();
        }else{
            Toast.makeText(this, "You can't make map requests", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

}
