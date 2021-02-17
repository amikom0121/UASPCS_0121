package com.example.uaspcs_0121;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.uaspcs_0121.Adapter.UpcomingAdapter;
import com.example.uaspcs_0121.Models.Upcoming;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class UpcomingScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_akandatang);

        final RecyclerView rcBerlangsung = findViewById(R.id.rcAkandatang);


        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url ="https://www.thesportsdb.com/api/v1/json/1/lookup_all_teams.php?id=4331";

        final ArrayList<Upcoming> akandatang = new ArrayList<Upcoming>();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);
                            JSONArray events = obj.getJSONArray("teams");
                            for(int x=0;x<events.length();x++){
                                JSONObject item = events.getJSONObject(x);
                                String matchTitle = item.getString("strAlternate");
                                String matchDescription = item.getString("strDescriptionEN");
                                String image = item.getString("strTeamBadge");
                                akandatang.add(new Upcoming(matchTitle,"....","....",matchDescription,image));

                                UpcomingAdapter akandatangAdapter = new UpcomingAdapter(getApplicationContext(), akandatang );

                                rcBerlangsung.setAdapter(akandatangAdapter);
                                rcBerlangsung.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error);
            }
        });


        queue.add(stringRequest);

    }
}
