package com.example.Elasticsearch;

import java.io.IOException;

//import org.springframework.stereotype.Controller;
//import org.springframework.data.elasticsearch.client.elc.ElasticsearchAggregation;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UIController {

	  private ElasticsearchQuery elasticSearchQuery;

	    @GetMapping("/home")
	    public String viewHomePage(Model model) throws IOException {
	        model.addAttribute("listProductDocuments",elasticSearchQuery.searchAllDocuments());
	        return "index";
	    }

	    @PostMapping("/saveProduct")
	    public String saveProduct(@ModelAttribute("product") Product product) throws IOException {
	        elasticSearchQuery.createOrUpdateDocument(product);
	        return "redirect:/";
	    }

	    @GetMapping("/showFormForUpdate/{id}")
	    public String showFormForUpdate(@PathVariable(value = "id") String id, Model model) throws IOException {

	        Product product = elasticSearchQuery.getDocumentById(id);
	        model.addAttribute("product", product);
	        return "updateProductDocument";
	    }

	    @PostMapping("/NewProductForm")
	    public String showNewEmployeeForm(Model model) {
	        // create model attribute to bind form data
	        Product product = new Product();
	        model.addAttribute("product", product);
	        return "newProductDocument";
	    }

	    @GetMapping("/deleteProduct/{id}")
	    public String deleteProduct(@PathVariable(value = "id") String id) throws IOException {

	        this.elasticSearchQuery.deleteDocumentById(id);
	        return "redirect:/";
	    }
}
