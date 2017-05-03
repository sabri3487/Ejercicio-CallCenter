package com.almundo;


import static org.junit.Assert.*;

import java.util.concurrent.ThreadLocalRandom;

import org.junit.Test;

import com.almundo.constantes.Constants;
import com.almundo.entidades.Call;
import com.almundo.entidades.Director;
import com.almundo.entidades.Operator;
import com.almundo.entidades.Supervisor;
import com.almundo.gestores.DirectorManager;
import com.almundo.gestores.OperatorManager;
import com.almundo.gestores.SupervisorManager;
import com.almundo.helper.CallQueue;
import com.almundo.helper.Dispatcher;

public class CallCenterTest {
	

	private int counter = 1;
			

	/**
	 * Genera un Test con 10 llamadas, para 6 empleados en total.
	 * Las primeras 6 son asignadas y las siguientes 4 puestas en cola de espera.
	 */
	@Test
	public final void testTenCallsWithQueue() {

		System.out.println("** testTenCallsWithQueue **");
		iniciateEmployees(2, 2, 2);
		//LLamada 1
		boolean response = generateCall();
        assertTrue(response);
        //LLamada 2
        response = generateCall();
        assertTrue(response);
        //LLamada 3
        response = generateCall();
        assertTrue(response);
        //LLamada 4
        response = generateCall();
        assertTrue(response);
        //LLamada 5
        response = generateCall();
        assertTrue(response);
        //LLamada 6
        response = generateCall();
        assertTrue(response);
        //LLamada 7
        response = generateCall();
        assertFalse(response);
        //LLamada 8
        response = generateCall();
        assertFalse(response);
        //LLamada 9
        response = generateCall();
        assertFalse(response);
        //LLamada 10
        response = generateCall();
        assertFalse(response);

	}
	
	
	/**
	 * Genera un Test con 10 llamadas, para 10 empleados en total.
	 * Todas las llamadas son asignadas a los empleados.
	 */
	@Test
	public final void testTenCallsAllReceived() {

		System.out.println("** testTenCallsAllReceived **");
		iniciateEmployees(5, 3, 2);
		//LLamada 1
		boolean response = generateCall();
        assertTrue(response);
        //LLamada 2
        response = generateCall();
        assertTrue(response);
        //LLamada 3
        response = generateCall();
        assertTrue(response);
        //LLamada 4
        response = generateCall();
        assertTrue(response);
        //LLamada 5
        response = generateCall();
        assertTrue(response);
        //LLamada 6
        response = generateCall();
        assertTrue(response);
        //LLamada 7
        response = generateCall();
        assertTrue(response);
        //LLamada 8
        response = generateCall();
        assertTrue(response);
        //LLamada 9
        response = generateCall();
        assertTrue(response);
        //LLamada 10
        response = generateCall();
        assertTrue(response);

	}
	
	
	
	/**
	 * Genera una llamada con duración random entre 5 y 10 segundos
	 */
	private boolean generateCall(){
    	int duration = ThreadLocalRandom.current().nextInt(Constants.MIN_DURATION_CALL, Constants.MAX_DURATION_CALL + 1);
        Call call = new Call( this.counter++, duration*1000 );
        return Dispatcher.dispatchCall(call);
	}
	
	/**
	 * Inicializa los empleados del Call Center, y vacía la cola de llamadas
	 * @param numerOperators
	 * @param numberSupervisors
	 * @param numberDirectors
	 */
	public void iniciateEmployees(int numerOperators, int numberSupervisors, int numberDirectors){
		
		OperatorManager.clearEmployees();
		for (int i = 1; i <= numerOperators; i++) {
            OperatorManager.addEmployee(new Operator(Constants.PREFIX_ID_OPERATOR+i));
        }
		SupervisorManager.clearEmployees();
        for (int i = 1; i <= numberSupervisors; i++) {
        	SupervisorManager.addEmployee(new Supervisor(Constants.PREFIX_ID_SUPERVISOR+i));
        }
        DirectorManager.clearEmployees();
        for (int i = 1; i <= numberDirectors; i++) {
        	DirectorManager.addEmployee(new Director(Constants.PREFIX_ID_DIRECTOR+i));
        }
        
        CallQueue.clearQueue();
	}
	
}
