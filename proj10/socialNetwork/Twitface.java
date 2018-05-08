package socialNetwork;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;



public class Twitface implements Runnable{

	private static Graph <String> network = new Graph <String> ();
	private String url;
	private static Object o = new Object();
	private int threadNum;
	
	public Twitface(String url, int threadNum) {
		this.url = new String (url);
		this.threadNum = threadNum;
	}
	
	public Twitface() {
		url = new String();
		threadNum = 1000;
	}

	public boolean addUser(String name) {
		
		return network.addVertexToGraph(name);
	}

	public Collection<String> getAllUsers() {
		return network.getVertices();
	}

	public boolean friend(String user1, String user2) {
		
		if (!network.isVertex(user1)) {
			addUser(user1);
		}
		
		if (!network.isVertex(user2)) {
			addUser(user2);
		}
		
		if (network.addUndirectedEdgeToGraph(user1, user2, 1)){
			return true;
		}
			
		return false;
	}

	public Collection<String> getFriends(String user) {
		return network.neighborsOfVertex(user);
	}

	public boolean unfriend(String user1, String user2) {
		return network.removeAllEdges(user1, user2);
	}

	public Collection<String> peopleYouMayKnow(String user) {
		Set<String> set = new HashSet <String> ();

		
		for (String friend : network.neighborsOfVertex(user)) {
				for (String suggestion: network.neighborsOfVertex(friend)) {
					if (!network.hasEdge(user, suggestion) && !user.equals(suggestion)) {
						set.add(suggestion);
					}
				}
		}
		
		return set;
	}

	public void readFriendData(List<String> urls) {
		
		int count = 0;
		List<Thread> threads = new ArrayList<Thread>();
		
		for (String url : urls) {
			Thread thread = new Thread (new Twitface(url,count));
			count++;
			threads.add(thread);
		}
		
		for (Thread thread : threads) {
			thread.start();
		}
		
		for (Thread thread : threads) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public String toString() {
		return network.toString();
	}

	@Override
	public void run() {
		try {
			CommandReader commandRead = new CommandReader(url);
			
			for (Command cmd : commandRead.getCommands()) {
				
				if (cmd.getCommand().equalsIgnoreCase("addPerson")) {
					synchronized (o) {
						addUser(cmd.getUser());
						
					}
				} else if (cmd.getCommand().equalsIgnoreCase("addFriend")) {
					synchronized (o) {
						String user = cmd.getUser();
						String friend = cmd.getFriend();
						
						friend(user,friend);
						
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
