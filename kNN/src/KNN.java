/**
 * A kNN classification algorithm implementation.
 * 
 */
import java.math.*;
import java.util.Arrays;

public class KNN {

	/**
	 * In this method, you should implement the kNN algorithm. You can add 
	 * other methods in this class, or create a new class to facilitate your
	 * work. If you create other classes, DO NOT FORGET to include those java
   * files when preparing your code for hand in.
   *
	 * Also, Please DO NOT MODIFY the parameters or return values of this method,
   * or any other provided code.  Again, create your own methods or classes as
   * you need them.
	 * 
	 * @param trainingData
	 * 		An Item array of training data
	 * @param testData
	 * 		An Item array of test data
	 * @param k
	 * 		The number of neighbors to use for classification
	 * @return
	 * 		The object KNNResult contains classification accuracy, 
	 * 		category assignment, etc.
	 */
	public KNNResult classify(Item[] trainingData, Item[] testData, int k) {

		/* ... YOUR CODE GOES HERE ... */
    // for each test item in testData
		String[][] nearestNeighbors = new String[testData.length][k];
		String[] categoryAssignment = new String[testData.length];
		double[] accuracies = new double[testData.length];

		for(int i = 0; i < testData.length; i++){
			Item testItem = testData[i];
      // find kNN in trainingData
			double[][] currentNeighbors = new double[trainingData.length][2];

			for(int j = 0; j < trainingData.length; j++){
				Item currentItem = trainingData[j];
				double part1 = Math.pow(currentItem.features[0] - testItem.features[0],2);
				double part2 = Math.pow(currentItem.features[1] - testItem.features[1],2);
				double part3 = Math.pow(currentItem.features[2] - testItem.features[2],2);
				double distance = Math.sqrt(part1+part2+part3);
				// input the distance
				currentNeighbors[j][0] = distance;
				// input the place in trainingData
				currentNeighbors[j][1] = j;
			}

			java.util.Arrays.sort(currentNeighbors, new java.util.Comparator<double[]>() {
				public int compare(double[] a, double[] b) {
					return Double.compare(a[0], b[0]);
				}
			});

			String[] currentCategories = new String[k];

			// save kNN in KNNResult.nearestNeighbors
			for(int j = 0; j < k; j++){
				//String stringNeighbor = String.valueOf(currentNeighbors[j][1]);
				int intNeighbor = (int)Math.round(currentNeighbors[j][1]);

				Item currentNeighbor = trainingData[intNeighbor];
				nearestNeighbors[i][j] = currentNeighbor.name;
				currentCategories[j] = currentNeighbor.category;
			}

      // get predicted category, save in KNNResult.categoryAssignment
			int numMachine = 0;
			int numFruit = 0;
			int numGPE = 0;

			for(int j = 0; j < currentCategories.length; j++){
				String category = currentCategories[j];
				if(category.equals("machine")){
					numMachine++;
				}
				else if(category.equals("fruit")){
					numFruit++;
				}
				else{
					numGPE++;
				}
			}

			if(numMachine > numFruit && numMachine > numGPE){
				categoryAssignment[i] = "machine";
			}
			else if(numFruit > numMachine && numFruit > numGPE){
				categoryAssignment[i] = "fruit";
			}
			else if(numGPE > numFruit && numGPE > numMachine){
				categoryAssignment[i] = "nation";
			} // beginning of tie-breaking cases
			else if(numGPE == numMachine && numGPE > numFruit){
				categoryAssignment[i] = "nation";
			}
			else if(numGPE == numFruit && numGPE > numMachine){
				categoryAssignment[i] = "nation";
			}
			else if(numMachine == numFruit && numMachine > numGPE){
				categoryAssignment[i] = "machine";
			}
			else{ // if all categories occur equally
				categoryAssignment[i] = "nation";
			}





			if(categoryAssignment[i].equals(testData[i].category)){
				accuracies[i] = 1;
			}
			else{
				accuracies[i] = 0;
			}

		}

    // calculate accuracy
		double accuracy = 0;
		for(int i = 0; i < accuracies.length; i++){
			accuracy += accuracies[i];
		}
		accuracy = accuracy/testData.length;

		KNNResult knnResult = new KNNResult();

		knnResult.accuracy = accuracy;
		knnResult.categoryAssignment = categoryAssignment;
		knnResult.nearestNeighbors = nearestNeighbors;

		return knnResult;
	}
}
