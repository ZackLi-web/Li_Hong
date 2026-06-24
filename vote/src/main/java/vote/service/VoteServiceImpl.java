package vote.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.HtmlUtils;
import vote.dto.VoteItemDto;
import vote.dto.VoteCastDto;
import vote.repository.VoteItemRepository;
import vote.repository.VoteRecordRepository;
import vote.service.VoteService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VoteServiceImpl implements VoteService {

    private final VoteItemRepository voteItemRepository;
    private final VoteRecordRepository voteRecordRepository;

    @Override
    @Transactional
    public List<VoteItemDto> getAllItemsWithCount() {
        List<Object[]> rawList = voteItemRepository.getVoteItemsWithCountRaw();
        List<VoteItemDto> dtoList = new ArrayList<>();

        for (Object[] row : rawList) {
            Integer itemId = row[0] != null ? ((Number) row[0]).intValue() : null;
            String itemName = row[1] != null ? row[1].toString() : "";
            Long voteCount = row[2] != null ? ((Number) row[2]).longValue() : 0L;

            dtoList.add(new VoteItemDto(itemId, itemName, voteCount));
        }

        return dtoList;
    }

    @Override
    @Transactional
    public void createVoteItem(String itemName) {
        // 防止 XSS 攻擊
        String cleanItemName = HtmlUtils.htmlEscape(itemName);
        voteItemRepository.addVoteItem(cleanItemName);
    }

    @Override
    @Transactional(rollbackFor = Exception.class) // 多選投票事務控制
    public void processVoting(VoteCastDto request) {
        // 防止 XSS 攻擊
        String cleanVoterName = HtmlUtils.htmlEscape(request.getVoterName());

        if (request.getItemIds() == null || request.getItemIds().isEmpty()) {
            throw new IllegalArgumentException("請至少選擇一個投票項目進行投票");
        }

        // 走訪多選項目 ID，寫入紀錄
        for (Integer itemId : request.getItemIds()) {
            voteRecordRepository.castVote(cleanVoterName, itemId);
        }
    }
}