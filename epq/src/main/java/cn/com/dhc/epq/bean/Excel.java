package cn.com.dhc.epq.bean;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "excel")
public class Excel {
	
	private String fileName;
	
	private String sheetName;
	
	private List<String> titles;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getSheetName() {
		return sheetName;
	}

	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}

	public List<String> getTitles() {
		return titles;
	}

	public void setTitles(List<String> titles) {
		this.titles = titles;
	}

	@Override
	public String toString() {
		return "Excel [fileName=" + fileName + ", sheetName=" + sheetName + ", titles=" + titles + "]";
	}

}
