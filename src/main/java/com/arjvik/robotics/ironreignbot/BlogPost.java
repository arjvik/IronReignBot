package com.arjvik.robotics.ironreignbot;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BlogPost {
	
	public final String topic;
	public final String date;

	@JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
	public BlogPost(@JsonProperty("topic") String topic, @JsonProperty("date") String date) {
		this.topic = topic;
		this.date = date;
	}

	public String getTopic() {
		return topic;
	}

	public String getDate() {
		return date;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((topic == null) ? 0 : topic.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BlogPost other = (BlogPost) obj;
		if (topic == null) {
			if (other.topic != null)
				return false;
		} else if (!topic.equals(other.topic))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format("%s (assigned %s)", topic, date);
	}

}