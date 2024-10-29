package co.edu.icesi.dev.saamfi.dtos.speout;

import java.util.List;

public class ListOutDTO<T> {

    private List<T> elements;

    public ListOutDTO() {

    }

    public ListOutDTO(List<T> eleList) {
        this.elements = eleList;
    }

    /**
     * @return the elements
     */
    public List<T> getElements() {
        return elements;
    }

    /**
     * @param elements the elements to set
     */
    public void setElements(List<T> elements) {
        this.elements = elements;
    }

}
