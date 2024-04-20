package in.ashokit.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import in.ashokit.entity.Product;
import in.ashokit.repo.ProductRepo;

@Controller
public class ProductController {

	@Autowired
	ProductRepo repo;

//	load empty page
	@GetMapping("/")
	public String loadProductPage(Model model) {
		model.addAttribute("p", new Product());
		return "index";
	}

//	add product to db binding data

	@PostMapping("/product")
	public String saveProduct(@Validated @ModelAttribute("p") Product p,BindingResult result, Model model) {
		
		if(result.hasErrors()) {
			return "index";
		}
		
		
		p = repo.save(p);
		if(p.getPid() != null) {
			model.addAttribute("msg", "Product added");
		}
		
		return "index";

	}
	
//	show products that are added
	
	@GetMapping("/products")
	public String showProducts(Model model) {
		
		model.addAttribute("products", repo.findAll());
		
		return "data";
	}
	
//	Delete products 
	
	@GetMapping("/delete")
	public String deleteProduct(@RequestParam("pid")Integer pid, Model model) {
		
		repo.deleteById(pid);
		model.addAttribute("dmsg", "Product Deleted");
		model.addAttribute("products", repo.findAll());
		return "data";
	}
	
	@GetMapping("/edit")
	public String edit(@RequestParam("pid") Integer pid, Model model) {
		
		Optional<Product> byId = repo.findById(pid);
		
		if(byId.isPresent()) {
			Product product = byId.get();
			model.addAttribute("p", product);
		}
		
		return "index";
		
	}
}
