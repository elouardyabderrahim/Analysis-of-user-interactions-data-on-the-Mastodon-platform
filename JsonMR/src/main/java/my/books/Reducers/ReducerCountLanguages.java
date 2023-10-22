package my.books.Reducers;


import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class ReducerCountLanguages extends Reducer<Text, IntWritable, Text, IntWritable> {
    public void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        int languageCount = 0;

        // Iterate through the values to count the occurrences of the language
        for (IntWritable value : values) {
            languageCount += value.get();
        }

        // Emit language as the key and the total count as the value
        context.write(key, new IntWritable(languageCount));
    }
}