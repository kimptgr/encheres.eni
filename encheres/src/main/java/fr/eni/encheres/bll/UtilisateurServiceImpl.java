/**
 *
 */
package fr.eni.encheres.bll;

import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.exceptions.UserException;
import fr.eni.encheres.repository.UtilisateurDAO;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe en charge de
 *
 * @author : kim, ophélie, alex
 * @projet : encheres - V1.0
 * @since: 10 sept. 2024 - 10:44:59
 */
@Service
public class UtilisateurServiceImpl implements UtilisateurService {

    /**
     *
     */
    private static final int CREDIT = 200;
    private UtilisateurDAO utilisateurDAO;
    private PasswordEncoder passwordEncoder;

    /**
     * Constructeur.
     *
     * @param utilisateurDAO
     */
    public UtilisateurServiceImpl(UtilisateurDAO utilisateurDAO, PasswordEncoder passwordEncoder) {
        super();
        this.utilisateurDAO = utilisateurDAO;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void addUser(Utilisateur utilisateur) {
        utilisateur.setCredit(CREDIT);
        utilisateur.setAdministrateur(false);
        utilisateurDAO.create(utilisateur);

    }

    @Override
    public boolean verifByEmail(String email) {
        return utilisateurDAO.doesEmailExist(email);
    }

    @Override
    public Utilisateur findById(Integer noUtilisateur) {
        return utilisateurDAO.readById(noUtilisateur);
    }

    @Override
    public boolean verifByPseudo(String pseudo) {
        return utilisateurDAO.doesPseudoExist(pseudo);
    }

    @Override
    public Utilisateur findByPseudo(String pseudo) {
        return utilisateurDAO.readByPseudo(pseudo);
    }

    @Override
    public void updateUser(Utilisateur utilisateurUpdate, String newPassword, String newpasswordconfirm) {
        Utilisateur userInDataBase = utilisateurDAO.readById(utilisateurUpdate.getNoUtilisateur());
        String oldpassword = utilisateurUpdate.getMotDePasse();
        List<String> errors = new ArrayList<>();
        boolean isValid = true;

// Vérifie que c'est le bon mot de passe
        if (!passwordEncoder.matches(oldpassword, userInDataBase.getMotDePasse())) {
            isValid = false;
            errors.add("Mot de passe incorrect");
        }

// Vérifie si un nouveau mot de passe est fourni
        if (newPassword == null || newPassword.isBlank()) {
            utilisateurUpdate.setMotDePasse(userInDataBase.getMotDePasse());
        } else if (newPassword.equals(newpasswordconfirm)) {
            // Vérifie qu'il correspond aux critères
            if (!newPassword.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}$")) {
                isValid = false;
                errors.add("Le mot de passe doit contenir 8 caractères dont 1 minuscule, 1 majuscule, 1 chiffre et un caractère spécial.");
            } else {
                // Encode le mot de passe
                utilisateurUpdate.setMotDePasse(passwordEncoder.encode(newPassword));
            }
        } else {
            isValid = false;
            errors.add("Les mots de passe ne correspondent pas.");
        }

// Vérifie si le nom d'utilisateur est déjà pris
        if (!utilisateurUpdate.getPseudo().equals(userInDataBase.getPseudo())
                && utilisateurDAO.doesPseudoExist(utilisateurUpdate.getPseudo())) {
            isValid = false;
            errors.add("Nom d'utilisateur déjà pris.");
        }

// Vérifie si l'email est déjà pris
        if (!utilisateurUpdate.getEmail().equals(userInDataBase.getEmail())
                && utilisateurDAO.doesEmailExist(utilisateurUpdate.getEmail())) {
            isValid = false;
            errors.add("E-mail déjà pris.");
        }
// Si des erreurs sont présentes, lève une exception
        if (!isValid) {
            throw new UserException(String.join("\n", errors));
        } else {
            utilisateurDAO.update(utilisateurUpdate);
        }
    }
}
