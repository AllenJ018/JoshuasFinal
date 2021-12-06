package edu.mssu.cis385.afinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
            /*
            new FetchItem(mItemRefVar).execute(mNumberChoosen);
            mNameData = (String[]) mItemRefVar[0];
            mTypeData = (String[]) mItemRefVar[1];
            mIconData = (String[]) mItemRefVar[2];
            mRarityData = (String[]) mItemRefVar[3];
            mVendorData = (int[]) mItemRefVar[4];
            ListPopulation();
            */
           // new FetchItem(itemList).onPostExecute(mNumberChoosen);
            new FetchItem(listAdapter, mRecyclerView).execute(mNumberChoosen);
        } catch (Exception e) {
            Log.d(LOG_BAT, "mNameData is null");
        }
/*
        listAdapter = new RecAdapter(itemList, this);
        mRecyclerView.setAdapter(listAdapter);
*/

    }

    private void ListPopulation() {
        if (mNameData == null) {
            for (int i = 0; i < mNameData.length; i++) {
                itemList.add(new AnItem(mNameData[i], mTypeData[i], mIconData[i], mRarityData[i], mVendorData[i]));
            }
        }
    }
}