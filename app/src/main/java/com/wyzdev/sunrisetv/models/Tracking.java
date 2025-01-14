package com.wyzdev.sunrisetv.models;

/**
 * Created by Noel Emmanuel Roodly on 8/26/2022.
 */


import androidx.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Tracking {
    @SerializedName("id") @Expose public Long id;
    @SerializedName("products_sale_id") @Expose public Long productSaleId;
    @SerializedName("flight_id") @Expose @Nullable public Long flightId;
    @SerializedName("movement_id") @Expose @Nullable public Long movementId;
    @SerializedName("station_id") @Expose @Nullable public Long stationId;
    @SerializedName("user_id") @Expose @Nullable public Long userId;
    @SerializedName("product") @Expose @Nullable public Item product;
    @SerializedName("movement") @Expose @Nullable public Movement movement;
    @SerializedName("flight") @Expose @Nullable public Flight flight;
    @SerializedName("station") @Expose @Nullable public Station station;
    @SerializedName("user") @Expose @Nullable public User user;
    @SerializedName("comment") @Expose public String comment;
    @SerializedName("created") @Expose public String created;
    @SerializedName("modified") @Expose public String modified;
}