package Template;

public abstract class Dodish {
	//统一调用
	protected void Dodish()
	{
		this.before();
		this.doing();
		this.after();
	}

	public abstract void  before();
	public abstract void doing();
	public abstract void after();
}
