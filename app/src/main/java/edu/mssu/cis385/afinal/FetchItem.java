package edu.mssu.cis385.afinal;

import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class FetchItem extends AsyncTask<String, Void, ArrayList<AnItem>> {
    private static final String LOG_CAT = FetchItem.class.getSimpleName();



    private WeakReference<RecAdapter> listAdapter;
    private WeakReference<RecyclerView> mRecyclerView;
    private String[] nameData;
    private String[] typeData;
    private String[] iconData;
    private String[] rarityData;
    private int[] vendorData;
    private Object[] itemRefVars = new Object[5];
    private ArrayList<AnItem> itemList = new ArrayList<>();
    private String[] vendorDataReworked;


    //initialize api data
    public FetchItem(RecAdapter listAdapter, RecyclerView recycler) {
        this.listAdapter = new WeakReference<>(listAdapter);
        this.mRecyclerView = new WeakReference<>(recycler);
    }

    //get api data
    @Override
    protected ArrayList<AnItem> doInBackground(String... strings) {

        String aJsonString = NetworkUtils.getItemInfo(strings[0]);
        makeJsonElements(aJsonString);

        return itemList;
    }

    //set api data into recylcer view
    @Override
    protected void onPostExecute(ArrayList<AnItem> anItems) {
        super.onPostExecute(anItems);
        listAdapter.get().setItemList(itemList);
        mRecyclerView.get().setAdapter(listAdapter.get());

        for(int i = 0; i < itemList.size(); i++)
        {
            System.out.println(itemList.toString());
        }
    }

    //parse out aip data
    private void makeJsonElements(String s)
    {

        try {
            JSONArray itemData = new JSONArray(s);
            nameData = new String[itemData.length()];
            typeData = new String[itemData.length()];
            iconData = new String[itemData.length()];
            rarityData = new String[itemData.length()];
            vendorDataReworked = new String[itemData.length()];
            vendorData = new int[itemData.length()];
            for (int i = 0; i < itemData.length(); i++) {
                JSONObject jsonObj = itemData.getJSONObject(i);
                nameData[i] = jsonObj.getString("name");
                typeData[i] = jsonObj.getString("type");
                iconData[i] = jsonObj.getString("icon");
                rarityData[i] = jsonObj.getString("rarity");
                vendorData[i] = jsonObj.getInt("vendor_value");

                //turn int value into in game vendor value
                vendorDataReworked[i] = AnItem.vendorStringConverter(vendorData[i]);
            //put data into AnItem class called itemList
            itemList.add(new AnItem(nameData[i], typeData[i], rarityData[i], vendorDataReworked[i], iconData[i]));
        }
        } catch (JSONException e) {
            e.printStackTrace();
            System.out.println("makeJsonElements catch triggered");
        }

        //move ref variables into 1 object, unused .....
        itemRefVars[0] = nameData;
        itemRefVars[1] = typeData;
        itemRefVars[2] = iconData;
        itemRefVars[3] = rarityData;
        itemRefVars[4] = vendorData;

    }
}
