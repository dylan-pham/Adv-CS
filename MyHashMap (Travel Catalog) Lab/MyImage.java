public class MyImage {
    private String url;
    private String landmarkName;

    public MyImage(String url, String landmarkName) {
        this.url = url;
        this.landmarkName = landmarkName;
    } 

    public String getURL() {
        return url;
    }

    public String getLandmarkName() {
        return landmarkName;
    }
}