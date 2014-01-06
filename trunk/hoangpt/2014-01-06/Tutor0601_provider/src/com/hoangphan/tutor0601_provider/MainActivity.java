package com.hoangphan.tutor0601_provider;

import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.widget.SimpleCursorAdapter;
import android.app.Activity;
import android.app.ListActivity;
import android.database.Cursor;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// get ui

		// get provider (system provider: contact/phone, media, browser
		Uri allContacts = ContactsContract.Contacts.CONTENT_URI; //constant
		Uri allContacts1 = Uri.parse("content://contacts/people");
		Uri allContacts2 = Uri.parse("content://contacts/people/1");

		// get data == url provide
		String[] projection = new String[] { ContactsContract.Contacts._ID,
				ContactsContract.Contacts.DISPLAY_NAME,
				ContactsContract.Contacts.HAS_PHONE_NUMBER };
		String selection = ContactsContract.Contacts.DISPLAY_NAME + " LIKE ?";
		String[] option = new String[] { "%" };
		String orders = ContactsContract.Contacts.DISPLAY_NAME + " ASC";

		Cursor c = managedQuery(allContacts, projection, selection, option,
				orders);

		// print data
		String[] cols = new String[] { ContactsContract.Contacts.DISPLAY_NAME,
				ContactsContract.Contacts._ID };

		int[] views = new int[] { R.id.contactName, R.id.contactID };

		// bind to list
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
				R.layout.activity_main, c, cols, views);
		this.setListAdapter(adapter);

		_printContacts(c);

	}

	private void _printContacts(Cursor c) {
		// using cursor
		if (c.moveToFirst()) {
			do {
				String contactID = c.getString(c
						.getColumnIndex(ContactsContract.Contacts._ID));
				String contactDisplayName = c
						.getString(c
								.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));

				// check contact with phone number
				int hasPhone = c
						.getInt(c
								.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));
				if (hasPhone == 1) {
					Cursor phoneCursor = getContentResolver().query(
							ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
							null,
							ContactsContract.CommonDataKinds.Phone.CONTACT_ID
									+ " = " + contactID, null, null);
					while (phoneCursor.moveToNext()) {
						Log.v("Phone",
								phoneCursor.getString(phoneCursor
										.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)));
					}
					phoneCursor.close();
				}

			} while (c.moveToNext());
		}
		
		//c.close(); //safe-thread
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
