package com.learning.watch2vehicle;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.learning.watch2vehicle.databinding.ActivityMainBinding;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        com.learning.watch2vehicle.databinding.ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        @SuppressLint("SimpleDateFormat") DateFormat dateFormatter = new SimpleDateFormat("EEE d hh:mm");
        dateFormatter.setLenient(false);
        Date today = new Date();
        String s =  dateFormatter.format(today);
        TextView mTextView = binding.text;
        mTextView.setText(s);
    }

    public void startChargeTasksActivity(View view) {
        startActivity(new Intent(this, ChargeTasksActivity.class));
    }

    public void startMaintenanceTasksActivity(View view)
    {
        startActivity(new Intent(this,MaintenanceTasksActivity.class));
    }
}