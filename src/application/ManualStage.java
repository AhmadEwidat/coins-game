package application;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ManualStage {

	private int[] arr;
//This window appears when you choose to manually fill the array
	public ManualStage(Stage mainStage, VBox vBox) {
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.initOwner(mainStage);

		Label titleLabel = new Label("Manual Array Input");
		titleLabel.setStyle("-fx-font-size: 24; -fx-font-weight: Bold;");

		Label sizeLabel = new Label("Enter Array Size:");
		sizeLabel.setStyle("-fx-font-size: 16;");

		TextField sizeField = new TextField();
		sizeField.setPromptText("Size");
		HBox hBox = new HBox(10);

		HBox elementBox = new HBox(10);
		elementBox.setAlignment(Pos.CENTER);

		MyButton generateButton = new MyButton("Set Size");
		generateButton.inHoverSmall();
		generateButton.setStyle("-fx-background-color: #FFD700; -fx-text-fill: black; -fx-font-size: 18px;");

		hBox.getChildren().addAll(sizeField, generateButton);
		hBox.setAlignment(Pos.CENTER);
		HBox hBox2 = new HBox(10);

		generateButton.setOnAction(e -> {
			if (isEvenInteger(sizeField)) { // Check if sizeField contains an even integer
				int size = Integer.parseInt(sizeField.getText().trim());
				if (size > 0) {
					arr = new int[size];
					elementBox.getChildren().clear();

					for (int i = 0; i < size; i++) {
						TextField elementField = new TextField();
						elementField.setPromptText("Value " + (i + 1));
						restrictToInteger(elementField); // Restrict elementField to only integers
						elementBox.getChildren().add(elementField);
					}

					MyButton submitButton = new MyButton("Submit");
					MyButton cancelButton = new MyButton("Cancel");
					submitButton.inHoverSmall();
					cancelButton.inHoverSmall();

					hBox2.getChildren().clear(); // Clear any existing buttons
					hBox2.setAlignment(Pos.CENTER);
					hBox2.getChildren().addAll(submitButton, cancelButton);

					cancelButton.setStyle("-fx-background-color: #FFD700; -fx-text-fill: black; -fx-font-size: 18px;");
					submitButton.setStyle("-fx-background-color: #FFD700; -fx-text-fill: black; -fx-font-size: 18px;");

					// Cancel button action
					cancelButton.setOnAction(e1 -> {
						arr = null;
						stage.close();
					});

					// Submit button action
					submitButton.setOnAction(e1 -> {
						String conString = "Set Of Coins : ";
						try {
							// Collect values from each text field into the array
							for (int i = 0; i < size; i++) {
								TextField elementField = (TextField) elementBox.getChildren().get(i);
								arr[i] = Integer.parseInt(elementField.getText().trim());
								conString += "[" + arr[i] + "] ";
							}

							// Pass array to SecPage
							SecPage secPage = new SecPage(vBox, conString, mainStage, arr);
							stage.close();
						} catch (NumberFormatException ex) {
							sizeLabel.setText("All fields must contain valid integers.");
						}
					});
				} else {
					sizeLabel.setText("Size must be greater than zero.");
				}
			} else {
				sizeLabel.setText("Please enter a valid even integer for size.");
			}
		});

		VBox layout = new VBox(40, titleLabel, sizeLabel, hBox, elementBox, hBox2);
		layout.setAlignment(Pos.CENTER);

		RadialGradient backgroundGradient = new RadialGradient(0, 0, 0, 0, 1, true, CycleMethod.NO_CYCLE,
				new Stop(0, Color.web("#ffcce6")), new Stop(1, Color.web("#fcc200")));
		BackgroundFill backgroundFill = new BackgroundFill(backgroundGradient, CornerRadii.EMPTY,
				javafx.geometry.Insets.EMPTY);
		layout.setBackground(new Background(backgroundFill));

		Scene scene = new Scene(layout, 600, 400);
		stage.setScene(scene);
		stage.setTitle("Manual Array Input");
		stage.show();
	}

	// Helper method to check if TextField contains an even integer
	public boolean isEvenInteger(TextField textField) {
		try {
			int value = Integer.parseInt(textField.getText().trim());
			return value % 2 == 0; // Check if the number is even
		} catch (NumberFormatException e) {
			return false;
		}
	}

	// Helper method to restrict a TextField to only allow integer input
	public void restrictToInteger(TextField textField) {
		textField.setOnKeyTyped(e -> {
			if (!e.getCharacter().matches("\\d")) { // Allow only numeric characters
				e.consume();
			}
		});
	}
//method to check the input is integer
	public boolean isInteger(TextField textField) {
		try {
			Integer.parseInt(textField.getText().trim());
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public int[] getArray() {
		return arr;
	}
}
