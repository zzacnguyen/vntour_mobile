package com.doan3.canthotour.View.Personal.Login_Register;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.doan3.canthotour.Adapter.HttpRequestAdapter.httpPost;
import com.doan3.canthotour.Config;
import com.doan3.canthotour.Helper.JsonHelper;
import com.doan3.canthotour.Model.SessionManager;
import com.doan3.canthotour.R;
import com.doan3.canthotour.View.Personal.ActivityPersonal;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import static com.doan3.canthotour.Model.ModelService.setImage;
import static com.doan3.canthotour.View.Main.MainActivity.menuBotNavBar;
import static com.doan3.canthotour.View.Personal.ActivityPersonal.avatar;
import static com.doan3.canthotour.View.Personal.ActivityPersonal.userId;
import static com.doan3.canthotour.View.Personal.ActivityPersonal.userName;
import static com.doan3.canthotour.View.Personal.ActivityPersonal.userType;

/**
 * Created by zzacn on 12/7/2017.
 */

public class ActivityLogin extends AppCompatActivity {
    EditText etUserId, etPassword;
    Button btnReg, btnLogin;
    int REQUEST_CODE_REGISTER = 1;
    SessionManager sessionManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etUserId = findViewById(R.id.etUserName);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnReg = findViewById(R.id.btnRegister);

        sessionManager = new SessionManager(getApplicationContext());

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (etUserId.getText().toString().equals("")) {
                    etUserId.setError(getResources().getString(R.string.text_UsernameIsNotAllowedToBeEmpty));
                } else if (etPassword.getText().toString().equals("")) {
                    etPassword.setError(getResources().getString(R.string.text_PasswordIsNotAllowedToBeEmpty));
                } else {
                    try {
                        JSONObject jsonPost = new JSONObject("{" + Config.POST_KEY_LOGIN.get(0) + ":\""
                                + etUserId.getText().toString() + "\"," + Config.POST_KEY_LOGIN.get(1) + ":\""
                                + etPassword.getText().toString() + "\"}");
                        String rs = new httpPost(jsonPost).execute(Config.URL_HOST + Config.URL_LOGIN).get();
                        JSONObject jsonGet = new JSONObject(rs);
                        // nếu status = error
                        if (jsonGet.getString(Config.GET_KEY_JSON_LOGIN.get(2)).equals(Config.GET_KEY_JSON_LOGIN.get(3))) {
                            Toast.makeText(ActivityLogin.this, getResources().getString(R.string.text_TheUsernameOrPasswordIsIncorrect),
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            ArrayList<String> arrayUser =
                                    JsonHelper.parseJson(new JSONObject(jsonGet.getString(Config.GET_KEY_JSON_LOGIN.get(0))),
                                            Config.GET_KEY_JSON_USER);

                            userId = Integer.parseInt(arrayUser.get(0));
                            userName = arrayUser.get(1);
                            userType = arrayUser.get(3);
                            if (!arrayUser.get(2).equals(Config.NULL)) {
                                avatar = setImage("", Config.FOLDER_AVATAR, arrayUser.get(2));
                            } else {
                                avatar = null;
                            }

                            sessionManager.createLoginSession(userId + "", userName, userType, avatar);
                            if (getCallingActivity() != null) {
                                if (getCallingActivity().getClassName().equals("com.doan3.canthotour.View.Personal.ActivityPersonal")) {
                                    startActivity(new Intent(ActivityLogin.this, ActivityPersonal.class));
                                } else {
                                    finishActivity(1);
                                    finish();
                                }
                            }
                        }
                    } catch (JSONException | ExecutionException | InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityLogin.this, ActivityRegister.class);
                startActivityForResult(intent, REQUEST_CODE_REGISTER);
            }
        });

        menuBotNavBar(this, 3);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE_REGISTER) {
            if (data.hasExtra("mess"))
                Toast.makeText(this, data.getStringExtra("mess"), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void finishActivity(int requestCode) {
        Intent data = new Intent();
        data.putExtra("mess", getResources().getString(R.string.text_LoginSuccess));
        setResult(RESULT_OK, data);
        super.finishActivity(requestCode);
    }
}
