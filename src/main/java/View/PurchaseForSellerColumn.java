package View;

import Controller.Controller;
import Model.Purchase;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class PurchaseForSellerColumn {

    public String PurchaseID;
    public String VacationID;
    public String BuyerUserName;
    public String Price;
    public Button Confirmation;
    public Button Details;

    public Button getDetails() {
        return Details;
    }

    public void setDetails(Button details) {
        Details = details;
    }

    public String getPurchaseID() {
        return PurchaseID;
    }

    public void setPurchaseID(String purchaseID) {
        PurchaseID = purchaseID;
    }

    public String getVacationID() {
        return VacationID;
    }

    public void setVacationID(String vacationID) {
        VacationID = vacationID;
    }

    public String getBuyerUserName() {
        return BuyerUserName;
    }

    public void setBuyerUserName(String buyerUserName) {
        BuyerUserName = buyerUserName;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public Button getConfirmation() {
        return Confirmation;
    }

    public void setConfirmation(Button confirmation) {
        Confirmation = confirmation;
    }

    public PurchaseForSellerColumn(Purchase p, Button c, Button details) {
        Confirmation = c;
        PurchaseID = p.getPurchaseID();
        VacationID = p.getVacationID();
        BuyerUserName = p.getBuyerUserName();
        Price = p.getPrice();

        Confirmation.setText("Details");

        Confirmation.setOnAction(event -> {
            System.out.println("todo");
        });

        Details.setOnAction(event -> {

            Stage s = (Stage) details.getScene().getWindow();
//        s.close();
            try {
                Controller.vacationID = VacationID;
                Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("VacationDetailsWindow.fxml"));
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setResizable(true);
                stage.setTitle("Details Vacation");
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }
}