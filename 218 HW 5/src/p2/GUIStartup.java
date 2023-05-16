package p2;

import javafx.application.Application;
import p2.Demo;
import javafx.stage.Stage;
import java.io.File;
import java.io.PrintWriter;
import java.util.LinkedList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

public class GUIStartup extends Application {
	
	//The GUI defaults its searching options to the Blowing in The Wind lyrics
	public LinkedList<LinkedList<String>> lyricsList = Demo.getList("src/blowingInTheWind.txt");
	
	public int randNum(int min, int max) {
	    return (int) ((Math.random() * (max - min)) + min);
	}
	
	public void changeFile(File file) {
	   lyricsList = Demo.getList(file);
	   //endOfChangeFile
	}
	
	public void refreshTextArea(TextArea ta, String word, int key) {
		LinkedList<String> wordList = null;
		for(int i = 0; i < lyricsList.size(); i++) {
			if(lyricsList.get(i).contains(word)) {
				if(lyricsList.get(i).get(0).equals(word)){
					wordList = lyricsList.get(i);
					break;
				}
			}
		}
		if(wordList == null) {
			ta.setText("Word Does Not Exist"); 
			return;
		}
		String[] randomWords = wordList.toArray(new String[wordList.size()]);
		ta.setText(randomWords[0]);
		for(int i = 1; key > randomWords.length ? i < randomWords.length : i <= key - 1; i++) {
			ta.appendText(" " + randomWords[randNum(1, randomWords.length - 1)]);
		}
		//end of refreshTextArea
	}

	@Override
	public void start(Stage stage) throws Exception {
		//Initializing the pane, menu and textarea for displaying
		StackPane mainPane = new StackPane();
		MenuBar menu = new MenuBar();
		Menu menuDropDown = new Menu("File Options");
		MenuItem openBtn = new MenuItem("Open");
		MenuItem saveBtn = new MenuItem("Save");
		TextArea ta = new TextArea();
		
		//Button Handling
		openBtn.setOnAction(e -> {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Choose a .txt File to open");
			fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("txt", "*.txt"));
			changeFile(fileChooser.showOpenDialog(stage));
		});
		
		saveBtn.setOnAction(e -> {
			try{
				PrintWriter pw = new PrintWriter("C:\\JavaFX Workspace\\CSE218HW04Redo\\output\\output.txt");
				pw.println(ta.getText());
				pw.close();
			}
			catch(Exception ex) {
				ex.printStackTrace();
			}
		});
		
		//Finishing up the menu 
		menuDropDown.getItems().addAll(openBtn, saveBtn);
		menu.getMenus().add(menuDropDown);
		
		VBox MainBox = new VBox(menu);
		MainBox.prefHeight(510);
		
		ta.setMinHeight(510 - 85);
		ta.setMaxHeight(510 - 85);
		
		MainBox.setAlignment(Pos.TOP_CENTER);
		
		//Initializing the HBox to contain the user inputs
		HBox inputBox = new HBox();
		inputBox.setSpacing(25);
		inputBox.setAlignment(Pos.CENTER);
		
		//Initializing the VBox to contain the user inputs for number of nodes that will be shown
		VBox numVBox = new VBox();
		numVBox.setAlignment(Pos.CENTER);
		Label maxNumLbl = new Label("Enter max num of displayed nodes:");
		TextField numField = new TextField();
		numField.setPrefWidth(50);
		numField.setMaxWidth(50);
		numField.setAlignment(Pos.CENTER);
		
		//Initializing the VBox to contain the user inputs for the key word that is beind used
		VBox wordVBox = new VBox();
		wordVBox.setAlignment(Pos.CENTER);
		Label wordSearchLbl = new Label("Enter Word to search for:");
		TextField wordField = new TextField();
		wordField.setPrefWidth(150);
		wordField.setMaxWidth(150);
		
		//Button Event Handling
		wordField.setOnAction(e -> {
			if(numField.getText().isBlank() || wordField.getText().isBlank()) {
				return;
			}
			refreshTextArea(ta, wordField.getText(), Integer.valueOf(numField.getText()));
		});
		
		numField.setOnAction(e -> {
			if(numField.getText().isBlank() || wordField.getText().isBlank()) {
				return;
			}
			refreshTextArea(ta, wordField.getText(), Integer.valueOf(numField.getText()));
		});
		
		//Adding everything to each box
		numVBox.getChildren().addAll(maxNumLbl, numField);
		wordVBox.getChildren().addAll(wordSearchLbl, wordField);
		inputBox.getChildren().addAll(numVBox, wordVBox);
		
		MainBox.setSpacing(10);
		MainBox.getChildren().addAll(inputBox, ta);
		
		mainPane.getChildren().add(MainBox);
		
		ta.setEditable(false);
		ta.setPadding(new Insets(10,10,10,10));
		
		//Initializing the GUI
		Scene scene = new Scene(mainPane, 510, 510);
		stage.setTitle("Baby List Displayer");
		scene.setFill(Color.GREY); //Color changing wont work.
		stage.setResizable(false);
		stage.setScene(scene);
		stage.show();
		//end of start
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
	
	
//end
}
