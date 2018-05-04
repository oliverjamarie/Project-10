package socialNetwork;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public class Twitface {

	private Graph <String> network;
	
	public Twitface() {
		network = new Graph <String>();
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
		//Set is an interface so this needs to be changes
		Set<String> set = new Set <String> ();
		
		return set;
	}

	public void readFriendData(List<String> urls) {
		throw new UnsupportedOperationException("You must write this method.");
	}

}
