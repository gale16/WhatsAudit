package org.api.whatsaudit;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.os.Build;

public class MainRegistro extends Activity {

	private Button butAceptar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_registro);
		
		butAceptar = (Button) findViewById(R.id.buttonAceptar);
		butAceptar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// Meterlo en la BD
				//Alerta Registro Completado!!
				LaBD.getMiBD(getApplicationContext()).insertarUsuario(1, "hola", "No");
				MainRegistro.this.finish();
				
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_registro, menu);
		return true;
	}


}
