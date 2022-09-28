package exercise.jplay.data.repository;

import exercise.jplay.data.entity.AudioTrack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AudioTrackRepository extends JpaRepository<AudioTrack, Long> {

    AudioTrack findById(long id);

    @Query("SELECT t from AudioTrack t WHERE t.title=:title")
    List<AudioTrack> findByTitle(@Param("title") String title);

    List<AudioTrack> findByAuthor(String artist);

}
