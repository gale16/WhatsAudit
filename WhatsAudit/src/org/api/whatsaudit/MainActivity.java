package org.api.whatsaudit;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.os.Build;

public class MainActivity extends Activity {
	
	private Button butEntrar;
	private Button butRegistro;
	private EditText editUsuario;
	private EditText editContraseña;
	private Intent intentMenu;
	private Intent intentRegistro;
	
	private boolean invisible;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		editUsuario = (EditText) findViewById(R.id.editTextUsuario);
		editContraseña = (EditText) findViewById(R.id.editTextContrasena);
		
		//Borrar cuando no se necesite
		editUsuario.setText("0");
		editContraseña.setText("admin");
		
		butEntrar = (Button) findViewById(R.id.buttonEntrar);
		butEntrar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				boolean invisible = false;
				intentMenu = new Intent(MainActivity.this,MainMenu.class);
				int idUsuario = Integer.parseInt(editUsuario.getText().toString());
				String password = editContraseña.getText().toString();
				
				if(!existeUsuario(idUsuario)){
					if(coincidenContraseñas(idUsuario, password)){
						if(!comprobarSiAdmin(idUsuario)){
							invisible = true;
						}					
						intentMenu.putExtra("Invisible", invisible);
						intentMenu.putExtra("idUsuario", idUsuario);
						startActivity(intentMenu);
					}
					else{
						Toast.makeText(getApplicationContext(), "La contraseña es incorrecta", 2500).show();
					}
				}
				else{
					Toast.makeText(getApplicationContext(), "El usuario no existe", 2500).show();
				}
			}
		});
		
		butRegistro = (Button) findViewById(R.id.buttonRegistro);
		butRegistro.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				intentRegistro = new Intent(MainActivity.this, MainRegistro.class);
				intentRegistro.putExtra("TipoRegistro", "Usuario");
				startActivity(intentRegistro);
			}
		});
		

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private boolean comprobarSiAdmin(int pIdUser) {
		boolean esAdmin = false;
		Cursor cursorAdmin = LaBD.getMiBD(getApplicationContext()).buscarUsuario(pIdUser);
		
		if(cursorAdmin.moveToFirst()){
			if(cursorAdmin.getString(2).equals("Si")){
				esAdmin = true;
			}
		}
		return esAdmin;
	}
	
	private boolean coincidenContraseñas(int pUsuario, String pContraseña){
		boolean coinciden = false;
		Cursor cursorPassword = LaBD.getMiBD(getApplicationContext()).buscarUsuario(pUsuario);
		
		if(cursorPassword.moveToFirst()){
			if(cursorPassword.getString(1).compareTo(pContraseña) == 0){
				coinciden = true;
			}
		}
		return coinciden; 
	}
	
	private boolean existeUsuario(int pUsuario){
		boolean noExisteUsuario = false;
		Cursor cursorUsuario = LaBD.getMiBD(getApplicationContext()).buscarUsuario(pUsuario);
		
		cursorUsuario.moveToFirst();
		if(cursorUsuario.getCount() <= 0){
			noExisteUsuario = true;
		}

		return noExisteUsuario;
	}
}
