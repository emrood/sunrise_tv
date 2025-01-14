package com.wyzdev.sunrisetv.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by hollyn.derisse on 06/04/2018.
 */

public class ApiResponse {
    @SerializedName("error") @Expose public Boolean error;
    @SerializedName("message") @Expose public String message;
//    @SerializedName("data") @Expose public Receiving receiving;
}
