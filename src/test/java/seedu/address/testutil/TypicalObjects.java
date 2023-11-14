package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.model.LocalCourseCatalogue;
import seedu.address.model.MappingCatalogue;
import seedu.address.model.NoteCatalogue;
import seedu.address.model.PartnerCourseCatalogue;
import seedu.address.model.UniversityCatalogue;
import seedu.address.model.localcourse.LocalCourse;
import seedu.address.model.mapping.Mapping;
import seedu.address.model.mapping.MappingMiscInformation;
import seedu.address.model.note.Content;
import seedu.address.model.note.Note;
import seedu.address.model.partnercourse.PartnerCourse;
import seedu.address.model.tag.Tag;
import seedu.address.model.university.University;
import seedu.address.model.university.UniversityName;

/**
 * A utility class containing a list of SEPlendid objects to use in tests.
 */
public class TypicalObjects {

    public static final String TYPICAL_LOCAL_COURSE_CODE = "CS2103";
    public static final String TYPICAL_LOCAL_COURSE_NAME = "Software Engineering";
    public static final Double TYPICAL_LOCAL_COURSE_UNIT = 4.0;
    public static final String TYPICAL_LOCAL_COURSE_DESCRIPTION = "This course introduces the necessary conceptual "
            + "and analytical tools for systematic and rigorous development of software systems. It covers four main "
            + "areas of software development, namely object-oriented system analysis, object-oriented system modelling "
            +
            "and design, implementation, and testing, with emphasis on system modelling and design and implementation "
            + "of software courses that work cooperatively to fulfill the requirements of the system. Tools and "
            +
            "techniques for software development, such as Unified Modelling Language (UML), program specification, and "
            + "testing methods, will be taught. Major software engineering issues such as modularisation criteria, "
            + "program correctness, and software quality will also be covered.";

    public static final String TYPICAL_PARTNER_COURSE_CODE = "CS50";
    public static final String TYPICAL_PARTNER_COURSE_NAME = "Introduction to Computer Science";
    public static final Double TYPICAL_PARTNER_COURSE_UNIT = 5.0;
    public static final String TYPICAL_PARTNER_COURSE_DESCRIPTION = "This is CS50x , Harvard University's "
            +
            "introduction to the intellectual enterprises of computer science and the art of programming for majors and"
            +
            " non-majors alike, with or without prior programming experience. An entry-level course taught by David J. "
            +
            "Malan, CS50x teaches students how to think algorithmically and solve problems efficiently. Topics include "
            + "abstraction, algorithms, data structures, encapsulation, resource management, security, software "
            + "engineering, and web development. Languages include C, Python, SQL, and JavaScript plus CSS and HTML. "
            +
            "Problem sets inspired by real-world domains of biology, cryptography, finance, forensics, and gaming. The "
            + "on-campus version of CS50x , CS50, is Harvard's largest course.";

    public static final String TYPICAL_UNIVERSITY_NAME = "Typical University";
    public static final UniversityName TYPICAL_PARTNER_UNIVERSITY_NAME = new UniversityName("Harvard University");

    public static final String TYPICAL_MAPPING_MISC_INFORMATION = "CSCSCS";

    public static final String EDGE_CASE_VALID_LOCAL_COURSE_CODE = "S";
    public static final String EDGE_CASE_VALID_LOCAL_COURSE_NAME = " Software Testing";
    public static final Double EDGE_CASE_VALID_LOCAL_COURSE_UNIT = 0.0;
    public static final String EDGE_CASE_VALID_LOCAL_COURSE_DESCRIPTION = "a!";

    public static final String EDGE_CASE_VALID_PARTNER_COURSE_CODE = "V";
    public static final String EDGE_CASE_VALID_PARTNER_COURSE_NAME = " Game Development";
    public static final Double EDGE_CASE_VALID_PARTNER_COURSE_UNIT = 0.0;
    public static final String EDGE_CASE_VALID_PARTNER_COURSE_DESCRIPTION = "@";

