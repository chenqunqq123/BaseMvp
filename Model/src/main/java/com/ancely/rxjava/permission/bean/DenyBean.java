package com.ancely.rxjava.permission.bean;

import java.util.List;

public class DenyBean {

    private int requestCode;
    private List<String> denyList;

    public List<String> getDenyListName() {
        return denyListName;
    }

    public void setDenyListName(List<String> denyListName) {
        this.denyListName = denyListName;
    }

    private List<String> denyListName;

    public int getRequestCode() {
        return requestCode;
    }

    public void setRequestCode(int requestCode) {
        this.requestCode = requestCode;
    }

    public List<String> getDenyList() {
        return denyList;
    }

    public void setDenyList(List<String> denyList) {
        this.denyList = denyList;
    }
}