package com.agu.model;

import java.util.HashMap;
import java.util.Map;

public class Field {
    private String coloum_name;   //user_id
    private String field_name;    //userId
    private String method_name;   //UserId
    private String method_name2;   //User_id
    private String type_name;     //Int
    private String java_type;     //int
    private String object_java_type;     //Integer
    private Boolean is_pkey;      //主键
    private String comment;       //注释
    private String commentLong;   //长注释

    private String coloum_type;
    private String jdbc_type;
    private Integer length;
    private Integer precision;
    private Integer scale;
    private String constraint;
    private String nullable;

    private String inputType = "textfield";
    private String mapName;
    private String mapString;

    private boolean nolist = false;
    private boolean noinput = false;
    private boolean noview = false;


    private Map map;

    public Field() {

    }

    public Field(Cloumn cloumn) {

        this.coloum_name = cloumn.getColumn_name().toLowerCase();
        this.coloum_type = cloumn.getColumn_type();
        this.length = cloumn.getData_length();
        this.precision = cloumn.getData_precision();
        this.scale = cloumn.getData_scale();
        this.commentLong = cloumn.getColumn_comment();

        this.constraint = cloumn.getColumn_key();
        this.nullable = cloumn.getNullable();

        this.field_name = FiledUtil.toFieldName(this.coloum_name);
        this.method_name = FiledUtil.toMethodName(this.coloum_name);
        this.method_name2 = FiledUtil.toMethodName2(this.coloum_name);

        if (this.commentLong != null) {

            int i = this.commentLong.indexOf("|");

            if (i <= 0) {
                this.comment = this.commentLong;
            } else {
                this.comment = this.commentLong.substring(0, i);
                //nolist noinput noview
                if (this.commentLong.contains("nolist")) {
                    this.nolist = true;
                    this.commentLong = this.commentLong.replaceAll("nolist", "").trim();
                }
                if (this.commentLong.contains("noinput")) {
                    this.noinput = true;
                    this.commentLong = this.commentLong.replaceAll("noinput", "").trim();
                }
                if (this.commentLong.contains("noview")) {
                    this.noview = true;
                    this.commentLong = this.commentLong.replaceAll("noview", "").trim();
                }

                if (i + 1 < this.commentLong.length()) {
                    String tempString = this.commentLong.substring(i + 1).trim();

                    int j = tempString.indexOf("@");
                    if (j > 0 && j < tempString.length()) {
                        this.inputType = tempString.substring(0, j);

                        this.map = new HashMap<String, Map>();
                        if (j + 1 < tempString.length()) {
                            String mapString = tempString.substring(j + 1);
                            int k = mapString.indexOf("#");
                            if (k > 0 && k + 1 < mapString.length()) {
                                this.mapName = mapString.substring(0, k);
                                this.mapString = mapString.substring(k + 1);

                               Map map1 = FiledUtil.strToMap(this.mapString);
                                if(map1!=null) {
                                    this.map.put(this.mapName, map1);
                                }
                                System.out.println(this.mapName);
                                System.out.println(this.mapString);
                            }
                        }
                    }
                }
            }
        }

        String temptype = "";
        String java_type = "";


        if (coloum_type.contains("varchar")) {
            temptype = "String";
            java_type = "String";
            object_java_type = "String";
        } else if (coloum_type.contains("text")) {
            temptype = "String";
            java_type = "String";
            object_java_type = "String";
        } else if (coloum_type.contains("float")) {
            temptype = "Float";
            java_type = "float";
            object_java_type = "Float";
        } else if (coloum_type.contains("double")) {
            temptype = "Double";
            java_type = "double";
            object_java_type = "Double";
        } else if (coloum_type.contains("int")) {
            int per = this.precision;
            int sca = this.scale;

            if (sca == 0) {
                if (per >= 11) {
                    temptype = "Long";
                    java_type = "Long";
                    object_java_type = "Long";
                } else {
                    temptype = "Int";
                    java_type = "Integer";
                    object_java_type = "Integer";
                }
            } else {
                if (per >= 11) {
                    temptype = "Double";
                    java_type = "double";
                    object_java_type = "Double";
                } else {
                    temptype = "Float";
                    java_type = "float";
                    object_java_type = "Float";
                }
            }
        } else if (coloum_type.contains("datetime")) {
            temptype = "Date";
            java_type = "Date";
            object_java_type = "Date";
        } else if (coloum_type.contains("timestamp")) {
            temptype = "Date";
            java_type = "Date";
            object_java_type = "Date";
        } else {
            temptype = "String";
            java_type = "String";
            object_java_type = "String";
        }

        this.type_name = temptype;
        this.java_type = java_type;

        if (this.constraint.equals("PRI")) {
            this.is_pkey = true;
        } else {
            this.is_pkey = false;
        }

        int index = coloum_type.indexOf("(");
        if (index > 0) {
            this.jdbc_type = coloum_type.substring(0, index).toUpperCase();
        } else {
            this.jdbc_type = coloum_type.toUpperCase();
        }

        if ("INT".equals(this.jdbc_type)) {
            this.jdbc_type = "INTEGER";
        }


    }

