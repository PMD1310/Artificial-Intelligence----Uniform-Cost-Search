

public class Node {
	
	public final String state; 				//indicates the value of the node
	public double Cost_Of_Path;    			//cumulative cost of the path
	public Path[] Adjacencies;    			//successor of state (source)
	public Node Ancestor;       				//Parent of the source
	
	public Node(String val)
	
	{
		
		state = val;
	}
	
	public String toString() {
		return state;
	}
	

}
