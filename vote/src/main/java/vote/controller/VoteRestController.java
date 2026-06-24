package vote.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vote.dto.VoteItemDto;
import vote.dto.VoteCastDto;
import vote.service.VoteService;

import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/api/votes")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class VoteRestController {

    private final VoteService voteService;


    @GetMapping
    public ResponseEntity<List<VoteItemDto>> getVoteItems() {
        List<VoteItemDto> items = voteService.getAllItemsWithCount();
        return ResponseEntity.ok(items);
    }


    @PostMapping("/item")
    public ResponseEntity<?> addVoteItem(@RequestBody Map<String, String> payload) {
        String itemName = payload.get("itemName");
        if (itemName == null || itemName.trim().isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "投票項目名稱不能為空"));
        }

        voteService.createVoteItem(itemName);
        return ResponseEntity.ok(Map.of("message", "投票項目新增成功"));
    }


    @PostMapping("/cast")
    public ResponseEntity<?> castVote(@RequestBody VoteCastDto request) {
        if (request.getVoterName() == null || request.getVoterName().trim().isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "投票人姓名不能為空"));
        }

        try {
            voteService.processVoting(request);
            return ResponseEntity.ok(Map.of("message", "投票成功！"));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(Map.of("error", "系統處理錯誤: " + e.getMessage()));
        }
    }
}
