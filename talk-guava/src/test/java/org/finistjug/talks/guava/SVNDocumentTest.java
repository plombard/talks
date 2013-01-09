package org.finistjug.talks.guava;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.*;
import com.sun.istack.internal.Nullable;
import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class SVNDocumentTest {

    private ImmutableSet<SVNDocument> immutableChangeList;
    private List<SVNDocument> unorderedChangeList;

    @Before
    public void setUp() throws Exception {

        SVNDocument first = new SVNDocument("/trunk/",
                "index.html",
                "Pascal",
                "FRONTENDS",
                125L,
                new DateTime().minusDays(15).toDate(),
                1024L,
                "First commit");
        SVNDocument second = new SVNDocument("/trunk/",
                "index.html",
                "Horacio",
                "FRONTENDS",
                126L,
                new DateTime().minusDays(10).toDate(),
                512L,
                "Refactoring");
        SVNDocument third = new SVNDocument("/trunk/",
                "routes.xml",
                "Xavier",
                "FRONTENDS",
                130L,
                new DateTime().minusDays(5).toDate(),
                64L,
                "Introducing Apache Camel");
        SVNDocument fourth = new SVNDocument("/trunk/",
                "pom.xml",
                "Sébastien",
                "FRONTENDS",
                160L,
                new DateTime().minusDays(1).toDate(),
                64L,
                "Mavenizing the whole project");

        immutableChangeList = ImmutableSet.of(first, second, third, fourth);
        unorderedChangeList = Lists.newArrayList(second, fourth, first, third);


    }

    @After
    public void tearDown() throws Exception {
        immutableChangeList = null;
        unorderedChangeList = null;
    }

    /**
     * Uses the Files.readLines to instantiate a list with a LineProcessor
     *
     * @throws Exception
     */
    public void testFileReadLines() throws Exception {

    }

    /**
     * Uses "functional" programming to change values in a list
     *
     * @throws Exception
     */
    @Test
    public void changeValuesUsingFunction() throws Exception {

        Function createBranches = new Function() {
            @Override
            public Object apply(@Nullable Object o) {
                SVNDocument toProcess = (SVNDocument) o;
                toProcess.path = "/branches/".concat(toProcess.author);
                return toProcess;
            }
        };

        List<SVNDocument> transformedChangeList = Lists.transform(immutableChangeList.asList(), createBranches);
        System.out.println("Original ChangeList :");
        System.out.println(Iterables.toString(immutableChangeList));
        System.out.println("----------------");
        System.out.println("Transformed ChangeList :");
        System.out.println(Iterables.toString(transformedChangeList));
        System.out.println("/******************************/");
    }

    /**
     * Filter the list on several usernames
     *
     * @throws Exception
     */
    @Test
    public void filterUsingPredicates() throws Exception {
        final ImmutableSet<String> selectedUsers = ImmutableSet.of("Horacio", "Xavier");

        Predicate isCommitedBySelectedUser = new Predicate() {
            @Override
            public boolean apply(@Nullable Object o) {
                SVNDocument toFilter = (SVNDocument) o;
                // is toFilter.author in the selected users list ?
                return Iterables.contains(selectedUsers, toFilter.author);
            }
        };

        Iterable<SVNDocument> filteredChangeList = Iterables.filter(immutableChangeList, isCommitedBySelectedUser);
        System.out.println("Filtered ChangeList :");
        System.out.println(Iterables.toString(filteredChangeList));
        System.out.println("/******************************/");
    }

    @Test
    public void testHashCode() throws Exception {
        System.out.print("Hashcode :");
        System.out.println(immutableChangeList.asList().get(0).hashCode());
        System.out.println("/******************************/");
    }

    @Test
    public void testToString() throws Exception {
        System.out.print("toString :");
        System.out.println(immutableChangeList.asList().get(0).toString());
        System.out.println("/******************************/");
    }


    /**
     * Order the whole List
     *
     * @throws Exception
     */
    @Test
    public void orderUsingOrdering() throws Exception {
        // Facilitates "on the fly" Comparators
        Ordering<SVNDocument> svnOrdering = new Ordering<SVNDocument>() {
            @Override
            public int compare(@Nullable SVNDocument svnDocument, @Nullable SVNDocument svnDocument2) {
                // if repos are not the same,
                // we see the documents as "equals"
                if (svnDocument.repository.compareTo(svnDocument2.repository) != 0) {
                    return 0;
                }
                // now the pretty part
                return ComparisonChain.start()
                        .compare(svnDocument.date, svnDocument2.date)
                        .compare(svnDocument.revision, svnDocument2.revision)
                        .result();
            }
        };

        List<SVNDocument> orderedChangeList = svnOrdering.sortedCopy(unorderedChangeList);
        System.out.println("Ordered ChangeList :");
        System.out.println(Iterables.toString(orderedChangeList));
        System.out.println("/******************************/");
    }
}
