import java.util.Scanner; 

public class Main{
    public static void main(String[] args) {

        String[] columns = new String[]{"id","name","number"};
        Table table1 = new Table(columns, "drivers");
        String[] values = new String[]{"1","Verstappen","3"};
        table1.addRow(values);
        String[] values2 = new String[]{"2","Hamilton","44"};
        table1.addRow(values2);
        String[] values3 = new String[]{"3","Leclerc","16"};
        table1.addRow(values3);
        String[] values4 = new String[]{"4","Hadjar","6"};
        table1.addRow(values4);
        String[] values5 = new String[]{"5","Piastri","81"};
        table1.addRow(values5);
        String[] values6 = new String[]{"6","Bottas","77"};
        table1.addRow(values6);

        Table[] tablesdb1 = new Table[]{table1};
        Database db1 = new Database(tablesdb1);

        System.out.println("Table : " + table1.name + "\n" + table1.toString());
        
        Scanner scanner = new Scanner(System.in);
        CommandProcessor cp = new CommandProcessor();
        FileHandler fh = new FileHandler();

        System.out.println("Enter intruction to run. Type \"help\" to got help, type \"quit\" or \"exit\" to quit.");
        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine();
            
            if (input.isEmpty()) continue;

            input = input.trim();
            String[] tokens = input.split("\\s+");

            if (tokens[0].equalsIgnoreCase("quit") || tokens[0].equalsIgnoreCase("exit")){
                System.out.println("Thanks for using.");
                break;
            }else if(tokens[0].equalsIgnoreCase("help")){
                if (tokens.length == 1){
                    System.out.println("Available commands : SELECT, CREATE TABLE. Type HELP <command> to get details of a command.");
                }else if (tokens[1].equalsIgnoreCase("select")){
                    fh.clearScreen();
                    fh.readFile("../res/txt/HelpSelect.txt");
                }else {
                    System.out.println("Command not found : " + tokens[1]);
                }
                
            }else {
                cp.process(tokens, db1);
            }
        }
    }
}
