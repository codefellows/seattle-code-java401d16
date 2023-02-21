package com.zork.zorkmaster.model;

import java.util.Date;

public class SuperPet {
    public Long id;
    private String name;
    private SuperPetTypeEnum type;
    private java.util.Date birthDate;
    private Integer height;

    public SuperPet(String name, SuperPetTypeEnum type, Date birthDate, Integer height) {
        this.name = name;
        this.type = type;
        this.birthDate = birthDate;
        this.height = height;
    }


    public Long getId() {
        return id;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SuperPetTypeEnum getType() {
        return type;
    }

    public void setType(SuperPetTypeEnum type) {
        this.type = type;
    }

    public enum SuperPetTypeEnum{
        WATER("Water"),
        ELECTRIC("Electric"),
        ROCK("Rock"),
        FIRE("Fire"),
        GRASS("Grass");

        private final String superPetType;

        SuperPetTypeEnum(String superPetType) {
            this.superPetType = superPetType;
        }

        public static SuperPetTypeEnum fromString(String possibleSuperPetType){
            for (SuperPetTypeEnum type : SuperPetTypeEnum.values()) {
                if (type.superPetType.equals(possibleSuperPetType)){
                    return type;
                }
            }
            return null;
        }

        @Override
        public String toString() {
            return "SuperPetTypeEnum{" +
                    "superPetType='" + superPetType + '\'' +
                    '}';
        }
    }
}
