package com.wyzdev.sunrisetv.models;

/**
 * Created by Noel Emmanuel Roodly on 6/19/2019.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class User {

    @SerializedName("id") @Expose public Long id;
    @SerializedName("username") @Expose public String userName;
    @SerializedName("last_name") @Expose public String lastName;
    @SerializedName("first_name") @Expose public String firstName;
    @SerializedName("photo") @Expose public String photo;
    @SerializedName("role_id") @Expose public Integer roleId;
    @SerializedName("access_token") @Expose public String token;
    @SerializedName("remember_token") @Expose public String remember_token;
    @SerializedName("card") @Expose public String card;
    @SerializedName("status") @Expose public Integer status;
    @SerializedName("station_id") @Expose public Long stationId;
}