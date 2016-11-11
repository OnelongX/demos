package com.agu;

import com.agu.model.Config;
import com.agu.model.Table;
import com.agu.service.DbService;
import com.agu.utils.FreemarkerUtil;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class Controller implements Initializable{

    @FXML
    private  TextField server ;
    @FXML
    private  TextField port ;
    @FXML
    private  TextField db ;
    @FXML
    private  TextField table ;
    @FXML
    private  TextField userName ;
    @FXML
    private  TextField password;
    @FXML
    private  TextField basePackage;
    @FXML
    private  TextField plate;
    @FXML
    private  TextField modelCn;
    @FXML
    private  TextField modelName;
    @FXML
    private  TextField functionCn;
    @FXML
    private  TextField functionName;


    @FXML protected void handleOkButtonAction(ActionEvent event) {

        DbService service = new DbService();

        Table table = service.getTable(this.getConfig());

        FreemarkerUtil futil = new FreemarkerUtil();

        Map<String,Object> map = new HashMap<String,Object>();

        map.put("table",table);



        futil.fprint("java/module.ftl",map,"ok.java");



    }

    @FXML protected void handleExitButtonAction(ActionEvent event) {

        System.exit(0);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        server.setText("localhost");
        port.setText("3306");
        db.setText("mytest");
        table.setText("t_user");
        userName.setText("root");
        password.setText("Hl198711");
        basePackage.setText("com.sxit");
        plate.setText("mgt");
        modelCn.setText("企业管理");
        modelName.setText("entmgt");
        functionCn.setText("用户");
        functionName.setText("entUser");
    }


   public Config getConfig(){

       Config config = new Config();

       config.setServer(server.getText());
       config.setPort(port.getText());
       config.setDb(db.getText());
       config.setUserName(userName.getText());
       config.setPassword(password.getText());
       config.setTable(table.getText());
       config.setBasePackage(basePackage.getText());
       config.setPlate(plate.getText());
       config.setFunctionCn(functionCn.getText());
       config.setFunctionName(functionName.getText());
       config.setModelCn(modelCn.getText());
       config.setModelName(modelName.getText());

       return config;

   }
}
