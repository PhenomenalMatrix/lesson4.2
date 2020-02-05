package com.company;

import java.util.Random;

public class Main {

    public static int[] heroesHealth = {260, 240, 250, 250, 1000, 250, 260,250};
    public static int[] heroesDamage = {20, 15, 10, 0, 3, 10, 20, 15};
    public static String[] heroesAttackType = {"Physical", "Magical", "Kinetic", "-Medic-", "Tank", "Carry", "Berserk", "Tor"};
    public static int boosHealth = 700;
    public static int boosDamage = 50;
    public static String bossDefenceType = "";

    public static void main(String[] args) {

        while (!isFinished()) {
            round();
        }


    }


    public static void changeBossDefence() {
        Random r = new Random();
         int randomIndex = 0;
        // do {
             randomIndex = r.nextInt(heroesAttackType.length);  ///Генерирует 0, 1, 2,
        // } while (randomIndex == 3);
        bossDefenceType = heroesAttackType[randomIndex];

    }

    public static void round() {
        changeBossDefence();
        heroesHit();
        defenceMates();
        bossHit();
        bossStun();
        returnDamage ();

        evasionCarry();
        healMates();
        printStatistics();
    }

    public static void printStatistics() {
        System.out.println("_________________");
        System.out.println("Boss health: " + boosHealth + " Boss Defence: " + bossDefenceType);
        System.out.println("Warrior health: " + heroesHealth[0]);
        System.out.println("Magic health: " + heroesHealth[1]);
        System.out.println("Kinetic health: " + heroesHealth[2]);
        System.out.println("Medic health: " + heroesHealth[3]);
        System.out.println("Tank health: " + heroesHealth[4]);
        System.out.println("Carry health: " + heroesHealth[5]);
        System.out.println("Berserk health: " + heroesHealth[6]);
        System.out.println("Tor health: " + heroesHealth[7]);
        System.out.println("_________________");

    }

    public static void heroesHit() {
        for (int i = 0; i < heroesDamage.length; i++) {

                if (boosHealth > 0 && heroesHealth[i] > 0 && heroesDamage[i] > 0 ) {
                    if (bossDefenceType.equals(heroesAttackType[i])) {
                        Random r = new Random();
                        int coef = r.nextInt(8) + 2;
                        if (boosHealth - heroesDamage[i] * coef < 0) {
                            boosHealth = 0;
                        } else {
                            boosHealth = boosHealth - heroesDamage[i] * coef;
                        }

                        System.out.println(heroesAttackType[i] + " Critically hit " + heroesDamage[i] * coef);
                    } else {
                        if (boosHealth - heroesDamage[i] < 0) {
                            boosHealth = 0;

                        } else {
                            boosHealth = boosHealth - heroesDamage[i];
                        }
                    }
                }
        }
    }


    public static void bossHit() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0 && boosHealth > 0 ) {
                if (heroesHealth[i] - boosDamage < 0) {
                    heroesHealth[i] = heroesHealth[i] - boosDamage;
                    heroesHealth[i] = 0;
                } else {
                    heroesHealth[i] = heroesHealth[i] - boosDamage;
                }
            }
        }
    }

    public static int defenceMates(){
             if (heroesHealth[4] > 0 && boosDamage > 0){
                 boosDamage = 50;
                 if(boosDamage == 50){
                 boosDamage = boosDamage / 2;
                 heroesHealth[4] = heroesHealth[4] -(boosDamage  * heroesHealth.length) ;
                 }
             } else  if (boosDamage != 0){
                 boosDamage = 50;
             }
        return  boosDamage;

    }

    public static void returnDamage (){
        if(heroesHealth[6] > 0 && boosDamage >0){
            Random r = new Random();
            int returnDamageRandom = r.nextInt(boosDamage);
            heroesHealth[6] = heroesHealth[6] + returnDamageRandom;
            int returnDamage = boosDamage + heroesDamage[6];
            boosHealth = boosHealth - returnDamage;
            System.out.println("return damage: " + returnDamage);

        }
    }
    public static int bossStun(){
        Random ra = new Random();
        int stunChance = ra.nextInt(2);
        if (heroesHealth[7] > 0){
            if ( stunChance == 1){
                boosDamage = 0;
                System.out.println("Boss was stuned");
            }
        } return boosDamage;

    }



    public static void healMates() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0 && heroesHealth[3] > 0) {
                heroesHealth[i] = heroesHealth[i] + 10;
            }

        }
    }

    public  static  void  evasionCarry(){
        Random r = new Random();
        int chance = r.nextInt(2);
        if (chance == 1){
            heroesHealth[5] = heroesHealth[5] + boosDamage;
            System.out.println("Carry evasion damage");
        }

    }


    public static boolean isFinished() {
        if (boosHealth <= 0) {
            System.out.println("Heroes won!");
            return true;
        }

        if (heroesHealth[0] <= 0 && heroesHealth[1] <= 0 && heroesHealth[2] <= 0 && heroesHealth[3] <=0) {
            System.out.println("Boss won!!!");
            return true;
        }

        return false;
    }
}
