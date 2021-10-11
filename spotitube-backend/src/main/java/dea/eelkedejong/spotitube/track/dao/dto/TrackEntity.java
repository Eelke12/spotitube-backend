package dea.eelkedejong.spotitube.track.dao.dto;

import java.util.Date;

public class TrackEntity {
    private int id;
    private String title;
    private String performer;
    private int duration;
    private String album;
    private int playcount;
    private Date publicationDate;
    private String description;
    private int offlineAvailable;

    public TrackEntity(int id,
                       String title,
                       String performer,
                       int duration,
                       String album,
                       int playcount,
                       Date publicationDate,
                       String description,
                       int offlineAvailable) {
        this.id = id;
        this.title = title;
        this.performer = performer;
        this.duration = duration;
        this.album = album;
        this.playcount = playcount;
        this.publicationDate = publicationDate;
        this.description = description;
        this.offlineAvailable = offlineAvailable;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getPerformer() {
        return performer;
    }

    public int getDuration() {
        return duration;
    }

    public String getAlbum() {
        return album;
    }

    public int getPlaycount() {
        return playcount;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public String getDescription() {
        return description;
    }

    public int getOfflineAvailable() {
        return offlineAvailable;
    }
}
