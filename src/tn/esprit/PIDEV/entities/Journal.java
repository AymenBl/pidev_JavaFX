/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.PIDEV.entities;

import java.util.Date;


/**
 *
 * @author ghada
 */
public class Journal {

    private int id;
    private user user_id;
    private int nbChasse;
    private annimal animal;
    private lieu lieu;
    private Date date;
    private String description;
    private String image;
    private int id_chasseur;

    public Journal() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public user getUser_id() {
        return user_id;
    }

    public void setUser_id(user user_id) {
        this.user_id = user_id;
    }

    public int getNbChasse() {
        return nbChasse;
    }

    public void setNbChasse(int nbChasse) {
        this.nbChasse = nbChasse;
    }

    public annimal getAnimal() {
        return animal;
    }

    public void setAnimal(annimal animal) {
        this.animal = animal;
    }

    public lieu getLieu() {
        return lieu;
    }

    public void setLieu(lieu lieu) {
        this.lieu = lieu;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getId_chasseur() {
        return id_chasseur;
    }

    public void setId_chasseur(int id_chasseur) {
        this.id_chasseur = id_chasseur;
    }
    

   

   

}
