package rz.rasel.library.socialurlextractor;

public class FacebookFile {

    private String sdUrl;
    private String hdUrl;
    private String ext;
    private String filename;
    private String author;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String argAuthor) {
        this.author = argAuthor;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String argExt) {
        this.ext = argExt;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String argFilename) {
        this.filename = argFilename;
    }

    public String getSdUrl() {
        return sdUrl;
    }

    public void setSdUrl(String argSdUrl) {
        this.sdUrl = argSdUrl;
    }

    public String getHdUrl() {
        return hdUrl;
    }

    public void setHdUrl(String argHdUrl) {
        this.hdUrl = argHdUrl;
    }
}
