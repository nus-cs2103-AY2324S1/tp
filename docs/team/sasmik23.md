---
layout: page
title: Mikhil's Project Portfolio Page
---

## Project: Tran$act

Tran$act is a desktop application used to record transactions. The user interacts with it using a CLI 
allowing quick management of transactions, along with being able to view an overall snapshot of their 
balance sheet. It is built java, developed for small business accountant looking for a cheap solution.

### **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=sasmik23&breakdown=true)

### **Enhancements implemented**
1. Delete Transaction: [PR 56](https://github.com/AY2324S1-CS2103T-W13-3/tp/pull/56)
   - `del`: Deletes transaction by id from transaction list.
2. Create Unique Person Index: [PR 78](https://github.com/AY2324S1-CS2103T-W13-3/tp/pull/78)
   - Created new PersonID class and added it as an attribute to Person Class. This allows for a transaction to be tagged to a person by their ID
   - Refactored Address book to use UniqueHashMap(Created by cxo05 in [PR 64](https://github.com/AY2324S1-CS2103T-W13-3/tp/pull/64))
3. Export of transactions and staff: [PR 120](https://github.com/AY2324S1-CS2103T-W13-3/tp/pull/120)
   - Created export button in menu bar, allowing user to export transactions(csv format) or staff list(json format) to chosen location.
4. Wrote additional test cases 
   - AddTransactionCommandTest: [PR 217](https://github.com/AY2324S1-CS2103T-W13-3/tp/pull/217)
5. Bug fixes from Mock PE: [PR 190](https://github.com/AY2324S1-CS2103T-W13-3/tp/pull/190)
   - Handle duplicate persons in Address Book [Issue 151](https://github.com/AY2324S1-CS2103T-W13-3/tp/issues/151)

### **User Guide**
1. Move draft UG to github: [PR 17](https://github.com/AY2324S1-CS2103T-W13-3/tp/pull/17)
2. Wrote introductory section and added common FAQs: [PR 106](https://github.com/AY2324S1-CS2103T-W13-3/tp/pull/106)
3. Update on behaviour of Staff indexing: [Issue 157](https://github.com/AY2324S1-CS2103T-W13-3/tp/issues/157)


### **Developer Guide**

### **Review Contributions**
1. In charge of UG: 
   - Reviewed and merged following PRS: [PR 193](https://github.com/AY2324S1-CS2103T-W13-3/tp/pull/193), [PR 185](https://github.com/AY2324S1-CS2103T-W13-3/tp/pull/185), [PR 107](https://github.com/AY2324S1-CS2103T-W13-3/tp/pull/107), [PR 89](https://github.com/AY2324S1-CS2103T-W13-3/tp/pull/89), [PR 87](https://github.com/AY2324S1-CS2103T-W13-3/tp/pull/87)