    public static final String EDGE_CASE_VALID_UNIVERSITY_NAME = " School";

    public static final String INVALID_LOCAL_COURSE_CODE = "$HOW2BECOMERICH";
    public static final String INVALID_LOCAL_COURSE_NAME = " ";
    public static final Double INVALID_LOCAL_COURSE_UNIT = -1.0;
    public static final String INVALID_LOCAL_COURSE_ATTRIBUTE = "partnercode";
    public static final String INVALID_PARTNER_COURSE_ATTRIBUTE = "localname";
    public static final String INVALID_PARTNER_COURSE_ATTRIBUTE_UPDATE = "UNIVERSITY";

    public static final String INVALID_PARTNER_COURSE_CODE = "$H23Y1";
    public static final String INVALID_PARTNER_COURSE_NAME = " ";
    public static final String INVALID_UNIVERSITY_NAME = " ";

    public static final String INVALID_UNIVERSITY = "!!!";
    public static final Double INVALID_PARTNER_COURSE_UNIT = -1.0;
    public static final String INVALID_PARTNER_COURSE_DESCRIPTION = " ";

    public static final String INVALID_MAPPING_ATTRIBUTE = "index";

    public static final LocalCourse CS2040S = new LocalCourseBuilder().withLocalCode("CS2040S").withLocalName(

        "Data Structures & Algorithms").withLocalUnit(4.0).withLocalDescription(
        "This course introduces students to the "
            + "design and implementation of "
            + "fundamental data structures and "
            + "algorithms. The course covers basic "
            + "data structures (linked lists, "
            + "stacks, queues, hash tables, binary "
            + "heaps, trees, and graphs), searching"
            + " and sorting algorithms, and basic "
            + "analysis of algorithms.").build();

    public static final LocalCourse CS2041S = new LocalCourseBuilder().withLocalCode("CS2041S").withLocalName(

            "Data Structures & Algorithms").withLocalUnit(4.0).withLocalDescription(
            "This course introduces students to the "
                    + "design and implementation of "
                    + "fundamental data structures and "
                    + "algorithms. The course covers basic "
                    + "data structures (linked lists, "
                    + "stacks, queues, hash tables, binary "
                    + "heaps, trees, and graphs), searching"
                    + " and sorting algorithms, and basic "
                    + "analysis of algorithms.").build();

    public static final LocalCourse CS2042S = new LocalCourseBuilder().withLocalCode("CS2042S").withLocalName(
            "Data Structures & Algorithms").withLocalUnit(4.0).withLocalDescription(
            "This course introduces students to the "
                    + "design and implementation of "
                    + "fundamental data structures and "
                    + "algorithms. The course covers basic "
                    + "data structures (linked lists, "
                    + "stacks, queues, hash tables, binary "
                    + "heaps, trees, and graphs), searching"
                    + " and sorting algorithms, and basic "
                    + "analysis of algorithms.").build();

    public static final LocalCourse CS1231S = new LocalCourseBuilder().withLocalCode("CS1231S").withLocalName(
        "Discrete Structures").withLocalUnit(4.0).withLocalDescription("This course introduces mathematical tools "
        + "required in the study of computer "
        + "science. Topics include: (1) Logic "
        + "and proof techniques: propositions, "
        + "conditionals, quantifications. (2) "
        + "Relations and Functions: Equivalence"
        + " relations and partitions. Partially"
        + " ordered sets. Well-Ordering "
        + "Principle. Function equality. "
        + "Boolean/identity/inverse functions. "
        + "Bijection. (3) Mathematical "
        + "formulation of data models (linear "
        + "model, trees, graphs). (4) Counting "
        + "and Combinatoric: Pigeonhole "
        + "Principle. Inclusion-Exclusion "
        + "Principle. Number of relations on a "
        + "set, number of injections from one "
        + "finite set to another, "
        + "Diagonalization proof: An infinite "
        + "countable set has an uncountable "
        + "power set; Algorithmic proof: An "
        + "infinite set has a countably "
        + "infinite subset. Subsets of "
        + "countable sets are countable.").build();

