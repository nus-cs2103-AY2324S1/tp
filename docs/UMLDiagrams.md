ModelClassDiagram

@startuml
title Model
interface ReadOnlyAddressBook
interface Model
interface ReadOnlyUserPrefs
class AddressBook
class ModelManager
class UserPrefs
class UniquePersonList
class Person {
String name
String phone
String telegram
String hour
}
class Tag
class FreeTime
class Course
class Lesson
class UniqueCourseList
class ObservableList
ReadOnlyAddressBook <|-down- AddressBook
Model <|-down- ModelManager
ReadOnlyUserPrefs <|-down- UserPrefs
AddressBook "1"<-- ModelManager
UserPrefs "1"<-- ModelManager
AddressBook *--> "       1" UniquePersonList
Person <-* UniquePersonList
Person <-- ModelManager
Person --> FreeTime
Person -->" *" Course
Person -->"*" Tag
UniqueCourseList "1"<-- ModelManager
UniqueCourseList *-->"            *" Course
Course --> Lesson
Person --> Lesson
ObservableList *-down->"filtered *" Person
@enduml