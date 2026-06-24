package vote.service;

import org.springframework.stereotype.Service;
import vote.dto.VoteCastDto;
import vote.dto.VoteItemDto;

import java.util.List;

@Service
public interface VoteService {
    public List<VoteItemDto> getAllItemsWithCount();
    public void createVoteItem(String itemName);
    public void processVoting(VoteCastDto request);
}
