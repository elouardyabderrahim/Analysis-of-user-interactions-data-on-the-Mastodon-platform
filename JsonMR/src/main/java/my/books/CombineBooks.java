package my.books;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;


public class CombineBooks {

    public static class Map extends Mapper<LongWritable, Text, Text, Text> {
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
                // Add more fields as needed

                // Emit language as the key and the post data as the value
                context.write(new Text(username), new Text(postData.toString()));

            } catch (JSONException e) {
                // Handle JSON parsing exceptions here
            }
        }
    }

    public static class Reduce extends Reducer<Text, Text, Text, Text> {
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

    public static void main(String[] args) throws Exception {
        if (args.length != 2) {
            System.err.println("Usage: CombineBooks <input path> <output path>");
            System.exit(-1);
        }

        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, "Language Posts");

        job.setJarByClass(CombineBooks.class);
        job.setMapperClass(Map.class);
        job.setReducerClass(Reduce.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
