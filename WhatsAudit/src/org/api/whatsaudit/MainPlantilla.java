package org.api.whatsaudit;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
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
				mostrarMensaje();	
			}
		});
	}
	
	private void mostrarPlantilla(){
		Cursor aCursor = LaBD.getMiBD(getApplicationContext()).buscarPreguntasCuestionario(nombrePlantilla);
		
		titulo.setText(nombrePlantilla);
		if(aCursor.moveToFirst()) {
			do {
				pregunta1.setText(aCursor.getString(0));
				pregunta2.setText(aCursor.getString(1));
				pregunta3.setText(aCursor.getString(2));
				
			} while(aCursor.moveToNext());
		}
	}
	
	private void mostrarMensaje() {
		AlertDialog.Builder borrarBuilder = new AlertDialog.Builder(this);
		borrarBuilder.setTitle("Borrar Plantilla");
		borrarBuilder.setMessage("¿Quieres borrar la plantilla?");
		borrarBuilder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				LaBD.getMiBD(getApplicationContext()).borrarPlantilla(nombrePlantilla);
				Toast.makeText(getApplicationContext(), "Se ha borrado correctamente", 2500).show();
				MainPlantilla.this.finish();
				dialog.cancel();	
			}
		});
		borrarBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
				
			}
		});
		borrarBuilder.show();
	}

}
