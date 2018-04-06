package ncec.cfweb;

import java.util.Date;

/**
 *
 * @author DantalioNxxi
 */
public class MovieForm {
    
    //пока не все поля

    private String title;

    private Date dateCreation;

    private int duration;

    private String description;

    public MovieForm() {
    }

    public MovieForm(String title, Date dateCreation, int duration, String description) {
        this.title = title;
        this.dateCreation = dateCreation;
        this.duration = duration;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
    
}