    public static final LocalCourse CS3230 = new LocalCourseBuilder().withLocalCode("CS3230").withLocalName(
            "Design & Analysis of Algorithms").withLocalUnit(4.0).withLocalDescription(
            "This course introduces different techniques"
                    + " of designing and analysing "
                    + "algorithms. Students will learn about"
                    + " the framework for algorithm "
                    + "analysis, for example, lower bound "
                    + "arguments, average case analysis, and"
                    + " the theory of NP-completeness. In "
                    + "addition, students are exposed to "
                    + "various algorithm design paradigms. "
                    + "The course serves two purposes: to "
                    + "improve the students' ability to "
                    + "design algorithms in different areas,"
                    + " and to prepare students for the "
                    + "study of more advanced algorithms. "
                    + "The course covers lower and upper "
                    + "bounds, recurrences, basic algorithm "
                    + "paradigms (such as prune-and-search, "
                    + "dynamic programming, "
                    + "branch-and-bound, graph traversal, "
                    + "and randomised approaches), amortized"
                    + " analysis, NP-completeness, and some "
                    + "selected advanced topics.").build();

    public static final LocalCourse CS2030S = new LocalCourseBuilder().withLocalCode("CS2030S").withLocalName(
            "Programming Methodology II").withLocalUnit(4.0).build();
    public static final LocalCourse MA2001 = new LocalCourseBuilder().withLocalCode("MA2001").withLocalName(
            "Linear Algebra").withLocalUnit(4.0).withLocalDescription("This course is a first course in linear "
            + "algebra. Fundamental concepts of "
            + "linear algebra will be introduced and"
            + " investigated in the context of the "
            + "Euclidean spaces R^n. Proofs of "
            + "results will be presented in the "
            + "concrete setting. Students are "
            + "expected to acquire computational "
            + "facilities and geometric intuition "
            + "with regard to vectors and matrices. "
            + "Some applications will be presented. "
            + "Major topics: Systems of linear "
            + "equations, matrices, determinants, "
            + "Euclidean spaces, linear combinations"
            + " and linear span, subspaces, linear "
            + "independence, bases and dimension, "
            + "rank of a matrix, inner products, "
            + "eigenvalues and eigenvectors, "
            + "diagonalization, linear "
            + "transformations between Euclidean "
            + "spaces, applications.").build();

    public static final LocalCourse MA1521 = new LocalCourseBuilder().withLocalCode("MA1521").withLocalName(
            "Calculus in Computing").withLocalUnit(4.0).build();
    public static final LocalCourse TYPICAL_LOCAL_COURSE = new LocalCourseBuilder().withLocalCode(
                    TYPICAL_LOCAL_COURSE_CODE)
            .withLocalName(
                    TYPICAL_LOCAL_COURSE_NAME)
            .withLocalUnit(
                    TYPICAL_LOCAL_COURSE_UNIT)
            .build();
    public static final LocalCourse EDGE_CASE_VALID_LOCAL_COURSE = new LocalCourseBuilder().withLocalCode(
            EDGE_CASE_VALID_LOCAL_COURSE_CODE).withLocalName(EDGE_CASE_VALID_LOCAL_COURSE_NAME).withLocalUnit(
            EDGE_CASE_VALID_LOCAL_COURSE_UNIT).build();

