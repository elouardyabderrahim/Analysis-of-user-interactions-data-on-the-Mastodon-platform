package my.books.Mappers;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public  class Map extends Mapper<LongWritable, Text, Text, Text> {
    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        try {
            JSONObject jsonObject = new JSONObject(value.toString());
            String language = jsonObject.getString("language");
            String username = jsonObject.getJSONObject("account").getString("username");
            // Create a JSON object to represent the post data
            JSONObject postData = new JSONObject();
            postData.put("postId", jsonObject.getLong("id"));
            postData.put("created_at", jsonObject.getString("created_at"));
            //postData.put("content", jsonObject.getString("content"));
            postData.put("language", language);  // Add username to the JSON

            // Emit language as the key and the post data as the value
            context.write(new Text(username), new Text(postData.toString()));

        } catch (JSONException e) {
            System.err.println(e);
        }
    }
}