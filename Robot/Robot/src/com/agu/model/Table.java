package com.agu.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Table {
    private String table_name;    //t_d_user
    private String method_name;   //UserId
    private String bean_name;     //TDUser
    private String object_name;     //user
    private String seq_name;   //UserId
    private int length;
    private List<Field> field_list;

    private Map<String, String> map = new HashMap<String, String>();

    public Table() {

    }

    public String getTable_name() {
        return table_name;
    }

    public void setTable_name(String table_name) {
        this.table_name = table_name;
    }

    public String getMethod_name() {
        return method_name;
    }

    public void setMethod_name(String method_name) {
        this.method_name = method_name;
    }

    public String getBean_name() {
        return bean_name;
    }

    public void setBean_name(String bean_name) {
        this.bean_name = bean_name;
    }

    public String getObject_name() {
        return object_name;
    }

    public void setObject_name(String object_name) {
        this.object_name = object_name;
    }

    public String getSeq_name() {
        return seq_name;
    }

    public void setSeq_name(String seq_name) {
        this.seq_name = seq_name;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public List<Field> getField_list() {
        return field_list;
    }

    public void setField_list(List<Field> field_list) {
        this.field_list = field_list;
    }

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }


}
