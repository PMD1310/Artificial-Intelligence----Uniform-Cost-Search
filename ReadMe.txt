Name: Pramod Kemisetty
UTA ID: 1001570333

What programming language is used?
Java

How code is structured?
FindRoute.java- This is main class. Created connection to the nodes and virtual edges and performed uniform cost search.
ConnectingPath.java: This class contains the target nodes for connection. In other words it establishes the edges between nodes.
Node.java: Class contains the state of the node, cumulative cost and adjacent edges info.
Input1.txt: Place the text file that needed to be used as reference.

How to run the code?
Navigate to the following path :  /home/p/px/pxk0333/ai1
To compile all the java files in src provide following comment: javac *.java 
Note: Make sure all .class in src are deleted before compiling
To run the code:
java FindRoute Input1.txt source destination
ex: 	java FindRoute Input1.txt Bremen London


References:
Similar code found in following websites:
To read the line: https://stackoverflow.com/questions/4574041/read-next-word-in-java
How to split the line with space: https://www.geeksforgeeks.org/split-string-java-examples/
ArrayList Usage: https://www.geeksforgeeks.org/arraylist-in-java/
HashSet Implemenatation: https://www.geeksforgeeks.org/hashset-in-java/
Uniform Cost logic: https://stackoverflow.com/questions/46132592/java-uniform-cost-search-implementation-with-txt-file-input
Node and ConnectingPath DataStructure: https://stackoverflow.com/questions/20635394/uniform-cost-search-implementation
PrintPath Implemenation: https://github.com/raymondchua/algorithms/blob/master/UniformCostSearchAlgo.java
https://stackoverflow.com/questions/18068756/how-to-detect-end-of-line-with-scanner-from-text-file	
https://stackoverflow.com/questions/8816001/how-to-get-distinct-values-from-an-array-in-c
https://www.javatpoint.com/java-string-equalsignorecase
https://stackoverflow.com/questions/4574041/read-next-word-in-java
https://algorithmicthoughts.wordpress.com/2012/12/15/artificial-intelligence-uniform-cost-searchucs/
