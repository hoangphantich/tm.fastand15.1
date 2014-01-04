package com.activity.shop;

import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;


public class ShopActivity extends Activity {

	Product product=null;
	private static int MENU_INFO=0;
	private static int MENU_PAY=1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shop);
		final ArrayList<Product> arr=new ArrayList<Product>();	
		
		//clear all data
		SharedPreferences pre=getSharedPreferences("mydata", MODE_PRIVATE);
		SharedPreferences.Editor editor=pre.edit();
		editor.clear();
		editor.commit();

		//read data from xml
		try
		{
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db= dbf.newDocumentBuilder();
            Document doc = db.parse(getAssets().open("data.xml"));
            
			NodeList nodeList = doc.getElementsByTagName("item");
            for(int i=0; i<nodeList.getLength(); i++){
                Node node = nodeList.item(i);
                if(node.getNodeType() == Node.ELEMENT_NODE){
                    product = new Product();
                    Element elm = (Element)node;
                    //Product name
                    NodeList productList = elm.getElementsByTagName("product");
                    Element productElement = (Element)productList.item(0);
                    product.setProduct(productElement.getTextContent().trim());
                     
                    //Price
                    NodeList priceList = elm.getElementsByTagName("price");
                    Element priceElement = (Element)priceList.item(0);
                    int price=Integer.valueOf(priceElement.getTextContent().trim());
                    product.setPrice(price);
                    
                    //Image
                    NodeList imgList = elm.getElementsByTagName("picture");
                    Element imgElement = (Element)imgList.item(0);
                    product.setPicture(imgElement.getTextContent().trim()); 
//                    Toast.makeText(ShopActivity.this, imgElement.getTextContent(), Toast.LENGTH_LONG).show();
                    
                    //add to list
                    arr.add(product);
                }
            }
		}catch(Exception e)
		{
			
		}
		final ListView ls_product=(ListView)findViewById(R.id.ls_product);
		MyArrayAdapter myarr=new MyArrayAdapter(this, 1, arr);
		ls_product.setAdapter(myarr);

		//list onitemclick
		ls_product.setOnItemClickListener(new AdapterView.OnItemClickListener() {
		
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) 
			{
			        ProductView productV = (ProductView) view;
//			        Toast.makeText(ShopActivity.this, "You buy \"" + productV.lb_product.getText() +"\"", Toast.LENGTH_LONG).show();
			        //put intent
			        Product product=(Product)ls_product.getItemAtPosition(position);
			        product.setQuantity(1);
			        Intent itshop= new Intent(ShopActivity.this, ResultActivity.class);
			        itshop.putExtra("product", product);
	                startActivity(itshop);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.

		//default menu settings
//		getMenuInflater().inflate(R.menu.shop, menu);
		
		//add menu
		menu.add(0, MENU_INFO, 0, "Info");
		menu.add(0, MENU_PAY, 1,"Pay");
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		
		//Event of menu
		int id=item.getItemId();
		switch (id) {
		case 0:
			startActivity(new Intent(this,ResultActivity.class));
			break;
		case 1:
			startActivity(new Intent(this, PayActivity.class));
			break;
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

}
