package socialNetwork;

import java.io.IOException;
import java.util.ArrayList;

public class CommandReader{
	private class Command{
		//Stores the actual command
		private String command; 
		
		//Stores the user names 
		//Is an arraylist for the add friend command so for the sake of practicality
		private ArrayList<String> users; 
		
		private Command (String commandIn, ArrayList<String> userIn) {
			command = new String (commandIn);
			users = new ArrayList<String>();
			users.addAll(userIn);
		}
	}
	
	private ArrayList<Command> commandList;
	
	public CommandReader() {
		commandList= new ArrayList<Command>();
	}
	
	public void processCommands () throws IOException {
		Reader read = new Reader();
		ArrayList<String> inputs = read.getInputs();
		
		for (String msg: inputs) {
			if (msg.contains("addperson")) {
				System.out.println("I found someone");
			}else if (msg.contains("addfriend")) {
				System.out.println("Someone found a friend");
			}
		}
	}
	
	private ArrayList<String> getNames(String in){
		int size = in.length();
		
		for (int i = 0; i < size; i ++) {
			char c = in.charAt(i);
			if (c == ' ' && i > 0) {
			}
		}
	}
	
	public ArrayList<Command> getCommands() {
		return commandList;
	}

	public static void main(String[] args) {
		CommandReader read = new CommandReader();
		
		try {
			read.processCommands();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
