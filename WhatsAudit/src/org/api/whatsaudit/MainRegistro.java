package org.api.whatsaudit;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainRegistro extends Activity {

	private Button butAceptar;
	private EditText editUser, editPassword, editRepPassword;
	private String esAdmin;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		esAdmin = "Si";
		setContentView(R.layout.activity_main_registro);
		
		editUser = (EditText) findViewById(R.id.editTextUsuario);
		editPassword = (EditText) findViewById(R.id.editTextContrasena);
		editRepPassword = (EditText) findViewById(R.id.editTextRepContrasena);
		
		if(getIntent().getExtras().getString("TipoRegistro").compareTo("Usuario") == 0){
			esAdmin = "No";
		}
				
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
				String idUser, password;
				idUser = editUser.getText().toString();
				password = editPassword.getText().toString();
				
				if(comprobarSiNoVacio()){
					if(coincidenContrasenas()){
						if(usuarioDisponible()){
							LaBD.getMiBD(getApplicationContext()).insertarUsuario(Integer.valueOf(idUser), password, esAdmin);
							Toast.makeText(getApplicationContext(), "Te has registrado correctamente", 2000).show();
							MainRegistro.this.finish();
						}
						else{
							Toast.makeText(getApplicationContext(), "Ese usuario está escogido. Elija otro", 2000).show();
						}
					}
					else{
						Toast.makeText(getApplicationContext(), "No coinciden las contraseñas", 2000).show();
					}
				}
				else{
					Toast.makeText(getApplicationContext(), "Hay algún campo que está vacío", 2000).show();
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
		
		if(editPassword.getText().toString().compareTo(editRepPassword.getText().toString()) == 0 ){
			coinciden = true;
		}
		
		return coinciden;
		
	}
	
	private boolean usuarioDisponible() {
		boolean noUsuario = false;
		
		Cursor cursorUsuario = LaBD.getMiBD(getApplicationContext()).buscarUsuario(Integer.valueOf(editUser.getText().toString()));
		
		cursorUsuario.moveToFirst();
		if(cursorUsuario.getCount() <= 0){
			noUsuario = true;
		}
		return noUsuario;
	}




}
