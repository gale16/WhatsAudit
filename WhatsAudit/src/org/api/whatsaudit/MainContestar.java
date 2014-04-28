package org.api.whatsaudit;

import org.api.whatsaudit.FragmentLista.IListFragmentListener;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class MainContestar extends FragmentActivity implements IListFragmentListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_contestar);
		
		FragmentLista details = (FragmentLista) getSupportFragmentManager().findFragmentById(R.id.fragment1);
		details.mostrarTodosLosCuestionarios();
	}

	@Override
	public void onItemSelected(String item) {
		// TODO Auto-generated method stub
		
	}

}
