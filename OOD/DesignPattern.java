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
	private static class ItemBuilder{
		private String name;
		private double rating;
		private String url;

		public void setName(String name) this.name = name;
		public void setRating(double rating) this.rating = rating;
		public void setURL(String url) this.url = url;
		public Item build() return new Item(this);
	}
}
