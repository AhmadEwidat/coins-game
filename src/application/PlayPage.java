
package application;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import java.time.Duration;
import java.util.Stack;
import javafx.animation.PauseTransition;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
//This page is for simulating the game between two people,and build the design for the play screen
public class PlayPage {
	private Label select1, select2 ;
	private Label player1Label;
	private Label player2Label;
	private MyButton choice1, choice2, choice21, choice22;
	private boolean x = false;
	String ArrayRed = "";
	String ArrayBlue = "";
	private Label set1, set2, coinsArray;
	private VBox vBoxControls;
	private DecetedPage decetedPage;
	private int i = 0;
	private int j;
	private int counter;
	private MyButton reset;
	private String cArray;
	private Stage stage;

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}

	public Label getSelect1() {
		return select1;
	}

	public void setSelect1(Label select1) {
		this.select1 = select1;
	}

	public Label getSelect2() {
		return select2;
	}

	public void setSelect2(Label select2) {
		this.select2 = select2;
	}

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public MyButton getReset() {
		return reset;
	}

	public void setReset(MyButton reset) {
		this.reset = reset;
	}

	public DecetedPage getDecetedPage() {
		return decetedPage;
	}

	public void setDecetedPage(DecetedPage decetedPage) {
		this.decetedPage = decetedPage;
	}

	public VBox getvBoxControls() {
		return vBoxControls;
	}

	public void setvBoxControls(VBox vBoxControls) {
		this.vBoxControls = vBoxControls;
	}

	public Label getSet1() {
		return set1;
	}

	public void setSet1(Label set1) {
		this.set1 = set1;
	}

	public Label getSet2() {
		return set2;
	}

	public void setSet2(Label set2) {
		this.set2 = set2;
	}

	public Label getCoinsArray() {
		return coinsArray;
	}

	public void setCoinsArray(Label coinsArray) {
		this.coinsArray = coinsArray;
	}

	public boolean isX() {
		return x;
	}

	public void setX(boolean x) {
		this.x = x;
	}

	public MyButton getChoice2() {
		return choice2;
	}

	public String getcArray() {
		return cArray;
	}

	public void setcArray(String cArray) {
		this.cArray = cArray;
	}

	public void setChoice2(MyButton choice2) {
		this.choice2 = choice2;
	}

	public MyButton getChoice21() {
		return choice21;
	}

	public void setChoice21(MyButton choice21) {
		this.choice21 = choice21;
	}

	public MyButton getChoice22() {
		return choice22;
	}

	public void setChoice22(MyButton choice22) {
		this.choice22 = choice22;
	}

	public MyButton getChoice1() {
		return choice1;
	}

	public void setChoice1(MyButton choice1) {
		this.choice1 = choice1;
	}

	public Label getPlayer1Label() {
		return player1Label;
	}

	public void setPlayer1Label(Label player1Label) {
		this.player1Label = player1Label;
	}

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

	public int getJ() {
		return j;
	}

	public void setJ(int j) {
		this.j = j;
	}

	public Label getPlayer2Label() {
		return player2Label;
	}

	public void setPlayer2Label(Label player2Label) {
		this.player2Label = player2Label;
	}

	public PlayPage(int[] arrayOfCoins, Stage mystage) {

	    // Create a new modal stage
	    stage = new Stage();
	    stage.initModality(Modality.APPLICATION_MODAL); // Set modality to block interaction with the parent stage
	    stage.initOwner(mystage); // Set the parent stage

	    // Load and configure player images
	    Image red = new Image("athlete.png");
	    ImageView redImageView = new ImageView(red);
	    redImageView.setFitWidth(130); // Set image width
	    redImageView.setFitHeight(150); // Set image height

	    Image CoinRed = new Image("CoinRed.gif");
	    ImageView CoinRedv = new ImageView(CoinRed);
	    CoinRedv.setFitWidth(100);
	    CoinRedv.setFitHeight(70);

	    Image blue = new Image("soccer-player.png");
	    ImageView blueImageView = new ImageView(blue);
	    blueImageView.setFitWidth(130);
	    blueImageView.setFitHeight(150);

	    Image vsImage = new Image("VS.jpg");
	    ImageView vsImageView = new ImageView(vsImage);
	    vsImageView.setFitWidth(1050);
	    vsImageView.setFitHeight(770);

	    // Generate string representation of the array and set label text
	    cArray = getString(arrayOfCoins, 0, arrayOfCoins.length - 1);
	    coinsArray = new Label(cArray);
	    coinsArray.setPrefHeight(100); // Set label height
	    coinsArray.setAlignment(Pos.CENTER); // Align text to center

	    // Style the coinsArray label
	    coinsArray.setStyle("-fx-font-size: 40px;" +
	            "-fx-font-weight: bold;" +
	            "-fx-text-fill: #FFD700;" + // Gold text color
	            "-fx-background-color: linear-gradient(to right, #333333, #444444);" + // Dark gradient background
	            "-fx-border-color: #FFD700;" +
	            "-fx-border-width: 3px;" +
	            "-fx-border-radius: 15px;" +
	            "-fx-background-radius: 15px;" +
	            "-fx-padding: 10px;"
	    );

	    // Add shadow effect to coinsArray label
	    DropShadow shadow = new DropShadow();
	    shadow.setColor(Color.web("#222222")); // Dark shadow color
	    shadow.setRadius(10);
	    shadow.setOffsetX(5);
	    shadow.setOffsetY(5);
	    coinsArray.setEffect(shadow);

	    // Horizontal box to combine the coin view and the array label
	    HBox hBox = new HBox(CoinRedv, coinsArray);

	    // Player 1 layout (Red player)
	    VBox vBox1 = new VBox(20);
	    vBox1.setStyle("-fx-background-color: #6c2427;"); // Background color for player 1 section
	    player1Label = new Label("Player 1");
	    player1Label.setStyle("-fx-font-size: 20; -fx-font-weight: bold; -fx-text-fill: #FFD700;");
	    set1 = new Label("0"); // Initial score for player 1
	    set1.setStyle("-fx-font-size: 20; -fx-font-weight: bold; -fx-text-fill: #FFD700;");

	    // Buttons for player 1 actions
	    MyButton showResult1 = new MyButton("Show Result");
	    select1 = new Label("Select : ");
	    select1.setStyle("-fx-font-size: 20; -fx-font-weight: bold; -fx-text-fill: #FFD700;");
	    choice1 = new MyButton("First");
	    choice2 = new MyButton("End");
	    showResult1.inHoverSmall();
	    choice1.inHoverSmall();
	    choice2.inHoverSmall();

	    // Style buttons
	    showResult1.setStyle(" -fx-background-color: #FFD700;-fx-text-fill: black; -fx-font-size: 20px;");
	    choice1.setStyle(" -fx-background-color: #FFD700;-fx-text-fill: black; -fx-font-size: 20px;");
	    choice2.setStyle(" -fx-background-color: #FFD700;-fx-text-fill: black; -fx-font-size: 20px;");

	    // Horizontal box for player 1 choices
	    HBox hBox1 = new HBox(10, choice1, choice2);
	    hBox1.setAlignment(Pos.CENTER);

	    // Add player 1 components to VBox
	    vBox1.getChildren().addAll(redImageView, player1Label, set1, showResult1, select1, hBox1);

	    // Player 2 layout (Blue player)
	    VBox vBox2 = new VBox(20);
	    vBox2.setStyle("-fx-background-color: #254159;"); // Background color for player 2 section
	    player2Label = new Label("Player 2");
	    player2Label.setStyle("-fx-font-size: 20; -fx-font-weight: bold; -fx-text-fill: #FFD700;");
	    set2 = new Label("0"); // Initial score for player 2
	    set2.setStyle("-fx-font-size: 20; -fx-font-weight: bold; -fx-text-fill: #FFD700;");

	    // Buttons for player 2 actions
	    MyButton showResult2 = new MyButton("Show Result");
	    select2 = new Label("Select : ");
	    select2.setStyle("-fx-font-size: 20; -fx-font-weight: bold; -fx-text-fill: #FFD700;");
	    choice21 = new MyButton("First");
	    choice22 = new MyButton("End");
	    showResult2.inHoverSmall();
	    choice21.inHoverSmall();
	    choice22.inHoverSmall();

	    // Style buttons
	    showResult2.setStyle(" -fx-background-color: #FFD700;-fx-text-fill: black; -fx-font-size: 20px;");
	    choice21.setStyle(" -fx-background-color: #FFD700;-fx-text-fill: black; -fx-font-size: 20px;");
	    choice22.setStyle("-fx-background-color: #FFD700;fx-text-fill: black; -fx-font-size: 20px;");

	    // Horizontal box for player 2 choices
	    HBox hBox2 = new HBox(10, choice21, choice22);
	    hBox2.setAlignment(Pos.CENTER);

	    // Add player 2 components to VBox
	    vBox2.getChildren().addAll(blueImageView, player2Label, set2, showResult2, select2, hBox2);

	    // Controls section with Reset and Back buttons
	    vBoxControls = new VBox(10);
	    reset = new MyButton("Reset");
	    MyButton back = new MyButton("Back");
	    reset.inHover();
	    back.inHover();
	    back.setPrefWidth(250);
	    back.setOnAction(e -> {
	        stage.close();
	        mystage.setFullScreen(true);
	    });
	    reset.setPrefWidth(250);

	    // Add buttons to controls VBox
	    vBoxControls.getChildren().addAll(reset, back);
	    vBoxControls.setAlignment(Pos.BOTTOM_CENTER);

	    // Center layout with StackPane for background and controls
	    StackPane stackPane = new StackPane();
	    stackPane.getChildren().addAll(vsImageView, vBoxControls);

	    // Main layout with BorderPane
	    BorderPane borderPane = new BorderPane();
	    borderPane.setLeft(vBox1);
	    borderPane.setRight(vBox2);
	    borderPane.setTop(hBox);
	    hBox.setAlignment(Pos.CENTER);
	    hBox.setStyle("-fx-background-color: #6c2427;");

	    borderPane.setCenter(stackPane);
	    borderPane.getCenter().setStyle("-fx-background-color: #254159;");

	    // Adjust layout sizes and alignment
	    vBox1.setPrefWidth(250);
	    vBox2.setPrefWidth(250);
	    vBox1.setAlignment(Pos.TOP_CENTER);
	    vBox2.setAlignment(Pos.TOP_CENTER);

	    // Create and set the scene
	    Scene scene = new Scene(borderPane, 800, 600);
	    stage.setScene(scene);
	    stage.setTitle("MultiPage");
	    stage.setFullScreen(true);
	    stage.show();

	    // Initialize variables and external dependencies
	    j = arrayOfCoins.length;
	    choice21.setDisable(true);
	    choice22.setDisable(true);
	    decetedPage = new DecetedPage(stage, this);

	    // Reset button logic
	    reset.setOnAction(e -> {
	        ArrayBlue = "";
	        ArrayRed = "";
	        counter = arrayOfCoins.length;
	        i = 0;
	        j = arrayOfCoins.length;

	        // Confirmation dialog for reset
	        Stage confirmationStage = new Stage();
	        confirmationStage.initModality(Modality.APPLICATION_MODAL);
	        confirmationStage.initOwner(stage);
	        confirmationStage.setTitle("Confirmation");

	        Label confirmationLabel = new Label("Are you sure?");
	        confirmationLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

	        Button yesButton = new Button("Yes");
	        Button noButton = new Button("No");

	        // Reset actions on confirmation
	        yesButton.setOnAction(e1 -> {
	            set1.setText("0");
	            set2.setText("0");
	            choice1.setDisable(x);
	            choice2.setDisable(x);
	            choice21.setDisable(!x);
	            choice22.setDisable(!x);
	            coinsArray.setText(cArray);
	            confirmationStage.close();
	        });

	        // Close dialog on cancel
	        noButton.setOnAction(e1 -> {
	            confirmationStage.close();
	        });

	        // Layout for confirmation dialog
	        HBox buttonBox = new HBox(10, yesButton, noButton);
	        buttonBox.setAlignment(Pos.CENTER);

	        VBox layout = new VBox(20, confirmationLabel, buttonBox);
	        layout.setAlignment(Pos.CENTER);
	        layout.setStyle("-fx-padding: 20; -fx-background-color: #ffffff;");

	        // Show confirmation dialog
	        Scene confirmationScene = new Scene(layout, 300, 150);
	        confirmationStage.setScene(confirmationScene);
	        confirmationStage.show();
	    });

//this variable to show if it finished
		counter = arrayOfCoins.length;
		//the changes if the first player select first
		choice1.setOnAction(e -> {
			counter--;
			int x = Integer.valueOf(arrayOfCoins[i]);
			set1.setText(String.valueOf(Integer.valueOf(set1.getText()) + x));
			i++;

			coinsArray.setText(getString(arrayOfCoins, i, j - 1)); // Update coinsArray dynamically
			choice1.setDisable(true);
			choice2.setDisable(true);
			choice21.setDisable(false);
			choice22.setDisable(false);
			ArrayRed += String.valueOf(x) + ",";
			if (counter == 0) {
				choice1.setDisable(true);
				choice2.setDisable(true);
				choice21.setDisable(true);
				choice22.setDisable(true);
				compare(Integer.valueOf(set1.getText()), Integer.valueOf(set2.getText()), stage);
			}
		});
		//the changes if the first player select end
		choice2.setOnAction(e -> {
			counter--;
			int x = Integer.valueOf(arrayOfCoins[j - 1]);
			set1.setText(String.valueOf(Integer.valueOf(set1.getText()) + x));
			j--;

			coinsArray.setText(getString(arrayOfCoins, i, j - 1)); // Update coinsArray dynamically
			choice1.setDisable(true);
			choice2.setDisable(true);
			choice21.setDisable(false);
			choice22.setDisable(false);
			ArrayRed += String.valueOf(x) + ",";
			if (counter == 0) {
				choice1.setDisable(true);
				choice2.setDisable(true);
				choice21.setDisable(true);
				choice22.setDisable(true);
				compare(Integer.valueOf(set1.getText()), Integer.valueOf(set2.getText()), stage);
			}
		});
		//the changes if the second player select first
		choice21.setOnAction(e -> {
			counter--;
			int x = Integer.valueOf(arrayOfCoins[i]);
			set2.setText(String.valueOf(Integer.valueOf(set2.getText()) + x));
			i++;

			coinsArray.setText(getString(arrayOfCoins, i, j - 1)); // Update coinsArray dynamically
			choice1.setDisable(false);
			choice2.setDisable(false);
			choice21.setDisable(true);
			choice22.setDisable(true);
			ArrayBlue += String.valueOf(x) + ",";
			if (counter == 0) {
				choice1.setDisable(true);
				choice2.setDisable(true);
				choice21.setDisable(true);
				choice22.setDisable(true);
				compare(Integer.valueOf(set1.getText()), Integer.valueOf(set2.getText()), stage);
			}
		});
		//the changes if the second player select end
		choice22.setOnAction(e -> {
			counter--;
			int x = Integer.valueOf(arrayOfCoins[j - 1]);
			set2.setText(String.valueOf(Integer.valueOf(set2.getText()) + x));
			j--;

			coinsArray.setText(getString(arrayOfCoins, i, j - 1)); // Update coinsArray dynamically
			choice1.setDisable(false);
			choice2.setDisable(false);
			choice21.setDisable(true);
			choice22.setDisable(true);
			ArrayBlue += String.valueOf(x) + ",";
			if (counter == 0) {
				choice1.setDisable(true);
				choice2.setDisable(true);
				choice21.setDisable(true);
				choice22.setDisable(true);
				compare(Integer.valueOf(set1.getText()), Integer.valueOf(set2.getText()), stage);
			}
		});
		//show the choices for player1
		showResult1.setOnAction(e -> {
			// Create an information alert
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Result");
			alert.setHeaderText(null); // Optional: remove header if you don't need it
			alert.setContentText(ArrayRed);
			alert.initOwner(stage);
			// Show the alert and wait for user action (blocks until OK is pressed)
			alert.showAndWait();

		});
		//show the choices for player2
		showResult2.setOnAction(e -> {
			// Create an information alert
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Result");

			alert.setHeaderText(null); // Optional: remove header if you don't need it
			alert.setContentText(ArrayBlue);
			alert.initOwner(stage);
			// Show the alert and wait for user action (blocks until OK is pressed)
			alert.showAndWait();

		});

	}
