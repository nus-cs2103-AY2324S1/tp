---
title: Heng Yi's Project Portfolio Page
---

## Project: TAfinder

TAfinder is a desktop resource management application used by NUS SOC professors to choose TAs from a large pool of applicants. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

### Summary of Contributions

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=lheng1&breakdown=true)


* **Enhancements implemented**:
  * Enhancement 1: Compare between two applicants
    * What it does: The enhancement facilitates a side-by-side comparison of two applicants through a user interface (UI).
    * Description: The UI displays comprehensive details of both applicants, including Student Number, Name, GPA, Interview Score, Previous Module Grade, and Comments. The window intelligently compares values (e.g., GPA, Interview Score, Previous Module Grade) and highlights the higher score for quick reference. This feature streamlines the decision-making process for selecting a Teaching Assistant (TA).
    * Justification: The implemented feature offers a holistic view of applicants' credentials and simplifies the decision-making process by highlighting superior scores. This ensures that the professor can easily discern which applicant excels in specific criteria.
    * Highlights: Creating the UI posed challenges, particularly in integrating it with the main window. Ultimately, a separate window was developed to pop up upon the execution of the compare command. Besides, having to constantly update and integrate all the different parameters, including new ones created by my teammates ate up most of my time on doing the tP, as there were a lot of dependencies between each parameter.
    * Credits: Special thanks to Ravern for inspiring the idea of utilizing a separate window.


* **Contributions to the UG**:
  * Created the skeletal version of the UG for the team: [PR #52](https://github.com/AY2324S1-CS2103T-W10-1/tp/pull/52)
  * In charge of updating the `edit` and `compare` function of UG: [PR #52](https://github.com/AY2324S1-CS2103T-W10-1/tp/pull/52), [PR #85](https://github.com/AY2324S1-CS2103T-W10-1/tp/pull/85)
  * Helped check overall quality of UG: [PR #211](https://github.com/AY2324S1-CS2103T-W10-1/tp/pull/211)


* **Contributions to the DG**:
  * Created the compare command implementation details: [PR #95](https://github.com/AY2324S1-CS2103T-W10-1/tp/pull/95)
  * Created sample use cases for the team: [PR #48](https://github.com/AY2324S1-CS2103T-W10-1/tp/pull/48)
  * Update target user profile, value proposition, and user stories: [PR #47](https://github.com/AY2324S1-CS2103T-W10-1/tp/pull/47)
  * In charge of Planned Enhancements appendix: [PR #206](https://github.com/AY2324S1-CS2103T-W10-1/tp/pull/206)
        

* **Contributions to team-based tasks**:
  * Created deadlines for the milestones.
  * Created labels and milestones in team GitHub page.
  * Enabled assertions for the project: [PR #87](https://github.com/AY2324S1-CS2103T-W10-1/tp/pull/34)


* **Review/mentoring contributions**:
  * Reviewed teammates' PRs: [PR #34](https://github.com/AY2324S1-CS2103T-W10-1/tp/pull/87), [PR #42](https://github.com/AY2324S1-CS2103T-W10-1/tp/pull/42), [PR #57](https://github.com/AY2324S1-CS2103T-W10-1/tp/pull/57), [PR #61](https://github.com/AY2324S1-CS2103T-W10-1/tp/pull/61), [PR #75](https://github.com/AY2324S1-CS2103T-W10-1/tp/pull/75), [PR #77](https://github.com/AY2324S1-CS2103T-W10-1/tp/pull/77), [PR #79](https://github.com/AY2324S1-CS2103T-W10-1/tp/pull/79), [PR #89](https://github.com/AY2324S1-CS2103T-W10-1/tp/pull/89), [PR #90](https://github.com/AY2324S1-CS2103T-W10-1/tp/pull/90), [PR #96](https://github.com/AY2324S1-CS2103T-W10-1/tp/pull/96), [PR #98](https://github.com/AY2324S1-CS2103T-W10-1/tp/pull/98), [PR #110](https://github.com/AY2324S1-CS2103T-W10-1/tp/pull/110), [PR #204](https://github.com/AY2324S1-CS2103T-W10-1/tp/pull/204), [PR #209](https://github.com/AY2324S1-CS2103T-W10-1/tp/pull/209)


* **Contributions beyond the project team**:
  * Reported bugs and suggestions for other teams in the class: [PED repo](https://github.com/LHeng1/ped)
