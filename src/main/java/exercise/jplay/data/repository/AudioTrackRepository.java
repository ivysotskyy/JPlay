package exercise.jplay.data.repository;

import exercise.jplay.data.entity.AudioTrack;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@ComponentScan
public interface AudioTrackRepository extends CrudRepository<AudioTrack, Long> {

    AudioTrack findById(long id);
    @Query("SELECT t from AudioTrack t WHERE t.title=:title")
    List<AudioTrack> findByTitle(@Param("title") String title);
    List<AudioTrack> findByAuthor(String artist);

}
