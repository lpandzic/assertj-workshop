#Assertj Workshop

Workshop for the [Infobip Devdays 2015 conference][infobip-devdays].

This workshop is aimed at the Java developers familiar with [JUnit][junit], [Hamcrest][hamcrest] and [Mockito][mockito]. [AssertJ][assertj] is a relatively new library which provides an alternative to assertions API provided by JUnit and Hamcrest.

The goal of the workshop is to provide hands on experience with some of the assertion API provided by AssertJ.
Some of the examples are similar to the ones provided in the AssertJ official documentation and others extend them in order to keep clear focus on the goal and workshop flow transparent.

Flow:

1. Examine in each test in the test package, ideally following test classes in alphabetical order.
2. Replace every JUnit/Hamcrest assertion with AssertJ assertion, most of the multiline assertions can be replaced with one line AssertJ assertion.
3. Refactor all tests to use BDD API of [AssertJ][bdd-assertj] and [Mockito][bdd-mockito].
4. Have fun!

[infobip-devdays]: http://www.infobip.com/dev-days/
[hamcrest]: http://hamcrest.org/
[junit]: http://junit.org/
[mockito]: http://mockito.org/
[assertj]: http://joel-costigliola.github.io/assertj
[bdd-assertj]: http://joel-costigliola.github.io/assertj/core-8/api/org/assertj/core/api/BDDAssertions.html
[bdd-mockito]: http://site.mockito.org/mockito/docs/current/org/mockito/BDDMockito.html
