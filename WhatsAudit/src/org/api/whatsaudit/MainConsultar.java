package org.api.whatsaudit;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainConsultar extends Activity{

	private TextView txtTitulo, txtPreg1, txtPreg2, txtPreg3;
	private Button butBorrar;
	private String NombrePlantilla;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_consultar);
		
		txtTitulo = (TextView) findViewById(R.id.textNombrePlantilla);
		txtPreg1 = (TextView) findViewById(R.id.Pregunta1);
		txtPreg2 = (TextView) findViewById(R.id.Pregunta2);
		txtPreg3 = (TextView) findViewById(R.id.Pregunta3);
		
		NombrePlantilla = getIntent().getExtras().getString("NombrePlantilla");
		
		rellenarDatosPlantilla();
	}

	private void rellenarDatosPlantilla() {
		Cursor cuestCursor = LaBD.getMiBD(getApplicationContext()).buscarPreguntas(NombrePlantilla);
		
		txtTitulo.setText(NombrePlantilla);
		
		if(cuestCursor.moveToFirst()) {
			do {
				txtPreg1.setText(cuestCursor.getString(0).toString());
				txtPreg2.setText(cuestCursor.getString(1).toString());
				txtPreg3.setText(cuestCursor.getString(2).toString());
				
			} while(cuestCursor.moveToNext());
		}
		
	}

}
