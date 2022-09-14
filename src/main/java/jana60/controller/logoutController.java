package jana60.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/logout")
public class logoutController {

	@PostMapping(name = "/logout", value = "")
	public String logout() {
		return "redirect:/";
	}

	@GetMapping
	public String logoutForm() {
		return "/accesso/logoutCustom";
	}

}
