//design pattern
public class Singleton{
	//initial as null
	private static final Singleton instance = null;
	private List<Level> levels;
	//private constructor
	private Singleton() {
		levels = new ArrayList<Level>();
	}
	//set global getter
	public static synchronized Singleton getInstance() {
		if (instance == null) instance = new Singleton();
		return instance;
	}
}


//factory pattern
public class DBFactory{
	private static final String DEFAULT = "mysql";
	public DBConnection getConnection(String db){
		switch(db){
		case "mysql":
			return new MySqlConnection();
		case "mongodb":
			return null;
		default:
			return new IllegalArgumentException("invalid input: " + db);
		}
	}
	public DBConnection getConnection(){
		return getConnection(DEFAULT);
	}
}

//builder pattern
public class Item{
	private String name;
	private double rating;
	private String url;
	//private constructor
	private Item(ItemBuilder ib) {
		this.name = ib.name;
		this.rating = ib.rating;
		this.url = ib.url;
	}
	//public getter methods, only allows read fields
	public String getName() return name;
	public double getRating() return rating;
	public String geturl() return url;

	//public builder class with optional fields to be set(some setter and a build methods)
	//other classes just call ItemBuilder constructor to new ItemBuilder
	private static class ItemBuilder{//this class should be public, right? yes
		private String name;
		private double rating;
		private String url;

		public void setName(String name) this.name = name;
		public void setRating(double rating) this.rating = rating;
		public void setURL(String url) this.url = url;
		public Item build() return new Item(this);
	}
}


//builder pattern example2.
package com.gkatzioura.design.creational.builder;
		import java.util.HashSet;
		import java.util.Set;

public class Email {
	private final String title;
	private final String recipients;
	private final String message;
	private Email(String title, String recipients, String message) {
		this.title = title;
		this.recipients = recipients;
		this.message = message;
	}
	public String getTitle() {
		return title;
	}
	public String getRecipients() {
		return recipients;
	}
	public String getMessage() {
		return message;
	}
	public void send() {
	}
	public static class EmailBuilder {
		private Set recipients = new HashSet();
		private String title;
		private String greeting;
		private String mainText;
		private String closing;
		public EmailBuilder addRecipient(String recipient) {
			this.recipients.add(recipient);
			return this;
		}
		public EmailBuilder removeRecipient(String recipient) {
			this.recipients.remove(recipient);
			return this;
		}
		public EmailBuilder setTitle(String title) {
			this.title = title;
			return this;
		}
		public EmailBuilder setGreeting(String greeting) {
			this.greeting = greeting;
			return this;
		}
		public EmailBuilder setMainText(String mainText) {
			this.mainText = mainText;
			return this;
		}
		public EmailBuilder setClosing(String closing) {
			this.closing = closing;
			return this;
		}
		public Email build() {
			String message = greeting+"\n"+mainText+"\n"+closing;
			String recipientSection = commaSeparatedRecipients();
			return new Email(title,recipientSection,message);
		}
		private String commaSeparatedRecipients() {
			StringBuilder sb = new StringBuilder();
			for(String recipient:recipients) {
				sb.append(",").append(recipient);
			}
			return sb.toString().replaceFirst(",","");
		}
	}
}