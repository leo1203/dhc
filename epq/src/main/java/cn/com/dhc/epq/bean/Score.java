package cn.com.dhc.epq.bean;

import java.util.Map;

public class Score {

	private int id;
	
	private String name;
	
	private String tel;
	
	private int age;
	
	private String gender;

	private int asource;

	private int bsource;

	private int csource;

	private int dsource;

	private int esource;

	private int fsource;

	private int gsource;

	private int hsource;

	private int isource;

	private int jsource;

	private String answers;
	
	private Map<String, Object> answersMap;
	
	private String createTime;

	public Score() {
	}

	public Score(String name, String tel, int age, String gender,String answers,String createTime) {
		this.name = name;
		this.tel = tel;
		this.age = age;
		this.gender = gender;
		this.answers = answers;
		this.createTime = createTime;
	}

	public Score(String name, String tel, int age, String gender, int asource, int bsource, int csource,
				 int dsource, int esource, int fsource, int gsource, int hsource, int isource, int jsource, String answers,String createTime) {
		this.name = name;
		this.tel = tel;
		this.age = age;
		this.gender = gender;
		this.asource = asource;
		this.bsource = bsource;
		this.csource = csource;
		this.dsource = dsource;
		this.esource = esource;
		this.fsource = fsource;
		this.gsource = gsource;
		this.hsource = hsource;
		this.isource = isource;
		this.jsource = jsource;
		this.answers = answers;
		this.createTime = createTime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAsource() {
		return asource;
	}

	public void setAsource(int asource) {
		this.asource = asource;
	}

	public int getBsource() {
		return bsource;
	}

	public void setBsource(int bsource) {
		this.bsource = bsource;
	}

	public int getCsource() {
		return csource;
	}

	public void setCsource(int csource) {
		this.csource = csource;
	}

	public int getDsource() {
		return dsource;
	}

	public void setDsource(int dsource) {
		this.dsource = dsource;
	}

	public int getEsource() {
		return esource;
	}

	public void setEsource(int esource) {
		this.esource = esource;
	}

	public int getFsource() {
		return fsource;
	}

	public void setFsource(int fsource) {
		this.fsource = fsource;
	}

	public int getGsource() {
		return gsource;
	}

	public void setGsource(int gsource) {
		this.gsource = gsource;
	}

	public int getHsource() {
		return hsource;
	}

	public void setHsource(int hsource) {
		this.hsource = hsource;
	}

	public int getIsource() {
		return isource;
	}

	public void setIsource(int isource) {
		this.isource = isource;
	}

	public int getJsource() {
		return jsource;
	}

	public void setJsource(int jsource) {
		this.jsource = jsource;
	}

	public String getAnswers() {
		return answers;
	}

	public void setAnswers(String answers) {
		this.answers = answers;
	}

	public Map<String, Object> getAnswersMap() {
		return answersMap;
	}

	public void setAnswersMap(Map<String, Object> answersMap) {
		this.answersMap = answersMap;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "Score{" +
				"id=" + id +
				", name='" + name + '\'' +
				", tel='" + tel + '\'' +
				", age=" + age +
				", gender='" + gender + '\'' +
				", asource=" + asource +
				", bsource=" + bsource +
				", csource=" + csource +
				", dsource=" + dsource +
				", esource=" + esource +
				", fsource=" + fsource +
				", gsource=" + gsource +
				", hsource=" + hsource +
				", isource=" + isource +
				", jsource=" + jsource +
				", answers='" + answers + '\'' +
				", answersMap=" + answersMap +
				", createTime='" + createTime +
				'}';
	}


}
