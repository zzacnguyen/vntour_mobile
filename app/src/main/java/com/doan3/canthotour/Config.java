package com.doan3.canthotour;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Config {
    public static final String URL_HOST = "http://192.168.1.57/doan3_canthotour/";
    public static final String URL_LOGIN = "login-mobile";
    public static final String URL_GET_PROVINCE = "province";
    public static final String URL_GET_DISTRICT = "district/";
    public static final String URL_GET_WARD = "ward/";
    public static final String URL_REGISTER = "register-mobile";
    public static final ArrayList<String> URL_SEARCH_TYPE =
            new ArrayList<>(Arrays.asList("search/searchServicesTypeKeyword/type=", "&keyword="));
    public static final String URL_SEARCH_ALL = "search/services/keyword=";
    public static final ArrayList<String> URL_SEARCH_SERVICE_NEAR =
            new ArrayList<>(Arrays.asList("search/servicevicinity/location=", "&type=", "&radius="));
    public static final String URL_GET_ALL_EATS = "eating";
    public static final String URL_GET_HISTORY_SEARCH = "usersearch";
    public static final String URL_POST_REVIEW = "rating-post";
    public static final String URL_PUT_REVIEW = "rating-put/";
    public static final String URL_GET_REVIEW = "rating-view";
    public static final String URL_GET_ALL_REVIEWS = "rating-service/";
    public static final String URL_GET_ALL_PLACES = "sightseeing";
    public static final String URL_GET_ALL_HOTELS = "hotels";
    public static final ArrayList<String> URL_GET_SERVICE_INFO =
            new ArrayList<>(Arrays.asList("service/service-id=", "&user-id="));
    public static final String URL_GET_ALL_VEHICLES = "transport";
    public static final String URL_GET_ALL_EVENTS = "events";
    public static final String URL_GET_ALL_ENTERTAINMENTS = "entertainments";
    public static final String URL_GET_ALL_FAVORITE = "like";
    public static final String URL_GET_LINK_THUMB_1 = "get-thumb-1/";
    public static final String URL_GET_LINK_THUMB_2 = "get-thumb-2/";
    public static final String URL_GET_LINK_DETAIL_1 = "get-detail-1/";
    public static final String URL_GET_LINK_DETAIL_2 = "get-detail-2/";
    public static final String URL_GET_LINK_BANNER = "get-banner/";
    public static final String URL_GET_THUMB = "thumbnails/";
    public static final String URL_POST_PLACE = "add-places";
    public static final String URL_POST_SERVICE = "add-services/";
    public static final String URL_POST_IMAGE = "upload-image/";
    public static final String URL_GET_EVENT_NUMBER = "counter-events";
    public static final String URL_GET_TRIP_SCHEDULE_INFO = "list-schedule-details/";
    public static final String URL_GET_TRIP_SCHEDULE = "list-schedule/";
    public static final String URL_POST_TRIP_SCHEDULE = "post-schedule/user=";

    public static final String FOLDER = "/vietnamtour";
    public static final String FOLDER_AVATAR = "avatar";
    public static final String FILE_EVENT = "listOfEvent.json";
    public static final String FILE_LIKE = "listOfFavorite.json";
    public static final String NULL = "null";
    public static final String KEY_DISTANCE = "distance";
    public static final String DEFAULT_DISTANCE = "500";

    public static final ArrayList<String> KEY_NEAR_LOCATION =
            new ArrayList<>(Arrays.asList("longitude", "latitude", "serviceType"));

    public static final ArrayList<String> KEY_SERVICE_INFO =
            new ArrayList<>(Arrays.asList("isLike", "idLike", "like_id", "isRating", "idRating",
                    "rating_id", "service", "type_event", "type_name", "count_like"));

    public static final ArrayList<String> GET_KEY_JSON_EAT =
            new ArrayList<>(Arrays.asList("eat_name", "image_id", "image_details_1"));

    public static final ArrayList<String> GET_KEY_JSON_USER =
            new ArrayList<>(Arrays.asList("username", "avatar", "level"));

    public static final ArrayList<String> GET_KEY_JSON_PLACE =
            new ArrayList<>(Arrays.asList("sightseeing_name", "image_id", "image_details_1"));

    public static final ArrayList<String> GET_KEY_JSON_SERVICE_INFO =
            new ArrayList<>(Arrays.asList("hotel_name", "entertainments_name", "transport_name",
                    "sightseeing_name", "eat_name", "sv_website", "sv_description", "sv_open", "sv_close", "sv_lowest_price",
                    "sv_highest_price", "pl_address", "pl_phone_number", "rating", "pl_latitude", "pl_longitude"));

    public static final ArrayList<String> GET_KEY_JSON_HOTEL =
            new ArrayList<>(Arrays.asList("hotel_name", "image_id", "image_details_1"));

    public static final ArrayList<String> GET_KEY_JSON_VEHICLE =
            new ArrayList<>(Arrays.asList("transport_name", "image_id", "image_details_1"));

    public static final ArrayList<String> GET_KEY_JSON_EVENT =
            new ArrayList<>(Arrays.asList("event_name", "event_start", "event_end",
                    "image_id", "image_details_1"));

    public static final ArrayList<String> GET_KEY_JSON_ENTERTAINMENT =
            new ArrayList<>(Arrays.asList("entertainments_name", "image_id", "image_details_1"));

    public static final ArrayList<String> GET_KEY_JSON_SERVICE_LIST =
            new ArrayList<>(Arrays.asList("hotel_name", "entertainments_name", "transport_name",
                    "sightseeing_name", "eat_name", "image_id", "image_details_1"));

    public static final ArrayList<String> GET_KEY_JSON_LOAD =
            new ArrayList<>(Arrays.asList("data", "next_page_url", "total"));

    public static final ArrayList<String> GET_KEY_JSON_PROVINCE =
            new ArrayList<>(Collections.singletonList("province_city_name"));
    public static final ArrayList<String> GET_KEY_JSON_DISTRICT =
            new ArrayList<>(Collections.singletonList("district_name"));
    public static final ArrayList<String> GET_KEY_JSON_WARD =
            new ArrayList<>(Collections.singletonList("ward_name"));

    public static final ArrayList<String> GET_KEY_JSON_ALL_REVIEW =
            new ArrayList<>(Arrays.asList("username", "vr_rating", "vr_title", "vr_ratings_details", "date_rating"));

    public static final ArrayList<String> GET_KEY_JSON_REVIEW =
            new ArrayList<>(Arrays.asList("vr_rating", "vr_title", "vr_ratings_details"));

    public static final ArrayList<String> GET_KEY_JSON_LOGIN =
            new ArrayList<>(Arrays.asList("result", "error", "status", "ERROR", "OK"));

    public static final ArrayList<String> GET_KEY_JSON_TRIP_SCHEDULE =
            new ArrayList<>(Arrays.asList("trip_name", "trip_startdate", "trip_enddate"));

    public static final ArrayList<String> GET_KEY_SEARCH_NEAR =
            new ArrayList<>(Arrays.asList("data", "status", "NOT FOUND", "OK"));

    public static final ArrayList<String> POST_KEY_HISTORY_SEARCH =
            new ArrayList<>(Arrays.asList("\"id_service\"", "\"id_user\""));

    public static final ArrayList<String> POST_KEY_LOGIN =
            new ArrayList<>(Arrays.asList("\"username\"", "\"password\""));

    public static final ArrayList<String> POST_KEY_REGISTER =
            new ArrayList<>(Arrays.asList("\"username\"", "\"password\""));

    public static final ArrayList<String> POST_KEY_TRIP_SCHEDULE =
            new ArrayList<>(Arrays.asList("\"trip_name\"", "\"trip_startdate\"", "\"trip_enddate\""));

    public static final ArrayList<String> POST_KEY_JSON_REVIEW =
            new ArrayList<>(Arrays.asList("\"service_id\"", "\"user_id\"", "\"vr_rating\"",
                    "\"vr_title\"", "\"details\""));

    public static final ArrayList<String> POST_KEY_JSON_LIKE =
            new ArrayList<>(Arrays.asList("\"service_id\"", "\"user_id\""));

    public static final ArrayList<String> POST_KEY_JSON_PLACE =
            new ArrayList<>(Arrays.asList("\"pl_name\"", "\"pl_details\"", "\"pl_address\"", "\"pl_phone_number\"",
                    "\"pl_latitude\"", "\"pl_longitude\"", "\"id_ward\"", "\"partner_user\"", "\"tourguide_user\""));

    public static final ArrayList<String> POST_KEY_JSON_LIKE_SERVICE =
            new ArrayList<>(Arrays.asList("\"id\"", "\"hotel_name\"", "\"entertainments_name\"", "\"transport_name\"",
                    "\"sightseeing_name\"", "\"eat_name\"", "\"image_id\"", "\"image_details_1\""));

    public static final ArrayList<String> POST_KEY_JSON_SERVICE =
            new ArrayList<>(Arrays.asList("\"sv_description\"", "\"sv_open\"", "\"sv_close\"",
                    "\"sv_highest_price\"", "\"sv_lowest_price\"", "\"sv_phone_number\"", "\"sv_types\"",
                    "\"tourguide_user\"", "\"partner_user\"", "\"sv_website\"", "\"id_place\""));

    public static final ArrayList<String> POST_KEY_JSON_SERVICE_EAT =
            new ArrayList<>(Collections.singletonList("\"eat_name\""));
    public static final ArrayList<String> POST_KEY_JSON_SERVICE_HOTEL =
            new ArrayList<>(Arrays.asList("\"hotel_name\"", "\"hotel_number_star\""));
    public static final ArrayList<String> POST_KEY_JSON_SERVICE_SIGHTSEEING =
            new ArrayList<>(Collections.singletonList("\"sightseeing_name\""));
    public static final ArrayList<String> POST_KEY_JSON_SERVICE_ENTERTAINMENTS =
            new ArrayList<>(Collections.singletonList("\"entertainments_name\""));
    public static final ArrayList<String> POST_KEY_JSON_SERVICE_TRANSPORT =
            new ArrayList<>(Collections.singletonList("\"transport_name\""));

}
