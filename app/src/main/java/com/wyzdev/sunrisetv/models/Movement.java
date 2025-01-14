package com.wyzdev.sunrisetv.models;

/**
 * Created by Noel Emmanuel Roodly on 8/26/2022.
 */


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Movement {
    @SerializedName("id") @Expose public Long id;
    @SerializedName("name") @Expose public String name;
    @SerializedName("customer_validation") @Expose public boolean customerValidation;
    @SerializedName("with_flight") @Expose public boolean withFlight = true;
    @SerializedName("created") @Expose public String created;
    @SerializedName("modified") @Expose public String modified;
}