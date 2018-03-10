package ro.ase.pdm.incercare4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    private static final String TAG="LoginActivity";
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

        //UI references
    private EditText mEmail, mPassword;
    private Button btnSignIn,btnSignOut,btnAddItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEmail = (EditText) findViewById(R.id.email);
        mPassword = (EditText) findViewById(R.id.password);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String data = extras.getString("user");
        }


        //mAuth = FirebaseAuth.getInstance();

    }







    public void GoToMainActivityFromLogin(View view){
        Intent intent=new Intent(Login.this,MainActivity.class);
        startActivity(intent);
        Toast.makeText(Login.this,"Congratulations! You have successfully logged in! " +
                "Now you cand add a report, call us or visit our website",Toast.LENGTH_LONG).show();
        finish();// keep this in mind! very important
    }

}
