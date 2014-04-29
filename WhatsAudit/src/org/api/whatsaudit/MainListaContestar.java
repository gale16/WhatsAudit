package org.api.whatsaudit;

import org.api.whatsaudit.FragmentLista.IListFragmentListener;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

public class MainListaContestar extends FragmentActivity implements IListFragmentListener {
	
	private int idUsuario;
	

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_elegirconsultar);
		
		idUsuario = getIntent().getExtras().getInt("idUsuario");
		
		FragmentLista details = (FragmentLista) getSupportFragmentManager().findFragmentById(R.id.fragment1);
		details.mostrarTodosLosCuestionarios();
	}

	
	
	@Override
	public void onItemSelected(String plantillaSelec) {
		
		if(!comprobarSiCuestionarioContestado(plantillaSelec)){
			Intent intentContestar = new Intent(getApplicationContext(), MainContestar.class);
			intentContestar.putExtra("PlantillaSeleccionada", plantillaSelec);
			intentContestar.putExtra("Usuario", idUsuario);
			startActivity(intentContestar);
		}
		else{
			Toast.makeText(getApplicationContext(), "Este cuestionario ya ha sido contestado ", 2000).show();
			
		}

	}
	
	private boolean comprobarSiCuestionarioContestado(String plantillaSelecc){
		boolean contestado = true;
		Cursor cursorCuestionario = LaBD.getMiBD(getApplicationContext()).comprobarSiCuestContestado(plantillaSelecc, idUsuario);
				
		if(cursorCuestionario.moveToFirst()){
			if(cursorCuestionario.getCount() == 0){
				contestado = false;
			}
		};
		
		return contestado;
				
	}


}
