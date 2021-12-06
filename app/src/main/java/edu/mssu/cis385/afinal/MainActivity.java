package edu.mssu.cis385.afinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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
    public static final String EXTRA_MESSAGE =
            "edu.mssu.cis385.afinal.extra.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPageInput = (EditText)findViewById(R.id.pageInput);
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
}
