package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;
import java.util.Random;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Main extends Application {
	private int[] coins; // Array to store the set of coin values entered by the user
	private Label descrep = new Label(); // Label to display status messages to the user
	private VBox vBox;

	@Override
	public void start(Stage primaryStage) {
		// exit button image

		Image ExitImage1 = new Image("logout.png");
		ImageView EximageView1 = new ImageView(ExitImage1);
		EximageView1.setFitWidth(50);
		EximageView1.setFitHeight(50);

		// Create and style exit button
		MyButton exitButton = new MyButton("Exit", EximageView1);
		exitButton.setPrefWidth(350);


		// font and color for description label
		Font font = new Font(20);
		descrep.setFont(font);
		descrep.setTextFill(Color.BLACK);

		// Main label
		Label inputLab = new Label("You need to fill the coins set from:");
		inputLab.setPrefWidth(950);
		inputLab.setPrefHeight(200);

		// Apply a refined complementary background style
		inputLab.setStyle(
		    "-fx-font-size: 50px;" +
		    "-fx-font-weight: bold;" +
		    "-fx-text-fill: #444444;" + // Soft dark gray for clear and elegant text
//		    "-fx-background-color: linear-gradient(to bottom, #FFF8DC, #FFD700);" + // Cream to gold gradient
		    "-fx-padding: 20px;" +
		    "-fx-border-color: #FFECB3;" + // Soft pastel yellow border
		    "-fx-border-width: 4px;" +
		    "-fx-border-radius: 25px;" +
		    "-fx-background-radius: 25px;"
		);

		// Add a subtle shadow for depth and elegance
		DropShadow shadow = new DropShadow();
		shadow.setColor(Color.web("#DDDDDD")); // Light gray shadow for softness
		shadow.setRadius(15);
		shadow.setOffsetX(5);
		shadow.setOffsetY(5);
		inputLab.setEffect(shadow);
		// Load images for the input option buttons
		Image backgroundImage1 = new Image("Fromfile.png");
		ImageView imageView1 = new ImageView(backgroundImage1);
		imageView1.setFitWidth(50);
		imageView1.setFitHeight(50);

		Image backgroundImage2 = new Image("dice.png");
		ImageView imageView2 = new ImageView(backgroundImage2);
		imageView2.setFitWidth(50);
		imageView2.setFitHeight(50);

		Image backgroundImage3 = new Image("Manual.png");
		ImageView imageView3 = new ImageView(backgroundImage3);
		imageView3.setFitWidth(50);
		imageView3.setFitHeight(50);

		// Create buttons for coin set input options: from file, random, and manual
		// entry
		MyButton btnFile = new MyButton("File", imageView1);
		MyButton btnRandom = new MyButton("Random", imageView2);
		MyButton btnManual = new MyButton("Manual", imageView3);

		btnFile.setPrefWidth(350);
		btnRandom.setPrefWidth(350);
		btnManual.setPrefWidth(350);


		// Set hover effects for each button to enhance user interaction
		btnFile.inHover();
		btnManual.inHover();
		btnRandom.inHover();
		exitButton.inHover();

		vBox = new VBox(30);
		vBox.getChildren().addAll(inputLab, btnFile, btnRandom, btnManual, exitButton, descrep);
		vBox.setAlignment(Pos.TOP_CENTER);
		descrep.setStyle("-fx-font-size:30;-fx-font-weight:Bold;-fx-text-fill: Black;");

		vBox.requestLayout();

		// background image
		Image backgroundImage = new Image("BackgroundCoin.jpg");
		ImageView imageView = new ImageView(backgroundImage);
		imageView.setFitWidth(1650);
		imageView.setFitHeight(1050);

		// Place background image and VBox in a StackPane
		StackPane centerPane = new StackPane();
		centerPane.getChildren().addAll(imageView, vBox);

		// Use BorderPane to organize layout
		BorderPane borderPane = new BorderPane();
		borderPane.setCenter(centerPane);

		// Set up scene with main BorderPane
		Scene scene = new Scene(borderPane, 1320, 480);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Coins Game");
		primaryStage.setFullScreen(true);
		primaryStage.show();

		// to show confirmation dialog
		exitButton.setOnAction(e -> {
			showConfirmationDialog(primaryStage);
		});

		// button action to load coin set from a file
		btnFile.setOnAction(e -> {
			String conString = "Set Of Coins: ";
			try {
				// Open file chooser to select a coin set file
				FileChooser fileChooser = new FileChooser();
				File selector = fileChooser.showOpenDialog(primaryStage);

				// If file is selected, start reading data
				if (selector != null) {
					BufferedReader scan = new BufferedReader(new FileReader(selector));
					String firstLine;

					// Skip empty lines and read the first non-empty line as array size
					while ((firstLine = scan.readLine()) != null && firstLine.trim().isEmpty()) {
					}

					// even array size
					if (firstLine != null && !firstLine.trim().isEmpty()) {
						int expectedSize = Integer.parseInt(firstLine.trim());

						if (expectedSize % 2 != 0) {
							descrep.setText("Array size cannot be odd");
							scan.close();
							return;
						}

						// Initialize coin array with specified size
						coins = new int[expectedSize];
						descrep.setText("Array size set to: " + coins.length);

						// Populate the array with values from the file
						String line;
						int i = 0;
						while ((line = scan.readLine()) != null) {
							if (!line.trim().isEmpty()) {
								if (i < expectedSize) {
									coins[i] = Integer.parseInt(line.trim());
									conString += "[" + coins[i] + "] ";
									i++;
								} else {
									descrep.setText("File has more numbers than expected (" + expectedSize
											+ "). Cannot read further");
									break;
								}
							}
						}

						if (i < expectedSize) {
							descrep.setText("File has fewer numbers than expected (" + expectedSize
									+ "). Array not fully populated: " + i);
						} else {
							descrep.setText("Array populated successfully with " + i + " numbers.");
							SecPage secPage = new SecPage(vBox, conString, primaryStage, coins);
						}

						scan.close();
					} else {
						descrep.setText("No valid size found in the file.");
					}
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (NumberFormatException e2) {
				descrep.setText("Not an integer");
			}
		});

		// to generate random coin set
		btnRandom.setOnAction(e -> {
			RandomStage randomStage = new RandomStage(primaryStage, vBox);
		});

		// to allow manual coin entry
		btnManual.setOnAction(e -> {
			ManualStage manualStage = new ManualStage(primaryStage, vBox);
		});
	}

	// Confirmation dialog to confirm exit when exit button is clicked
	private static void showConfirmationDialog(Stage stage) {
		Stage confirmationStage = new Stage();
		confirmationStage.initModality(Modality.APPLICATION_MODAL);
		confirmationStage.initOwner(stage);
		confirmationStage.setTitle("Confirmation");

		Label confirmationLabel = new Label("Are you sure?");
		confirmationLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

		Button yesButton = new Button("Yes");
		Button noButton = new Button("No");

		yesButton.setOnAction(e -> {
			stage.close();
		});

		noButton.setOnAction(e -> {
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

	public static void main(String[] args) {
		launch(args);
	}
}
