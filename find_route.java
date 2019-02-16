

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.HashSet;
import java.util.Set;
import java.util.Comparator;
import java.util.Collections;

public class find_route {

	public static void main(String[] args) {
		
		int n = 0;
		int flag = 0;
		String [] InputArray;
		ArrayList<String> Source = new ArrayList<String>();
		ArrayList<String> Destination = new ArrayList<String>();
		ArrayList<Double> Distance = new ArrayList<Double>();
		ArrayList<String> Nodes = new ArrayList<String>();
		ArrayList<Node> NodeList = new ArrayList<Node>();
		
		String fileName = args[0];
		String source = args[1];
		String destination = args[2];
		
		try
		{
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			String line;
			while(!(line = br.readLine()).equalsIgnoreCase("END OF INPUT"))
			{
				InputArray = line.split(" ");
				Source.add(InputArray[0]);
				Destination.add(InputArray[1]);
				Distance.add(Double.parseDouble(InputArray[2]));
			}
		}
		catch (IOException E) 
		{
			E.printStackTrace();
		}
		
		//To get all the unique nodes from Source and Destination arrayList.
		
		while(n<Source.size())
		{
			int p = 0;
			while(p<Nodes.size())
			{
				if(Nodes.get(p).contains(Source.get(n)))
				{
					flag =1;
					break;
				}
				p++;				
			}
			if(flag==0)
			{
				Nodes.add(Source.get(n));
			}
			flag = 0;
			n++;
		}
		n = 0;
		while(n<Destination.size())
		{
			int p = 0;
			while(p<Nodes.size())
			{
				if(Nodes.get(p).contains(Destination.get(n)))
				{
					flag =1;
					break;
				}
				p++;				
			}
			if(flag==0)
			{
				Nodes.add(Destination.get(n));
			}
			flag = 0;
			n++;
		}
		n = 0;
		while(n<Nodes.size())
		{
			NodeList.add(new Node(Nodes.get(n)));
			n++;
		}
		n = 0;
		int i = 0;
		int j = 0;
		
		//To create edges between nodes
		
		ArrayList<Path> Edges = new ArrayList<Path>();
		while(n<NodeList.size())
		{
			while(i<Source.size() && i<Destination.size())
			{
				if(NodeList.get(n).state.contains(Source.get(i)))
				{
					while(j<NodeList.size())
					{
						if(NodeList.get(j).state.contains(Destination.get(i)))
						{
							break;
						}
						j++;
					}
					Edges.add(new Path(NodeList.get(j), Distance.get(i)));
					j = 0;
				}
				if(NodeList.get(n).state.contains(Destination.get(i)))
				{
					while(j<NodeList.size())
					{
						if(NodeList.get(j).state.contains(Source.get(i)))
						{
							break;
						}
						j++;
					}
					Edges.add(new Path(NodeList.get(j), Distance.get(i)));
					j = 0;
				}
				i++;
			}
			i = 0;
			NodeList.get(n).Adjacencies = Edges.toArray(new Path[Edges.size()]);
			Edges.clear();
			n++;
		}
		n= 0;
		i = 0;
		//To get index of the source and destination mentioned in the cmd line.
		while(n < NodeList.size())
		{
			if(NodeList.get(n).state.contains(source))
			{
				break;
			}
			n++;
		}
		while(i < NodeList.size())
		{
			if(NodeList.get(i).state.contains(destination))
			{
				break;
			}
			i++;
		}
		// Call to UniformCostSearch
		UniformCostSearch(NodeList.get(n), NodeList.get(i));
		
		//To print path
		List<Node> route = printRoute(NodeList.get(i));
		
	
	//If no connection between two nodes
	if(1 == route.size())
	{
		System.out.println("Distance: Infinity \r\n" + "Route: \r\n" + "None");
	}
	// Print source, Intermediate and Destination node
	else
	{
		System.out.println("Route: \r\n");
		int p = 0;
		while(p<route.size()-1) {
    		for(int i1=0;i1<Source.size()&&i1<Destination.size();i1++) {
    			if(((route.get(p).state.equalsIgnoreCase(Source.get(i1))) && (route.get(p+1).state.equalsIgnoreCase(Destination.get(i1))))||((route.get(p).state.equalsIgnoreCase(Destination.get(i1))) && (route.get(p+1).state.equalsIgnoreCase(Source.get(i1)))))
    			{
    				System.out.println(route.get(p).state+" to "+route.get(p+1).state+", "+Distance.get(i1));
    				break;
    			}
    		}p++;
    	}
    	System.out.println("distance: "+NodeList.get(i).Cost_Of_Path);
    }

}
	public static void UniformCostSearch(Node source, Node goal){

        source.Cost_Of_Path = 0;
        //Minimum priority queue 
        PriorityQueue<Node> queue = new PriorityQueue<Node>(20,
                new Comparator<Node>(){

                    //override compare method
                    public int compare(Node i, Node j){
                        if(i.Cost_Of_Path > j.Cost_Of_Path){
                            return 1;
                        }

                        else if (i.Cost_Of_Path < j.Cost_Of_Path){
                            return -1;
                        }

                        else{
                            return 0;
                        }
                    }
                }

        );
        //Add the source into the queue
        queue.add(source);
        
        Set<Node> closedSet = new HashSet<Node>();
        boolean found = false;

        //while frontier is not empty
        do{

            Node current = queue.poll();
            closedSet.add(current); 		//indicates it is visited


            if(current.state.equals(goal.state)){	//If current state is goal state then destination is reached
                found = true;


            }
            //If the current node has successor then expand
            for(Path e: current.Adjacencies){
                Node child = e.target;
                double cost = e.cost;
            
            //Add the successor to the queue if it is not explored 
                if(!closedSet.contains(child) && !queue.contains(child)){
                	child.Cost_Of_Path=current.Cost_Of_Path+cost;
                    child.Ancestor = current;
                    queue.add(child);

                }
              //If already present, checks the cumulative cost, if it less than child cost visited before, update it
                else if((queue.contains(child))&&(child.Cost_Of_Path>(current.Cost_Of_Path+cost))){
                    
                	child.Ancestor=current;
                    child.Cost_Of_Path=current.Cost_Of_Path+cost;
                    
                    queue.remove(child);
                    queue.add(child);
                }


            }
        }while(!queue.isEmpty()&&(found==false));

    }
//Print the path
public static List<Node> printRoute(Node target){
        List<Node> path = new ArrayList<Node>();
        for(Node node = target; node!=null; node = node.Ancestor){
            path.add(node);
        }

        Collections.reverse(path);
        
        return path;

    }

}


		

		
		
		
	
















