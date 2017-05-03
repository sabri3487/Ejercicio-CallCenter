package com.almundo.helper;

import org.apache.log4j.Logger;

import com.almundo.entidades.Call;
import com.almundo.entidades.Employee;
import com.almundo.gestores.OperatorManager;

/**
 * Clase para la asignación de llamadas a empleados
 */
public class Dispatcher {
	

	private static Logger LOGGER = Logger.getLogger(Dispatcher.class);
	
	// --------------- METODOS -------------------
	
	/**
	 * Se encarga de asignar la llamada a un empleado. 
	 * Si la cola de llamadas está vacía, asigna la nueva llamada a un empleado libre.
	 * Si hay llamadas en la cola, o no hay empleados libres, inserta la llamada al final de la cola de espera.
	 * @param call llamada recibida
	 * @return true si la llamada fue atendida, o false si fue insertada en la cola de espera
	 */
	public static boolean dispatchCall(Call call){
		
		LOGGER.info("Se recibió llamada "+call.getNumber()+ " con duración "+call.getDurationInSeconds()+" segundos" );
		
		if(CallQueue.isEmpty() && assignCall(call)){
			return true;
		}
		CallQueue.enqueueCall(call);
		return false;
	}
	
	/**
	 * Asigna la llamada a un empleado libre.
	 * @param call llamada a asignar
	 * @return true si la llamada fue atendida, o false si no hay empleados libres
	 */
	private static boolean assignCall(Call call) {
		Employee freeEmployee = OperatorManager.getFreeEmployee();
    	if( freeEmployee!=null ){
            call.setTimeStart(System.currentTimeMillis());
            freeEmployee.start(call);
            return true;
    	} 
    	return false;
	}

	/**
	 * Método que es invocado para notificar que un empleado ya está libre.
	 * Obtiene la siguiente llamada de la cola y la asigna a un empleado.
	 */
	public static void notifyFreeEmployee(){
		Call call = CallQueue.retrieveCall();
		if(call!=null){
			assignCall(call);
		}
	}
	
}
