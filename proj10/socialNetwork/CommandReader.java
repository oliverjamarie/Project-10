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
				/*Command c = new Command("addPerson",getNames(msg,1),1);
				commandList.add(c);*/
			}else if (msg.contains("addfriend")) {
				Command c = new Command("addFriend",getNames(msg,2),2);
				commandList.add(c);
			}
		}
	}

	private ArrayList<String> getNames(String in, int max){
		ArrayList<String> names = new ArrayList<String>();
		int size = in.length();
		int count = 0;
		boolean foundName = false;
		String name = new  String();

		for (int i = 0; i < size; i ++) {
			if (count <=max) {
				char c = in.charAt(i);

				if (c == ' ' && i > 0) { // Trigger for foundName
					if (!foundName) {
						foundName = true;
					}else {
						names.add(name);
						name = new String();
						foundName = false;
						count ++;
					}
				}else if (foundName) {
					name += c;
				}
			}
		}
		
		for (String msg : names) {
			System.out.println(msg);
		}
		return names;
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
