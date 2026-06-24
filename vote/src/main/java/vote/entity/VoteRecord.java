package vote.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "vote_record")
public class VoteRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "record_id")
    private Integer recordId;

    @Column(name = "voter_name", nullable = false, length = 50)
    private String voterName;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id", nullable = false)
    private VoteItem voteItem;

    @Column(name = "voted_at", insertable = false, updatable = false)
    private LocalDateTime votedAt;


}
