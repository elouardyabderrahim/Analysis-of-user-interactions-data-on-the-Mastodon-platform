package my.books.Reducers;

import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.mapreduce.TableReducer;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;

import java.io.IOException;

public class ReducerCountLanguages extends TableReducer<Text, IntWritable, Text> {
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        int languageCount = 0;

        // Iterate through the values to count the occurrences of the language
        for (IntWritable value : values) {
            languageCount += value.get();
        }

        // Create a Put object to insert data into HBase
        Put put = new Put(Bytes.toBytes(key.toString()));
        put.addColumn(Bytes.toBytes("cf"), Bytes.toBytes("language"), Bytes.toBytes(languageCount));

        // Write the data to the HBase table
        context.write(key, put);
    }
}