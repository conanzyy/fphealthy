package rule.bean;

public class Rule {
	private int ruleId;
	private String ruleName;
	private String keyWord;
	private int isWordReply;//1是,0否
	private String wordContent;
	private int isPictureReply;//1是,0否
	private String pictureContent;
	private int isVoiceReply;//1是,0否
	private String voiceContent;
	private int isVideoReply;//1是,0否
	private String videoContent;
	private int isPictureWordReply;//1是,0否
	private String pictureContent1;
	private String wordContent1;

	public Rule() {
	}
	
	public Rule(int ruleId, String ruleName, String keyWord, int isWordReply,
			String wordContent, int isPictureReply, String pictureContent,
			int isVoiceReply, String voiceContent, int isVideoReply,
			String videoContent, int isPictureWordReply,
			String pictureContent1, String wordContent1) {
		this.ruleId = ruleId;
		this.ruleName = ruleName;
		this.keyWord = keyWord;
		this.isWordReply = isWordReply;
		this.wordContent = wordContent;
		this.isPictureReply = isPictureReply;
		this.pictureContent = pictureContent;
		this.isVoiceReply = isVoiceReply;
		this.voiceContent = voiceContent;
		this.isVideoReply = isVideoReply;
		this.videoContent = videoContent;
		this.isPictureWordReply = isPictureWordReply;
		this.pictureContent1 = pictureContent1;
		this.wordContent1 = wordContent1;
	}

	public int getRuleId() {
		return ruleId;
	}
	public void setRuleId(int ruleId) {
		this.ruleId = ruleId;
	}
	public String getRuleName() {
		return ruleName;
	}
	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}
	public String getKeyWord() {
		return keyWord;
	}
	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}
	public int getIsWordReply() {
		return isWordReply;
	}
	public void setIsWordReply(int isWordReply) {
		this.isWordReply = isWordReply;
	}
	public String getWordContent() {
		return wordContent;
	}
	public void setWordContent(String wordContent) {
		this.wordContent = wordContent;
	}
	public int getIsPictureReply() {
		return isPictureReply;
	}
	public void setIsPictureReply(int isPictureReply) {
		this.isPictureReply = isPictureReply;
	}
	public String getPictureContent() {
		return pictureContent;
	}
	public void setPictureContent(String pictureContent) {
		this.pictureContent = pictureContent;
	}
	public int getIsVoiceReply() {
		return isVoiceReply;
	}
	public void setIsVoiceReply(int isVoiceReply) {
		this.isVoiceReply = isVoiceReply;
	}
	public String getVoiceContent() {
		return voiceContent;
	}
	public void setVoiceContent(String voiceContent) {
		this.voiceContent = voiceContent;
	}
	public int getIsVideoReply() {
		return isVideoReply;
	}
	public void setIsVideoReply(int isVideoReply) {
		this.isVideoReply = isVideoReply;
	}
	public String getVideoContent() {
		return videoContent;
	}
	public void setVideoContent(String videoContent) {
		this.videoContent = videoContent;
	}
	public int getIsPictureWordReply() {
		return isPictureWordReply;
	}
	public void setIsPictureWordReply(int isPictureWordReply) {
		this.isPictureWordReply = isPictureWordReply;
	}
	public String getPictureContent1() {
		return pictureContent1;
	}
	public void setPictureContent1(String pictureContent1) {
		this.pictureContent1 = pictureContent1;
	}
	public String getWordContent1() {
		return wordContent1;
	}
	public void setWordContent1(String wordContent1) {
		this.wordContent1 = wordContent1;
	}
}
