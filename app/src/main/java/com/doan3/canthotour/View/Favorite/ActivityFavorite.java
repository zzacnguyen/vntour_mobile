package com.doan3.canthotour.View.Favorite;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.doan3.canthotour.Adapter.FavoriteAdapter;
import com.doan3.canthotour.Adapter.HttpRequestAdapter;
import com.doan3.canthotour.Config;
import com.doan3.canthotour.Helper.BottomNavigationViewHelper;
import com.doan3.canthotour.Helper.JsonHelper;
import com.doan3.canthotour.Model.ModelPlace;
import com.doan3.canthotour.Model.ObjectClass.Place;
import com.doan3.canthotour.R;
import com.doan3.canthotour.View.Main.MainActivity;
import com.doan3.canthotour.View.Notify.ActivityNotify;
import com.doan3.canthotour.View.Personal.ActivityPersonal;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.File;
import java.util.ArrayList;

public class ActivityFavorite extends AppCompatActivity {

    TextView txtTenDD;
    ImageView imgHinhDD;
    RecyclerView recyclerView;
    private ArrayList<String> id = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yeuthich);

        txtTenDD = findViewById(R.id.textViewYeuThich);
        imgHinhDD = findViewById(R.id.imageViewYeuThich);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavView_Bar);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);

        File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
        File file = new File(path, "/dsyeuthich.json");

        recyclerView = findViewById(R.id.RecyclerView_DanhSachYeuThich);
        recyclerView.setHasFixedSize(true); //Tối ưu hóa dữ liệu, k bị ảnh hưởng bởi nội dung trong adapter

        LinearLayoutManager linearLayoutManager =
                new LinearLayoutManager(ActivityFavorite.this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        load(file);

        menuBotNavBar();
    }

    @Override
    protected void onPause() {
        super.onPause();
        File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
        File file = new File(path, "/dsyeuthich.json");
        if (file.exists()) {
            new PostJson(file).execute(Config.URL_HOST + Config.URL_GET_ALL_FAVORITE);
        }
    }

    private void menuBotNavBar() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavView_Bar);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);

        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.ic_trangchu:
                        startActivity(new Intent(ActivityFavorite.this, MainActivity.class));
                        break;
                    case R.id.ic_yeuthich:

                        break;
                    case R.id.ic_thongbao:
                        startActivity(new Intent(ActivityFavorite.this, ActivityNotify.class));
                        break;
                    case R.id.ic_canhan:
                        startActivity(new Intent(ActivityFavorite.this, ActivityPersonal.class));
                        break;
                }
                return false;
            }
        });
    }

    private void load(File file) {
        ArrayList<Place> places = new ModelPlace().getFavoriteList(file);

        FavoriteAdapter favoriteAdapter = new FavoriteAdapter(places, getApplicationContext());
        recyclerView.setAdapter(favoriteAdapter);
    }

    private class PostJson extends AsyncTask<String, Void, Void> {
        File file;

        private PostJson(File file) {
            this.file = file;
        }

        @Override
        protected Void doInBackground(String... strings) {
            JSONArray jsonFile;
            try {
                jsonFile = new JSONArray(JsonHelper.readJson(file));
                for (int i = 0; i < jsonFile.length(); i++) {
                    HttpRequestAdapter.httpPost(strings[0], jsonFile.getJSONObject(i));
                }
                file.delete();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
