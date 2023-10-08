package cl;

import java.io.FileNotFoundException;

public interface BookService {
    void buyBook(Integer bookId, Integer userId) throws FileNotFoundException;
}