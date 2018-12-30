package View.RowsForTables;

import Controller.Controller;
import Model.Excpetions.V4UException;
import Model.Vacation;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

public class VacationsForSearchRow {
    public Vacation vacation;
    public Button details;
    public Button buy;
    public String from;
    public String destination;
    public Button trade;
    public String SellerUserName;
    public LocalDate departureDate;
    public Button getTrade() {
        return trade;
    }

    //<editor-fold desc="Get and Set">
    public void setTrade(Button trade) {
        this.trade = trade;
    }

    public Vacation getVacation() {
        return vacation;
    }

    public void setVacation(Vacation vacation) {
        this.vacation = vacation;
    }

    public Button getDetails() {
        return details;
    }

    public void setDetails(Button details) {
        this.details = details;
    }

    public Button getBuy() {
        return buy;
    }

    public void setBuy(Button buy) {
        this.buy = buy;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public LocalDate returnDate;

    public String getSellerUserName() {
        return SellerUserName;
    }

    public void setSellerUserName(String sellerUserName) {
        SellerUserName = sellerUserName;
    }
    //</editor-fold>

    public VacationsForSearchRow(Vacation vacation, Button details, Button buy, Button trade) {
        this.vacation = vacation;
        this.departureDate = LocalDate.parse(vacation.getDepartureDate());
        this.returnDate = LocalDate.parse(vacation.getReturnDate());
        this.from = vacation.getFrom();
        this.destination = vacation.getDestination();
        this.details = details;
        this.buy = buy;
        this.SellerUserName = vacation.getUserName();
        this.trade = trade;

        trade.setText("Trade");
        buy.setText("Buy");
        details.setText("Details");

        Controller controller = Controller.getInstance();

        trade.setOnAction(event -> {
            boolean connected_user = true;
            if (controller.get_connected_user_id() == null)
                connected_user = false;
            if (connected_user) {
                boolean flag = false;
                if (controller.get_connected_user_id().equals(this.SellerUserName)) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Your request to trade has been cancelled");
                    alert.setHeaderText("You can't trade vacation with yourself");
                    alert.showAndWait();
                    return;
                }

                    try {
                        if (controller.req_exists(vacation.getVacationID())) {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Why asking Trading again?!");
                            alert.setHeaderText("You already request to trade or to buy this vacation. \nPlease keep calm and check your trade requests page soon");
                            alert.showAndWait();
                            return;
                        }
                        else {
                            // Controller.setVacationIDForTrade(vacation.getVacationID());
                            controller.setTradeId(vacation.getVacationID());
                            // Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("VacationDetailsWindow.fxml"));
                            controller.setCurrent_trade_seller_user_name(vacation.getUserName());
                            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("MyVacationForTrade.fxml"));
                            Stage stage = new Stage();
                            stage.initModality(Modality.APPLICATION_MODAL);
                            stage.setResizable(true);

                            stage.setTitle("Choose one vacation");
                            Scene scene = new Scene(root);
                            stage.setScene(scene);
                            stage.show();
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    finally{
                        //Controller.setVacationIDForTrade(null);
                    }

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Log in");
                alert.setHeaderText("Only signed users are allowing to request to buy vacations\nPlease close the window, log in and try again");
                alert.showAndWait();
            }
        });

        details.setOnAction(event -> {
            Stage s = (Stage) details.getScene().getWindow();
            try {
                Controller.vacationID = vacation.getVacationID();
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

        buy.setOnAction(event -> {
            boolean connected_user = true;
            if (controller.get_connected_user_id() == null)
                connected_user = false;
            if (connected_user) {
                boolean flag = false;
                if (controller.get_connected_user_id().equals(this.SellerUserName)) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Your request to buy has been cancelled");
                    alert.setHeaderText("You can't buy vacation from yourself");
                    alert.showAndWait();
                    return;
                }
                try {
                    if (controller.req_exists(vacation.getVacationID())) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Why asking Buyinh again?!");
                        alert.setHeaderText("You already request to trade or to buy this vacation. \nPlease keep calm and check your trade requests page soon");
                        alert.showAndWait();
                        return;
                    }
                    flag = controller.insertBuyingRequest(vacation.getVacationID(), vacation.getUserName());
                } catch (V4UException e) {
                    System.out.println("error in insert");
                }
                if (flag) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("BuyingRequest Sent");
                    alert.setHeaderText("Your request to buy has been sent to Buyer. \nPlease check your requests page soon");
                    alert.showAndWait();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Why Buying again?!");
                    alert.setHeaderText("You already requested to buy this vacation. \nPlease keep calm and check your requests page soon");
                    alert.showAndWait();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Log in");
                alert.setHeaderText("Only signed users are allowing to request to buy vacations\nPlease close the window, log in and try again");
                alert.showAndWait();
            }
        });
    }
}