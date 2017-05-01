package com.almundo.gestores;

import java.util.ArrayList;
import java.util.List;

import com.almundo.constantes.EnumEmployeeStatus;
import com.almundo.entidades.Director;
import com.almundo.entidades.Employee;

/**
 * Clase que controla el estado de los directores
 */
public class DirectorManager{

	// --------------- ATRIBUTOS ---------------
	
	private static DirectorManager instance;
	private List<Director> employees;

	// -------------- CONSTRUCTOR ---------------
	
	public DirectorManager() {
		employees = new ArrayList<Director>();
	}

	// --------------- METODOS -----------------

	/**
	 * Busca un empleado libre dentro de la lista.
	 * @return Empleado en estado Libre o null si no hay ninguno Libre.
	 */
	public static Employee getFreeEmployee() {

		for (Employee employee : getInstance().getEmployees()) {
			if (employee.getStatus().equals(EnumEmployeeStatus.FREE.getProperty())) {
				return employee;
			}
		}
		return null;
	}
	
	public static void addEmployee(Director employee){
		getInstance().getEmployees().add(employee);
	}
	
	
	// --------------- GETTER / SETTER -----------------
	
	public List<Director> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Director> employees) {
		this.employees = employees;
	}
	
	public static DirectorManager getInstance() {
    	if (instance == null){
            instance = new DirectorManager();
        }
        return instance;
	}
}
