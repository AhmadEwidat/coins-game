package application;

import javafx.animation.ScaleTransition;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.List;

//this stage are just to  choice the play way and show the array
public class SecPage {
	private List<javafx.scene.Node> originalContent;

	public SecPage(VBox vBox, String Coinsarray, Stage stage, int[] arrCoins) {
		originalContent = List.copyOf(vBox.getChildren());
		Label coinsArray = new Label(Coinsarray);
		coinsArray.setPrefWidth(1150);
		coinsArray.setPrefHeight(Region.USE_COMPUTED_SIZE); // Allow content to determine height
		coinsArray.setWrapText(true); // Enable text wrapping if needed
		coinsArray.setStyle("-fx-font-size: 40px;" + "-fx-font-weight: bold;" + "-fx-text-fill: black;"
				+ "-fx-background-color: linear-gradient(to right, #FFFACD, #FFD700);" + // Gradient background
				"-fx-padding: 15px;" + "-fx-border-color: #8B4513;" + "-fx-border-width: 3px;"
				+ "-fx-border-radius: 15px;" + "-fx-background-radius: 15px;");

//build buttons and the style of button

		Label inputLab = new Label("Please choose mode of play:");
		inputLab.setTextFill(Color.BLACK);
		inputLab.setPrefWidth(750);
		inputLab.setPrefHeight(200);
		inputLab.setStyle("-fx-font-size:50;-fx-font-weight:bold;-fx-color-fill:black;-fx-color-setBackground:brown");

		Image image1 = new Image("one-player-game-symbol.png");
		ImageView imageView1 = new ImageView(image1);
		imageView1.setFitWidth(50);
		imageView1.setFitHeight(50);

		Image image2 = new Image("two-players-game-interface-symbol.png");
		ImageView imageView2 = new ImageView(image2);
		imageView2.setFitWidth(50);
		imageView2.setFitHeight(50);

		Image image3 = new Image("logout.png");
		ImageView imageView3 = new ImageView(image3);
		imageView3.setFitWidth(50);
		imageView3.setFitHeight(50);

		MyButton multi = new MyButton("multi Player", imageView2);
		MyButton comp = new MyButton("VS Computer", imageView1);
		MyButton btnExit = new MyButton("Back", imageView3);

		multi.setPrefWidth(500);
		comp.setPrefWidth(500);
		btnExit.setPrefWidth(500);

		multi.inHover();
		comp.inHover();
		btnExit.inHover();
//open multi stage
		multi.setOnAction(ev -> {
			PlayPage playPage = new PlayPage(arrCoins, stage);
		});
		// open computer stage
		comp.setOnAction(ev -> {

			ComputerPage computerPage = new ComputerPage(arrCoins, stage);
		});
//the before page
		btnExit.setOnAction(ev -> {
			vBox.getChildren().setAll(originalContent);
		});
//build the vbox of buttons
		vBox.getChildren().clear();
		vBox.getChildren().addAll(coinsArray, inputLab, multi, comp, btnExit);
		vBox.setAlignment(Pos.TOP_CENTER);
	}
}
