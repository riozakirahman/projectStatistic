package com.example.projectstatistic.model;

public class PersonRank implements Comparable<PersonRank>{

    private final String name;
    private final int mean;

    public PersonRank(String name, int mean) {
        this.name = name;
        this.mean = mean;
    }

    public String name() {
        return name;
    }

    public int mean() {
        return mean;
    }


    public boolean equals(Object o) {
        if (!(o instanceof PersonRank))
            return false;
        PersonRank person = (PersonRank) o;
        return person.name.equals(name) && person.mean == mean;
    }
    public String toString(){
        return "Name: " + name +", mean = " + mean ;
    }

    @Override
    public int compareTo(PersonRank personRank) {
        return mean > personRank.mean ? 1 : mean < personRank.mean ? -1 : 0;
    }
}
