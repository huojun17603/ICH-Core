package com.ich.international.pojo;

import java.util.HashMap;
import java.util.Map;

public class ILocaleMessage {

    public static Map<String,String> DATAMAP = new HashMap<>();

    private String id;

    private String ikey;

    private String ival;

    private String locale;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIkey() {
        return ikey;
    }

    public void setIkey(String ikey) {
        this.ikey = ikey;
    }

    public String getIval() {
        return ival;
    }

    public void setIval(String ival) {
        this.ival = ival;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }
}
