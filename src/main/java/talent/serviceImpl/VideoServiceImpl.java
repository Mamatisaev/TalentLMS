package talent.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import talent.entity.Video;
import talent.repository.VideoRepository;
import talent.service.VideoService;

import java.util.List;
@Service
public class VideoServiceImpl implements VideoService {

    private final VideoRepository videoRepository;

    @Autowired
    public VideoServiceImpl(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

    @Override
    public void insertVideo(Long lessonId, Video video) {
    videoRepository.insertVideo(lessonId, video);
    }

    @Override
    public List<Video> getAllVideos(Long videoId) {
        return videoRepository.getAllVideos(videoId);
    }

    @Override
    public Video getVideoById(Long videoId) {
        return videoRepository.getVideoById(videoId);
    }

    @Override
    public void editVideo(Long videoId, Video video) {
    videoRepository.editVideo(videoId, video);
    }

    @Override
    public void removeVideo(Long videoId) {
    videoRepository.removeVideo(videoId);
    }
}
