package cn.com.dhc.epq.bean;


public class Answer {

	private int id;

	private String tel;

	private String dimensionAnswer;

	public Answer() {
	}

	public Answer(String tel, String dimensionAnswer) {
		this.tel = tel;
		this.dimensionAnswer = dimensionAnswer;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	@Override
	public String toString() {
		return "Answer{" +
				"id=" + id +
				", tel='" + tel + '\'' +
				", dimensionAnswer='" + dimensionAnswer + '\'' +
				'}';
	}

	public String getDimensionAnswer() {
		return dimensionAnswer;
	}

	public void setDimensionAnswer(String dimensionAnswer) {
		this.dimensionAnswer = dimensionAnswer;
	}

}
