package talent.repositoryImpl;

import org.springframework.stereotype.Repository;
import talent.entity.Instructor;
import talent.entity.Lesson;
import talent.entity.Video;
import talent.repository.VideoRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.List;

@Repository
@Transactional
public class VideoRepositoryImpl implements VideoRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void insertVideo(Long lessonId, Video video) {
        Lesson lesson = entityManager.find(Lesson.class, lessonId);
        lesson.setVideo(video);
        video.setLesson(lesson);
        entityManager.persist(video);
    }

    @Override
    public List<Video> getAllVideos(Long videoId) {
        List<Video> videos = entityManager
                .createQuery("SELECT v FROM Video v WHERE v.lesson.lessonId = : videoId", Video.class)
                .setParameter("videoId", videoId).getResultList();
        Comparator<Video> comparator = ((o1, o2) -> (int) (o1.getVideoId() - o2.getVideoId()));
        videos.sort(comparator);
        return videos;
    }

    @Override
    public Video getVideoById(Long videoId) {
        return entityManager.find(Video.class, videoId);
    }

    @Override
    public void editVideo(Long videoId, Video video) {
        Video video1 = entityManager.find(Video.class, videoId);
        video1.setVideoName(video.getVideoName());
        video1.setLink(video.getLink());
        entityManager.merge(video1);
    }

    @Override
    public void removeVideo(Long videoId) {
        Video video = entityManager.find(Video.class, videoId);
        video.setLesson(null);
        entityManager.remove(video);
    }
}
