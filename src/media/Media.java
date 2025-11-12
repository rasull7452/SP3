package media;

public abstract class Media {
    private String title;
    private String category;
    private int duration;
    private int rating;
    private int releaseYear;


    public Media(String title, String category, int duration, int rating, int releaseYear){
        this.title = title;
        this.category = category;
        this.duration = duration;
        this.rating = rating;
        this.releaseYear = releaseYear;
    }

    public String getTitleName(){
        return title;
    }
    public String getCategoryName(){
        return category;
    }
    public String getCategory(){
        return category;
    }
    public int getDuration(){
        return duration;
    }
    public int getRating(){
        return rating;
    }
    public int getReleaseYear(){
        return releaseYear;
    }
}
