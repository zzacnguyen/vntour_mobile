package com.doan3.canthotour.View.Personal;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.doan3.canthotour.Helper.JsonHelper;
import com.doan3.canthotour.R;
import com.doan3.canthotour.View.Main.ActivityServiceInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;


public class ActivityOption extends AppCompatActivity {
    LinearLayout btnKhoangCachLc;
    TextView txtKhoangCachLc;
    String khoangcach;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caidat);

        btnKhoangCachLc = findViewById(R.id.btnKhoangCachLC);
        txtKhoangCachLc = findViewById(R.id.textViewKhoanCachLc);

        File path = new File(Environment.getExternalStorageDirectory() + "/canthotour");
        if (!path.exists()) {
            path.mkdirs();
        }
        final File file = new File(path, "khoangcach.json");
        try {
            txtKhoangCachLc.setText(new JSONArray(JsonHelper.readJson(file)).getJSONObject(0).
                    getString("khoangcach")+"m");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        btnKhoangCachLc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(ActivityOption.this);
                dialog.setTitle("Đặt lại khoảng cách");
                dialog.setCancelable(false); //Khóa màn hình ngoài sau khi ấn vàodialog
                dialog.setContentView(R.layout.custom_khoangcach);

                //Ánh xạ các palette trong dialog
                final EditText etKhoangCach = dialog.findViewById(R.id.etKhoangCach);
                Button btnDongY = dialog.findViewById(R.id.btnDongYKC);
                Button btnHuy = dialog.findViewById(R.id.btnHuyKC);

                btnDongY.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        khoangcach = etKhoangCach.getText().toString().trim();
                        try {
                            file.delete();
                            JsonHelper.writeJson(file, new JSONObject("{\"khoangcach\":\"" + khoangcach + "\"}"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        txtKhoangCachLc.setText(khoangcach + "m");

                        dialog.cancel();
                    }
                });

                btnHuy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.cancel();
                    }
                });

                dialog.show();
            }
        });

        ActivityServiceInfo.menuBotNavBar(this);
    }
}
