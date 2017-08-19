package com.example.guill.petagram.restApi;

/**
 * Created by guill on 18/08/2017.
 */
public class ConstantesRestApi {

    public static final String API_BASE_URL = "https://api.instagram.com";
    public static final String VERSION = "/v1/";
    public static final String ROOT_URL = API_BASE_URL + VERSION;
    public static final String PATH_USER_DATA = "users/search/?";
    public static final String PATH_USER_SELF_MEDIA_RECENT = "users/self/media/recent/?";
    public static final String PATH_USER_MEDIA_RECENT = "users/{user}/media/recent/?";
    public static final String ACCESS_TOKEN = "access_token=";
    public static final String API_ACCESS_TOKEN = "5893698639.58be7b8.69f107c9fbf74cdd99524c0e33231a29";

    public static final String REQUEST_USER_SELF_MEDIA_RECENT = PATH_USER_SELF_MEDIA_RECENT + ACCESS_TOKEN + API_ACCESS_TOKEN;
    public static final String URL_GET_USER_DATA = PATH_USER_DATA + ACCESS_TOKEN  + API_ACCESS_TOKEN;
    public static final String URL_GET_USER_MEDIA_RECENT = PATH_USER_MEDIA_RECENT + ACCESS_TOKEN  + API_ACCESS_TOKEN;
    //https://api.instagram.com/v1/users/{user}/media/recent/?access_token=ACCESS-TOKEN

}
