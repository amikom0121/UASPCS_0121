package com.example.uaspcs_0121;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.example.uaspcs_0121.Adapter.FavoriteAdapter;
import com.example.uaspcs_0121.Models.Favorite;

import java.util.ArrayList;

public class FavoriteScreen extends AppCompatActivity {


    ArrayList<Favorite> arrayFavorite = new ArrayList<Favorite>();
    RecyclerView rcFavorit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorit);

        final SQLiteDatabase mydatabase = openOrCreateDatabase("database_0121",android.content.Context.MODE_PRIVATE,null);

        Cursor res =  mydatabase.rawQuery( "SELECT * FROM favorit", null );

        res.moveToFirst();

        rcFavorit = findViewById(R.id.rcFavorit);

        while(res.isAfterLast() == false){
            String matchid = res.getString(0);
            String namaevent = res.getString(1);
            String liga = res.getString(2);
            String venue = res.getString(3);
            String waktu = res.getString(4);
            String hasil = res.getString(5);
            String image = res.getString(6);
            System.out.println(namaevent);
            arrayFavorite.add(new Favorite(matchid,image,namaevent, "-","-","-"));
            res.moveToNext();
        }

        FavoriteAdapter fAdapter = new FavoriteAdapter(getApplicationContext(), arrayFavorite);

        rcFavorit.setAdapter(fAdapter);
        rcFavorit.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

    }
}
