package org.jboss.ws.booksearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BookFinderImpl implements BookFinder {
    @Override
    public SearchResult search(SearchCriteria criteria) {
        //  always return the same result as test data
        SearchResult result = new SearchResult();
        result.setOriginalCriteria(criteria);

        result.setResult(new Book[]{
            Book.of("9784621303252", "Effective Java 3rd edition", "Joshua Bloch"),
            Book.of("9780596802295", "The Art of Readable Code", "Dustin Boswell, Trevor Foucher")
        });

        return result;
    }
}
