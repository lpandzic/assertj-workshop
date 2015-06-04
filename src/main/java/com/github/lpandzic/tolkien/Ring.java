package com.github.lpandzic.tolkien;

@Magical
public class Ring {

    private final String name;

    public Ring(String name) {

        this.name = name;
    }

    @Override
    public int hashCode() {

        return name != null ? name.hashCode() : 0;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final Ring ring = (Ring) o;

        return !(name != null ? !name.equals(ring.name) : ring.name != null);

    }

    @Override
    public String toString() {

        return "Ring{" +
                "name='" + name + '\'' +
                '}';
    }
}
