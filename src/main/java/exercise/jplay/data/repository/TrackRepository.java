package exercise.jplay.data.repository;

import exercise.jplay.data.entity.Track;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TrackRepository extends CrudRepository<Track, Long> {
    List<Track> findByTitle(String title);
    Track findById(long id);

}
