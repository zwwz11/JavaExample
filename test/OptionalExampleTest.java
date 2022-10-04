
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

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

    static Integer key = 0;
    private Integer getKey() {
        key = 0;
        return key++;
    }
}