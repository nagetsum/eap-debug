package org.jboss.ws.booksearch;

import java.util.Arrays;
import java.util.List;

public class SearchResult {
    private SearchCriteria originalCriteria;
    private Book[] result;

    public SearchCriteria getOriginalCriteria() {
        return originalCriteria;
    }

    public void setOriginalCriteria(SearchCriteria originalCriteria) {
        this.originalCriteria = originalCriteria;
    }

    public Book[] getResult() {
        return result;
    }

    public void setResult(Book[] result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "SearchResult{" +
                "originalCriteria=" + originalCriteria +
                ", result=" + Arrays.toString(result) +
                '}';
    }
}
