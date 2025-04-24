package application;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
//this page are just to build special button to use in design
public class MyButton extends Button {

	public MyButton(String text) {
		super(text);
		setStyle("-fx-background-color: linear-gradient(to bottom, #FFD700, #FFA500);" + // Gradient background
				"-fx-text-fill: black;" + // Text color
				"-fx-font-size: 50px;" + // Font size
				"-fx-font-weight: bold;" + // Bold text
				"-fx-background-radius: 20px;" + // Rounded corners
				"-fx-border-radius: 20px;" + // Rounded border
				"-fx-border-width: 3px;" + // Border width
				"-fx-border-color: black;" // Border color
		);

		// Add drop shadow effect
		DropShadow shadow = new DropShadow();
		shadow.setColor(Color.GOLD);
		shadow.setRadius(15);
		shadow.setOffsetX(0);
		shadow.setOffsetY(4);
		setEffect(shadow);

	}

	public MyButton(String text, Node n) {
		super(text, n);
		setStyle("-fx-background-color: linear-gradient(to bottom, #FFD700, #FFA500);" + // Gradient background
				"-fx-text-fill: black;" +
				"-fx-font-size: 50px;" +
				"-fx-font-weight: bold;" +
				"-fx-background-radius: 20px;" +
				"-fx-border-radius: 20px;" +
				"-fx-border-width: 3px;" +
				"-fx-border-color: black;"
		);

		// Add drop shadow effect
		DropShadow shadow = new DropShadow();
		shadow.setColor(Color.GOLD);
		shadow.setRadius(15);
		shadow.setOffsetX(0);
		shadow.setOffsetY(4);
		setEffect(shadow);

	}

	public void inHover() {
		// When the mouse enters the button area
		setOnMouseEntered(e -> {
			setStyle("-fx-background-color: linear-gradient(to bottom, #FFA500, #FF8C00);" +
					"-fx-text-fill: white;" +
					"-fx-font-size: 52px;" +
					"-fx-font-weight: bold;" + "-fx-background-radius: 20px;" + "-fx-border-radius: 20px;"
					+ "-fx-border-width: 3px;" + "-fx-border-color: white;"
			);
			setScaleX(1.05); // Slightly enlarge the button
			setScaleY(1.05);
		});

		// When the mouse exits the button area
		setOnMouseExited(e -> {
			setStyle("-fx-background-color: linear-gradient(to bottom, #FFD700, #FFA500);" + "-fx-text-fill: black;"
					+ "-fx-font-size: 50px;" + "-fx-font-weight: bold;" + "-fx-background-radius: 20px;"
					+ "-fx-border-radius: 20px;" + "-fx-border-width: 3px;" + "-fx-border-color: black;");
			setScaleX(1.0); // Reset size
			setScaleY(1.0);
		});
	}

	public void inHoverSmall() {

		setOnMouseEntered(e -> {
			setStyle("-fx-background-color: black; -fx-text-fill: white; -fx-font-size: 22px;"); // Change color
		});

		// When the mouse exits the button area
		setOnMouseExited(e -> {
			setStyle("-fx-background-color: #FFD700; -fx-text-fill: black; -fx-font-size: 20px;"); // Revert color
		});
	}
}
