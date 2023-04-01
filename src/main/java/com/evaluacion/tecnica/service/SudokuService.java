package com.evaluacion.tecnica.service;

import java.util.HashSet;

import org.springframework.stereotype.Service;

import com.evaluacion.tecnica.models.Cara;
import com.evaluacion.tecnica.models.Cuadro;
import com.evaluacion.tecnica.models.Linea;
import com.evaluacion.tecnica.models.Sudoku;

@Service
public class SudokuService {

	private static final String MENSAJE_VALIDO = "El sudoku ha sido completado";
	private static final String MENSAJE_NO_VALIDO = "El sudoku no ha sido completado";
	
	public String validarSudoku(Sudoku sudoku) {		
		Cara cara = sudoku.getCara();
		Linea linea;
		Cuadro cuadro;
			
		for(int x = 0; x < cara.getLineas().size(); x++) {
			linea = cara.getLineas().get(x);
			
			for(int y = 0; y < linea.getCuadros().size(); y++) {
				cuadro = linea.getCuadros().get(y);
				
				if(!validaLineaY(cuadro, linea, y) || !validaLineaX(cuadro, cara, x, y)) {
					return MENSAJE_NO_VALIDO;
				}
			}
		}
		
		if(!validarSecciones(cara)) {
			return MENSAJE_NO_VALIDO;
		}
		
		return MENSAJE_VALIDO;
	}
	
	private boolean validaLineaY(Cuadro cuadro, Linea linea, int posicionY) {
		boolean valida = true;
		
		for(int y = 0; y < linea.getCuadros().size(); y++) {					
			if(posicionY != y && cuadro.getCuadro() == linea.getCuadros().get(y).getCuadro()) {
				valida = false;				
				break;
			}
		}
		
		return valida;
	}
	
	private boolean validaLineaX(Cuadro cuadro, Cara cara, int posicionX, int posicionY) {
		boolean valida = true;
		
		for(int x = 0; x < cara.getLineas().size(); x++) {					
			if(posicionX != x && cuadro.getCuadro() == cara.getLineas().get(x).getCuadros().get(posicionY).getCuadro()) {
				valida = false;				
				break;
			}
		}
		
		return valida;
	}
	
	private boolean validarSecciones(Cara cara) {
		boolean valida = true;
		
		for(int x = 1; x <= 3; x++) {
			for(int y = 1; y <= 3; y++) {
				if(validaSeccion(x, y, cara)) {
					valida = false;
					break;
				}
			}
		}
		
		return valida;
	}
	
	private boolean validaSeccion(int seccionX, int seccionY, Cara cara) {		
		HashSet<Integer> numeros = new HashSet<Integer>();
		for(int x = (seccionX - 1) * 3; x < (seccionX * 3); x++) {
			for(int y = 1 + ((seccionY - 1) * 3); y <= 3 + (seccionY - 1) * 3; y++) {
				
				numeros.add(cara.getLineas().get(x).getCuadros().get(y - 1).getCuadro());
			}
		}
		
		return numeros.size() != 9;
	}
}
