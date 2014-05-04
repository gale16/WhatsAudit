package org.api.whatsaudit;


import org.api.whatsaudit.FragmentLista.IListFragmentListener;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;

public class MainGestionar extends FragmentActivity implements IListFragmentListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_gestionar);
		FragmentLista details = (FragmentLista) getSupportFragmentManager().findFragmentById(R.id.fragment1);
		details.mostrarTodosLosCuestionarios();
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main_gestionar, menu);
		return true;
	}
	
	
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.Anadir:
				Intent intAnadir = new Intent(getApplicationContext(), MainAnadirPlantilla.class);
				startActivity(intAnadir);
				
				
			break;

			case R.id.Actualizar:
				FragmentLista details = (FragmentLista) getSupportFragmentManager().findFragmentById(R.id.fragment1);
				details.mostrarTodosLosCuestionarios();
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
