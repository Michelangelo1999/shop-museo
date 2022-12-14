package jana60.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/accesso")
public class AccessoController {

	@GetMapping
	private String accesso() {
		return "/gestionale/gestionale";
	}

	@PostMapping("/entra")
	private String invio() {
		return "redirect:/gestionale";
	}
}
