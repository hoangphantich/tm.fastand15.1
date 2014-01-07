package nguyentiendung.example.totalapp;

public class NumberOfElectric {
	private float personalPrice;
	private float consumePrice;
	private float productPrice;
	private int	  totalNumber;
	private float vat;
	
	public float getVat() {
		return vat;
	}
	public void setVat(float vat) {
		this.vat = vat;
	}
	public float getPersonalPrice() {
		return personalPrice;
	}
	public void setPersonalPrice(float personalPrice) {
		this.personalPrice = personalPrice;
	}
	public float getConsumePrice() {
		return consumePrice;
	}
	public void setConsumePrice(float consumePrice) {
		this.consumePrice = consumePrice;
	}
	public float getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(float productPrice) {
		this.productPrice = productPrice;
	}
	public int getTotalNumber() {
		return totalNumber;
	}
	public void setTotalNumber(int totalNumber) {
		this.totalNumber = totalNumber;
	}
	public NumberOfElectric() {
		super();
	}
	

	public NumberOfElectric(float personalPrice, float consumePrice,
			float productPrice, int totalNumber, float vat) {
		super();
		this.personalPrice = personalPrice;
		this.consumePrice = consumePrice;
		this.productPrice = productPrice;
		this.totalNumber = totalNumber;
		this.vat = vat;
	}
	public float CalculateVAT(){
		return this.Calculate()*(1+vat);
	}
	public float Calculate(){
		
		float personal = 0.0f;
		float consume = 0.0f;
		float product = 0.0f;
		float temp = 0;
		
		if(totalNumber>=100) personal = 100*personalPrice; 
			else personal=totalNumber*personalPrice;
		if(totalNumber>=150) consume = 50*consumePrice; 
			else consume = ((totalNumber%100)*(totalNumber/100))*consumePrice;
		if(totalNumber>=200)  product = (totalNumber-150)*productPrice; 
			else product = ((totalNumber/150)*(totalNumber%150))*productPrice;
		temp=personal+consume+product;
		return temp;
		
	}
}
