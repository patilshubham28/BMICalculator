package com.example.shubham.bmicalculator;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Final extends AppCompatActivity {

    TextView tvAnswer, tv1, tv2, tv3, tv4;
    Button btnSave, btnShare, btnBack;
    boolean isOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);
        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);
        tv3 = (TextView) findViewById(R.id.tv3);
        tv4 = (TextView) findViewById(R.id.tv4);
        btnBack = (Button) findViewById(R.id.btnBack);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnShare = (Button) findViewById(R.id.btnShare);
        tvAnswer = (TextView) findViewById(R.id.tvAnswer);
        Intent i = getIntent();
        double sum = i.getDoubleExtra("sum", 0.0);
        String fans = String.valueOf(sum);
        final String ans = fans.substring(0, 4);
        if (sum < 18.5) {
            tvAnswer.setText("Your Bmi is " + ans + " and you are underweight");
            tv1.setTextColor(Color.RED);
        } else if (sum >= 18.5 && sum <= 25) {
            tvAnswer.setText("Your Bmi is " + ans + " and you are normal");
            tv2.setTextColor(Color.RED);
        } else if (sum > 25 && sum <= 30) {
            tvAnswer.setText("Your Bmi is " + ans + " and you are overweight");
            tv3.setTextColor(Color.RED);
        } else {
            tvAnswer.setText("Your Bmi is " + ans + " and you are obese");
            tv4.setTextColor(Color.RED);
        }

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Final.this, Bmi.class);
                startActivity(intent);
            }
        });

        final DbHandler db = new DbHandler(this);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = tvAnswer.getText().toString();
                DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
                Date today = Calendar.getInstance().getTime();
                String tdate = df.format(today);
                db.addDetails(tdate, name);
            }
        });

        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_TEXT, "my bmi is" + ans);
                startActivity(i);


            }
        });
    }

    public void onBackPressed(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Do you want to exit");
        builder.setCancelable(false);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();

            }
        });
        AlertDialog alert = builder.create();
        alert.setTitle("Exit");
        alert.show();


    }

}
