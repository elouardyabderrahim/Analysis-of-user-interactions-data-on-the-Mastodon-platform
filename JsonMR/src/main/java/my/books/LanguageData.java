package my.books;

import org.json.JSONObject;

public class LanguageData {
    private int languageCount;
    private JSONObject postData;

    public LanguageData() {
        this.languageCount = 0;
        this.postData = new JSONObject();
    }

    public int getLanguageCount() {
        return languageCount;
    }

    public void incrementLanguageCount() {
        this.languageCount++;
    }

    public JSONObject getPostData() {
        return postData;
    }

    public void setPostData(JSONObject postData) {
        this.postData = postData;
    }

    @Override
    public String toString() {
        JSONObject languageData = new JSONObject();
        languageData.put("languageCount", languageCount);
        languageData.put("postData", postData);
        return languageData.toString();
    }
}

