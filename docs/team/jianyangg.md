---
layout: default.md
title: "Lim Jian Yang's Project Portfolio Page"
---

### Project: HomeBoss

HomeBoss is a desktop application used for managing deliveries for home business owners. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 19 kLoC.

Given below are my contributions to the project.

* **New Feature:** Added ability to register for an account in HomeBoss. The user is also allowed to delete the account if they wish to.
  * What it does: Allows the user to register for an account in HomeBoss. Upon registering for an account, the user
    will have their user details stored in a file, with the password hashed for enhanced security.
  * Justification: This feature improves the product as it allows the user to secure their data and prevent unlawful
    access to the data through the GUI.
  * Highlights: The password is hashed using SHA-256 hashing algorithm, which is a one-way hashing algorithm. This
    ensures that the password cannot be retrieved from the hash, and the password is stored securely. As the user
    should not be able to execute most of the commands without logging in (and by
    extension, having an account registered), the blocking of
    the execution of commands that deal with the Customer and Delivery database was also implemented. This register command also prepared for the recovery of an account in HomeBoss, by storing the user's secret question and answer in the user's account file.
  * Credits: The implementation of the SHA-256 hashing algorithm was adapted from [this](https://www.baeldung.com/sha-256-hashing-java)
    article.
  * Related Pull Requests: [\#124](https://github.com/AY2324S1-CS2103T-T13-3/tp/pull/124), [\#194](https://github.com/AY2324S1-CS2103T-T13-3/tp/pull/194), [\#225](https://github.com/AY2324S1-CS2103T-T13-3/tp/pull/225)

* **New Feature**: Added ability to recover an account in HomeBoss
    * What it does: Allows the user to recover their account in HomeBoss in case they forget their password.
    * Justification: This feature allows for corrective action to be taken in case the user forgets their password. This
      in turn ensures that they are able to access their data through the GUI if they forget their password.
    * Highlights: This feature required the user to first register for an account with a secret question and answer. The secret question is then shown to the user when prompted, and they are allowed to recover their account by resetting their password is their answer matches with the stored answer to the secret question.
    * Related Pull Request: [\#198](https://github.com/AY2324S1-CS2103T-T13-3/tp/pull/198)

* **Major Enhancement:** Revamped the UI experience of the user
  * What it does: This enhancement reordered the UI elements and gave the GUI a more aesthetic look. The UI is also made more personalised
    for the user by displaying the user's username in the UI.
  * Justification: The AB3's implementation of the UI has the error messages squeezed into a narrow rectangle that frequently required scrolling to get through. Thus for displaying things like `Customer` or `Delivery` details, it's difficult to display all the information in a single page. This enhancement allows the user to read more data at once before having to scroll. The UI was also made to feel friendly and welcoming to the user by displaying the user's username in the UI.
  * Highlights: Each ItemCard is redesigned to include details specific to our application. The footer was also edited to include information such as if the user is logged out or hasn't registered an account or if the user is logged in (where it'll then display the user's username instead). Also fixed Ui text overflow issue in AB3 such that text is wrapped.
  * Related Pull Requests: [\#271](https://github.com/AY2324S1-CS2103T-T13-3/tp/pull/271), [\#222](https://github.com/AY2324S1-CS2103T-T13-3/tp/pull/222), [\#119](https://github.com/AY2324S1-CS2103T-T13-3/tp/pull/119), [\#339](https://github.com/AY2324S1-CS2103T-T13-3/tp/pull/339)
  
* **Enhancements to existing features:** Updated the `delete ID` command from AB3 to `customer delete ID`, which allows for the deletion of `Delivery` objects linked to the deleted `Customer`.
  * What it does: Once a Customer is deleted, all Deliveries linked to the Customer will also be deleted.
  * Justification: If a `Customer` is no longer relevant to a home-based business (i.e., all the `Deliveries` are completed for a one-off `Customer`), all the `Deliveries` associated with this `Customer` will become irrelevant as well.
  * Highlights: `Customer` deletion will cascade delete all `Deliveries` associated with the `Customer`.
  * Credits: This method was adapated from AB3's `delete ID` command.
  * Related Pull Requests: [\#109](https://github.com/AY2324S1-CS2103T-T13-3/tp/pull/109), [\#230](https://github.com/AY2324S1-CS2103T-T13-3/tp/pull/230)

* **Code contributed:** [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=jianyangg&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code)

* **Community:**
  * PRs reviewed: As of 14th November, I have reviewed 72 PRs [(GitHub)](https://github.com/AY2324S1-CS2103T-T13-3/tp/pulls?q=is%3Apr+reviewed-by%3Ajianyangg+is%3Aclosed+)
  * Reported bugs for other teams during PE Dry Run: I found 11 [issues](https://github.com/jianyangg/ped/issues) with my allocated PE-Dry-Run project.

* **Documentation:**
  * User Guide:
    * Added and updated documentation for the features `register`, `recover account`, `delete account`, `customer edit` and refined content of User Guide.
    * Related Pull Requests: [\#454](https://github.com/AY2324S1-CS2103T-T13-3/tp/pull/454/files), [\#541](https://github.com/AY2324S1-CS2103T-T13-3/tp/pull/541)

  * Developer Guide:
    * Modified the Ui class diagram for our application to include the new classes added / modified classes and refine content of Developer Guide.
    * Related Pull Requests: [\#546](https://github.com/AY2324S1-CS2103T-T13-3/tp/pull/546), [\#498](https://github.com/AY2324S1-CS2103T-T13-3/tp/pull/498)


