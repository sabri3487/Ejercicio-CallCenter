package com.almundo.entidades;

import org.apache.log4j.Logger;

import com.almundo.constantes.Constants;
import com.almundo.constantes.EnumEmployeeStatus;

/**
 * Clase que representa al empleado del Call Center
 */
public class Employee implements Runnable {
	
	// --------------- ATRIBUTOS ---------------
	
	private static Logger LOGGER = Logger.getLogger(Employee.class);

    private String id;
    private String status;
    private Call callReceived;
    private boolean isRunning;
    

    // -------------- CONSTRUCTOR ---------------

    public Employee(String id) {
        this.id = id;
        this.status = EnumEmployeeStatus.FREE.getProperty();
    }

    // --------------- METODOS -----------------
    
    /**
     * Ejecuta mientras la duración de la llamada no supere el limite maximo establecido para una llamada (10 segundos), 
     * o mientras no supere la duración preestablecida para esa llamada.
     */
    public void run() {
        while ( this.isRunning ) {

            if ( System.currentTimeMillis() > (this.callReceived.getTimeStart() + this.callReceived.getDuration()) 
              || System.currentTimeMillis() > (this.callReceived.getTimeStart() + Constants.MAX_DURATION_CALL)) {
            	
            	LOGGER.info("Empleado ID " + id + " Colgando llamada " + callReceived.getNumber());
                stop();
                
            } else {
            	sleep();
            }
        }
    }

    /**
     * Inicia un hilo de ejecución para que se procese la llamada, y cambia el estado del empleado a ocupado.
     * @param llamada a atender
     */
    public void start(Call call) {
    	
    	LOGGER.info("Empleado ID " + id + " Atendiendo llamada " + call.getNumber() + " con duración "+ call.getDurationInSeconds() +" segundos");
        this.isRunning = true;
        this.callReceived = call;
        this.status = EnumEmployeeStatus.IN_CALL.getProperty();
        
        new Thread( this ).start();
    }

    /**
     * Finaliza el hilo de ejecución y cambia el estado del empleado a libre.
     */
    public void stop() {
        this.isRunning = false;
        this.status = EnumEmployeeStatus.FREE.getProperty();
    }

    /**
     * Duerme el hilo de ejecucución por 1 segundo.
     */
    private void sleep() {
        try {
            Thread.sleep( 1000 );
        } catch ( InterruptedException e ) {
            e.printStackTrace();
        }
    }
    
    // --------------- GETTER / SETTER -----------------

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}	

}
