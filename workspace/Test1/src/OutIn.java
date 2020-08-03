public class OutIn {

	private class inner implements IA{
		public void dothis() {
			System.out.println("sdsd");

		}

		@Override
		public void methodA() {
			// TODO Auto-generated method stub
			
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		OutIn out = new OutIn();
		OutIn.inner in = out.new inner();
		in.dothis();
	}

}
