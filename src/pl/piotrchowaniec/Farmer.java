package pl.piotrchowaniec;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static pl.piotrchowaniec.Animal.Species.*;

public class Farmer {
    private List<Barn> barnList;
    private Scanner scanner;
    private Security security;

    public Farmer() {
        barnList = new ArrayList<>();
        scanner = new Scanner(System.in);
        security = new Security();
    }

    public void addNewBarn() {
        printSetNewBarnNameText();
        boolean isNameCorrect;
        String newBarnName;
        do {
            newBarnName = scanner.nextLine();
            if (security.isBarnNameAvaliable(this, newBarnName)) {
                isNameCorrect = true;
            } else {
                printNameAlreadyExistsText();
                isNameCorrect = false;
            }
        } while (!isNameCorrect);
        barnList.add(new Barn(setNewBarnId(), newBarnName));
    }

    private int setNewBarnId() {
        int newId;
        if (barnList.size() > 0) {
            newId = barnList.get(barnList.size() - 1).getId() + 1;
        } else {
            newId = 1;
        }
        return newId;
    }

    public void addAnimalToBarn() {
        printBarnList();
        if (!barnList.isEmpty()) {
            printChooseBarnText();
            security.choiceNumberValidation(barnList.size());
            int id = Integer.parseInt(security.getChoiceString());
            barnList.get(id - 1).addAnimal(id);
        }
    }

    public void setAnimalsToBarns(List<Animal> animalList) {
        for (int i = 0; i < barnList.size(); i++) {
            int j = i;
            List<Animal> animalsInBarn = animalList.stream()
                    .filter(s -> s.getBarnId() == barnList.get(j).getId())
                    .collect(Collectors.toList());
            barnList.get(i).setAnimalsInBarn(animalsInBarn);
        }
    }

    public List<Animal> getAllAnimalsList() {
        List<Animal> animalList = new ArrayList<>();
        for (int i = 0; i < barnList.size(); i++) {
            animalList.addAll(barnList.get(i).getAnimalsInBarn());
        }
        return animalList;
    }

    public void removeBarn() {
        printBarnList();
        printChooseBarnText();
        security.choiceNumberValidation(barnList.size());
        int id = Integer.parseInt(security.getChoiceString()) - 1;
        printRemovalWarning();
        if (security.yesNoChoiceValidation()) {
            barnList.remove(id);
        }
    }

    public List<Animal> get5YoungestAnimals() {
        return getAllAnimalsList().stream()
                .sorted(Comparator.comparingInt(s -> s.getAge()))
                .limit(5)
                .collect(Collectors.toList());
    }

    public List<Animal> get5OldestAnimals() {
        return getAllAnimalsList().stream()
                .sorted(Comparator.comparing(Animal::getAge).reversed())
                .limit(5)
                .collect(Collectors.toList());
    }

    public int getMostSpecies() {
        Animal.Species[] species = {COW, HORSE, PIG, DUCK, CHICKEN};
        int count = 0, max = 0, mostSpecies = 0;
        for (int i = 0; i < species.length; i++) {
            for (Animal animal : getAllAnimalsList()) {
                if (animal.getSpecies() == species[i]) {
                    count++;
                }
            }
            if (count > max) {
                max = count;
                mostSpecies = i + 1;
            }
            count = 0;
        }
        return mostSpecies;
    }

    public List<Animal> getMostSpeciesList(Animal.Species species) {
        return getAllAnimalsList().stream()
                .filter(s -> s.getSpecies() == species)
                .collect(Collectors.toList());
    }

    public List<Animal> getVaccinatedAnimals() {
        return getAllAnimalsList().stream()
                .filter(s -> s.isVaccinated() == true)
                .collect(Collectors.toList());
    }

    public List<Barn> getBarnList() {
        return barnList;
    }

    public void setBarnList(List<Barn> barnList) {
        this.barnList = barnList;
    }

    private void printSetNewBarnNameText() {
        System.out.print("Podaj nazwę stodoły: ");
    }

    public void printBarnList() {
        if (barnList.isEmpty()) {
            printListIsEmptyText();
        } else {
            printBarns();
        }
    }

    public void printAnimalsInBarn() {
        printBarnList();
        if (!barnList.isEmpty()) {
            printChooseBarnText();
            security.choiceNumberValidation(barnList.size());
            int id = Integer.parseInt(security.getChoiceString()) - 1;
            barnList.get(id).printAnimalList();
        }
    }

    private void printChooseBarnText() {
        System.out.print("Wybierz stodołę: ");
    }

    private void printRemovalWarning() {
        System.out.print("\nUWAGA!" +
                "\nUsuwając stodołę usuniesz także wszystkie zwierzęta." +
                "\nCzy jesteś pewny: (T)ak / (N)ie");
    }

    private void printNameAlreadyExistsText() {
        System.out.print("Podana nazwa już istnieje. Spróbuj jeszcze raz: ");
    }

    private void printListIsEmptyText() {
        System.out.println("Lista jest pusta.");
    }

    private void printBarns() {
        System.out.printf("%n%s  %s%n", "Id", "Nazwa");
        barnList.stream()
                .forEach(s -> System.out.printf("%2d. %s%n", s.getId(), s.getName()));
    }

    public void pressEnter() {
        System.out.print("\nNaciśnij ENTER aby kontynuować...");
        scanner.nextLine();
    }
}
