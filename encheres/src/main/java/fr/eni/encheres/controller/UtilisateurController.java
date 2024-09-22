/**
 *
 */
package fr.eni.encheres.controller;

import fr.eni.encheres.bll.UtilisateurService;
import fr.eni.encheres.bll.contexte.ContexteService;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.exceptions.UserException;
import jakarta.validation.Valid;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

/**
 * Classe en charge de
 *
 * @author : aferry2024
 * @projet : encheres - V1.0
 * @since: 10 sept. 2024 - 11:00:33
 */
@Controller
@RequestMapping()
@SessionAttributes({"userInSession"})
public class UtilisateurController {

    private final UtilisateurService utilisateurService;
    private final PasswordEncoder passwordEncoder;
    private final ContexteService contexteservice;

    public UtilisateurController(UtilisateurService utilisateurService, PasswordEncoder passwordEncoder,
                                 ContexteService contexteservice) {
        this.utilisateurService = utilisateurService;
        this.passwordEncoder = passwordEncoder;
        this.contexteservice = contexteservice;

    }

    @GetMapping("/inscription")
    public String addUtilisateur(Model model) {

        model.addAttribute(new Utilisateur());
        return "view-encheres-inscription";
    }

    @PostMapping("/inscription")
    public String addUtilisateur(@Valid @ModelAttribute("utilisateur") Utilisateur utilisateur,
                                 BindingResult bindingResult, @RequestParam("mdpConfirm") String mdpConfirm, Model model) {
        if (bindingResult.hasErrors()) {
            return "view-encheres-inscription";

        }
        if (utilisateurService.verifByPseudo(utilisateur.getPseudo())) {
            bindingResult.rejectValue("pseudo", "error.utilisateur", "Le pseudo est déjà pris.");

            if (utilisateurService.verifByEmail(utilisateur.getEmail())) {
                bindingResult.rejectValue("email", "error.utilisateur", "L'email est déjà utilisé.");

                if (!utilisateur.getMotDePasse().equals(mdpConfirm)) {
                    bindingResult.rejectValue("motDePasse", "error.utilisateur",
                            "Les mots de passe ne correspondent pas.");
                }
            }
            return "view-encheres-inscription";
        } else {
            String password = utilisateur.getMotDePasse();
            String encodedPassword = passwordEncoder.encode(password);
            utilisateur.setMotDePasse(encodedPassword);
            utilisateurService.addUser(utilisateur);

            return "redirect:/";
        }
    }

    @GetMapping("/detailsProfil")
    public String afficherUnProfil(@RequestParam(name = "pseudo", required = true) String pseudo, Model model,
                                   Principal principal) {
        System.err.println(pseudo);
        if (!pseudo.isEmpty() && !pseudo.isBlank()) {
            Utilisateur utilisateur = utilisateurService.findByPseudo(pseudo);

            Utilisateur userInSession = contexteservice.getUserInSession();
            if (utilisateur != null && userInSession != null) {
                model.addAttribute("utilisateur", utilisateur);
                model.addAttribute("userInSession", userInSession);

                return "view-detail-user";
            } else
                System.out.println("Utilisateur inconnu!!");
        } else {
            System.out.println("Identifiant inconnu");
        }
        return "redirect:/";
    }

    @GetMapping("/detailsProfil/modifierProfil")
    public String afficherFormulaireModification(Model model) {
        Utilisateur userInSession = contexteservice.getUserInSession();
        if (userInSession != null) {
            model.addAttribute("userInSession", userInSession);
            return "view-encheres-modify-user";
        }
        return "redirect:/";

    }

    @PostMapping("/detailsProfil/modifierProfil")
    public String returnFormulaireModification(@Valid @ModelAttribute("userInSession") Utilisateur utilisateur, BindingResult bindingResult, @RequestParam("newpassword") String newpassword, @RequestParam("newpasswordconfirm") String newpasswordconfirm, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "view-encheres-modify-user";
        }
        Utilisateur userInSession = contexteservice.getUserInSession();
        utilisateur.setNoUtilisateur(userInSession.getNoUtilisateur());
        try {
            utilisateurService.updateUser(utilisateur, newpassword, newpasswordconfirm);
            userInSession.setPseudo(utilisateur.getPseudo());
        } catch (UserException e) {
            //model.addAttribute("userInSession", utilisateur);
            model.addAttribute("errorMessage", e.getMessage());
            return "view-encheres-modify-user";
        }
        return "redirect:/detailsProfil?pseudo=" + userInSession.getPseudo();

    }
}
