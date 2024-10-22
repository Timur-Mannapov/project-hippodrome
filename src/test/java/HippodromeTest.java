import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class HippodromeTest {
    Horse horse = new Horse("test", 2, 2);


    @Test
    void testConstructor_ShouldThrowIAE_WhenArgsIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
    }

    @Test
    void testConstructor_ShouldExceptionMessage_WhenArgsIsNull() {
        String expected = "Horses cannot be null.";

        var exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
        String actual = exception.getMessage();

        assertEquals(expected, actual);
    }

    @Test
    void testConstructor_ShouldThrowIAE_WhenListIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(List.of()));
    }

    @Test
    void testConstructor_ShouldExceptionMessage_WhenListIsEmpty() {
        String expected = "Horses cannot be empty.";

        var exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(List.of()));
        String actual = exception.getMessage();

        assertEquals(expected, actual);
    }

    @Test
    void testGetHorses_ShouldReturnList_WhenUseMethodGetHorses() {
        List<Horse> horses = IntStream.range(0, 30).mapToObj(i -> new Horse("Zefir " + i, i, i)).toList();

        assertEquals(horses, new Hippodrome(horses).getHorses());
    }


    @Test
        //
    void testMove_ShouldVerifyHorses_WhenUseMethodMove() {

        List<Horse> list = IntStream.range(0, 50).mapToObj(i -> mock(Horse.class)).toList();

//        List<Horse> list = new ArrayList<>();
//        for (int i = 0; i < 50; i++) {
//            list.add(Mockito.mock(Horse.class));
//        }

        Hippodrome hippodrome = new Hippodrome(list);
        hippodrome.move();

        list.forEach(horseMock -> verify(horseMock, times(1)).move());
//        for (Horse horseMock : list) {
//            verify(horseMock, times(1)).move();
//        }


    }

    @Test
    void testGetWinner_ShouldReturnDistance_WhenUseMethodGetWinner() {
        Horse horse1 = new Horse("Lobster", 1, 10);
        Horse horse2 = new Horse("Pegas", 2, 20);
        Horse horse3 = new Horse("Toursitic", 3, 30);

        Hippodrome hippodrome = new Hippodrome(List.of(horse1, horse2, horse3));

        assertSame(horse3, hippodrome.getWinner());


    }

}