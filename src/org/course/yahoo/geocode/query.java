package org.course.yahoo.geocode;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "query")
public class query {
	private results results;

	public query() {
	}

	@XmlElement(name = "results")
	public void setResults(results results) {
		this.results = results;
	}
    
	public results getResults() {
		return results;
	}
}
