package socialNetwork;

import java.util.ArrayList;
import java.util.Collection;

public class Node <V> {
	private class Edge {
		private Node<V> source;
		private Node<V> destination;
		private int weight;
				
		//Create a weighed, undirected edge
		public Edge(Node <V> source, Node<V> dest, int weight) {
			this.source = source;
			this.destination = dest;
			this.weight = weight;
		}
	} 
	
	private V data;
	private ArrayList<Edge> edges;

	public Node(V data){
		edges = new  ArrayList<Edge>();
		this.data = data;
	}
	
	public Node () {
		edges = new ArrayList<Edge>();
	}
	
	public boolean addEdge (Node<V> dest, int weight) {
		Edge edge = new Edge (this,dest,weight);
		
		if (weight < 0) {
			return false;
		}
		
		edges.add(edge);
		return true;
	}

	public int numEdges(Node<V> destNode) {
		int count = 0;
		
		for (Edge edge: edges) {
			if (edge.destination.data.equals(destNode.data)) {
				count ++;
			}
		}
		
		return count;
	}

	public boolean hasEdge(Node<V> destNode, int weight) {
		for (Edge edge: edges) {
			if (edge.destination.data.equals(destNode.data) &&
					edge.weight == weight) {
				return true;
			}
		}
		return false;
	}

	public boolean hasEdge(Node<V> destNode) {
		for (Edge edge: edges) {
			if (edge.destination.data.equals(destNode.data)) {
				return true;
			}
		}
		return false;
	}
	
	public Collection<V> getEdges() {
		Collection<V> coll = new ArrayList<V>();
		
		for (Edge edge: edges) {
			coll.add(edge.destination.data);
		}
		return coll;
	}
	
	public V getData() {
		return data;
	}
	
	public String toString() {
		String msg = new String();
		for (Edge edge: edges) {
			msg+= edge.destination.data + "\t";
		}
		return msg;
	}

	public boolean removeAllEdges(V dest) {
		Edge edge;
		int size = edges.size();
		boolean removed = false;
		
		for (int i = 0; i < size; i ++) {
			edge = edges.get(i);
			if (edge.destination.data.equals(dest)) {
				edges.remove(i);
				size--;
				removed = true;
			}
		}
		
		return removed;
	}

	public boolean removeEdge(V dest, int weight) {
		for (Edge edge: edges) {
			if(edge.destination.data.equals(dest) && edge.weight == weight) {
				return edges.remove(edge);
			}
		}
		return false;
	}
	
	public ArrayList<V> predecessorsOf(V dest){
		ArrayList<V> list = new ArrayList<V>();
		
		for (Edge edge :edges) {
			if (edge.destination.data.equals(dest)) {
				list.add(data);
			}
			
		}
		
		return list;
	}
}
