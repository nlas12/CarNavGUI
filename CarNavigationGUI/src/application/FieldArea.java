package application;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.Node;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class FieldArea extends GridPane {

    private IntegerProperty cellSize;
    private String[][] mapData = Map.getMap();
    
    public FieldArea() {
        this.cellSize = new SimpleIntegerProperty(0);
        this.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
    }

    /**
     * Draws a Field based on the String Array in the Map.java class.
     * Draws green rectangle if array contains "0" and gray rectangle if array contains "s".
     * Rectangle sizes depend on the width and height that are given as parameters and are bound to the property cell size for resizing without redrawing the field.
     * Also adds tooltips which show the row and column of each rectangle.
     * @param width the resulting field is 70% of the width that is given
     * @param height the resulting field is 70% of the height that is given
     */
    public void drawField(int width, int height) {
    	mapData = Map.getMap();
    	
    	this.getChildren().clear();
    	this.update(width, height);
        for (int i = 0; i < mapData.length; i++) {
            for (int j = 0; j < mapData[i].length; j++) {
                Rectangle rectangle = new Rectangle();
                rectangle.widthProperty().bind(cellSize);
                rectangle.heightProperty().bind(cellSize);

                rectangle.setStroke(Color.BLACK);
                rectangle.setStrokeWidth(1);
                
                Tooltip tooltip = new Tooltip("Row: " + i + ", Column: " + j);
                tooltip.setShowDelay(Duration.millis(300));
                Tooltip.install(rectangle, tooltip);
                if (mapData[i][j].equals("0")) {
                    rectangle.setFill(Color.GREEN);
                } else if (mapData[i][j].equals("s")) {
                    rectangle.setFill(Color.GRAY);
                }

                this.add(rectangle, j, i);
            }
        }
    }

    public IntegerProperty cellSizeProperty() {
        return cellSize;
    }

    /**
     *Updates cell Size and updates field through binding of rectangles to cell size
     * @param width
     * @param height
     */
    public void update(int width, int height) {
        cellSize.set((int)(Math.min(width / mapData[0].length, height / mapData.length)*0.7));
    }
    
    /**
     * Marks a rectangle of the field by changing its color to a darker hue.
     * This is permanent and can only be reset by redrawing the whole field.
     * @param row
     * @param column
     */
    public void mark(int row, int column) {
    	//iterate through all Children of this Grid Pane and find the one with the given row and column
        for (Node node : this.getChildren()) {
            if (GridPane.getRowIndex(node) != null && GridPane.getRowIndex(node) == row &&
                    GridPane.getColumnIndex(node) != null && GridPane.getColumnIndex(node) == column) {
            	if (node instanceof Rectangle) {
            		//change the color of the found rectangle to a darker hue of green or gray depending on its former color
                    Rectangle rectangle = (Rectangle) node;
                    Color originalColor = (Color) rectangle.getFill();
                    if (originalColor != null) {
                        if (originalColor.equals(Color.GREEN)) {
                            rectangle.setFill(Color.GREEN.deriveColor(0, 1, 1, 0.3));
                        }else if (originalColor.equals(Color.GRAY)) {
                            rectangle.setFill(Color.GRAY.deriveColor(0, 1, 1, 0.3));
                        }
                    }
                    break;
                }
            }
        }
    }
}
