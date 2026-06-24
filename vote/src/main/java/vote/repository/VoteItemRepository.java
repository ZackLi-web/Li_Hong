package vote.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vote.entity.VoteItem;

import java.util.List;

@Repository
public interface VoteItemRepository extends JpaRepository<VoteItem, Integer> {


    @Query(value = "CALL sp_get_vote_items_with_count()", nativeQuery = true)
    List<Object[]> getVoteItemsWithCountRaw();


    @Modifying
    @Query(value = "CALL sp_add_vote_item(:itemName)", nativeQuery = true)
    void addVoteItem(@Param("itemName") String itemName);
}