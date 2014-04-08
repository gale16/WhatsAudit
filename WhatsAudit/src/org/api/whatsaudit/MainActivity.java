package org.api.whatsaudit;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.os.Build;

public class MainActivity extends Activity {
	
	private Button butEntrar;
	private EditText editUsuario;
	private EditText editContrase�a;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		editUsuario = (EditText) findViewById(R.id.editTextUsuario);
		editContrase�a = (EditText) findViewById(R.id.editTextContrasena);
		
		butEntrar = (Button) findViewById(R.id.buttonEntrar);
		butEntrar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				boolean invisible = false;
				Intent intentMenu = new Intent(MainActivity.this,MainActivityMenu.class);
				
				if(!editContrase�a.getText().toString().equals("admin")){
					invisible = true;
				}
				
				intentMenu.putExtra("Invisible", invisible);
				startActivity(intentMenu);
			}
		});
		
		

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}



}
