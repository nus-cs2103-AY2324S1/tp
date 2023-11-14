---
layout: page
title: Teck Fang's Project Portfolio Page
---

### Project: NUSCourseMates

NUSCourseMates is a desktop address book application used for students to keep track of the modules taken by their contacts. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.


* **New Feature**: Added the ability to filter student based on module ID.
    * What it does: allows users to filter and display students enrolled in a specified course. For example, executing "findcourse MA2001" will show all students taking the course "MA2001".
    * Justification: This feature significantly improves the usability of the application for users by enabling them to quickly find peers or friends associated with specific courses.
    * Highlights: The findcourse feature's implementation necessitated a new command class, FindCourseCommand, and a predicate class, CourseContainsKeywordsPredicate, to handle the search logic. The challenge lay in integrating these new classes seamlessly with the existing architecture and ensuring that the search was both efficient and accurate. This enhancement impacts not only the user's interaction with the application but also the underlying model's data management.
    * Credits: the `find` logic in AB3 address book.


* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=tanteckfang&breakdown=false&sort=groupTitle%20dsc&sortWithin=title&since=2023-09-22&timeframe=commit&mergegroup=&groupSelect=groupByRepos)

* **Enhancements to existing features**: Streamlined the add command to require only a name and phone number.
    * What it does: The `add` command has been modified to make email and address optional when adding a new contact. Now, users can quickly input essential information (just the name and phone number) to create a new entry in their address book.
    * Justification: This modification enhances the application’s flexibility and speed of use. Users often have just the basic contact information at hand and may wish to add more details at a later stage. By reducing the number of mandatory fields, the application becomes more accessible and reduces the barrier for entry creation.
    * Highlights: The core validation process has been modified to no longer require optional fields, simplifying data entry.
    * Credits: the original AB3 address book.

* **Enhancements to existing features**: Refined tagging system with predefined categories.
    * What it does: The `add` and `edit` command's tagging functionality has been overhauled to accept only three specific tag categories: 'Friend', 'Close Friend', and 'Emergency'. Tags are case-insensitive to ensure user convenience and consistency. For instance, inputting 'friend', 'FRIEND', or 'friEnd' will all categorize the contact as 'Friend'. Additionally, 'cf' is recognized as a shortcut for 'Close Friend'. The 'Emergency' tag is limited to a maximum of two contacts to emphasize its importance and to keep these contacts readily accessible.
    * Justification: This update standardises the tagging system for better contact organisation, using predefined categories for consistency and less clutter. Tags are case-insensitive to minimize input errors, enhancing usability. The 'Emergency' tag is specially restricted to highlight its urgency and keep it distinct.
    * Highlights: Implemented a case-insensitive tagging validation system and a limit for 'Emergency' tags to streamline contact management.

* **Enhancements to existing features**: Refined the find command to findstudent to align with the findcourse command for consistency.
    * Credits: the original AB3 address book.

* **Project management**
    * Responsible for overseeing and ensuring the consistency and quality of documentation for the project.

* **Documentation**:
    * User Guide:
        * Added documentation for the features `findcourse` and `findstudent`. Pull Request([#138](https://github.com/AY2324S1-CS2103T-T17-4/tp/pull/138/files))
        * Added some specification for the changes in `Tag`.
    * Developer Guide:
        * Added documentation for the features `findcourse`, `findstudent`, added activity diagram and sequence diagram for both of these features. Pull Request([#123](https://github.com/AY2324S1-CS2103T-T17-4/tp/pull/123/file),[#148](https://github.com/AY2324S1-CS2103T-T17-4/tp/pull/148/files))
        * Added documentation for the feature `Tag`, added activity diagram, sequence diagram and class diagram for it.
        * Added NFRs. Pull Request([#89](https://github.com/AY2324S1-CS2103T-T17-4/tp/pull/89/files))

* **Community** :
    * PRs reviewed: [#124](https://github.com/AY2324S1-CS2103T-T17-4/tp/pull/124),[#149](https://github.com/AY2324S1-CS2103T-T17-4/tp/pull/149), [#232](https://github.com/AY2324S1-CS2103T-T17-4/tp/pull/232)
    * Posted an issue about an error I faced but resolved it myself and share my solution in forum: [395](https://github.com/nus-cs2103-AY2324S1/forum/issues/385)
    * Reported bugs and some suggestions for other team during PE Dry Run:  [1](https://github.com/tanteckfang/ped/issues/1), [3](https://github.com/tanteckfang/ped/issues/3),[7](https://github.com/tanteckfang/ped/issues/7)


