public class CommandProcessor {

    CommandProcessor(){};
    
    public void process (String[] tokens, Database db){

        String command = tokens[0].toLowerCase();

        if(command.equals("select")){

            //Handle SELECT command

            //Search for FROM index
            int fromIndex = -1;
            for (int i = 0; i<tokens.length;i++){
                if (tokens[i].equalsIgnoreCase("from")){
                    fromIndex = i;
                    break;
                }
            }
            if (fromIndex == -1){
                System.out.println("Invalid command : FROM missing");
                return;
            }

            //Get the table from the db
            String tableName = tokens[fromIndex +1];
            Table table = db.getTableByName(tableName);
            if (table == null){
                System.out.println("Unknown table : " + tableName);
                return;
            }

            //Search and handle columns for the select request
            int startCol = 1;
            int endCol = fromIndex - 1 ;
            String[] columns;
            if(tokens[startCol].equals("*")){
                columns = new String[table.tab[0].length];
                for (int i = 0;i<table.tab[0].length;i++){
                    columns[i] = table.tab[0][i];
                }
            }else{
                columns = new String[endCol - startCol +1 ];
            for (int i = startCol;i<=endCol;i++){
                columns[i - startCol] = tokens[i];
            }

            }
            
            //Make columns table an int table for the projection operation
            int[] columnIndices = new int[columns.length];
            for (int i = 0; i < columns.length; i++) {
                int index = table.getColumnIndex(columns[i]);
                
                if (index == -1) {
                    System.out.println("Unknown column : " + columns[i] );
                    return;
                }
                
                columnIndices[i] = index;
            }

            //Make the projection 
            String[][] result = db.projection(columnIndices, table);

            //Search INTO index if existing

            int intoIndex = -1;
            for (int i = 0; i<tokens.length;i++){
                if (tokens[i].equalsIgnoreCase("into")){
                    intoIndex = i;
                    break;
                }
            }
            if(intoIndex+1>=tokens.length){
                intoIndex = -1;
                System.out.println("Missing INTO table name, the result will only be displayed.");                
            }
            if (intoIndex == -1){
                //Display the result if INTO not present in the request
                if (result == null){
                    System.out.println("Error : Operation Failed");
                    return;
                }
                System.out.println("Table : " + table.name);
                for (int i = 0; i < result.length; i++) {
                    for (int j = 0; j < result[0].length; j++) {
                        System.out.print( " | " + result[i][j]);
                    }
                    System.out.println();
                }
                return;
            }else{
                //Add new tab in db with the result of the request
                Table resultTable = new Table(result, tokens[intoIndex +1 ]);
                db.addTable(resultTable);
                System.out.println("Table : " + resultTable.name);
                System.out.println(resultTable.toString());
                return;
            }

            




        }else if (command.equals("create") && tokens[1].equalsIgnoreCase("table")){

            //Handle CREATE TABLE command 
            
            //Search for CONTENT index
            int contentIndex = -1;
            for (int i = 0; i<tokens.length;i++){
                if (tokens[i].equalsIgnoreCase("content")){
                    contentIndex = i;
                    break;
                }
            }
            if (contentIndex == -1){
                System.out.println("Invalid command : keyword CONTENT missing");
                return;
            }else if(contentIndex == 2){
                System.out.println("Error : Missing table name");
                return;
            }else if(contentIndex == tokens.length-1){
                System.out.println("Error : Missing table columns names");
                return;
            }

            String tableName = tokens[2];

            int nbColumns = tokens.length - contentIndex - 1 ;

            String[] columns = new String[nbColumns];
            for (int i=contentIndex+1;i<tokens.length;i++){
                columns[i-contentIndex-1] = tokens[i];
            }

            Table nt = new Table(columns, tableName);
            boolean success = db.addTable(nt);
            
            if(success){
                System.out.println("Table : " + nt.name);
                System.out.println(nt.toString());
            }
            return;

        }else{
            System.out.println("Unknown command : " + command);
        }

    }

}
