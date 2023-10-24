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

![image](https://github.com/elouardyabderrahim/Analysis-of-user-interactions-data-on-the-Mastodon-platform/assets/101024060/6b7957d5-5108-4f0c-b60e-4d7f561366fc)


### 3. YARN (Yet Another Resource Negotiator)

The third component of Hadoop is YARN, which plays a crucial role in managing resources within a Hadoop cluster. YARN (Yet Another Resource Negotiator) acts as the resource manager for Hadoop, allocating resources such as memory and CPU across applications efficiently. This ensures that multiple tasks can run simultaneously on a Hadoop cluster without resource contention.

![image](https://github.com/elouardyabderrahim/Analysis-of-user-interactions-data-on-the-Mastodon-platform/assets/101024060/5eeb859a-7c9e-45c8-a9c4-05a9bffdd11f)


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
   
### Install AirFlow:
https://www.freecodecamp.org/news/install-apache-airflow-on-windows-without-docker/
![image](https://github.com/elouardyabderrahim/Analysis-of-user-interactions-data-on-the-Mastodon-platform/assets/101024060/961565a8-2d7a-4f04-ba77-0e84726ee627)

## Installing HBase on Ubuntu:
![image](https://github.com/elouardyabderrahim/Analysis-of-user-interactions-data-on-the-Mastodon-platform/assets/101024060/bc0c7e99-251b-4867-bac6-ca037f4313ca)




Below are the step-by-step instructions for installing HBase on Ubuntu:

### Step 1: Place the following command

Place `hbase-1.1.2-bin.tar.gz` in the `/home/hduser` directory.

### Step 2: Extract it by running the command

Use the command below to extract the contents and create a folder named `hbase-1.1.2` in the `/home/hduser` location:

```bash
tar -xvf hbase-1.1.2-bin.tar.gz
```

### Step 3: Configure hbase-env.sh

Open the `hbase-env.sh` file and specify the `JAVA_HOME` path.

### Step 4: Configure the .bashrc file

Open the `~/.bashrc` file and specify the `HBASE_HOME` path as follows:

```bash
export HBASE_HOME=/home/hduser/hbase-1.1.1
export PATH=$PATH:$HBASE_HOME/bin
```

### Step 5: Add properties to the hbase-site.xml file

Open the `hbase-site.xml` file and add the following properties:

```xml
<property>
  <name>hbase.rootdir</name>
  <value>file:///home/hduser/HBASE/hbase</value>
</property>
<property>
  <name>hbase.zookeeper.property.dataDir</name>
  <value>/home/hduser/HBASE/zookeeper</value>
</property>
```

### Step 6: Specify IP addresses

Edit the `/etc/hosts` file and specify IP addresses as indicated.

### Step 7: Run the Start-hbase.sh script

Execute the `Start-hbase.sh` script located in the `hbase-1.1.1/bin` directory.

You can check whether HMaster is running or not by using the `jps` command.

### Step 8: Start the HBase shell

Start the HBase shell by running `hbase shell`. It will enter interactive shell mode, allowing you to execute various commands.

Standalone mode doesn't require starting Hadoop daemons. HBase can operate independently.

Enjoy using HBase on Ubuntu .

# GDPR and Personal Data Processing

## Identified Personal Data

In the Mastodon message, the following personal data has been identified:

1. **User ID** (may be associated with a real person).
2. **User's username** (may be a pseudonym).
3. **User's display name** (may be a pseudonym).
4. **User's description** (may contain personal information).
5. **User's profile URL** (may contain personal information).
6. **User's profile picture** (may be a profile photo).

## Steps to Ensure GDPR Compliance

To ensure GDPR compliance in the processing of this personal data, follow the following steps:

### a. Identification of Personal Data

You have already identified the personal data in the Mastodon message. Ensure that this information is documented for proper traceability.

### b. Legal Basis for Processing

Determine the legal basis for processing this personal data. This can be user consent or a legitimate interest in collecting this data.

### c. Transparency

Ensure that users are informed about the collection of their personal data and the purposes of processing, for example, by displaying a privacy policy.

### d. User Rights

Respect the rights of users under GDPR, such as the right to access, rectify, delete, and port their data.

### e. Data Security

Ensure that personal data is stored securely to prevent data breaches.

### f. Data Retention Period

Determine how long you need to retain this personal data and delete it when it is no longer necessary.

### g. Management of GDPR Requests

Establish a process to handle user requests for access, rectification, or deletion of personal data.

# Planification:
## JIRA:
![image](https://github.com/elouardyabderrahim/Analysis-of-user-interactions-data-on-the-Mastodon-platform/assets/101024060/34508b02-8103-49c4-b243-78e93b42f5ee)



