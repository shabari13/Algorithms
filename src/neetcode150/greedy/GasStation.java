package neetcode150.greedy;
/*
 * Time Complexity: O(n)

We iterate through the array exactly once
Each operation inside the loop (addition, comparison) takes O(1) time
n is the number of gas stations

Space Complexity: O(1)

We only use a constant amount of extra space (4 integer variables)
No additional data structures that grow with input size
The space used doesn't depend on the input size

Idea is to check if the totalGas is greater or eualt to total cost. if it is, then there must be a point from where
we can start and keep looking. Have current Gs calculated which is total gas - total cost and if it is less than 0 means we need to go for an index
different than the start index which is i+1; So whatever was visited so far also wont work

 */
public class GasStation {
	
	public int canCompleteCircuit(int[] gas, int[] cost) {
		int totalGas = 0;
		int totalCost = 0;
		int currentGas = 0;
		int startIndex = 0;
		for(int i = 0; i < gas.length; i++) {
			totalGas += gas[i];
			totalCost += cost[i];
			currentGas = totalGas - totalCost;
			if(currentGas < 0) {
				startIndex = i+1;
				currentGas = 0;
			}
		}
		return totalGas >= totalCost ? startIndex : -1;
	}
	
	public static void main(String[] args) {
        GasStation solution = new GasStation();
        
        // Test case 1
        int[] gas1 = {1, 2, 3, 4, 5};
        int[] cost1 = {3, 4, 5, 1, 2};
        System.out.println("Test Case 1:");
        System.out.println("Gas: [1, 2, 3, 4, 5]");
        System.out.println("Cost: [3, 4, 5, 1, 2]");
        System.out.println("Starting station index: " + solution.canCompleteCircuit(gas1, cost1));
        System.out.println();
        
        // Test case 2
        int[] gas2 = {2, 3, 4};
        int[] cost2 = {3, 4, 3};
        System.out.println("Test Case 2:");
        System.out.println("Gas: [2, 3, 4]");
        System.out.println("Cost: [3, 4, 3]");
        System.out.println("Starting station index: " + solution.canCompleteCircuit(gas2, cost2));
        System.out.println();
        
        // Test case 3
        int[] gas3 = {5, 1, 2, 3, 4};
        int[] cost3 = {4, 4, 1, 5, 1};
        System.out.println("Test Case 3:");
        System.out.println("Gas: [5, 1, 2, 3, 4]");
        System.out.println("Cost: [4, 4, 1, 5, 1]");
        System.out.println("Starting station index: " + solution.canCompleteCircuit(gas3, cost3));
        System.out.println();
        
        // Test case 4 - Impossible case
        int[] gas4 = {1, 2};
        int[] cost4 = {3, 4};
        System.out.println("Test Case 4:");
        System.out.println("Gas: [1, 2]");
        System.out.println("Cost: [3, 4]");
        System.out.println("Starting station index: " + solution.canCompleteCircuit(gas4, cost4));
    }

}
