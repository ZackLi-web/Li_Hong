package vote.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vote.entity.VoteItem;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoteRecordDto {
    private Integer recordId;
    private String voterName;
    private VoteItem voteItem;
}
