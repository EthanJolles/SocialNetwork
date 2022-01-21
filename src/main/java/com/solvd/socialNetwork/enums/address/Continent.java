package com.solvd.socialNetwork.enums.address;

public enum Continent {
	AUSTRALIA("Australia"), ASIA("Asia"), EUROPE("Europe"), AFRICA("Africa"), NORTH_AMERICA("North America"),
	SOUTH_AMERICA("South America"), ANTARCTICA("Antartica"), UNKNOWN("Unknown");

	private String name;

	Continent(String name) {
		this.setName(name);

	}

	public static Continent valueOfName(final String name) {
		final String enumName = name.toUpperCase().replaceAll(" ", "_");
		try {
			return valueOf(enumName);
		} catch (final IllegalArgumentException e) {
			return Continent.UNKNOWN;
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
