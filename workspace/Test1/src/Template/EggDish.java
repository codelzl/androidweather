package Template;

public class EggDish extends Dodish{

	@Override
	public void before() {
		// TODO Auto-generated method stub
		System.out.println("B");
		
	}

	@Override
	public void doing() {
		// TODO Auto-generated method stub
		System.out.println("d");
		
	}

	@Override
	public void after() {
		// TODO Auto-generated method stub
		System.out.println("A");
	}

}
