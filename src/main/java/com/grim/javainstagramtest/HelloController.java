package com.grim.javainstagramtest;

import com.github.instagram4j.instagram4j.IGClient;
import com.github.instagram4j.instagram4j.exceptions.IGLoginException;
import com.github.instagram4j.instagram4j.models.media.timeline.TimelineImageMedia;
import com.github.instagram4j.instagram4j.requests.feed.FeedUserRequest;
import com.github.instagram4j.instagram4j.requests.users.UsersUsernameInfoRequest;
import com.github.instagram4j.instagram4j.responses.feed.FeedUserResponse;
import com.github.instagram4j.instagram4j.responses.users.UserResponse;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.Arrays;

public class HelloController {

    @FXML
    private TextField username;

    @FXML
    private PasswordField pass;

    @FXML
    private Label status;

    String un,password;

    @FXML
    protected void login(ActionEvent e){
        un = username.getText();
        password = pass.getText();

        String[] usernameSyntax = un.split(" ");
        String[] passwordSyntax = password.split(" ");

        if (un.isEmpty()){
            System.out.println("Kullanıcı Adı Girilmedi!");
            errorAlert();
        }else if(usernameSyntax.length > 1){
            System.out.println("Invalid Username Syntax");
            errorAlert();
        }else if(password.isEmpty()){
            System.out.println("Parola Girilmedi");
            errorAlert();
        }else if(passwordSyntax.length > 1){
            System.out.println("Invalid password syntax");
            errorAlert();
        }else{
            try {
                IGClient client = IGClient.builder()
                        .username(un)
                        .password(password)
                        .login();
                status.setText("Başarılı!");
                status.setTextFill(Color.GREEN);
                System.out.println("Bağlantı başarılı!");

                try {
                    UserResponse usernameInfoRequest = new UsersUsernameInfoRequest(un).execute(client).join();

                    FeedUserResponse userFeed = new FeedUserRequest(usernameInfoRequest.getUser().getPk()).execute(client).join();

                    userFeed.getItems().forEach(item -> {
                        System.out.println(item.getCaption().getText());
                    });
                }catch (Exception exception){
                    exception.getMessage();
                }

            }catch (IGLoginException ex){
                System.out.println(ex.getLoginResponse().getMessage());
                loginAlert();
                status.setText("Başarısız!");
                status.setTextFill(Color.RED);
            }
        }
    }

    private void errorAlert(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Hatalı Giriş!");
        alert.setContentText("Lütfen parolanızı veya şifrenizi kontrol ediniz!");
        alert.showAndWait();
    }

    private void loginAlert(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Hata!");
        alert.setContentText("Girilen parola veya şifre hatalı! Lütfen kontrol ediniz.");
        alert.showAndWait();
    }

}