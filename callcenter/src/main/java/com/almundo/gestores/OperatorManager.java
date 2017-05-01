package com.almundo.gestores;

import java.util.ArrayList;
import java.util.List;

import com.almundo.constantes.EnumEmployeeStatus;
import com.almundo.entidades.Employee;
import com.almundo.entidades.Operator;

/**
 * Clase que controla el estado de los operadores
 */
public class OperatorManager {

	// --------------- ATRIBUTOS ---------------
	
	private static OperatorManager instance;
	private List<Operator> employeees;

	// --------------- CONSTRUCTOR ---------------
	
	public OperatorManager() {
		employeees = new ArrayList<Operator>();
	}
	
	// --------------- METODOS ---------------

	/**
	 * Busca un empleado libre dentro de la lista. En caso de no encontrarlo, busca un superior libre. 
	 * @return Empleado en estado Libre o null si no hay ninguno Libre.
	 */
	public static Employee getFreeEmployee() {

		for (Employee employee : getInstance().getEmployees()) {
			if (employee.getStatus().equals(EnumEmployeeStatus.FREE.getProperty())) {
				return employee;
			}
		}
		return SupervisorManager.getFreeEmployee();
	}
	
	public static void addEmployee(Operator employee){
		getInstance().getEmployees().add(employee);
	}
	
	// --------------- GETTER / SETTER ---------------
	
	public static OperatorManager getInstance() {
    	if (instance == null){
            instance = new OperatorManager();
        }
        return instance;
	}

	public List<Operator> getEmployees() {
		return employeees;
	}

	public void setEmployees(List<Operator> employeees) {
		this.employeees = employeees;
	}
	
}
