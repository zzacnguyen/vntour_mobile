package com.doan3.canthotour.Model;

import android.app.Activity;
import android.widget.Toast;

import com.doan3.canthotour.Adapter.HttpRequestAdapter;
import com.doan3.canthotour.Config;
import com.doan3.canthotour.Model.ObjectClass.NearLocation;
import com.doan3.canthotour.Model.ObjectClass.Service;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import static com.doan3.canthotour.Helper.JsonHelper.parseJson;
import static com.doan3.canthotour.Helper.JsonHelper.parseJsonNoId;
import static com.doan3.canthotour.Model.ModelService.setImage;

/**
 * Created by sieut on 4/26/2018.
 */

public class ModelSearch {
    public ArrayList<NearLocation> getNearLocationList(String url, int type, Activity activity) {

        ArrayList<String> arrayList = new ArrayList<>(), keyJson;
        ArrayList<NearLocation> services = new ArrayList<>();

        try {
            JSONObject jsonObject = new JSONObject(new HttpRequestAdapter.httpGet().execute(url).get());
            // nếu status = not found thì hiển thị không tìm thấy
            if (jsonObject.getString(Config.GET_KEY_SEARCH_NEAR.get(1)).equals(Config.GET_KEY_SEARCH_NEAR.get(2))) {
                Toast.makeText(activity, "Không tìm thấy dịch vụ lân cận", Toast.LENGTH_SHORT).show();
            } else {
                // nếu tìm thấy thì thêm vào model nearlocation

                /* nếu loại hình = 1 lấy key json ăn uống
                     nếu = 2 lấy key json khách sạn
                     nếu = 3 lấy key json phương tiện
                     nếu = 4 lấy key json tham quan
                     nếu = 5 lấy key json vui chơi*/
                if (type == 1) {
                    keyJson = Config.GET_KEY_JSON_EAT;
                    keyJson.add(Config.KEY_DISTANCE);
                } else if (type == 2) {
                    keyJson = Config.GET_KEY_JSON_HOTEL;
                    keyJson.add(Config.KEY_DISTANCE);
                } else if (type == 3) {
                    keyJson = Config.GET_KEY_JSON_VEHICLE;
                    keyJson.add(Config.KEY_DISTANCE);
                } else if (type == 4) {
                    keyJson = Config.GET_KEY_JSON_PLACE;
                    keyJson.add(Config.KEY_DISTANCE);
                } else {
                    keyJson = Config.GET_KEY_JSON_ENTERTAINMENT;
                    keyJson.add(Config.KEY_DISTANCE);
                }

                JSONArray jsonArray = jsonObject.getJSONArray(Config.GET_KEY_SEARCH_NEAR.get(0));
                System.out.println(jsonArray);
                for (int i = 0; i < jsonArray.length(); i++) {

                    NearLocation service = new NearLocation();
                    JSONObject jsonObjectData = jsonArray.getJSONObject(i);

                    arrayList.clear();
                    arrayList = parseJson(jsonObjectData, keyJson);

                    //Set hình ảnh
                    service.setNearLocationImage(setImage(Config.URL_HOST + Config.URL_GET_THUMB + arrayList.get(3),
                            arrayList.get(2), arrayList.get(3)));
                    //Set mã dịch vụ
                    service.setNearLocationId(Integer.parseInt(arrayList.get(0)));
                    //Set tên dịch vụ
                    service.setNearLocationName(arrayList.get(1));
                    // set khoảng cách
                    service.setNearLocationDistance(arrayList.get(4));

                    services.add(service);
                }
            }
        } catch (JSONException | InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        return services;
    }

    public ArrayList<Service> getAdvancedSearchList(String url, int type) {

        ArrayList<String> arrayList;
        ArrayList<Service> services = new ArrayList<>();

        try {
            arrayList = parseJsonNoId(new JSONObject(new HttpRequestAdapter.httpGet().
                    execute(url).get()), Config.GET_KEY_JSON_LOAD);
            JSONArray jsonArray = new JSONArray(arrayList.get(0));

            for (int i = 0; i < jsonArray.length(); i++) {

                Service service = new Service();
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                arrayList.clear();
                if (type == 0) {
                    arrayList = parseJson(jsonObject, Config.GET_KEY_JSON_SERVICE_LIST);

                    //Set hình ảnh
                    service.setImage(setImage(Config.URL_HOST + Config.URL_GET_THUMB + arrayList.get(7),
                            arrayList.get(6), arrayList.get(7)));
                    //Set mã dịch vụ
                    service.setId(Integer.parseInt(arrayList.get(0)));
                    //Set tên dịch vụ tìm kiếm
                    service.setName(!arrayList.get(1).equals(Config.NULL) ? arrayList.get(1)
                            : !arrayList.get(2).equals(Config.NULL) ? arrayList.get(2)
                            : !arrayList.get(3).equals(Config.NULL) ? arrayList.get(3)
                            : !arrayList.get(4).equals(Config.NULL) ? arrayList.get(4)
                            : arrayList.get(5));

                    services.add(service);
                } else {
                    if (type == 1) {
                        arrayList = parseJson(jsonObject, Config.GET_KEY_JSON_EAT);
                    } else if (type == 2) {
                        arrayList = parseJson(jsonObject, Config.GET_KEY_JSON_HOTEL);
                    } else if (type == 3) {
                        arrayList = parseJson(jsonObject, Config.GET_KEY_JSON_VEHICLE);
                    } else if (type == 4) {
                        arrayList = parseJson(jsonObject, Config.GET_KEY_JSON_PLACE);
                    } else {
                        arrayList = parseJson(jsonObject, Config.GET_KEY_JSON_ENTERTAINMENT);
                    }

                    //Set hình ảnh
                    service.setImage(setImage(Config.URL_HOST + Config.URL_GET_THUMB + arrayList.get(3),
                            arrayList.get(2), arrayList.get(3)));
                    //Set mã dịch vụ
                    service.setId(Integer.parseInt(arrayList.get(0)));
                    //Set tên dịch vụ yêu thích
                    service.setName(arrayList.get(1));

                    services.add(service);
                }
            }
        } catch (JSONException | InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        return services;
    }
}
