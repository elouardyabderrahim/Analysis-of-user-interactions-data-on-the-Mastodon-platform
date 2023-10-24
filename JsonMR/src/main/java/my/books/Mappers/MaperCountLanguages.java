package my.books.Mappers;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class MaperCountLanguages extends Mapper<LongWritable, Text, Text, IntWritable> {
    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        try {
            JSONObject jsonObject = new JSONObject(value.toString());
            if (jsonObject.has("language")) {
                String language = jsonObject.getString("language");
                // Emit language as the key and a count of 1 as the value
                context.write(new Text(language), new IntWritable(1));
            }
        } catch (JSONException e) {
            // Handle JSON parsing exceptions here
        }
    }
}
