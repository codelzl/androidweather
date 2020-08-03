package Singleton;

public class MySingleton2 {

	private static MySingleton2 mysingleton2=new MySingleton2();
	protected MySingleton2()
	{
		System.out.println("start");
	}
	public static MySingleton2 getinstacne()
	{
		return mysingleton2;
	}
	
}
