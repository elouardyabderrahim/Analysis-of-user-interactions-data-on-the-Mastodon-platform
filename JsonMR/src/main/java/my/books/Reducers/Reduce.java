package my.books.Reducers;

import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.mapreduce.TableReducer;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.Text;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class Reduce extends TableReducer<Text,Text,Text> {
    public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        JSONObject result = new JSONObject();

        for (Text value : values) {
            try {
                JSONObject post = new JSONObject(value.toString());
                result.append("posts", post);
            } catch (JSONException e) {
                System.err.println(e);
            }
        }

        Put put = new Put(Bytes.toBytes(key.toString()));
        put.addColumn(Bytes.toBytes("cf"), Bytes.toBytes("user_post"), Bytes.toBytes(result.toString()));
        // Emit language as the key and the aggregated posts as the value
        context.write(key,put);
    }
}

