package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.model.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Bootstrap is started.");

        Publisher publisher = new Publisher();
        publisher.setName("SFG Publish");
        publisher.setCity("St. Petersburg");
        publisher.setState("FL");
        publisherRepository.save(publisher);

        Author tolkien = new Author("J.R.R", "Tolkien");
        authorRepository.save(tolkien);
        Book lotr = new Book("Lord of the rings", "123456789");
        bookRepository.save(lotr);
        lotr.setPublisher(publisher);
        lotr.getAuthors().add(tolkien);
        bookRepository.save(lotr);
        publisherRepository.save(publisher);

        Book hobbit = new Book("The Hobbit", "223456789");
        bookRepository.save(hobbit);
        hobbit.getAuthors().add(tolkien);
        hobbit.setPublisher(publisher);
        publisher.getBooks().add(hobbit);
        publisherRepository.save(publisher);
        bookRepository.save(hobbit);

        tolkien.getBooks().add(lotr);
        tolkien.getBooks().add(hobbit);
        authorRepository.save(tolkien);
        publisherRepository.save(publisher);
        
        System.out.println("Number of books : " + bookRepository.count());
        System.out.println("Number of books of publisher : " + publisher.getName() + " is: " + publisher.getBooks().size());
    }
}