    // PartnerCourse
    public static final PartnerCourse TYPICAL_PARTNER_COURSE = new PartnerCourseBuilder().withPartnerUniversity(
            TYPICAL_PARTNER_UNIVERSITY_NAME).withPartnerCode(TYPICAL_PARTNER_COURSE_CODE).withPartnerName(
            TYPICAL_PARTNER_COURSE_NAME).withPartnerUnit(TYPICAL_PARTNER_COURSE_UNIT).withPartnerDescription(
            TYPICAL_PARTNER_COURSE_DESCRIPTION).build();
    public static final PartnerCourse EDGE_CASE_VALID_PARTNER_COURSE =
            new PartnerCourseBuilder().withPartnerUniversity(
                            new UniversityName(EDGE_CASE_VALID_UNIVERSITY_NAME))
                    .withPartnerCode(
                            EDGE_CASE_VALID_PARTNER_COURSE_CODE)
                    .withPartnerName(
                            EDGE_CASE_VALID_PARTNER_COURSE_NAME)
                    .withPartnerUnit(
                            EDGE_CASE_VALID_PARTNER_COURSE_UNIT)
                    .withPartnerDescription(
                            EDGE_CASE_VALID_PARTNER_COURSE_DESCRIPTION)
                    .build();

    public static final PartnerCourse COMP1000 = new PartnerCourseBuilder().withPartnerUniversity(new UniversityName(
                    "University of Edinburgh"))
            .withPartnerCode("COMP1000")
            .withPartnerName(
                    "Introduction to Programming")
            .withPartnerUnit(5.0)
            .withPartnerDescription("COMP1000 is a "
                    + "level 1000 course.")
            .build();
    public static final PartnerCourse COMP2000 = new PartnerCourseBuilder().withPartnerUniversity(new UniversityName(
                    "University of Leeds"))
            .withPartnerCode("COMP2000")
            .withPartnerName("Introduction to Databases")
            .withPartnerUnit(5.0)
            .withPartnerDescription("COMP2000 is a "
                    + "level 2000 course.")
            .build();
    public static final PartnerCourse COMP3000 = new PartnerCourseBuilder().withPartnerUniversity(new UniversityName(
                    "University of Zurich"))
            .withPartnerCode("COMP3000")
            .withPartnerName("Introduction to Networks")
            .withPartnerUnit(5.0)
            .withPartnerDescription("COMP3000 is a "
                    + "level 3000 course.")
            .build();

    public static final PartnerCourse S0402SC = new PartnerCourseBuilder().withPartnerUniversity(new UniversityName(
                    "Nanyang Technological University")).withPartnerCode("S0402SC").withPartnerName("Algorithms")
            .withPartnerUnit(
                    3.0).withPartnerDescription("Funny NTU module.").build();
    public static final PartnerCourse STAN3230 = new PartnerCourseBuilder().withPartnerUniversity(new UniversityName(
                    "Stanford University"))
            .withPartnerCode("STAN3230")
            .withPartnerName(
                    "Design & Analysis of Algorithms")
            .withPartnerUnit(3.0)
            .withPartnerDescription(
                    "Fun analysis module.")
            .build();
    public static final PartnerCourse COMP4000 = new PartnerCourseBuilder().withPartnerUniversity(new UniversityName(
                    "Seoul National University"))
            .withPartnerCode("COMP4000")
            .withPartnerName(
                    "Introduction to Game Development")
            .withPartnerDescription("Fun game module")
            .build();


    // University
    public static final University NTU = new University(new UniversityName("Nanyang Technological University"));
    public static final University STANFORD = new University(new UniversityName("Stanford University"));
    public static final University WATERLOO = new University(new UniversityName("University of Waterloo"));
    public static final University SNU = new University(new UniversityName("Seoul National University"));
    public static final University YONSEI = new University(new UniversityName("Yonsei University"));
    public static final University HYUN = new University(new UniversityName("Hyun University"));

    public static final University EDINBURGH = new University(new UniversityName("University of Edinburgh"));
    public static final University ZURICH = new University(new UniversityName("University of Zurich"));

