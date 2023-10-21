package my.books;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class LanguageStats implements Writable {
    private int postCount;
    private double avgFollowers;
    private double avgFollowing;

    public LanguageStats() {
        // Default constructor
    }

    public LanguageStats(int postCount, double avgFollowers, double avgFollowing) {
        this.postCount = postCount;
        this.avgFollowers = avgFollowers;
        this.avgFollowing = avgFollowing;
    }


    public int getPostCount() {
        return postCount;
    }

    public void setPostCount(int postCount) {
        this.postCount = postCount;
    }

    public double getAvgFollowers() {
        return avgFollowers;
    }

    public void setAvgFollowers(double avgFollowers) {
        this.avgFollowers = avgFollowers;
    }

    public double getAvgFollowing() {
        return avgFollowing;
    }

    public void setAvgFollowing(double avgFollowing) {
        this.avgFollowing = avgFollowing;
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeInt(postCount);
        out.writeDouble(avgFollowers);
        out.writeDouble(avgFollowing);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        postCount = in.readInt();
        avgFollowers = in.readDouble();
        avgFollowing = in.readDouble();
    }
}
