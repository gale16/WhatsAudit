package org.api.whatsaudit;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.os.Build;

public class MainRegistro extends Activity {

	private Button butAceptar;
	private EditText editUser, editPassword, editRepPassword;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_registro);
		
		editUser = (EditText) findViewById(R.id.editTextUsuario);
		editPassword = (EditText) findViewById(R.id.editTextContrasena);
		editRepPassword = (EditText) findViewById(R.id.editTextRepContrasena);
		
		butAceptar = (Button) findViewById(R.id.buttonAceptar);
		butAceptar.setOnClickListener(new View.OnClickListener() {
			
			/**
			 * Comprobar: 
			 * Que esten todos rellenos
			 * Contraseñan coinciden
			 * Que no haya un usuario con el mismo id
			 * 
			 * Insertar en la BD
			 * Alerta Registro oK
			 * 
			 */
			@Override
			public void onClick(View v) {
				if(comprobarSiNoVacio()){
					if(coincidenContrasenas()){
						if(usuarioDisponible()){
							
						}
					}
				}
				

				
				
			}

		});

	}
	

	private boolean comprobarSiNoVacio() {
		boolean noVacio = false;
		if(editUser.getText().toString().compareTo("") != 0 && editPassword.getText().toString().compareTo("") != 0 
				&& editRepPassword.toString().compareTo("") != 0){
			noVacio = true;
		}
		
		return noVacio;
	}
	
	private boolean coincidenContrasenas(){
		boolean coinciden = false;
		
		if(editPassword.getText().toString().compareTo(editRepPassword.toString()) == 0 ){
			coinciden = true;
		}
		
		return coinciden;
		
	}
	
	private boolean usuarioDisponible() {
		boolean noUsuario = false;
		
		Cursor cursorUsuario = LaBD.getMiBD(getApplicationContext()).buscarUsuario(editUser.getText().toString());
		
		cursorUsuario.moveToFirst();
		if(cursorUsuario.getCount() <= 0){
			noUsuario = true;
		}
		return noUsuario;
	}




}
