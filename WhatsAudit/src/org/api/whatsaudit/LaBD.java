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
		db.execSQL("CREATE TABLE Cuestionarios ('NombreCuestionario' TEXT PRIMARY KEY NOT NULL, 'idCreador' INTEGER, Pregunta1 TEXT, Pregunta2 TEXT, Pregunta3 TEXT, FOREIGN KEY (idCreador) REFERENCES Usuarios(idUser))");
		db.execSQL("CREATE TABLE CuestionariosRespondidos ('NombreCuestionario' TEXT, 'idUser' INTEGER, 'Respuesta1' TEXT, 'Respuesta2' TEXT, 'Respuesta3' TEXT, FOREIGN KEY (idUser) REFERENCES Usuarios(idUser), FOREIGN KEY (NombreCuestionario) REFERENCES Cuestionarios(NombreCuestionario), PRIMARY KEY ('NombreCuestionario', 'idUser'))");
		
		db.execSQL("INSERT INTO 'Usuarios' (idUser, Password, Administrador) VALUES (0,'admin', 'Si')");
		db.execSQL("INSERT INTO 'Cuestionarios' (NombreCuestionario, idCreador, Pregunta1, Pregunta2, Pregunta3) VALUES ('Cuestionario Inicial', 0, '¿Ha sido fácil completar la tarea?','Si has utilizado el manual, ¿la información ha sido fácil de encontrar?',"
				+ "'¿La información que encontraste en el manual ha sido fácil de utilizar?' )");
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
	
	public Cursor buscarUsuario(int pUser){
		String sql = "SELECT * FROM Usuarios WHERE idUser ='" + pUser + "'";
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
	
	public void insertarCuestionario(String pNombre, int pCreador, String pPregunta1, String pPregunta2, String pPregunta3){
		String sql = "INSERT INTO Cuestionarios ('NombreCuestionario', 'idCreador', 'Pregunta1', 'Pregunta2', 'Pregunta3') VALUES ('" + pNombre + "','" + pCreador +  "','" + pPregunta1 + "','" + pPregunta2 + "','" + pPregunta3 + "')";
		db.execSQL(sql);
	}	
	
	public Cursor buscarCuestionariosDeUsuario(int pUsuario) {
		String sql = "SELECT * FROM Cuestionarios WHERE idCreador = " + pUsuario;
		return db.rawQuery(sql, null);
	}
	
	public Cursor buscarPreguntasCuestionario(String pNombre) {
		String sql = "SELECT Pregunta1, Pregunta2, Pregunta3 FROM Cuestionarios WHERE NombreCuestionario = '" + pNombre + "'";
		return db.rawQuery(sql, null);
	}
	
	// TABLA CUESTIONARIOSRESPONDIDOS
	
	public Cursor seleccionarTodosLosCuestionariosResp(){
		String sql = "SELECT * FROM CuestionariosRespondidos";
		return db.rawQuery(sql, null);	
	}
	
	public Cursor buscarCuestionariosRespDeUsuario(int pUsuario) {
		String sql = "SELECT * FROM CuestionariosRespondidos WHERE idUser =" + pUsuario;
		return db.rawQuery(sql, null);
	}
	
	public void insertarCuestionarioRespondido(String pNombre, int pCreador, String pRespuesta1, String pRespuesta2, String pRespuesta3){
		String sql = "INSERT INTO CuestionariosRespondidos ('NombreCuestionario', 'idUser', 'Respuesta1', 'Respuesta2', 'Respuesta3') "
				+ "VALUES ('" + pNombre + "'," + pCreador +  ",'" 
				+ pRespuesta1 + "','" + pRespuesta2 + "','" + pRespuesta3 + "')";
		db.execSQL(sql);
	}	
	
	public Cursor comprobarSiCuestContestado(String pNombre, int pUsuario) {
		String sql = "SELECT COUNT (*) FROM CuestionariosRespondidos WHERE NombreCuestionario = '" + pNombre + "' AND idUser =" + pUsuario;
		return db.rawQuery(sql, null);
	}
	
	public Cursor buscarPreguntasDeCuestionario(String pNombre, int pUsuario) {
		String sql = "SELECT Respuesta1, Respues2, Respues3 FROM CuestionariosRespondidos WHERE NombreCuestionario = '" + pNombre + "' AND idUser =" + pUsuario;
		return db.rawQuery(sql, null);
	}

	public void borrarCuestionarioUsuario(int pIdUser, String pNombrePlantilla) {
		String sql = "DELETE FROM CuestionariosRespondidos WHERE NombreCuestionario ='" + pNombrePlantilla + "' AND idUser = "+ pIdUser;
		db.execSQL(sql);
		
	}
	
	

		
}

