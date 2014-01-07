package nguyentiendung.example.totalapp;

public class KindOfElectric {
	private int id;
	private int number;
	private String money;
	private String porpose;
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getMoney() {
		return money;

	}
	public void setMoney(String money) {
		
		this.money = money;
	}
	public String getPorpose() {
		return porpose;
	}
	public void setPorpose(String porpose) {
		this.porpose = porpose;
	}
	public KindOfElectric(int id,int number, String money, String porpose) {
		super();
		this.id = id;
		this.number = number;
		this.money = money;
		this.porpose = porpose;
	}
	public KindOfElectric() {
		super();
	}
	
}
