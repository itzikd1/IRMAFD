package View.Account;

import Controller.Controller;
import Model.Excpetions.V4UException;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.*;

public class CreateUserView implements Initializable {
    public TextField tf_city;
    public TextField tf_lastName;
    public TextField tf_firstName;
    public PasswordField tf_password;
    public DatePicker bd;
    public TextField tf_username;
    public Button send_button;
    public Button BackButton;

    private Controller controller = Controller.getInstance();

    public void send_info(ActionEvent actionEvent) {
        String user, city, ln, fn, password;
        user = tf_username.getText();
        city = tf_city.getText();
        ln = tf_lastName.getText();
        fn = tf_firstName.getText();
        password = tf_password.getText();

//            java.sql.Date today = new java.sql.Date(Calendar.getInstance().getTime().getTime());
//            java.util.Date todayJavaDate = new Date(today.getTime());
//            LocalDate localTodayDate = todayJavaDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();


        Object[] user_details = new Object[]{user, password, bd, fn, ln, city};
        try {
            boolean flag = controller.insertNewUser("Users", user_details);
            Stage s = (Stage) BackButton.getScene().getWindow();

            //ChangeScene();

            if (flag) {
                updateLoginOnGui();
                s.close();
            }

            else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setHeaderText("your username is already in use, please choose another");
                alert.showAndWait();
                return;
            }
        } catch (V4UException e) {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText(e.getMessage());
            alert.showAndWait();
        } catch (Exception e) {

            System.out.println(e.getMessage());

        }
    }


    private void updateLoginOnGui() {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Security Alert");
            alert.setHeaderText("You have been signed up!\nPlease log in using your new account information");
            alert.showAndWait();
        //     //   FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("MainPage.fxml"));
//
//        fxmlLoader.getController();
//        MainPageView mainPageView = fxmlLoader.getController();
//        mainPageView.disable_loginInfo();

    }



    public void go_main(ActionEvent actionEvent) {
        Stage s = (Stage) BackButton.getScene().getWindow();
        s.close();
//        ChangeScene();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
