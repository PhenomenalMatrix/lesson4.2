package com.company;

import java.util.Random;

public class Main {

    public static int[] heroesHealth = {260, 240, 250, 250};
    public static int[] heroesDamage = {20, 15, 10, 0};
    public static String[] heroesAttackType = {"Physical", "Magical", "Kinetic", "-Medic-"};
    public static int boosHealth = 700;
    public static int boosDamage = 50;
    public static String bossDefenceType = "";

    public static void main(String[] args) {

        while (!isFinished()) {
            round();
        }
        ;

    }


    public static void changeBossDefence() {
        Random r = new Random();
         int randomIndex = 0;
         do {
             randomIndex = r.nextInt(heroesAttackType.length);  ///Генерирует 0, 1, 2,
         } while (randomIndex == 3);
        bossDefenceType = heroesAttackType[randomIndex];

    }

    public static void round() {
        changeBossDefence();
        heroesHit();
        bossHit();
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
            if (heroesHealth[i] > 0 && boosHealth > 0) {
                if (heroesHealth[i] - boosDamage < 0) {
                    heroesHealth[i] = heroesHealth[i] - boosDamage;
                    heroesHealth[i] = 0;
                } else {
                    heroesHealth[i] = heroesHealth[i] - boosDamage;
                }
            }
        }
    }

    public static void healMates() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0 && heroesHealth[3] > 0) {
                heroesHealth[i] = heroesHealth[i] + 10;
            }

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
