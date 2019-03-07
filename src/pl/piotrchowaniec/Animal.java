package pl.piotrchowaniec;

public class Animal {
    private Species species;
    private int age;
    private boolean isVaccinated;
    private int barnId;

    public enum Species {
        COW, HORSE, PIG, DUCK, CHICKEN;
    }

    public Animal(Species species, int age, boolean isVaccinated, int barnId) {
        this.species = species;
        this.age = age;
        this.isVaccinated = isVaccinated;
        this.barnId = barnId;
    }

    public Species getSpecies() {
        return species;
    }

    public void setSpecies(Species species) {
        this.species = species;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isVaccinated() {
        return isVaccinated;
    }

    public void setVaccinated(boolean vaccinated) {
        isVaccinated = vaccinated;
    }

    public int getBarnId() {
        return barnId;
    }

    public void setBarnId(int barnId) {
        this.barnId = barnId;
    }

    public String getSpeciesToString(Species species) {
        String speciesString = "";
        switch (species.toString()) {
            case "COW": {
                speciesString = "Krowa";
                break;
            }
            case "HORSE": {
                speciesString = "Koń";
                break;
            }
            case "PIG": {
                speciesString = "Świnia";
                break;
            }
            case "DUCK": {
                speciesString = "Kaczka";
                break;
            }
            case "CHICKEN": {
                speciesString = "Kura";
                break;
            }
        }
        return speciesString;
    }

    public String isVaccinatedString(boolean isVaccinated) {
        if (isVaccinated) {
            return "Tak";
        }
        return "Nie";
    }
}
