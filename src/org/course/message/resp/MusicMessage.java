package org.course.message.resp;

/**
 * 音乐消息
 * 
 * @author zhukejia
 * @date 2013
 */
public class MusicMessage extends BaseMessage {
	// 音乐
	private Music Music;

	public Music getMusic() {
		return Music;
	}

	public void setMusic(Music music) {
		Music = music;
	}
}