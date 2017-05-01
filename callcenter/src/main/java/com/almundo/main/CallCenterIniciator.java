package com.almundo.main;

import com.almundo.constantes.Constants;
import com.almundo.entidades.Director;
import com.almundo.entidades.Operator;
import com.almundo.entidades.Supervisor;
import com.almundo.gestores.DirectorManager;
import com.almundo.gestores.OperatorManager;
import com.almundo.gestores.SupervisorManager;
import com.almundo.helper.CallGenerator;
import com.almundo.helper.Dispatcher;

/**
 * Clase principal 
 */
public class CallCenterIniciator {
    
    public static final int NUMBER_OPERATORS = 2;
	public static final int NUMBER_SUPERVISORS = 2;
	public static final int NUMBER_DIRECTORS = 2;
			
	public static void iniciateEmployees(){
		
		for (int i = 1; i <= NUMBER_OPERATORS; i++) {
            OperatorManager.addEmployee(new Operator(Constants.PREFIX_ID_OPERATOR+i));
        }
        for (int i = 1; i <= NUMBER_SUPERVISORS; i++) {
        	SupervisorManager.addEmployee(new Supervisor(Constants.PREFIX_ID_SUPERVISOR+i));
        }
        for (int i = 1; i <= NUMBER_DIRECTORS; i++) {
        	DirectorManager.addEmployee(new Director(Constants.PREFIX_ID_DIRECTOR+i));
        }
	}
	
	public static void main( String... args ) {
		iniciateEmployees();
		new Dispatcher().start();
        CallGenerator.generateCalls(10, 12);
    }
}
