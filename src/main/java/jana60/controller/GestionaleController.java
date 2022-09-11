package jana60.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/gestionale")
public class GestionaleController {
	
	

	@GetMapping
	public String gestionale() {
		return "/gestionale/gestionale";
	}
}
