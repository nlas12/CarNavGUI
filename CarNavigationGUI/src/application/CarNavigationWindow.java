package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class CarNavigationWindow extends Application{
	
	private FieldArea fieldArea;
	private HBox fieldContainer;
	private ButtonArea btnArea;
	
	@Override
	public void start(Stage stage) throws Exception {
		fieldArea = new FieldArea();
		fieldArea.setStyle("-fx-border-color: black");

		//add field area to a container which is a hbox
		fieldContainer = new HBox();
		fieldContainer.getChildren().add(fieldArea);
		fieldContainer.setBackground(new Background(new BackgroundFill(Color.DARKGREEN,
                CornerRadii.EMPTY,
                Insets.EMPTY)));
		fieldContainer.setAlignment(Pos.CENTER);
		
		btnArea = new ButtonArea();
		configureButtons();
		
		//add field area container and button area to a border pane and add that to a scene
		BorderPane root = new BorderPane();
		root.setCenter(fieldContainer);
		root.setRight(btnArea);
		Scene scene = new Scene(root);
		
		//configure the window
		stage.setScene(scene);
		stage.setMinHeight(1000);
		stage.setMinWidth(1000);
		stage.setResizable(false);
		stage.show();
	}


	
	/**
	 * configures all the buttons' events 
	 */
	private void configureButtons() {
		btnArea.getGenerateButton().setOnAction(e ->{
			//
			//generate new Map and set it in Map.java
			//
			fieldArea.drawField((int)fieldContainer.getWidth(),(int)fieldContainer.getHeight());
		});
		
		btnArea.getClearButton().setOnAction(e ->{
			fieldArea.getChildren().clear();
		});
		
		btnArea.getRedrawButton().setOnAction(e ->{
			fieldArea.drawField((int)fieldContainer.getWidth(),(int)fieldContainer.getHeight());
		});
	}


}
