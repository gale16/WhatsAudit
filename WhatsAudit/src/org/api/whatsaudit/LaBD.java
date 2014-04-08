package org.api.whatsaudit;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class LaBD extends SQLiteOpenHelper{

	private static LaBD miLaBD;
	private SQLiteDatabase db = getWritableDatabase();
	
	private LaBD(Context context, String name, CursorFactory factory, int version)  {
		super(context, name, factory, version);
	}
	
	public static LaBD getMiBD(Context context) {
		if(miLaBD == null) {
			miLaBD = new LaBD(context, "aDataBase", null, 1);
		 
		}
		
		return miLaBD;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE Usuarios ('idUser' INTEGER PRIMARY KEY	AUTOINCREMENT NOT NULL, 'Password' TEXT, 'Administardor' TEXT)");
		db.execSQL("CREATE TABLE Cuestionarios ('idCuestionario' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, 'idCreador' INTEGER FOREIGN KEY)");
		db.execSQL("CREATE TABLE Pregunta ('idPregunta' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, 'Pregunta' TEXT, 'idCuestionario' INTEGER FOREIGN KEY)");
		db.execSQL("CREATE TABLE Respuesta ('idPregunta' INTEGER PRIMARY FOREIGN KEY, 'idUser' INTEGER PRIMARY FOREIGN KEY, 'Respuesta' TEXT)");
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}
	
	public void insertarUsuario(Integer pId, String pPassword, String pAdmin){
		String sql = "INSERT INTO Usuarios ('idUser', 'Password', 'Administrador') VALUES ('"+ pId +"','"+ pPassword + "'," + pAdmin + "')";
		this.db.execSQL(sql);
	}
	
	public Cursor seleccionar(){
		String sql = "select * from Usuarios";
		return db.rawQuery(sql, null);
	}

	public void eliminar(String pNombre) {
		String sql = "DELETE FROM Usuarios WHERE Nombre ='" + pNombre + "'";
		this.db.execSQL(sql);
	}

	public void eliminar(int position) {
		String sql = "DELETE FROM Usuarios WHERE Codigo ='" + position + "'";
		this.db.execSQL(sql);
	}
}
