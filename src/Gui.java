
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Scanner;
import java.util.stream.Collectors;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;


import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
/**
 * Gui is a class that creates the GUI for the user.
 * It also reads in a text file of baseball players and
 * inserts them into an array and lets the user create their own
 * list of baseball players.
 * @author Collin
 *@version 1.8
 */
public class Gui extends Application implements Cloneable, Comparable {
	
	

	public static void main(String[] args){
		createPlayerObjects(player); //create baseball player objects.
		createUserPlayerObjects(user); //create objects for the user made list.
		
		readPlayerFromFile(player, playerNames); //read in the players from the file.
		launch(args);
	}
	
	private static final File playerNames = new File("players.txt");
	private static BaseballPlayer[] player = new BaseballPlayer[20];//Pre existing list
	public static BaseballPlayer[] user = new BaseballPlayer[3]; //The user created array.
	private static BaseballPlayer[] answer; //The player and user arrays combined.
	private static BaseballPlayer[] copy;
	static TableView table;
	static TableView table2;
	static TableView table3;
	private static int playerArrayIndex;
	private static int playerArrayUserIndex;
	private static int numOfPlayers;
	private static int numOfUserPlayers;
	
	private static TextField txtField = new TextField();
	public static TextArea textArea = new TextArea();
	
	public void start(Stage primaryStage){
		//add title
		final Label theTitle = new Label("List of Baseball Players");
		
		//TableColumn 
		
		//textfields
		TextField nameFd = new TextField();
		TextField ageFd = new TextField();
		TextField teamFd = new TextField();
		TextField positionFd = new TextField();
		TextField searchFd = new TextField();
		TextField deleteFd = new TextField();
		
		//labels
		Label nameLbl = new Label();
		nameLbl.setText("Name");
		Label teamLbl = new Label();
		teamLbl.setText("Team");
		Label positionLbl = new Label();
		positionLbl.setText("Position");
		Label ageLbl = new Label();
		ageLbl.setText("Age");
		Label searchLbl = new Label();
		searchLbl.setText("Search");
		Label deleteLbl = new Label();
		deleteLbl.setText("Delete by Name");
		
		//buttons
		Button deleteBtn = new Button("Delete");
		deleteBtn.setMaxSize(55, 35);
		deleteBtn.setMinSize(55, 35);
		
		Button addBtn = new Button("Add to Current List");
		addBtn.setMaxSize(120, 35);
		addBtn.setMinSize(120, 35);
		
		Button addBtn2 = new Button("Add to new List");
		addBtn2.setMaxSize(120, 35);
		addBtn2.setMinSize(120, 35);
		
		Button displayBtn = new Button("Display");
		displayBtn.setMaxSize(55, 35);
		displayBtn.setMinSize(55, 35);
		
		Button compareBtn = new Button("Compare");
		compareBtn.setMaxSize(65, 35);
		compareBtn.setMinSize(65, 35);
		
		Button searchBtn = new Button("Search");
		searchBtn.setMaxSize(65, 35);
		searchBtn.setMinSize(65, 35);
		
		Button combineBtn = new Button("Combine");
		searchBtn.setMaxSize(65, 35);
		searchBtn.setMinSize(65, 35);
		
		Button cloneBtn = new Button("Clone List");
		cloneBtn.setMaxSize(80, 35);
		cloneBtn.setMinSize(80, 35);
		
		//Program Layout
		BorderPane primaryPane = new BorderPane();
		
		//add title to pane
		primaryPane.setTop(theTitle);
		theTitle.setPadding(new Insets(10, 10, 10, 10));
		BorderPane.setAlignment(theTitle, Pos.CENTER);
		
		GridPane leftGrid = new GridPane();
		leftGrid.setHgap(5);
		leftGrid.setVgap(5);
		leftGrid.setPadding(new Insets(0, 0, 0, 5));
		
		//add name label and field
		leftGrid.add(nameLbl, 0, 0);
		leftGrid.add(nameFd, 1, 0);
		
		//add team label and field
		leftGrid.add(teamLbl, 0, 1);
		leftGrid.add(teamFd, 1, 1);
		
		//add position label and field
		leftGrid.add(positionLbl, 0, 2);
		leftGrid.add(positionFd, 1, 2);
		
		//add age label and field
		leftGrid.add(ageLbl, 0, 3);
		leftGrid.add(ageFd, 1, 3);
		
		//add the buttons to left grid
		leftGrid.add(addBtn, 1, 5);
		leftGrid.add(displayBtn, 0, 5);
		leftGrid.add(compareBtn, 0, 6);
		leftGrid.add(addBtn2, 1, 6);
		leftGrid.add(combineBtn, 0, 7);
		leftGrid.add(cloneBtn, 1, 7);
		
		//add the search(label,button,textfield) to left grid.
		leftGrid.add(searchLbl, 0, 8);
		leftGrid.add(searchFd, 1, 8);
		leftGrid.add(searchBtn, 2, 8);
		
		//add the delete(label,button, textfield) to left grid.
		leftGrid.add(deleteLbl, 0, 9);
		leftGrid.add(deleteFd, 1, 9);
		leftGrid.add(deleteBtn, 2, 9);
		
		//display players in table
		displayAllPlayers(player);
		
		//create vbox and add the table to it.
		VBox vbox = new VBox();
		vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 10, 0, 10));
        vbox.getChildren().addAll(table);
        
        //add text area
        VBox tArea = new VBox(textArea);
        textArea.setPrefHeight(100);  
        textArea.setPrefWidth(200);
		primaryPane.setLeft(leftGrid);
		primaryPane.setRight(vbox);
		
		leftGrid.add(textArea,0,15);
		
		//event handling. 
		addBtn.setOnAction(e -> {
			String name, team, age, position;
			name = nameFd.getText();
			age = ageFd.getText();
			team = teamFd.getText();
			position = positionFd.getText();
			try {
				addPlayer(name, age, team, position);//add to current list
				
			} catch (CloneNotSupportedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				textArea.setText("Error");
			}
			textArea.setText("Player added successfully");
			
		});
		
		addBtn2.setOnAction(e -> {
			String name, team, age, position;
			name = nameFd.getText();
			age = ageFd.getText();
			team = teamFd.getText();
			position = positionFd.getText();
			try {
				addUserPlayer(name, age, team, position);//add to new list.
				
			} catch (CloneNotSupportedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				textArea.setText("Error");
			}
			textArea.setText("Player added successfully");
		});
		
		displayBtn.setOnAction(e -> {
			displayAllPlayers(player);
			displayAllUserPlayers(user); //display user made list.
			primaryPane.setBottom(table2); //display user made list in the bottom.
		});
		
		searchBtn.setOnAction(e -> {
			String searchEntry = searchFd.getText();
			findPlayer(searchEntry);
		});
		
		deleteBtn.setOnAction(e -> {
			String deleteEntry = deleteFd.getText();
			removePlayer(deleteEntry);
			
		});
		
		combineBtn.setOnAction(e -> {
			createAnswerPlayerObjects(answer);
			union(player, user);
			displayAllAnswerPlayers(answer);
			vbox.getChildren().addAll(table3);
			
		});
		
		cloneBtn.setOnAction(e -> {
			copy = player.clone();
			textArea.setText("List was copied.");
		});
		
		compareBtn.setOnAction(e -> {
			try{
			compareTo(copy);
			}catch(Exception e6){
				textArea.setText("error");
			}
		});
		
		Scene leftScene = new Scene(primaryPane,1000,700);
		primaryStage.setScene(leftScene);
		
		
		
		primaryStage.setTitle("List of Players");
		
		primaryStage.show();
	}
	
	/**
	 * This is the displayAllPlayers method. This method will display the players from the text file into a table.
	 * @param players This is the array of baseball players from the text file.
	 * @return table -The table that will be displayed in the GUI.
	 */
	public static TableView displayAllPlayers(BaseballPlayer[] players){
		//reference: in class example.
		table = new TableView<>();
		//create table columns
		TableColumn<BaseballPlayer, String> nameColumn = new TableColumn<>("Name");
		nameColumn.setMinWidth(200);
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		
		TableColumn<BaseballPlayer, String> ageColumn = new TableColumn<>("Age");
		ageColumn.setMinWidth(100);
		ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
		
		TableColumn<BaseballPlayer, String> teamColumn = new TableColumn<>("Team");
		teamColumn.setMinWidth(200);
		teamColumn.setCellValueFactory(new PropertyValueFactory<>("team"));
		
		TableColumn<BaseballPlayer, String> positionColumn = new TableColumn<>("Position");
		positionColumn.setMinWidth(200);
		positionColumn.setCellValueFactory(new PropertyValueFactory<>("position"));
		
		//reference: in class example
		ArrayList<BaseballPlayer> playerArrayList = new ArrayList<BaseballPlayer>(Arrays.asList(player));
		ObservableList<BaseballPlayer> playerList = FXCollections.observableArrayList(playerArrayList);
		
		table.getColumns().addAll(nameColumn, ageColumn, teamColumn, positionColumn);//add columns
		table.setItems(playerList);
		
		return table;
	}
	
	/**
	 * This is the displayAllUserPlayers method. This method will display the list the user made.
	 * @param players -the user made list
	 * @return table2 -the table that will display the user made list.
	 */
	public static TableView displayAllUserPlayers(BaseballPlayer[] players){
		//reference: in class example.
		table2 = new TableView<>();
		//create table columns
		TableColumn<BaseballPlayer, String> nameColumn = new TableColumn<>("Name");
		nameColumn.setMinWidth(200);
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		
		TableColumn<BaseballPlayer, String> ageColumn = new TableColumn<>("Age");
		ageColumn.setMinWidth(100);
		ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
		
		TableColumn<BaseballPlayer, String> teamColumn = new TableColumn<>("Team");
		teamColumn.setMinWidth(200);
		teamColumn.setCellValueFactory(new PropertyValueFactory<>("team"));
		
		TableColumn<BaseballPlayer, String> positionColumn = new TableColumn<>("Position");
		positionColumn.setMinWidth(200);
		positionColumn.setCellValueFactory(new PropertyValueFactory<>("position"));
		
		//reference: in class example
		ArrayList<BaseballPlayer> playerArrayList = new ArrayList<BaseballPlayer>(Arrays.asList(user));
		ObservableList<BaseballPlayer> playerList = FXCollections.observableArrayList(playerArrayList);
		
		table2.getColumns().addAll(nameColumn, ageColumn, teamColumn, positionColumn);//add columns
		table2.setItems(playerList);
		
		return table2;
	}
	
	/**
	 * This is the displayAllAnswerPlayers method. This method will display the combined list of the original list and user made list.
	 * @param answer -This is the combined array.
	 * @return table3 -The table that will display the combine list.
	 */
	public static TableView displayAllAnswerPlayers(BaseballPlayer[] answer){
		//reference: in class example.
		table3 = new TableView<>();
		//create table columns
		TableColumn<BaseballPlayer, String> nameColumn = new TableColumn<>("Name");
		nameColumn.setMinWidth(200);
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
		
		TableColumn<BaseballPlayer, String> ageColumn = new TableColumn<>("Age");
		ageColumn.setMinWidth(100);
		ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
		
		TableColumn<BaseballPlayer, String> teamColumn = new TableColumn<>("Team");
		teamColumn.setMinWidth(200);
		teamColumn.setCellValueFactory(new PropertyValueFactory<>("team"));
		
		TableColumn<BaseballPlayer, String> positionColumn = new TableColumn<>("Position");
		positionColumn.setMinWidth(200);
		positionColumn.setCellValueFactory(new PropertyValueFactory<>("position"));
		
		//reference: in class example
		ArrayList<BaseballPlayer> playerArrayList = new ArrayList<BaseballPlayer>(Arrays.asList(answer));
		ObservableList<BaseballPlayer> playerList = FXCollections.observableArrayList(playerArrayList);
		
		table3.getColumns().addAll(nameColumn, ageColumn, teamColumn, positionColumn);//add columns
		table3.setItems(playerList);
		
		return table3;
	}
	
	
	
	/**
	 * This is the createPlayerObjects method. This method will create objects for the player array.
	 * @param playerObject -This is the player array.
	 * @return playerObject -The created player objects for array. 
	 * 
	 */
	private static BaseballPlayer[] createPlayerObjects(BaseballPlayer[] playerObject){
		//reference: in class example
		try{
			if(playerObject instanceof BaseballPlayer[]){
				for(int index = 0; index < playerObject.length; index++){
					playerObject[index] = new BaseballPlayer();
				}
			}
			else{
				for(int index = 0; index < playerObject.length; index++){
					playerObject[index] = new BaseballPlayer();
				}
			}
		}
		catch(NullPointerException e){
			System.out.println(e);
		}
		catch(Exception e){
			System.out.println(e);
		}
		return playerObject;
	}
	
	/**
	 * This is the createUserPlayerObjects method. This method will create objects for the user array.
	 * @param userObject -This is the user array.
	 * @return userObject -The created objects for the user array.
	 */
	private static BaseballPlayer[] createUserPlayerObjects(BaseballPlayer[] userObject){
		//reference: in class example
		try{
			if(userObject instanceof BaseballPlayer[]){
				for(int index = 0; index < userObject.length; index++){
					userObject[index] = new BaseballPlayer();
				}
			}
			else{
				for(int index = 0; index < userObject.length; index++){
					userObject[index] = new BaseballPlayer();
				}
			}
		}
		catch(NullPointerException e){
			System.out.println(e);
		}
		catch(Exception e){
			System.out.println(e);
		}
		return userObject;
	}
	
	/**
	 * This is the createAnswerPlayerObjects method. This method will create objects for the answer array.(The combination of user and player arrays)
	 * @param answerObject -This is the answer array.
	 * @return answerObject -The objects for the answer array.
	 */
	private static BaseballPlayer[] createAnswerPlayerObjects(BaseballPlayer[] answerObject){
		//reference: in class example
		try{
			if(answerObject instanceof BaseballPlayer[]){
				for(int index = 0; index < answerObject.length; index++){
					answerObject[index] = new BaseballPlayer();
				}
			}
			else{
				for(int index = 0; index < answerObject.length; index++){
					answerObject[index] = new BaseballPlayer();
				}
			}
		}
		catch(NullPointerException e){
			System.out.println(e);
		}
		catch(Exception e){
			System.out.println(e);
		}
		return answerObject;
	}
	
	/**
	 * This is the readPlayerFromFile method. This method will read from the file.
	 * postcondition-the file is read into an array.
	 * @param playerObject -This is the player array.
	 * @param file -This is the text file.
	 */
	public static void readPlayerFromFile(BaseballPlayer[] playerObject, File file){
		//reference: in class example
		try{

			@SuppressWarnings("resource")
			Scanner input = new Scanner(file).useDelimiter("/");

			int index = 0;

			while(input.hasNext() && index < playerObject.length){

				playerObject[index].setName(input.next().trim());
				playerObject[index].setAge(input.next().trim());
				playerObject[index].setTeam(input.next().trim());
				playerObject[index].setPosition(input.next().trim());

				index++;
			}
			input.close();
		}
		catch(NullPointerException e){
			System.out.println(e);	
		}
		catch(FileNotFoundException e){
			System.out.println(e);
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
	
	/**
	 * This is the addPlayer method. This method will add a player into the player array. 
	 * postcondition-a player is added to the player array.
	 * @param name -players name.
	 * @param age -players age.
	 * @param team -players team.
	 * @param position -players position.
	 * @throws CloneNotSupportedException
	 */
	public void addPlayer(String name, String age, String team, String position) throws CloneNotSupportedException {
		//reference: in class example
		if(numOfPlayers == player.length) {
			ensureCapacity((numOfPlayers * 2) + 1);
		}

		player[playerArrayIndex].setName(name);
		player[playerArrayIndex].setAge(age);
		player[playerArrayIndex].setTeam(team);
		player[playerArrayIndex].setPosition(position);
		

		playerArrayIndex++;
		numOfPlayers++;
	}
	
	/**
	 * This the addUserPlayer method. This will a player to the User made list.
	 * postcondition-a player is added to the user array.
	 * @param name -The players name.
	 * @param age -The players age.
	 * @param team -The players tea,.
	 * @param position -The players position.
	 * @throws CloneNotSupportedException
	 */
	public void addUserPlayer(String name, String age, String team, String position) throws CloneNotSupportedException {
		//reference: in class example
		if(numOfUserPlayers == user.length) {
			ensureUserCapacity((numOfUserPlayers * 2) + 1);
		}

		user[playerArrayUserIndex].setName(name);
		user[playerArrayUserIndex].setAge(age);
		user[playerArrayUserIndex].setTeam(team);
		user[playerArrayUserIndex].setPosition(position);
		

		playerArrayUserIndex++;
		numOfUserPlayers++;
	}
	
	/**
	 * This is the ensureCapacity method. This will increase the 
	 * capacity of the array if user wants to add to the array.
	 * precondition: biggerCapacity must be greater than 0.
	 * postcondition: an array with a larger capacity.
	 * @param biggerCapacity This is the new capacity number.
	 * @throws IllegalArgumentException if given a negative number.
	 */
	public void ensureCapacity(int biggerCapacity){
		//reference: in class example
		if(biggerCapacity < 0)
			throw new IllegalArgumentException("Cannot be a negative number");
		BaseballPlayer[] biggerList;
		if(player.length < biggerCapacity){
			biggerList = new BaseballPlayer[biggerCapacity];
			for(int i = 0; i < biggerList.length; i++){
				biggerList[i] = new BaseballPlayer();
			}
			
			System.arraycopy(player, 0, biggerList, 0, numOfPlayers);

			player = biggerList;
		}
	}
	
	/**
	 * This is the ensureUserCapacity method. This will increase the 
	 * capacity of the array if user wants to add to the array.
	 * precondition: biggerCapacity must be greater than 0.
	 * postcondition: an array with a larger capacity.
	 * @param biggerCapacity This is the new capacity number.
	 * @throws IllegalArgumentException if given a negative number.
	 */
	public void ensureUserCapacity(int biggerCapacity){
		//reference: in class example
		if(biggerCapacity < 0)
			throw new IllegalArgumentException("Cannot be a negative number");
		BaseballPlayer[] biggerList;
		if(user.length < biggerCapacity){
			biggerList = new BaseballPlayer[biggerCapacity];
			for(int i = 0; i < biggerList.length; i++){
				biggerList[i] = new BaseballPlayer();
			}
			
			System.arraycopy(user, 0, biggerList, 0, numOfUserPlayers);

			user = biggerList;
		}
	}
	
	/**
	 * This is the findPlayer method. This will search for a player by name.
	 * postcondition-a player is found or not found.
	 * @param name -The name of the player to be searched for. 
	 * @return The location of the searched player. 
	 */
	public int findPlayer(String name){
		//reference: in class example
		int i;
		for(i = 0; i < player.length; i++) {
			if(name.equalsIgnoreCase(player[i].getName())){
				textArea.setText(name + " found");
				System.out.println(name + " found");
				return i;
			}	
		}
		return -1;
	}
	
	/**
	 * This is the removePlayer method. This will remove a player from the list.
	 * postcondition-a player is removed from the array.
	 * @param name -name of the player to be removed
	 * @return removed
	 */
	public boolean removePlayer(String name) {
		//reference: in class example
		boolean removed = false;
		int index = -1;

		if(numOfPlayers > 0) {		
			index = findPlayer(name);

			if(index >= 0) {
				player[index] = player[playerArrayIndex - 1];

				playerArrayIndex--;
				numOfPlayers--;
				removed = true;
				textArea.setText(name + " was remove");
			}
		}

		return removed;
	}
	
	/**
	 * This is the Clone method. This will create a copy of the array.
	 * precondition: an array with values.
	 * @return a copy of the array.
	 * @throws CloneNotSupportedException if the class can't implement Clonable.
	 * 
	 */
	public Gui Clone(){
		//reference: in class example
		Gui newPlayerArray;

		try {
			newPlayerArray = (Gui)super.clone();
		} catch (CloneNotSupportedException e) {
			throw new RuntimeException("This class does not implement Clonable");
		}

		newPlayerArray.player = player.clone();

		return newPlayerArray;
	}
	
	/**
	 * This is the getCapacity method. This will return the capacity of the player array.
	 * @return player.length - size of the array. 
	 */
	public int getCapacity() {
		return player.length;
	}
	
	/**
	 * This is the union method. This method will combine the player array and user array into a 
	 * new array called answer.
	 * postcondition-The player and user arrays are combined into an array named answer.
	 * @param player -the player array
	 * @param user -the user made array
	 * @return answer -the new array.
	 */
	public static BaseballPlayer[] union(BaseballPlayer[] player, BaseballPlayer[] user){
		//reference: in class example
		answer = new BaseballPlayer[player.length + user.length];
		
		System.arraycopy(player, 0, answer, 0, player.length);
		System.arraycopy(user, 0, answer, player.length, user.length);
		
		return answer;
	}
	
	
	/**
	 * This is the compareTo method. This will compare two objects
	 */
	@Override
	public int compareTo(Object arg) {
		if(arg.equals(player)){
			textArea.setText("The copied list is equal to original list.");
			return 0;
		}else
			return -1;
	}

}
