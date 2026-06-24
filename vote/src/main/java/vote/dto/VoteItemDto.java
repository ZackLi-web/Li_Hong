package vote.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoteItemDto {
    private Integer itemId;
    private String itemName;
    private Long voteCount;
    }