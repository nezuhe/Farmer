package pl.piotrchowaniec;

import java.util.ArrayList;
import java.util.List;

public class Barn {
    private int id;
    private String name;
    private List<Animal> animalsInBarn;
    private Animal.Species species;
    private Security security;

    public Barn() {}

    public Barn(int id, String name) {
        this.id = id;
        this.name = name;
        animalsInBarn = new ArrayList<>();
        security = new Security();
    }

    public void addAnimal(int barnId) {
        printChooseSpeciesText();
        security.choiceNumberValidation(5);
        int speciesId = Integer.parseInt(security.getChoiceString());
        Animal.Species species = getSpeciesById(speciesId);

        printTypeAge();
        security.choiceNumberValidation();
        int age = Integer.parseInt(security.getChoiceString());

        printIsVaccinated();
        boolean isVaccinated = security.yesNoChoiceValidation();

        animalsInBarn.add(new Animal(species, age, isVaccinated, barnId));
    }

    public Animal.Species getSpeciesById(int speciesId) {
        switch (speciesId) {
            case 1: {
                species = Animal.Species.COW;
                break;
            }
            case 2: {
                species = Animal.Species.HORSE;
                break;
            }
            case 3: {
                species = Animal.Species.PIG;
                break;
            }
            case 4: {
                species = Animal.Species.DUCK;
                break;
            }
            case 5: {
                species = Animal.Species.CHICKEN;
                break;
            }
        }
        return species;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Animal> getAnimalsInBarn() {
        return animalsInBarn;
    }

    public void setAnimalsInBarn(List<Animal> animalsInBarn) {
        this.animalsInBarn = animalsInBarn;
    }

    public void printBarnName() {
        System.out.println("\nStodoła nr: " + getId() + ". " + getName());
    }

    private void printChooseSpeciesText() {
        System.out.print("\nPodaj gatunek: 1. Krowa, 2. Koń, 3. Świnia, 4. Kaczka, 5. Kura: ");
    }

    private void printTypeAge() {
        System.out.print("Podaj wiek zwierzęcia: ");
    }

    private void printIsVaccinated() {
        System.out.print("Czy zwierzę jest zaszczepione: (T)ak / (N)ie: ");
    }

    public void printAnimalList() {
        if (animalsInBarn.isEmpty()) {
            printListIsEmptyText();
        } else {
            printBarnName();
            printAnimals(animalsInBarn);
        }
    }

    private void printListIsEmptyText() {
        System.out.println("Lista jest pusta.");
    }

    public void printAnimals(List<Animal> animalList) {
        System.out.println("\nGatunek | Wiek | Szczepienie");
        animalList.stream()
                .forEach(s -> System.out.printf("%7s |  %2d  | %s%n", s.getSpeciesToString(s.getSpecies()), s.getAge(),
                        s.isVaccinatedString(s.isVaccinated())));
    }
}
