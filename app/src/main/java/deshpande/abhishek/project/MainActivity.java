package deshpande.abhishek.project;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private ConstraintLayout parentLayout;
    private EditText mailEdittext, passEdittext, nameEdittext, heightEdittext;
    private FirebaseAuth firebaseAuth;

    private String mail, pass, name;
    private float height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        parentLayout = findViewById(R.id.signupactivity_parent_layout);
        mailEdittext = findViewById(R.id.signupactivity_email_edittext);
        passEdittext = findViewById(R.id.signupactivity_pass_edittext);
        nameEdittext = findViewById(R.id.signupactivity_name_edittext);
        heightEdittext = findViewById(R.id.signupactivity_height_edittext);
    }

    public void onSignUpClick(View view) {
        mail = mailEdittext.getText().toString();
        pass = passEdittext.getText().toString();
        name = nameEdittext.getText().toString();
        height = Float.parseFloat(heightEdittext.getText().toString());
        firebaseAuth.createUserWithEmailAndPassword(mail, pass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit();
                            editor.putString("mail", mail);
                            editor.putString("name", name);
                            editor.putFloat("height", height);
                            editor.commit();

                            Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                            startActivity(intent);
                        } else {
                            Snackbar.make(parentLayout, "Some Error Occured ! (1)", Snackbar.LENGTH_SHORT);
                        }
                    }
                });
    }
}
