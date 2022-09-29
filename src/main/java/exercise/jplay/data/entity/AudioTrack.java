package exercise.jplay.data.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Set;

@Table(name = "track")
@Entity
public class AudioTrack {
    @Id
    @GeneratedValue
    @Column(name = "track_id", nullable = false)
    private Long id;
    private String title;
    private String author;
    private String album;
    @Column(name = "duration_sec")
    private int durationSeconds;
    private Date releaseDate;
    private String comment;
    @Column(name = "file_path", nullable = false)
    private String filePath;
    @Column(nullable = false)
    private String fileName;
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "track_genres")
    private Set<String> genres;

    public AudioTrack() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public int getDurationSeconds() {
        return durationSeconds;
    }

    public void setDurationSeconds(int duration) {
        this.durationSeconds = duration;
    }

    public Set<String> getGenres() {
        return genres;
    }

    public void setGenres(Set<String> genres) {
        this.genres = genres;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date date) {
        this.releaseDate = date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
