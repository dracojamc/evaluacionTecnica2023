package com.evaluacion.tecnica.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.evaluacion.tecnica.models.Ajedrez;
import com.evaluacion.tecnica.models.CuboRubik;
import com.evaluacion.tecnica.models.Sudoku;
import com.evaluacion.tecnica.service.AjedrezService;
import com.evaluacion.tecnica.service.CuboRubikService;
import com.evaluacion.tecnica.service.SudokuService;

@Controller
public class MainController {
	
	@Autowired
	private CuboRubikService suboRubikService;
	
	@Autowired
	private AjedrezService ajedrezService;
	
	@Autowired
	private SudokuService sudokuService;

	@GetMapping("/")
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView("/index.html");
		
		return mav;
	}
	
	@PostMapping("/cuborubik")
	@ResponseBody
	public String cuboRubik(@RequestBody CuboRubik cubo) {
		
		return suboRubikService.validarCuboRubik(cubo);
	}
	
	@PostMapping("/ajedrez")
	@ResponseBody
	public String ajedrez(@RequestBody Ajedrez ajedrez) {
		
		return ajedrezService.validarAjedrez(ajedrez);
	}
	
	@PostMapping("/sudoku")
	@ResponseBody
	public String sudoku(@RequestBody Sudoku sudoku) {
		
		return sudokuService.validarSudoku(sudoku);
	}
}
