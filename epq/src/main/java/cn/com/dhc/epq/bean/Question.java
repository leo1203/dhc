package cn.com.dhc.epq.bean;

public class Question {
	
	private int no;
	
	private String question; 
	
	private String answer;
	
	private String type;

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Question [no=" + no + ", question=" + question + ", answer=" + answer + ", type=" + type + "]";
	}

	
	
	
	
	
}
