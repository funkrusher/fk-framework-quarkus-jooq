package org.fk.core.dto;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.TestProfile;
import org.fk.core.exception.InvalidDataException;
import org.fk.core.exception.MappingException;
import org.fk.core.query.jooq.FkQueryJooqMapper;
import org.fk.core.query.model.*;
import org.fk.core.test.CoreTestLifecycleManager;
import org.fk.core.test.CoreTestProfile;
import org.fk.core.test.database.coretestdatabase.tables.Basic1;
import org.fk.core.test.database.coretestdatabase.tables.Nested1;
import org.fk.core.test.database.coretestdatabase.tables.dtos.Basic1Dto;
import org.fk.core.test.database.coretestdatabase.tables.dtos.Basic2Dto;
import org.fk.core.test.database.coretestdatabase.tables.dtos.Nested1Dto;
import org.fk.core.test.database.coretestdatabase.tables.interfaces.IBasic1;
import org.fk.core.test.database.coretestdatabase.tables.interfaces.IBasic2;
import org.fk.core.test.database.coretestdatabase.tables.interfaces.INested1;
import org.jooq.*;
import org.jooq.impl.DSL;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
@TestProfile(CoreTestProfile.class)
@QuarkusTestResource(CoreTestLifecycleManager.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BookKeeperTest {
    @BeforeEach
    void setup() {
    }

    @Test
    void testBasics() {
        Basic1DTO basic1Dto = new Basic1DTO();
        Nested1DTO nested1Dto1 = new Nested1DTO();
        Nested1DTO nested1Dto2 = new Nested1DTO();
        BookKeeper basic1Keeper = basic1Dto.getBookKeeper();
        BookKeeper nested1Keeper1 = nested1Dto1.getBookKeeper();
        BookKeeper nested1Keeper2 = nested1Dto2.getBookKeeper();

        // validate keeper directly after initialization of dto
        assertEquals(0, basic1Keeper.touched().size());
        assertEquals(0, nested1Keeper1.touched().size());
        assertEquals(0, nested1Keeper2.touched().size());

        // add nested list to main-dto and validate this.
        List<Nested1DTO> nestedDtos = List.of(nested1Dto1, nested1Dto2);
        List<String> list = List.of("xyz", "abc");
        Map<String, String> map = Map.of("key", "value");
        basic1Dto.setNestedDtos(nestedDtos);
        basic1Dto.setList(list);
        basic1Dto.setMap(map);
        assertEquals(3, basic1Keeper.touched().size());
        assertEquals(nestedDtos, basic1Keeper.touched().get("nestedDtos"));
        assertEquals(list, basic1Keeper.touched().get("list"));
        assertEquals(map, basic1Keeper.touched().get("map"));

        // validate touch-functionality basics
        basic1Dto.setString1("test1");
        basic1Dto.setString2("test2");
        assertEquals("test1", basic1Keeper.touched().get("string1"));
        assertEquals("test2", basic1Keeper.touched().get("string2"));
        assertEquals(5, basic1Keeper.touched().size());

        nested1Dto1.setDecimal1(new BigDecimal("10.00"));
        nested1Dto1.setLong1(1L);
        assertEquals(new BigDecimal("10.00"), nested1Keeper1.touched().get("decimal1"));
        assertEquals(1L, nested1Keeper1.touched().get("long1"));
        assertEquals(2, nested1Keeper1.touched().size());

        // toString
        String toString1 = """
                {
                    string1: test1,
                    string2: test2,
                    nestedDtos: [
                        long1: 1,
                        decimal1: 10.00,
                    ]
                    list: [
                        xyz,
                        abc,
                    ]
                    map: {
                        key: value,
                    }
                }""";
        assertEquals(toString1, basic1Dto.toString());

        // hashCode
        int hashCode = basic1Dto.hashCode();
        assertEquals(hashCode, -1161809942);

        // equals
        assertEquals(basic1Dto, basic1Dto);
        assertEquals(nested1Dto1, nested1Dto1);
        assertEquals(nested1Dto2, nested1Dto2);

        assertNotEquals(basic1Dto, nested1Dto1);
        assertNotEquals(nested1Dto1, basic1Dto);
        assertNotEquals(basic1Dto, nested1Dto2);
        assertNotEquals(nested1Dto2, basic1Dto);
        assertNotEquals(nested1Dto1, nested1Dto2);
        assertNotEquals(nested1Dto2, nested1Dto1);

        Nested1AlmostSimilar1DTO almost1 = new Nested1AlmostSimilar1DTO();
        Nested1AlmostSimilar2DTO almost2 = new Nested1AlmostSimilar2DTO();
        almost1.setDiff1("d1");
        almost2.setDiff2("d1");
        assertNotEquals(almost1, almost2);
        assertNotEquals(almost2, almost1);
        assertFalse(almost1.getBookKeeper().touchedEquals(almost2));
        assertFalse(almost2.getBookKeeper().touchedEquals(almost1));

        Basic1DTO compare1 = new Basic1DTO();
        assertNotEquals(basic1Dto, compare1);

        Basic1DTO compare2 = new Basic1DTO();
        compare2.setString1("test1");
        compare2.setString2("test2");
        assertNotEquals(basic1Dto, compare2);

        Basic1DTO compare3 = new Basic1DTO();
        compare3.setString1("test1");
        compare3.setString2("test2");
        Nested1DTO nestedCompare1 = new Nested1DTO();
        nestedCompare1.setDecimal1(new BigDecimal("10.00"));
        nestedCompare1.setLong1(1L);
        Nested1DTO nestedCompare2 = new Nested1DTO();
        List<Nested1DTO> compareNestedDtos = List.of(nestedCompare1, nestedCompare2);
        List<String> compareList = List.of("xyz", "abc");
        Map<String, String> compareMap = Map.of("key", "value");
        compare3.setNestedDtos(compareNestedDtos);
        compare3.setList(compareList);
        compare3.setMap(compareMap);
        assertEquals(basic1Dto, compare3);
        assertEquals(compare3, basic1Dto);

        compare3.setString1("test2");
        assertNotEquals(basic1Dto, compare3);
        assertNotEquals(compare3, basic1Dto);

        compare3.setString1(null);
        assertNotEquals(basic1Dto, compare3);
        assertNotEquals(compare3, basic1Dto);

        // some errors
        assertThrows(MappingException.class, () -> {
            basic1Keeper.touch("typo");
        });
        assertThrows(MappingException.class, () -> {
            basic1Keeper.touch("existingButInvalidSetter");
        });

        // test reset bookkeeper
        basic1Keeper.resetTouched();
        nested1Keeper1.resetTouched();
        nested1Keeper2.resetTouched();
        assertEquals(0, basic1Keeper.touched().size());
        assertEquals(0, nested1Keeper1.touched().size());
        assertEquals(0, nested1Keeper2.touched().size());
    }

    // -------------------------------------------------------------------------
    // Helper-functions and classes
    // -------------------------------------------------------------------------

    /**
     * Basic1DTO
     */
    public static class Basic1DTO extends Basic1Dto implements IBasic1 {

        private List<Nested1DTO> nestedDtos = new ArrayList<>();

        private List<String> list = new ArrayList<>();

        private Map<String, String> map = new HashMap<>();

        private String existingButInvalidSetter;

        public Basic1DTO setNestedDtos(List<Nested1DTO> nestedDtos) {
            this.nestedDtos = nestedDtos;
            this.keeper.touch("nestedDtos");
            return this;
        }

        public List<Nested1DTO> getNestedDtos() {
            return this.nestedDtos;
        }

        public Basic1DTO setList(List<String> list) {
            this.list = list;
            this.keeper.touch("list");
            return this;
        }

        public List<String> getList() {
            return this.list;
        }

        public Basic1DTO setMap(Map<String, String> map) {
            this.map = map;
            this.keeper.touch("map");
            return this;
        }

        public Map<String, String> getMap() {
            return this.map;
        }
    }

    /**
     * Nested1DTO
     */
    public static class Nested1DTO extends Nested1Dto implements INested1 {
    }

    /**
     * Nested1AlmostSimilar1DTO
     */
    public static class Nested1AlmostSimilar1DTO extends Nested1Dto implements INested1 {
        private String diff1;

        public Nested1AlmostSimilar1DTO setDiff1(String diff1) {
            this.diff1 = diff1;
            this.keeper.touch("diff1");
            return this;
        }

        public String getDiff1() {
            return this.diff1;
        }
    }

    /**
     * Nested1AlmostSimilar2DTO
     */
    public static class Nested1AlmostSimilar2DTO extends Nested1Dto implements INested1 {
        private String diff2;

        public Nested1AlmostSimilar2DTO setDiff2(String diff2) {
            this.diff2 = diff2;
            this.keeper.touch("diff2");
            return this;
        }

        public String getDiff2() {
            return this.diff2;
        }
    }
}