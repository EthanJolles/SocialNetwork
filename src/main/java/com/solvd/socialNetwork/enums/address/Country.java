package com.solvd.socialNetwork.enums.address;

public enum Country {

	CHINA("China"), INDIA("India"), UNITED_STATES("United States"), INDONESIA("Indonesia"), PAKISTAN("Pakistan"),
	BRAZIL("Brazil"), NIGERIA("Nigeria"), BANGLADESH("Bangladesh"), RUSSIA("Russia"), MEXICO("Mexico"), JAPAN("Japan"),
	ETHIOPIA("Ethiopia"), PHILIPPINES("Philippines"), EGYPT("Egypt"), VIETNAM("Vietnam"), DR_CONGO("DR Congo"),
	TURKEY("Turkey"), IRAN("Iran"), GERMANY("Germany"), THAILAND("Thailand"), UNITED_KINGDOM("United Kingdom"),
	FRANCE("France"), ITALY("Italy"), TANZANIA("Tanzania"), SOUTH_AFRICA("South Africa"), MYANMAR("Myanmar"),
	KENYA("Kenya"), SOUTH_KOREA("South Korea"), COLOMBIA("Colombia"), SPAIN("Spain"), UGANDA("Uganda"),
	ARGENTINA("Argentina"), ALGERIA("Algeria"), SUDAN("Sudan"), UKRAINE("Ukraine"), IRAQ("Iraq"),
	AFGHANISTAN("Afghanistan"), POLAND("Poland"), CANADA("Canada"), MOROCCO("Morocco"), SAUDI_ARABIA("Saudi Arabia"),
	UZBEKISTAN("Uzbekistan"), PERU("Peru"), ANGOLA("Angola"), MALAYSIA("Malaysia"), MOZAMBIQUE("Mozambique"),
	GHANA("Ghana"), YEMEN("Yemen"), NEPAL("Nepal"), VENEZUELA("Venezuela"), MADAGASCAR("Madagascar"),
	CAMEROON("Cameroon"), COTE_D_IVOIRE("C�te d'Ivoire"), NORTH_KOREA("North Korea"), AUSTRALIA("Australia"),
	NIGER("Niger"), SRI_LANKA("Sri Lanka"), BURKINA_FASO("Burkina Faso"), MALI("Mali"), ROMANIA("Romania"),
	MALAWI("Malawi"), CHILE("Chile"), KAZAKHSTAN("Kazakhstan"), ZAMBIA("Zambia"), GUATEMALA("Guatemala"),
	ECUADOR("Ecuador"), SYRIA("Syria"), NETHERLANDS("Netherlands"), SENEGAL("Senegal"), CAMBODIA("Cambodia"),
	CHAD("Chad"), SOMALIA("Somalia"), ZIMBABWE("Zimbabwe"), GUINEA("Guinea"), RWANDA("Rwanda"), BENIN("Benin"),
	BURUNDI("Burundi"), TUNISIA("Tunisia"), BOLIVIA("Bolivia"), BELGIUM("Belgium"), HAITI("Haiti"), CUBA("Cuba"),
	SOUTH_SUDAN("South Sudan"), DOMINICAN_REPUBLIC("Dominican Republic"), CZECH_REPUBLIC("Czech Republic"),
	GREECE("Greece"), JORDAN("Jordan"), PORTUGAL("Portugal"), AZERBAIJAN("Azerbaijan"), SWEDEN("Sweden"),
	HONDURAS("Honduras"), UNITED_ARAB_EMIRATES("United Arab Emirates"), HUNGARY("Hungary"), TAJIKISTAN("Tajikistan"),
	BELARUS("Belarus"), AUSTRIA("Austria"), PAPUA_NEW_GUINEA("Papua New Guinea"), SERBIA("Serbia"), ISRAEL("Israel"),
	SWITZERLAND("Switzerland"), TOGO("Togo"), SIERRA_LEONE("Sierra Leone"), LAOS("Laos"), PARAGUAY("Paraguay"),
	BULGARIA("Bulgaria"), LIBYA("Libya"), LEBANON("Lebanon"), NICARAGUA("Nicaragua"), KYRGYZSTAN("Kyrgyzstan"),
	EL_SALVADOR("El Salvador"), TURKMENISTAN("Turkmenistan"), SINGAPORE("Singapore"), DENMARK("Denmark"),
	FINLAND("Finland"), CONGO("Congo"), SLOVAKIA("Slovakia"), NORWAY("Norway"), OMAN("Oman"),
	STATE_OF_PALESTINE("State of Palestine"), COSTA_RICA("Costa Rica"), LIBERIA("Liberia"), IRELAND("Ireland"),
	CENTRAL_AFRICAN_REPUBLIC("Central African Republic"), NEW_ZEALAND("New Zealand"), MAURITANIA("Mauritania"),
	PANAMA("Panama"), KUWAIT("Kuwait"), CROATIA("Croatia"), MOLDOVA("Moldova"), GEORGIA("Georgia"), ERITREA("Eritrea"),
	URUGUAY("Uruguay"), BOSNIA_AND_HERZEGOVINA("Bosnia and Herzegovina"), MONGOLIA("Mongolia"), ARMENIA("Armenia"),
	JAMAICA("Jamaica"), QATAR("Qatar "), ALBANIA("Albania"), LITHUANIA("Lithuania"), NAMIBIA("Namibia"),
	GAMBIA("Gambia"), BOTSWANA("Botswana"), GABON("Gabon"), LESOTHO("Lesotho"), NORTH_MACEDONIA("North Macedonia"),
	SLOVENIA("Slovenia"), GUINEA_BISSAU("Guinea-Bissau"), LATVIA("Latvia"), BAHRAIN("Bahrain"),
	EQUATORIAL_GUI("Equatorial Gui  "), TRINIDAD_AND_TOBAGO("Trinidad and Tobago"), ESTONIA("Estonia"),
	TIMOR_LESTE("Timor-Leste"), MAURITIUS("Mauritius"), CYPRUS("Cyprus"), ESWATINI("Eswatini"), DJIBOUTI("Djibouti"),
	FIJI("Fiji"), COMOROS("Comoros"), GUYANA("Guyana"), BHUTAN("Bhutan"), SOLOMON_ISLANDS("Solomon Islands"),
	MONTENEGRO("Montenegro"), LUXEMBOUR("Luxembour"), SURINAME("Suriname"), CABO_VERD("Cabo Verd "),
	MICRONESI("Micronesi"), MALDIVES("Maldives"), MALTA("Malta	"), BRUNEI("Brunei"), BELIZE("Belize"),
	BAHAMAS("Bahamas"), ICELAND("Iceland"), VANUATU("Vanuatu"), BARBADOS("Barbados"),
	SAO_TOME_AND_PRINCIPE("Sao Tome & Principe"), SAMOA("Samoa"), SAINT_LUCIA("Saint Lucia"), KIRIBATI("Kiribati"),
	GRENADA("Grenada"), ST_VINCENT_AND_GRENADINES("St. Vincent & Grenadines"), TONGA("Tonga"), SEYCHELLES("Seychelles"),
	ANTIGUA_AND_BARBUDA("Antigua and Barbuda"), ANDORRA("Andorra"), DOMINICA("Dominica"),
	MARSHALL_ISLANDS("Marshall Islands"), SAINT_KITTS_AND_NEVIS("Saint Kitts & Nevis"), MONACO("Monaco"),
	LIECHTENSTEIN("Liechtenstein"), SAN_MARINO("San Marino"), PALAU("Palau"), TUVALU("Tuvalu"), NAURU("Nauru"),
	UNKNOWN("Unknown");

	private String name;

	Country(String name) {
		this.setName(name);
	}

	public static Country valueOfName(final String name) {
		final String enumName = name.toUpperCase().replaceAll(" ", "_");
		try {
			return valueOf(enumName);
		} catch (final IllegalArgumentException e) {
			return Country.UNKNOWN;
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
