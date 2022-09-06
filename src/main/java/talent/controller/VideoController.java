package talent.controller;

import talent.entity.Video;
import talent.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/videos")
public class VideoController {

    private final VideoService videoService;

    @Autowired
    public VideoController(VideoService videoService) {
        this.videoService = videoService;
    }

    @GetMapping("/allVideos/{lessonId}")
    private String getAllVideos(@PathVariable("lessonId") Long lessonId, Model model) {
        model.addAttribute("allVideos", videoService.getAllVideos(lessonId));
        model.addAttribute("lesson", lessonId);
        return "video/videoPage";
    }

    @GetMapping("/{lessonId}/addVideo")
    private String addVideo(@PathVariable("lessonId") Long lessonId, Model model) {
        model.addAttribute("addVideo", new Video());
        model.addAttribute("lessonId", lessonId);
        return "video/addVideo";
    }

    @PostMapping("/{lessonId}/insertVideo")
    private String insertVideo(@PathVariable("lessonId") Long lessonId,
                             @ModelAttribute("addVideo") Video video) {
        videoService.insertVideo(lessonId, video);
        return "redirect:/videos/allVideos/ " + lessonId;
    }

    @GetMapping("/getVideo/{videoId}")
    private String getVideoById(@PathVariable("videoId") Long videoId, Model model) {
        model.addAttribute("video", videoService.getVideoById(videoId));
        return "video/videoPage";
    }

    @GetMapping("/editVideo/{videoId}")
    private String editVideo(@PathVariable("videoId") Long videoId, Model model) {
        Video video = videoService.getVideoById(videoId);
        model.addAttribute("video", video);
        model.addAttribute("lessonId", video.getLesson().getLessonId());
        return "video/editVideo";
    }

    @PostMapping("/{lessonId}/{videoId}/saveEditedVideo")
    private String saveEditedVideo(@PathVariable("lessonId") Long lessonId,
                                   @PathVariable("videoId") Long videoId,
                                   @ModelAttribute("video") Video video) {
        videoService.editVideo(videoId, video);
        return "redirect:/videos/allVideos/ " + lessonId;
    }

    @PostMapping("/{lessonId}/{videoId}/removeVideo")
    private String removeVideo(@PathVariable("lessonId") Long lessonId,
                               @PathVariable("videoId") Long videoId) {
        videoService.removeVideo(videoId);
        return "redirect:/videos/allVideos/ " + lessonId;
    }
}