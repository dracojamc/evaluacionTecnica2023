package com.evaluacion.tecnica.models;

import java.util.ArrayList;
import java.util.List;

public class Linea {

	private List<Cuadro> cuadros = new ArrayList<>();

	public List<Cuadro> getCuadros() {
		return cuadros;
	}

	public void setCuadros(List<Cuadro> cuadros) {
		this.cuadros = cuadros;
	}
}
