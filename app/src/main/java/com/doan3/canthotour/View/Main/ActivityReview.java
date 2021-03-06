package com.doan3.canthotour.View.Main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.doan3.canthotour.Adapter.HttpRequestAdapter.httpGet;
import com.doan3.canthotour.Adapter.HttpRequestAdapter.httpPost;
import com.doan3.canthotour.Adapter.HttpRequestAdapter.httpPut;
import com.doan3.canthotour.Config;
import com.doan3.canthotour.Helper.JsonHelper;
import com.doan3.canthotour.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import static com.doan3.canthotour.View.Personal.ActivityPersonal.userId;

/**
 * Created by zzacn on 12/7/2017.
 */

public class ActivityReview extends AppCompatActivity {
    Button btnSend, btnCancel;
    TextView txtTitle, txtReview;
    RatingBar rbRating;
    int id;
    String idReview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        btnSend = findViewById(R.id.btnGui);
        btnCancel = findViewById(R.id.btnCancel);
        rbRating = findViewById(R.id.rbReview);
        txtTitle = findViewById(R.id.etTitle);
        txtReview = findViewById(R.id.txtComment);

        id = getIntent().getIntExtra("id", 1);
        idReview = getIntent().getStringExtra("idRating");
        if (!idReview.equals("0")) {
            try {
                String rs =
                        new httpGet().execute(Config.URL_HOST + Config.URL_GET_REVIEW + "/" + idReview).get();
                ArrayList<String> arr = JsonHelper.parseJsonNoId(new JSONArray(rs), Config.GET_KEY_JSON_REVIEW);
                rbRating.setRating(Float.parseFloat(arr.get(0)));
                txtTitle.setText(arr.get(1));
                txtReview.setText(arr.get(2));
            } catch (InterruptedException | ExecutionException | JSONException e) {
                e.printStackTrace();
            }
        }
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txtTitle.getText().toString().equals("")
                        && txtReview.getText().toString().equals("")
                        && (int) rbRating.getRating() == 0) {
                    Toast.makeText(ActivityReview.this, getResources().getString(R.string.text_PleaseReviewBeforeSend), Toast.LENGTH_SHORT).show();
                } else {
                    JSONObject json = null;
                    try {
                        json = new JSONObject("{"
                                + Config.POST_KEY_JSON_REVIEW.get(0) + ":\"" + id + "\","
                                + Config.POST_KEY_JSON_REVIEW.get(1) + ":\"" + userId + "\","
                                + Config.POST_KEY_JSON_REVIEW.get(2) + ":\"" + (int) rbRating.getRating() + "\","
                                + Config.POST_KEY_JSON_REVIEW.get(3) + ":\"" + txtTitle.getText() + "\","
                                + Config.POST_KEY_JSON_REVIEW.get(4) + ":\"" + txtReview.getText() + "\"}");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    if (idReview.equals("0")) {
                        try {
                            String stt = new httpPost(json).execute(Config.URL_HOST + Config.URL_POST_REVIEW).get();
                            if (stt.equals("\"status:200\""))
                                intend(getResources().getString(R.string.text_ReviewCompleted));
                            else
                                Toast.makeText(ActivityReview.this,
                                        getResources().getString(R.string.text_ReviewFailure), Toast.LENGTH_SHORT).show();
                        } catch (InterruptedException | ExecutionException e) {
                            e.printStackTrace();
                        }
                    } else {
                        try {
                            String stt = new httpPut(json).execute(Config.URL_HOST + Config.URL_PUT_REVIEW + idReview).get();
                            if (stt.equals("\"status:200\""))
                                intend(getResources().getString(R.string.text_ReviewCompleted));
                            else
                                Toast.makeText(ActivityReview.this,
                                        getResources().getString(R.string.text_ReviewFailure), Toast.LENGTH_SHORT).show();
                        } catch (InterruptedException | ExecutionException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    void intend(String mess) {
        Intent intent = new Intent();
        intent.putExtra("mess", mess);
        setResult(RESULT_OK, intent);
        finish();
    }
}
