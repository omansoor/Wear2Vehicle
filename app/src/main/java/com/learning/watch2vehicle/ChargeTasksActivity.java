package com.learning.watch2vehicle;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.learning.watch2vehicle.databinding.ActivityChargeTasksBinding;

public class ChargeTasksActivity extends Activity {

    private TextView mTextView;
    private ActivityChargeTasksBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityChargeTasksBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mTextView = binding.text;
    }
}