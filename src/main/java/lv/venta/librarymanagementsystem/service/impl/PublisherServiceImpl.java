package lv.venta.librarymanagementsystem.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lv.venta.librarymanagementsystem.model.Publisher;
import lv.venta.librarymanagementsystem.repo.PublisherRepo;
import lv.venta.librarymanagementsystem.service.PublisherService;

@Service
public class PublisherServiceImpl implements PublisherService {

	@Autowired
	private PublisherRepo publisherRepo;

	public PublisherServiceImpl(PublisherRepo publisherRepo) {
		this.publisherRepo = publisherRepo;
	}

	@Override
	public List<Publisher> findAllPublishers() {
		return publisherRepo.findAll();
	}

	@Override
	public Publisher findPublisherById(Long id) throws Exception {
		return publisherRepo.findById(id).orElseThrow(() -> new Exception("Publisher with this ID not found"));
	}

	@Override
	public void createPublisher(Publisher publisher) {
		publisherRepo.save(publisher);
	}

	@Override
	public void updatePublisher(Publisher publisher) {
		publisherRepo.save(publisher);
	}

	@Override
	public void deletePublisher(Long id) throws Exception {
		Publisher publisher = publisherRepo.findById(id).orElseThrow(() -> new Exception("Publisher with this ID not found"));
		publisherRepo.deleteById(publisher.getId());
	}

}