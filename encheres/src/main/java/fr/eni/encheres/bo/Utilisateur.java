package fr.eni.encheres.bo;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.util.List;
import java.util.Objects;

public class Utilisateur {

    private Integer noUtilisateur; // primary key identity

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9]{3,20}$"
            , message = "Votre pseudo doit contenir entre 3 et 20 caractères alphanumériques.")
    private String pseudo;

    @NotBlank
    @Pattern(regexp = "^[A-Za-zÀ-ÿ][A-Za-zÀ-ÿ'-]{1,}(?: [A-Za-zÀ-ÿ][A-Za-zÀ-ÿ'-]*)*$"
            , message = "Votre nom doit contenir au minimum une lettre.")
    private String nom;

    @NotBlank
    @Pattern(regexp = "^[A-Za-zÀ-ÿ][A-Za-zÀ-ÿ'-]{2,29}$",
            message = "Votre prénom doit contenir au minimum 2 lettres.")
    private String prenom;

    @NotBlank
    @Email
    @Pattern(regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$",
            message = "Votre email est invalide. Veuillez saisir un email valide.")
    private String email;

    @NotBlank
    @Pattern(regexp = "^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\s\\./0-9]*$"
            , message = "Numéro de téléphone non reconnu.")
    private String telephone;

    @NotBlank
    private String rue;

    @NotBlank
    @Pattern(regexp = "^\\d{5,6}$"
            , message = "Code postal inconnu")
    private String codePostal;

    @NotBlank
    @Pattern(regexp = "^[a-zA-ZÀ-ÿ' -]+$"
            , message = "Nom de ville incorrect")
    private String ville;

    @NotBlank
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}$"
            , message = "Le mot de passe comprend au moins 8 caractères dont 1 majuscule, 1 minisucule, 1 chiffres, 1 caractère spécial (@#$%^&+=)")
    private String motDePasse;

    private Integer credit;

    private Boolean administrateur;

    private List<ArticleVendu> articleVendus;
    private List<Enchere> enchere;

    /**
     * Constructeur par défaut
     */
    public Utilisateur() {
    }


    public Utilisateur(Integer noUtilisateur, String pseudo) {
        super();
        this.noUtilisateur = noUtilisateur;
        this.pseudo = pseudo;
    }


    /**
     * Constructeur.
     *
     * @param noUtilisateur
     * @param pseudo
     * @param nom
     * @param prenom
     * @param email
     * @param telephone
     * @param rue
     * @param codePostal
     * @param ville
     * @param motDePasse
     * @param credit
     * @param administrateur
     * @param articleVendus
     * @param enchere
     */
    public Utilisateur(Integer noUtilisateur, String pseudo, String nom, String prenom, String email, String telephone,
                       String rue, String codePostal, String ville, String motDePasse, Integer credit, Boolean administrateur,
                       List<ArticleVendu> articleVendus, List<Enchere> enchere) {
        super();
        this.noUtilisateur = noUtilisateur;
        this.pseudo = pseudo;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        this.rue = rue;
        this.codePostal = codePostal;
        this.ville = ville;
        this.motDePasse = motDePasse;
        this.credit = credit;
        this.administrateur = administrateur;
        this.articleVendus = articleVendus;
        this.enchere = enchere;
    }


    /**
     * Constructeur.
     *
     * @param pseudo
     * @param nom
     * @param prenom
     * @param email
     * @param telephone
     * @param rue
     * @param codePostal
     * @param ville
     * @param motDePasse
     */
    public Utilisateur(String pseudo, String nom, String prenom, String email, String telephone, String rue,
                       String codePostal, String ville, String motDePasse) {
        super();
        this.pseudo = pseudo;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        this.rue = rue;
        this.codePostal = codePostal;
        this.ville = ville;
        this.motDePasse = motDePasse;
    }

    public boolean isAdmin() {
        return administrateur;
    }


    /**
     * Getter for noUtilisateur.
     *
     * @return the noUtilisateur
     */
    public Integer getNoUtilisateur() {
        return noUtilisateur;
    }

    /**
     * Setter pour the noUtilisateur.
     *
     * @param noUtilisateur the noUtilisateur to set
     */
    public void setNoUtilisateur(Integer noUtilisateur) {
        this.noUtilisateur = noUtilisateur;
    }

    /**
     * Getter for pseudo.
     *
     * @return the pseudo
     */
    public String getPseudo() {
        return pseudo;
    }

    /**
     * Setter pour the pseudo.
     *
     * @param pseudo the pseudo to set
     */
    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    /**
     * Getter for nom.
     *
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Setter pour the nom.
     *
     * @param nom the nom to set
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Getter for prenom.
     *
     * @return the prenom
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Setter pour the prenom.
     *
     * @param prenom the prenom to set
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * Getter for email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setter pour the email.
     *
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Getter for telephone.
     *
     * @return the telephone
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * Setter pour the telephone.
     *
     * @param telephone the telephone to set
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    /**
     * Getter for rue.
     *
     * @return the rue
     */
    public String getRue() {
        return rue;
    }

    /**
     * Setter pour the rue.
     *
     * @param rue the rue to set
     */
    public void setRue(String rue) {
        this.rue = rue;
    }

    /**
     * Getter for codePostal.
     *
     * @return the codePostal
     */
    public String getCodePostal() {
        return codePostal;
    }

    /**
     * Setter pour the codePostal.
     *
     * @param codePostal the codePostal to set
     */
    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    /**
     * Getter for ville.
     *
     * @return the ville
     */
    public String getVille() {
        return ville;
    }

    /**
     * Setter pour the ville.
     *
     * @param ville the ville to set
     */
    public void setVille(String ville) {
        this.ville = ville;
    }

    /**
     * Getter for motDePasse.
     *
     * @return the motDePasse
     */
    public String getMotDePasse() {
        return motDePasse;
    }

    /**
     * Setter pour the motDePasse.
     *
     * @param motDePasse the motDePasse to set
     */
    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    /**
     * Getter for credit.
     *
     * @return the credit
     */
    public Integer getCredit() {
        return credit;
    }

    /**
     * Setter pour the credit.
     *
     * @param credit the credit to set
     */
    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    /**
     * Getter for administrateur.
     *
     * @return the administrateur
     */
    public Boolean getAdministrateur() {
        return administrateur;
    }

    /**
     * Setter pour the administrateur.
     *
     * @param administrateur the administrateur to set
     */
    public void setAdministrateur(Boolean administrateur) {
        this.administrateur = administrateur;
    }

    /**
     * Getter for articleVendus.
     *
     * @return the articleVendus
     */
    public List<ArticleVendu> getArticleVendus() {
        return articleVendus;
    }

    /**
     * Setter pour the articleVendus.
     *
     * @param articleVendus the articleVendus to set
     */
    public void setArticleVendus(List<ArticleVendu> articleVendus) {
        this.articleVendus = articleVendus;
    }

    /**
     * Getter for enchere.
     *
     * @return the enchere
     */
    public List<Enchere> getEnchere() {
        return enchere;
    }

    /**
     * Setter pour the enchere.
     *
     * @param enchere the enchere to set
     */
    public void setEnchere(List<Enchere> enchere) {
        this.enchere = enchere;
    }

    @Override
    public String toString() {
        return "Utilisateur [noUtilisateur=" + noUtilisateur + ", pseudo=" + pseudo + ", nom=" + nom + ", prenom="
                + prenom + ", email=" + email + ", telephone=" + telephone + ", rue=" + rue + ", codePostal="
                + codePostal + ", ville=" + ville + ", motDePasse=" + motDePasse + ", credit=" + credit
                + ", administrateur=" + administrateur + ", articleVendus=" + articleVendus + ", enchere=" + enchere
                + "]";
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + Objects.hash(administrateur, email);
        return result;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Utilisateur other = (Utilisateur) obj;
        return administrateur == other.administrateur && Objects.equals(email, other.email);
    }

}
