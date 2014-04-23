package org.api.whatsaudit;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainPlantilla extends Activity {
	
	private TextView titulo, pregunta1, pregunta2, pregunta3; 
	private Button butBorrarPlantilla;
	private String nombrePlantilla;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_plantilla);
		
		nombrePlantilla = getIntent().getExtras().getString("NombrePlantilla");
		
		titulo = (TextView) findViewById(R.id.textNombrePlantilla);
		pregunta1 = (TextView) findViewById(R.id.Pregunta1);
		pregunta2 = (TextView) findViewById(R.id.Pregunta2);
		pregunta3 = (TextView) findViewById(R.id.Pregunta3);
		
		mostrarPlantilla();
		
		butBorrarPlantilla = (Button) findViewById(R.id.butBorrarPlantilla);
		butBorrarPlantilla.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				LaBD.getMiBD(getApplicationContext()).borrarPlantilla(nombrePlantilla);
				Toast.makeText(getApplicationContext(), "Se ha borrado correctamente", 2500).show();
				
				MainPlantilla.this.finish();
				
			}
		});
	}
	
	private void mostrarPlantilla(){
		Cursor aCursor = LaBD.getMiBD(getApplicationContext()).buscarPreguntas(nombrePlantilla);
		
		titulo.setText(nombrePlantilla);
		if(aCursor.moveToFirst()) {
			pregunta1.setText(aCursor.getString(0));
			aCursor.moveToNext();
			pregunta2.setText(aCursor.getString(0));
			aCursor.moveToNext();
			pregunta3.setText(aCursor.getString(0));
			aCursor.moveToNext();
		}
	}

}
