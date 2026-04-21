package com.emi.nutritrack.entity;

import jakarta.persistence.Entity;

@Entity
public class DailyLog {


    private Long id;
    private date date;
    private int totalCalories;
    private int totalProtein;
    private int totalCarbs;
    private int totalFat;
    private String user;
    /*

        id
    date
    totalCalories
    totalProtein
    totalCarbs
    totalFat
    user


         user aura une relation vers mealentry dailylog dailygoal qui sera one to many
         j'aurais food qui aura une relation entre foodcategory one to many
         user aura une relation vers mealentry one to many aussi
         meal entry aura une relation vers user qui aura many to one
         foodcategory aura un many to one vers food
         dailylog aura une relation de many to one avec user et daily goal aussi si il en reste tu sais juste me citer les tables qui me resterait à lier



     */


}
