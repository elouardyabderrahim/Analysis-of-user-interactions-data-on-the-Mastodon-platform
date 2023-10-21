package my.books;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class PostInfo implements Writable {
    private String language;
    private int followersCount;
    private int followingCount;

    public PostInfo() {
        // Default constructor
    }

    public PostInfo(String language, int followersCount, int followingCount) {
        this.language = language;
        this.followersCount = followersCount;
        this.followingCount = followingCount;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getFollowersCount() {
        return followersCount;
    }

    public void setFollowersCount(int followersCount) {
        this.followersCount = followersCount;
    }

    public int getFollowingCount() {
        return followingCount;
    }

    public void setFollowingCount(int followingCount) {
        this.followingCount = followingCount;
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeUTF(language);
        out.writeInt(followersCount);
        out.writeInt(followingCount);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        language = in.readUTF();
        followersCount = in.readInt();
        followingCount = in.readInt();
    }
}
