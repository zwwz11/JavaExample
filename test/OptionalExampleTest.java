
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OptionalExampleTest {

    @Test
    @DisplayName("Optional.Of(null)일 경우 NPE 에러 테스트")
    void optionalOfNullTest() {
        // given
        String str = null;

        // then
        assertThrows(NullPointerException.class, () -> Optional.of(str));
    }

    @Test
    @DisplayName("Optional.OfNullable(null)일 때 empty 반환 테스트")
    void optionalOfNullableTest() {
        //given
        String str = null;

        // when
        Optional<String> optional = Optional.ofNullable(str);

        // then
        assertEquals(optional, Optional.empty());
    }

    @Test
    @DisplayName("ofNullable(null)일 때 orElseGet을 통해 No Name 반환 테스트")
    void optionalOfNullableOrElseGetTest() {
        //given
        String str = null;

        // when
        String result = Optional.ofNullable(str).orElseGet(() -> "No Name");

        //then
        assertEquals(result, "No Name");
    }

    @Test
    @DisplayName("orElse(null) 테스트")
    void orElseIsNullTest() {
        //given
        Integer value = null;

        // when
        Integer result = Optional.ofNullable(value).orElse(getKey());

        //then
        assertEquals(this.key, 1);
    }

    @Test
    @DisplayName("orElse(not null) 테스트")
    void orElseNotNullTest() {
        //given
        Integer value = 0;

        // when
        Integer result = Optional.ofNullable(value).orElse(getKey());

        //then
        assertEquals(this.key, 1);
    }

    @Test
    @DisplayName("orElseGet(null) 테스트")
    void orElseGetIsNullTest() {
        //given
        Integer value = null;

        // when
        Integer result = Optional.ofNullable(value).orElseGet(() -> getKey());

        // then
        assertEquals(this.key, 1);
    }

    @Test
    @DisplayName("orElseGet(not null) 테스트")
    void orElseGetNotNullTest() {
        //given
        Integer value = 0;

        // when
        Integer result = Optional.ofNullable(value).orElseGet(() -> getKey());

        // then
        assertEquals(result, 0);
    }

    @Test
    @DisplayName("orElseThrow 테스트")
    void orElseThrowTest() {
        //given
        String value = null;

        //then
        assertThrows(Exception.class, () -> Optional.ofNullable(value).orElseThrow(() -> new Exception("Error!")));
    }

    @Test
    @DisplayName("stream filter findFirst Not Null 테스트")
    void streamFilterNotNullTest() {
        //given
        List<String> names = getNames();

        //when
        Optional<String> findName = names.stream().filter(name -> name.equals("Han")).findFirst();
        String name = findName.orElseGet(() -> "No Name");

        //then
        assertEquals(name, "Han");
    }

    @Test
    @DisplayName("stream filter findfirst Null 테스트")
    void streamFilterIsNullTest() {
        //given
        List<String> names = getNames();

        //when
        Optional<String> findName = names.stream().filter(name -> name.equals("EMPTH")).findFirst();
        String name = findName.orElseGet(() -> "No Name");

        //then
        assertEquals(name, "No Name");
    }

    @Test
    @DisplayName("Class To List By Name 테스트")
    void classToListByNameTest() {
        //given
        List<Human> humans = getHumans();

        //when
        List<String> names = humans.stream().map(human -> human.getName()).collect(Collectors.toList());

        //then
        assertEquals(humans.stream().count(), names.stream().count());
    }

    @Test
    @DisplayName("filter and Map 테스트")
    void streamFilterMapTest() {
        //given
        List<Human> humans = getHumans();

        //when
        List<String> names = humans.stream()
                .filter(human -> human.getAge() >= 20)
                .map(Human::getName)
                .map(String::toUpperCase)
                .collect(Collectors.toList());

        boolean isUpper = false;
        for (String name : names) {
            for (Character c : name.toCharArray()) {
                isUpper = Character.isUpperCase(c);
            }
        }

        //then
        assertEquals(isUpper, true);
    }

    @Test
    @DisplayName("String.join 테스트")
    void stringJoinTest() {
        //given
        List<String> names = Arrays.asList("cho", "han", "sol");

        //when
        String joinString = String.join(",", names);
        System.out.println("joinString = " + joinString);

        //then
        assertEquals(joinString, "cho,han,sol");
    }



    static Integer key = 0;
    private Integer getKey() {
        key = 0;
        return key++;
    }

    private List<String> getNames() {
        return Arrays.asList("Han", "Cho", "Sol", "Kan", "Faker");
    }

    private List<Human> getHumans() {
        Human humanA = new Human("hansol", 30);
        Human humanB = new Human("huni", 22);
        Human humanC = new Human("korean", 21);
        Human humanD = new Human("whatthe", 10);
        return Arrays.asList(humanA, humanB, humanC, humanD);
    }

    @Getter
    @Setter
    class Human {
        public Human(String name, Integer age) {
            this.name = name;
            this.age = age;
        }
        String name;
        Integer age;
    }

}