---
layout: default.md
title: "Cheng Hou's Project Portfolio Page"
---

### Project: SEPlendid

**Team Member: Lam Cheng Hou**

**SEPlendid** is a versatile application used by prospective exchangers to plan their Student Exchange Programme (SEP)
study plans. Students from NUS Computing can use this CLI-based application, with a beautiful GUI, to view partner
universities and course mappings. The application is also bundled with many other useful features.

Given below are my contributions to the project.

* **New Feature 1**: Added the ability to list, add and delete local courses. \
  (PRs:
  [\#68](https://github.com/AY2324S1-CS2103T-W10-2/tp/pull/68),
  [\#69](https://github.com/AY2324S1-CS2103T-W10-2/tp/pull/69),
  [\#78](https://github.com/AY2324S1-CS2103T-W10-2/tp/pull/78),
  [\#92](https://github.com/AY2324S1-CS2103T-W10-2/tp/pull/92),
  [\#97](https://github.com/AY2324S1-CS2103T-W10-2/tp/pull/97))

    * What it does: Allows the user to list local courses, specifying the local course code, local course name and
      other relevant attributes. The user can also delete local courses, by specifying its local code. Lastly, users are
      able to list the local courses, to be display on the UI.

    * Justification: This feature is essential as it allows the user to manage the local courses available to map,
      which are preloaded in releases and also added by the user. This feature is also essential for the mapping
      feature,
      as it represents the NUS local course being mapped.

    * Highlights: This enhancement began our work on morphing AB3 into SEPlendid. It required an in-depth
      understanding of the existing code base, and provides the basis for other data types such as partner course (added
      by Sohyun), university (added by Alyssa) and notes (added by Alicia). It was important to maintain a consistent
      implementation across the codebase, and thus local course had to be implemented well to ensure that the other data
      type can follow suit. The main challenge was to identify that a local course cannot be deleted if it is being used
      in a mapping.

* **New Feature 2**: Added the mappings feature, including ability to list, add, delete, sort and search
  \
  (PRs:
  [\#110](https://github.com/AY2324S1-CS2103T-W10-2/tp/pull/110),
  [\#146](https://github.com/AY2324S1-CS2103T-W10-2/tp/pull/146),
  [\#176](https://github.com/AY2324S1-CS2103T-W10-2/tp/pull/176))

    * Sub-feature 2.1 (list mappings): Allows the user to list mappings, which include details on local courses,
      partner courses, universities and information description of individual mappings.

    * Sub-feature 2.2 (add mappings): Allows the user to add mappings from a local course to a partner course offered
      by an overseas university. The user has to specify a local code of an existing local course, a partner code of an
      existing partner course and the name of the university offering that partner course.

    * Sub-feature 2.3 (delete mappings): Allows the user to delete mappings by specifying the local code, partner code 3
      and university name tied to an existing mapping.

    * Sub-feature 2.4 (sort mappings): Allows the user to sort mappings by any of the attributes of a mapping, namely
      the local course code/name, partner course code/name, overseas university name and mapping-specific information.

    * Sub-feature 2.5 (search mappings): Allows the user to search mappings by any of the attributes of a mapping,
      namely
      the local course code/name, partner course code/name, overseas university name and mapping-specific information.

    * Justification: This feature is the core of our application, and provides the most value to our target users, NUS
      Computing students looking to embark on their exchange programmes. On top of basic functionality such as list, add
      and delete commands, I have decided to add sort and search commands for all mapping attributes. This is because a
      student might want to search for a mapping by any of the attributes. For instance, searching for a mapping by the
      university name might show all mappings offered by that partner university. Similarly, students can search for
      mappings by a specific partner course name e.g. 'Artificial Intelligence', to narrow down universities which offer
      a
      certain type of course.

    * Highlights: This feature greatly depends on my teammates' work, which is why project management was also an
      important aspect of my experience with this course. The main challenge with this feature was how our data was
      designed. I decided on a relational-database-like design, in order to not have any data redundancy. Our local
      course, partner course, university and notes catalogue were akin to relations in a relational database, while the
      mappings table represented the many-to-many relationship between local courses and partner courses. With this
      design, I had to come up with a creative way to implement sort and search for mappings, by local/partner course
      names. I also had to minimise coupling between the components, and ensure that the code was modular and
      extensible.

* **New Feature 3**: Added the functionality to switch between list views for different data types.

    * What it does: Allows the User Interface to switch between lists of different data types based on their
      respective commands. For instance, `localcourse list` will switch to the list of local courses, and `partnercourse
      list` will subsequently switch a list of partner courses.

    * Justification: This feature was key for the User Interface to function properly, as it switches the view based
      on the `CommandResult`.

    * Highlights: This enhancement required some understanding of how the execution flow of a command. As we had to
      deliver a minimum viable product, I had to ensure that the ability to switch between views was done effectively.
      The main challenge was to identify that `CommandResult` was a good way to implement this.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=lamchenghou&breakdown=false&sort=totalCommits%20dsc&sortWithin=totalCommits%20dsc&since=2023-09-22&timeframe=commit&mergegroup=&groupSelect=groupByRepos&tabOpen=true&tabType=authorship&tabAuthor=lamchenghou&tabRepo=AY2324S1-CS2103T-W10-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

<div style="page-break-after: always;"></div>

* **Project management**:
    * Created, managed and closed milestones `v1.1`, `v1.2`, `v1.2b` (3 milestones).
    * Managed releases `v1.3.1` (1 release) on GitHub, used for PE-D.
    * Maintained the project document by adding new sections for deliverables for team members (including myself) to
      add to.
    * Created the repository, and did initial set up of the project, including the MarkBind website and CI workflow.
    * Managed the project board, including adding issues and assigning them to team members.
    * Did my fair share of reviewing and merge PRs.
    * Initiated meetings to meet deliverables, and ensured that the team was on track to meet deadlines.

* **Enhancements to existing features**:
    * Wrote `StorageManager` to be able to handle more data types.
    * Built `SeplendidArgumentMap`, `SeplendidArgumentTokenizer` and `SeplendidParser` on top of the existing AB3's
      parsing logic to implement our command set. See PR [here](https://github.com/AY2324S1-CS2103T-W10-2/tp/pull/68).
      (Note: `SeplendidParser` had recently been refactored by another teammate who used code from
      [my PR](https://github.com/AY2324S1-CS2103T-W10-2/tp/pull/185), which had been closed.)
    * Adapted `ModelManager` to become `SeplendidModelManager` to support more niche functionalities such as
      `getLocalCourseIfExists`.

* **Documentation**:
    * User Guide:
        * Added `Command Format` section [\#35](https://github.com/AY2324S1-CS2103T-W10-2/tp/pull/35)
        * Added `Command Summary` section [\#35](https://github.com/AY2324S1-CS2103T-W10-2/tp/pull/35)
        * Added documentation for the `mapping` features [\#35](https://github.com/AY2324S1-CS2103T-W10-2/tp/pull/35)
        * Modified site-wide settings: [\#35](https://github.com/AY2324S1-CS2103T-W10-2/tp/pull/35)
        * Helped design the table of contents
    * Developer Guide:
        * Added implementation details for `SeplendidParser`
        * Added implementation details for the mapping feature
        * Added implementation details for `Storage` and `SeplendidModel`
        * Added use cases for mapping search and sort features
        * Added user stories for mappings
        * Added 'Morphing of AB3 to SEPlendid' portion of Effort section

* **Community**:
    * PRs reviewed (with non-trivial review comments): [1](https://github.com/AY2324S1-CS2103T-W10-2/tp/pull/85/files),
      [2](https://github.com/AY2324S1-CS2103T-W10-2/tp/pull/87),
      [3](https://github.com/AY2324S1-CS2103T-W10-2/tp/pull/93),
      [4](https://github.com/AY2324S1-CS2103T-W10-2/tp/pull/102),
      [5](https://github.com/AY2324S1-CS2103T-W10-2/tp/pull/115),
      [6](https://github.com/AY2324S1-CS2103T-W10-2/tp/pull/137),
      [7](https://github.com/AY2324S1-CS2103T-W10-2/tp/pull/139),
      [8](https://github.com/AY2324S1-CS2103T-W10-2/tp/pull/144),
      [9](https://github.com/AY2324S1-CS2103T-W10-2/tp/pull/167),
      [10](https://github.com/AY2324S1-CS2103T-W10-2/tp/pull/236)

    * Contributed to forum discussions:
      [1](https://github.com/nus-cs2103-AY2324S1/forum/issues/17#issuecomment-1690925221),
      [2](https://github.com/nus-cs2103-AY2324S1/forum/issues/32#issuecomment-1694675009),
      [3](https://github.com/nus-cs2103-AY2324S1/forum/issues/66#issuecomment-1703777885),
      [4](https://github.com/nus-cs2103-AY2324S1/forum/issues/130#issuecomment-1712751815),
      [5](https://github.com/nus-cs2103-AY2324S1/forum/issues/189#issuecomment-1731641936),
      [6](https://github.com/nus-cs2103-AY2324S1/forum/issues/189#issuecomment-1731656687),
      [7](https://github.com/nus-cs2103-AY2324S1/forum/issues/206#issuecomment-1732471322),
      [8](https://github.com/nus-cs2103-AY2324S1/forum/issues/203#issuecomment-1732472103) \
      For an exhaustive list: Refer to the
      [forum dashboard](https://nus-cs2103-ay2324s1.github.io/dashboards/contents/forum-activities.html) and search
      for`lamchenghou`
    * Reported bugs and suggestions for other teams in the class: [PED repo](https://github.com/lamchenghou/ped/issues)

* **Tools**:
    * Integrated CI
    * Integrated MarkBind (switching from Jekyll to MarkBind)
    * Integrated Codecov
