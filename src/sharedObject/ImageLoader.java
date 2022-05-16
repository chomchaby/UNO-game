package sharedObject;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ImageLoader {
	
	// cards
	public static String colorCardURL = ClassLoader.getSystemResource("image/color.png").toString();
	public static String challengeCardURL = ClassLoader.getSystemResource("image/challenge.png").toString();
	public static String stopRedCardURL = ClassLoader.getSystemResource("image/stop_red.png").toString();
	public static String stopYellowCardURL = ClassLoader.getSystemResource("image/stop_yellow.png").toString();
	public static String stopGreenCardURL = ClassLoader.getSystemResource("image/stop_green.png").toString();
	public static String stopBlueCardURL = ClassLoader.getSystemResource("image/stop_blue.png").toString();
	public static String rotateRedCardURL = ClassLoader.getSystemResource("image/rotate_red.png").toString();
	public static String rotateYellowCardURL = ClassLoader.getSystemResource("image/rotate_yellow.png").toString();
	public static String rotateGreenCardURL = ClassLoader.getSystemResource("image/rotate_green.png").toString();
	public static String rotateBlueCardURL = ClassLoader.getSystemResource("image/rotate_blue.png").toString();
	public static String pickRedCardURL = ClassLoader.getSystemResource("image/plus_red.png").toString();
	public static String pickYellowCardURL = ClassLoader.getSystemResource("image/plus_yellow.png").toString();
	public static String pickGreenCardURL = ClassLoader.getSystemResource("image/plus_green.png").toString();
	public static String pickBlueCardURL = ClassLoader.getSystemResource("image/plus_blue.png").toString();
	
	// reverse sign
	public static Image clockwiseImg = new Image(ClassLoader.getSystemResource("image/clockwise.png").toString());
	public static Image counterClockwiseImg = new Image(ClassLoader.getSystemResource("image/counterclockwise.png").toString());
	
	// UNO logo
	public static Image logoImg = new Image(ClassLoader.getSystemResource("image/UNO_Logo.png").toString());

	// sound setting sign
	public static ImageView soundOnImg = new ImageView(new Image(ClassLoader.getSystemResource("image/SoundOn.jpg").toString()));
	public static ImageView soundOffImg = new ImageView(new Image(ClassLoader.getSystemResource("image/SoundOff.jpg").toString()));
	
}
