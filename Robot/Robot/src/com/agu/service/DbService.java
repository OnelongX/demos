package com.agu.service;

import com.agu.model.*;
import com.agu.utils.DBUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by agu on 16/11/2.
 */
public class DbService {



    public Table getTable(Config config){



        DBUtils.URL="jdbc:mysql://"+config.getServer()+":"+config.getPort()+"/information_schema?useSSL=false";
        DBUtils.USERNAME=config.getUserName();
        DBUtils.PASSWORD=config.getPassword();
        DBUtils.DRIVER="com.mysql.jdbc.Driver";


        QueryRunner runner = new QueryRunner();

        String sql = "select column_name,COLUMN_TYPE,character_octet_length data_length,NUMERIC_PRECISION data_precision,NUMERIC_SCALE  data_SCALE,COLUMN_COMMENT ,COLUMN_KEY,IS_NULLABLE  nullable\n" +
                "                            from information_schema.columns  \n" +
                "                            Where  table_name=? and TABLE_SCHEMA= ?\n" +
                "                            order by ORDINAL_POSITION";


        try {
            String [] p = new String[2];

            p[0]=config.getTable();
            p[1]=config.getDb();

            Table table = new Table();
            table.setBean_name(FiledUtil.toMethodName(config.getTable().toLowerCase()));
            table.setMethod_name(FiledUtil.toMethodName(config.getTable().toLowerCase()));
            table.setObject_name(FiledUtil.toFieldName(config.getTable().toLowerCase()));
            table.setTable_name(config.getTable().toLowerCase());

            Map table_map = new HashMap<String, String>();

            List<Field> f_list = new ArrayList<Field>();
            List<Cloumn> list = runner.query(DBUtils.getConnection(), sql,new BeanListHandler<Cloumn>(Cloumn.class),p);

            for(Cloumn c:list)
            {
                //System.out.println(c.getColumn_name());
                Field field =  new Field(c);
                if(field.getMap()!=null)
                {
                    table_map.putAll(field.getMap());
                }

               f_list.add(field);
            }

            table.setField_list(f_list);
            table.setMap(table_map);




            return table;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

}
