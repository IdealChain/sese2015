package sese2015.g3.goldenlion.protocol.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import sese2015.g3.goldenlion.protocol.domain.ProtocolEntry;

/**
 * Created by Mario on 02.12.2015.
 */
@Repository
public interface ProtocolEntryRepository extends PagingAndSortingRepository<ProtocolEntry, Integer> {
}
