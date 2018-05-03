package socialNetwork;

import java.util.ArrayList;
import java.util.Collection;

public class Graph<V> {
	private ArrayList<Node<V>> vertices;

	public Graph() {
		vertices = new ArrayList<Node<V>>();
	}

	public boolean addVertexToGraph(V vertexToAdd) {
		Node<V> newVertex = getNode(vertexToAdd);
		if (newVertex == null) {
			newVertex = new Node<V>(vertexToAdd);
		}
		if (!vertices.contains(newVertex)) {
			vertices.add(newVertex);
			return true;
		}
		return false;
	}

	public boolean isVertex(V vertexToCheck) {
		Node<V> newVertex = getNode(vertexToCheck);
		return newVertex != null;
	}

	public Collection<V> getVertices() {
		Collection<V> coll = new ArrayList<V>();

		for (Node<V> vertex: vertices) {
			coll.add(vertex.getData());
		}

		return coll;
	}

	public boolean removeVertexFromGraph(V vertexToRemove) {
		Node<V> newVertex = getNode(vertexToRemove);
		if (vertices.contains(newVertex)) {
			vertices.remove(newVertex);
			return true;
		}
		return false;
	}

	public boolean addEdgeToGraph(V source, V dest, int weight) {
		Node<V> sourceNode = getNode(source);
		Node<V> destinationNode = getNode(dest);

		if (sourceNode != null && destinationNode != null) {
			return sourceNode.addEdge(destinationNode, weight);
		}

		return false;
	}

	private Node<V> getNode(V data){
		for (Node<V> vertex : vertices) {
			if (vertex.getData().equals(data)) {
				return vertex;
			}
		}
		return null;
	}

	public int numEdges(V source, V dest) {
		Node<V> sourceNode = getNode(source);
		Node<V> destNode = getNode(dest);

		if (sourceNode != null && destNode != null) {
			return sourceNode.numEdges(destNode);
		}
		return 0;
	}

	public boolean hasEdge(V source, V dest, int weight) {
		Node<V> sourceNode = getNode(source);
		Node<V> destNode = getNode(dest);

		if (sourceNode != null && destNode != null) {
			return sourceNode.hasEdge(destNode, weight);
		}
		return false;
	}

	public boolean hasEdge(V source, V dest) {
		Node<V> sourceNode = getNode(source);
		Node<V> destNode = getNode(dest);

		if (sourceNode != null && destNode != null) {
			return sourceNode.hasEdge(destNode);
		}
		return false;
	}

	public boolean removeAllEdges(V source, V dest) {
		Node<V> sourceNode = getNode(source);
		Node<V> destNode = getNode(dest);
		
		if(sourceNode != null && destNode != null) {
			return sourceNode.removeAllEdges(dest);
		}
		
		return false;
	}

	public boolean removeEdge(V source, V dest, int weight) {
		Node<V> sourceNode = getNode(source);
		Node<V> destNode = getNode(dest);
		
		if(sourceNode != null || destNode != null) {
			return sourceNode.removeEdge(dest, weight);
		}
		
		return false;
	}

	public Collection<V> neighborsOfVertex(V sourceVertex) {
		Node<V> sourceNode = getNode(sourceVertex);
		if (sourceNode != null) {
			return sourceNode.getEdges();
		}
		return new ArrayList<V>();
	}

	public Collection<V> predecessorsOfVertex(V destVertex) {
		Node<V> dest = getNode(destVertex);
		ArrayList<V> list = new ArrayList<V>();
		for (Node<V> vertex: vertices) {
			list.addAll(vertex.predecessorsOf(destVertex));
		}
		return list;
	}

	public String toString() {
		int i = 0 ;
		String msg = new  String();
		for (Node<V> vertex : vertices) {
			i ++;
			msg += i + "\t" + vertex.getData() + "\t" + vertex.toString() + "\n";
		}
		return msg;
	}

}
