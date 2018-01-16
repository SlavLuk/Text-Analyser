/*
   Name :Vyacheslav Lukyanov
   Description: File and URL processing utility
   Date created:7/12/2016

*/
package ie.gmit.java2;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javafx.application.*;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.event.*;
import javafx.geometry.Insets;

public class Runner extends Application {

	private TextParser txt = null;

	private String search;

	public static void main(String[] args) {

		// Launch the JavaFX application
		Application.launch(args);

	}

	@Override
	public void start(Stage stage) {

		// Create label array
		Label[] label = { new Label(" 1) Parse a File or URL :"), new Label(" 2) Search :"), new Label(), new Label(),
				new Label(" 3) Delete all :"), new Label(" 4) Delete one :") };

		// set font and width for labels
		label[0].setFont(Font.font(16));
		label[0].setPrefWidth(170);

		label[1].setFont(Font.font(16));
		label[1].setPrefWidth(170);

		label[2].setFont(Font.font(16));

		label[3].setStyle("-fx-text-fill: green;");
		label[3].setFont(Font.font(16));

		label[4].setFont(Font.font(16));
		label[4].setPrefWidth(170);

		label[5].setFont(Font.font(16));
		label[5].setPrefWidth(170);

		// array of textfield
		TextField[] textFld = { new TextField(), new TextField(), new TextField(), new TextField() };

		// set text on textfields
		textFld[0].setText("http://www.google.com");
		textFld[1].setText("Enter a word to search");
		textFld[2].setText("Enter a string");
		textFld[3].setText("Enter an index");

		TextArea status = new TextArea();
		status.setPrefWidth(Double.MAX_VALUE);
		status.setFont(Font.font(16));
		status.setText("Status...");

		// Create array of the buttons
		Button[] btnArr = { new Button("Go"), new Button("File"), new Button("Search"), new Button("Print status"),
				new Button("Delete all"), new Button("Delete"), new Button("Exit") };

		// set width and font
		btnArr[0].setPrefWidth(47.5);
		btnArr[0].setFont(Font.font(12));

		btnArr[1].setPrefWidth(47.5);
		btnArr[1].setFont(Font.font(12));

		btnArr[2].setPrefWidth(100);
		btnArr[2].setFont(Font.font(12));

		btnArr[3].setFont(Font.font(12));
		btnArr[3].setPrefWidth(100);

		btnArr[4].setPrefWidth(100);
		btnArr[4].setFont(Font.font(12));

		btnArr[5].setPrefWidth(100);
		btnArr[5].setFont(Font.font(12));

		btnArr[6].setFont(Font.font(12));
		btnArr[6].setPrefWidth(100);

		// Action Listener on URL button
		btnArr[0].setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {

				String name = textFld[0].getText().trim();

				// validate input and process otherwise throw an exception
				if (name.length() > 0) {

					label[2].setStyle("-fx-text-fill: green;");

					if (name.startsWith("www.")) {

						name = "http://" + name;

					}

					try {
						txt = new UrlProcessor(name);

						txt.readFile();

						label[2].setText(name + " has been parsed successfully.");

					} catch (IOException ex) {

						label[2].setStyle("-fx-text-fill: red;");
						label[2].setText("Please enter valid Url ...");

					}

				} else {

					label[2].setStyle("-fx-text-fill: red;");
					label[2].setText("Please enter Url to parse...");

				}
			}
		});
		// Action Listener on file button
		btnArr[1].setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {

				label[2].setStyle("-fx-text-fill: green;");

				// file chooser dialog window
				FileChooser fileChooser = new FileChooser();
				configureFileChooser(fileChooser);

				// filter for .txt extension
				fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text", "*.txt"));
				fileChooser.setTitle("Open Resource File");

				File f = fileChooser.showOpenDialog(stage);

				if (f != null) {

					txt = new FileProcessor(f);

					try {

						txt.readFile();

						label[2].setText(f.getName() + " has been parsed successfully");

					} catch (FileNotFoundException fn) {

						label[2].setStyle("-fx-text-fill: red;");
						label[2].setText("File not found . ");

					} catch (IOException io) {

						label[2].setStyle("-fx-text-fill: red;");
						label[2].setText(io.getMessage());

					}

				} else {

					label[2].setStyle("-fx-text-fill: red;");
					label[2].setText("Invalid file try again...");
				}

			}
		});
		// Action on search btn DONE
		btnArr[2].setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {

				search = textFld[1].getText().trim();

				if (search.length() > 0 && txt != null) {

					status.setStyle("-fx-text-fill: green;");

					if (txt.contains(search)) {

						status.setText("Word \"" + search + "\" exists ");

					} else {

						status.setStyle("-fx-text-fill: red;");
						status.setText("Word \"" + search + "\" does not exist ");
					}

				} else {

					status.setStyle("-fx-text-fill: red;");
					status.setText("Array is empty...");

				}

			}
		});
		// Action on print status button
		btnArr[3].setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {

				if (txt != null) {

					status.setStyle("-fx-text-fill: green;");

					// the total number of elements in the array.
					// the number of occurrences of s in the array
					// the index of the 1st occurrence of s in the array.
					// an array of the indices of all occurrences of s in the
					// array.
					status.setText("The total number of elements in the array " + txt.count()
							+ "\nThe number of occurrences of \"" + search + "\" in the array "
							+ txt.countOccurrences(search) + "\nThe index of the 1st occurrence of \"" + search
							+ "\" in the array " + txt.getFirstIndex(search)
							+ "\nThe index of the last occurrence of \"" + search + "\" in the array "
							+ txt.getLastIndex(search) + "\nAn array of the indices of all occurrences of \"" + search
							+ "\" in the array " + txt.getAllIndicesToString(search));

				}

			}
		});

		// Action on delete all strings button
		btnArr[4].setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {

				String deleted = textFld[2].getText().trim();

				if (txt != null && deleted != null) {

					if (txt.contains(deleted)) {

						txt.delete(deleted);

						label[3].setStyle("-fx-text-fill: green;");
						label[3].setText("\"" + deleted + "\" has been deleted successfully.");

					} else {

						label[3].setStyle("-fx-text-fill: red;");
						label[3].setText("\"" + deleted + "\" has not been deleted .");

					}

				}

			}
		});
		// Action on delete at an index button
		btnArr[5].setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {

				String deleted = textFld[3].getText().trim();

				Integer indexOfDeleted = 0;

				try {

					indexOfDeleted = Integer.parseInt(deleted, 10);

					if (txt != null && (indexOfDeleted < txt.count() && indexOfDeleted > 0)) {

						txt.delete(indexOfDeleted);

						label[3].setStyle("-fx-text-fill: green;");
						label[3].setText("String at index " + indexOfDeleted + " has been deleted successfully.");

					} else {

						label[3].setStyle("-fx-text-fill: red;");
						label[3].setText("Invalid input...");

					}

				} catch (NumberFormatException e1) {

					label[3].setStyle("-fx-text-fill: red;");
					label[3].setText("Invalid input...");

				}

			}
		});
		// Action on exit button
		btnArr[6].setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				Platform.exit();
			}
		});

		// create horizontal boxes
		HBox horizBox = new HBox(5);
		HBox.setHgrow(textFld[0], Priority.ALWAYS);
		HBox.setMargin(btnArr[0], new Insets(0, 10, 0, 0));
		horizBox.getChildren().addAll(label[0], textFld[0], btnArr[1], btnArr[0]);

		HBox horizBox1 = new HBox(5);
		HBox.setHgrow(textFld[1], Priority.ALWAYS);
		HBox.setMargin(btnArr[2], new Insets(0, 10, 0, 0));
		horizBox1.getChildren().addAll(label[1], textFld[1], btnArr[2]);

		HBox horizBox2 = new HBox(5);
		HBox.setHgrow(textFld[2], Priority.ALWAYS);
		HBox.setMargin(btnArr[4], new Insets(0, 10, 0, 0));
		horizBox2.getChildren().addAll(label[4], textFld[2], btnArr[4]);

		HBox horizBox3 = new HBox(5);
		HBox.setHgrow(textFld[3], Priority.ALWAYS);
		HBox.setMargin(btnArr[5], new Insets(0, 10, 0, 0));
		horizBox3.getChildren().addAll(label[5], textFld[3], btnArr[5]);

		// Create the root node
		VBox root = new VBox(10);
		// Set the vertical spacing between children to 10px
		VBox.setMargin(label[2], new Insets(0, 0, 0, 10));
		VBox.setMargin(label[3], new Insets(0, 0, 0, 10));
		VBox.setMargin(btnArr[3], new Insets(0, 0, 0, 10));
		VBox.setMargin(btnArr[6], new Insets(0, 0, 10, 10));
		VBox.setMargin(horizBox, new Insets(10, 0, 0, 0));

		// Add children to the root node
		root.getChildren().addAll(horizBox, horizBox1, label[2], btnArr[3], status, horizBox2, horizBox3, label[3],
				btnArr[6]);

		Scene scene = new Scene(root, 600, 500);
		stage.setScene(scene);
		stage.setTitle("----Text Analyser----");
		stage.show();
	}

	// points to user's directory
	private static void configureFileChooser(final FileChooser fileChooser) {

		fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
	}

}
