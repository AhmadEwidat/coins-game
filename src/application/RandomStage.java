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

import java.util.Random;

public class RandomStage {

	private int[] generatedArray; // Array to store generated numbers

	public RandomStage(Stage mainStage, VBox secvBox) {
		//set this stage to be window for main stage
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.initOwner(mainStage);

		Label l1 = new Label("Please enter the bounds for array size and random values");
		l1.setStyle("-fx-font-size:24;-fx-font-weight:Bold");

		Label l2 = new Label();
		l2.setStyle("-fx-font-size:14;-fx-font-weight:Bold; -fx-text-fill: red;");
//confirm button
		Button button1 = new Button("Go");
		button1.setStyle("-fx-background-color: #FFD700; -fx-text-fill: black; -fx-font-size: 20px;");
//exit button
		Button button2 = new Button("Cancel");
		button2.setStyle("-fx-background-color: #FFD700; -fx-text-fill: black; -fx-font-size: 20px;");
		button2.setOnAction(e -> stage.close());

		TextField textFrom1 = new TextField(); // Array size lower bound
		TextField textTo1 = new TextField(); // Array size upper bound
		TextField textFrom2 = new TextField(); // Random values lower bound
		TextField textTo2 = new TextField(); // Random values upper bound

		restrictToInteger(textFrom1);
		restrictToInteger(textTo1);
		restrictToInteger(textFrom2);
		restrictToInteger(textTo2);
		button1.setOnAction(e -> {
			if (isInteger(textFrom1) && isInteger(textTo1) && isInteger(textFrom2) && isInteger(textTo2)) {
				int fromSize = Integer.parseInt(textFrom1.getText().trim());
				int toSize = Integer.parseInt(textTo1.getText().trim());
				int fromValue = Integer.parseInt(textFrom2.getText().trim());
				int toValue = Integer.parseInt(textTo2.getText().trim());

				if (fromSize >= 2 && fromSize <= toSize && fromValue <= toValue) {
					// Adjust bounds to ensure an even-sized array
					fromSize = (fromSize + 1) / 2; // Half of fromSize, rounded up if odd
					toSize = toSize / 2; // Half of toSize
					int arraySize;
					if (toSize < fromSize) {
						l2.setText("size should be even ");
					} else {
						// Generate a random size within the modified bounds and multiply by 2
						arraySize = (new Random().nextInt(toSize - fromSize + 1) + fromSize) * 2;

						// Create and populate the array with random numbers within the specified value
						// range
						generatedArray = new int[arraySize];
						Random random = new Random();
						String conString = "Set Of Coins : ";
						//fill the array
						for (int i = 0; i < generatedArray.length; i++) {
							generatedArray[i] = random.nextInt(toValue - fromValue + 1) + fromValue;
							conString += "[" + generatedArray[i] + "] ";
						}
						l2.setText("Array of size " + arraySize + " populated with random numbers");
						//go to the next page
						SecPage secPage = new SecPage(secvBox, conString, mainStage, generatedArray);
						stage.close();
					}
				} else {
					l2.setText("Ensure 'From' values are ≤ 'To' values and sizes are non-zero or negative");
				}
			} else {
				//error massage
				l2.setText("Please enter valid integers in all fields");
			}
		});

		HBox hBox1 = new HBox(l1);
		HBox hBox2 = new HBox(20, new Label("Length From"), textFrom1, new Label("To"), textTo1);
		HBox hBox3 = new HBox(20, new Label("Values From"), textFrom2, new Label("To"), textTo2);
		HBox hBox4 = new HBox(20, button1, button2);

		VBox vBox = new VBox(30, hBox1, hBox2, hBox3, hBox4, l2);
		hBox1.setAlignment(Pos.CENTER);
		hBox2.setAlignment(Pos.CENTER);
		hBox3.setAlignment(Pos.CENTER);
		hBox4.setAlignment(Pos.CENTER);
		l2.setAlignment(Pos.CENTER);
		vBox.setAlignment(Pos.CENTER);
		//build the background
		Scene scene = new Scene(vBox, 700, 300);
		RadialGradient nR = new RadialGradient(0, 0, 0, 0, 1, true, CycleMethod.NO_CYCLE,
				new Stop(0, Color.web("#ffcce6")), new Stop(1, Color.web("#fcc200")));
		BackgroundFill backgroundFill = new BackgroundFill(nR, CornerRadii.EMPTY, javafx.geometry.Insets.EMPTY);
		Background background = new Background(backgroundFill);
		vBox.setBackground(background);

		stage.setScene(scene);
		stage.setTitle("Random Array Generator");
		stage.setResizable(false);
		stage.show();
	}
	//to set the inputs are just integers
	public void restrictToInteger(TextField textField) {
		textField.setOnKeyTyped(e -> {
			if (!e.getCharacter().matches("[0-9]")) {
				e.consume();
			}
		});
	}
	//to check if input is integer
	public boolean isInteger(TextField textField) {
		try {
			Integer.parseInt(textField.getText().trim());
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}


}
