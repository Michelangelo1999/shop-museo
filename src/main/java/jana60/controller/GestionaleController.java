package jana60.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jana60.model.Acquisto;
import jana60.model.Prodotto;
import jana60.model.Visita;
import jana60.repository.AcquistoRepo;
import jana60.repository.ProdottoRepo;
import jana60.repository.VisitaRepository;

@Controller
@RequestMapping("/gestionale")
public class GestionaleController {

	@Autowired
	private ProdottoRepo prodotto;

	@Autowired
	private VisitaRepository visita;

	@Autowired
	private AcquistoRepo acquisto;

	@GetMapping
	public String gestionale(Model model) {
		model.addAttribute("prodottoList", prodotto.findAll());
		model.addAttribute("visiteList", visita.findAll());
		model.addAttribute("acquistiList", acquisto.findAll());

		List<Prodotto> bestSellerProd = new ArrayList<Prodotto>();
		Iterator<Prodotto> ListaProdotti = prodotto.findAll().iterator();
		while (ListaProdotti.hasNext()) {
			Prodotto current = ListaProdotti.next();
			if (current.getQuantAcquistata() > 20) {
				bestSellerProd.add(current);
			}
		}
		model.addAttribute("bestSellerProd", bestSellerProd); // da passare in html gestionale

		List<Visita> visiteProgrammate = new ArrayList<Visita>();
		Iterator<Visita> tutteLeVisite = visita.findAll().iterator();
		while (tutteLeVisite.hasNext()) {
			Visita current = tutteLeVisite.next();
			LocalDateTime traSetteGiorni = LocalDateTime.now().plusDays(8);
			if (current.getDataInizio().isBefore(traSetteGiorni)) {
				visiteProgrammate.add(current);
			}
		}
		model.addAttribute("visiteProgrammate", visiteProgrammate); // da passare in gestionale

		List<Acquisto> riepilogoAcquisto = new ArrayList<Acquisto>();
		Iterator<Acquisto> tuttiGliAcquisti = acquisto.findAll().iterator();
		while (tuttiGliAcquisti.hasNext()) {
			Acquisto current = tuttiGliAcquisti.next();
			if (current.getData().isAfter(LocalDate.now().minusDays(30))) {
				riepilogoAcquisto.add(current);
			}
		}

		model.addAttribute("riepilogoAcquisto", riepilogoAcquisto);

		return "/gestionale/gestionale";
	}

	@GetMapping("/user")
	public String user(Authentication authentication, Model model) {
		model.addAttribute("loggedUser", authentication.getName());
		return "user";
	}

	@GetMapping("/admin")
	public String admin() {
		return "admin";
	}

}
