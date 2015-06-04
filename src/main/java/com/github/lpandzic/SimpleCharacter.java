package com.github.lpandzic;

public class SimpleCharacter {

    private final String name;

    public SimpleCharacter(String name) {

        this.name = name;
    }

    public String getName() {

        return name;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final SimpleCharacter character = (SimpleCharacter) o;

        return !(name != null ? !name.equals(character.name) : character.name != null);

    }

    @Override
    public int hashCode() {

        return name != null ? name.hashCode() : 0;
    }

	@Override
	public String toString() {

		return "Character{" +
				"name='" + name + '\'' +
				'}';
	}
}
