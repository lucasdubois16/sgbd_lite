public class Database {
    Table[] tables;

    //Create a database from zero
    Database(){};

    //Create a database from existing tables
    Database(Table[] base_tables){
        this.tables = base_tables;
    }

    public Table getTableByName(String tableName){
        for (int i=0;i<this.tables.length;i++){
            if(tableName.equals(this.tables[i].name)){
                return this.tables[i];
            }
        }
        return null;
    }

    public boolean addTable(Table table){
        Table[] newTab = new Table[this.tables.length + 1 ];
        for (int i=0;i<this.tables.length;i++){
            newTab[i] = this.tables[i];
            if (this.tables[i].name.equals(table.name)){
                System.out.println("Error : Table " + table.name + " already exists");
                return false;
            }
        }
        newTab[newTab.length-1] = table;
        this.tables = newTab;
        return true;
    }

    public String[][] projection(int[] columns, Table table){
        String[][] output = new String[table.tab.length][columns.length];
        for (int i = 0; i<table.tab.length; i++){
            for (int j = 0; j<columns.length; j++){
                output[i][j] = table.tab[i][columns[j]];
            }
        }
        return output;
    }
}
