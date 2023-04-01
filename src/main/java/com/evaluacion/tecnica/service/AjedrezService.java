package com.evaluacion.tecnica.service;

import org.springframework.stereotype.Service;

import com.evaluacion.tecnica.models.Ajedrez;
import com.evaluacion.tecnica.models.Cara;
import com.evaluacion.tecnica.models.Cuadro;
import com.evaluacion.tecnica.models.Linea;

@Service
public class AjedrezService {

	private static final String MENSAJE_VALIDO = "No hay reinas atacando";
	private static final String MENSAJE_NO_VALIDO = "Existen reinas atacandose";
	
	public String validarAjedrez(Ajedrez ajedrez) {		
		Cara cara = ajedrez.getCara();
		Linea linea;
		Cuadro cuadro;
			
		for(int x = 0; x < cara.getLineas().size(); x++) {
			linea = cara.getLineas().get(x);
			
			for(int y = 0; y < linea.getCuadros().size(); y++) {
				cuadro = linea.getCuadros().get(y);
				
				if(cuadro.getCuadro() == 1 && validaReina(cara, x, y)) {
					return MENSAJE_NO_VALIDO;
				}
			}
		}
		
		return MENSAJE_VALIDO;
	}
	
	private boolean validaReina(Cara tablero, int posicionPiezaX, int posicionPiezaY) {
		boolean estaAtacando = false;
		
		if(ataqueArribaAbajo(tablero, posicionPiezaX, posicionPiezaY)) {
			estaAtacando = true;
		}
		
		if(!estaAtacando && ataqueDerechaIzquierda(tablero, posicionPiezaX, posicionPiezaY)) {
			estaAtacando = true;
		}
		
		if(!estaAtacando && ataqueDiagonales(tablero, posicionPiezaX, posicionPiezaY)) {
			estaAtacando = true;
		}
		
		return estaAtacando;
	}
	
	private boolean ataqueArribaAbajo(Cara tablero, int posicionPiezaX, int posicionPiezaY) {
		
		Linea linea;
		Cuadro cuadro;
		
		/* Arriba */		
		for(int x = posicionPiezaX - 1; x > 0; x--) {
			linea  = tablero.getLineas().get(x);
			cuadro = linea.getCuadros().get(posicionPiezaY);
			
			if(cuadro.getCuadro() == 1) {
				return true;
			}
		}
		
		/* Abajo*/
		for(int x = posicionPiezaX + 1; x < 8; x++) {
			linea  = tablero.getLineas().get(x);
			cuadro = linea.getCuadros().get(posicionPiezaY);
			
			if(cuadro.getCuadro() == 1) {
				return true;
			}
		}
		
		return false;
	}
	
	private boolean ataqueDerechaIzquierda(Cara tablero, int posicionPiezaX, int posicionPiezaY) {
		
		Linea linea;
		Cuadro cuadro;
		
		/* Izquierda */		
		for(int y = posicionPiezaY - 1; y > 0; y--) {
			linea  = tablero.getLineas().get(posicionPiezaX);
			cuadro = linea.getCuadros().get(y);
			
			if(cuadro.getCuadro() == 1) {
				return true;
			}
		}
		
		/* Derecha*/
		for(int y = posicionPiezaY + 1; y < 8; y++) {
			linea  = tablero.getLineas().get(posicionPiezaX);
			cuadro = linea.getCuadros().get(y);
			
			if(cuadro.getCuadro() == 1) {				
				return true;
			}
		}
		
		return false;
	}
	
	private boolean ataqueDiagonales(Cara tablero, int posicionPiezaX, int posicionPiezaY) {
		
		Linea linea;
		Cuadro cuadro;
		
		/* Diagonal Izquierda Arriba */
		for(int x = posicionPiezaX - 1, y = posicionPiezaY - 1; x > 0 && y > 0; x--, y--) {
			linea  = tablero.getLineas().get(x);
			cuadro = linea.getCuadros().get(y);
			
			if(cuadro.getCuadro() == 1) {
				return true;
			}
		}
		
		/* Diagonal Derecha Arriba */
		for(int x = posicionPiezaX - 1, y = posicionPiezaY + 1; x > 0 && y < 8; x--, y++) {
			linea  = tablero.getLineas().get(x);
			cuadro = linea.getCuadros().get(y);
			
			if(cuadro.getCuadro() == 1) {
				return true;
			}
		}
		
		/* Diagonal Izquierda Abajo */	
		for(int x = posicionPiezaX + 1, y = posicionPiezaY - 1; x < 8 &&  y > 0; x++, y--) {
			linea  = tablero.getLineas().get(x);
			cuadro = linea.getCuadros().get(y);
			
			if(cuadro.getCuadro() == 1) {
				return true;
			}
		}
		
		/* Diagonal Derecha Abajo */
		for(int x = posicionPiezaX + 1, y = posicionPiezaY + 1; x < 8 && y < 8; x++, y++) {
			linea  = tablero.getLineas().get(x);
			cuadro = linea.getCuadros().get(y);
			
			if(cuadro.getCuadro() == 1) {
				return true;
			}
		}
		
		return false;
	}
}
