package net.homecredit.jobsdev;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * @author Karel Ha <mathemage@gmail.com>
 */
public class YourMazeSolver implements MazeSolver {
	
	private int n;
	private boolean[][] virginCells;                         // false = a touched cell or wall; true = cell to touch
	private static boolean debugMode = false;
	
	@Override
	public boolean existsPath(boolean[][] mazeMatrix) {
		virginCells = mazeMatrix;
		n = mazeMatrix.length;
		// uncomment the desired estimate for queue size
		int estimateOnQueueSize
			= n;
//			= n * n;
		Queue<Integer> queue = new ArrayDeque<>(estimateOnQueueSize);   // 1D indexing top-down left-right
		
		// entrances at the top-most row
		for (int i = 0; i < n; i++) {
			if (isVirgin(i)) {
				queue.add(i);
				touchVirgin(i);
			}
		}
		
		while (!queue.isEmpty()) {
			int currentCellIndex = queue.peek();
			queue.remove();
			if (getRow(currentCellIndex) == n - 1) { // a cell in the bottom-most row
				return true;                         // i.e. the bottom-most row is reachable from the top-most row
			}
			for (Integer neighbor: getNeighbors(currentCellIndex)) {
				if (isVirgin(neighbor)) {
					queue.add(neighbor);
					touchVirgin(neighbor);
				}
			}
			
			if (debugMode) {
				printMaze(virginCells, currentCellIndex);
			}
		}
		
		return false;
	}
	
	private boolean isVirgin(Integer cellIndex) {
		return virginCells[getRow(cellIndex)][getColumn(cellIndex)];
	}
	
	private void touchVirgin(Integer cellIndex) {
		virginCells[getRow(cellIndex)][getColumn(cellIndex)] = false;
	}
	
	private List<Integer> getNeighbors(Integer cellIndex) {
		List<Integer> neighbors = new ArrayList<>();
		
		Integer leftNeighbor = cellIndex - 1;
		if (leftNeighbor > 0 && getRow(leftNeighbor) == getRow(cellIndex)) {        // cells in the same row
			neighbors.add(leftNeighbor);
		}
		
		Integer rightNeighbor = cellIndex + 1;
		if (rightNeighbor < n * n && getRow(rightNeighbor) == getRow(cellIndex)) {  // cells in the same row
			neighbors.add(rightNeighbor);
		}
		
		Integer upperNeighbor = cellIndex - n;
		if (upperNeighbor >= 0 && getRow(upperNeighbor) < n ) {
			neighbors.add(upperNeighbor);
		}
		
		Integer lowerNeighbor = cellIndex + n;
		if (getRow(lowerNeighbor) < n ) {                      // dubious condition, unnecessary in this implementation
			neighbors.add(lowerNeighbor);
		}
		
		return neighbors;
	}
	
	private int getRow(Integer neighbor) {
		return neighbor / n;
	}
	
	private int getColumn(Integer neighbor) {
		return neighbor % n;
	}
	
	/**
	 * Prints maze to stdout
	 * @param maze  maze to print
	 * @param currentCellIndex  location of cell where BFS expands to neighbors
	 */
	private void printMaze(boolean[][] maze, int currentCellIndex) {
		int currentRow = 0;
		for (boolean[] aMaze : maze) {
			int currentColumn = 0;
			for (boolean anAMaze : aMaze) {
				if (currentRow == getRow(currentCellIndex) &&
					currentColumn == getColumn(currentCellIndex)) {
					System.out.print((anAMaze ? 'T' : 'F') + " ");
				} else {
					System.out.print((anAMaze ? 1 : 0) + " ");
				}
				currentColumn++;
			}
			System.out.println();
			currentRow++;
		}
		System.out.println();
	}
}
