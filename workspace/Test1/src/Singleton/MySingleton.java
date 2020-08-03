package Singleton;

public class MySingleton {

	private static MySingleton mySingleton=null;
	private MySingleton() {
		// TODO Auto-generated constructor stub
		System.out.println("加载");
	}
	public static MySingleton getinstace()
	{
		if(mySingleton==null)
		{
			System.out.println("未创建");
			mySingleton=new MySingleton();
		}
		else
		{
			System.out.println("已经创建");
			
		}
		return mySingleton;
	}

}
