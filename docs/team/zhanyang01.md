---
  layout: default.md
  title: "Ng Zhan Yang's Project Portfolio Page"
---

## Ng Zhan Yang's Project Portfolio Page

### Project: InsuraHub

InsuraHub - InsuraHub is a CLI application that targets financial advisors that type fast and prefers to use CLI over GUI to use the app.
The application is mainly used by financial advisors in their day-to-day planning and scheduling of meetings with clients,
assisting them by providing quick access to important information of each client stored in the database, displayed on the UI of InsuraHub.

While InsuraHub has limited functionalities at this stage, there are further plans to improve the application with the timeline being set in place.

Given below are my contributions to the project.

- **New Feature**: Added the ability to allow users to add the preferred contact method of their clients.

  - What it does: It allows the users to add the client's preferred contact method, it is stored as a string and there are currently 2 methods of preferred contact, email and phone, else it will be empty.
  - Justification: This feature improves the product as it provides users a convenient way to be able to tailer to the clients preferred form of contact without having to remember the clients preferred form of contact by heart.
  - Highlights: This enhancement is challenging for me as it is my first feature after the remark tutorial and I am still not used to the AB3 codebase.
  - Credits: AB3 Remark tutorial

- **New Feature**: Added the ability to group clients by their preferred meeting region.

  - What it does: Clients now have one extra attribute, the preferredMeetingRegion attribute and this command allow clients to be filtered based on the region that they would prefer to meet.
  - Justification: This feature improves the product significantly as it is tailered to the busy Financial Advisor schedule of having to meet many clients on a daily basis. This allows the user to plan where to meet different clients based on their preferred meeting region, allowing the user to minimise travelling from one place to another to meet different clients, providing convenience to the user.
  - Highlights: This enhancement requires me to do edits in other commands such as add and edit, which is not needed in preferredContact command feature, hence it is not trivial as many test cases have to be edited to accommodate this new feature.
  - Credits: FindCommand AB3, AB3 Remark tutorial

- **New Feature**: Added the ability to filter clients by their policy description

  - What it does: This allow the user to be able to filter clients by the clients policy description, the list of users with the particular policy will be returned.
  - Justification: This feature improves the product as it allows users to be able return clients who have a particular policy description, for example Cancer Plans or Accident Plans, as updates for clients for changes in how their policy works usually happen for insurance plans of the same description type, such as changes to Cancer Plans will affect all users who have bought Cancer Plans and hence this simplifies the user job as they will be able to filter to the clients who have the plans to update the clients about the changes.
  - Highlights: This policy is the first one that I work on that require working on the policy of the clients.
  - Credits: FindCommand AB3

- **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=zhanyang01&breakdown=true)

- **Project management**:

  - Participated in releases v1.1 - v1.4 on github with 2 releases currently, namely v1.3.trial and v1.3.final, v1.4 will be released when it is done.
  - Helped in creation of project board and milestones of the project.
  - Managed issue tracking and assigning of issues to team members.

- **Enhancements to existing features**:

  - Add preferred contact command in [\#72](https://github.com/AY2324S1-CS2103-W14-1/tp/pull/72)
  - Add preferred meeting region attribute and groupMeetingCommand in [\#103](https://github.com/AY2324S1-CS2103-W14-1/tp/pull/103)
  - Add filter by policy description in [\#117](https://github.com/AY2324S1-CS2103-W14-1/tp/pull/117)

- **Documentation**:

  - User Guide:
    - Add commands to user guide and refined in [\#89](https://github.com/AY2324S1-CS2103-W14-1/tp/pull/89)
  - Developer Guide:
    - Added implementation details for preferredContact command in [\#91](https://github.com/AY2324S1-CS2103-W14-1/tp/pull/91)

- **Community**:

  - Reviewed 24 PRs and provided feedback
  - Reviewed 4 other IPs, 2 during the peer review in week 8 and 2 during the week 4
  - Contributed to 13 forum posts and discussions
  - Reported 8 bugs and suggestions for other teams in the class (examples: [1](https://github.com/AY2324S1-CS2103T-W08-2/tp/issues/130), [2](https://github.com/AY2324S1-CS2103T-W08-2/tp/issues/122))
