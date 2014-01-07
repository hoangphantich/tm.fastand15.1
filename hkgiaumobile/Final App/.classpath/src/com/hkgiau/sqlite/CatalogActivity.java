package com.hkgiau.sqlite;

import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.hkgiau.custom.Electric;
import com.hkgiau.custom.ElectricArrayAdapter;
import com.hkgiau.custom.ElectricView;

public class CatalogActivity extends ClassActivity{

	private ArrayList<Electric> arr;
	private Electric electric;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_catalog);
		
		//get ui
		final ListView ls_catalog=(ListView)findViewById(R.id.ls_catalog);		
		arr=new ArrayList<Electric>();
		//init data
		electric=new Electric("For personal consume", 1000);
		arr.add(electric);
		electric=new Electric("For office consume", 2000);
		arr.add(electric);
		electric=new Electric("For production consume", 3000);
		arr.add(electric);
		
		ElectricArrayAdapter myarr=new ElectricArrayAdapter(this, 1, arr);
		ls_catalog.setAdapter(myarr);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);	
		menu.getItem(1).setEnabled(false);
		return true;
	}
	void GetData()
	{
		//read data from xml
		try
		{
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db= dbf.newDocumentBuilder();
            Document doc = db.parse(getAssets().open("data.xml"));
            
			NodeList nodeList = doc.getElementsByTagName("item");
            for(int i=0; i<nodeList.getLength();i++){
                Node node = nodeList.item(i);
                if(node.getNodeType() == Node.ELEMENT_NODE){
                    electric = new Electric();
                    Element elm = (Element)node;
                    //Product name
                    NodeList nameList = elm.getElementsByTagName("name");
                    Element nameElement = (Element)nameList.item(0);
                    electric.setName(nameElement.getTextContent().trim());
                     
                    //Price
                    NodeList priceList = elm.getElementsByTagName("price");
                    Element priceElement = (Element)priceList.item(0);
                    electric.setPrice(Integer.parseInt(priceElement.getTextContent().trim()));                    
                    
                    //add to list
                    arr.add(electric);              
                }                
            }
		}catch(Exception e)
		{
			
		}
	}
}
