package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.model.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    private void initData(){
        //Eric
        Author eric = new Author("Eric", "Evans");
        Publisher pub1 = new Publisher("Bill", "Idras 34");
        Publisher pub2 = new Publisher("Niki", "Idras 34");
        Set<Publisher> pubs_eric = new HashSet<>();
        Book ddd = new Book("Domain Driven Design", "1234", pubs_eric);
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);
        ddd.getPublisher().add(pub1);
        ddd.getPublisher().add(pub2);

        authorRepository.save(eric);
        bookRepository.save(ddd);
        publisherRepository.save(pub1);
        publisherRepository.save(pub2);

        // Rod
        Author rod = new Author("Rod", "Johnson");
        Publisher pub3 = new Publisher("Fokos", "Leonida 13");
        Set<Publisher> pubs_rod = new HashSet<>();
        Book noEJB = new Book("J2EE Developtment without EJB", "23444", pubs_rod);
        rod.getBooks().add(noEJB);
        noEJB.getPublisher().add(pub1);
        noEJB.getPublisher().add(pub3);

        authorRepository.save(rod);
        bookRepository.save(noEJB);
        publisherRepository.save(pub3);
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }
}
