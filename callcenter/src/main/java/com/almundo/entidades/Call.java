package com.almundo.entidades;

/**
 * Clase que representa una llamada del Call Center
 */
public class Call {

	// ------------ ATRIBUTOS ----------------

    private long timeStart;
    private int number;
    private int duration;

    // ------------ CONSTRUCTOR ------------
    
    public Call(int number, int duration) {
        this.number = number;
        this.duration = duration;
    }
    
    // ----------- GETTER / SETTER -----------

	public int getNumber() {
		return number;
	}
	
	public void setNumber(int number) {
		this.number = number;
	}

	public long getTimeStart() {
		return timeStart;
	}

	public void setTimeStart(long timeStart) {
		this.timeStart = timeStart;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	public int getDurationInSeconds(){
		return this.duration/1000;
	}

}
