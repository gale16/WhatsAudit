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
	private Button butRegistro;
	private EditText editUsuario;
	private EditText editContraseña;
	private Intent intentMenu;
	private Intent intentRegistro;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		editUsuario = (EditText) findViewById(R.id.editTextUsuario);
		editContraseña = (EditText) findViewById(R.id.editTextContrasena);
		
		//Borrar cuando no se necesite
		editUsuario.setText("admin");
		editContraseña.setText("admin");
		
		butEntrar = (Button) findViewById(R.id.buttonEntrar);
		butEntrar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				boolean invisible = false;
				intentMenu = new Intent(MainActivity.this,MainMenu.class);
				
				if(!editContraseña.getText().toString().equals("admin")){
					invisible = true;
				}
				
				intentMenu.putExtra("Invisible", invisible);
				startActivity(intentMenu);
			}
		});
		
		butRegistro = (Button) findViewById(R.id.buttonRegistro);
		butRegistro.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				intentRegistro = new Intent(MainActivity.this, MainRegistro.class);
				startActivity(intentRegistro);
				
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
