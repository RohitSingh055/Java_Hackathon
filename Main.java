package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;


public class Main extends Application {
	
	private double x=0;
	private double y=0;
	@Override
	public void start(Stage stage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("super_fxml.fxml"));
			StackPane root = new StackPane();
			loader.setRoot(root);
			loader.load();
			Scene scene = new Scene(root,600,500);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			root.setOnMousePressed((MouseEvent event) -> {
				x = event.getSceneX();
				y = event.getSceneY(); 
			});
			
			root.setOnMouseDragged((MouseEvent event) ->{
				stage.setX(event.getScreenX() - x);
				stage.setY(event.getScreenY() - y);
				stage.setOpacity(.8);
			});
			
			root.setOnMouseReleased((MouseEvent event) ->{
				stage.setOpacity(1);
			});
			
			
			
			stage.initStyle(StageStyle.TRANSPARENT);
			stage.setScene(scene);
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
