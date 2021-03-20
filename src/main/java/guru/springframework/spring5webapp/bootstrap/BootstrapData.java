package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Author tolkien = new Author("J.R.R", "Tolkien");
        Book lotr = new Book("Lord of the rings", "123456789");
        Book hobbit = new Book("The Hobbit", "223456789");

        tolkien.getBooks().add(lotr);
        tolkien.getBooks().add(hobbit);
        lotr.getAuthors().add(tolkien);

        authorRepository.save(tolkien);
        bookRepository.save(lotr);
        bookRepository.save(hobbit);

        System.out.println("Bootstrap is started.");
    }
}
