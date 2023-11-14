---
  layout: default.md
  title: Ji-xuan's Project Portfolio Page"
---

### Project: InsuraHub

InsuraHub - InsuraHub is a CLI application that targets financial advisors that type fast and prefers to use CLI over GUI to use the app.
The application is mainly used by financial advisors in their day-to-day planning and scheduling of meetings with clients,
assisting them by providing quick access to important information of each client stored in the database, displayed on the UI of InsuraHub.

While InsuraHub has limited functionalities at this stage, there are further plans to improve the application with the timeline being set in place.

Given below are my contributions to the project.

- **New Feature**: Add tags to clients

  - What it does: Tags clients with a short description of their relationship to the user. E.g. "friend".
  - Justification: This allows for the user to categorise the clients into similar/ different groups of tags.
  - Highlights: Adding this command was challenging as it required the ability to parse in several commands at a time.
  - Credits: Edit command AB3.

- **New Feature**: Added the ability to delete tags from clients

  - What it does: Delete tags input by the user from a particular client if that tag currently exists in the client's tag list
  - Justification: This allows for the user to delete tags that are no longer relevant to a given client.
  - Highlights: Consideration had to be put into the fact that a user might provide tags that does not belong to a client.
  - Credits: Edit command AB3.

- **New Feature**: Added the ability to add Policy to clients

  - What it does: add policies to clients
  - Justification: This allows for the user to add a policy's details to a client whom just purchased a policy.
  - Highlights: As policies take in many arguments, there was no previous feature to reference from.
    Adding the policy also meant that I had to change many current implementations of the person object
  - Credits: N/A.

- **New Feature**: Added the ability to remove policies from clients

  - What it does: Remove policies that a client owns.
  - Justification: This allows for the user to delete policies that are no longer relevant to a given client.
  - Highlights: Implemented remove policies by using indexing as it was too challenging to do so any other way.
  - Credits: N/A

- **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=johnnythesnake12&breakdown=false&sort=groupTitle%20dsc&sortWithin=title&since=2023-09-22&timeframe=commit&mergegroup=&groupSelect=groupByRepos)

- **Project management**:

  - Participated in releases v1.1 - v1.4 on Github with 2 releases currently, namely v1.3.trial and v1.3.final, v1.4 will be released when it is done.
  - Ensured that all developers followed the proper workflow of creating pull requests and reviewing before merging.

- **Enhancements to existing features**:

  - Added new constructors for `Person` object to accommodate for `Policy` (Pull request [\#104](https://github.com/AY2324S1-CS2103-W14-1/tp/pull/104/commits/e5ce6839fbfa07fb47a5131921c03f9a59dd5cae))
  - Added `Policy` to the UI so that it can be displayed on the UI (Pull request [\#106](https://github.com/AY2324S1-CS2103-W14-1/tp/pull/106/commits/e05ee761403d82a8fc03cb17fd89ff4127d5411f))

- **Documentation**:

  - User Guide:
    - Added documentation for `changePassword` [#203](https://github.com/AY2324S1-CS2103-W14-1/tp/commit/4a2670e6019bb58d89be161599c448ec059d6474)
  - Developer Guide:
    - Added documentation for the features `deleteTag` [#82](https://github.com/AY2324S1-CS2103-W14-1/tp/pull/82/commits/09e20278cb09219eb859767e9b1500a3e9b80bb8)
  - Images:
    - Added preliminary UI mockup for `deleteTag`

- **Community**:

  - 19 PRs reviewed
  - Reviewed 4 other individual Projects of classmates
  - Reported 7 bugs in a team project by another team

- **Tools**:
