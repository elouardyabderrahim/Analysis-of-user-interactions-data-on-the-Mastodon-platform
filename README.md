# Analysis of user interactions data on the Mastodon platform
This project aims to analyze social media data to gather insights into user engagement, content popularity, etc. It utilizes MapReduce for data processing, stores the results in HBase, and orchestrates the workflow with Apache Airflow.

## Hadoop: Handling Big Data

Hadoop is a powerful framework designed to handle the challenges posed by large-scale datasets, often referred to as "BIG DATA." The ordinary methods of data processing are not sufficient for these massive datasets. Hadoop is comprised of three key components, each tailored to work with big data effectively.

### 1. Storing Data: HDFS (Hadoop Distributed File System)

In the first step of handling big data, we address the need for scalable storage. HDFS, the Hadoop Distributed File System, is a fundamental component for this purpose. It takes the large dataset and distributes it among different computers while storing it in blocks.

For example, if we have a 600MB dataset, HDFS divides it into 128MB blocks. These blocks are created and then replicated three times, with each copy stored on different data nodes. This replication method ensures data redundancy, making HDFS fault-tolerant.

In case of data node failures, data remains accessible, and no information is lost. HDFS's ability to create multiple copies of data and distribute them across various systems helps ensure data integrity.

### 2. Processing Data: MapReduce

After effectively storing large datasets, the next challenge is processing the data efficiently. This is where MapReduce comes into play. In the traditional way, processing all the data as one unit can be time-consuming and resource-intensive. 

Hadoop's MapReduce framework divides data into parts and processes each part separately on different data nodes. This parallel processing approach allows for more efficient data processing, ultimately resulting in a final output.

Once you have your MapReduce program ready, you need to run it on a Hadoop cluster. This is achieved with the help of various resources, including RAM and CPU, to efficiently manage these resources.

### 3. YARN (Yet Another Resource Negotiator)

The third component of Hadoop is YARN, which plays a crucial role in managing resources within a Hadoop cluster. YARN (Yet Another Resource Negotiator) acts as the resource manager for Hadoop, allocating resources such as memory and CPU across applications efficiently. This ensures that multiple tasks can run simultaneously on a Hadoop cluster without resource contention.
