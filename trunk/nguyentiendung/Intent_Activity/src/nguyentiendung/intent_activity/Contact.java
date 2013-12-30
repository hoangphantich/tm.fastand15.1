/**
 * 
 */
package nguyentiendung.intent_activity;

/**
 * @author GODKING
 *
 */
public class Contact {
	public String name;
	public int 	  photo;
	public Contact(String name, int photo) {
		super();
		this.name = name;
		this.photo = photo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	

}
