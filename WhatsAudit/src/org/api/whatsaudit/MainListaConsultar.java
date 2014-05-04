package org.api.whatsaudit;

import org.api.whatsaudit.FragmentLista.IListFragmentListener;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class MainListaConsultar extends FragmentActivity implements IListFragmentListener{
	
	private int idUser;
	private boolean esAdmin;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_elegirconsultar);
		
		idUser = getIntent().getExtras().getInt("idUsuario");
				
		if(comprobarSiAdmin()){
			FragmentLista details = (FragmentLista) getSupportFragmentManager().findFragmentById(R.id.fragment1);
			details.mostrarTodosLosCuestionariosAdmin();
		}
		else{
			FragmentLista details = (FragmentLista) getSupportFragmentManager().findFragmentById(R.id.fragment1);
			details.mostrarTodosLosCuestionariosResp(idUser);
		}
			
		comprobarSiAdmin();
	}
	


	@Override
	public void onItemSelected(String item) {
		String cuestionario = item;
		String usuario = Integer.toString(idUser);
		if(esAdmin){
			String [] cuestionarios;
			cuestionarios = item.split(",");
			cuestionario = cuestionarios [0];
			usuario = cuestionarios[1];
		}
		
		Intent intentConsultar = new Intent(getApplicationContext(), MainConsultar.class);
		intentConsultar.putExtra("idUsuario", Integer.parseInt(usuario));
		intentConsultar.putExtra("CuestionarioContestado", cuestionario);
		startActivity(intentConsultar);
		
	}
	
	private boolean comprobarSiAdmin() {
		Cursor cursorAdmin = LaBD.getMiBD(getApplicationContext()).buscarUsuario(idUser);
		
		if(cursorAdmin.moveToFirst()){
			if(cursorAdmin.getString(2).equals("Si")){
				esAdmin = true;
			}
		}
		return esAdmin;
		
	}

}
