package org.api.whatsaudit;

import org.api.whatsaudit.FragmentLista.IListFragmentListener;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainListaConsultar extends FragmentActivity implements IListFragmentListener{
	
	private TextView txtTitulo, txtPreg1, txtPreg2, txtPreg3, txtResp1, txtResp2, txtResp3;
	private Button butBorrar;
	private String NombrePlantilla;
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
			Toast.makeText(getApplicationContext(), "No entra", 3000).show();
		}
			
		comprobarSiAdmin();
	}
	


	@Override
	public void onItemSelected(String item) {
		Intent intentConsultar = new Intent(getApplicationContext(), MainConsultar.class);
		intentConsultar.putExtra("idUsuario", idUser);
		intentConsultar.putExtra("CuestionarioContestado", item);
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
