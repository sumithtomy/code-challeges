package com.ledger.power.bean;

import java.util.List;

/**
 * The Class PostCodeRangeRespons Just a Response Wrapper
 */
public class PostCodeRangeResponse {
	private List<String> userNames;
	private Long totalWordCount;

	public PostCodeRangeResponse(List<String> userNames, Long totalWordCount) {
		super();
		this.userNames = userNames;
		this.totalWordCount = totalWordCount;
	}

	public Long getTotalWordCount() {
		return totalWordCount;
	}

	public List<String> getUserNames() {
		return userNames;
	}

}
