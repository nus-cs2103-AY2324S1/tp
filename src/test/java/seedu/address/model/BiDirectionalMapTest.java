package seedu.address.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.lessons.Lesson;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.testutil.Assert;
import seedu.address.testutil.TypicalLessons;
import seedu.address.testutil.TypicalPersons;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BiDirectionalMapTest {
    private BiDirectionalMap<Person, Lesson> biDirectionalMap;
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "BiDirectionalMapTest");
    @BeforeEach
    public void setup() {
        this.biDirectionalMap = new BiDirectionalMap<>();
    }

    @Test
    public void addToMap_success() throws ParseException {
        Person person1 = TypicalPersons.ALICE;
        Lesson lesson1 = TypicalLessons.getSample1();

        biDirectionalMap.addMapping(person1, lesson1);

        assertArrayEquals(biDirectionalMap.get(person1), new Name[]{lesson1.getName()});
        assertArrayEquals(biDirectionalMap.getReversed(lesson1), new Name[]{person1.getName()});
    }

    /**
     * Converts a Java Array into a List.
     *
     * @param input the input array
     * @return a List with the same elements as the input array
     * @param <T> The type of the input array
     */
    public <T> List<T> convertToList(T[] input) {
        return Arrays.stream(input).collect(Collectors.toList());
    }

    @Test
    public void addManyLessonsToOnePerson_success() throws ParseException {
        Person person1 = TypicalPersons.ALICE;
        Lesson lesson1 = TypicalLessons.getSample1();
        Lesson lesson2 = TypicalLessons.getSample2();
        Lesson lesson3 = TypicalLessons.getSample3();

        biDirectionalMap.addMapping(person1, lesson1);
        biDirectionalMap.addMapping(person1, lesson2);
        biDirectionalMap.addMapping(person1, lesson3);

        Name[] expectedNames = new Name[]{lesson1.getName(), lesson2.getName(), lesson3.getName()};
        Name[] actualNames = biDirectionalMap.get(person1);
        assertEquals(3, actualNames.length);
        assertTrue(convertToList(actualNames).containsAll(convertToList(expectedNames)));
        assertArrayEquals(biDirectionalMap.getReversed(lesson1), new Name[]{person1.getName()});
    }

    @Test
    public void addManyPersonsToOneLesson_success() throws ParseException {
        Person person1 = TypicalPersons.ALICE;
        Person person2 = TypicalPersons.AMY;
        Person person3 = TypicalPersons.BOB;
        Lesson lesson1 = TypicalLessons.getSample1();

        biDirectionalMap.addMapping(person3, lesson1);
        biDirectionalMap.addMapping(person2, lesson1);
        biDirectionalMap.addMapping(person1, lesson1);


        Name[] expectedNames = new Name[]{person1.getName(), person2.getName(), person3.getName()};
        Name[] actualNames = biDirectionalMap.getReversed(lesson1);
        assertEquals(3, actualNames.length);
        assertTrue(convertToList(actualNames).containsAll(convertToList(expectedNames)));
        assertArrayEquals(biDirectionalMap.get(person1), new Name[]{lesson1.getName()});
    }

    @Test
    public void removeLessonFromPerson_success() throws ParseException {
        Person person1 = TypicalPersons.ALICE;
        Lesson lesson1 = TypicalLessons.getSample1();
        Lesson lesson2 = TypicalLessons.getSample2();

        biDirectionalMap.addMapping(person1, lesson1);
        biDirectionalMap.addMapping(person1, lesson2);

        Name[] expectedNames1 = new Name[]{lesson1.getName(), lesson2.getName()};
        Name[] actualNames1 = biDirectionalMap.get(person1);
        assertEquals(2, actualNames1.length);
        assertTrue(convertToList(actualNames1).containsAll(convertToList(expectedNames1)));

        // try to remove
        biDirectionalMap.removeMapping(person1, lesson1);

        Name[] expectedNames2 = new Name[]{lesson2.getName()};
        Name[] actualNames2 = biDirectionalMap.get(person1);
        assertEquals(1, actualNames2.length);
        assertTrue(convertToList(actualNames2).containsAll(convertToList(expectedNames2)));
    }

    @Test
    public void deleteLesson_removesAllMentions_success() throws ParseException {
        Person person1 = TypicalPersons.ALICE;
        Person person2 = TypicalPersons.AMY;
        Person person3 = TypicalPersons.BOB;

        Lesson lesson1 = TypicalLessons.getSample1();
        Lesson lesson2 = TypicalLessons.getSample2();
        Lesson lesson3 = TypicalLessons.getSample3();

        biDirectionalMap.addMapping(person1, lesson1);
        biDirectionalMap.addMapping(person1, lesson2);
        biDirectionalMap.addMapping(person1, lesson3);

        biDirectionalMap.addMapping(person2, lesson2);
        biDirectionalMap.addMapping(person2, lesson3);

        biDirectionalMap.addMapping(person3, lesson1);
        biDirectionalMap.addMapping(person3, lesson3);

        biDirectionalMap.removeReverse(lesson3);

        assertEquals(biDirectionalMap.getReversed(lesson3).length, 0);
        Name[] actualPerson1 = biDirectionalMap.get(person1);
        Name[] actualPerson2 = biDirectionalMap.get(person2);
        Name[] actualPerson3 = biDirectionalMap.get(person3);

        Name[] expectedPerson1 = new Name[]{lesson1.getName(), lesson2.getName()};
        Name[] expectedPerson2 = new Name[]{lesson2.getName()};
        Name[] expectedPerson3 = new Name[]{lesson1.getName()};

        assertTrue(convertToList(actualPerson1).containsAll(convertToList(expectedPerson1)));
        assertTrue(convertToList(actualPerson2).containsAll(convertToList(expectedPerson2)));
        assertTrue(convertToList(actualPerson3).containsAll(convertToList(expectedPerson3)));
    }

    // Update the student name
    @Test
    public void updateStudentName_updatesMap() throws ParseException {
        Person oldPerson = TypicalPersons.ALICE.clone();
        Lesson lesson1 = TypicalLessons.getSample1();

        biDirectionalMap.addMapping(oldPerson, lesson1);

        Person updatedPerson = oldPerson.clone();
        updatedPerson.setName(new Name("Aliced"));

        assertEquals(1, biDirectionalMap.get(oldPerson).length);

        biDirectionalMap.update(oldPerson, updatedPerson);


        assertEquals(0, biDirectionalMap.get(oldPerson).length);
        assertEquals(1, biDirectionalMap.get(updatedPerson).length);
    }

    // Update the lesson name
    @Test
    public void updateLessonName_updatesMap() throws ParseException {
        Person person = TypicalPersons.ALICE.clone();
        Lesson oldLesson = TypicalLessons.getSample1().clone();


        biDirectionalMap.addMapping(person, oldLesson);

        Lesson updatedLesson = oldLesson.clone();
        updatedLesson.setName(new Name("LessonTwoNewName"));


        assertEquals(1, biDirectionalMap.getReversed(oldLesson).length);

        biDirectionalMap.updateReverse(oldLesson, updatedLesson);

        assertEquals(0, biDirectionalMap.getReversed(oldLesson).length);
        assertEquals(1, biDirectionalMap.getReversed(updatedLesson).length);
    }

    @Test
    public void readMap_invalidData_returnEmptyMap() throws ParseException {
        Path filePath = TEST_DATA_FOLDER.resolve("TempAddressBook.json");
        // there's nothing here to begin with
        BiDirectionalMap<Person, Lesson> readBack = BiDirectionalMap.readFrom(filePath);
        assertEquals(readBack, new BiDirectionalMap<>());
    }

    @Test
    public void readAndSaveMap_allInOrder_success() throws ParseException {
        Path filePath = TEST_DATA_FOLDER.resolve("TempAddressBook.json");

        Person person1 = TypicalPersons.ALICE;
        Person person2 = TypicalPersons.AMY;
        Person person3 = TypicalPersons.BOB;

        Lesson lesson1 = TypicalLessons.getSample1();
        Lesson lesson2 = TypicalLessons.getSample2();
        Lesson lesson3 = TypicalLessons.getSample3();

        biDirectionalMap.addMapping(person1, lesson1);
        biDirectionalMap.addMapping(person1, lesson2);
        biDirectionalMap.addMapping(person1, lesson3);

        biDirectionalMap.addMapping(person2, lesson2);
        biDirectionalMap.addMapping(person2, lesson3);

        biDirectionalMap.addMapping(person3, lesson1);
        biDirectionalMap.addMapping(person3, lesson3);

        biDirectionalMap.saveTo(filePath);

        BiDirectionalMap<Person, Lesson> readBack = BiDirectionalMap.readFrom(filePath);
        assertEquals(readBack, biDirectionalMap);

        // test saving when there's a file already
        readBack.remove(person1);
        readBack.saveTo(filePath);

        BiDirectionalMap<Person, Lesson> readBack2 = BiDirectionalMap.readFrom(filePath);
        assertEquals(readBack2, readBack);


        // cleanup
        File file = new File(String.valueOf(filePath));
        file.delete();

    }



}
