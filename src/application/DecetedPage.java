package application;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
//this page  to select who is first and change the names of players if we need
public class DecetedPage {
	private Stage stage ;

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public DecetedPage(Stage secStage, PlayPage playPage) {
		//build the style of page
		Label player1Label = new Label("Player 1");
		player1Label.setStyle("-fx-font-size: 20; -fx-font-weight: Bold; -fx-text-fill: 	#FFD700;");
		Label player2Label = new Label("Player 2");
		player2Label.setStyle("-fx-font-size: 20; -fx-font-weight: Bold; -fx-text-fill: 	#FFD700;");
		TextField name1 = new TextField();
		TextField name2 = new TextField();
		name1.setPromptText("name of Player1");
		name2.setPromptText("name of Player2");
		MyButton First1 = new MyButton("Is First");
		MyButton First2 = new MyButton("Is First");
		//style of the buttons
		First1.inHoverSmall();
		First2.inHoverSmall();
		First1.setStyle(" -fx-background-color: #FFD700;-fx-text-fill: black; -fx-font-size: 20px;");
		First2.setStyle(" -fx-background-color: #FFD700;-fx-text-fill: black; -fx-font-size: 20px;");
		MyButton Go = new MyButton("Go");
		Go.inHoverSmall();
		Go.setStyle(" -fx-background-color: #FFD700;-fx-text-fill: black; -fx-font-size: 20px;");
		//image for player1
		Image red = new Image("athlete.png");
		ImageView redImageView = new ImageView(red);
		redImageView.setFitWidth(130);
		redImageView.setFitHeight(150);
		//image for player2
		Image blue = new Image("soccer-player.png");
		ImageView blueImageView = new ImageView(blue);
		blueImageView.setFitWidth(130);
		blueImageView.setFitHeight(150);
		//to set the play stage be the owner of this stage (window for this stage)
		 stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.initOwner(secStage);
		SplitPane splitPane = new SplitPane();
		//change the disable buttons according who is first from players
		First1.setOnAction(e -> {
			playPage.getChoice21().setDisable(true);
			playPage.getChoice22().setDisable(true);
			playPage.getChoice1().setDisable(false);
			playPage.getChoice2().setDisable(false);
			showConfirmationDialog("Player 1 is First?", stage, playPage, name1.getText().toString(),
					name2.getText().toString());
		});
		First2.setOnAction(e -> {
			playPage.getChoice1().setDisable(true);
			playPage.getChoice2().setDisable(true);
			playPage.getChoice21().setDisable(false);
			playPage.getChoice22().setDisable(false);
			showConfirmationDialog("Player 2 is First?", stage, playPage, name1.getText().toString(),
					name2.getText().toString());
		});

		VBox leftPane = new VBox(10);
		leftPane.setStyle("-fx-background-color: #6c2427;");
		leftPane.getChildren().addAll(player1Label, redImageView, name1, First1);
		leftPane.setAlignment(Pos.CENTER);
		// Create the right pane with a light coral color
		VBox rightPane = new VBox(10);
		rightPane.setAlignment(Pos.CENTER);
		rightPane.setStyle("-fx-background-color: #254159;");
		rightPane.getChildren().addAll(player2Label, blueImageView, name2, First2);
		// Add the panes to the SplitPane
		splitPane.getItems().addAll(leftPane, rightPane);

		// Set the initial divider position
		splitPane.setDividerPositions(0.5);

		// Set up the scene and stage
		Scene scene = new Scene(splitPane, 800, 600);
		stage.setScene(scene);
		stage.setResizable(false);

		stage.setTitle("Before Start");
		stage.show();
	}
//confirm window after we start to do the changes or not
	public void showConfirmationDialog(String message, Stage stage, PlayPage playPage, String name1, String name2) {
		Stage confirmationStage = new Stage();
		//set this stage to be window for deceted stage
		confirmationStage.initModality(Modality.APPLICATION_MODAL);
		confirmationStage.initOwner(stage);
		confirmationStage.setTitle("Confirmation");

		Label confirmationLabel = new Label(message);
		confirmationLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

		Button yesButton = new Button("Yes");

		Button noButton = new Button("No");
//confirm the changes
		yesButton.setOnAction(e -> {
			playPage.setX((playPage.getChoice1().isDisable()));
			playPage.getPlayer1Label().setText(name1);
			playPage.getPlayer2Label().setText(name2);
			confirmationStage.close();
			stage.close();
		});
//reject the changes
		noButton.setOnAction(e -> {
			playPage.getChoice1().setDisable(false);
			playPage.getChoice2().setDisable(false);
			playPage.getChoice21().setDisable(true);
			playPage.getChoice22().setDisable(true);
			confirmationStage.close();
		});

		HBox buttonBox = new HBox(10, yesButton, noButton);
		buttonBox.setAlignment(Pos.CENTER);

		VBox layout = new VBox(20, confirmationLabel, buttonBox);
		layout.setAlignment(Pos.CENTER);
		layout.setStyle("-fx-padding: 20; -fx-background-color: #ffffff;");

		Scene confirmationScene = new Scene(layout, 300, 150);
		confirmationStage.setScene(confirmationScene);
		confirmationStage.show();
	}

}
