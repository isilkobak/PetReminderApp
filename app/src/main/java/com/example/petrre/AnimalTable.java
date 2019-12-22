package com.example.petrre;

public class AnimalTable {

    String name;
    String breed_name;
    String vaccine;
    String age;
    float height;
    float weight;
    String last_date;

    public AnimalTable(String name, String breed_name, String vaccine, String age_category, float height, float weight, String date) {
        this.name = name;
        this.breed_name = breed_name;
        this.vaccine = vaccine;
        this.age = age_category;
        this.height = height;
        this.weight = weight;
        this.last_date = date;
    }

    public AnimalTable(String breed, String vaccine, String last_date){
        this.breed_name = breed;
        this.vaccine = vaccine;
        this.last_date = last_date;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return last_date;
    }

    public void setDate(String date) {
        this.last_date = date;
    }

    public String getBreed_name() {
        return breed_name;
    }

    public void setBreed_name(String breed_name) {
        this.breed_name = breed_name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age_category) {
        this.age = age_category;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }
}
