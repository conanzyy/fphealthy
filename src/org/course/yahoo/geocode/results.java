package org.course.yahoo.geocode;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ResultSet")
public class results {
	private Result result;

	public results() {
	}

	@XmlElement(name = "Result")
	public void setResult(Result result) {
		this.result = result;
	}
	
	public Result getResult() {
		return result;
	}

}
