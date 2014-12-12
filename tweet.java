package tweetanalyse;

public class tweet {
	private String created_at,lang;
	private Boolean favorited,retweeted;
	private Integer favourites_count,retweet_count,followers_count;
	 
	 
	public String getcreated_at() {
		return created_at;
	}
 
	public void setcreated_at(String created_at) {
		this.created_at = created_at;
	}
	
	public String getlang() {
		return lang;
	}
 
	public void setlang(String lang) {
		this.lang = lang;
	}
	
	public Boolean getfavorited() {
		return favorited;
	}
 
	public void setfavorited(Boolean favorited) {
		this.favorited = favorited;
	}
	
	public Boolean getretweeted() {
		return retweeted;
	}
 
	public void setretweeted(Boolean retweeted) {
		this.retweeted = retweeted;
	}
	
	public Integer getfavourites_count() {
		return favourites_count;
	}
 
	public void setfavourites_count(Integer favourites_count) {
		this.favourites_count = favourites_count;
	}
	
	public Integer getretweet_count() {
		return retweet_count;
	}
 
	public void setretweet_count(Integer retweet_count) {
		this.retweet_count = retweet_count;
	}
	
	public Integer getfollowers_count() {
		return followers_count;
	}
 
	public void setfollowers_count(Integer followers_count) {
		this.followers_count = followers_count;
	}

}
