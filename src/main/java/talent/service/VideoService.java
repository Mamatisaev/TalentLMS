package talent.service;

import talent.entity.Video;

import java.util.List;

public interface VideoService {

    void insertVideo(Long lessonId, Video video);

    List<Video> getAllVideos(Long videoId);

    Video getVideoById(Long videoId);

    void editVideo(Long videoId, Video video);

    void removeVideo(Long videoId);

}