    public String getColoum_name() {
        return coloum_name;
    }

    public void setColoum_name(String coloum_name) {
        this.coloum_name = coloum_name;
    }

    public String getField_name() {
        return field_name;
    }

    public void setField_name(String field_name) {
        this.field_name = field_name;
    }

    public String getMethod_name() {
        return method_name;
    }

    public void setMethod_name(String method_name) {
        this.method_name = method_name;
    }

    public String getMethod_name2() {
        return method_name2;
    }

    public void setMethod_name2(String method_name2) {
        this.method_name2 = method_name2;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    public String getJava_type() {
        return java_type;
    }

    public void setJava_type(String java_type) {
        this.java_type = java_type;
    }

    public String getObject_java_type() {
        return object_java_type;
    }

    public void setObject_java_type(String object_java_type) {
        this.object_java_type = object_java_type;
    }

    public Boolean getIs_pkey() {
        return is_pkey;
    }

    public void setIs_pkey(Boolean is_pkey) {
        this.is_pkey = is_pkey;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCommentLong() {
        return commentLong;
    }

    public void setCommentLong(String commentLong) {
        this.commentLong = commentLong;
    }

    public String getColoum_type() {
        return coloum_type;
    }

    public void setColoum_type(String coloum_type) {
        this.coloum_type = coloum_type;
    }

    public String getJdbc_type() {
        return jdbc_type;
    }

    public void setJdbc_type(String jdbc_type) {
        this.jdbc_type = jdbc_type;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getPrecision() {
        return precision;
    }

    public void setPrecision(Integer precision) {
        this.precision = precision;
    }

    public Integer getScale() {
        return scale;
    }

    public void setScale(Integer scale) {
        this.scale = scale;
    }

    public String getConstraint() {
        return constraint;
    }

    public void setConstraint(String constraint) {
        this.constraint = constraint;
    }

    public String getNullable() {
        return nullable;
    }

    public void setNullable(String nullable) {
        this.nullable = nullable;
    }

    public String getInputType() {
        return inputType;
    }

    public void setInputType(String inputType) {
        this.inputType = inputType;
    }

    public String getMapName() {
        return mapName;
    }

    public void setMapName(String mapName) {
        this.mapName = mapName;
    }

    public String getMapString() {
        return mapString;
    }

    public void setMapString(String mapString) {
        this.mapString = mapString;
    }

    public boolean isNolist() {
        return nolist;
    }

    public void setNolist(boolean nolist) {
        this.nolist = nolist;
    }

    public boolean isNoinput() {
        return noinput;
    }

    public void setNoinput(boolean noinput) {
        this.noinput = noinput;
    }

    public boolean isNoview() {
        return noview;
    }

    public void setNoview(boolean noview) {
        this.noview = noview;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }
}
