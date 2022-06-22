import org.junit.jupiter.api.DisplayName;
import org.mockito.Mockito;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HippodromeTest {
    List<Horse> listOfHorseEmpty = Collections.emptyList();

    @Test
    @DisplayName("Test1: Проверить, что при передаче в конструктор null, будет выброшено IllegalArgumentException")
    public void  exceptionGetArgumentIsNullTest(){
        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Hippodrome (null);
                }
        );
    }

    @Test
    @DisplayName("Test2: Проверить, что при передаче в конструктор null, выброшенное исключение будет содержать сообщение \"Horses cannot be null.\"")
    public void  exceptionGetArgumentIsNullGetMessageTest(){
        IllegalArgumentException illegalArgumentException = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Hippodrome (null);
                }
        );
        assertEquals("Horses cannot be null.", illegalArgumentException.getMessage());
    }

    @Test
    @DisplayName("Test3: Проверить, что при передаче в конструктор пустого списка, будет выброшено IllegalArgumentException")
    public void  exceptionGetListEmptyTest(){
        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Hippodrome (listOfHorseEmpty);
                }
        );
    }

    @Test
    @DisplayName("Test4: Проверить, что при передаче в конструктор пустого списка, выброшенное исключение будет содержать сообщение \"Horses cannot be empty.\"")
    public void  exceptionGetListEmptyGetMessageTest(){
        IllegalArgumentException illegalArgumentException = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Hippodrome (listOfHorseEmpty);
                }
        );
        assertEquals("Horses cannot be empty.", illegalArgumentException.getMessage());
    }

    @Test
    @DisplayName("Test5: Проверить, что метод возвращает список, который содержит те же объекты и в той же последовательности, что и список который был передан в конструктор. ")
    public void getHorsesTest(){
        List<Horse> listOfHorses = new ArrayList<>();
        for (int i = 1; i <= 30; i++) {
            listOfHorses.add(new Horse("horse"+String.valueOf(i),i *10.0, i* 15.0));
        }
        Hippodrome hippodrome = new Hippodrome(listOfHorses);
        assertEquals(listOfHorses, hippodrome.getHorses());
    }

    @Test
    @DisplayName("Test6: Проверить, что метод вызывает метод move у всех лошадей. ")
    public void allHorsesMoveTest(){
        List<Horse> listOfHorses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            Horse horseOne = Mockito.mock(Horse.class);
            listOfHorses.add(i, horseOne);
        }
        Hippodrome hippodrome = new Hippodrome(listOfHorses);
        hippodrome.move();

        for (Horse horseOne: listOfHorses) {
            Mockito.verify(horseOne).move();
        }
    }
    @Test
    @DisplayName("Test7: Проверить, что метод возвращает лошадь с самым большим значением distance")
    public void getWinnerTest(){
        Horse winner = new Horse("Winner", 100.0,100.0);
        List <Horse> listOfHorses = new ArrayList<>();
        listOfHorses.add(new Horse("horseOne", 10.0, 20.0));
        listOfHorses.add(new Horse("horseTwo", 20.0, 30.0));
        listOfHorses.add(new Horse("horseThree", 30.0, 40.0));
        listOfHorses.add(new Horse("horseFour", 40.0, 50.0));
        listOfHorses.add(winner);

        assertEquals(winner, listOfHorses.stream().max(Comparator.comparing(Horse::getDistance)).get());

    }
}
