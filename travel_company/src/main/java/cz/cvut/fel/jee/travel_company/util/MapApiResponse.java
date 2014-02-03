package cz.cvut.fel.jee.travel_company.util;

import java.util.List;

public class MapApiResponse {
	 private List<Result> results;
	    private String status;

	    public List<Result> getResults() {
	        return results;
	    }

	    public void setResults(List<Result> results) {
	        this.results = results;
	    }

	    public String getStatus() {
	        return status;
	    }

	    public void setStatus(String status) {
	        this.status = status;
	    }
}
