package org.api.whatsaudit;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainAnadirPlantilla extends Activity{

	private Button butAnadir;
	private EditText edTitulo, edPreg1, edPreg2, edPreg3;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_anadirplantilla);
		
		edTitulo = (EditText) findViewById(R.id.tituloPlantilla);
		edPreg1 = (EditText) findViewById(R.id.AnadPregunta1);
		edPreg2 = (EditText) findViewById(R.id.AnadPregunta2);
		edPreg3 = (EditText) findViewById(R.id.AnadPregunta3);
		
		butAnadir = (Button) findViewById(R.id.butAnadirPlantilla);
		butAnadir.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				if(comprobarCamposLLenos()){
					Toast.makeText(getApplicationContext(), "CamposOK", 2500).show();
					if(nombrePlantillaDisponible((edTitulo.getText().toString()))){
						Toast.makeText(getApplicationContext(), "NombreOK", 2500).show();
						anadirPreguntas();
						Toast.makeText(getApplicationContext(), "La inserción se ha hecho correctamente", 2500).show();
						MainAnadirPlantilla.this.finish();
					}
					else{
						Toast.makeText(getApplicationContext(), "Ese nombre de cuestionario ya está en uso", 2500).show();
					}
				}
				
				else{
					Toast.makeText(getApplicationContext(), "Hay algún campo vacío", 2500).show();
				}
				
			}
		});
	}
	
	private void anadirPreguntas(){
		String titulo = edTitulo.getText().toString();
		
		LaBD.getMiBD(getApplicationContext()).insertarCuestionario(titulo);
		LaBD.getMiBD(getApplicationContext()).insertarPregunta(1, titulo, edPreg1.getText().toString());
		LaBD.getMiBD(getApplicationContext()).insertarPregunta(2, titulo, edPreg2.getText().toString());
		LaBD.getMiBD(getApplicationContext()).insertarPregunta(3, titulo, edPreg3.getText().toString());
	}
	
	private boolean nombrePlantillaDisponible(String pNombre) {
		boolean noPlantilla = false;
		Cursor cursorPlantilla = LaBD.getMiBD(getApplicationContext()).comprobarSiExistePlantilla(pNombre);
		
		cursorPlantilla.moveToFirst();
		
		if(cursorPlantilla.getCount() <= 0){
			noPlantilla = true;
		}
		return noPlantilla;
	}
	
	private boolean comprobarCamposLLenos(){
		boolean todoLleno = false;
		
		if(edTitulo.getText().toString().compareTo("") != 0 && edPreg1.getText().toString().compareTo("") != 0 &&
			edPreg2.getText().toString().compareTo("") != 0 && edPreg3.getText().toString().compareTo("") != 0)
		{
			todoLleno = true;
		}
		return todoLleno;
	}

}
