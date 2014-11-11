package com.wiley.fordummies.androidsdk.tictactoe;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.widget.EditText;

public class Settings extends PreferenceActivity {

	private final static String OPT_NAME = "name";
	private final static String OPT_NAME_DEF = "Player";
	private static DatabaseHelper dh;
	

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.dh = new DatabaseHelper(this);

		getFragmentManager().beginTransaction()
				.replace(android.R.id.content, new SettingsFragment()).commit();
	}

	public static String getName(Context context) {
		return PreferenceManager.getDefaultSharedPreferences(context)
				.getString(OPT_NAME, OPT_NAME_DEF);
	}

	/**
	 * This fragment shows the preferences for the first header.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	public static class SettingsFragment extends PreferenceFragment {
		private EditText etPassword;
		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			// Load the preferences from an XML resource
			addPreferencesFromResource(R.xml.settings);
			//etPassword = (EditText) findViewById(R.id.password);
			
			dh.deleteAll();
			
			//dh.insert("user", R.xml.password);
		}
	}
}
