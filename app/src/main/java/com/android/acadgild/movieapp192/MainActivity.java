package com.android.acadgild.movieapp192;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.android.acadgild.movieapp192.adapter.DataAdapter;
import com.android.acadgild.movieapp192.models.DataHandler;
import com.android.acadgild.movieapp192.network.CallAddr;
import com.android.acadgild.movieapp192.network.NetworkStatus;
import com.android.acadgild.movieapp192.network.OnWebServiceResult;
import com.android.acadgild.movieapp192.utils.CommonUtilities;
import com.squareup.okhttp.FormEncodingBuilder;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnWebServiceResult {

    //Webservice URL to be called
    String url= "http://api.themoviedb.org/3/tv/top_rated?api_key=8496be0b2149805afa458ab8ec27560c";
    //Arraylist object to form list of movies
    List<DataHandler> model= new ArrayList<>();
    //Object of RecyclerView
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //GEt UI of recyclerView
        recyclerView= (RecyclerView)findViewById(R.id.recycler);
        //call hitRequest
        hitRequest();
    }

    private void hitRequest(){
        FormEncodingBuilder parameters= new FormEncodingBuilder();
        parameters.add("page" , "1");
        if(NetworkStatus.getInstance(this).isConnectedToInternet()) {
            CallAddr call = new CallAddr(this, url,parameters, CommonUtilities.SERVICE_TYPE.GET_DATA, this);
            call.execute();
        }else {
            Toast.makeText(this, "No Network!!", Toast.LENGTH_SHORT).show();
        }
    }

    //getWebResponse method
    @Override
    public void getWebResponse(String result, CommonUtilities.SERVICE_TYPE type) {
        Log.e("res", "type= "+ type+ " res= "+ result);
        try {
            //object JSONObject class
            JSONObject obj= new JSONObject(result);
            //object of JSONArray class
            JSONArray arr= obj.getJSONArray("results");
            //loop through the length of JSONArray
            for(int i=0; i<arr.length(); i++) {
                //Object of JSONObject class to get single item from array
                JSONObject jobj= arr.getJSONObject(i);
                //Object of DataHandler class
                DataHandler handler = new DataHandler();
                //set values to properties of data handler class
                handler.setMid("Id :"+jobj.getString("id"));
                handler.setVote("Votes: "+jobj.getString("vote_count"));
                handler.setName(jobj.getString("name"));
                //add into arraylist
                model.add(handler);
            }

            //DataAdapter object to set adapter to recyclerr view
            DataAdapter adapter= new DataAdapter(this, model);
            //set adapter to recyclerView
            recyclerView.setAdapter(adapter);
            //setLayoutManager to recyclerView
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
