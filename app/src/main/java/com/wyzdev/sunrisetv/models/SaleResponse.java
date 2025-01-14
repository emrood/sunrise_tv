package com.wyzdev.sunrisetv.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Noel Emmanuel Roodly on 8/25/2019.
 */
public class SaleResponse {
    @SerializedName("error") @Expose public Boolean error;
    @SerializedName("message") @Expose public String message;
    @SerializedName("sale") @Expose public Sale sale;
}
