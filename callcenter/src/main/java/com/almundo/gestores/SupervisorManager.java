package com.almundo.gestores;

import java.util.ArrayList;
import java.util.List;

import com.almundo.constantes.EnumEmployeeStatus;
import com.almundo.entidades.Employee;
import com.almundo.entidades.Supervisor;

/**
 * Clase que controla el estado de los supervisores
 */
public class SupervisorManager {

	// --------------- ATRIBUTOS ---------------
	
	private static SupervisorManager instance;
	private List<Supervisor> employees;

	// --------------- CONSTRUCTOR ---------------
	
	public SupervisorManager() {
		employees = new ArrayList<Supervisor>();
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
		return DirectorManager.getFreeEmployee();
	}
	
	public static void addEmployee(Supervisor employee){
		getInstance().getEmployees().add(employee);
	}
	
	public static void clearEmployees(){
		getInstance().getEmployees().clear();
	}
	
	// --------------- GETTER / SETTER ---------------
	
	public static SupervisorManager getInstance() {
    	if (instance == null){
            instance = new SupervisorManager();
        }
        return instance;
	}
	
	public List<Supervisor> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Supervisor> employees) {
		this.employees = employees;
	}
	
}
