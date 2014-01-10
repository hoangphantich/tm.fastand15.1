package com.group4.foodclass;

import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import android.app.Activity;
import android.content.Context;

public class Data extends Activity{
	private ArrayList<Food> arrFood;
	private Context context;	
	
	public Data(Context con, int index) {
		// TODO Auto-generated constructor stub
		context=con;
		this.setArrFood(GetData(index));
	}	
	public ArrayList<Food> getArrFood() {
		return arrFood;
	}

	public void setArrFood(ArrayList<Food> arrFood) {
		this.arrFood = arrFood;
	}
	public ArrayList<Food> GetData(int index) {
		// read data from xml
		ArrayList<Food> arr = new ArrayList<Food>();
		try {
			DocumentBuilderFactory fac=DocumentBuilderFactory.newInstance();
			DocumentBuilder db= fac.newDocumentBuilder();
			Document doc = db.parse(context.getAssets().open("data/"+index+".xml"));
			 
			Element root= doc.getDocumentElement(); 
			NodeList list= root.getChildNodes();
			for(int i=0;i<list.getLength();i++) 
			{
				Node node=list.item(i);
				if(node instanceof Element) 
				{
					Element elm=(Element) node;
					NodeList listChild= elm.getElementsByTagName("cat");
					int cat=Integer.parseInt(listChild.item(0).getTextContent().trim());
					listChild= elm.getElementsByTagName("subject");
					String subject=listChild.item(0).getTextContent().trim();
					listChild= elm.getElementsByTagName("title");
					String title=listChild.item(0).getTextContent().trim();
					listChild= elm.getElementsByTagName("code");
					int code=Integer.parseInt(listChild.item(0).getTextContent().trim());
					listChild= elm.getElementsByTagName("tp");
					String tp=listChild.item(0).getTextContent().trim();
					listChild= elm.getElementsByTagName("ccb");
					String ccb=listChild.item(0).getTextContent().trim();
					listChild= elm.getElementsByTagName("vh");
					String vh=listChild.item(0).getTextContent().trim();
					Food food = new Food(cat, subject, title, code, tp, ccb, vh);
					arr.add(food);
				}
			}
			  
		} catch (Exception e) {

		}
		return arr;			  
	}	
}
