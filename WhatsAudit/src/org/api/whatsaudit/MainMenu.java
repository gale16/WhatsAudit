package org.api.whatsaudit;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.os.Build;

public class MainMenu extends Activity {
	
	private Button butConsultar;
	private Button butGestionar;
	private Button butContestar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_activity_menu);
		
		butConsultar = (Button) findViewById(R.id.buttonConsultar);
		butContestar = (Button) findViewById(R.id.buttonContestar);
		butGestionar = (Button) findViewById(R.id.buttonGestionar);

		
		if(getIntent().getExtras().getBoolean("Invisible")){
			butGestionar.setVisibility(View.INVISIBLE);
		}
		
		butGestionar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intentGestionar = new Intent (getApplicationContext(),MainGestionar.class);
				startActivity(intentGestionar);	
			}
		});
		
		butConsultar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intentConsultar = new Intent (getApplicationContext(),MainConsultar.class);
				startActivity(intentConsultar);	
			}
		});
		
		butContestar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intentContestar = new Intent (getApplicationContext(),MainContestar.class);
				startActivity(intentContestar);	
			}
		});

	}
}
