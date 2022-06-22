
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.runner.RunWith;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;



@RunWith(MockitoJUnitRunner.class)
public class HorseTest {
    String nameHorse = "horseOne";
    Double speedHorse = 50.0;
    Double distanceHorse = 100.0;


    @Test
    @DisplayName("Test1: Проверить, что при передаче в конструктор первым параметром null, будет выброшено IllegalArgumentException")
    public void exceptionFirstArgumentIsNull() {
        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Horse(null, speedHorse, distanceHorse);
                }
        );

    }

    @Test
    @DisplayName("Test2: Проверить, что при передаче в конструктор первым параметром null, выброшенное исключение будет содержать сообщение \"Name cannot be null.\"")
    public void exceptionFirstArgumentIsNullGetMessage() {
        IllegalArgumentException illegalArgumentException = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Horse(null, speedHorse, distanceHorse);
                }
        );
        assertEquals("Name cannot be null", illegalArgumentException.getMessage());
    }

    @ParameterizedTest
    @DisplayName("Test3: Проверить, что при передаче в конструктор первым параметром пустой строки или строки содержащей только пробельные символы (пробел, табуляция и т.д.), будет выброшено IllegalArgumentException.")
    @ValueSource(strings = {"", " ", "\n", "\t"})
    public void exceptionFirstArgumentIsEmptyString(String nameHorse) {
        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Horse(nameHorse, speedHorse, distanceHorse);
                }
        );


    }

    @ParameterizedTest
    @DisplayName("Test4: Проверить, что при передаче в конструктор первым параметром пустой строки или строки содержащей только пробельные символы (пробел, табуляция и т.д.), выброшенное исключение будет содержать сообщение \"Name cannot be blank.\"")
    @ValueSource(strings = {"", " ", "\n", "\t"})
    public void exceptionFirstArgumentIsEmptyStringGetMessage(String nameHorse) {
        IllegalArgumentException illegalArgumentException = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Horse(nameHorse, speedHorse, distanceHorse);
                }
        );
        assertEquals("Name cannot be blank.", illegalArgumentException.getMessage());
    }

    @ParameterizedTest
    @DisplayName("Test5: Проверить, что при передаче в конструктор вторым параметром отрицательного числа, будет выброшено IllegalArgumentException")
    @ValueSource(doubles = {-10.0, -20.0, -77.0})
    public void exceptionSecondArgumentIsNegative(Double speedNegative) {
        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Horse(nameHorse, speedNegative, distanceHorse);
                }
        );
    }

    @ParameterizedTest
    @DisplayName("Test6: Проверить, что при передаче в конструктор вторым параметром отрицательного числа, выброшенное исключение будет содержать сообщение \"Speed cannot be negative.\"")
    @ValueSource(doubles = {-10.0, -20.0, -77.0})
    public void exceptionSecondArgumentIsNegativeGetMessage(Double speedNegative) {
        IllegalArgumentException illegalArgumentException = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Horse(nameHorse, speedNegative, distanceHorse);
                }
        );
        assertEquals("Speed cannot be negative.", illegalArgumentException.getMessage());
    }

    @ParameterizedTest
    @DisplayName("Test7: Проверить, что при передаче в конструктор третьим параметром отрицательного числа, будет выброшено IllegalArgumentException")
    @ValueSource(doubles = {-10.0, -20.0, -77.0})
    public void exceptionThirdArgumentIsNegative(Double distanceNegative) {
        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Horse(nameHorse, speedHorse, distanceNegative);
                }
        );
    }


    @ParameterizedTest
    @DisplayName("Test8: Проверить, что при передаче в конструктор третьим параметром отрицательного числа, выброшенное исключение будет содержать сообщение \"Distance cannot be negative.\"")
    @ValueSource(doubles = {-10.0, -20.0, -77.0})
    public void exceptionThirdArgumentIsNegativeGetMessage(Double distanceNegative) {
        IllegalArgumentException illegalArgumentException = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Horse(nameHorse, speedHorse, distanceNegative);
                }
        );
        assertEquals("Distance cannot be negative.", illegalArgumentException.getMessage());
    }

    @Test
    @DisplayName("Test9: Проверить, что метод возвращает строку, которая была передана первым параметром в конструктор")
    public void getNameTest() {
        Horse horse = new Horse(nameHorse, speedHorse, distanceHorse);
        assertEquals(nameHorse, horse.getName());

    }

    @Test
    @DisplayName("Test10: Проверить, что метод возвращает число, которое было передано вторым параметром в конструктор")
    public void getSpeedTest() {
        Horse horse = new Horse(nameHorse, speedHorse, distanceHorse);
        assertEquals(speedHorse, horse.getSpeed());

    }

    @Test
    @DisplayName("Test11: Проверить, что метод возвращает число, которое было передано третьим параметром в конструктор")
    public void getDistanceTest() {
        Horse horse = new Horse(nameHorse, speedHorse, distanceHorse);
        assertEquals(distanceHorse, horse.getDistance());

    }

    @Test
    @DisplayName("Test12: Проверить, что метод возвращает ноль, если объект был создан с помощью конструктора с двумя параметрами")
    public void getDistanceTestReturnNull() {
        Horse horseActual = new Horse(nameHorse, speedHorse);
        assertEquals(0, horseActual.getDistance());

    }

    @Test
    @DisplayName("Test13: Проверить, что метод вызывает внутри метод getRandomDouble с параметрами 0.2 и 0.9")
    public void moveGetRandomDoubleTest() {
        try (MockedStatic<Horse> horseMockedStatic = Mockito.mockStatic(Horse.class)) {
            Horse horseOne = new Horse(nameHorse, speedHorse, distanceHorse);
            horseOne.move();
            horseMockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }


    @ParameterizedTest
    @DisplayName("Test14: Проверить, что метод присваивает дистанции значение высчитанное по формуле")
    @ValueSource(doubles = {10.0, 20.0, 35.5})
    public void moveGetRandomDoubleCheckDistanceTest(Double number) {
        try (MockedStatic<Horse> horseMockedStatic = Mockito.mockStatic(Horse.class)) {
            horseMockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(number);
            Horse horseOne = new Horse(nameHorse, speedHorse, distanceHorse);
            horseOne.move();
            double distance = distanceHorse + 31 * number;
            assertEquals(distance, horseOne.getDistance());
        }
    }
}