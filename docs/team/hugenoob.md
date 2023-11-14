---
layout: page
title: Xing Lingxi's Project Portfolio Page
---

### Project: CoordiMate

CoordiMate is a desktop application designed specifically for SoC Computing Club event planners to help manage their contacts and tasks for their events, so that they can focus on the event itself.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=HugeNoob&breakdown=true&sort=groupTitle%20dsc&sortWithin=title&since=2023-09-22&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **New Feature**: `deleteAllPerson` ([PR #77](https://github.com/AY2324S1-CS2103T-T10-2/tp/pull/77))
  * What it does: Deletes all persons in the contacts list.
  * Justification: Provides a quick solution for clearing persons, enhancing organization and decluttering the interface.
  * Highlights: This command required me to have a deep understanding of the existing codebase. I had to ensure that only `UniquePersonList` was reset, and not the entire `AddressBook`, in order to preserve the tasks list.

* **New Feature**: `addTask` ([PR #84](https://github.com/AY2324S1-CS2103T-T10-2/tp/pull/84))
  * What it does: Adds a task to the task list.
  * Justification: Enables users to keep track of their tasks.
  * Highlights: Since task is a new entity that did not exist in AB3, I had to implement the logic and model flows for tasks to match the existing flow for persons.

* **New Feature**: `editTask` ([PR #91](https://github.com/AY2324S1-CS2103T-T10-2/tp/pull/91))
  * What it does: Allows users to edit the title, note, or tags of a task.
  * Justification: Enables users to correct mistakes or update the details of their tasks.
  * Highlights: I had to understand AB3's `EditPersonDescriptor` class design, which was only present in the `edit` command, and adapt it to `editTask`.

* **New Feature**: `deleteTagPerson` ([PR #120](https://github.com/AY2324S1-CS2103T-T10-2/tp/pull/120))
  * What it does: Deletes tag(s) from a person.
  * Justification: Initially, we could only specify new tags using the `editPerson` command. To delete a specific tag, the user would have to specify all existing tags, except the unwanted tag. This command provides a quick way for users to remove specific tags from a person.
  * Highlights: For this command, I had to handle the validity of the specified tags. Tags that exist on the person will be removed, while tags that do not exist will be ignored.

* **New Feature**: `deleteTagTask` ([PR #122](https://github.com/AY2324S1-CS2103T-T10-2/tp/pull/122))
  * What it does: Deletes tag(s) from a task.
  * Justification: Initially, we could only specify new tags using the `editTask` command. To delete a specific tag, the user would have to specify all existing tags, except the unwanted tag. This command provides a quick way for users to remove specific tags from a task.
  * Highlights: Similar to the `deleteTagPerson` command, I also had to handle the validity of the specified tags.

* **New Feature**: Add shortened command words to existing commands ([PR #106](https://github.com/AY2324S1-CS2103T-T10-2/tp/pull/106))
  * What it does: Users can now use shortened command words for existing commands.
  * Justification: Some of our commands, such as `deleteTagPerson`, were getting lengthy and troublesome to write each time. In the spirit of catering to fast typers, we decided to add shortened command words.
  * Credits: For this task, I added shortened command words for the commands that existed at that point in time. Further shortened command words were added by other team members later on.

* **Enhancement to existing features**: `help` ([PR #75](https://github.com/AY2324S1-CS2103T-T10-2/tp/pull/75))
  * What it does: Brings up a new window that displays the link to our user guide. A new button was added that allows users to directly open the link in their browser.
  * Justification: We wanted to improve the user experience by skipping the step of manually copy-and-pasting the link to the browser.

* **Project management**:
  * Introduced a [PR template](https://github.com/AY2324S1-CS2103T-T10-2/tp/pull/17) for the team to follow.
  * Proposed a 2-approval policy for PRs before merge. This improves the quality of the codebase and reduces the chances of bugs.

* **Documentation**:
  * User Guide:
    * Added documentation for `deleteAllPerson`, `addTask`, `editTask`, `deleteTagPerson`, `deleteTagTask`, `help`, `exit`. ([PR #62](https://github.com/AY2324S1-CS2103T-T10-2/tp/pull/62), [PR #108](https://github.com/AY2324S1-CS2103T-T10-2/tp/pull/108), [PR #160](https://github.com/AY2324S1-CS2103T-T10-2/tp/pull/160))
  * Developer Guide:
    * Added implementation details for `editTask`. ([PR #163](https://github.com/AY2324S1-CS2103T-T10-2/tp/pull/163))
    * Added user stories and use cases for `deleteAllPerson`, `addTask`, `editTask`, `deleteTagPerson`, `deleteTagTask`, `help`. ([PR #100](https://github.com/AY2324S1-CS2103T-T10-2/tp/pull/100), [PR #163](https://github.com/AY2324S1-CS2103T-T10-2/tp/pull/163))
    * Added Non-Functional Requirements. ([PR #56](https://github.com/AY2324S1-CS2103T-T10-2/tp/pull/56))

* **Community**:
  * PRs reviewed by me can be found [here](https://github.com/AY2324S1-CS2103T-T10-2/tp/pulls?q=is%3Apr+reviewed-by%3AHugeNoob+). As of time of writing, I have reviewed more than 50% of all PRs.
  * Forum discussions that I have participated in can be found [here](https://github.com/nus-cs2103-AY2324S1/forum/issues?q=is%3Aissue+commenter%3AHugeNoob+).
  * Reported [10 bugs and suggestions](https://github.com/HugeNoob/ped/issues) for other teams during the PE dry run.
