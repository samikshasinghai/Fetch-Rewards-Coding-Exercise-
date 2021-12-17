package coding.excercise;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;


public class MainActivity extends AppCompatActivity
{
    ExpandableListView expandableList;
    CustomAdapter customAdapter;

    public ArrayList<items> data_items = new ArrayList<items>();
    public static String apiData = "XYZ";
    public  ArrayList<Integer> listIDs = new ArrayList<Integer>();
    public  ArrayList<Integer> ids = new ArrayList<Integer>();
    public HashMap<Integer,ArrayList<String>> childitems = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        expandableList = findViewById(R.id.expandableList);

        //Get Data from the URL
        getDataFromURL json_data = new getDataFromURL();

        try {
            apiData = json_data.execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Parse JSON Data - This will create two Array List: 1. List IDs array List | 2. A filtered list of item objects with a valid Item Name.
        parseData(apiData);

        //Sorting the list Ids in the ListID array and sorting by ID of items in the items array.
        sortData();

        //Building dataset for Expandable view

        for(int i = 0;i<listIDs.size();i++){
            ArrayList<String> arrayList = new ArrayList<>();
            for(int j=0;j<data_items.size();j++){

                if(data_items.get(j).getListid() == listIDs.get(i))
                {
                    arrayList.add(data_items.get(j).getName() + "|" + data_items.get(j).getId());
                }

        }
            childitems.put(listIDs.get(i),arrayList);

    }

        customAdapter = new CustomAdapter(this, listIDs, childitems);
        expandableList.setAdapter(customAdapter);

    }

    public void parseData(String apiData){
        try {
            JSONArray data = new JSONArray(apiData);
            System.out.println("JSON Records Counts:" + data.length());
            for (int i=0; i<data.length(); i++){
                JSONObject item_detail = data.getJSONObject(i);
                String name = item_detail.getString("name");
                //Filtering out any items with name as spaces or null.
                if(name.length() != 0 && name != "null") {
                    Integer listId = item_detail.getInt("listId");
                    if (!listIDs.contains(listId)) {
                        listIDs.add(listId);
                    }
                    items item = new items(listId, item_detail.getInt("id"), name);
                    data_items.add(item);
                }
            }
        }

        catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void sortData(){

        Collections.sort(listIDs);

        //Since the Item Names were derived from Item IDs, I sorted the data based on Item IDs.

        Collections.sort(data_items, new Comparator<items>() {
            @Override
            public int compare(items i1, items i2) {
                //Ascending Order Sort
                return i1.getId() - i2.getId();
            }
        });

    }








}