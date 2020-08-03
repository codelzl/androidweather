package MapTEST;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

public class HMTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Map<String,Person> map=new HashMap<String,Person>();
		List<String> ids=new LinkedList<String>();
		for(int i=0;i<10;i++){
			String uuid=UUID.randomUUID().toString().replace("-", "");
			ids.add(uuid);
			Person p=new Person(uuid,"person"+i,1000.0+i);
			map.put(uuid,p);
		}
		Iterator it=map.entrySet().iterator();
		while(it.hasNext()){
			Entry e=(Entry) it.next();
			System.out.println(e.getValue());
		}
		
		
		for(String id:ids){
		    System.out.println(id+map.get(id).getBalance()+map.get(id).getName());
		}
		
		
		Iterator IID=ids.iterator();
		while(IID.hasNext()){
			String id=(String) IID.next();
			System.out.println(id+map.get(id).getBalance()+map.get(id).getName());
		}
		
		
		List<String> a=new LinkedList<String>();
		a.add("student");
		a.add("teacher");
        Iterator<String> iterator=a.iterator();
        while(iterator.hasNext())
        {
      	System.out.println(iterator.next());
        }
	}

}
