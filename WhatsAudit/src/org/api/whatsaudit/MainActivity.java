package org.api.whatsaudit;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends Activity {
	
	private Button butEntrar, butRegistro, butSalir;
	private EditText editUsuario, editContrasenia;
	private Intent intentMenu, intentRegistro;
	
	private boolean invisible;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		editUsuario = (EditText) findViewById(R.id.editTextUsuario);
		editContrasenia = (EditText) findViewById(R.id.editTextContrasena);
		
		butEntrar = (Button) findViewById(R.id.buttonEntrar);
		butEntrar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				boolean invisible = false;
				intentMenu = new Intent(MainActivity.this,MainMenu.class);
				String password = editContrasenia.getText().toString();
				
				if(!comprobarSiUsuarioVacio()){
				int idUsuario = Integer.parseInt(editUsuario.getText().toString());
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
				else{
					Toast.makeText(getApplicationContext(), "Campo de usuario vacío. Escriba su usuario", 2500).show();
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
		
		butSalir = (Button) findViewById(R.id.butSalir);
		butSalir.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				AlertDialog.Builder salirBuilder = new AlertDialog.Builder(MainActivity.this);
				salirBuilder.setTitle("Salir");
				salirBuilder.setMessage("¿Quieres salir de la aplicación?");
				salirBuilder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						MainActivity.this.finish();
						
					}
				});
				salirBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.cancel();
						
					}
				});
				salirBuilder.show();
				
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
	
	private boolean comprobarSiUsuarioVacio(){
		boolean vacio = false;
		if(editUsuario.getText().toString().compareTo("") == 0){
			vacio = true;
		}
		
		return vacio;
	}
}
