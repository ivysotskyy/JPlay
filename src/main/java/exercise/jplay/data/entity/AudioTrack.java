package exercise.jplay.data.entity;

import jakarta.persistence.*;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Objects;
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
    @Column(nullable = true)
    private String fileName;
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "track_genres", joinColumns = @JoinColumn(name = "track_id"))
    private Set<String> genres;

    public AudioTrack() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        AudioTrack track = (AudioTrack) o;
        return Objects.equals(filePath, track.getFilePath()) &&
                Objects.equals(title, track.getTitle()) &&
                Objects.equals(author, track.getAuthor()) &&
                Objects.equals(album, track.album);
    }

    @Override
    public int hashCode() {
        return Objects.hash(filePath, title, author, album);
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

    /**
     * Overloads standard getter to provide some
     * view formatting.
     *
     * @param format Date format patter
     * @return String representation of the Release date
     */
    public String getReleaseDate(String format) {
        if (releaseDate == null)
            return "N/A";
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(releaseDate);
    }

    public void setReleaseDate(Date date) {
        releaseDate = date;
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
