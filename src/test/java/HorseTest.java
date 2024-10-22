import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.ArgumentMatchers;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;

//test{Method}_Should{Do}_When{Condition}
class HorseTest {
    double speed = 10;
    String name = "Bucefal";
    double distance = 20;

    @Test
    void testConstructor_ShouldThrowException_WhenArgsNameIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new Horse(null, speed));
    }

    @Test
    void testConstructor_ShouldExceptionMessage_WhenArgsNameIsNull() {
        String expected = "Name cannot be null.";

        var exception = assertThrows(IllegalArgumentException.class, () -> new Horse(null, speed));
        String actual = exception.getMessage();

        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\n", "\t", "\r", "\f"})
    void testConstructor_ShouldThrowException_WhenArgsNameIsBlank(String name) {
        assertThrows(IllegalArgumentException.class, () -> new Horse(name, speed));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\n", "\t", "\r", "\f"})
    void testConstructor_ShouldExceptionMessage_WhenArgsNameIsBlank(String name) {
        String expected = "Name cannot be blank.";

        var exception = assertThrows(IllegalArgumentException.class, () -> new Horse(name, speed));
        String actual = exception.getMessage();

        assertEquals(expected, actual);
    }

    @Test
    void testConstructor_ShouldThrowException_WhenArgsSpeedIsNegative() {
        assertThrows(IllegalArgumentException.class, () -> new Horse(name, -1));
    }

    @Test
    void testConstructor_ShouldExceptionMessage_WhenArgsSpeedIsNegative() {
        String expected = "Speed cannot be negative.";

        var exception = assertThrows(IllegalArgumentException.class, () -> new Horse(name, -1));
        String actual = exception.getMessage();

        assertEquals(expected, actual);
    }

    @Test
    void testConstructor_ShouldThrowException_WhenArgsDistanceIsNegative() {
        assertThrows(IllegalArgumentException.class, () -> new Horse(name, speed, -1));
    }

    @Test
    void testConstructor_ShouldExceptionMessage_WhenArgsDistanceIsNegative() {
        String expected = "Distance cannot be negative.";

        var exception = assertThrows(IllegalArgumentException.class, () -> new Horse(name, speed, -1));
        String actual = exception.getMessage();

        assertEquals(expected, actual);
    }


    @Test
    void testGetter_ShouldGetName_WhenUseGetterName() {
        assertEquals("Bucefal", new Horse(name, speed).getName());
    }

    @Test
    @SneakyThrows
    void testGetterName_UsingReflection() {
        String expected = "Bucefal";

        Horse horse = new Horse(name, speed);
        Field field = Horse.class.getDeclaredField("name");
        field.setAccessible(true);
        String actual = (String) field.get(horse);

        assertEquals(expected, actual);

    }

    @Test
    void testGetter_ShouldGetSpeed_WhenUseGetterSpeed() {
        assertEquals(10, new Horse(name, speed).getSpeed());
    }

    @Test
    @SneakyThrows
    void testGetterSpeed_UsingReflection() {
        double expected = 10;

        Horse horse = new Horse(name, speed);
        Field field = Horse.class.getDeclaredField("speed");
        field.setAccessible(true);
        double actual = (double) field.get(horse);

        assertEquals(expected, actual);
    }

    @Test
    void testGetter_ShouldGetDistance_WhenUseGetterDistance() {
        assertEquals(20, new Horse(name, speed, distance).getDistance());
    }

    @Test
    @SneakyThrows
    void testGetterDistance_UsingReflection() {
        double expected = 20;

        Horse horse = new Horse(name, speed, distance);
        Field field = Horse.class.getDeclaredField("distance");
        field.setAccessible(true);
        double actual = (double) field.get(horse);

        assertEquals(expected, actual);
    }


    @Test
    void testGetterWithTwoParam() {
        assertEquals(0, new Horse("test", 20).getDistance());
    }

    @Test
    @SneakyThrows
    void testGetterDistance_UsingReflection_WhenTwoParams() {
        double expected = 0;

        Horse horse = new Horse(name, speed);
        Field field = Horse.class.getDeclaredField("distance");
        field.setAccessible(true);
        double actual = (double) field.get(horse);

        assertEquals(expected, actual);
    }


    @Test
    void testMove_ShouldCheckMethodGetRandomDouble_WhenUseMethodMove() {
        try (MockedStatic<Horse> mockStatic = Mockito.mockStatic(Horse.class)) {
            new Horse(name, speed, 0).move();

            mockStatic.verify(() -> Horse.getRandomDouble(eq(0.2), eq(0.9)));
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = {0, 0.2, 0.3, 0.7, 1, 10, 100})
    void testMove_ShouldAccendingValue_WhenGetRandomDoubleArgs0dot2and0dot9(double fakeValue) {
        Horse horse = new Horse(name, speed, distance);

        double expectedDistance = horse.getDistance() + horse.getSpeed() * fakeValue;

        try (MockedStatic<Horse> mockedStatic = Mockito.mockStatic(Horse.class)) {
            mockedStatic.when(() -> Horse.getRandomDouble(0.2,0.9)).thenReturn(fakeValue);
            horse.move();
            double actualDistance = horse.getDistance();

            assertEquals(expectedDistance,actualDistance);
        }

    }
}