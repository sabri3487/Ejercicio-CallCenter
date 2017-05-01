package com.almundo.helper;

import java.util.concurrent.LinkedBlockingQueue;

import org.apache.log4j.Logger;

import com.almundo.entidades.Call;

/**
 * Clase para la gestión de la cola de llamadas
 */
public class CallQueue {
	
	// --------------- ATRIBUTOS ----------------

	private static Logger LOGGER = Logger.getLogger(CallQueue.class);
	
	private static CallQueue instance;
    private LinkedBlockingQueue<Call> queue;
    
    // ---------------- CONSTRUCTOR -------------
	
    private CallQueue() {
        this.queue = new LinkedBlockingQueue<Call>();
    }

    // ----------------- METODOS ----------------

    /**
     * Inserta una llamada en la cola.
     * @param call a insertar
     */
    public static void enqueueCall( Call call ) {
        try {
        	LOGGER.info("Encolando llamada " + call.getNumber() + " con duración "+ call.getDurationInSeconds() + " segundos");
            getInstance().getQueue().put( call );
        } catch ( InterruptedException e ) {
        	LOGGER.error("Error al encolar la llamada." );
        }
    }

    /**
     * Devuelve la primera llamada de la cola. 
     * @return primera llamada de la cola, o null si la cola esta vacía.
     */
    public static Call retrieveCall() {
    	return getInstance().getQueue().poll();
    }
    
    /**
     * Verifica el estado de la cola.
     * @return true si hay llamadas en la cola, o false si la cola está vacía.
     */
    public static boolean hasPendingCalls(){
    	return getInstance().getQueue().size()>0;
    }
    
    /**
     * TODO: Realizar una acción para comunicar al usuario que la llamada debe esperar.
     */
    public static void notifyNoEmployeesAvailable() {
		LOGGER.info("No hay operadores disponibles para atender la llamada.");
	}
    
    
    // ----------------- GETTER / SETTER ----------------
    
    public static CallQueue getInstance() {
    	if (instance == null){
            instance = new CallQueue();
        }
        return instance;
	}

	public LinkedBlockingQueue<Call> getQueue() {
		return queue;
	}

}
