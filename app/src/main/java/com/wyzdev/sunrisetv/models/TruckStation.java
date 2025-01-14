package com.wyzdev.sunrisetv.models;

/**
 * Created by Noel Emmanuel Roodly on 8/26/2022.
 */


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TruckStation {
    @SerializedName("id") @Expose public Long id;
    @SerializedName("station_id") @Expose public Long stationId;
    @SerializedName("truck_id") @Expose public Long truck_id;
    @SerializedName("price") @Expose public Double price;
    @SerializedName("taxe") @Expose public Double taxe;
    @SerializedName("created") @Expose public String created;
    @SerializedName("modified") @Expose public String modified;
}