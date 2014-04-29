package org.api.whatsaudit;


import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.webkit.WebChromeClient.CustomViewCallback;
import android.widget.Button;
import android.widget.TextView;

public class MainConsultar extends Activity{

	private TextView txtTitulo, txtPreg1, txtPreg2, txtPreg3, txtResp1, txtResp2, txtResp3;
	private Button butBorrar;
	private String NombrePlantilla;
	private int idUser;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_consultar);
		
		idUser = getIntent().getExtras().getInt("idUsuario");
		NombrePlantilla = getIntent().getExtras().getString("CuestionarioContestado");
		
		txtTitulo = (TextView) findViewById(R.id.consulTitulo);
		txtPreg1 = (TextView) findViewById(R.id.consulPreg1);
		txtPreg2 = (TextView) findViewById(R.id.consulPreg2);
		txtPreg3 = (TextView) findViewById(R.id.consulPreg3);
		txtResp1 = (TextView) findViewById(R.id.consulRespues1);
		txtResp2 = (TextView) findViewById(R.id.consulRespues2);
		txtResp3 = (TextView) findViewById(R.id.consulRespues3);
		butBorrar = (Button) findViewById(R.id.butConsulBorrar);
		
		
		rellenarDatosPlantilla();
		

	}
	
	
	private void rellenarDatosPlantilla() {
		Cursor cuestCursor = LaBD.getMiBD(getApplicationContext()).buscarPreguntasCuestionario(NombrePlantilla);
		Cursor respCursor = LaBD.getMiBD(getApplicationContext()).buscarCuestionariosRespDeUsuario(idUser);
		
			txtTitulo.setText(NombrePlantilla);
			
			if(cuestCursor.moveToFirst() && respCursor.moveToFirst()) {
				do {
					txtPreg1.setText(cuestCursor.getString(0).toString());
					txtPreg2.setText(cuestCursor.getString(1).toString());
					txtPreg3.setText(cuestCursor.getString(2).toString());
					txtResp1.setText(respCursor.getString(0).toString());
					txtResp2.setText(respCursor.getString(0).toString());
					txtResp3.setText(respCursor.getString(0).toString());
					
				} while(cuestCursor.moveToNext());
			}			
		}	
		
	}


