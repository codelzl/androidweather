package MapTEST;

import java.util.LinkedList;

public class MapTest {
	LinkedList<Integer> queue=new LinkedList();
	public void put(int data)
	{
		this.queue.addLast(data);
		
	}
	
	public int get()
	{
		return this.queue.removeFirst();
	}
	
	
	public boolean isEmpty()
	{
		return this.queue.isEmpty();
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MapTest q1=new MapTest();
		q1.put(3);
		if(!q1.isEmpty())
		{
			System.out.println("不空");
			int data=	q1.get();
			System.out.println(data);
		}
		else 
		{
			System.out.println("empty");
		}
	 
		if(!q1.isEmpty())
		{
			System.out.println("不空");
		}
		else 
		{
			System.out.println("empty");
		}
	}

}
