package media;

public abstract class Media {
    private String title;
    private String category;
    private double rating;
    private int releaseYear;


    public Media(String title, int releaseYear, String category, double rating){
        this.title = title;
        this.category = category;
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
    public double getRating(){
        return rating;
    }
    public int getReleaseYear(){
        return releaseYear;
    }
}
