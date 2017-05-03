package com.almundo.main;

import java.util.concurrent.ThreadLocalRandom;

import com.almundo.constantes.Constants;
import com.almundo.entidades.Call;
import com.almundo.entidades.Director;
import com.almundo.entidades.Operator;
import com.almundo.entidades.Supervisor;
import com.almundo.gestores.DirectorManager;
import com.almundo.gestores.OperatorManager;
import com.almundo.gestores.SupervisorManager;
import com.almundo.helper.Dispatcher;

/**
 * Clase principal 
 */
public class CallCenterIniciator {
	
	
	// --------------- METODOS -------------------
	
	public static void main( String... args ) {
		
		iniciateEmployees();
		
		for(int i=1; i<=10; i++){
			
			//Genera una llamada con duración random entre 5 y 10 segundos
			int duration = ThreadLocalRandom.current().nextInt(Constants.MIN_DURATION_CALL, Constants.MAX_DURATION_CALL + 1);
	        Call call = new Call( i, duration*1000 );
	        Dispatcher.dispatchCall(call);
		}
    }
	
	public static void iniciateEmployees(){
		
		for (int i = 1; i <= Constants.NUMBER_OPERATORS; i++) {
            OperatorManager.addEmployee(new Operator(Constants.PREFIX_ID_OPERATOR+i));
        }
        for (int i = 1; i <= Constants.NUMBER_SUPERVISORS; i++) {
        	SupervisorManager.addEmployee(new Supervisor(Constants.PREFIX_ID_SUPERVISOR+i));
        }
        for (int i = 1; i <= Constants.NUMBER_DIRECTORS; i++) {
        	DirectorManager.addEmployee(new Director(Constants.PREFIX_ID_DIRECTOR+i));
        }
	}
	
}