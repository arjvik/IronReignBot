package com.arjvik.robotics.ironreignbot.handlers.toa;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Team {

	private String teamKey;
	private String regionKey;
	private String leagueKey;
	private int teamNumber;
	private String teamNameShort;
	private String teamNameLong;
	private String robotName;
	private String lastActive;
	private String city;
	private String stateProv;
	private String zipCode;
	private String country;
	private int rookieYear;
	private String website;

	public Team() {
		super();
	}

	public Team(String teamKey, String regionKey, String leagueKey, int teamNumber, String teamNameShort,
			String teamNameLong, String robotName, String lastActive, String city, String stateProv, String zipCode,
			String country, int rookieYear, String website) {
		this();
		this.teamKey = teamKey;
		this.regionKey = regionKey;
		this.leagueKey = leagueKey;
		this.teamNumber = teamNumber;
		this.teamNameShort = teamNameShort;
		this.teamNameLong = teamNameLong;
		this.robotName = robotName;
		this.lastActive = lastActive;
		this.city = city;
		this.stateProv = stateProv;
		this.zipCode = zipCode;
		this.country = country;
		this.rookieYear = rookieYear;
		this.website = website;
	}

	public String getTeamKey() {
		return teamKey;
	}

	public void setTeamKey(String teamKey) {
		this.teamKey = teamKey;
	}

	public String getRegionKey() {
		return regionKey;
	}

	public void setRegionKey(String regionKey) {
		this.regionKey = regionKey;
	}

	public String getLeagueKey() {
		return leagueKey;
	}

	public void setLeagueKey(String leagueKey) {
		this.leagueKey = leagueKey;
	}

	public int getTeamNumber() {
		return teamNumber;
	}

	public void setTeamNumber(int teamNumber) {
		this.teamNumber = teamNumber;
	}

	public String getTeamNameShort() {
		return teamNameShort;
	}

	public void setTeamNameShort(String teamNameShort) {
		this.teamNameShort = teamNameShort;
	}

	public String getTeamNameLong() {
		return teamNameLong;
	}

	public void setTeamNameLong(String teamNameLong) {
		this.teamNameLong = teamNameLong;
	}

	public String getRobotName() {
		return robotName;
	}

	public void setRobotName(String robotName) {
		this.robotName = robotName;
	}

	public String getLastActive() {
		return lastActive;
	}

	public void setLastActive(String lastActive) {
		this.lastActive = lastActive;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStateProv() {
		return stateProv;
	}

	public void setStateProv(String stateProv) {
		this.stateProv = stateProv;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getRookieYear() {
		return rookieYear;
	}

	public void setRookieYear(int rookieYear) {
		this.rookieYear = rookieYear;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + ((lastActive == null) ? 0 : lastActive.hashCode());
		result = prime * result + ((leagueKey == null) ? 0 : leagueKey.hashCode());
		result = prime * result + ((regionKey == null) ? 0 : regionKey.hashCode());
		result = prime * result + ((robotName == null) ? 0 : robotName.hashCode());
		result = prime * result + rookieYear;
		result = prime * result + ((stateProv == null) ? 0 : stateProv.hashCode());
		result = prime * result + ((teamKey == null) ? 0 : teamKey.hashCode());
		result = prime * result + ((teamNameLong == null) ? 0 : teamNameLong.hashCode());
		result = prime * result + ((teamNameShort == null) ? 0 : teamNameShort.hashCode());
		result = prime * result + teamNumber;
		result = prime * result + ((website == null) ? 0 : website.hashCode());
		result = prime * result + ((zipCode == null) ? 0 : zipCode.hashCode());
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
		Team other = (Team) obj;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (lastActive == null) {
			if (other.lastActive != null)
				return false;
		} else if (!lastActive.equals(other.lastActive))
			return false;
		if (leagueKey == null) {
			if (other.leagueKey != null)
				return false;
		} else if (!leagueKey.equals(other.leagueKey))
			return false;
		if (regionKey == null) {
			if (other.regionKey != null)
				return false;
		} else if (!regionKey.equals(other.regionKey))
			return false;
		if (robotName == null) {
			if (other.robotName != null)
				return false;
		} else if (!robotName.equals(other.robotName))
			return false;
		if (rookieYear != other.rookieYear)
			return false;
		if (stateProv == null) {
			if (other.stateProv != null)
				return false;
		} else if (!stateProv.equals(other.stateProv))
			return false;
		if (teamKey == null) {
			if (other.teamKey != null)
				return false;
		} else if (!teamKey.equals(other.teamKey))
			return false;
		if (teamNameLong == null) {
			if (other.teamNameLong != null)
				return false;
		} else if (!teamNameLong.equals(other.teamNameLong))
			return false;
		if (teamNameShort == null) {
			if (other.teamNameShort != null)
				return false;
		} else if (!teamNameShort.equals(other.teamNameShort))
			return false;
		if (teamNumber != other.teamNumber)
			return false;
		if (website == null) {
			if (other.website != null)
				return false;
		} else if (!website.equals(other.website))
			return false;
		if (zipCode == null) {
			if (other.zipCode != null)
				return false;
		} else if (!zipCode.equals(other.zipCode))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Team [teamKey=" + teamKey + ", regionKey=" + regionKey + ", leagueKey=" + leagueKey + ", teamNumber="
				+ teamNumber + ", teamNameShort=" + teamNameShort + ", teamNameLong=" + teamNameLong + ", robotName="
				+ robotName + ", lastActive=" + lastActive + ", city=" + city + ", stateProv=" + stateProv
				+ ", zipCode=" + zipCode + ", country=" + country + ", rookieYear=" + rookieYear + ", website="
				+ website + "]";
	}

}
