package application;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;

public class ButtonArea extends GridPane {
    
	private List<Button> buttons = new ArrayList<Button>();
	private Button generateButton,clearButton,redrawButton;
	private Button but1,but2;
	
	public ButtonArea() {
		super();
        generateButton = new Button("Generate Field");
        redrawButton = new Button("Redraw Field");
        clearButton = new Button("Clear Field");
        but1 = new Button("customizable");
        but2 = new Button("customizable");
		Collections.addAll(buttons,generateButton,clearButton,redrawButton,but1,but2);
        
        for(Button button : buttons) {
        	button.setMinWidth(100);
        }
        
        ColumnConstraints c = new ColumnConstraints();
        c.setHalignment(HPos.CENTER);
        this.getColumnConstraints().add(c);
        this.setVgap(20);
        this.setPadding(new Insets(10));

        
        add(generateButton, 0, 0);
        add(redrawButton, 0, 1);
        add(clearButton, 0, 2);
        add(but1, 0, 3);
        add(but2, 0, 4);
    }

	public Button getGenerateButton() {
		return generateButton;
	}
	public Button getRedrawButton() {
		return redrawButton;
	}

	public Button getClearButton() {
		return clearButton;
	}

	public Button getBut1() {
		return but1;
	}

	public Button getBut2() {
		return but2;
	}
	
}
