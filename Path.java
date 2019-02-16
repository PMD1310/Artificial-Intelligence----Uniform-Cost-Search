

public class Path {
	
	public double cost;     //step cost for path
	public Node target; 	//Destination node from the source node
	
	//Constructor to initialize step cost and destination
	public Path(Node node, double costValue)
	{
		cost = costValue;
		target = node;
	}

}
