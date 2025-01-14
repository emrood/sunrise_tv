package com.wyzdev.sunrisetv.models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Flight {
    @SerializedName("id") @Expose public Long id;
    @SerializedName("name") @Expose public String name;
    @SerializedName("status") @Expose public Integer status;
    @SerializedName("station_id") @Expose public Integer stationId;
}
