package com.wiley.fordummies.androidsdk.tictactoe;

import java.util.List;

import com.wiley.fordummies.androidsdk.tictactoe.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Login extends Activity implements OnClickListener {
	private DatabaseHelper dh;
	private EditText passwordEditableField;
	private final static String OPT_NAME = "name";
	private String user = "user";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		this.dh = new DatabaseHelper(this);

		if(this.dh.getSize() == 0){
			startActivity(new Intent(this, Account.class));
		}
		
		passwordEditableField = (EditText) findViewById(R.id.password_text);
		View btnLogin = (Button) findViewById(R.id.login_button);
		btnLogin.setOnClickListener(this);
		View btnCancel = (Button) findViewById(R.id.cancel_button);
		btnCancel.setOnClickListener(this);
	}

	private void checkLogin() {
		String password = this.passwordEditableField.getText().toString();
		List<String> names = this.dh.selectAll(user, password);
		if (names.size() > 0) { // Login successful
			// Save username as the name of the player
			SharedPreferences settings = PreferenceManager
					.getDefaultSharedPreferences(this);
			SharedPreferences.Editor editor = settings.edit();
			editor.putString(OPT_NAME, user);
			editor.commit();

			// Bring up the GameOptions screen
			startActivity(new Intent(this, GameOptions.class));
//			 startActivity(new Intent(this, DummyActivity.class));
			finish();
		} else {
			// Try again?
			new AlertDialog.Builder(this)
					.setTitle("Error")
					.setMessage("Login failed")
					.setNeutralButton("Try Again",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int which) {
								}
							}).show();
		}
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.login_button:
			checkLogin();
			break;
		case R.id.cancel_button:
			finish();
			break;
		}
	}
}
