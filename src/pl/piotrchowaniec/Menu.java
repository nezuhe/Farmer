package pl.piotrchowaniec;

class Menu {
    private boolean stayInMenu;
    private Farmer farmer;
    private DatabaseUtilities utils;
    private Security security;
    private Barn barn;

    Menu() {
        stayInMenu = true;
        farmer = new Farmer();
        utils = new DatabaseUtilities();
        security = new Security();
        barn = new Barn();
    }

    void startMenu() {printOpeningText();
        readFromDatabase();
        farmer.setBarnList(utils.getBarnListFromFile());
        farmer.setAnimalsToBarns(utils.getAnimalListFromFile());

        while(stayInMenu) {
            printMenu();

            security.switchNumberValidation(9);
            int choice = Integer.parseInt(security.getChoiceString());

            switch (choice) {
                case 1: {
                    farmer.addNewBarn();
                    break;
                }
                case 2: {
                    farmer.addAnimalToBarn();
                    break;
                }
                case 3: {
                    farmer.printBarnList();
                    farmer.pressEnter();
                    break;
                }
                case 4: {
                    farmer.printAnimalsInBarn();
                    farmer.pressEnter();
                    break;
                }
                case 5: {
                    farmer.removeBarn();
                    break;
                }
                case 6: {
                    barn.printAnimals(farmer.get5YoungestAnimals());
                    farmer.pressEnter();
                    break;
                }
                case 7: {
                    barn.printAnimals(farmer.get5OldestAnimals());
                    farmer.pressEnter();
                    break;
                }
                case 8: {
                    Animal.Species mostSpecies = barn.getSpeciesById(farmer.getMostSpecies());
                    barn.printAnimals(farmer.getMostSpeciesList(mostSpecies));
                    break;
                }
                case 9: {
                    barn.printAnimals(farmer.getVaccinatedAnimals());
                    break;
                }
                case 0: {
                    saveToDatabase();
                    printClosingText();
                    stayInMenu = false;
                    break;
                }
            }
        }
    }

    private void printMenu() {
        System.out.print("\nMENU\n" +
                "\n1. Dodaj stodołę" +
                "\n2. Dodaj zwierzę do stodoły" +
                "\n3. Wyświetl wszystkie stodoły" +
                "\n4. Wyświetl zwierzęta w wybranej stodole" +
                "\n5. Usuń stodołę" +
                "\n6. 5 najmłodszych zwierząt" +
                "\n7. 5 najstarszych zwierząt" +
                "\n8. Najliczniejszy gatunek" +
                "\n9. Zwierzęta zaszczepione" +
                "\n0. Zakończ program" +
                "\nWybierz: ");
    }

    private void printOpeningText() {
        System.out.println("\nWitaj w programie do zarządzania farmą!");
    }

    private void printClosingText() {
        System.out.println("\nDo widzenia!");
    }

    private void readFromDatabase() {
        farmer.setBarnList(utils.getBarnListFromFile());
        farmer.setAnimalsToBarns(utils.getAnimalListFromFile());
    }

    private void saveToDatabase() {
        utils.saveBarnListToFile(farmer.getBarnList());
        utils.saveAnimalListToFile(farmer.getAllAnimalsList());
    }
}
