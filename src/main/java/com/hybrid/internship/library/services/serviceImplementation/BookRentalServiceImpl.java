package com.hybrid.internship.library.services.serviceImplementation;

import com.hybrid.internship.library.dtos.BookRentalCountDto;
import com.hybrid.internship.library.dtos.BookRentalDto;
import com.hybrid.internship.library.models.Book;
import com.hybrid.internship.library.models.BookRental;
import com.hybrid.internship.library.repositories.BookRentalRepository;
import com.hybrid.internship.library.services.BookRentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookRentalServiceImpl implements BookRentalService {

    @Autowired
    BookRentalRepository bookRentalRepository;
    @Autowired
    BookCopyServiceImpl bookCopyService;
    @Autowired
    ConversionService conversionService;

    @Override
    public List<BookRental> findAll() {
//        return bookRentalRepository.findAll().
//                stream()
//                .map(bookRental -> conversionService.convert(bookRental, BookRentalDto.class))
//                .collect(Collectors.toList());
        return bookRentalRepository.findAll();
    }

    @Override
    public BookRental findById(Long id) {
        //return conversionService.convert(bookRentalRepository.findById(id).orElseGet(() -> null), BookRentalDto.class);
        return bookRentalRepository.findById(id).orElseGet(() -> null);
    }

    @Override
    public BookRental create(BookRental bookRental) {
        //int totalCopies = bookCopyService.totalCopiesByBookId(bookRentalDto.getBookCopyDto().getBookDto().getId());
        Long id = bookRental.getBookCopy().getBook().getId();
        int totalCopies =
                bookCopyService.totalCopiesByBookId(id);
        //int rentedCopies = rentedCopies(bookRentalDto.getBookCopyDto().getBookDto().getId());
        int rentedCopies = rentedCopies(id);
        if(totalCopies - rentedCopies > 0){
            return bookRentalRepository.save(bookRental);
        }
            //return conversionService.convert(bookRentalRepository.save(conversionService.convert(bookRental, BookRental.class)), BookRentalDto.class);
        return null;
    }

    @Override
    public BookRental update(BookRental bookRental) {
//        BookRental updatedEntity = conversionService.convert(bookRentalDto, BookRental.class);
//        updatedEntity.setIsReturned(true);
        bookRental.setIsReturned(true);
        return bookRentalRepository.save(bookRental);
        //return conversionService.convert(bookRentalRepository.save(updatedEntity), BookRentalDto.class);
    }

    @Override
    public List<BookRental> findAllByUser(Long id) {
//        return bookRentalRepository.findAllByUserId(id)
//                .stream()
//                .map(bookRental -> conversionService.convert(bookRental, BookRentalDto.class))
//                .collect(Collectors.toList());
        return bookRentalRepository.findAllByUser(id);
    }

//    @Override
//    public List<BookRental> findAllByBook(Long id) {
////        return bookRentalRepository.findAllByBookId(id)
////                .stream()
////                .map(bookRental -> conversionService.convert(bookRental, BookRentalDto.class))
////                .collect(Collectors.toList());
//        return bookRentalRepository.findAllByBook(id);
//    }

    @Override
    public List<BookRentalCountDto> findMostRentedBook() {
        return bookRentalRepository.findMostRentedBook();
    }

    @Override
    public int rentedCopies(Long id) {
        return bookRentalRepository.rentedCopies(id);
    }

    @Override
    public void delete(Long id) {
    }

    @Override
    public boolean exists(Long id) {
        return bookRentalRepository.existsById(id);
    }
}
