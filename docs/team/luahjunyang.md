---
  layout: default.md
  title: "Luah Jun Yang's Project Portfolio Page"
---

### Project: InsuraHub

InsuraHub - InsuraHub is a CLI application that targets financial advisors that type fast and prefers to use CLI over GUI to use the app.
The application is mainly used by financial advisors in their day-to-day planning and scheduling of meetings with clients,
assisting them by providing quick access to important information of each client stored in the database, displayed on the UI of InsuraHub.

While InsuraHub has limited functionalities at this stage, there are further plans to improve the application with the timeline being set in place.

Given below are my contributions to the project.

- **New Feature**: Added the ability to addTags to clients.

  - What it does: It allows the users to add any number of tags to a specified client currently listed on the Ui.
  - Justification: This feature improves the product as it improves on the existing ability to edit tags by adding on new tags while preserving the existing set of tags of each client.
  - Highlights: This enhancement is challenging for me as our team had yet to fully understand the existing implementation of AB3. Thus, I only had a rough start in it with Ji Xuan finished the remaining implementation of it after we further assigned the new features for V1.2.
  - Credits: EditCommand AB3

- **New Feature**: Added the ability filter for client objects based on any number of tags.

  - What it does: It allows the users to filter for clients based on any number of tags that the advisor would want to filter for. There should be a minimum of one tag being filtered for and as many tags as the user desires can be entered. The UI will display the list of all clients that have the tags being filtered for.
  - Justification: This feature improves the product as it provides users a convenient way for them to group their clients systematically, e.g. all colleageus, all friends, all family members. This is pertinent for financial advisors as their client base can be very diverse and the implementation of tags allows for quick grouping of clients.
  - Highlights: This enhancement is challenging for me as it requires the implementation of a predicate which is similar to the existing implementation of the find command while being more specific and only filtering for tags. This is also why we initially assigned this feature to be split up amongst all members thus making it more challenging for me to complete it alone.
  - Credits: FindCommand AB3

- **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=LuahJunYang&breakdown=false&sort=groupTitle%20dsc&sortWithin=title&since=2023-09-22&timeframe=commit&mergegroup=&groupSelect=groupByRepos)

- **Project management**:

  - Participated in releases v1.1 - v1.4 on github with 2 releases currently, namely v1.3.trial and v1.3.final, v1.4 will be released when it is done.

  * Fixed majority of checkstyle and other errors to ensure checks on GitHub were passed after v1.3.

- **Enhancements to existing features**:

  - Ability to filter via just tags instead of finding any keyword in [\#74](https://github.com/AY2324S1-CS2103-W14-1/tp/pull/74)

- **Documentation**:

  - User Guide:

    - Added documentation for the features `addTag` and `deleteTag` [\#38](https://github.com/AY2324S1-CS2103-W14-1/tp/pull/38)
    - Added documentation for the features `filter` [\#125](https://github.com/AY2324S1-CS2103-W14-1/tp/pull/125)
    - Added documentation for the features `addPolicy` and `removePolicy` [\#125](https://github.com/AY2324S1-CS2103-W14-1/tp/pull/125)
    - Updated Command Summary table to reflect all the final commands that have been implemented [\#196](https://github.com/AY2324S1-CS2103-W14-1/tp/pull/196)

  - Developer Guide:
    - Added terms Tag, Client Priority, Usage Instructions to the Glossary [\#39](https://github.com/AY2324S1-CS2103-W14-1/tp/pull/39)
    - Added documentation for the `filter` tag command, including a sequence diagram [\#92](https://github.com/AY2324S1-CS2103-W14-1/tp/pull/92)
    - Added documentation for the `addTag`, `addPolicy`, `removePolicy` commands, including sequence and activity diagrams [\#228](https://github.com/AY2324S1-CS2103-W14-1/tp/pull/228)
    - Added planned enhancements appendix [\#228](https://github.com/AY2324S1-CS2103-W14-1/tp/pull/228)
    - Added sequence and activity diagrams for `deleteTag`, `filterTag`, `addPolicy`, `removePolicy` commands [\#233](https://github.com/AY2324S1-CS2103-W14-1/tp/pull/233)
    - Added documentation and diagrams for `filterPolicy` and `ToggleMode` commands [\#235](https://github.com/AY2324S1-CS2103-W14-1/tp/pull/235)

- **Community**:
  - Reviewed 14 Pull Requests and provided relevant feedback
  - Reviewed 4 IPs in total
- **Tools**:
  - to be added soon
