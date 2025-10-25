package lv.venta.librarymanagementsystem.service;

import java.util.List;
import lv.venta.librarymanagementsystem.model.Publisher;

public interface PublisherService {

	public List<Publisher> findAllPublishers();

	public Publisher findPublisherById(Long id) throws Exception;

	public void createPublisher(Publisher publisher);

	public void updatePublisher(Publisher publisher);

	public void deletePublisher(Long id) throws Exception;

}