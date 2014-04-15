package org.api.whatsaudit;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class FragmentLista extends ListFragment {

	public interface IListFragmentListener {
		void onItemSelected(String item);
	}
	
	private IListFragmentListener iLista;
	private ArrayList<String> datos;
	private ArrayAdapter<String> adaptador;
	
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);

		iLista.onItemSelected(adaptador.getItem(position).toString());	
		
		mostrarTodos();
		
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
			
		datos = new ArrayList<String>();
		adaptador = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, datos);
		setListAdapter(adaptador);
	}

		
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		return inflater.inflate(R.layout.fragment_fragmentolista, container, false);
	}
	

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		
		try{
			iLista = (IListFragmentListener) activity;
		} catch (ClassCastException e) {}
		}

	
	public void mostrarTodos(){
		// Para probar
		Cursor aCursor = LaBD.getMiBD(getActivity()).seleccionarTodosUsuarios();
		
		//Cursor aCursor = LaBD.getMiBD(getActivity()).seleccionarTodosCuestionarios();
		adaptador.clear();
		String nombre = "";
		
		if(aCursor.moveToFirst()) {
			do {
				nombre = aCursor.getString(0);
				adaptador.add(nombre);
			} while(aCursor.moveToNext());
		}
	}
}
	
	

