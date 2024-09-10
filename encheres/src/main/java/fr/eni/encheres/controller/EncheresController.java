/**
 * 
 */
package fr.eni.encheres.controller;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Classe en charge de 
 * @projet : encheres - V1.0
 * @author : kpatingre2024
 * @since: 9 sept. 2024 - 15:01:28
 */


public class EncheresController {
	
	@GetMapping
	public String afficherIndex() {
		System.out.println("EncheresController.afficherIndex()");
		return "index";
	}

}
