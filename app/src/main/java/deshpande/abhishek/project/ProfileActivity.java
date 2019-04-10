package deshpande.abhishek.project;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class ProfileActivity extends AppCompatActivity {

    private EditText nameEdittext, heightEdittext;
    private String name;
    private float height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        nameEdittext = findViewById(R.id.profileactivity_name_edittext);
        heightEdittext = findViewById(R.id.profileactivity_height_edittext);

        name = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("name", "No name");
        height = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getFloat("height", 0);

        nameEdittext.setText(name);
        heightEdittext.setText(String.valueOf(height));

    }
}
