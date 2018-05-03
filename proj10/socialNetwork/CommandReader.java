package socialNetwork;

import java.io.IOException;
import java.util.ArrayList;

public class CommandReader{
	private class Command{
		//Stores the actual command
		private String command; 
		private  int maxNumUsers;

		//Stores the user names 
		//Is an arraylist for the add friend command so for the sake of practicality
		private ArrayList<String> users; 

		private Command (String commandIn, ArrayList<String> userIn,
				int maxNumUsers) {
			command = new String (commandIn);
			users = new ArrayList<String>();
			users.addAll(userIn);
			this.maxNumUsers = maxNumUsers;
		}
		
		public String toString() {
			String msg = new String();
			
			msg += command + "\n";
			
			for (String name: users) {
				msg += name + "\t";
			}
			
			msg += "\n";
			
			return msg;
		}
	}

	private ArrayList<Command> commandList;

	public CommandReader() {
		commandList= new ArrayList<Command>();
	}
	
	//Reads the inputs from the Reader class and loads them into Command Objects
	public void readCommands () throws IOException {
		Reader read = new Reader();
		ArrayList<String> inputs = read.getInputs();

		for (String msg: inputs) {
			if (msg.contains("addperson")) {
				Command c = new Command("addPerson",getNames(msg,1),1);
				commandList.add(c);
			}else if (msg.contains("addfriend")) {
				Command c = new Command("addFriend",getNames(msg,2),2);
				commandList.add(c);
			}
		}
	}
	
	
	//returns the list of the users' names
	private ArrayList<String> getNames(String in, int max){
		ArrayList<String> names = new ArrayList<String>();
		int size = in.length();
		int count = 0;
		boolean foundName = false;
		String name = new  String();
		
		for (int i = 0; i < size; i ++) {
			char c = in.charAt(i);
			
			if (count <=max) {
				if (c == ' ') { // Trigger for foundName
					if (!foundName) {
						foundName = true;
					}else  if (count >= 0){
						names.add(name);
						name = new String();
						count ++;
					}
				}else if (foundName) {
					name += c;
				}
			}
		}
		return names;
	}

	public ArrayList<Command> getCommands() {
		return commandList;
	}

	public static void main(String[] args) {
		CommandReader read = new CommandReader();

		try {
			read.readCommands();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
