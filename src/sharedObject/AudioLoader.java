package sharedObject;

import javafx.scene.media.AudioClip;

public class AudioLoader {

	// bgSound
	public static AudioClip startBGSound = new AudioClip(ClassLoader.getSystemResource("audio/Funky_Bass.mp3").toString());
	public static AudioClip gamePlayBGSound = new AudioClip(ClassLoader.getSystemResource("audio/Soft_Piano.mp3").toString());
	// mouse handle sound
	public static AudioClip mouseEnterSound = new AudioClip(ClassLoader.getSystemResource("audio/Mouse_Enter.wav").toString());
	public static AudioClip mouseClick1Sound = new AudioClip(ClassLoader.getSystemResource("audio/Mouse_Click1.mp3").toString());
	public static AudioClip mouseClick2Sound = new AudioClip(ClassLoader.getSystemResource("audio/Mouse_Click2.mp3").toString());
	public static AudioClip nopeSound = new AudioClip(ClassLoader.getSystemResource("audio/Nope.mp3").toString());
	
}
