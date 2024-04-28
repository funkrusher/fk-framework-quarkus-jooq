package org.fk.core.dao;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.TestProfile;
import jakarta.inject.Inject;
import org.fk.core.request.RequestContext;
import org.fk.core.test.CoreTestLifecycleManager;
import org.fk.core.test.CoreTestProfile;
import org.fk.core.test.CoreTestUtil;
import org.fk.core.test.InjectCoreTestUtil;
import org.fk.core.ulid.UlidGenerator;
import org.fk.coreTestDatabase.CoreTestDatabase;
import org.fk.coreTestDatabase.coretestdatabase.tables.Post;
import org.fk.coreTestDatabase.coretestdatabase.tables.records.PostRecord;
import org.jooq.DSLContext;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static java.util.Optional.empty;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * PostDAOTest
 */
@QuarkusTest
@TestProfile(CoreTestProfile.class)
@QuarkusTestResource(CoreTestLifecycleManager.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PostDAOTest {

    @InjectCoreTestUtil
    static CoreTestUtil testDbUtil;

    @Inject
    CoreTestDatabase database;

    private DSLContext dsl;

    @BeforeEach
    public void setup() {
        RequestContext request = new RequestContext(1, 1);
        this.dsl = database.dsl(request);
    }

    private PostRecord createTestRecord(Optional<UUID> maybePostId) {
        PostRecord postRecord = new PostRecord();
        maybePostId.ifPresent(postRecord::setId);
        postRecord.setTitle("test123");
        return postRecord;
    }

    private PostDTO createTestDTO(Optional<UUID> maybePostId) {
        PostDTO postDTO = new PostDTO();
        maybePostId.ifPresent(postDTO::setId);
        postDTO.setTitle("test123");
        return postDTO;
    }

    private void validateRecordEqual(PostRecord expectedRecord, PostRecord existingRecord) {
        assertNotNull(expectedRecord);
        assertNotNull(existingRecord);
        assertEquals(expectedRecord.getId(), existingRecord.getId());
        assertEquals(expectedRecord.getTitle(), existingRecord.getTitle());
    }

    private void validateDTOEqual(PostDTO expectedDTO, PostRecord existingRecord) {
        assertNotNull(expectedDTO);
        assertNotNull(existingRecord);
        assertEquals(expectedDTO.getId(), existingRecord.getId());
        assertEquals(expectedDTO.getTitle(), existingRecord.getTitle());
    }

    private List<PostRecord> resolveRecordsFromDb(UUID... ids) {
        return dsl.select()
                .from(Post.POST)
                .where(Post.POST.ID.in(List.of(ids)))
                .fetchInto(PostRecord.class);
    }

    private void assertCount(long expectedCount) {
        int count = dsl.fetchCount(dsl.selectFrom(Post.POST));
        assertEquals(count, expectedCount);
    }


    @Test
    @Order(1)
    public void testInsertRecordFixedId() throws IOException {
        PostDAO postDAO = new PostDAO(dsl);

        PostRecord postRecord = createTestRecord(Optional.of(UlidGenerator.createMariadbUuid()));
        int result = postDAO.insert(postRecord);
        assertEquals(result, 1);

        PostRecord existingRecord = resolveRecordsFromDb(postRecord.getId()).getFirst();
        validateRecordEqual(postRecord, existingRecord);

        assertCount(1);
    }

    @Test
    @Order(2)
    public void testInsertRecordsFixedId() throws IOException {
        PostDAO postDAO = new PostDAO(dsl);

        UUID id1 = UlidGenerator.createMariadbUuid();
        UUID id2 = UlidGenerator.createMariadbUuid();
        UUID id3 = UlidGenerator.createMariadbUuid();

        PostRecord postRecord1 = createTestRecord(Optional.of(id1));
        PostRecord postRecord2 = createTestRecord(Optional.of(id2));
        PostRecord postRecord3 = createTestRecord(Optional.of(id3));

        int result = postDAO.insert(List.of(postRecord1, postRecord2, postRecord3));
        assertEquals(result, 3);

        List<PostRecord> existingRecords = resolveRecordsFromDb(id1, id2, id3);
        assertEquals(existingRecords.size(), 3);
        validateRecordEqual(postRecord1, existingRecords.get(0));
        validateRecordEqual(postRecord2, existingRecords.get(1));
        validateRecordEqual(postRecord3, existingRecords.get(2));

        assertCount(4);
    }


    @Test
    @Order(3)
    public void testInsertRecordGenId() throws IOException {
        PostDAO postDAO = new PostDAO(dsl);

        PostRecord postRecord = createTestRecord(empty());
        int result = postDAO.insert(postRecord);
        assertEquals(result, 1);

        PostRecord existingRecord = resolveRecordsFromDb(postRecord.getId()).getFirst();
        validateRecordEqual(postRecord, existingRecord);

        assertCount(5);
    }

    @Test
    @Order(4)
    public void testInsertRecordsGenId() throws IOException {
        PostDAO postDAO = new PostDAO(dsl);

        PostRecord postRecord1 = createTestRecord(empty());
        PostRecord postRecord2 = createTestRecord(empty());
        PostRecord postRecord3 = createTestRecord(empty());

        int result = postDAO.insert(List.of(postRecord1, postRecord2, postRecord3));
        assertEquals(result, 3);

        List<PostRecord> existingRecords = resolveRecordsFromDb(postRecord1.getId(), postRecord2.getId(), postRecord3.getId());
        assertEquals(existingRecords.size(), 3);
        validateRecordEqual(postRecord1, existingRecords.get(0));
        validateRecordEqual(postRecord2, existingRecords.get(1));
        validateRecordEqual(postRecord3, existingRecords.get(2));

        assertCount(8);
    }

    @Test
    @Order(5)
    public void testInsertDTOFixedId() throws IOException {
        PostDAO postDAO = new PostDAO(dsl);

        PostDTO postDTO = createTestDTO(Optional.of(UlidGenerator.createMariadbUuid()));
        int result = postDAO.insert(postDTO);
        assertEquals(result, 1);

        PostRecord existingRecord = resolveRecordsFromDb(postDTO.getId()).getFirst();
        validateDTOEqual(postDTO, existingRecord);

        assertCount(9);
    }

    @Test
    @Order(6)
    public void testInsertDTOsFixedId() throws IOException {
        PostDAO postDAO = new PostDAO(dsl);

        UUID id1 = UlidGenerator.createMariadbUuid();
        UUID id2 = UlidGenerator.createMariadbUuid();
        UUID id3 = UlidGenerator.createMariadbUuid();

        PostDTO postDTO1 = createTestDTO(Optional.of(id1));
        PostDTO postDTO2 = createTestDTO(Optional.of(id2));
        PostDTO postDTO3 = createTestDTO(Optional.of(id3));

        int result = postDAO.insert(List.of(postDTO1, postDTO2, postDTO3));
        assertEquals(result, 3);

        List<PostRecord> existingRecords = resolveRecordsFromDb(id1, id2, id3);
        assertEquals(existingRecords.size(), 3);
        validateDTOEqual(postDTO1, existingRecords.get(0));
        validateDTOEqual(postDTO2, existingRecords.get(1));
        validateDTOEqual(postDTO3, existingRecords.get(2));

        assertCount(12);
    }

    @Test
    @Order(7)
    public void testInsertDTOGenId() throws IOException {
        PostDAO postDAO = new PostDAO(dsl);

        PostDTO postDTO = createTestDTO(empty());
        int result = postDAO.insert(postDTO);
        assertEquals(result, 1);

        PostRecord existingRecord = resolveRecordsFromDb(postDTO.getId()).getFirst();
        validateDTOEqual(postDTO, existingRecord);

        assertCount(13);
    }

    @Test
    @Order(8)
    public void testInsertDTOsGenId() throws IOException {
        PostDAO postDAO = new PostDAO(dsl);

        PostDTO postDTO1 = createTestDTO(empty());
        PostDTO postDTO2 = createTestDTO(empty());
        PostDTO postDTO3 = createTestDTO(empty());

        int result = postDAO.insert(List.of(postDTO1, postDTO2, postDTO3));
        assertEquals(result, 3);

        List<PostRecord> existingRecords = resolveRecordsFromDb(postDTO1.getId(), postDTO2.getId(), postDTO3.getId());
        assertEquals(existingRecords.size(), 3);
        validateDTOEqual(postDTO1, existingRecords.stream().filter(x -> x.getId().equals(postDTO1.getId())).toList().getFirst());
        validateDTOEqual(postDTO2, existingRecords.stream().filter(x -> x.getId().equals(postDTO2.getId())).toList().getFirst());
        validateDTOEqual(postDTO3, existingRecords.stream().filter(x -> x.getId().equals(postDTO3.getId())).toList().getFirst());

        assertCount(16);
    }

}