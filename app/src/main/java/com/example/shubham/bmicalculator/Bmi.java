package com.example.shubham.bmicalculator;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;

import static com.example.shubham.bmicalculator.R.styleable.Spinner;

public class Bmi extends AppCompatActivity {
        TextView tvWelcome;
        EditText etWeight;
        Button btnCalculate,btnHistory;
        Spinner spnFeet,spnInches;
        static String ans="";
        SharedPreferences sp1;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_bmi);
            tvWelcome= (TextView) findViewById(R.id.tvWelcome);
            sp1=getSharedPreferences("P1",MODE_PRIVATE);
            String name=sp1.getString("n","");
            tvWelcome.setText("Welcome "+name);
            etWeight= (EditText) findViewById(R.id.etWeight);
            btnCalculate= (Button) findViewById(R.id.btnCalculate);
            btnHistory= (Button) findViewById(R.id.btnHistory);
            spnFeet= (Spinner) findViewById(R.id.spnFeet);
            spnInches= (Spinner) findViewById(R.id.spnInches);
            Integer feet[]={1,2,3,4,5,6,7,8,9,10};
            Integer inches[]={0,1,2,3,4,5,6,7,8,9,10,11,12};
            ArrayAdapter<Integer> adapter=new ArrayAdapter<Integer>(this,android.R.layout.simple_list_item_1,feet);
            spnFeet.setAdapter(adapter);
            ArrayAdapter<Integer> ad=new ArrayAdapter<Integer>(this,android.R.layout.simple_list_item_1,inches);
            spnInches.setAdapter(ad);

            btnCalculate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String weight=etWeight.getText().toString();
                    if(weight.length()==0)
                    {
                        etWeight.setError("Enter your weight");
                        etWeight.requestFocus();
                        return;
                    }
                    int feet=spnFeet.getSelectedItemPosition();
                    int wt=Integer.parseInt(weight);
                    feet+=1;
                    int inches=spnInches.getSelectedItemPosition();
                    double sum=feet*12+inches;
                    sum*=0.0245;
                    sum=wt/(sum*sum);
                    Intent i1=new Intent(Bmi.this,Final.class);
                    i1.putExtra("sum",sum);
                    startActivity(i1);
                }
            });

            btnHistory.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Toast.makeText(Bmi.this, DbHandler.answer, Toast.LENGTH_SHORT).show();
                }
            });
        }











    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu1,menu);
        //return super.onCreateOptionsMenu(menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.About:
                Snackbar.make(findViewById(android.R.id.content), "Developed by Shubham", Snackbar.LENGTH_LONG).show();
                break;
            case R.id.Website:
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("http://www.google.com"));
                startActivity(i);

        }
            return super.onOptionsItemSelected(item);

    }

}
