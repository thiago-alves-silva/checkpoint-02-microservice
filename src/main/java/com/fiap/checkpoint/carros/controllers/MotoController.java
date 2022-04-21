package com.fiap.checkpoint.carros.controllers;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fiap.checkpoint.carros.model.Moto;
import com.fiap.checkpoint.carros.repository.MotoRepository;
import com.fiap.checkpoint.dto.MotoDto;

@Controller
public class MotoController {
	@Autowired
	private MotoRepository motoRepository;
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping("/motos")
	public ModelAndView home() {
		ModelAndView modelAndView = new ModelAndView("motos/home"); 
		List<Moto> predefinedMotos = Arrays.asList(
			new Moto("Honda", "CG 125", 2022, 125, 15, "Flex"), 
			new Moto("Honda", "Fan 150", 2018, 150, 15, "Flex"), 
			new Moto("Honda", "Hornet", 1998, 600, 13, "Gasolina"), 
			new Moto("Yamaha", "MT-03", 2000, 321, 13, "Álcool")
		);
		
		List<Moto> motos = motoRepository.findAll();
		modelAndView.addObject("motos", motos.size() > 0 ? motos : predefinedMotos);
		modelAndView.addObject("predefined", motos.size() == 0);
		
		return  modelAndView;
	}
	
	@GetMapping("/motos/inserir")
	public ModelAndView inserir(MotoDto moto) {
		ModelAndView modelAndView = new ModelAndView("motos/inserir");
		return modelAndView;
	} 
	
	@PostMapping("/motos/inserir")
	public ModelAndView salvar(@Valid MotoDto moto, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			System.out.println("Temos erros!");
			return new ModelAndView("inserir");
		} else System.out.println("Não tem erro!");
		
		Moto motoEntity  = modelMapper.map(moto, Moto.class);
		motoRepository.save(motoEntity);
		return new ModelAndView("redirect:/motos");
	}
}
