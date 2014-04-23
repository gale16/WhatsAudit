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
		db.execSQL("CREATE TABLE Usuarios ('idUser' INTEGER PRIMARY KEY	AUTOINCREMENT NOT NULL, 'Password' TEXT, 'Administrador' TEXT)");
		db.execSQL("CREATE TABLE Cuestionarios ('NombreCuestionario' TEXT PRIMARY KEY NOT NULL, 'idCreador' INTEGER, FOREIGN KEY (idCreador) REFERENCES Usuarios(idUser))");
		db.execSQL("CREATE TABLE Pregunta ('idPregunta' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, 'Pregunta' TEXT, 'idCuestionario'  INTEGER, FOREIGN KEY (idCuestionario) REFERENCES Cuestionarios(NombreCuestionario))");
		db.execSQL("CREATE TABLE Respuesta ('idPregunta' INTEGER , 'idUser' INTEGER , 'Respuesta' TEXT, foreign key (idPregunta) references pregunta(idPregunta), FOREIGN KEY (idUser) REFERENCES Usuarios(idUser), PRIMARY KEY ('idPregunta','idUser') )");
		
		db.execSQL("INSERT INTO 'Usuarios' (idUser, Password, Administrador) VALUES (1,'admin', 'si')");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}
	
	
	// TABLA USUARIOS
	public void insertarUsuario(Integer pId, String pPassword, String pAdmin){
		String sql = "INSERT INTO Usuarios ('idUser', 'Password', 'Administrador') VALUES ("+ pId +",'"+ pPassword + "','" + pAdmin + "')";
		db.execSQL(sql);
	}
	
	public Cursor seleccionar(){
		String sql = "SELECT * FROM Usuarios";
		return db.rawQuery(sql, null);
	}

	public void eliminar(String pNombre) {
		String sql = "DELETE FROM Usuarios WHERE Nombre ='" + pNombre + "'";
		db.execSQL(sql);
	}

	public void eliminar(int position) {
		String sql = "DELETE FROM Usuarios WHERE Codigo ='" + position + "'";
		db.execSQL(sql);
	}
	
	public Cursor buscarUsuario(int pUser){
		String sql = "SELECT * FROM Usuarios WHERE idUser ='" + pUser + "'";
		return db.rawQuery(sql, null);
		
	}
	
	public Cursor seleccionarTodosUsuarios(){
		String sql = "SELECT * FROM Usuarios";
		return db.rawQuery(sql, null);
		
	}
	
	// TABLA CUESTIONARIOS
	public Cursor seleccionarTodosLosCuestionarios(){
		String sql = "SELECT * FROM Cuestionarios";
		return db.rawQuery(sql, null);
		
	}
	
	public void borrarPlantilla(String pNombre) {
		String sql = "DELETE FROM Cuestionarios WHERE NombreCuestionario ='" + pNombre + "'";
		db.execSQL(sql);
	}

	public Cursor comprobarSiExistePlantilla(String pNombre) {
		String sql = "SELECT * FROM Cuestionarios WHERE NombreCuestionario = '" + pNombre + "'";
		return db.rawQuery(sql, null);
	}
	
	public void insertarCuestionario(String pNombre){
		String sql = "INSERT INTO Cuestionarios ('NombreCuestionario') VALUES ('" + pNombre + "')";
		db.execSQL(sql);
	}	
	
	// TABLA PREGUNTA
	public Cursor buscarPreguntas(String nombrePlantilla) {
		String sql = "SELECT Pregunta FROM Pregunta WHERE idCuestionario = '" + nombrePlantilla + "'";
		return db.rawQuery(sql, null);
	}
	
	public void insertarPregunta(int pId, String pCuestionario, String pPregunta){
//		String sql = "INSERT INTO Pregunta ('idPregunta', 'idCuestionario', 'Pregunta') VALUES ("+ pId +",'"+ pCuestionario + "','" + pPregunta + "')";
		String sql = "INSERT INTO Pregunta ('idCuestionario', 'Pregunta') VALUES ('"+ pCuestionario + "','" + pPregunta + "')";
		db.execSQL(sql);
	}

}
