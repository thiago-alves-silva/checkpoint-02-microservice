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

import com.fiap.checkpoint.carros.model.Carro;
import com.fiap.checkpoint.carros.repository.CarroRepository;
import com.fiap.checkpoint.dto.CarroDto;

@Controller
public class CarroController {
	@Autowired
	private CarroRepository carroRepository;
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping("/carros")
	public ModelAndView home() {
		ModelAndView modelAndView = new ModelAndView("carros/home");
		List<Carro> predefinedCars = Arrays.asList(
				new Carro("Fiat", "Uno", 2022, 4, 15, "Flex"), 
				new Carro("Chevrolet", "Onix", 2018, 4, 15, "Flex"), 
				new Carro("Volkswagen", "Gol", 1998, 2, 13, "Gasolina"), 
				new Carro("Chevrolet", "Monza", 2000, 2, 13, "Álcool"), 
				new Carro("Peugeot", "306", 2010, 4, 13, "Gasolina"), 
				new Carro("Hyundai", "HB20", 2021, 4, 14, "Flex"), 
				new Carro("Renault", "Kwid", 2014, 4, 14, "Flex"), 
				new Carro("Volkswagen", "Voyage", 1995, 4, 14, "Álcool")
		);
		
		List<Carro> carRepository = carroRepository.findAll();
		modelAndView.addObject("cars", carRepository.size() > 0 ? carRepository : predefinedCars);
		modelAndView.addObject("predefined", carRepository.size() == 0);
		
		return  modelAndView;
	}
	
	@GetMapping("/carros/inserir")
	public ModelAndView inserir(CarroDto carro) {
		ModelAndView modelAndView = new ModelAndView("carros/inserir");
		return modelAndView;
	} 
	
	@PostMapping("/carros/inserir")
	public ModelAndView salvar(@Valid CarroDto carro, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			System.out.println("Temos erros!");
			return new ModelAndView("inserir");
		} else System.out.println("Não tem erro!");
		
		Carro carroEntity  = modelMapper.map(carro, Carro.class);
		carroRepository.save(carroEntity);
		return new ModelAndView("redirect:/carros");
	}
}
