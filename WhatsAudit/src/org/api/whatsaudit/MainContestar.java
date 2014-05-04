package org.api.whatsaudit;


import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainContestar extends Activity{

	private String plantillaSelecc;
	private int usuarioContesta;
	
	private TextView preg1, preg2, preg3, titulo;
	private EditText resp1, resp2, resp3;
	private Button butAceptar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_contestar);
		
		plantillaSelecc = getIntent().getExtras().getString("PlantillaSeleccionada");
		usuarioContesta = getIntent().getExtras().getInt("Usuario");
		
		preg1 = (TextView) findViewById(R.id.textContPreg1);
		preg2 = (TextView) findViewById(R.id.textContPreg2);
		preg3 = (TextView) findViewById(R.id.textContPreg3);
		titulo = (TextView) findViewById(R.id.textContTitulo);
		resp1 = (EditText) findViewById(R.id.editContResp1);
		resp2 = (EditText) findViewById(R.id.editContResp2);
		resp3 = (EditText) findViewById(R.id.editContResp3);
		butAceptar = (Button) findViewById(R.id.butContAceptar);
		
		rellenarPreguntas();
		
		butAceptar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
					if(comprobarSiNoVacio()){
						insertarCuestionarioRespondido();
						Toast.makeText(getApplicationContext(), "El cuestionario se ha almacenado correctamente", 2000).show();
						MainContestar.this.finish();
					}
					else{
						Toast.makeText(getApplicationContext(), "Hay algún campo que está vacío", 2000).show();
					}
				}
		});

	}

	
	private void rellenarPreguntas(){
		Cursor cursorPreguntas = LaBD.getMiBD(getApplicationContext()).buscarPreguntasCuestionario(plantillaSelecc);
		titulo.setText(plantillaSelecc);
		
		if(cursorPreguntas.moveToFirst()){
			preg1.setText(cursorPreguntas.getString(0));
			preg2.setText(cursorPreguntas.getString(1));
			preg3.setText(cursorPreguntas.getString(2));			
		}
	}
	
	private boolean comprobarSiNoVacio() {
		boolean noVacio = false;
		if(preg1.getText().toString().compareTo("") != 0 && preg2.getText().toString().compareTo("") != 0 
				&& preg3.toString().compareTo("") != 0){
			noVacio = true;
		}
		
		return noVacio;
	}
	
	private void insertarCuestionarioRespondido(){
		String r1,r2,r3;
		
		r1 = resp1.getText().toString();
		r2 = resp2.getText().toString();
		r3 = resp3.getText().toString();
		
		LaBD.getMiBD(getApplicationContext()).insertarCuestionarioRespondido(plantillaSelecc, usuarioContesta, r1, r2, r3);;
	}
	

}
