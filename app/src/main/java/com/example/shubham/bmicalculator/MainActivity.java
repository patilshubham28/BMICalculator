package com.example.shubham.bmicalculator;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText etName,etPassword;
    Button btnLogin;
    SharedPreferences sp1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = (EditText) findViewById(R.id.etName);
        etPassword = (EditText) findViewById(R.id.etPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
            sp1=getSharedPreferences("P1",MODE_PRIVATE);
        if(sp1.getBoolean("ne",false)==false){




        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(etName.getText().toString().length()==0)
                {
                    etName.setError("Enter name");
                    etName.requestFocus();
                    return;
                }
                if(etPassword.getText().toString().length()==0)
                {
                    etPassword.setError("Enter password");
                    etPassword.requestFocus();
                    return;
                }

                String name = etName.getText().toString();
                SharedPreferences.Editor editor = sp1.edit();

                editor.putString("n", name);
                editor.putBoolean("ne", true);
                editor.commit();

                Intent intent = new Intent(MainActivity.this, Bmi.class);
                startActivity(intent);
                finish();

            }
        });
    }
        else {
            Intent intent = new Intent(MainActivity.this, Bmi.class);
            startActivity(intent);
            finish();
        }

        }
}
