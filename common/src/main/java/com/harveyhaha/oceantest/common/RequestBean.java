package com.harveyhaha.oceantest.common;

import com.alibaba.fastjson.JSONObject;

public class RequestBean {
    public String action;
    public JSONObject data;

    public RequestBean(String action, JSONObject data) {
        this.action = action;
        this.data = data;
    }
}
