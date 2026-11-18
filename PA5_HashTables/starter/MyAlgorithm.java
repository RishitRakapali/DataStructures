
/*
 * Name: Rishit Rakapali
 * Email: rrakapali@ucsd.edu
 * TSN: 200440552
 * Sources Used: PA5 write-up, course materials
 *
 * This file implements a fraud-detection algorithm using
 * a hash map to identify repeated merchant IDs within
 * a specified distance between transactions.
 */

/**
 * Provides an algorithm for detecting potentially fraudulent
 * transactions using a hash map.
 */
public class MyAlgorithm {

    /**
     * Checks whether a merchant ID appears more than once
     * within the specified number of index positions.
     *
     * @param merchantIds the array of merchant IDs
     * @param certainty the maximum allowed distance between duplicates
     * @return true if two matching merchant IDs occur close enough,
     *         false otherwise
     */
    public static boolean hasFraudulentTxns(
            int[] merchantIds, int certainty) {

        if (certainty <= 0) {
            throw new IllegalArgumentException();
        }

        MyHashMap<Integer, Integer> merchantIndices =
                new MyHashMap<Integer, Integer>();

        for (int index = 0; index < merchantIds.length; index++) {
            int merchantId = merchantIds[index];

            Integer previousIndex = merchantIndices.get(merchantId);

            if (previousIndex != null) {
                if (index - previousIndex <= certainty) {
                    return true;
                }
            }

            merchantIndices.put(merchantId, index);
        }

        return false;
    }
}
