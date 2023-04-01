package com.evaluacion.tecnica.service;

import org.springframework.stereotype.Service;

import com.evaluacion.tecnica.models.Cara;
import com.evaluacion.tecnica.models.Cuadro;
import com.evaluacion.tecnica.models.CuboRubik;
import com.evaluacion.tecnica.models.Linea;

@Service
public class CuboRubikService {

	private static final String MENSAJE_VALIDO = "El cubo rubik ha sido armado correctamente";
	private static final String MENSAJE_NO_VALIDO = "El cubo rubik ha sido no ha sido compleato";
	
	public String validarCuboRubik(CuboRubik cubo) {		
		Cara cara;
		Linea linea;
		Cuadro cuadro;
				
		Cuadro primerCuadro;
		for(int x = 0; x < cubo.getCaras().size(); x++) {
			cara = cubo.getCaras().get(x);
					
			primerCuadro = cara.getLineas().get(0).getCuadros().get(0);
			for(int y = 0; y < cara.getLineas().size(); y++) {
				linea = cara.getLineas().get(y);
				
				for(int z = 0; z < linea.getCuadros().size(); z++) {
					cuadro = linea.getCuadros().get(z);
					
					if(primerCuadro.getCuadro() != cuadro.getCuadro()) {
						return MENSAJE_NO_VALIDO;
					}
				}
			}
		}
		
		return MENSAJE_VALIDO;
	}
}
