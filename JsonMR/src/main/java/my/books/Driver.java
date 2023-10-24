package my.books;

import my.books.Mappers.Map;
import my.books.Mappers.MaperCountLanguages;
import my.books.Reducers.Reduce;
import my.books.Reducers.ReducerCountLanguages;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.mapreduce.TableMapReduceUtil;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;

public class Driver {
    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.err.println("Usage: Driver <input path> <output path1> <output path2> <output path3>");
            System.exit(-1);
        }

        Configuration conf = HBaseConfiguration.create();
        Job job = Job.getInstance(conf, "user Posts");

        job.setJarByClass(Driver.class);
        job.setMapperClass(Map.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Text.class);
        FileInputFormat.addInputPath(job, new Path(args[0]));

        // Set the output format for HBase
        TableMapReduceUtil.initTableReducerJob("user_post", Reduce.class, job);
        job.setReducerClass(Reduce.class);

/*
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, "Language Posts");

        job.setJarByClass(Driver.class);
        job.setMapperClass(Map.class);
        job.setReducerClass(Reduce.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));*/

        if (job.waitForCompletion(true)) {

            Configuration conf2 = HBaseConfiguration.create();
            Job job2 = Job.getInstance(conf2, "Count Languages");

            job2.setJarByClass(Driver.class);
            job2.setMapperClass(MaperCountLanguages.class);
            job2.setMapOutputKeyClass(Text.class);
            job2.setMapOutputValueClass(IntWritable.class);
            FileInputFormat.addInputPath(job2, new Path(args[0]));

            // Set the output format for HBase
            TableMapReduceUtil.initTableReducerJob("language_count2", ReducerCountLanguages.class, job2);
            job2.setReducerClass(ReducerCountLanguages.class);

            // Create and configure the second MapReduce job (CountLanguages)
            /*Configuration conf2 = org.apache.hadoop.hbase.HBaseConfiguration.create();
            Job job2 = Job.getInstance(conf2, "Count Languages");

            job2.setJarByClass(Driver.class);
            job2.setMapperClass(MaperCountLanguages.class);
            job2.setReducerClass(ReducerCountLanguages.class);

            job2.setOutputKeyClass(Text.class);
            job2.setOutputValueClass(IntWritable.class);

            FileInputFormat.addInputPath(job2, new Path(args[0]));

            // Do not use FileOutputFormat.setOutputPath for the second job

            // Configure the output format for HBase
            job2.setOutputFormatClass(TableOutputFormat.class);
            job2.getConfiguration().set(TableOutputFormat.OUTPUT_TABLE, "language_count");
*/
            if (job2.waitForCompletion(true)) {
                System.exit(0); // Exit with a success code
            } else {
                System.exit(1); // Exit with an error code if the second job fails
            }
        } else {
            System.exit(1); // Exit with an error code if the first job fails
        }
    }
}
