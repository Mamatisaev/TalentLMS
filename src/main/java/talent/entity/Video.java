package talent.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "videos")
@Getter
@Setter
@NoArgsConstructor
public class Video {
    @Id
    @GeneratedValue(generator = "videos_sequence", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "videos_sequence", sequenceName = "videos_sequence", allocationSize = 1)
    private Long videoId;
    @Column(name = "video_name")
    private String videoName;
    @Column(unique = true)
    private String link;

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST,
            CascadeType.REFRESH, CascadeType.DETACH})
    private Lesson lesson;

    public Video(String videoName, String link) {
        this.videoName = videoName;
        this.link = link;
    }

}