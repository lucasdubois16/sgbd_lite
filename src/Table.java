public class Table {
    String[][] tab;
    String name;


    //Create a Table from scratch
    Table(String[] columns, String name){
        this.tab = new String[1][columns.length];
        for(int i = 0; i<columns.length;i++){
            this.tab[0][i] = columns[i];
        }
        this.name = name;
    };

    //Create a table from a String tab
    Table(String[][] data, String name){
        this.tab = new String[data.length][data[0].length];
        for(int i = 0;i<data.length;i++){
            for(int j=0;j<data[0].length;j++){
                this.tab[i][j] = data[i][j];
            }
        }
        this.name = name;
    }


    public void addRow(String[] values){
        String[][] newTable = new String[this.tab.length + 1][this.tab[0].length];
        for (int i = 0; i<this.tab.length;i++){
            for(int j =0;j<this.tab[0].length;j++){
                newTable[i][j] = this.tab[i][j];
            }
        }
        for(int i = 0; i<newTable[0].length;i++){
            newTable[newTable.length-1][i] = values[i];
        }
        this.tab = newTable;
    }

    public String toString(){
        String output = "";
        for (int i = 0; i<this.tab.length;i++){
            for(int j =0;j<this.tab[0].length;j++){
                output += " | " + this.tab[i][j] ;
            }
            output +="\n";
        }
        return output;
    }

    public int getColumnIndex(String columnName){
        int index = -1;
        for (int i=0;i<this.tab[0].length;i++){
            if(columnName.equals(this.tab[0][i])){
                index = i;
            }
        }
        return index;
    }
}
