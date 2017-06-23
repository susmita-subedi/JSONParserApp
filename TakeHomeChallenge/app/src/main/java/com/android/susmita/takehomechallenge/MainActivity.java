package com.android.susmita.takehomechallenge;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.susmita.takehomechallenge.GetJsonFromUrlTask;
import com.android.susmita.takehomechallenge.Info;

public class MainActivity extends Activity{
    private ListView listview;
    private ArrayList<Info> infos;
    private ArrayAdapter<Info> adapter;

    private final static String TAG =  MainActivity.class.getSimpleName();
    private final static String url = "http://jsonplaceholder.typicode.com/photos";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listview = (ListView) findViewById(R.id.listview);
        setListViewAdapter();
        getDataFromInternet();
    }

    private void getDataFromInternet() {
        new GetJsonFromUrlTask(this, url).execute();
    }

    private void setListViewAdapter(){
        infos = new ArrayList<Info>();
        adapter = new CustomListViewAdapter(this, R.layout.item_listview, infos);
        listview.setAdapter(adapter);
    }
    public void parseJsonResponse(String result) {
        Log.i(TAG, result);
        try {
            JSONObject json = new JSONObject(result);
            JSONArray jArray = new JSONArray(json.getString(""));
            for (int i = 0; i < jArray.length(); i++) {

                JSONObject jObject = jArray.getJSONObject(i);
                Info info = new Info();
                info.setImageUrl(jObject.getString("thumbnailUrl"));
                info.setNumber(jObject.getString("id"));
                info.setDescription(jObject.getString("title"));

                infos.add(info);

            }
            adapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}