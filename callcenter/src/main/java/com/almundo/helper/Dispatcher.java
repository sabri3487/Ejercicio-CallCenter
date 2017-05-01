package com.almundo.helper;

import org.apache.log4j.Logger;

import com.almundo.entidades.Call;
import com.almundo.entidades.Employee;
import com.almundo.gestores.OperatorManager;

/**
 * Clase para la asignación de llamadas a empleados
 */
public class Dispatcher implements Runnable{
	
	// --------------- ATRIBUTOS ----------------
	
	private static Logger LOGGER = Logger.getLogger(Dispatcher.class);
	
	private boolean isRunning;

	
	// --------------- METODOS -------------------

	/**
	 * Mientras este ejecutando el hilo, checkea cada 1 segundo si hay una llamada en la cola y la asigna
	 */
	public void run() {
		LOGGER.info("Comienza a escuchar cola de llamadas.");
        while ( this.isRunning ) {
            dispatchCall();
            sleep();
        }
    }
	
	/**
	 * Se encarga de controlar si hay llamadas pendientes en la cola. 
	 * Si hay un empleado libre, obtiene la siguiente llamada de la cola y se la asigna.
	 */
	private void dispatchCall(){
		
		if( CallQueue.hasPendingCalls() ){
			Employee freeEmployee = OperatorManager.getFreeEmployee();
	    	if( freeEmployee!=null ){
	    		Call call = CallQueue.retrieveCall();
	            if ( call != null ) {
	            	call.setTimeStart(System.currentTimeMillis());
	            	freeEmployee.start(call);
	            }
	    	} else {
	    		CallQueue.notifyNoEmployeesAvailable();
	    	}
		} 
	}

	/**
	 * Crea un hilo de ejecución
	 */
    public void start() {
        isRunning = true;
        new Thread( this ).start();
    }

    /**
     * Detiene el hilo de ejecución
     */
    public void stop() {
        isRunning = false;
    }

    /**
     * Duerme el hilo de ejecución por 1 segundo
     */
    private void sleep() {
        try {
            Thread.sleep( 1000 );
        } catch ( InterruptedException e ) {
            e.printStackTrace();
        }
    }

}
