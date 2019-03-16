Hi,

Please use following command line args - 

1. Java -jar Search.jar graph.txt 
[FOR ALL METHODS]

2. Java -jar Search.jar graph.txt BFS 
[FOR Breadth First Search]

3. Java -jar Search.jar graph.txt DFS 
[FOR Depth First Search]
   Java -jar Search.jar graph.txt DFS $Depthlimit (Foreg: Java -jar Search.jar graph.txt DFS 2)
[FOR Setting the depth limit, by default it is 2]

4. Java -jar Search.jar graph.txt UNIFORM 
[FOR UNIFORM COST SEARCH]

5. Java -jar Search.jar graph.txt LIMITEDDEPTH 
[FOR LIMITEDDEPTH]

6. Java -jar Search.jar graph.txt ITERATIVE 
[FOR ITERATIVE DEEPENING DEPTH FIRST SEARCH]

7. Java -jar Search.jar graph.txt GREEDY 
[FOR GREEDY SEARCH]

8. Java -jar Search.jar graph.txt ASTAR 
[FOR ASTAR SEARCH]

9. Java -jar Search.jar graph.txt BEAM 
[For Beam Search]

While solving, i have checked for nodes which are already in frontier or are already explored not to go through them ahain, which is why my Queue status might by slighty different from the solution provided, but my final answer should be correct. To check for the path choosen to goal node, you can check the last status of the Queue printed.

Please email or contact me if you face any problem in running the code

Regards
Paritosh (pgoel@wpi.edu)
