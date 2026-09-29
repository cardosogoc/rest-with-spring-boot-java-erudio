package br.com.erudio.unitests.mapper.mocks;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import br.com.erudio.data.dto.BooksDTO;
import br.com.erudio.model.Books;

public class MockBook {

    public Books mockEntity() {
        return mockEntity(0);
    }

    public BooksDTO mockDTO() {
        return mockDTO(0);
    }

    public List<Books> mockEntityList() {

        List<Books> books = new ArrayList<>();

        for (int i = 0; i < 14; i++) {
            books.add(mockEntity(i));
        }

        return books;
    }

    public List<BooksDTO> mockDTOList() {

        List<BooksDTO> books = new ArrayList<>();

        for (int i = 0; i < 14; i++) {
            books.add(mockDTO(i));
        }

        return books;
    }

    public Books mockEntity(Integer number) {

        Books book = new Books();

        book.setId(number);
        book.setAuthor("Author Test" + number);
        book.setLaunchDate(LocalDateTime.of(2025, 1, 1, 10, 0).plusDays(number));
        book.setPrice(BigDecimal.valueOf(29.90 + number));
        book.setTitle("Title Test" + number);

        return book;
    }

    public BooksDTO mockDTO(Integer number) {

        BooksDTO book = new BooksDTO();

        book.setId(number);
        book.setAuthor("Author Test" + number);
        book.setLaunchDate(LocalDateTime.of(2025, 1, 1, 10, 0).plusDays(number));
        book.setPrice(BigDecimal.valueOf(29.90 + number));
        book.setTitle("Title Test" + number);

        return book;
    }
}