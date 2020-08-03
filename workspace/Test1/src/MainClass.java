
public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
     
     Student s1=  new Student();
     s1.setAge(3);
     s1.setClassNo("r2");
     s1.setName("R2");
     s1.doSomething();
       
     String a="500";
     String b="300";
    
    
     
     long d=999;
     String e=String.valueOf(d);
     float f=Float.valueOf(a);
     double h=Double.valueOf(a);
     a.equals(b);
     a.toUpperCase();
    int c= a.indexOf("5");
    System.out.println("iiii"+c);
    String h1=a.trim();
    
    String indextest="english";
    if(indextest.indexOf("li")!=-1){
    	System.out.println("position:"+indextest.indexOf("li"));
    }else{
    	System.out.println("not found");
    }
    if(indextest.lastIndexOf("li")!=-1){
    	System.out.println("last position:"+indextest.lastIndexOf("li"));
    }else{
    	System.out.println("not found");
    	
    }
    System.out.println("lentgh:"+indextest.length());
    
    if("english".equals(indextest)){
    	System.out.println("相等");
    }
    if("ENGLISH".equals(indextest)){
    	System.out.println("相等");
    }else{
    	System.out.println("not相等");
    }
    if("ENGLISH".equalsIgnoreCase(indextest)){
    	System.out.println("相等");
    }else{
    	System.out.println("not相等");
    }
    if("ENGLISH".equalsIgnoreCase(indextest.toUpperCase())){
    	System.out.println("相等");
    }else{
    	System.out.println("not相等");
    }
    if("ENGLISH".toLowerCase().equalsIgnoreCase(indextest.toUpperCase())){
    	System.out.println("相等");
    }else{
    	System.out.println("not相等");
    }

    char c1=indextest.charAt(0);
    System.out.println(String.valueOf(c1));
    System.out.println(indextest.substring(2));
    System.out.println(indextest.substring(2,indextest.length()));
    StringBuffer h2 = new StringBuffer();
    h2.append("98ge");
    h2.insert(1, "c");
    System.out.println(h2);

    String str = "This is yiibai";
    
    // prints character at 1st location
    System.out.println(str.charAt(0));
           
    // prints character at 5th location i.e white-space character
    System.out.println(str.charAt(4));
           
    // prints character at 18th location 
    System.out.println(str.charAt(17));
     
     
	}

}
