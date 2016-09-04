package br.edu.ifpi.eventos.util;

import java.util.ArrayList;
import java.util.List;

public abstract class Subject {
	
	private List<Observer> observers = new ArrayList<>();
	protected String notificacao;
	
	
	public void addObserver(Observer obs){
		this.observers.add(obs);
	}
	
	public void delObserver(Observer obs){
		this.observers.remove(obs);
	}
	
	public void notifyObservers(){
		for (Observer observer : observers) {
			observer.update(notificacao);
		}		
	}
	
	public abstract void setNotificacao(String string);

}
