package com.mightyjava.service.impl;

import java.util.Collection;
import java.util.Optional;

import com.mightyjava.service.IPageService;
import com.mightyjava.service.IService;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mightyjava.domain.Book;
import com.mightyjava.repository.BookRepository;

@Service
public class BookServiceImpl  implements IService<Book>, IPageService<Book> {

	@Autowired
	private BookRepository bookRepository;

	@Override
	public Collection<Book> findAll() {
		return (Collection<Book>) bookRepository.findAll();
	}

	@Override
	public Page<Book> findAll(Pageable pageable, String searchText) {
		return bookRepository.findAllBooks(pageable, searchText);
	}

	@Override
	public Page<Book> findAll(Pageable pageable) {
		return bookRepository.findAll(pageable);
	}

	@Override
	public Optional<Book> findById(Long id) {
		return bookRepository.findById(id);
	}

	public Book findByIdd(Long id){
		Optional<Book> book1 = bookRepository.findById(id);
		return book1.orElse(new Book());
	}

	@Override
	public Book updateBook(Book book) {
		Book book1 = findByIdd(book.getId());

		if(book1 != null){
			return book1;
		}


		return bookRepository.save(book);
	}

	@Override
	public Book saveBook(Book book) {
		return bookRepository.save(book);
	}

	@Override
	public String deleteById(Long id) {
		JSONObject jsonObject = new JSONObject();
		try {
			bookRepository.deleteById(id);
			jsonObject.put("message", "Book deleted successfully");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jsonObject.toString();
	}

}
