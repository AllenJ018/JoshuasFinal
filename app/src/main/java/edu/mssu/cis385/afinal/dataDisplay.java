package edu.mssu.cis385.afinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class dataDisplay extends AppCompatActivity {

    private String mJsonString = "";
    private String mNumberChoosen;
    private String[] mNameData;
    private String[] mTypeData;
    private String[] mIconData;
    private String[] mRarityData;
    private int[] mVendorData;
    private Object[] mItemRefVar = new Object[5];
    private RecyclerView mRecyclerView;
    private RecAdapter listAdapter;
    private ArrayList<AnItem> itemList = new ArrayList<>();


    private static final String LOG_BAT = dataDisplay.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_display);

        mRecyclerView = findViewById(R.id.recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        listAdapter = new RecAdapter(itemList, this);
        mRecyclerView.setAdapter(listAdapter);

        listAdapter = new RecAdapter(itemList, this);
        Intent intent = getIntent();
        mNumberChoosen = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        try {
            new FetchItem(listAdapter, mRecyclerView).execute(mNumberChoosen);
        } catch (Exception e) {
            Log.d(LOG_BAT, "mNameData is null");
        }
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId()==R.id.darkMode){
            int darkMode = AppCompatDelegate.getDefaultNightMode();
            if(darkMode == AppCompatDelegate.MODE_NIGHT_YES){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            } else{
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            }
            recreate();
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        int darkMode = AppCompatDelegate.getDefaultNightMode();
        if(darkMode == AppCompatDelegate.MODE_NIGHT_YES){
            menu.findItem(R.id.darkMode).setTitle(R.string.dark_mode);

        }else{
            menu.findItem(R.id.darkMode).setTitle(R.string.dark_mode);
        }
        return true;
    }
}