
    var pageVueRenderFn = function noop (a, b, c) {};
    var pageVueStaticRenderFns = [function anonymous(
) {
with(this){return _c('blockquote',[_c('p',[_v("Perfection is achieved, not when there is nothing more to add, but when there is nothing left to take away.")]),_v(" "),_c('p',[_v("—  Antoine de Saint-Exupery")])])}
},function anonymous(
) {
with(this){return _c('p',[_v("When working on an existing code base, you will most likely find that some features that are no longer necessary.\nThis tutorial aims to give you some practice on such a code 'removal' activity by removing the "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("address")]),_v(" field from "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("Person")]),_v(" class.")])}
},function anonymous(
) {
with(this){return _c('div',{staticClass:"alert alert-success",attrs:{"markdown":"span"}},[_c('p',[_c('strong',[_v("If you have done the "),_c('a',{attrs:{"href":"/docs/tutorials/AddRemark.html"}},[_v("Add "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("remark")]),_v(" command tutorial")]),_v("  already")]),_v(", you should know where the code had to be updated to add the field "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("remark")]),_v(". From that experience, you can deduce where the code needs to be changed to "),_c('em',[_v("remove")]),_v(" that field too. The removing of the "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("address")]),_v(" field can be done similarly.\n"),_c('br'),_v(" "),_c('br'),_v("\nHowever, if you have no such prior knowledge, removing a field can take a quite a bit of detective work. This tutorial takes you through that process. "),_c('strong',[_v("At least have a read even if you don't actually do the steps yourself.")])])])}
},function anonymous(
) {
with(this){return _c('h2',{attrs:{"id":"safely-deleting-address"}},[_v("Safely deleting "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("Address")]),_c('a',{staticClass:"fa fa-anchor",attrs:{"href":"#safely-deleting-address","onclick":"event.stopPropagation()"}})])}
},function anonymous(
) {
with(this){return _c('p',[_v("IntelliJ IDEA provides a refactoring tool that can identify "),_c('em',[_v("most")]),_v(" parts of a removal easily. Let’s try to use it as much as we can.")])}
},function anonymous(
) {
with(this){return _c('h3',{attrs:{"id":"assisted-refactoring"}},[_v("Assisted refactoring"),_c('a',{staticClass:"fa fa-anchor",attrs:{"href":"#assisted-refactoring","onclick":"event.stopPropagation()"}})])}
},function anonymous(
) {
with(this){return _c('p',[_v("The "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("address")]),_v(" field in "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("Person")]),_v(" is actually an instance of the "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("seedu.address.model.person.Address")]),_v(" class. Since removing the "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("Address")]),_v(" class will break the application, we start by identifying "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("Address")]),_v("'s usages. This allows us to see code that depends on "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("Address")]),_v(" to function properly and edit them on a case-by-case basis. Right-click the "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("Address")]),_v(" class and select "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("Refactor")]),_v(" > "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("Safe Delete")]),_v(" through the menu.")])}
},function anonymous(
) {
with(this){return _c('ul',[_c('li',[_v("💡 To make things simpler, you can unselect the options "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("Search in comments and strings")]),_v(" and "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("Search for text occurrences")])])])}
},function anonymous(
) {
with(this){return _c('p',[_c('a',{attrs:{"href":"/docs/images/remove/UnsafeDelete.png","target":"_self"}},[_c('img',{staticClass:"img-fluid",attrs:{"src":"/docs/images/remove/UnsafeDelete.png","alt":"Usages detected"}})])])}
},function anonymous(
) {
with(this){return _c('p',[_v("Choose to "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("View Usages")]),_v(" and you should be presented with a list of "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("Safe Delete Conflicts")]),_v(". These conflicts describe locations in which the "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("Address")]),_v(" class is used.")])}
},function anonymous(
) {
with(this){return _c('p',[_c('a',{attrs:{"href":"/docs/images/remove/SafeDeleteConflicts.png","target":"_self"}},[_c('img',{staticClass:"img-fluid",attrs:{"src":"/docs/images/remove/SafeDeleteConflicts.png","alt":"List of conflicts"}})])])}
},function anonymous(
) {
with(this){return _c('p',[_v("Remove usages of "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("Address")]),_v(" by performing "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("Safe Delete")]),_v("s on each entry i.e., double-click on the entry (which takes you to the code in concern, right-click on that entity, and choose "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("Refactor")]),_v(" -> "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("Safe delete")]),_v(" as before). You will need to exercise discretion when removing usages of "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("Address")]),_v(". Functions like "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("ParserUtil#parseAddress()")]),_v(" can be safely removed but its usages must be removed as well. Other usages like in "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("EditPersonDescriptor")]),_v(" may require more careful inspection.")])}
},function anonymous(
) {
with(this){return _c('p',[_v("Let’s try removing references to "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("Address")]),_v(" in "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("EditPersonDescriptor")]),_v(".")])}
},function anonymous(
) {
with(this){return _c('ol',[_c('li',[_c('p',[_v("Safe delete the field "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("address")]),_v(" in "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("EditPersonDescriptor")]),_v(".")])]),_v(" "),_c('li',[_c('p',[_v("Select "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("Yes")]),_v(" when prompted to remove getters and setters.")])]),_v(" "),_c('li',[_c('p',[_v("Select "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("View Usages")]),_v(" again."),_c('br'),_v(" "),_c('a',{attrs:{"href":"/docs/images/remove/UnsafeDeleteOnField.png","target":"_self"}},[_c('img',{staticClass:"img-fluid",attrs:{"src":"/docs/images/remove/UnsafeDeleteOnField.png","alt":"UnsafeDeleteOnField"}})])])]),_v(" "),_c('li',[_c('p',[_v("Remove the usages of "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("address")]),_v(" and select "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("Do refactor")]),_v(" when you are done.")]),_v(" "),_c('div',{staticClass:"alert alert-primary",attrs:{"markdown":"span"}},[_c('p',[_v("💡 "),_c('strong',[_v("Tip:")]),_v(" Removing usages may result in errors. Exercise discretion and fix them. For example, removing the "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("address")]),_v(" field from the "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("Person")]),_v(" class will require you to modify its constructor.")])])]),_v(" "),_c('li',[_c('p',[_v("Repeat the steps for the remaining usages of "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("Address")])])])])}
},function anonymous(
) {
with(this){return _c('h3',{attrs:{"id":"manual-refactoring"}},[_v("Manual refactoring"),_c('a',{staticClass:"fa fa-anchor",attrs:{"href":"#manual-refactoring","onclick":"event.stopPropagation()"}})])}
},function anonymous(
) {
with(this){return _c('p',[_v("Unfortunately, there are usages of "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("Address")]),_v(" that IntelliJ IDEA cannot identify. You can find them by searching for instances of the word "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("address")]),_v(" in your code ("),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("Edit")]),_v(" > "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("Find")]),_v(" > "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("Find in path")]),_v(").")])}
},function anonymous(
) {
with(this){return _c('p',[_v("Places of interest to look out for would be resources used by the application. "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("main/resources")]),_v(" contains images and "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("fxml")]),_v(" files used by the application and "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("test/resources")]),_v(" contains test data. For example, there is a "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("$address")]),_v(" in each "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("PersonCard")]),_v(" that has not been removed nor identified.")])}
},function anonymous(
) {
with(this){return _c('p',[_v("!["),_c('eq',{pre:true},[_c('span',{pre:true,attrs:{"class":"katex"}},[_c('span',{pre:true,attrs:{"class":"katex-mathml"}},[_c('math',{pre:true,attrs:{"xmlns":"http://www.w3.org/1998/Math/MathML"}},[_c('semantics',{pre:true},[_c('mrow',{pre:true},[_c('mi',{pre:true},[_v("a")]),_c('mi',{pre:true},[_v("d")]),_c('mi',{pre:true},[_v("d")]),_c('mi',{pre:true},[_v("r")]),_c('mi',{pre:true},[_v("e")]),_c('mi',{pre:true},[_v("s")]),_c('mi',{pre:true},[_v("s")]),_c('mo',{pre:true,attrs:{"stretchy":"false"}},[_v("]")]),_c('mo',{pre:true,attrs:{"stretchy":"false"}},[_v("(")]),_c('mi',{pre:true,attrs:{"mathvariant":"normal"}},[_v(".")]),_c('mi',{pre:true,attrs:{"mathvariant":"normal"}},[_v(".")]),_c('mi',{pre:true,attrs:{"mathvariant":"normal"}},[_v("/")]),_c('mi',{pre:true},[_v("i")]),_c('mi',{pre:true},[_v("m")]),_c('mi',{pre:true},[_v("a")]),_c('mi',{pre:true},[_v("g")]),_c('mi',{pre:true},[_v("e")]),_c('mi',{pre:true},[_v("s")]),_c('mi',{pre:true,attrs:{"mathvariant":"normal"}},[_v("/")]),_c('mi',{pre:true},[_v("r")]),_c('mi',{pre:true},[_v("e")]),_c('mi',{pre:true},[_v("m")]),_c('mi',{pre:true},[_v("o")]),_c('mi',{pre:true},[_v("v")]),_c('mi',{pre:true},[_v("e")]),_c('mi',{pre:true,attrs:{"mathvariant":"normal"}},[_v("/")])],1),_c('annotation',{pre:true,attrs:{"encoding":"application/x-tex","v-pre":""}},[_v("address](../images/remove/")])],1)],1)],1),_c('span',{pre:true,attrs:{"class":"katex-html","aria-hidden":"true"}},[_c('span',{pre:true,attrs:{"class":"base"}},[_c('span',{pre:true,attrs:{"class":"strut","style":"height:1em;vertical-align:-0.25em;"}}),_c('span',{pre:true,attrs:{"class":"mord mathnormal"}},[_v("a")]),_c('span',{pre:true,attrs:{"class":"mord mathnormal"}},[_v("dd")]),_c('span',{pre:true,attrs:{"class":"mord mathnormal"}},[_v("ress")]),_c('span',{pre:true,attrs:{"class":"mclose"}},[_v("]")]),_c('span',{pre:true,attrs:{"class":"mopen"}},[_v("(")]),_c('span',{pre:true,attrs:{"class":"mord"}},[_v("../")]),_c('span',{pre:true,attrs:{"class":"mord mathnormal"}},[_v("ima")]),_c('span',{pre:true,attrs:{"class":"mord mathnormal","style":"margin-right:0.03588em;"}},[_v("g")]),_c('span',{pre:true,attrs:{"class":"mord mathnormal"}},[_v("es")]),_c('span',{pre:true,attrs:{"class":"mord"}},[_v("/")]),_c('span',{pre:true,attrs:{"class":"mord mathnormal"}},[_v("re")]),_c('span',{pre:true,attrs:{"class":"mord mathnormal"}},[_v("m")]),_c('span',{pre:true,attrs:{"class":"mord mathnormal"}},[_v("o")]),_c('span',{pre:true,attrs:{"class":"mord mathnormal","style":"margin-right:0.03588em;"}},[_v("v")]),_c('span',{pre:true,attrs:{"class":"mord mathnormal"}},[_v("e")]),_c('span',{pre:true,attrs:{"class":"mord"}},[_v("/")])])])])]),_v("address.png)")],1)}
},function anonymous(
) {
with(this){return _c('p',[_v("A quick look at the "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("PersonCard")]),_v(" class and its "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("fxml")]),_v(" file quickly reveals why it slipped past the automated refactoring.")])}
},function anonymous(
) {
with(this){return _c('p',[_c('strong',[_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("PersonCard.java")])])])}
},function anonymous(
) {
with(this){return _c('pre',[_c('code',{pre:true,attrs:{"class":"hljs"}},[_c('span',[_v("...\n")]),_c('span',[_v("@FXML\n")]),_c('span',[_v("private Label address;\n")]),_c('span',[_v("...\n")])])])}
},function anonymous(
) {
with(this){return _c('p',[_c('strong',[_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("PersonCard.fxml")])])])}
},function anonymous(
) {
with(this){return _c('pre',[_c('code',{pre:true,attrs:{"class":"hljs"}},[_c('span',[_v("...\n")]),_c('span',[_v("<Label fx:id=\"phone\" styleClass=\"cell_small_label\" text=\"\\$phone\" />\n")]),_c('span',[_v("<Label fx:id=\"address\" styleClass=\"cell_small_label\" text=\"\\$address\" />\n")]),_c('span',[_v("<Label fx:id=\"email\" styleClass=\"cell_small_label\" text=\"\\$email\" />\n")]),_c('span',[_v("...\n")])])])}
},function anonymous(
) {
with(this){return _c('p',[_v("After removing the "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("Label")]),_v(", we can proceed to formally test our code. If everything went well, you should have most of your tests pass. Fix any remaining errors until the tests all pass.")])}
},function anonymous(
) {
with(this){return _c('h2',{attrs:{"id":"tidying-up"}},[_v("Tidying up"),_c('a',{staticClass:"fa fa-anchor",attrs:{"href":"#tidying-up","onclick":"event.stopPropagation()"}})])}
},function anonymous(
) {
with(this){return _c('p',[_v("At this point, your application is working as intended and all your tests are passing. What’s left to do is to clean up references to "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("Address")]),_v(" in test data and documentation.")])}
},function anonymous(
) {
with(this){return _c('p',[_v("In "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("src/test/data/")]),_v(", data meant for testing purposes are stored. While keeping the "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("address")]),_v(" field in the json files does not cause the tests to fail, it is not good practice to let cruft from old features accumulate.")])}
},function anonymous(
) {
with(this){return _c('p',[_c('strong',[_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("invalidPersonAddressBook.json")]),_v(":")])])}
},function anonymous(
) {
with(this){return _c('pre',[_c('code',{pre:true,attrs:{"class":"hljs json"}},[_c('span',[_v("{\n")]),_c('span',[_v("  "),_c('span',{pre:true,attrs:{"class":"hljs-attr"}},[_v("\"persons\"")]),_v(": [ {\n")]),_c('span',[_v("    "),_c('span',{pre:true,attrs:{"class":"hljs-attr"}},[_v("\"name\"")]),_v(": "),_c('span',{pre:true,attrs:{"class":"hljs-string"}},[_v("\"Person with invalid name field: Ha!ns Mu@ster\"")]),_v(",\n")]),_c('span',[_v("    "),_c('span',{pre:true,attrs:{"class":"hljs-attr"}},[_v("\"phone\"")]),_v(": "),_c('span',{pre:true,attrs:{"class":"hljs-string"}},[_v("\"9482424\"")]),_v(",\n")]),_c('span',[_v("    "),_c('span',{pre:true,attrs:{"class":"hljs-attr"}},[_v("\"email\"")]),_v(": "),_c('span',{pre:true,attrs:{"class":"hljs-string"}},[_v("\"hans@example.com\"")]),_v(",\n")]),_c('span',[_v("    "),_c('span',{pre:true,attrs:{"class":"hljs-attr"}},[_v("\"address\"")]),_v(": "),_c('span',{pre:true,attrs:{"class":"hljs-string"}},[_v("\"4th street\"")]),_v("\n")]),_c('span',[_v("  } ]\n")]),_c('span',[_v("}\n")])])])}
},function anonymous(
) {
with(this){return _c('p',[_v("You can go through each individual "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("json")]),_v(" file and manually remove the "),_c('code',{pre:true,attrs:{"class":"hljs inline no-lang"}},[_v("address")]),_v(" field.")])}
}];
  