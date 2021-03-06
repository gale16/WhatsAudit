package org.api.whatsaudit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainMenu extends Activity {
	
	private Button butConsultar, butGestionar, butContestar, butNuevoAdmin;
	private int idUsuario;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_activity_menu);
		
		butConsultar = (Button) findViewById(R.id.buttonConsultar);
		butContestar = (Button) findViewById(R.id.buttonContestar);
		butGestionar = (Button) findViewById(R.id.buttonGestionar);
		butNuevoAdmin = (Button) findViewById(R.id.buttonNuevoAdmin);
		
		idUsuario = getIntent().getExtras().getInt("idUsuario");
		
		if(getIntent().getExtras().getBoolean("Invisible")){
			butGestionar.setVisibility(View.INVISIBLE);
			butNuevoAdmin.setVisibility(View.INVISIBLE);
		}
		else{
			butContestar.setVisibility(View.INVISIBLE);
		}
		
		butGestionar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intentGestionar = new Intent (getApplicationContext(),MainGestionar.class);
				intentGestionar.putExtra("idUsuario", idUsuario);
				startActivity(intentGestionar);	
			}
		});
		
		butConsultar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intentConsultar = new Intent (getApplicationContext(), MainListaConsultar.class);
				intentConsultar.putExtra("idUsuario", idUsuario);
				startActivity(intentConsultar);	
			}
		});
		
		butContestar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intentContestar = new Intent (getApplicationContext(), MainListaContestar.class);
				intentContestar.putExtra("idUsuario", idUsuario);
				startActivity(intentContestar);	
			}
		});
		
		butNuevoAdmin.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intentNuevoAdmin = new Intent(getApplicationContext(), MainRegistro.class);
				intentNuevoAdmin.putExtra("TipoRegistro", "Admin");
				startActivity(intentNuevoAdmin);
				
			}
		});

	}
	

}
