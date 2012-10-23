package am.weaver.datasource;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class DataSource {
	
	private final Map<String, Table> tables = new HashMap<String, Table>();
	
	public DataSource() {
		tables.put("Elements", new Table("Elements", elementColumns, elementData));
		tables.put("Atomic Table", new Table("Atomic Table", atomicTableColumns, atomicTableData));
		tables.put("Countries", new Table("Countries", countryColumns, countryData));
	}
	
	public Collection<String> getTableNames() {
		System.out.println("Loading table names: " + tables.keySet());
		return tables.keySet(); 
	}
	
	public Table getTable(String tableName) {
		System.out.println("Loading table '" + tableName + "'");
		System.err.println("Test error: Loading table '" + tableName + "'");
		return tables.get(tableName); 
	}
	
	private static final ColumnDefinition[] elementColumns = new ColumnDefinition[] {
			new ColumnDefinition("Atomic no.", "", ColumnType.Number),
			new ColumnDefinition("Name", "", ColumnType.String),
			new ColumnDefinition("Symbol", "", ColumnType.String),
			new ColumnDefinition("Group",  "", ColumnType.Number),
			new ColumnDefinition("Period", "", ColumnType.Number), 
			new ColumnDefinition("Block", "", ColumnType.String),
			new ColumnDefinition("State at STP", "am.weaver.datasource.enums.State", ColumnType.Enum), 
			new ColumnDefinition("Occurrence", "am.weaver.datasource.enums.Occurrence", ColumnType.Enum),
			new ColumnDefinition("Description", "", ColumnType.String)
	};
	
	private static final Object[] elementData = new Object[] {
		"1", "Hydrogen", "H", "1", "1", "s", "Gas", "Primordial", "Non-metal",
		"2", "Helium", "He", "18", "1", "s", "Gas", "Primordial", "Noble gas",
		"3", "Lithium", "Li", "1", "2", "s", "Solid", "Primordial", "Alkali metal",
		"4", "Beryllium", "Be", "2", "2", "s", "Solid", "Primordial", "Alkaline earth metal",
		"5", "Boron", "B", "13", "2", "p", "Solid", "Primordial", "Metalloid",
		"6", "Carbon", "C", "14", "2", "p", "Solid", "Primordial", "Non-metal",
		"7", "Nitrogen", "N", "15", "2", "p", "Gas", "Primordial", "Non-metal",
		"8", "Oxygen", "O", "16", "2", "p", "Gas", "Primordial", "Non-metal",
		"9", "Fluorine", "F", "17", "2", "p", "Gas", "Primordial", "Halogen",
		"10", "Neon", "Ne", "18", "2", "p", "Gas", "Primordial", "Noble gas",
		"11", "Sodium", "Na", "1", "3", "s", "Solid", "Primordial", "Alkali metal",
		"12", "Magnesium", "Mg", "2", "3", "s", "Solid", "Primordial", "Alkaline earth metal",
		"13", "Aluminium", "Al", "13", "3", "p", "Solid", "Primordial", "Metal",
		"14", "Silicon", "Si", "14", "3", "p", "Solid", "Primordial", "Metalloid",
		"15", "Phosphorus", "P", "15", "3", "p", "Solid", "Primordial", "Non-metal",
		"16", "Sulfur", "S", "16", "3", "p", "Solid", "Primordial", "Non-metal",
		"17", "Chlorine", "Cl", "17", "3", "p", "Gas", "Primordial", "Halogen",
		"18", "Argon", "Ar", "18", "3", "p", "Gas", "Primordial", "Noble gas",
		"19", "Potassium", "K", "1", "4", "s", "Solid", "Primordial", "Alkali metal",
		"20", "Calcium", "Ca", "2", "4", "s", "Solid", "Primordial", "Alkaline earth metal",
		"21", "Scandium", "Sc", "3", "4", "d", "Solid", "Primordial", "Transition metal",
		"22", "Titanium", "Ti", "4", "4", "d", "Solid", "Primordial", "Transition metal",
		"23", "Vanadium", "V", "5", "4", "d", "Solid", "Primordial", "Transition metal",
		"24", "Chromium", "Cr", "6", "4", "d", "Solid", "Primordial", "Transition metal",
		"25", "Manganese", "Mn", "7", "4", "d", "Solid", "Primordial", "Transition metal",
		"26", "Iron", "Fe", "8", "4", "d", "Solid", "Primordial", "Transition metal",
		"27", "Cobalt", "Co", "9", "4", "d", "Solid", "Primordial", "Transition metal",
		"28", "Nickel", "Ni", "10", "4", "d", "Solid", "Primordial", "Transition metal",
		"29", "Copper", "Cu", "11", "4", "d", "Solid", "Primordial", "Transition metal",
		"30", "Zinc", "Zn", "12", "4", "d", "Solid", "Primordial", "Transition metal",
		"31", "Gallium", "Ga", "13", "4", "p", "Solid", "Primordial", "Metal",
		"32", "Germanium", "Ge", "14", "4", "p", "Solid", "Primordial", "Metalloid",
		"33", "Arsenic", "As", "15", "4", "p", "Solid", "Primordial", "Metalloid",
		"34", "Selenium", "Se", "16", "4", "p", "Solid", "Primordial", "Non-metal",
		"35", "Bromine", "Br", "17", "4", "p", "Liquid", "Primordial", "Halogen",
		"36", "Krypton", "Kr", "18", "4", "p", "Gas", "Primordial", "Noble gas",
		"37", "Rubidium", "Rb", "1", "5", "s", "Solid", "Primordial", "Alkali metal",
		"38", "Strontium", "Sr", "2", "5", "s", "Solid", "Primordial", "Alkaline earth metal",
		"39", "Yttrium", "Y", "3", "5", "d", "Solid", "Primordial", "Transition metal",
		"40", "Zirconium", "Zr", "4", "5", "d", "Solid", "Primordial", "Transition metal",
		"41", "Niobium", "Nb", "5", "5", "d", "Solid", "Primordial", "Transition metal",
		"42", "Molybdenum", "Mo", "6", "5", "d", "Solid", "Primordial", "Transition metal",
		"43", "Technetium", "Tc", "7", "5", "d", "Solid", "Transient", "Transition metal",
		"44", "Ruthenium", "Ru", "8", "5", "d", "Solid", "Primordial", "Transition metal",
		"45", "Rhodium", "Rh", "9", "5", "d", "Solid", "Primordial", "Transition metal",
		"46", "Palladium", "Pd", "10", "5", "d", "Solid", "Primordial", "Transition metal",
		"47", "Silver", "Ag", "11", "5", "d", "Solid", "Primordial", "Transition metal",
		"48", "Cadmium", "Cd", "12", "5", "d", "Solid", "Primordial", "Transition metal",
		"49", "Indium", "In", "13", "5", "p", "Solid", "Primordial", "Metal",
		"50", "Tin", "Sn", "14", "5", "p", "Solid", "Primordial", "Metal",
		"51", "Antimony", "Sb", "15", "5", "p", "Solid", "Primordial", "Metalloid",
		"52", "Tellurium", "Te", "16", "5", "p", "Solid", "Primordial", "Metalloid",
		"53", "Iodine", "I", "17", "5", "p", "Solid", "Primordial", "Halogen",
		"54", "Xenon", "Xe", "18", "5", "p", "Gas", "Primordial", "Noble gas",
		"55", "Caesium", "Cs", "1", "6", "s", "Solid", "Primordial", "Alkali metal",
		"56", "Barium", "Ba", "2", "6", "s", "Solid", "Primordial", "Alkaline earth metal",
		"57", "Lanthanum", "La", "3", "6", "f", "Solid", "Primordial", "Lanthanide",
		"58", "Cerium", "Ce", "3", "6", "f", "Solid", "Primordial", "Lanthanide",
		"59", "Praseodymium", "Pr", "3", "6", "f", "Solid", "Primordial", "Lanthanide",
		"60", "Neodymium", "Nd", "3", "6", "f", "Solid", "Primordial", "Lanthanide",
		"61", "Promethium", "Pm", "3", "6", "f", "Solid", "Transient", "Lanthanide",
		"62", "Samarium", "Sm", "3", "6", "f", "Solid", "Primordial", "Lanthanide",
		"63", "Europium", "Eu", "3", "6", "f", "Solid", "Primordial", "Lanthanide",
		"64", "Gadolinium", "Gd", "3", "6", "f", "Solid", "Primordial", "Lanthanide",
		"65", "Terbium", "Tb", "3", "6", "f", "Solid", "Primordial", "Lanthanide",
		"66", "Dysprosium", "Dy", "3", "6", "f", "Solid", "Primordial", "Lanthanide",
		"67", "Holmium", "Ho", "3", "6", "f", "Solid", "Primordial", "Lanthanide",
		"68", "Erbium", "Er", "3", "6", "f", "Solid", "Primordial", "Lanthanide",
		"69", "Thulium", "Tm", "3", "6", "f", "Solid", "Primordial", "Lanthanide",
		"70", "Ytterbium", "Yb", "3", "6", "f", "Solid", "Primordial", "Lanthanide",
		"71", "Lutetium", "Lu", "3", "6", "d", "Solid", "Primordial", "Lanthanide",
		"72", "Hafnium", "Hf", "4", "6", "d", "Solid", "Primordial", "Transition metal",
		"73", "Tantalum", "Ta", "5", "6", "d", "Solid", "Primordial", "Transition metal",
		"74", "Tungsten", "W", "6", "6", "d", "Solid", "Primordial", "Transition metal",
		"75", "Rhenium", "Re", "7", "6", "d", "Solid", "Primordial", "Transition metal",
		"76", "Osmium", "Os", "8", "6", "d", "Solid", "Primordial", "Transition metal",
		"77", "Iridium", "Ir", "9", "6", "d", "Solid", "Primordial", "Transition metal",
		"78", "Platinum", "Pt", "10", "6", "d", "Solid", "Primordial", "Transition metal",
		"79", "Gold", "Au", "11", "6", "d", "Solid", "Primordial", "Transition metal",
		"80", "Mercury", "Hg", "12", "6", "d", "Liquid", "Primordial", "Transition metal",
		"81", "Thallium", "Tl", "13", "6", "p", "Solid", "Primordial", "Metal",
		"82", "Lead", "Pb", "14", "6", "p", "Solid", "Primordial", "Metal",
		"83", "Bismuth", "Bi", "15", "6", "p", "Solid", "Primordial", "Metal",
		"84", "Polonium", "Po", "16", "6", "p", "Solid", "Transient", "Metalloid",
		"85", "Astatine", "At", "17", "6", "p", "Solid", "Transient", "Halogen",
		"86", "Radon", "Rn", "18", "6", "p", "Gas", "Transient", "Noble gas",
		"87", "Francium", "Fr", "1", "7", "s", "Solid", "Transient", "Alkali metal",
		"88", "Radium", "Ra", "2", "7", "s", "Solid", "Transient", "Alkaline earth metal",
		"89", "Actinium", "Ac", "3", "7", "f", "Solid", "Transient", "Actinide",
		"90", "Thorium", "Th", "3", "7", "f", "Solid", "Primordial", "Actinide",
		"91", "Protactinium", "Pa", "3", "7", "f", "Solid", "Transient", "Actinide",
		"92", "Uranium", "U", "3", "7", "f", "Solid", "Primordial", "Actinide",
		"93", "Neptunium", "Np", "3", "7", "f", "Solid", "Transient", "Actinide",
		"94", "Plutonium", "Pu", "3", "7", "f", "Solid", "Primordial", "Actinide",
		"95", "Americium", "Am", "3", "7", "f", "Solid", "Transient", "Actinide",
		"96", "Curium", "Cm", "3", "7", "f", "Solid", "Transient", "Actinide",
		"97", "Berkelium", "Bk", "3", "7", "f", "Solid", "Transient", "Actinide",
		"98", "Californium", "Cf", "3", "7", "f", "Solid", "Transient", "Actinide",
		"99", "Einsteinium", "Es", "3", "7", "f", "Solid", "Synthetic", "Actinide",
		"100", "Fermium", "Fm", "3", "7", "f", "Solid", "Synthetic", "Actinide",
		"101", "Mendelevium", "Md", "3", "7", "f", "Solid", "Synthetic", "Actinide",
		"102", "Nobelium", "No", "3", "7", "f", "Solid", "Synthetic", "Actinide",
		"103", "Lawrencium", "Lr", "3", "7", "d", "Solid", "Synthetic", "Actinide",
	};
	
	private static final ColumnDefinition[] atomicTableColumns = new ColumnDefinition[] {
			new ColumnDefinition("Period"), new ColumnDefinition("1"), new ColumnDefinition("2"), new ColumnDefinition("3"), new ColumnDefinition("4"), 
			new ColumnDefinition("5"), new ColumnDefinition("6"), new ColumnDefinition("7"), new ColumnDefinition("8"), new ColumnDefinition("9"), 
			new ColumnDefinition("10"), new ColumnDefinition("11"), new ColumnDefinition("12"), new ColumnDefinition("13"), new ColumnDefinition("14"),
			new ColumnDefinition("15"), new ColumnDefinition("16"), new ColumnDefinition("17"), new ColumnDefinition("18")
	};
	
	private static final Object[] atomicTableData = new Object[] {
			"1",           "H",  "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  ", "He",
			"2",           "Li", "Be", "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  ", "B",  "C",  "N",  "O",  "F",  "Ne",
			"3",           "Na", "Mg", "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  ", "  ", "Al", "Si", "P",  "S",  "Cl", "Ar",
			"4",           "K",  "Ca", "Sc", "Ti", "V",  "Cr", "Mn", "Fe", "Co", "Ni", "Cu", "Zn", "Ga", "Ge", "As", "Se", "Br", "Kr",
			"5",           "Rb", "Sr", "Y",  "Zr", "Nb", "Mo", "Tc", "Ru", "Rh", "Pd", "Ag", "Cd", "In", "Sn", "Sb", "Te", "I",  "Xe",
			"6",           "Cs", "Ba", "  ", "Hf", "Ta", "W",  "Re", "Os", "Ir", "Pt", "Au", "Hg", "Tl", "Pb", "Bi", "Po", "At", "Rn",
			"7",           "Fr", "Ra", "  ", "Rf", "Db", "Sg", "Bh", "Hs", "Mt", "Ds", "Rg", "Cn", "Uut", "Fl", "Uup", "Lv", "Uus", "Uuo",
			"Lanthanides", "  ", "  ", "La", "Ce", "Pr", "Nd", "Pm", "Sm", "Eu", "Gd", "Tb", "Dy", "Ho", "Er", "Tm", "Yb", "Lu", "  ",
			"Actinides",   "  ", "  ", "Ac", "Th", "Pa", "U",  "Np", "Pu", "Am", "Cm", "Bk", "Cf", "Es", "Fm", "Md", "No", "Lr", "  ",
	};
	
	private static final ColumnDefinition[] countryColumns = new ColumnDefinition[] {
		new ColumnDefinition("Name", "", ColumnType.String),
		new ColumnDefinition("Area", "", ColumnType.Number),
		new ColumnDefinition("Population", "", ColumnType.Number),
		new ColumnDefinition("Pop Density", "", ColumnType.Number),
		new ColumnDefinition("Capital", "", ColumnType.String)
	};
	
	private static final Object[] countryData = new Object[] {
		"Albania", "28,748", "2,831,741", "98.5", "Tirana",
		"Andorra", "468", "68,403", "146.2", "Andorra la Vella",
		"Armenia", "29,800", "3,229,900", "101", "Yerevan",
		"Austria", "83,858", "8,169,929", "97.4", "Vienna",
		"Azerbaijan", "86,600", "9,165,000", "105.8", "Baku",
		"Belarus", "207,560", "9,458,000", "45.6", "Minsk",
		"Belgium", "30,528", "11,007,000", "360.6", "Brussels",
		"Bosnia and Herzegovina", "51,129", "3,843,126", "75.2", "Sarajevo",
		"Bulgaria", "110,910", "7,621,337", "68.7", "Sofia",
		"Croatia", "56,542", "4,437,460", "77.7", "Zagreb",
		"Cyprus", "9,251", "788,457", "85", "Nicosia",
		"Czech Republic", "78,866", "10,256,760", "130.1", "Prague",
		"Denmark", "43,094", "5,564,219", "129", "Copenhagen",
		"Estonia", "45,226", "1,340,194", "29", "Tallinn",
		"Finland", "336,593", "5,157,537", "15.3", "Helsinki",
		"France", "547,030", "63,182,000", "115.5", "Paris",
		"Georgia", "69,700", "4,661,473", "64", "Tbilisi",
		"Germany", "357,021", "83,251,851", "233.2", "Berlin",
		"Greece", "131,940", "10,645,343", "80.7", "Athens",
		"Hungary", "93,030", "10,075,034", "108.3", "Budapest",
		"Iceland", "103,000", "307,261", "2.7", "Reykjavk",
		"Ireland", "70,280", "4,234,925", "60.3", "Dublin",
		"Italy", "301,230", "58,751,711", "191.6", "Rome",
		"Kazakhstan", "2,724,900", "15,217,711", "5.6", "Astana",
		"Latvia", "64,589", "2,067,900", "34.2", "Riga",
		"Liechtenstein", "160", "32,842", "205.3", "Vaduz",
		"Lithuania", "65,200", "3,195,702", "50.3", "Vilnius",
		"Luxembourg", "2,586", "448,569", "173.5", "Luxembourg",
		"Republic of Macedonia", "25,713", "2,054,800", "81.1", "Skopje",
		"Malta", "316", "397,499", "1,257.90", "Valletta",
		"Moldova", "33,843", "4,434,547", "131", "Chisinau",
		"Monaco", "1.95", "31,987", "16,403.60", "Monaco",
		"Montenegro", "13,812", "616,258", "44.6", "Podgorica",
		"Netherlands", "41,526", "16,318,199", "393", "Amsterdam",
		"Norway", "324,220", "5,018,836", "15.5", "Oslo",
		"Poland", "312,685", "38,625,478", "123.5", "Warsaw",
		"Portugal", "91,568", "10,409,995", "110.1", "Lisbon",
		"Romania", "238,391", "21,698,181", "91", "Bucharest",
		"Russia", "17,075,400", "142,200,000", "8.3", "Moscow",
		"San Marino", "61", "27,730", "454.6", "San Marino",
		"Serbia", "88,361", "7,120,666", "91.9", "Belgrade",
		"Slovakia", "48,845", "5,422,366", "111", "Bratislava",
		"Slovenia", "20,273", "2,050,189", "101", "Ljubljana",
		"Spain", "504,851", "45,061,274", "89.3", "Madrid",
		"Sweden", "449,964", "9,090,113", "19.7", "Stockholm",
		"Switzerland", "41,290", "7,507,000", "176.8", "Bern",
		"Turkey", "783,562", "74,724,269", "97", "Ankara",
		"Ukraine", "603,700", "48,396,470", "80.2", "Kiev",
		"United Kingdom", "244,820", "61,100,835", "244.2", "London",
		"Vatican City", "0.44", "900", "2,045.50", "Vatican City"
	};
}


