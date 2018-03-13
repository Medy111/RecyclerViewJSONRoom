package com.example.android.recyclerviewjsonroom;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.android.recyclerviewjsonroom.adapter.SearchItem;
import com.example.android.recyclerviewjsonroom.adapter.SearchItemAdapter;
import com.example.android.recyclerviewjsonroom.adapter.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private SearchItemAdapter mSearchAdapter;
    private ArrayList<SearchItem> mSearchList;
    private RequestQueue mRequestQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recycler_view);
        // Command to improve performance
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mSearchList = new ArrayList<>();

//        mRequestQueue = Volley.newRequestQueue(this);  = No singleton Pattern
        mRequestQueue = VolleySingleton.getInstance(this).getRequestQueue();     //Use Singleton Pattern Class
        parseJSON();
    }

        private void parseJSON() {
            String url = "https://pixabay.com/api/?key=5303976-fd6581ad4ac165d1b75cc15b3&q=kitten&image_type=photo&pretty=true";

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                    new Response.Listener<JSONObject>() {
                        @Override

                        // Response On Main Thread
                        public void onResponse(JSONObject response) {
                            try {
                                JSONArray jsonArray = response.getJSONArray("hits");

                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject hit = jsonArray.getJSONObject(i);

                                    String creatorName = hit.getString("user");
                                    String imageUrl = hit.getString("webformatURL");


                                    mSearchList.add(new SearchItem(imageUrl, creatorName));
                                }

                                mSearchAdapter = new SearchItemAdapter(MainActivity.this, mSearchList);
                                mRecyclerView.setAdapter(mSearchAdapter);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                }
            });
            // Add the request to the RequestQueue.
            mRequestQueue.add(request);
        }



}
