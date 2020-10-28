package com.qa.choonz.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.qa.choonz.persistence.domain.Album;
import com.qa.choonz.persistence.domain.Artist;
import com.qa.choonz.persistence.repository.ArtistRepository;
import com.qa.choonz.rest.dto.ArtistDTO;

@SpringBootTest
public class TESTArtistServiceIntegration {

	@Autowired
	private ArtistService service;
	
	@Autowired
	private ArtistRepository repo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	private ArtistDTO mapToDTO(Artist artist) {
		return this.modelMapper.map(artist, ArtistDTO.class);
	}
	
	private Long id = 1L;
	private final String name = "The Weeknd";
	private Artist testArtist;
	private Artist testArtistWithId;
	private List<Album> testAlbums;
	private ArtistDTO artistDTO;
	private ArtistDTO artistDTOWithId;
	
	@BeforeEach
	void init() {
		this.repo.deleteAll();
		testArtist = new Artist(id, name, testAlbums);
		
		this.testArtist = new Artist();
		this.testArtist.setName(this.name);
		this.testArtist.setAlbums(this.testAlbums);
		this.testArtistWithId = this.repo.save(testArtist);
		this.id = this.testArtistWithId.getId();
		
		this.artistDTO = this.mapToDTO(this.testArtistWithId);
		this.artistDTOWithId = new ArtistDTO();
		this.artistDTOWithId.setId(this.artistDTO.getId());
		this.artistDTOWithId.setName(this.artistDTO.getName());
		this.artistDTOWithId.setAlbums(this.testAlbums);	
		
	}
	
	@Test
	void testCreate() {
		System.out.println(this.testArtist.toString());
		System.out.println(this.artistDTOWithId.toString());
		assertThat(this.artistDTOWithId).isEqualTo(this.service.create(this.testArtist));
	}
	
	@Test
	void testRead() {
		assertThat(this.artistDTOWithId).isEqualTo(this.service.read(this.id));
	}
	
	@Test
	void testReadAll() {
		assertThat(Stream.of(this.artistDTOWithId).collect(Collectors.toList()))
			.isEqualTo(this.service.read());
	}
	
	@Test
	void testUpdate() {
		assertThat(this.artistDTOWithId).isEqualTo(this.service.update(this.testArtist, this.id));
	}
	
	@Test
	void testDelete() {
		assertThat(this.service.delete(this.id)).isTrue();
	}
}
