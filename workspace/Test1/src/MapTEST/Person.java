package MapTEST;

/**
 * Created by admin on 2017/8/8.
 */
public class Person {
    private String id;
    private String name;
    private Double balance;

   

    public Person(String id, String name, Double balance) {
		super();
		this.id = id;
		this.name = name;
		this.balance = balance;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
