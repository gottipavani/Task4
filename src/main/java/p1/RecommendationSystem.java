package p1;


import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;

import java.io.File;
import java.util.List;

public class RecommendationSystem {
    public static void main(String[] args) {
        try {
            // Load data file
            File dataFile = new File("src/main/resources/data.csv");
            DataModel model = new FileDataModel(dataFile);

            // Similarity between items
            ItemSimilarity similarity = new PearsonCorrelationSimilarity(model);

            // Build item-based recommender
            Recommender recommender = new GenericItemBasedRecommender(model, similarity);

            // Recommend 3 items for user with ID=1
            List<RecommendedItem> recommendations = recommender.recommend(1, 3);

            System.out.println("Item-Based Recommendations for User 1:");
            if (recommendations.isEmpty()) {
                System.out.println("No recommendations found.");
            } else {
                for (RecommendedItem recommendation : recommendations) {
                    System.out.println("Item: " + recommendation.getItemID() +
                            " , Predicted Preference: " + recommendation.getValue());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
