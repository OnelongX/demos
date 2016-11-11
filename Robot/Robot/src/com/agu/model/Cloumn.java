package com.agu.model;

/**
 * Created by agu on 16/11/2.
 */
public class Cloumn {

    private String column_name;
    private String column_type;
    private Integer data_length;
    private Integer data_precision;
    private Integer data_scale;
    private String column_comment;
    private String column_key;
    private String nullable;



    public Cloumn() {


    }
    public String getColumn_name() {
        return column_name;
    }
    public void setColumn_name(String column_name) {
        this.column_name = column_name;
    }
    public String getColumn_type() {
        return column_type;
    }
    public void setColumn_type(String column_type) {
        this.column_type = column_type;
    }
    public Integer getData_length() {
        return data_length;
    }
    public void setData_length(Integer data_length) {
        this.data_length = data_length;
    }
    public Integer getData_precision() {
        return data_precision;
    }
    public void setData_precision(Integer data_precision) {
        this.data_precision = data_precision;
    }
    public Integer getData_scale() {
        return data_scale;
    }
    public void setData_scale(Integer data_scale) {
        this.data_scale = data_scale;
    }
    public String getColumn_comment() {
        return column_comment;
    }
    public void setColumn_comment(String column_comment) {
        this.column_comment = column_comment;
    }
    public String getColumn_key() {
        return column_key;
    }
    public void setColumn_key(String column_key) {
        this.column_key = column_key;
    }
    public String getNullable() {
        return nullable;
    }
    public void setNullable(String nullable) {
        this.nullable = nullable;
    }

}