    // Note
    public static final Note NOTE1 = new Note(new Content("Exchange Application Deadline: 25 September 2023"),
            new HashSet<>(List.of()), 100);
    public static final Note NOTE2 = new Note(new Content("SEP Country Choices: Canada, Australia"),
            new HashSet<>(List.of(new Tag("plans"))), 200);
    public static final Note NOTE3 = new Note(new Content("Reminder: Submit Health Insurance Form by 10 October 2023"),
            new HashSet<>(List.of(new Tag("deadline"))), 300);
    public static final Note NOTE4 = new Note(new Content("Orientation Day: 2 November 2023, Main Auditorium"),
            new HashSet<>(List.of(new Tag("event"))), 400);
    public static final Note NOTE5 = new Note(new Content("Important: Visa Documents Verification on 15 October 2023"),
            new HashSet<>(List.of(new Tag("visa"), new Tag("deadline"))), 500);
    public static final Note NOTE6 = new Note(new Content("Housing Options Available: Check Your Email for Details"),
            new HashSet<>(List.of(new Tag("accommodation"))), 600);
    public static final Note NOTE7 = new Note(new Content("Language Proficiency Test: Schedule Your Exam by 5 "
            + "November 2023"), new HashSet<>(List.of(new Tag("test"), new Tag("language"))),
            700);

    // Mapping
    public static final Mapping CS2040S_TO_NTU_S0402SC = new Mapping(CS2040S.getLocalCode(),
            NTU.getUniversityName(),
            S0402SC.getPartnerCode(),
            new MappingMiscInformation("NIL"));
    public static final Mapping CS3230S_TO_STANFORD_STAN3230 = new Mapping(CS3230.getLocalCode(),
            STANFORD.getUniversityName(),
            STAN3230.getPartnerCode(),
            new MappingMiscInformation("NIL"));
    public static final Mapping CS1231S_TO_EDINBURGH_COMP1000 = new Mapping(CS1231S.getLocalCode(),
            COMP1000.getPartnerUniversity().getUniversityName(),
            COMP1000.getPartnerCode(),
            new MappingMiscInformation("NIL"));
    public static final Mapping CS2030S_TO_ZURICH_COMP3000 = new Mapping(CS2030S.getLocalCode(),
            COMP3000.getPartnerUniversity().getUniversityName(),
            COMP3000.getPartnerCode(),
            new MappingMiscInformation("NIL"));
    public static final Mapping MA1521_TO_SNU_COMP4000 = new Mapping(MA1521.getLocalCode(),
            COMP4000.getPartnerUniversity().getUniversityName(),
            COMP4000.getPartnerCode(),
            new MappingMiscInformation("DIFFERENT INFORMATION"));

    private TypicalObjects() {
    } // prevents instantiation

    /**
     * Returns an {@code LocalCourseCatalogue} with all the typical localCourses.
     */
    public static LocalCourseCatalogue getTypicalLocalCourseCatalogue() {
        LocalCourseCatalogue localCourseCatalogue = new LocalCourseCatalogue();
        for (LocalCourse localCourse : getTypicalLocalCourses()) {
            localCourseCatalogue.addLocalCourse(localCourse);
        }
        return localCourseCatalogue;
    }

    /**
     * Returns an {@code PartnerCourseCatalogue} with all the typical partnerCourses.
     */
    public static PartnerCourseCatalogue getTypicalPartnerCourseCatalogue() {
        PartnerCourseCatalogue partnerCourseCatalogue = new PartnerCourseCatalogue();
        for (PartnerCourse partnerCourse : getTypicalPartnerCourses()) {
            partnerCourseCatalogue.addPartnerCourse(partnerCourse);
        }
        return partnerCourseCatalogue;
    }

    /**
     * Returns an {@code LocalCourseCatalogue} with all the typical localCourses.
     * Specially made to support mapping tests.
     */
    public static LocalCourseCatalogue getTypicalLocalCourseCatalogueForMappingTests() {
        LocalCourseCatalogue localCourseCatalogue = new LocalCourseCatalogue();
        for (LocalCourse localCourse : getTypicalLocalCoursesForMappingTests()) {
            localCourseCatalogue.addLocalCourse(localCourse);
        }
        return localCourseCatalogue;
    }

