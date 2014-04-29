package org.api.whatsaudit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class MainPantallaInicio extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_pantallainicio);
        
    
        Thread timer = new Thread(){
   
            public void run(){
                try{
                    sleep(2000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally{
                    Intent actividaPrincipal = new Intent("org.api.whatsaudit.MainPantallaInicio.PRINCIPAL");
                    MainPantallaInicio.this.finish();
                    startActivity(actividaPrincipal);
                }                
            }
        };
        timer.start();
    }
    

}