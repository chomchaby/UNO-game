package sharedObject;

import javafx.scene.media.AudioClip;

public class AudioLoader {

	// bgSound
	public static AudioClip startBGSound = new AudioClip(ClassLoader.getSystemResource("audio/Funky_Bass.mp3").toString());
	public static AudioClip gamePlayBGSound = new AudioClip(ClassLoader.getSystemResource("audio/Soft_Piano.mp3").toString());
	// mouse handle sound
	// for entering menu (button), clicking all buttons in alert
	public static AudioClip mouseEnterSound = new AudioClip(ClassLoader.getSystemResource("audio/Mouse_Enter.wav").toString());
	// for placing a card and clicking a menu button
	public static AudioClip mouseClick1Sound = new AudioClip(ClassLoader.getSystemResource("audio/Mouse_Click1.mp3").toString());
	// for drawing a card
	public static AudioClip mouseClick2Sound = new AudioClip(ClassLoader.getSystemResource("audio/Mouse_Click2.mp3").toString());
	// for clicking a wrong place
	public static AudioClip nopeSound = new AudioClip(ClassLoader.getSystemResource("audio/Nope.mp3").toString());
	// for clicking color button (in color selection)
	public static AudioClip buttonClickSound = new AudioClip(ClassLoader.getSystemResource("audio/Button_Click.mp3").toString());
	// for entering color button (in color selection)
	public static AudioClip popSound = new AudioClip(ClassLoader.getSystemResource("audio/Pop.mp3").toString());
}
