package sample;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.text.Text;
import monologfx.MonologFXUtil;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private TextField server;
    @FXML
    private TextArea log;
    @FXML
    private TextField port;
    @FXML
    private TextField db;
    @FXML
    private TextField userName;
    @FXML
    private TextField table;
    @FXML
    private TextField basePackage;
    @FXML
    private TextField password;
    @FXML
    private TextField plate;
    @FXML
    private TextField modelCn;
    @FXML
    private TextField modelName;
    @FXML
    private TextField functionCn;
    @FXML
    private TextField functionName;

    @FXML
    protected void handleExitButtonAction(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    protected void handleSubmitButtonAction(ActionEvent event) {
        log.clear();
        ConfigData data = new ConfigData();

        String serverStr = server.getText();

        if(isEmpty(serverStr))
        {
            data.server = "localhost";
            log.appendText("[W]使用默认数据库地址：localhost \n");
        }else {
            data.server = serverStr;
        }

        String portStr = port.getText();
        if(isEmpty(portStr))
        {
            data.port = "3306";
            log.appendText("[W]使用默认数据库端口：3306 \n");
        }else {
            data.port = portStr;
        }

        String dbStr = db.getText();
        if(isEmpty(dbStr))
        {
            MonologFXUtil.alert("数据库名不能为空");
            return;
        }
        data.dbName = dbStr;

        String tableNameStr = table.getText();
        if(isEmpty(tableNameStr))
        {
            MonologFXUtil.alert("数据库表不能为空");
            return;
        }
        data.tableName = tableNameStr;

        String userNameStr = userName.getText();
        if(isEmpty(userNameStr))
        {
            MonologFXUtil.alert("用户名不能为空");
            return;
        }
        data.userName = userNameStr;

        String passwordStr = password.getText();
        data.password = passwordStr;
        if(isEmpty(passwordStr))
        {
            log.appendText("[W]密码为空 \n");
        }

        String basePackageStr =  basePackage.getText();
        if(isEmpty(basePackageStr))
        {
            MonologFXUtil.alert("包名不能为空");
            return;
        }

        data.basePackage = basePackageStr;

        data.plate = plate.getText();
        data.modelCn = modelCn.getText();
        data.modelName = modelName.getText();
        data.functionCn = functionCn.getText();
        data.functionName = functionName.getText();

        new Thread(task).start();

        //dbTest(data);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        server.setText("localhost");
        port.setText("3306");
        log.setDisable(false);


        //根据实际需要处理消息值
        task.messageProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                System.out.println(newValue);
            }
        });

    }


    private boolean isEmpty(String str)
    {
        if(str!=null)
        {
            return  str.isEmpty();
        }else {
            return true;
        }
    }

    private void dbTest(ConfigData data)
    {
        String url = String.format("jdbc:mysql://%s:%s/%s?useSSL=false",data.server,data.port,data.dbName);
        Connection con = null; //定义一个MYSQL链接对象
        try {
           // Class.forName("com.mysql.cj.jdbc.Driver").newInstance(); //新MYSQL驱动
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection(url, data.userName, data.password); //链接本地MYSQL
            log.appendText("[W]数据库链接成功 \n");
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            log.appendText("[E]MYSQL ERROR:" + e.getMessage());
        }finally {
            if(con!=null)
                try {con.close();} catch (Exception e) {e.printStackTrace();}
        }
    }





    private Task<Integer> task = new Task<Integer>() {
        @Override
        protected Integer call() throws Exception {
            int iterations;
            for (iterations = 0; iterations < 100000; iterations++) {
                if (isCancelled()) {
                    break;
                }

                final int ii = iterations;
                //Update JavaFX UI with runLater() in UI thread
                Platform.runLater(new Runnable(){
                    @Override
                    public void run() {
                        log.appendText(ii+"\n");
                    }
                });

                Thread.sleep(100);
            }
            return iterations;
        }

        @Override
        protected void running() {
            updateMessage("running...");
        }
        @Override
        protected void succeeded() {
            updateMessage("Done!");

            int value = getValue();
            System.out.println(value);

        }
        @Override
        protected void cancelled() {
            updateMessage("Cancelled!");
        }
        @Override
        protected void failed() {
            updateMessage("Failed!");
        }
    };


}
