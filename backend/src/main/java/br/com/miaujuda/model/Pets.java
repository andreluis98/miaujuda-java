package br.com.miaujuda.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "pets")
public class Pets implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 80)
    private String name;

    @Column(nullable = false)
    private String gender;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String observation;

    @Column(nullable = false)
    private String pet;

    @Column(name = "pet_image") 
    private String petImage; 

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public String getPet() {
        return pet;
    }

    public void setPet(String pet) {
        this.pet = pet;
    }

    public String getPetImage() { // Getter para a imagem do pet
        return petImage;
    }

    public void setPetImage(String petImage) { // Setter para a imagem do pet
        this.petImage = petImage;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public int hashCode() {
        return Objects.hash(address, gender, id, name, observation, pet, petImage, status);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Pets other = (Pets) obj;
        return Objects.equals(address, other.address) && Objects.equals(gender, other.gender)
                && Objects.equals(id, other.id) && Objects.equals(name, other.name)
                && Objects.equals(observation, other.observation) && Objects.equals(pet, other.pet)
                && Objects.equals(petImage, other.petImage) && Objects.equals(status, other.status);
    }
}
