package com.wyzdev.sunrisetv.models;

/**
 * Created by Noel Emmanuel Roodly on 6/19/2019.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;


public class Sale implements Serializable {

    @SerializedName("id") public Long id;
    @SerializedName("sale_number") @Expose public String saleNumber;
    @SerializedName("created") @Expose public String created;
    @SerializedName("modified") @Expose public String modified;
    @SerializedName("pointofsale_id") @Expose public Integer posId;
    @SerializedName("user_id") @Expose public Integer userId;
    @SerializedName("customer_id") @Expose public Integer customerId;
    @SerializedName("receiver_id") @Expose public Integer receiverId;
//    @SerializedName("truck_id") @Expose public Integer truckId;
    @SerializedName("taxe") @Expose public Double taxe;
    @SerializedName("monnaie") @Expose public Double monnaie;
    @SerializedName("discount") @Expose public Double discount;
    @SerializedName("discount_type") @Expose public String discount_type;
//    @SerializedName("charged") @Expose public Integer charged;
//    @SerializedName("sortie") @Expose public Integer sortie;
    @SerializedName("status") @Expose public int status;
    @SerializedName("type") @Expose public int type;
    @SerializedName("total") @Expose public Double total;
    @SerializedName("subtotal") @Expose public Double subtotal;
    @SerializedName("transport_fee") @Expose public Double transportFee;
    @SerializedName("change") @Expose public Double change;
    @SerializedName("change_rate") @Expose public Double changeRate;
//    @SerializedName("image_path") @Expose public String imagePath;
    @SerializedName("comment") @Expose public String comment;
    @SerializedName("tag") @Expose public String tag;
    @SerializedName("station_id") @Expose public Integer stationId;
    @SerializedName("change_rate_id") @Expose public Integer changeRateId;
    @SerializedName("destination_station_id") @Expose public Integer destinationStationId;
//    @SerializedName("prices") @Expose public List<CustomerPrice> prices;
    @SerializedName("items") @Expose public List<Item> items;
    @SerializedName("receiver") @Expose public Receiver receiver;
    @SerializedName("customer") @Expose public Customer customer;
    @SerializedName("destination") @Expose public Station destination;
    @SerializedName("station") @Expose public Station station;
    @SerializedName("truck") @Expose public Truck truck;

//    @SerializedName("customer") @Expose @Convert(converter = Converter.CustomerConverter.class, dbType = Long.class) public Customer customer;

    public boolean is_sync;
    public String sync_change;

}