//method to deceit the winner by the points
	public void compare(int redSc, int blueSc, Stage stage) {
		Image blue2 = new Image("Player2Blue.png");
		ImageView blueImageView2 = new ImageView(blue2);
		blueImageView2.setFitWidth(130);
		blueImageView2.setFitHeight(150);
		Image Red2 = new Image("RedP.png");
		ImageView RedImageView2 = new ImageView(Red2);
		RedImageView2.setFitWidth(130);
		RedImageView2.setFitHeight(150);
		Image winBlue = new Image("winBlue.png");
		ImageView winView = new ImageView(winBlue);
		winView.setFitWidth(160);
		winView.setFitHeight(100);
		Image winRed = new Image("win.png");
		ImageView winView2 = new ImageView(winRed);
		winView2.setFitWidth(160);
		winView2.setFitHeight(100);
		if (redSc > blueSc) {
			showDialog(winView2, RedImageView2, stage);
		} else if (redSc < blueSc) {
			showDialog(winView, blueImageView2, stage);
		} else {
			showDidraw(stage);
		}

	}
//method to show the winner
	public void showDialog(ImageView image, ImageView player, Stage mystage) {
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.initOwner(mystage);
		stage.setTitle("Winner");

		Label winnerLabel = new Label("Winner!");
		winnerLabel.setStyle("-fx-font-size: 20; -fx-font-weight: Bold; -fx-text-fill: 	#FFD700;");
		Image win = new Image("CoinBack.jpg");
		ImageView winView = new ImageView(win);
		winView.setFitWidth(500);
		winView.setFitHeight(500);

		StackPane stackPane = new StackPane();

		VBox layout = new VBox(20, image, winnerLabel, player);
		layout.setAlignment(Pos.CENTER);
		stackPane.getChildren().addAll(winView, layout);
		Scene confirmationScene = new Scene(stackPane, 500, 500);
		stage.setScene(confirmationScene);
		stage.show();
	}
//method to show draw stage
	public void showDidraw(Stage mystage) {
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.initOwner(mystage);
		stage.setTitle("Winner");

		Label winnerLabel = new Label("draw!");
		winnerLabel.setStyle("-fx-font-size: 50; -fx-font-weight: Bold; -fx-text-fill: 	#FFD700;");
		Image win = new Image("CoinBack.jpg");
		ImageView winView = new ImageView(win);
		winView.setFitWidth(500);
		winView.setFitHeight(500);

		StackPane stackPane = new StackPane();

		VBox layout = new VBox(20, winnerLabel);
		layout.setAlignment(Pos.CENTER);
		stackPane.getChildren().addAll(winView, layout);
		Scene confirmationScene = new Scene(stackPane, 500, 500);
		stage.setScene(confirmationScene);
		stage.show();
	}
// build  the label thats contain array
	public String getString(int[] arr, int from, int to) {
		StringBuilder coinString = new StringBuilder();
		for (int i = from; i <= to; i++) {
			coinString.append(arr[i]);
			if (i < to) { // Add a separator only if it's not the last number
				coinString.append(" ");
			}
		}
		return coinString.toString();
	}
}
