package lv.venta.librarymanagementsystem.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import lv.venta.librarymanagementsystem.model.Publisher;
import lv.venta.librarymanagementsystem.service.PublisherService;

@Controller
public class PublisherController {

	@Autowired
	private PublisherService publisherService;

	public PublisherController(PublisherService publisherService) {
		this.publisherService = publisherService;
	}

	@GetMapping("/publishers")
	public String findAllPublishers(Model model) {
		List<Publisher> publishers = publisherService.findAllPublishers();
		model.addAttribute("publishers", publishers);
		return "list-publishers";
	}

	@GetMapping("/publisher/{id}")
	public String findPublisherById(@PathVariable("id") Long id, Model model) throws Exception {
		Publisher publisher = publisherService.findPublisherById(id);
		model.addAttribute("publisher", publisher);
		return "list-publisher";
	}

	@GetMapping("/add-publisher")
	public String showCreateForm(Publisher publisher, Model model) {
		try {
			return "add-publisher";
		} catch (Exception e) {
			model.addAttribute("errormsg", e.getMessage());
			return "error-page";
		}
	}

	@PostMapping("/add-publisher")
	public String createPublisher(Publisher publisher, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "add-publisher";
		}
		publisherService.createPublisher(publisher);
		model.addAttribute("publisher", publisherService.findAllPublishers());
		return "redirect:/publishers";
	}

	@GetMapping("/update-publisher/{id}")
	public String showUpdateForm(@PathVariable("id") Long id, Model model) throws Exception {
		try {
			Publisher publisher = publisherService.findPublisherById(id);
		    model.addAttribute("publisher", publisher);
		    return "update-publisher";
		} catch (Exception e) {
			model.addAttribute("errormsg", e.getMessage());
			return "error-page";
		}
	}

	@PostMapping("/update-publisher/{id}")
	public String updatePublisher(@PathVariable("id") Long id, Publisher publisher, BindingResult result, Model model) {
		if (result.hasErrors()) {
			publisher.setId(id);
			return "update-publishers";
		}
		publisherService.updatePublisher(publisher);
		model.addAttribute("publisher", publisherService.findAllPublishers());
		return "redirect:/publishers";
	}

	@GetMapping("/remove-publisher/{id}")
	public String deletePublisher(@PathVariable("id") Long id, Model model) throws Exception {
		try {
			publisherService.deletePublisher(id);
		    model.addAttribute("publisher", publisherService.findAllPublishers());
		    return "redirect:/publishers";
		} catch (Exception e) {
			model.addAttribute("errormsg", e.getMessage());
			return "error-page";
		}
	}

}