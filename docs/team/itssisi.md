---
layout: page
title: Sigrid Elvina Huisen's Project Portfolio Page
---

### Project: TAManager

TAManager is a desktop address book application made for professors to manage their teaching assistants (TA) under their charge.
It provides fast access to TAs' contact details and their availabilities for tutorials/labs.
You can track teaching and claimable hours among your TAs and find relief TAs for impromptu events or unforeseen circumstances.

Given below are my contributions to the project.

<br/>

* **New Feature**: Added the ability to add TAs' Telegram handle.
  * What it does: Allows users to add Telegram handles to TAs' profiles.
  * Justification: This feature improves the product significantly because it allows users to contact TAs through Telegram, which is a popular messaging platform among students.
  * Highlights: This enhancement affects the `TA` class, `AddCommand` class, `EditCommand` class, and many other classes as the Telegram handle is a new field that needs to be added to the existing TA profile.
  The implementation was challenging due to the widespread implementation across the various classes.
  * Credits: This feature was implemented by me with reference to the `Email` field in the `TA` class. This feature was also implemented as the replacement for the original `Address` field in AB3. 
  
<br/>


* **New Feature**: Added the ability to set default course.
  * What it does: Allows users to set a default course for the application to be displayed on the main window.
  * Justification: This feature improves the product significantly because it allows users to display the list of TAs teaching under the default course when launching TAManager.
  * Highlights: This enhancement requires implementation of a new `TeachingCoursePredicate` class to filter the list of TAs teaching under the default course.
    The dependency between the classes required me to be careful as changing the implementation of one class may affect the other.
  * Credits: This feature was implemented by using the `TeachingCoursePredicate` as written by my teammate Rayner.

<br/>

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=itssisi&breakdown=true)

<br/>

* **Project management**:
  * Led group discussions on User Guide
  * Managed pull requests and code reviews for `v1.1` - `v1.4` (9 pull requests) on GitHub
  * Managed bug reports for `v1.4` on Github

<br/>

* **Enhancements to existing features**:
  * Added the ability to restrict `Phone` field to a valid phone number (Pull requests: [\#125](https://github.com/AY2324S1-CS2103T-T10-1/tp/pull/125))
  * Refactored time in `Lesson` class into `TimeInterval` (Pull requests: [\#69](https://github.com/AY2324S1-CS2103T-T10-1/tp/pull/69))

<br/>

* **Documentation for Developer Guide**:
  * Added implementation details of `TeachCommand` feature (Pull requests: [\#46](https://github.com/AY2324S1-CS2103T-T10-1/tp/pull/46))

<br/>

* **Documentation for User Guide**:
  * Added documentation for `TeachCommand` feature (Pull requests: [\#49](https://github.com/AY2324S1-CS2103T-T10-1/tp/pull/49))
  * Did cosmetic tweaks to the existing documentation features `CourseCommand` ([Commit link](https://github.com/AY2324S1-CS2103T-T10-1/tp/commit/9b4c003a34332a412fb8e113189dc9a1906455be))
  * Redesigned the entire User Guide to improve the readability and flow of the document, such as adding tables and fixing the language use (Pull requests: [\#158](https://github.com/AY2324S1-CS2103T-T10-1/tp/pull/158))
  * Reformatted and converted the User Guide into PDF for final submission 
  
<br/>

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#27](https://github.com/AY2324S1-CS2103T-T10-1/tp/pull/27),
    [\#49](https://github.com/AY2324S1-CS2103T-T10-1/tp/pull/49), [\#61](https://github.com/AY2324S1-CS2103T-T10-1/tp/pull/61), [\#75](https://github.com/AY2324S1-CS2103T-T10-1/tp/pull/75),
    [\#132](https://github.com/AY2324S1-CS2103T-T10-1/tp/pull/132)
  * Reported bugs and suggestions for other teams during PE-D (11 bug reports)
  * Created demo video for TAManager 
