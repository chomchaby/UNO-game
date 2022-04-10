package gui;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import logic.GameAction;
import logic.GameLogic;

public class StatusPane extends VBox implements Updatable{
	
	private final String logoURL;
	private TablePane tablePane;
	private Text currentPlayerText;
	private boolean hasColorSelectionText;
	private ColorSelection colorSelectionText;
	
	public StatusPane() {
		this.setSpacing(20);
		this.setAlignment(Pos.CENTER);
		// setting logo
		logoURL = ClassLoader.getSystemResource("java.png").toString();
		Image logoPNG = new Image(logoURL);
		ImageView imageView = new ImageView(logoPNG);
		imageView.setFitHeight(60);
		imageView.setFitWidth(60);
		
		// create tablePane
		tablePane = new TablePane();
		
		// setting currentPlayerText
		currentPlayerText = new Text("Player Turn : " + GameLogic.getInstance().getCurrentPlayer().getName());
		this.getChildren().addAll(imageView,tablePane,currentPlayerText);
		
		// set colorSelectionText 
		hasColorSelectionText = false;
		setColorSelectionText();
	}
	
	@Override
	public void update() {
		tablePane.update();
		currentPlayerText.setText("Player Turn : " + GameLogic.getInstance().getCurrentPlayer().getName());
		setColorSelectionText();
	}
	
	private void setColorSelectionText() {
		if (GameLogic.getInstance().isColorSelectionState() && !(hasColorSelectionText)) {
			colorSelectionText = new ColorSelection();
			this.getChildren().add(colorSelectionText);
			GameLogic.getInstance().setColorSelectionState(false);
			hasColorSelectionText = true;
		}
		else if (!(GameLogic.getInstance().isColorSelectionState()) && hasColorSelectionText){
			this.getChildren().remove(colorSelectionText);
		}
	}

}
