package jana60.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jana60.repository.VisitaRepository;

@Controller
@RequestMapping("/")
public class HomeController {

	@Autowired
	private VisitaRepository visiteRepo;
	
	@GetMapping
	private String home(Model model) {
		model.addAttribute("insiemeVisite", visiteRepo.findAll());
		return "/home/home";
	}
}
