package life.timber.animalcounter;

import org.bukkit.entity.EntityType;

public enum AnimalSpecies {

    COW("Kuh", EntityType.COW),
    CHICKEN("Huhn", EntityType.CHICKEN);

    private EntityType entityType;
    private String name;

    AnimalSpecies(String name, EntityType entityType) {
        this.name = name;
        this.entityType = entityType;
    }

    public EntityType getEntityType() {
        return entityType;
    }

    public String toString() {
        return name;
    }
}
