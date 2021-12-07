package edu.mssu.cis385.afinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;

public class MainActivity extends AppCompatActivity {

    private EditText mPageInput;
    private TextView mTitle;
    private ConstraintLayout mainActivity;
    private Button mExecute;

    public static final String EXTRA_MESSAGE =
            "edu.mssu.cis385.afinal.extra.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPageInput = findViewById(R.id.takeInput);
        mTitle = findViewById(R.id.search_title);
        mainActivity = findViewById(R.id.main_activity);
        mExecute = findViewById(R.id.searchButton);
    }

    public void searchItems(View view) {
        int pageNumber = -1;
        String queryString;
            try {
                queryString = mPageInput.getText().toString();
                System.out.println(queryString);
                    if(queryString == null){
                        Toast.makeText(getApplicationContext(), "Please enter a number",
                                Toast.LENGTH_SHORT).show();
                        throw new Exception();}
                    for(int i =0; i < queryString.length(); i++)
                    {
                        if(Character.isLetter(queryString.charAt(i)))
                        {
                            Toast.makeText(getApplicationContext(), "Please enter a number",
                                Toast.LENGTH_SHORT).show();
                            throw new Exception();}
                        }
                    pageNumber = Integer.parseInt(queryString);
                        if (pageNumber <= 304 && pageNumber >= 0) {
                            Intent intent = new Intent(this, dataDisplay.class);
                            intent.putExtra(EXTRA_MESSAGE, queryString);
                            startActivity(intent);
                        } else
                            { Toast.makeText(getApplicationContext(), "Please Enter a number between 0 and 304",
                                    Toast.LENGTH_SHORT).show();
                        }
                } catch (Exception e) {}
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
            menu.findItem(R.id.darkMode).setTitle(R.string.light_mode);

        }else{
            menu.findItem(R.id.darkMode).setTitle(R.string.dark_mode);
        }
        return true;
    }

}
