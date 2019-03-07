package pl.piotrchowaniec;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

class DatabaseUtilities {
    private File animalListFile;
    private File barnListFile;

    DatabaseUtilities() {
        animalListFile = new File("src\\database\\animalListFile.txt");
        barnListFile = new File("src\\database\\barnListFile.txt");
    }

    private void createFolder() {
        File dir = new File("src\\database");
        if (!dir.exists()) {
            dir.mkdir();
        }
    }

    private void createBarnListFile() {
        createFolder();
        if (!barnListFile.exists()) {
            try {
                barnListFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String setBarnListToString(List<Barn> barnList) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < barnList.size(); i++) {
            stringBuilder.append(barnList.get(i).getId()).append(":")
                    .append(barnList.get(i).getName()).append("\r\n");
        }
        return stringBuilder.toString();
    }

    void saveBarnListToFile(List<Barn> barnList) {
        createBarnListFile();
        try {
            Files.write(barnListFile.toPath(), setBarnListToString(barnList).getBytes(), StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    List<Barn> getBarnListFromFile() {
        List<String> listFromFile = readListFromFile(barnListFile);
        List<Barn> barnList = new ArrayList<>();
        for (int i = 0; i < listFromFile.size(); i++) {
            String[] barn = listFromFile.get(i).split(":");
            int id = Integer.parseInt(barn[0]);
            String name = barn[1];
            barnList.add(new Barn(id, name));
        }
        return barnList;
    }

    private void createAnimalListFile() {
        createFolder();
        if (!animalListFile.exists()) {
            try {
                animalListFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String setAnimalListToString(List<Animal> animalList) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < animalList.size(); i++) {
            stringBuilder.append(animalList.get(i).getSpecies()).append(":")
                    .append(animalList.get(i).getAge()).append(":")
                    .append(animalList.get(i).isVaccinated()).append(":")
                    .append(animalList.get(i).getBarnId()).append("\r\n");
        }
        return stringBuilder.toString();
    }

    void saveAnimalListToFile(List<Animal> animalList) {
        createAnimalListFile();
        try {
            Files.write(animalListFile.toPath(), setAnimalListToString(animalList).getBytes(), StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    List<Animal> getAnimalListFromFile() {
        List<String> listFromFile = readListFromFile(animalListFile);
        List<Animal> animalList = new ArrayList<>();
        for (int i = 0; i < listFromFile.size(); i++) {
            String[] animal = listFromFile.get(i).split(":");
            Animal.Species species = Animal.Species.valueOf(animal[0]);
            int age = Integer.parseInt(animal[1]);
            boolean isVaccinated = Boolean.getBoolean(animal[2]);
            int barnId = Integer.parseInt(animal[3]);
            animalList.add(new Animal(species, age, isVaccinated, barnId));
        }
        return animalList;
    }

    private List<String> readListFromFile(File file) {
        List<String> list = new ArrayList<>();
        try {
            list = Files.readAllLines(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}
