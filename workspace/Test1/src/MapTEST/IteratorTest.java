package com.neusoft.springtest.homework;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.neusoft.springtest.json.User;

public class IteratorTest {
	public static void main(String[] args) {
		// MAP读取
		Map<String, String> map = new HashMap<String, String>();
		map.put("key1", "value1");
		map.put("key2", "value2");
		// 将map集合中的映射关系取出，存入到set集合
		Iterator it = map.entrySet().iterator();
		while (it.hasNext()) {
			Entry e = (Entry) it.next();
			System.out.println("键" + e.getKey() + "的值为" + e.getValue());
		}
		Iterator itkeyMap = map.keySet().iterator();
		while (itkeyMap.hasNext()) {
		    Object key= itkeyMap.next();
			System.out.println("keySet方法读取的值"+map.get(key));
		}
		//set读取
		Set<String> objectSet = new HashSet<String>();  
        objectSet.add("white");  
        objectSet.add("white");  
        objectSet.add("green");  
        objectSet.add("yellow");  
        objectSet.add("blue");  
        objectSet.add("Yellow");  
        objectSet.add("red");  
        objectSet.add("white");  
        System.out.println("第一种方式读取set1 --------------------");  
        for (String item : objectSet) {  
            System.out.println(item);  
        } 
        //for 循环根据类型进行相应的转换
        for (Object obj : objectSet) {  
        	if(obj instanceof Integer){  
              int aa= (Integer)obj; 
              System.out.println(aa);
                
           }else if(obj instanceof String){  
             String aa = (String)obj;
             System.out.println(aa);
           } 
        	
        } 
  
        System.out.println("第二种方式读取set2 --------------------");  
        Iterator it1 = objectSet.iterator();  
        while (it1.hasNext()) {  
            System.out.println(it1.next());  
        }  
        
        //读取list
        List<User> list=new ArrayList<User>();
        User user = new User();  
        user.setName("小民");   
        user.setEmail("xiaomin@sina.com");  
        user.setAge(20);  
        
        User userb = new User();  
        userb.setName("小彬");   
        userb.setEmail("xiaobin@sina.com");  
        userb.setAge(20);  
        list.add(user);
		list.add(userb);
		Iterator it2=list.iterator();
		while(it2.hasNext()){
			User u=(User) it2.next();
			System.out.println("读取的LIST对象的属性值"+u.getName());
		}
		for(User userEntity:list){
			System.out.println("userEntity属性值"+userEntity.getName());
		}
		for(int i=0;i<list.size();i++){
			User u1=list.get(i);
			System.out.println("for循环2中的userEntity属性值"+u1.getName());
		}
		
		
	}
}