    /**
     * Returns an {@code PartnerCourseCatalogue} with all the typical partnerCourses.
     * Specially made to support mapping tests.
     */
    public static PartnerCourseCatalogue getTypicalPartnerCourseCatalogueForMappingTests() {
        PartnerCourseCatalogue partnerCourseCatalogue = new PartnerCourseCatalogue();
        for (PartnerCourse partnerCourse : getTypicalPartnerCoursesForMappingTests()) {
            partnerCourseCatalogue.addPartnerCourse(partnerCourse);
        }
        return partnerCourseCatalogue;
    }

    /**
     * Returns an {@code UniversityCatalogue} with all the typical universities.
     */
    public static UniversityCatalogue getTypicalUniversityCatalogue() {
        UniversityCatalogue universityCatalogue = new UniversityCatalogue();
        for (University university : getTypicalUniversities()) {
            universityCatalogue.addUniversity(university);
        }
        return universityCatalogue;
    }

    /**
     * Returns an {@code MappingCatalogue} with all the typical mappings.
     */
    public static MappingCatalogue getTypicalMappingCatalogue() {
        MappingCatalogue mappingCatalogue = new MappingCatalogue();
        for (Mapping mapping : getTypicalMappings()) {
            mappingCatalogue.addMapping(mapping);
        }
        return mappingCatalogue;
    }

    /**
     * Returns an {@code MappingCatalogue} with all the typical mappings.
     * Specially made to support mapping tests, to prevent interference with
     * other tests for other components.
     */
    public static MappingCatalogue getTypicalMappingCatalogueForMappingTests() {
        MappingCatalogue mappingCatalogue = new MappingCatalogue();
        for (Mapping mapping : getTypicalMappingsWithDependencies()) {
            mappingCatalogue.addMapping(mapping);
        }
        return mappingCatalogue;
    }

    /**
     * Returns an {@code NoteCatalogue} with all the typical notes.
     */
    public static NoteCatalogue getTypicalNoteCatalogue() {
        NoteCatalogue noteCatalogue = new NoteCatalogue();
        for (Note note : getTypicalNotes()) {
            noteCatalogue.addNote(note);
        }
        return noteCatalogue;
    }


    public static List<LocalCourse> getTypicalLocalCourses() {
        return new ArrayList<>(Arrays.asList(CS2040S, MA2001, CS3230, CS1231S));
    }

    public static List<PartnerCourse> getTypicalPartnerCourses() {
        return new ArrayList<>(Arrays.asList(COMP1000, S0402SC, COMP2000, STAN3230));
    }

    public static List<University> getTypicalUniversities() {

        return new ArrayList<>(Arrays.asList(NTU, STANFORD, SNU));
    }

    public static List<Note> getTypicalNotes() {
        return Stream.of(NOTE1, NOTE2, NOTE3, NOTE4, NOTE5, NOTE6, NOTE7).map(Note::getCopy)
                .collect(Collectors.toList());
    }

    public static List<LocalCourse> getTypicalLocalCoursesForMappingTests() {
        return new ArrayList<>(Arrays.asList(CS2040S, MA2001, CS3230, CS1231S, MA1521));
    }

    public static List<PartnerCourse> getTypicalPartnerCoursesForMappingTests() {
        return new ArrayList<>(Arrays.asList(COMP1000, S0402SC, COMP2000, STAN3230, COMP4000));
    }

    /**
     * Returns a List of Mappings, which have dependents that will not interfere with other tests.
     */
    public static List<Mapping> getTypicalMappings() {
        return new ArrayList<>(Arrays.asList(CS3230S_TO_STANFORD_STAN3230));
    }

    /**
     * Returns a List of Mappings, which have more mappings and dependencies, suitable for testing mapping feature
     */
    public static List<Mapping> getTypicalMappingsWithDependencies() {
        return new ArrayList<>(Arrays.asList(CS3230S_TO_STANFORD_STAN3230,
                CS2040S_TO_NTU_S0402SC,
                CS1231S_TO_EDINBURGH_COMP1000,
                MA1521_TO_SNU_COMP4000));
    }
}
