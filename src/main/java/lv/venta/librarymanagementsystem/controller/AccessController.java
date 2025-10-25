package lv.venta.librarymanagementsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccessController {

    @GetMapping("/") //localhost:8080/
	public String list() {
		return "index";
	}

    @GetMapping("/error")//localhost:8080/error
	public String getError() throws Exception {
		return "error-page";
	}
	
	@GetMapping("/access-denied")//localhost:8080/access-denied
	public String getAccesDenied(Model model ) {
		model.addAttribute("errormsg", "You don't have access to this page!!!");
		return "error-page";
	}

}