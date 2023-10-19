package com.example.myfirstappication;

import android.os.Bundle;
import android.view.View;

import androidx.activity.ComponentActivity;

public class MainActivity extends ComponentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }
    public void disable(View v)
    {
        v.setEnabled(false);
    }
}
