package application;

import javafx.util.Duration;
import javafx.animation.PauseTransition;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

/* this class are sub page from play page,in this class we add sum details for the play page to be for
computer vs player and build dynamic relation in code to set the two player choice the optimal choice   */
public class ComputerPage extends PlayPage {

	public ComputerPage(int[] arr, Stage mystage) {
		super(arr, mystage);
		// to be the user can not use buttons
		getSelect1().setVisible(false);
		getSelect2().setVisible(false);
		getChoice1().setVisible(false);
		getChoice2().setVisible(false);
		getChoice21().setVisible(false);
		getChoice22().setVisible(false);
		getDecetedPage().getStage().hide();
		MyButton Details = new MyButton("Details");
		Details.inHover();
		Details.setPrefWidth(250);

		MyButton start = new MyButton("Start");
		start.inHover();
		start.setPrefWidth(250);

		getvBoxControls().getChildren().add(0, start);

		int n = arr.length;
		int[][] towDim = new int[n][n]; // array to use in DP table
		byte[][] choice = new byte[n - 1][n - 1];// array to know what the optimal choice in all steps
		GridPane gridPane = new GridPane();
		gridPane.setAlignment(Pos.CENTER);
		gridPane.setVgap(10);
		gridPane.setHgap(50);

		initializeGridHeaders(gridPane, n);
		// if size =1 one choice
		for (int i = 0; i < n; i++) {
			Label label = new Label("");
			towDim[i][i] = arr[i];
			label.setStyle("-fx-font-size:24;-fx-font-weight:Bold ;");
			label.setText("" + arr[i]);

			gridPane.add(label, i + 1, i + 1);
		}
		// if size =0 coins is 0
		for (int i = 0; i < towDim.length; i++) {
			for (int j = i + 1; j < towDim.length; j++) {
				towDim[i][j] = 0;
				Label label = new Label("");
				label.setStyle("-fx-font-size:24;-fx-font-weight:Bold ;");
				label.setText("0");

				gridPane.add(label, i + 1, j + 1);
			}
		}

		/*
		 * the dynamic relation when size >0 max (arr[i] + min(towDim[i + 2][j](choice
		 * from right), towDim[i + 1][j - 1](choice from left)) ,arr[j] +
		 * min(towDim[i][j - 2](choice from left), towDim[i + 1][j - 1](choice from
		 * right))
		 */
		for (int subLen = 2; subLen <= n; subLen++) {

			for (int j = n - 1; j >= subLen - 1; j--) {
				int right = 0;
				int left = 0;
				int i = j - subLen + 1;
				if(subLen==2) { //if length of sub array =2
					left = arr[i];
					right = arr[j];
					towDim[i][j] = Math.max(left, right);
				}else if                //out of bonders
				 (i + 1 >= n || j - 1 < 0) {
					left = arr[i];
					right = arr[j];
					towDim[i][j] = Math.max(left, right);
               //the end of array
				} else if (i + 2 >= n) {
					left = arr[i];
					right = arr[j] + Math.min(towDim[i][j - 2], towDim[i + 1][j - 1]);
					towDim[i][j] = Math.max(left, right);

				}
				 //start array
				else if (j - 2 < 0) {
					left = arr[i] + Math.min(towDim[i + 2][j], towDim[i + 1][j - 1]);
					right = arr[j];
					towDim[i][j] = Math.max(left, right);
				} else {// normal case
					left = arr[i] + Math.min(towDim[i + 2][j], towDim[i + 1][j - 1]);
					right = arr[j] + Math.min(towDim[i][j - 2], towDim[i + 1][j - 1]);
					towDim[i][j] = Math.max(left, right);
				}
				if (right > left) {// fill the array of choices
					choice[i][j - 1] = 1;
				} else if (right == left) {
					if (arr[i] > arr[j]) {
						choice[i][j - 1] = 0;
					} else {
						choice[i][j - 1] = 1;
					}
				} else {
					choice[i][j - 1] = 0;
				}
				// build dp table
				createDPGrid(towDim[i][j], gridPane, i + 1, j + 1);
			}
		}

		start.setVisible(true);
		getvBoxControls().getChildren().add(0, Details);
		Details.setVisible(false);
		start.setOnAction(e -> {
			int i1 = 0;
			int j1 = n - 2;

			simPresses(mystage, arr, choice, i1, j1, Details);
			Details.setVisible(true);
			Details.setDisable(true);
			start.setVisible(false);

			getReset().setVisible(true);
		});

		Details.setOnAction(e -> {
			showDP(gridPane, getStage(), arr.length);
		});
		getReset().setOnAction(e -> {

			ArrayBlue = "";
			ArrayRed = "";

			setCounter(arr.length);
			setI(0);
			setJ(arr.length);
			Stage confirmationStage = new Stage();
			confirmationStage.initModality(Modality.APPLICATION_MODAL);
			confirmationStage.initOwner(getStage());
			confirmationStage.setTitle("Confirmation");

			Label confirmationLabel = new Label("are you sure?");
			confirmationLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

			Button yesButton = new Button("Yes");

			Button noButton = new Button("No");

			yesButton.setOnAction(e1 -> {
				start.setVisible(true);
				Details.setVisible(false);
				getSet1().setText("0");
				getSet2().setText("0");
				getChoice1().setDisable(isX());
				getChoice2().setDisable(isX());
				getChoice21().setDisable(!isX());
				getChoice22().setDisable(!isX());
//				doublyLinkedList.display();
				getCoinsArray().setText(getcArray());
				confirmationStage.close();
			});

			noButton.setOnAction(e1 -> {
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

		});

	}

	private void simPresses(Stage mystage, int[] arr, byte[][] select, int i, int j, MyButton Details) {
		if (i > j + 1) {
			Details.setDisable(false);
			return; // stop the simulation
		}

		// Check the first choice between left and right
		if (select[i][j] == 0 && !getChoice1().isDisable()) {
			pressChoice1();
			i++; // Move left index up if choosing left
		} else if (select[i][j] == 1 && !getChoice2().isDisable()) {
			pressChoice2();
			j--; // Move right index down if choosing right
		}

		// Delay before moving to the next step in the simulation
		int finalI = i;
		int finalJ = j;
		PauseTransition delay = new PauseTransition(Duration.seconds(3));
		// when the array of choice arrived the end the end(just one option) choice can
		// be end or first
		if (finalI == -1 || finalJ == -1) {
			pressChoice22();
			delay.setOnFinished(e -> {

				delay.play();
			});
			Details.setDisable(false);
			return;
		}

		delay.setOnFinished(event -> {
			// After the first player choice we determine if the next choice is on the right
			if (select[finalI][finalJ] == 1 && !getChoice22().isDisable()) {
				pressChoice22();
				delay.setOnFinished(e -> {
					if (finalJ != 0) {
						simPresses(mystage, arr, select, finalI, finalJ - 1, Details);
					}
				});

			} // After the first player choice we determine if the next choice is on the left
			else if (select[finalI][finalJ] == 0 && !getChoice21().isDisable()) {
				pressChoice21();
				delay.setOnFinished(e -> {
					if (finalI != arr.length - 2) {
						simPresses(mystage, arr, select, finalI + 1, finalJ, Details);
					}
				});

			}
			delay.play();

		});
		delay.play();
	}

// to set buttons on fire after I start
	public void pressChoice1() {
		getChoice1().fire();
	}

	public void pressChoice2() {
		getChoice2().fire();
	}

	public void pressChoice21() {
		getChoice21().fire();
	}

	public void pressChoice22() {
		getChoice22().fire();
	}

	public void initializeGridHeaders(GridPane gridPane, int size) {
		for (int i = 0; i < size; i++) {
			Label rowHeader = new Label("" + i);
			gridPane.add(rowHeader, 0, i + 1); // Row headers
			rowHeader.setStyle("-fx-font-size:24;-fx-font-weight:Bold ;-fx-text-fill:  #FFD700;");

			Label colHeader = new Label("" + i);
			colHeader.setStyle("-fx-font-size:24;-fx-font-weight:Bold ;-fx-text-fill:  #FFD700;");
			gridPane.add(colHeader, i + 1, 0); // Column headers
		}
	}

	public void createDPGrid(int x, GridPane gridPane, int j, int i) {
		Label label = new Label();
		label.setStyle("-fx-font-size:24;-fx-font-weight:Bold ;");
		label.setText(String.valueOf(x));
		gridPane.add(label, i, j);

	}

	public void showDP(GridPane dpGrid, Stage mainStage, int size) {
		// background Image
		Image imageBack = new Image("TableBack.jpg");
		ImageView imageView = new ImageView(imageBack);
		imageView.setFitWidth(1000);
		imageView.setFitHeight(600);
		imageView.setPreserveRatio(false);

		// create a Stage
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.initOwner(mainStage);

		// style GridPane (the DP table)
		if (size < 250) {
			dpGrid.setPadding(new Insets(20));
			dpGrid.setHgap(250 / size);
			dpGrid.setVgap(250 / size);
			dpGrid.setStyle("-fx-background-color: rgba(255, 255, 255, 0.8); " + "-fx-border-color: #2C3E50; "
					+ "-fx-border-width: 2; " + "-fx-border-radius: 10; " + "-fx-background-radius: 10;");
		}

		else {

			dpGrid.setPadding(new Insets(20));
			dpGrid.setHgap(25);
			dpGrid.setVgap(25);
			dpGrid.setStyle("-fx-background-color: rgba(255, 255, 255, 0.8); " + "-fx-border-color: #2C3E50; "
					+ "-fx-border-width: 2; " + "-fx-border-radius: 10; " + "-fx-background-radius: 10;");
		}
		// Add alternating row colors for better readability

		dpGrid.setStyle("-fx-background-color: white;-fx-font-family: 'Arial'; -fx-font-size: 14;");
		// Scroll Pane for the GridPane
		ScrollPane scrollPane = new ScrollPane(dpGrid);
		scrollPane.setFitToWidth(true);
		scrollPane.setFitToHeight(true);
		scrollPane.setStyle("-fx-background: transparent; " + "-fx-border-color: #34495E; " + "-fx-border-width: 2; "
				+ "-fx-border-radius: 10; " + "-fx-background-radius: 10;");

		// StackPane to layer background and scroll pane
		StackPane stackPane = new StackPane();
		stackPane.getChildren().addAll(imageView, scrollPane);
		stackPane.setPadding(new Insets(10));
		stackPane.setStyle("-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.5), 15, 0.5, 0, 0);");

		// Scene and Stage Configuration
		Scene scene = new Scene(stackPane, 1000, 600);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		dpGrid.getStyleClass().add("grid-pane");
		scrollPane.getStyleClass().add("scroll-pane");
		stackPane.getStyleClass().add("stack-pane");
		stage.setScene(scene);
		stage.showAndWait();
	}

}
