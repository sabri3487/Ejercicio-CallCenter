package com.almundo.helper;

import java.util.concurrent.ThreadLocalRandom;

import com.almundo.entidades.Call;

/**
 * Clase que simula la generación de llamadas
 */
public class CallGenerator {

	// --------------------- METODOS ------------------- 
    
	/**
	 * Genera llamadas con una duración random entre 5 segundos y el número enviado por parámetro.
	 * @param numberCalls número de llamadas a generar
	 * @param maxDuration máxima duración de la llamada a generar
	 */
    public static void generateCalls(int numberCalls, int maxDuration) {
    	
        for ( int index = 1; index <= numberCalls; index++ ) {
        	int duration = ThreadLocalRandom.current().nextInt(5, maxDuration + 1);
        	Call call = new Call( index, duration*1000 );
            CallQueue.enqueueCall( call );
        }
    }

}
