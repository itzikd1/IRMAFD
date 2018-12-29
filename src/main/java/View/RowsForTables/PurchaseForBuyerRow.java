package View.RowsForTables;

import Controller.Controller;
import Model.Purchase;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class PurchaseForBuyerRow {

    public String PurchaseID;
    public String VacationID;
    public String BuyerUserName;
    public String SellerUserName;
    public String Price;
    public String destination;
    public Button cancel;
    public Button Details;

    //<editor-fold desc="Get and Set">
    public Button getCancel() {
        return cancel;
    }

    public void setCancel(Button cancel) {
        this.cancel = cancel;
    }

    public Button getDetails() {
        return Details;
    }

    public void setDetails(Button details) {
        Details = details;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
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

    public String getSellerUserName() {
        return SellerUserName;
    }

    public void setSellerUserName(String sellerUserName) {
        SellerUserName = sellerUserName;
    }
    //</editor-fold>

    public PurchaseForBuyerRow(Purchase p, String Destination, Button Cancel, Button details) {
        cancel = Cancel;
        PurchaseID = p.getPurchaseID();
        VacationID = p.getVacationID();
        BuyerUserName = p.getBuyerUserName();
        Price = p.getPrice();
        Details = details;
        destination = Destination;
        SellerUserName = p.getSellerUserName();
        cancel.setText("Cancel");
        Details.setText("Details");

        cancel.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Not Implemented");
            alert.setHeaderText("We decided to not implement this on this part of the assignment\n you can buy vacations / trade them, so enjoy");
            alert.showAndWait();
        });

        Details.setOnAction(event -> {
            Stage s = (Stage) details.getScene().getWindow();
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
