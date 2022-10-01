package com.greatlearning.crmapp_demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.greatlearning.crmapp_demo.entity.Customer;
import com.greatlearning.crmapp_demo.service.CustomerService;

@Controller
@RequestMapping("/customers")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@RequestMapping("/list")
	public String listCustomer(Model theModel) {

		List<Customer> customers = customerService.findAll();
		theModel.addAttribute("customers", customers);
		return "list-customers";

	}

	@RequestMapping("/showFormForAdd")
	public String showFormforAdd(Model theModel) {
		Customer theCustomer = new Customer();

		theModel.addAttribute("Customer", theCustomer);

		return "Customer-form";

	}

	@RequestMapping("/showFormForUpdate")
	public String showFormforUpdate(@RequestParam("customerEmail") String email, Model theModel){
		
		Customer theCustomer = customerService.findByEmail(email);
		
		theModel.addAttribute("Customer",theCustomer);

		return "Customer-form";

	}

	@PostMapping("/save")
	public String saveBook(@RequestParam("email") String email, @RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName){
		Customer theCustomer;

		if (customerService.findByEmail(email) != null) {
			theCustomer = customerService.findByEmail(email);
			theCustomer.setFirstName(firstName);
			theCustomer.setLastName(lastName);
		} else
			theCustomer = new Customer(email, firstName, lastName);
		customerService.save(theCustomer);

		return "redirect:/customers/list";

	}

	@RequestMapping("/delete")
	public String delete(@RequestParam("customerEmail") String theEmail) {

		customerService.deleteByEmail(theEmail);

		return "redirect:/customers/list";

	}
}
