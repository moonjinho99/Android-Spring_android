package com.example.retrofitex;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.GridView;
import android.widget.TextView;

import androidx.core.view.WindowCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.retrofitex.databinding.UmbrellalistBinding;

public class UmbrellalistActivity extends AppCompatActivity {

    TextView name;
    GridView gridView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.umbrellalist);

        name = (TextView) findViewById(R.id.name);
        gridView = (GridView) findViewById(R.id.umbrellaGridView);

        GridListAdapter adapter = new GridListAdapter();

        Intent intent = getIntent();

        String addname = intent.getStringExtra("name");

        name.setText(addname);

        adapter.addItem(new ListItem("1번","2000"));
        adapter.addItem(new ListItem("1번","2000"));
        adapter.addItem(new ListItem("1번","2000"));
        adapter.addItem(new ListItem("1번","2000"));
        adapter.addItem(new ListItem("1번","2000"));
        adapter.addItem(new ListItem("1번","2000"));
        adapter.addItem(new ListItem("1번","2000"));
        adapter.addItem(new ListItem("1번","2000"));

        gridView.setAdapter(adapter);
    }

}