package org.api.whatsaudit;

import org.api.whatsaudit.FragmentLista.IListFragmentListener;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Button;
import android.widget.TextView;

public class MainConsultar extends FragmentActivity implements IListFragmentListener{

	private TextView txtTitulo, txtPreg1, txtPreg2, txtPreg3, txtResp1, txtResp2, txtResp3;
	private Button butBorrar;
	private String NombrePlantilla;
	private int idUser;
	private boolean esAdmin;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_elegirconsultar);
		
		idUser = getIntent().getExtras().getInt("idUsuario");
		
//		txtTitulo = (TextView) findViewById(R.id.textNombrePlantilla);
//		txtPreg1 = (TextView) findViewById(R.id.consulPreg1);
//		txtPreg2 = (TextView) findViewById(R.id.consulPreg2);
//		txtPreg3 = (TextView) findViewById(R.id.consulPreg3);
//		txtResp1 = (TextView) findViewById(R.id.consulRespues1);
//		txtResp2 = (TextView) findViewById(R.id.consulRespues2);
//		txtResp3 = (TextView) findViewById(R.id.consulRespues3);
//		butBorrar = (Button) findViewById(R.id.butConsulBorrar);
		
		//NombrePlantilla = getIntent().getExtras().getString("NombrePlantilla");
		
		//rellenarDatosPlantilla();
		
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



	private void rellenarDatosPlantilla() {
		Cursor cuestCursor = LaBD.getMiBD(getApplicationContext()).buscarPreguntasCuestionario(NombrePlantilla);
		
		if(!esAdmin){
			txtTitulo.setText(NombrePlantilla);
			
			if(cuestCursor.moveToFirst()) {
				do {
					txtPreg1.setText(cuestCursor.getString(0).toString());
					txtPreg2.setText(cuestCursor.getString(1).toString());
					txtPreg3.setText(cuestCursor.getString(2).toString());
					
				} while(cuestCursor.moveToNext());
			}			
		}
		else{
			
		}
		
		
	}

	@Override
	public void onItemSelected(String item) {
		
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
