---
layout: page
title: Nathaniel's Project Portfolio Page
---

### Project: Spend N Split

SpendNSplit (SNS) is a **desktop app for managing expenses and contacts, optimized for use via a Command Line Interface** while having the benefits of a Graphical User Interface.

By typing fast, SNS can get your personal management tasks done faster than traditional GUI apps.

**Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=nathanielcalimag&)

##### Extended Storage to Support Transactions
- **What it does**: Extended the storage to support our new model `Transaction`, allowing users to store a second core entity on top of the `Person` in the storage file.
- **Justification**: This was one of the core features that was implemented early on in order to speed up our development process. It allows the user to save the `UniqueTransactionList` into storage, which is a core functionality.
- **Highlights**: This was a large feature, requiring many code changes and a large amount of testing (e.g. creating transaction and expense builders, and typical transactions).

##### Added sample transaction data
- **What it does**: Added sample transaction data that will be used in SNS when there's no initial storage. Ensured the transaction data is sufficiently linked to the person data.
- **Justification**: This feature ensures that the user is able to understand our application better, and how we use the `Person` and `Transaction` models. This also gives them a starting base to use our commands for the first time.
- **Highlights**: This feature required a good understanding of our target audience in order to curate a relevant sample data that our users will relate to. Furthermore, this needed a good understanding of the `Transaction` and `Person` model to create meaningful data that are closely related to properly demonstrate the person-transaction relationship.

##### Enhanced UI from dark-theme to light-theme
- **What it does**: Utilise a sleek design and light colour theme to enhance the user interface.
- **Justification**: This feature gives SNS a unique look that our users will remember.
- **Highlights**: This feature required much design consideration and user feedback.

##### Added Expense Model (renamed to Portion) 
- **What it does**: Used in the `Transaction` model to represent the portion of the cost that a person owes to the payee.

##### `settlePerson` Command
- **What it does**: Added a command to settle any outstanding balances with a person by creating a single transaction that cancels out the balance.
- **Justification**: This feature allows users to leverage the precision and automation of SNS to calculate and settle the balance of a user's contact.
- **Highlights**: This feature was complicated, requiring calculations in order to take potentially fractional weights and amounts to distribute each person's balance against the user. Additionally, there were many other cases that had to be considered: settling a balance with a person that does not exist, the person not having any outstanding balance to settle, the command resulting in duplicate transactions against the `UniqueTransactionList`.

##### User Guide
- Added section on explaining Relevant Transactions.
- Added acknowledgements (UG / DG).
- Update `about.us` and `index.html`.

##### Developer Guide
- Updated `Storage` and `Logic` components under the design section .
- Added appendix sections: 10 planned enhancements, 21 user stories, 10 use cases, 9 NFRs.

##### Team
- [48 PRs contributed](https://github.com/AY2324S1-CS2103T-W17-3/tp/pulls?q=is%3Apr+author%3Anathanielcalimag+), [67 PRs reviewed](https://github.com/AY2324S1-CS2103T-W17-3/tp/pulls?q=is%3Apr+reviewed-by%3A%40me+), [34 issues raised](https://github.com/AY2324S1-CS2103T-W17-3/tp/issues?q=is%3Aissue+author%3Anathanielcalimag+)
- [PR comments](https://nus-cs2103-ay2324s1.github.io/dashboards/contents/tp-comments.html#156-nath-imag-nathanielcalimag-16-comments)

##### Community
- [15 PE-D issues raised](https://github.com/AY2324S1-CS2103T-W16-1/tp/issues?q=%5BPE-D%5D%5BTester+E%5D+) and [2 forum questions asked](https://github.com/nus-cs2103-AY2324S1/forum/issues?q=is%3Aissue+author%3Anathanielcalimag)
