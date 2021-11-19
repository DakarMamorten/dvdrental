package com.dvdrental;

import com.dvdrental.dao.ActorDAO;
import com.dvdrental.dao.CategoryDAO;
import com.dvdrental.dao.FilmDAO;
import com.dvdrental.menu.ActorsMenu;
import com.dvdrental.menu.CategoryMenu;
import com.dvdrental.menu.FilmMenu;
import com.dvdrental.menu.MainMenu;
import com.dvdrental.model.Actor;
import com.dvdrental.model.Category;
import com.dvdrental.model.Film;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Scanner;

public class Main {
    static CategoryDAO categoryDAO = new CategoryDAO();
    static ActorDAO actorDAO = new ActorDAO();
    static FilmDAO filmDAO = new FilmDAO();
    private static final Logger LOGGER = Logger.getLogger(Main.class);

    public static void main(String[] args) {

        MainMenu mainMenu = new MainMenu();
        boolean flag = true;
        while (flag) {
            mainMenu.show();
            System.out.println("Enter your choice: ");
            Scanner scanner = new Scanner(System.in);
            String choice = scanner.next();
            switch (choice) {//Process main menu
                case "c":
                    String userChoice = categoryMenuShow();
                    processCategoryMenu(userChoice);
                    break;
                case "a":
                    String userChoice1 = actorMenuShow();
                    processActorMenu(userChoice1);
                    break;
                case "f":
                    String userChoice2 = filmMenuShow();
                    processFilmMenu(userChoice2);
                    break;
                case "e":
                    flag = false;
                    break;
                default:
                    System.out.println("Bye!");
            }
        }

    }

    private static String categoryMenuShow() {
        CategoryMenu categoryMenu = new CategoryMenu();
        categoryMenu.show();
        return inputStringChoiceFromUser();//Ожидаем ввода(выбора) от пользователя
    }

    private static String actorMenuShow() {
        ActorsMenu actorsMenu = new ActorsMenu();
        actorsMenu.show();
        return inputStringChoiceFromUser();
    }

    private static String filmMenuShow() {
        FilmMenu filmMenu = new FilmMenu();
        filmMenu.show();
        return inputStringChoiceFromUser();
    }

    private static void processCategoryMenu(String userChoice) {
        switch (userChoice) {
            case "sc":
                List<Category> categories = categoryDAO.findAll();
                categories.forEach(c -> System.out.println(c.getCategoryID() + " - " + c.getName()));
                System.out.println("0 - Back to category menu");
                int userCategoryChoice = inputDigitChoiceFromUser();
                switch (userCategoryChoice) {
                    case 0:
                        String userChoice1 = categoryMenuShow();
                        processCategoryMenu(userChoice1);
                        break;
                    default:
                        break;
                }
            case "af":
                List<Film> films = filmDAO.findFilmByCategoryId((long) inputDigitChoiceFromUser());
                films.forEach(film -> System.out.println(film.getFilmId() + " - " + film.getTitle() + " - " + film.getDescription() + " - " + film.getReleaseYear() + " - " +
                        film.getLanguageId() + " - " + film.getRentalDuration() + " - " + film.getRentalRate() + " - " + film.getLength() + " - " + film.getReplacementCost()
                        + " - " + film.getRating() + " - " + film.getLastUpdate() + " - " + film.getSpecialFeatures() + " - " + film.getFullText()));
                String userChoice2 = categoryMenuShow();
                processCategoryMenu(userChoice2);
                break;
            case "adf" :
            case "ac":
                categoryDAO.save(new Category(inputStringChoiceFromUser()));
                LOGGER.info("Please insert name: ");
                String userChoice1 = categoryMenuShow();
                processCategoryMenu(userChoice1);
                break;
            default:
                break;
        }
    }

    private static void processActorMenu(String userChoice) {
        switch (userChoice) {
            case "sa":
                List<Actor> actors = actorDAO.findAll();
                actors.forEach(actor -> System.out.println((actor.getActorId() + " - " + actor.getFirstName() + " - " + actor.getLastName())));
                System.out.println("0 - Back to actor menu");
                int userActorChoice = inputDigitChoiceFromUser();
                switch (userActorChoice) {
                    case 0:
                        String userChoice1 = actorMenuShow();
                        processActorMenu(userChoice1);
                        break;
                    default:
                        break;
                }
            default:
                break;
        }
    }

    private static void processFilmMenu(String userChoice) {
        switch (userChoice) {
            case "fa":
                List<Film> films = filmDAO.findAll();
                films.forEach(film -> System.out.println(film.getFilmId() + " - " + film.getTitle() + " - " + film.getDescription() + " - " + film.getReleaseYear() + " - " +
                        film.getLanguageId() + " - " + film.getRentalDuration() + " - " + film.getRentalRate() + " - " + film.getLength() + " - " + film.getReplacementCost()
                        + " - " + film.getRating() + " - " + film.getLastUpdate() + " - " + film.getSpecialFeatures() + " - " + film.getFullText()));
                System.out.println("0 - Back to actor menu");
                int userFilmChoice = inputDigitChoiceFromUser();
                switch (userFilmChoice) {
                    case 0:
                        String userChoice1 = filmMenuShow();
                        processFilmMenu(userChoice1);
                        break;
                    default:
                        break;
                }
            default:
                break;
        }
    }

    private static int inputDigitChoiceFromUser() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    private static String inputStringChoiceFromUser() {
        Scanner scanner = new Scanner(System.in);
        return scanner.next();
    }
}

