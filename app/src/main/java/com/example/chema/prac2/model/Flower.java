package com.example.chema.prac2.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by CHEMA on 01/11/2015.
 */
public class Flower implements Parcelable {



        private String category;
        private Double price;
        private String instructions;
        private String photo;
        private String name;
        private Integer productId;


    public Flower(Integer productId, String name, String category,String instructions, double price, String photo ) {
        this.category = category;
        this.instructions = instructions;
        this.name = name;
        this.photo = photo;
        this.price = price;
        this.productId = productId;
    }


    public Flower(String name, String category,String instructions, double price, String photo ) {
        this.category = category;
        this.instructions = instructions;
        this.name = name;
        this.photo = photo;
        this.price = price;
    }

    public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getInstructions() {
            return instructions;
        }

        public void setInstructions(String instructions) {
            this.instructions = instructions;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public Double getPrice() {
            return price;
        }

        public void setPrice(Double price) {
            this.price = price;
        }

        public Integer getProductId() {
            return productId;
        }

        public void setProductId(Integer productId) {
            this.productId = productId;
        }

        @Override
        public String toString() {
            return "Example{" +
                    "category='" + category + '\'' +
                    ", price=" + price +
                    ", instructions='" + instructions + '\'' +
                    ", photo='" + photo + '\'' +
                    ", name='" + name + '\'' +
                    ", productId=" + productId +
                    '}';
        }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(productId);
        dest.writeString(name);
        dest.writeString(category);
        dest.writeString(instructions);
        dest.writeDouble(price);
        dest.writeString(photo);
    }

    public static final Parcelable.Creator<Flower> CREATOR
            = new Parcelable.Creator<Flower>() {
        public Flower createFromParcel(Parcel in) {
            return new Flower(in);
        }

        public Flower[] newArray(int size) {
            return new Flower[size];
        }
    };

    private Flower(Parcel origen) {
        this.productId       = origen.readInt();
        this.name     = origen.readString();
        this.category     = origen.readString();
        this.instructions  = origen.readString();
        this.price   = origen.readDouble();
        this.photo = origen.readString();
    }




}
