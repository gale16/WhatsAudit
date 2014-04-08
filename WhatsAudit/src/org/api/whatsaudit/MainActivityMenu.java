package org.api.whatsaudit;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.os.Build;

public class MainActivityMenu extends Activity {
	
	private Button butConsultar;
	private Button butGestionar;
	private Button butContestar;
	private Button butRegistro;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_activity_menu);
		
		butConsultar = (Button) findViewById(R.id.buttonConsultar);
		butContestar = (Button) findViewById(R.id.buttonContestar);
		butGestionar = (Button) findViewById(R.id.buttonGestionar);
		butRegistro = (Button) findViewById(R.id.buttonRegistro);
		
		if(getIntent().getExtras().getBoolean("Invisible")){
			butGestionar.setVisibility(View.INVISIBLE);
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_activity_m, menu);
		return true;
	}



}
