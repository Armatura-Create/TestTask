package com.example.alex.testtask.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

public class SingUpModel {

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("phone")
    @Expose
    private String phone;

    @SerializedName("password")
    @Expose
    private String password;

    @SerializedName("site")
    @Expose
    private String site;

    @SerializedName("address")
    @Expose
    private String address;

    @SerializedName("company")
    @Expose
    private String company;

    @SerializedName("token")
    @Expose
    private String pushToken;

    public String getToken() {
        return pushToken;
    }

    public void setToken(String token) {
        token = pushToken;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String toJSONString() throws JSONException {
        JSONObject json = new JSONObject();
        json.put("email", email);
        json.put("password", password);
        json.put("phone", phone);
        json.put("site", site);
        json.put("address", address);
        json.put("company", company);
        json.put("pushToken", pushToken);
        return json.toString().replaceAll("\\\\", "");
    }

}
