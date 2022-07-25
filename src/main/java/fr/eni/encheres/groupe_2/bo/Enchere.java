package fr.eni.encheres.groupe_2.bo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Enchere implements Serializable {
    private int no_enchere;
   private Timestamp dateEnchere;
   private int montantEnchere;
   private int no_article;
   private int no_utilisateur;

    public Enchere(int no_enchere, Timestamp dateEnchere, int montantEnchere, int no_article, int no_utilisateur) {
        this.no_enchere = no_enchere;
        this.dateEnchere = dateEnchere;
        this.montantEnchere = montantEnchere;
        this.no_article = no_article;
        this.no_utilisateur = no_utilisateur;
    }

    public Enchere(Timestamp dateEnchere, int montantEnchere, int no_article, int no_utilisateur) {
        this.dateEnchere = dateEnchere;
        this.montantEnchere = montantEnchere;
        this.no_article = no_article;
        this.no_utilisateur = no_utilisateur;
    }

    public Enchere() {
    }

    public int getNo_enchere() {
        return no_enchere;
    }

    public void setNo_enchere(int no_enchere) {
        this.no_enchere = no_enchere;
    }

    public Timestamp getDateEnchere() {
        return dateEnchere;
    }

    public void setDateEnchere(Timestamp dateEnchere) {
        this.dateEnchere = dateEnchere;
    }

    public int getMontantEnchere() {
        return montantEnchere;
    }

    public void setMontantEnchere(int montantEnchere) {
        this.montantEnchere = montantEnchere;
    }

    public int getNo_article() {
        return no_article;
    }

    public void setNo_article(int no_article) {
        this.no_article = no_article;
    }

    public int getNo_utilisateur() {
        return no_utilisateur;
    }

    public void setNo_utilisateur(int no_utilisateur) {
        this.no_utilisateur = no_utilisateur;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Enchere{");
        sb.append("no_enchere=").append(no_enchere);
        sb.append(", dateEnchere=").append(dateEnchere);
        sb.append(", montantEnchere=").append(montantEnchere);
        sb.append(", no_article=").append(no_article);
        sb.append(", no_utilisateur=").append(no_utilisateur);
        sb.append('}');
        return sb.toString();
    }
}
