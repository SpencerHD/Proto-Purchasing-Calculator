import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.text.DecimalFormat;
import java.util.Scanner;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXButton.ButtonType;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Nexteer extends Application {

	String yearOne;
	String yearTwo;
	String yearThree;
	double prevTotal;
	double totalAll;
	int count;
	int years;
	DecimalFormat two = new DecimalFormat("0.00");

	public Nexteer() {
		prevTotal = 0;
		totalAll = 0;
		yearOne = "";
		yearTwo = "";
		yearThree = "";
		count = 0;
		years = 0;
	}

	// Main method for launching
	public static void main(String[] args) {
		launch(args);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void start(final Stage primaryStage) throws InvocationTargetException {

		ObservableList<String> supplier = FXCollections.observableArrayList();
		ObservableList<String> operations = FXCollections.observableArrayList();

		Background background = new Background(new BackgroundFill(Color.WHITE, null, null));
		Background transparent = new Background(new BackgroundFill(Color.TRANSPARENT, null, null));
		Background buttonAdd = new Background(new BackgroundFill(Color.MEDIUMSEAGREEN, null, null));
		Background buttonDelete = new Background(new BackgroundFill(Color.LIGHTCORAL, null, null));
		Background buttonClear = new Background(new BackgroundFill(Color.LIGHTSLATEGRAY, null, null));
		Background buttonMerge = new Background(new BackgroundFill(Color.ROYALBLUE, null, null));

		Label popupHeader = new Label("Popup header goes here.");
		Label popupText = new Label("Popup text goes here.");
		JFXButton popupButton = new JFXButton("Okay");
		Line line = new Line();

		Label infoHeader = new Label("Info header goes here.");
		Label infoText = new Label("Info text goes here.");
		JFXButton infoButton = new JFXButton("Close");
		Line infoLine = new Line();

		JFXComboBox<String> supplierDrop = new JFXComboBox<String>(supplier);
		JFXComboBox<String> yearDrop = new JFXComboBox<String>();
		Text supp = new Text("Supplier:");
		Text yr = new Text("Current Year to Display:");
		Text desc = new Text("Description:");
		JFXTextArea itemName = new JFXTextArea("No item to display");
		Text yearTitle1 = new Text("20--");
		Text first = new Text("0.00");
		Text yearTitle2 = new Text("20--");
		Text second = new Text("0.00");
		Text qty = new Text("Qty:");
		Text prc = new Text("Price:");
		JFXTextField price = new JFXTextField();
		JFXTextField quantity = new JFXTextField();
		JFXButton calculate = new JFXButton("Add");
		JFXButton delete = new JFXButton("Delete");
		JFXButton edit = new JFXButton();
		TableView table = new TableView();
		Label placeholder = new Label("No contents to show");
		Text total = new Text("Total:");
		TextField totalDisplay = new TextField("$0.00");
		JFXCheckBox check = new JFXCheckBox("Expedited Delivery (plus 23%)");
		JFXButton clear = new JFXButton("Reset");

		Label title = new Label("Option Select");
		Label promptTitle = new Label("Are you sure you want to delete this supplier? This action cannot be undone.");
		JFXButton save = new JFXButton("Save");
		JFXButton edit2 = new JFXButton("Edit Supplier in Excel");
		JFXButton deleteFile = new JFXButton("Delete Supplier");

		Label dialogText = new Label("New Supplier:");
		JFXTextField entry = new JFXTextField();
		Label supplierName = new Label("Supplier Name");

		Line separate = new Line();
		JFXButton yes = new JFXButton("Yes");
		JFXButton no = new JFXButton("No");

		Label txtSelect = new Label("Select Item");
		JFXListView lstSelect = new JFXListView();
		JFXButton btnSelect = new JFXButton("Select");

		TableColumn<String, itemsList> column1 = new TableColumn<>("Item Name");
		column1.setCellValueFactory(new PropertyValueFactory<>("name"));
		column1.setPrefWidth(228);
		column1.setStyle("-fx-font: 16px \"Courier\";");
		column1.setResizable(false);
		TableColumn<String, itemsList> column2 = new TableColumn<>("Diff.");
		column2.setCellValueFactory(new PropertyValueFactory<>("diff"));
		column2.setPrefWidth(60);
		column2.setStyle("-fx-font: 16px \"Courier\";-fx-alignment: CENTER;");
		column2.setResizable(false);
		TableColumn<String, itemsList> column3 = new TableColumn<>("Qty.");
		column3.setCellValueFactory(new PropertyValueFactory<>("quant"));
		column3.setPrefWidth(50);
		column3.setStyle("-fx-font: 16px \"Courier\";-fx-alignment: CENTER;");
		column3.setResizable(false);
		TableColumn<Integer, itemsList> column4 = new TableColumn<>("Total");
		column4.setCellValueFactory(new PropertyValueFactory<>("tot"));
		column4.setStyle("-fx-font: 16px \"Courier\";-fx-alignment: CENTER;");
		column4.setResizable(false);

		line.setStartX(0);
		line.setEndX(260);
		popupHeader.setFont(Font.font("Courier", 16));
		popupText.setFont(Font.font("Courier", 14));
		popupText.setWrapText(true);
		popupText.setTextAlignment(TextAlignment.CENTER);
		popupButton.setFont(Font.font("Courier", 14));
		popupButton.setOnMouseEntered(e -> popupButton.setOpacity(0.7));
		popupButton.setOnMouseExited(e -> popupButton.setOpacity(1));

		infoLine.setStartX(0);
		infoLine.setEndX(260);
		infoHeader.setFont(Font.font("Courier", 16));
		infoText.setFont(Font.font("Courier", 14));
		infoText.setWrapText(true);
		infoText.setTextAlignment(TextAlignment.LEFT);
		infoButton.setFont(Font.font("Courier", 14));
		infoButton.setOnMouseEntered(e -> infoButton.setOpacity(0.7));
		infoButton.setOnMouseExited(e -> infoButton.setOpacity(1));

		supplierDrop.setPrefHeight(30);
		supplierDrop.setPrefWidth(290);
		supplierDrop.setStyle("-fx-font: 14px \"Courier\";");
		supplierDrop.setTranslateX(140);
		supplierDrop.setTranslateY(15);

		yearDrop.setPrefHeight(30);
		yearDrop.setPrefWidth(170);
		yearDrop.setStyle("-fx-font: 14px \"Courier\";");
		yearDrop.setTranslateX(260);
		yearDrop.setTranslateY(55);

		supp.setFont(Font.font("Courier", 16));
		supp.setX(20);
		supp.setY(36);

		yr.setFont(Font.font("Courier", 16));
		yr.setX(20);
		yr.setY(76);

		desc.setFont(Font.font("Courier", 16));
		desc.setX(20);
		desc.setY(116);

		itemName.setFont(Font.font("Courier", 16));
		itemName.setBackground(Background.EMPTY);
		itemName.setWrapText(true);
		itemName.setEditable(false);
		itemName.setPrefWidth(290);
		itemName.setPrefHeight(90);
		itemName.setTranslateX(140);
		itemName.setTranslateY(100);

		yearTitle1.setFont(Font.font("Courier", FontPosture.ITALIC, 14));
		yearTitle1.setX(30);
		yearTitle1.setY(245);

		first.setFont(Font.font("Courier", 16));
		first.setX(30);
		first.setY(265);

		yearTitle2.setFont(Font.font("Courier", FontPosture.ITALIC, 14));
		yearTitle2.setX(90);
		yearTitle2.setY(245);

		second.setFont(Font.font("Courier", 16));
		second.setX(90);
		second.setY(265);

		quantity.setFont(Font.font("Courier", 14));
		quantity.setAlignment(Pos.CENTER);
		quantity.setPrefWidth(60);
		quantity.setTranslateX(370);
		quantity.setTranslateY(200);

		price.setFont(Font.font("Courier", 14));
		price.setAlignment(Pos.CENTER);
		price.setPrefWidth(80);
		price.setTranslateX(235);
		price.setTranslateY(200);

		prc.setFont(Font.font("Courier", 16));
		prc.setX(185);
		prc.setY(221);

		qty.setFont(Font.font("Courier", 16));
		qty.setX(330);
		qty.setY(220);

		calculate.setFont(Font.font("Courier", 14));
		calculate.setBackground(buttonAdd);
		calculate.setTextFill(Color.WHITE);
		calculate.setOnMouseEntered(e -> calculate.setButtonType(ButtonType.RAISED));
		calculate.setOnMouseExited(e -> calculate.setButtonType(ButtonType.FLAT));
		calculate.setPrefWidth(70);
		calculate.setAlignment(Pos.CENTER);
		calculate.setTranslateX(360);
		calculate.setTranslateY(240);

		delete.setFont(Font.font("Courier", 14));
		delete.setBackground(buttonDelete);
		delete.setTextFill(Color.WHITE);
		delete.setOnMouseEntered(e -> delete.setButtonType(ButtonType.RAISED));
		delete.setOnMouseExited(e -> delete.setButtonType(ButtonType.FLAT));
		delete.setPrefWidth(70);
		delete.setAlignment(Pos.CENTER);
		delete.setTranslateX(280);
		delete.setTranslateY(240);

		edit.setFont(Font.font("Courier", 14));
		edit.setBackground(buttonMerge);
		edit.setTextFill(Color.WHITE);
		edit.setOnMouseEntered(e -> edit.setButtonType(ButtonType.RAISED));
		edit.setOnMouseExited(e -> edit.setButtonType(ButtonType.FLAT));
		edit.setWrapText(true);
		edit.setAlignment(Pos.CENTER);
		edit.setPrefWidth(110);
		edit.setPrefHeight(40);
		edit.setTranslateX(20);
		edit.setTranslateY(150);
		edit.setVisible(false);

		clear.setFont(Font.font("Courier", 14));
		clear.setBackground(buttonClear);
		clear.setTextFill(Color.WHITE);
		clear.setOnMouseEntered(e -> clear.setButtonType(ButtonType.RAISED));
		clear.setOnMouseExited(e -> clear.setButtonType(ButtonType.FLAT));
		clear.setPrefWidth(70);
		clear.setAlignment(Pos.CENTER);
		clear.setTranslateX(200);
		clear.setTranslateY(240);

		placeholder.setFont(Font.font("Courier", 16));
		table.setPlaceholder(placeholder);
		table.setPrefWidth(420);
		table.setPrefHeight(270);
		table.setTranslateX(20);
		table.setTranslateY(280);

		check.setFont(Font.font("Courier", 16));
		check.setTranslateX(30);
		check.setTranslateY(568);

		total.setFont(Font.font("Courier", 18));
		total.setX(320);
		total.setY(585);

		totalDisplay.setFont(Font.font("Courier", 18));
		totalDisplay.setBackground(Background.EMPTY);
		totalDisplay.setEditable(false);
		totalDisplay.setAlignment(Pos.CENTER_LEFT);
		totalDisplay.setPrefWidth(100);
		totalDisplay.setTranslateX(360);
		totalDisplay.setTranslateY(560);

		title.setAlignment(Pos.TOP_CENTER);
		title.setFont(Font.font("Courier", 16));
		title.setUnderline(true);
		edit2.setAlignment(Pos.BOTTOM_CENTER);
		edit2.setFont(Font.font("Courier", 14));
		edit2.setOnMouseEntered(e -> edit2.setOpacity(0.7));
		edit2.setOnMouseExited(e -> edit2.setOpacity(1));
		deleteFile.setAlignment(Pos.BOTTOM_CENTER);
		deleteFile.setFont(Font.font("Courier", 14));
		deleteFile.setOnMouseEntered(e -> deleteFile.setOpacity(0.7));
		deleteFile.setOnMouseExited(e -> deleteFile.setOpacity(1));

		dialogText.setAlignment(Pos.TOP_CENTER);
		dialogText.setFont(Font.font("Courier", 16));
		dialogText.setUnderline(true);
		entry.setFont(Font.font("Courier", 16));
		entry.setAlignment(Pos.CENTER);
		save.setFont(Font.font("Courier", 16));
		save.setOnMouseEntered(e -> save.setOpacity(0.7));
		save.setOnMouseExited(e -> save.setOpacity(1));

		promptTitle.setFont(Font.font("Courier", FontWeight.BOLD, 16));
		promptTitle.setWrapText(true);
		promptTitle.setTextAlignment(TextAlignment.CENTER);
		separate.setStartY(0);
		separate.setEndY(30);
		yes.setFont(Font.font("Courier", 16));
		yes.setBackground(transparent);
		yes.setOnMouseEntered(e -> yes.setOpacity(0.7));
		yes.setOnMouseExited(e -> yes.setOpacity(1));
		no.setFont(Font.font("Courier", 16));
		no.setBackground(transparent);
		no.setOnMouseEntered(e -> no.setOpacity(0.7));
		no.setOnMouseExited(e -> no.setOpacity(1));

		txtSelect.setAlignment(Pos.TOP_CENTER);
		txtSelect.setFont(Font.font("Courier", 16));
		txtSelect.setUnderline(true);
		lstSelect.setMinHeight(120);
		btnSelect.setFont(Font.font("Courier", 16));
		btnSelect.setOnMouseEntered(e -> btnSelect.setOpacity(0.7));
		btnSelect.setOnMouseExited(e -> btnSelect.setOpacity(1));

		// Popup for alerts and info
		final Stage popup = new Stage();
		popup.setTitle("Popup");
		popup.setResizable(false);
		popup.setAlwaysOnTop(true);
		VBox contents = new VBox(10);
		contents.setPadding(new Insets(5, 5, 5, 5));
		contents.setBackground(background);
		contents.setAlignment(Pos.CENTER);
		contents.getChildren().addAll(popupHeader, line, popupText, popupButton);
		Scene popupScene = new Scene(contents, 300, 150);
		popup.setScene(popupScene);
		popup.initOwner(primaryStage);
		popup.initModality(Modality.WINDOW_MODAL);

		final Stage information = new Stage();
		information.setTitle("Information");
		information.setResizable(false);
		information.setAlwaysOnTop(true);
		VBox infoContents = new VBox(10);
		infoContents.setPadding(new Insets(10, 10, 10, 10));
		infoContents.setBackground(background);
		infoContents.setAlignment(Pos.CENTER);
		infoContents.getChildren().addAll(infoHeader, infoLine, infoText, infoButton);
		Scene infoScene = new Scene(infoContents, 300, 250);
		information.setScene(infoScene);
		information.initOwner(primaryStage);
		information.initModality(Modality.WINDOW_MODAL);

		final Stage askUser = new Stage();
		askUser.setTitle("Option Select");
		VBox askVBox = new VBox(20);
		askVBox.setAlignment(Pos.CENTER);
		askVBox.getChildren().addAll(title, edit2, deleteFile);
		askVBox.setBackground(background);
		Scene askScene = new Scene(askVBox, 300, 150);
		askUser.setScene(askScene);
		askUser.initOwner(primaryStage);
		askUser.initModality(Modality.WINDOW_MODAL);

		final Stage confirm = new Stage();
		confirm.setTitle("Confirm Delete");
		confirm.setResizable(false);
		VBox confirmBox = new VBox(20);
		confirmBox.setPadding(new Insets(10, 10, 10, 10));
		confirmBox.setBackground(background);
		HBox buttons = new HBox(5, yes, separate, no);
		buttons.setAlignment(Pos.CENTER);
		confirmBox.setAlignment(Pos.CENTER);
		confirmBox.getChildren().addAll(promptTitle, buttons);
		Scene confirmScene = new Scene(confirmBox, 300, 150);
		confirm.setScene(confirmScene);
		confirm.initOwner(primaryStage);
		confirm.initOwner(askUser);
		confirm.initModality(Modality.WINDOW_MODAL);

		final Stage dialog = new Stage();
		VBox dialogVbox = new VBox(5);
		dialogVbox.setPadding(new Insets(10, 10, 10, 10));
		dialogVbox.setAlignment(Pos.CENTER);
		dialogVbox.getChildren().addAll(dialogText, entry, supplierName, save);
		dialogVbox.setBackground(background);
		Scene dialogScene = new Scene(dialogVbox, 300, 150);
		dialog.setScene(dialogScene);
		dialog.initOwner(primaryStage);
		dialog.initModality(Modality.WINDOW_MODAL);

		final Stage itemSelect = new Stage();
		itemSelect.setTitle("Multiple Items");
		VBox selectVBox = new VBox(5);
		selectVBox.setPadding(new Insets(10, 10, 10, 10));
		selectVBox.setAlignment(Pos.CENTER);
		selectVBox.getChildren().addAll(txtSelect, lstSelect, btnSelect);
		selectVBox.setBackground(background);
		Scene selectScene = new Scene(selectVBox, 500, 200);
		itemSelect.setScene(selectScene);
		itemSelect.initOwner(primaryStage);
		itemSelect.initModality(Modality.WINDOW_MODAL);

		ObservableList<String> files = FXCollections.observableArrayList();
		File fileOpen = new File("files.txt");
		try (Scanner scanner = new Scanner(fileOpen)) {
			while (scanner.hasNextLine()) {
				files.add(scanner.nextLine());
			}
			scanner.close();
		} catch (FileNotFoundException e2) {
			popupHeader.setText("File Not Found");
			popup.setTitle("No File");
			popupText.setText("Files.txt was not found. Please see administrator.");
			popup.show();
		}
		String op = "";
		for (int i = 0; i < files.size(); i++) {
			op = files.get(i);
			String[] split = op.split("\t");
			try {
				supplierDrop.getItems().add(split[0]);
			} catch (ArrayIndexOutOfBoundsException e) {

			}
		}

		popupButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				popup.close();
			}
		});

		infoButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				information.close();
			}
		});

		supplierDrop.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				edit.setVisible(true);
				yearDrop.getItems().clear();
				try {
					first.setText("0.00");
					second.setText("0.00");
					table.getItems().clear();
					price.clear();
					quantity.clear();
					itemName.setText("No item to display");
					totalDisplay.setText("$0.00");
					check.setSelected(false);
					prevTotal = 0;
					totalAll = 0;
					count = 0;
					years = 0;
				} catch (NullPointerException e) {

				}
				try {
					if (supplierDrop.getValue().equals("<Create New Supplier>")) {
						edit.setText("Create File");
					} else {
						edit.setText("Modify File");
						operations.clear();
						String op = "";
						File fileOpen = new File("files.txt");
						try (Scanner scanner = new Scanner(fileOpen)) {
							while (scanner.hasNextLine()) {
								files.add(scanner.nextLine());
							}
						} catch (FileNotFoundException e2) {
							popupHeader.setText("File Not Found");
							popup.setTitle("No File");
							popupText.setText("Files.txt was not found. Please see administrator.");
							popup.show();
						}
						for (int i = 0; i < files.size(); i++) {
							op = files.get(i);
							String[] split = op.split("\t");
							if (supplierDrop.getValue().equals(split[0])) {
								File file = new File(split[1]);
								try (Scanner scanner = new Scanner(file)) {
									while (scanner.hasNextLine()) {
										operations.add(scanner.nextLine());
									}
									scanner.close();
								} catch (FileNotFoundException e) {
									popupHeader.setText("File Not Found");
									popup.setTitle("No File");
									popupText.setText("Files.txt was not found. Please see administrator.");
									popup.show();
								}
							}
						}
						String op2 = "";
						for (int i = 0; i < 1; i++) {
							op2 = operations.get(i);
							String[] split = op2.split("\t");
							for (int j = 2; j < split.length; j++) {
								try {
									if (!split[j].equals(split[j - 1])) {
										yearDrop.getItems().add(split[j]);
										years++;
									}
								} catch (ArrayIndexOutOfBoundsException e) {

								}
								count++;
							}
						}
					}
				} catch (NullPointerException e) {

				}
				first.setText("0.00");
				second.setText("0.00");
			}
		});

		yearDrop.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				try {
					yearTitle1.setText(String.valueOf(Integer.parseInt(yearDrop.getValue()) - 1));
					yearTitle2.setText(yearDrop.getValue());
				} catch (NullPointerException | NumberFormatException e) {

				}
			}
		});

		edit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (!supplierDrop.getValue().equals("")) {
					if (supplierDrop.getValue().equals("<Create New Supplier>")) {
						dialog.show();
					} else {
						askUser.show();
					}
				} else {
					popupHeader.setText("No File Selected");
					popup.setTitle("No File");
					popupText.setText("Select a supplier from the dropdown to edit.");
					popup.show();
				}
			}
		});

		edit2.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				String op = "";
				File fileOpen = new File("files.txt");
				try (Scanner scanner = new Scanner(fileOpen)) {
					while (scanner.hasNextLine()) {
						files.add(scanner.nextLine());
					}
					scanner.close();
				} catch (FileNotFoundException e2) {
					popupHeader.setText("File Not Found");
					popup.setTitle("No File");
					popupText.setText("Files.txt was not found. Please see administrator.");
					popup.show();
				}
				for (int i = 0; i < files.size(); i++) {
					op = files.get(i);
					String[] split = op.split("\t");
					if (supplierDrop.getValue().equals(split[0])) {
						try {
							Runtime.getRuntime().exec("cmd /c start excel.exe " + split[1]);
						} catch (IOException e) {
							popupHeader.setText("Error Opening File");
							popup.setTitle("No File");
							popupText.setText("Could not open the file correctly. See administrator for help.");
							popup.show();
						}
						break;
					}
				}
				askUser.close();
				popupHeader.setText("Opening Excel");
				popup.setTitle("Loading Screen");
				popupText.setText("Please wait while Excel loads. This should only take a few seconds.");
				popup.show();
			}
		});

		save.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (entry.getText().equals("")) {
					popupHeader.setText("Entry is Blank");
					popup.setTitle("Invalid Entry");
					popupText.setText("Please make sure entry is not blank before adding.");
					popup.show();
				} else {
					String supplierName = entry.getText();
					String[] suppSplit = supplierName.split(" ");
					dialog.close();
					ObservableList<String> files = FXCollections.observableArrayList();
					File fileOpen = new File("files.txt");
					try (Scanner scanner = new Scanner(fileOpen)) {
						while (scanner.hasNextLine()) {
							files.add(scanner.nextLine());
						}
						scanner.close();
					} catch (FileNotFoundException e2) {
						popupHeader.setText("File Not Found");
						popup.setTitle("No File");
						popupText.setText("Files.txt was not found. Please see administrator.");
						popup.show();
					}
					String fileName = suppSplit[0].toLowerCase() + ".txt";
					File file = new File(fileName);
					if (!file.exists()) {
						for (int i = 0; i < files.size(); i++) {
							if (!files.contains(supplierName + "\t" + fileName)) {
								files.add(supplierName + "\t" + fileName);
							} else if (files.contains("<Create New Supplier>")) {
								files.remove("<Create New Supplier>");
							}
						}
						files.add("<Create New Supplier>");
						PrintWriter newFile;
						try {
							newFile = new PrintWriter(file);
							newFile.print("Op\tDescription\tYear1\tYear2");
							newFile.close();
						} catch (FileNotFoundException e1) {
							popupHeader.setText("File Not Found");
							popup.setTitle("No File");
							popupText.setText("New file was not found. Please see administrator.");
							popup.show();
						}
						File fileList = new File("files.txt");
						PrintWriter output;
						try {
							output = new PrintWriter(fileList);
							for (int i = 0; i < files.size(); i++) {
								output.println(files.get(i));
							}
							output.close();
						} catch (FileNotFoundException e1) {
							popupHeader.setText("File Not Found");
							popup.setTitle("No File");
							popupText.setText("Files.txt was not found. Please see administrator.");
							popup.show();
						}
						popupHeader.setText("File Created");
						popup.setTitle("File Status");
						popupText.setText("Supplier created successfully!");
						popup.show();
						supplierDrop.getItems().clear();
						String op = "";
						for (int i = 0; i < files.size(); i++) {
							op = files.get(i);
							String[] split = op.split("\t");
							try {
								supplierDrop.getItems().add(split[0]);
							} catch (ArrayIndexOutOfBoundsException e) {

							}
						}
					} else {
						popupHeader.setText("Duplicate File Name");
						popup.setTitle("File Status");
						popupText.setText("A potential duplicate file has been detected. Please use another name.");
						popup.show();
					}

				}
				entry.clear();
			}
		});

		deleteFile.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				confirm.show();
			}
		});

		yes.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (!supplierDrop.getValue().equals("<Create New Supplier>")) {
					String op = "";
					ObservableList<String> files = FXCollections.observableArrayList();
					File fileOpen = new File("files.txt");
					try (Scanner scanner = new Scanner(fileOpen)) {
						while (scanner.hasNextLine()) {
							files.add(scanner.nextLine());
						}
						scanner.close();
					} catch (FileNotFoundException e2) {
						popupHeader.setText("File Not Found");
						popup.setTitle("No File");
						popupText.setText("Files.txt was not found. Please see administrator.");
						popup.show();
					}

					boolean result;
					for (int i = 0; i < files.size(); i++) {
						op = files.get(i);
						String[] split = op.split("\t");
						if (split[0].equals(supplierDrop.getValue()) && !split[0].equals("<Create New Supplier>")) {
							File file = new File(split[1]);
							result = file.delete();
							if (result) {
								files.remove(files.get(i));
								popupHeader.setText("File Deleted");
								popup.setTitle("File Status");
								popupText.setText(split[1] + " deleted successfully.");
								popup.show();
							} else {
								popupHeader.setText("File Not Deleted");
								popup.setTitle("File Status");
								popupText.setText(split[1] + " was not deleted. Please see administrator.");
								popup.show();
							}
						}
					}
					File fileList = new File("files.txt");
					PrintWriter output;
					try {
						output = new PrintWriter(fileList);
						for (int i = 0; i < files.size(); i++) {
							output.println(files.get(i));
						}
						output.close();
					} catch (FileNotFoundException e1) {
						popupHeader.setText("File Not Found");
						popup.setTitle("No File");
						popupText.setText("Entry was not found. Please see administrator.");
						popup.show();
					}
					supplierDrop.getItems().clear();
					for (int i = 0; i < files.size(); i++) {
						op = files.get(i);
						String[] split = op.split("\t");
						try {
							supplierDrop.getItems().add(split[0]);
						} catch (ArrayIndexOutOfBoundsException e) {

						}
					}
					confirm.close();
					askUser.close();
				} else {
					popupHeader.setText("Cannot Remove Item");
					popup.setTitle("Invalid Request");
					popupText.setText("This item cannot be deleted.");
					popup.show();
				}
			}
		});

		no.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				confirm.close();
			}
		});

		price.setOnKeyTyped(event -> {
			int maxCharacters = 7;
			if (price.getText().length() > maxCharacters)
				event.consume();
		});

		quantity.setOnKeyTyped(event -> {
			int maxCharacters = 3;
			if (quantity.getText().length() > maxCharacters)
				event.consume();
		});

		quantity.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent ke) {
				if (ke.getCode().equals(KeyCode.ENTER)) {
					try {
						String diff = "";
						String tot = "";
						double runningTotal = 0;
						diff = String.valueOf(
								two.format(Double.parseDouble(first.getText()) - Double.parseDouble(second.getText())));
						tot = String
								.valueOf(two.format(Double.parseDouble(diff) * Double.parseDouble(quantity.getText())));
						if (Double.parseDouble(quantity.getText()) > 0 && Double.parseDouble(quantity.getText()) <= 9999
								&& Double.parseDouble(diff) > 0 && quantity.getText().matches("[0-9]+")) {
							table.getItems().add(new itemsList(itemName.getText(), diff, quantity.getText(), tot));
							runningTotal = Double.parseDouble(diff) * Double.parseDouble(quantity.getText());
							totalAll = totalAll + runningTotal;
							if (totalAll > 0 && !totalDisplay.getText().equals(null) && !check.isSelected()) {
								totalDisplay.setText("$" + two.format(totalAll));
							} else if (totalAll > 0 && !totalDisplay.getText().equals(null) && check.isSelected()) {
								prevTotal = 0;
								prevTotal = totalAll + (totalAll * .23);
								totalDisplay.setText("$" + two.format(prevTotal));
							} else {
								totalDisplay.setText("$0.00");
							}
						} else if (Double.parseDouble(quantity.getText()) < 0) {
							popupHeader.setText("Invalid Entry");
							popup.setTitle("Invalid Entry");
							popupText.setText("Please check that all values are correct before adding to list.");
							popup.show();
							quantity.requestFocus();
							quantity.selectAll();
						} else if (Double.parseDouble(quantity.getText()) > 9999) {
							popupHeader.setText("Max Number Exceeded");
							popup.setTitle("Invalid Entry");
							popupText.setText("Max quantity number reached.");
							popup.show();
							quantity.requestFocus();
							quantity.selectAll();
						} else {
							popupHeader.setText("Invalid Entry");
							popup.setTitle("Invalid Entry");
							popupText.setText("Please check that all values are correct before adding to list.");
							popup.show();
							quantity.requestFocus();
							quantity.selectAll();
						}
					} catch (NullPointerException e) {

					} catch (NumberFormatException e) {
						popupHeader.setText("Invalid Entry");
						popup.setTitle("Invalid Entry");
						popupText.setText("Please check that all values are correct before adding to list.");
						popup.show();
						quantity.requestFocus();
						quantity.selectAll();
					}
				}
			}
		});

		price.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
				String op = "";
				String line1 = "";
				int matches = 0;
				lstSelect.getItems().clear();
				try {
					for (int i = 0; i < operations.size(); i++) {
						op = operations.get(i);
						line1 = operations.get(0);
						String[] split = op.split("\t");
						String[] split1 = line1.split("\t");
						for (int j = 0; j < split.length; j++) {
							try {
								op = split[j];
								if (price.getText().equals(op) && split1[j].equals(yearDrop.getValue())) {
									if (yearDrop.getValue().equals(split1[2])) {
										itemName.setText("No previous year to calculate");
										first.setText("N/A");
										second.setText(op);
									} else {
										itemName.setText(split[1] + " - " + split[0]);
										first.setText(split[j - (count / years)]);
										second.setText(op);
										matches++;
										if (matches > 2 && !(count > years)) {
											itemName.setText("Multiple values found - select an item");
											for (int m = 0; m < lstSelect.getItems().size(); m++) {
												if (split[1].equals(lstSelect.getItems().get(m))) {
													lstSelect.getItems().remove(m);
												}
											}
											lstSelect.getItems().add(split[1]);
											itemSelect.show();
										}
									}
								} else if (price.getText().equals("")) {
									itemName.setText("No item to display");
									first.setText("0.00");
									second.setText("0.00");
								}
							} catch (ArrayIndexOutOfBoundsException e) {
							}
						}
					}
				} catch (NullPointerException | ArrayIndexOutOfBoundsException e) {

				}
			}
		});

		btnSelect.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				itemName.setText((String) lstSelect.getSelectionModel().getSelectedItem());
				itemSelect.close();
				lstSelect.getItems().clear();
			}
		});

		calculate.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					String diff = "";
					String tot = "";
					double runningTotal = 0;
					diff = String.valueOf(
							two.format(Double.parseDouble(first.getText()) - Double.parseDouble(second.getText())));
					tot = String.valueOf(two.format(Double.parseDouble(diff) * Double.parseDouble(quantity.getText())));
					if (Double.parseDouble(quantity.getText()) > 0 && Double.parseDouble(quantity.getText()) <= 9999
							&& Double.parseDouble(diff) > 0 && quantity.getText().matches("[0-9]+")) {
						table.getItems().add(new itemsList(itemName.getText(), diff, quantity.getText(), tot));
						runningTotal = Double.parseDouble(diff) * Double.parseDouble(quantity.getText());
						totalAll = totalAll + runningTotal;
						if (totalAll > 0 && !totalDisplay.getText().equals(null) && !check.isSelected()) {
							totalDisplay.setText("$" + two.format(totalAll));
						} else if (totalAll > 0 && !totalDisplay.getText().equals(null) && check.isSelected()) {
							prevTotal = 0;
							prevTotal = totalAll + (totalAll * .23);
							totalDisplay.setText("$" + two.format(prevTotal));
						} else {
							totalDisplay.setText("$0.00");
						}
					} else if (Double.parseDouble(quantity.getText()) < 0) {
						popupHeader.setText("Invalid Entry");
						popup.setTitle("Invalid Entry");
						popupText.setText("Please check that all values are correct before adding to list.");
						popup.show();
						quantity.requestFocus();
						quantity.selectAll();
					} else if (Double.parseDouble(quantity.getText()) > 9999) {
						popupHeader.setText("Max Number Exceeded");
						popup.setTitle("Invalid Entry");
						popupText.setText("Max quantity number reached.");
						popup.show();
						quantity.requestFocus();
						quantity.selectAll();
					} else {
						popupHeader.setText("Invalid Entry");
						popup.setTitle("Invalid Entry");
						popupText.setText("Please check that all values are correct before adding to list.");
						popup.show();
						quantity.requestFocus();
						quantity.selectAll();
					}
				} catch (NullPointerException e) {

				} catch (NumberFormatException e) {
					popupHeader.setText("Invalid Entry");
					popup.setTitle("Invalid Entry");
					popupText.setText("Please check that all values are correct before adding to list.");
					popup.show();
					quantity.requestFocus();
					quantity.selectAll();
				}
			}
		});

		table.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent ke) {
				try {
					itemsList remove = (itemsList) table.getSelectionModel().getSelectedItem();
					if (totalAll >= 0 && !totalDisplay.getText().equals(null) && !check.isSelected()) {
						totalAll = totalAll - Double.parseDouble(remove.getTot());
						totalDisplay.setText("$" + two.format(totalAll));
					} else if (totalAll >= 0 && !totalDisplay.getText().equals(null) && check.isSelected()) {
						prevTotal = 0;
						totalAll = totalAll - Double.parseDouble(remove.getTot());
						prevTotal = totalAll + (totalAll * .23);
						totalDisplay.setText("$" + two.format(prevTotal));
						prevTotal = 0;
					} else if (totalAll == 0) {
						totalDisplay.setText("$0.00");
					} else {
						totalDisplay.setText("$0.00");
					}
				} catch (NullPointerException e) {

				} catch (NumberFormatException e) {

				}
				Object selectedItem = table.getSelectionModel().getSelectedItem();
				table.getItems().remove(selectedItem);
			}
		});

		table.setRowFactory(tv -> {
			TableRow row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (event.getClickCount() == 2 && (!row.isEmpty())) {
					itemsList nameVal = (itemsList) table.getSelectionModel().getSelectedItem();
					itemsList diffVal = (itemsList) table.getSelectionModel().getSelectedItem();
					itemsList quantVal = (itemsList) table.getSelectionModel().getSelectedItem();
					itemsList totVal = (itemsList) table.getSelectionModel().getSelectedItem();
					infoHeader.setText("Item Information");
					information.setTitle("Information");
					infoText.setText("Item Name: " + nameVal.getName() + "\nPrice Difference: $" + diffVal.getDiff()
							+ "\nQuantity: " + quantVal.getQuant() + "\nTotal: $" + totVal.getTot());
					information.show();
				}
			});
			return row;
		});

		delete.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					itemsList remove = (itemsList) table.getSelectionModel().getSelectedItem();
					if (totalAll >= 0 && !totalDisplay.getText().equals(null) && !check.isSelected()) {
						totalAll = totalAll - Double.parseDouble(remove.getTot());
						totalDisplay.setText("$" + two.format(totalAll));
					} else if (totalAll >= 0 && !totalDisplay.getText().equals(null) && check.isSelected()) {
						prevTotal = 0;
						totalAll = totalAll - Double.parseDouble(remove.getTot());
						prevTotal = totalAll + (totalAll * .23);
						totalDisplay.setText("$" + two.format(prevTotal));
						prevTotal = 0;
					} else if (totalAll == 0) {
						totalDisplay.setText("$0.00");
					} else {
						totalDisplay.setText("$0.00");
					}
				} catch (NullPointerException e) {

				} catch (NumberFormatException e) {

				}
				Object selectedItem = table.getSelectionModel().getSelectedItem();
				table.getItems().remove(selectedItem);
			}
		});

		clear.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					first.setText("0.00");
					second.setText("0.00");
					table.getItems().clear();
					price.clear();
					quantity.clear();
					check.setSelected(false);
					itemName.setText("No item to display");
					totalDisplay.setText("$0.00");
					prevTotal = 0;
					totalAll = 0;
				} catch (NullPointerException e) {

				}
			}
		});

		check.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if (newValue) {
					prevTotal = 0;
					prevTotal = totalAll + (totalAll * .23);
					totalDisplay.setText("$" + two.format(prevTotal));
				} else {
					prevTotal = 0;
					totalDisplay.setText("$" + two.format(totalAll));
				}
			}
		});

		table.getColumns().addAll(column1, column2, column3, column4);

		Pane root = new Pane();
		root.getChildren().addAll(supp, yr, desc, supplierDrop, yearDrop, edit, itemName, yearTitle1, first, yearTitle2,
				second, price, quantity, prc, qty, clear, delete, calculate, table, check, total, totalDisplay);
		root.setBackground(background);
		Scene scene = new Scene(root, 450, 600);
		primaryStage.setResizable(false);
		primaryStage.setTitle("Nexteer Purchasing Calculator");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
