package org.api.whatsaudit;


import org.api.whatsaudit.FragmentLista.IListFragmentListener;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;

public class MainGestionar extends FragmentActivity implements IListFragmentListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_gestionar);
		
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main_gestionar, menu);
		return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.Anadir:
				FragmentLista details = (FragmentLista) getSupportFragmentManager().findFragmentById(R.id.fragment1);
				details.mostrarTodos();
				
			break;
			case R.id.Borrar:

			break;
				
			default:
				
				return super.onOptionsItemSelected(item);
		}
		return true;
				
		
	}


	@Override
	public void onItemSelected(String cuestionarioSeleccionado) {
		Intent intentPlantilla = new Intent(getApplicationContext(), MainPlantilla.class);
		intentPlantilla.putExtra("NombrePlantilla", cuestionarioSeleccionado);
		startActivity(intentPlantilla);
		
	}

}
