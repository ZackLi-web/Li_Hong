package vote.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vote.entity.VoteRecord;

@Repository
public interface VoteRecordRepository extends JpaRepository<VoteRecord, Integer> {


    @Modifying
    @Query(value = "CALL sp_cast_vote(:voterName, :itemId)", nativeQuery = true)
    void castVote(@Param("voterName") String voterName, @Param("itemId") Integer itemId);
}
