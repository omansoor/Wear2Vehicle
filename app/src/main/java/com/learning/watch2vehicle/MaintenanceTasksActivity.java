package com.learning.watch2vehicle;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.learning.watch2vehicle.databinding.ActivityMaintenanceTasksBinding;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MaintenanceTasksActivity extends Activity {

    private TextView mTextView;
    private ActivityMaintenanceTasksBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMaintenanceTasksBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mTextView = binding.text;
        @SuppressLint("SimpleDateFormat") DateFormat dateFormatter = new SimpleDateFormat("EEE d hh:mm");
        dateFormatter.setLenient(false);
        Date today = new Date();
        String s =  dateFormatter.format(today);
        TextView mTextView = binding.text;
        mTextView.setText(s);
    }
}