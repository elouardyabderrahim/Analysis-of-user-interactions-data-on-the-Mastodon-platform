package my.books.Reducers;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;

public class Reduce extends Reducer<Text, Text, Text, Text> {
    public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        JSONObject result = new JSONObject();

        for (Text value : values) {
            try {
                JSONObject post = new JSONObject(value.toString());
                result.append("posts", post);
            } catch (JSONException e) {
                // Handle JSON parsing exceptions here
            }
        }
        // Emit language as the key and the aggregated posts as the value
        context.write(key, new Text(result.toString()));
    }
}

