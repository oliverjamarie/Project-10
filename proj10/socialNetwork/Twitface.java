package socialNetwork;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Twitface implements Runnable{

	private Graph <String> network;
	private String url; // To Remove Later
	
	public Twitface(String url) {
		network = new Graph <String>();
		this.url = new String (url);
	}
	
	public Twitface() {
		network = new Graph <String>();
		url = new String();
	}

	public boolean addUser(String name) {
		return network.addVertexToGraph(name);
	}

	public Collection<String> getAllUsers() {
		return network.getVertices();
	}

	public boolean friend(String user1, String user2) {
		return network.addEdgeToGraph(user1, user2, 0);
	}

	public Collection<String> getFriends(String user) {
		return network.neighborsOfVertex(user);
	}

	public boolean unfriend(String user1, String user2) {
		return network.removeAllEdges(user1, user2);
	}

	public Collection<String> peopleYouMayKnow(String user) {
		Set<String> set = new HashSet <String> ();
		
		for (String msg : network.neighborsOfVertex(user)) {
			if (!user.equals(msg)) {
				set.add(msg);
			}
		}
		
		return set;
	}

	public void readFriendData(List<String> urls) {
		List<Thread> threads = new ArrayList<Thread>();
		
		for (String url : urls) {
			Thread thread = new Thread(new Twitface(url));
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

	@Override
	public void run() {
		System.out.println(url);
	}

}
