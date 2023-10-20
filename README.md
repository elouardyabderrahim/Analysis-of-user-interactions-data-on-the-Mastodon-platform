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

![hadoopMap reduce](https://github.com/elouardyabderrahim/Analysis-of-user-interactions-data-on-the-Mastodon-platform/assets/101024060/adbc99e9-50d4-4590-a379-f42364c9bf40)

### 3. YARN (Yet Another Resource Negotiator)

The third component of Hadoop is YARN, which plays a crucial role in managing resources within a Hadoop cluster. YARN (Yet Another Resource Negotiator) acts as the resource manager for Hadoop, allocating resources such as memory and CPU across applications efficiently. This ensures that multiple tasks can run simultaneously on a Hadoop cluster without resource contention.

![hadoopyarn](https://github.com/elouardyabderrahim/Analysis-of-user-interactions-data-on-the-Mastodon-platform/assets/101024060/84deaa89-8a66-4701-add7-029a4d38b8d4)

## Hadoop Installation and Configuration on WSL2 (Ubuntu)
This guide will help you install and configure Hadoop 3.2.4 on your WSL2 (Ubuntu) environment.
1. **Download Hadoop**

   ```bash
   wget https://dlcdn.apache.org/hadoop/common/hadoop-3.2.4/hadoop-3.2.4.tar.gz
   ```

2. **Update and Upgrade**

   Ensure your system is up to date:

   ```bash
   sudo apt update
   sudo apt upgrade
   ```

3. **Install Dependencies**

   Install necessary dependencies:

   ```bash
   sudo apt-get update
   sudo apt-get install -y openssh-client openssh-server vim ssh -y
   sudo apt install openjdk-8-jdk openjdk-8-jre
   ```

4. **Configure Bashrc**

   Edit your .bashrc file:

   ```bash
   sudo vim ~/.bashrc
   ```

   Add these lines at the end:

   ```bash
   export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64
   export JRE_HOME=/usr/lib/jvm/java-8-openjdk-amd64/jre
   ```

   If you face permission issues, use:

   ```bash
   sudo chown -R <userName> ~/.bashrc
   ```

5. **Install Hadoop**

   Decompress and rename the Hadoop file:

   ```bash
   tar -xzf hadoop-3.2.4.tar.gz
   sudo mv hadoop-3.2.4 hadoop
   ```

   Move Hadoop to '/usr/local' and set permissions:

   ```bash
   sudo mv hadoop /usr/local
   sudo chmod 777 /usr/local/hadoop
   ```

6. **Configure Hadoop Environment**

   Edit .bashrc again:

   ```bash
   code ~/.bashrc
   ```

   Add these lines for Hadoop environment variables:

   ```bash
   export HADOOP_HOME=/usr/local/hadoop
   export HADOOP_INSTALL=$HADOOP_HOME
   export HADOOP_MAPRED_HOME=$HADOOP_HOME
   export HADOOP_COMMON_HOME=$HADOOP_HOME
   export HADOOP_HDFS_HOME=$HADOOP_HOME
   export HADOOP_YARN_HOME=$HADOOP_HOME
   export YARN_HOME=$HADOOP_HOME
   export HADOOP_COMMON_LIB_NATIVE_DIR=$HADOOP_HOME/lib/native
   export PATH=$PATH:$HADOOP_HOME/sbin:$HADOOP_HOME/bin
   ```

   Save the file and run:

   ```bash
   source ~/.bashrc
