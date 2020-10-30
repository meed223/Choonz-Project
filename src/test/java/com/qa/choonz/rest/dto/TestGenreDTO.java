package com.qa.choonz.rest.dto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.qa.choonz.persistence.domain.Album;

public class TestGenreDTO {

	GenreDTO testGenre;
    final long id = 1L;
    final String name = "Pop";
    final String description = "Pop music";
    final String picture = "../";
    List<Album> testAlbums;
    
	@BeforeEach
	void init() {
		this.testAlbums = new ArrayList<Album>();
		this.testGenre = new GenreDTO(
				this.id,
				this.name,
				this.picture,
				this.description,
				this.testAlbums);
	}
	
	@Test
	void testZeroArgsConstructor() {
		GenreDTO newTestGenre = new GenreDTO();

		assertThat(newTestGenre instanceof GenreDTO);
	}
	
	@Test
	void testAllArgsConstructor() {
		Long newId = this.id + 1;
		String newName = "R&B";
		String newPicture = "../";
		String newDesc = "Rhythm and blues";
		List<Album> newTestAlbums = new ArrayList<Album>();
		GenreDTO newTestGenre = new GenreDTO(
				newId, 
				newName,
				newPicture,
				newDesc,
				newTestAlbums);	
	
		assertThat(newTestGenre instanceof GenreDTO);
	}
	
	@Test
	void getSetIdTest() {
		Long newId = 2l;
		this.testGenre.setId(newId);

		assertEquals(newId,this.testGenre.getId());
	}
	
	@Test
	void getSetNameTest() {
		String newName = "R&B";
		this.testGenre.setName(newName);

		assertEquals(newName,this.testGenre.getName());
	}
	
	@Test
	public void getSetDescriptionTest() {
		String newDesc = "Cheese music";
		this.testGenre.setDescription(newDesc);
		
		assertEquals(newDesc,this.testGenre.getDescription());
	}
	
	@Test
	void getSetAlbumsTest() {
		Album newAlbum = new Album();
		List<Album> newAlbums = new ArrayList<Album>();
		newAlbums.add(newAlbum);
		this.testGenre.setAlbums(newAlbums);
		
		assertEquals(newAlbums,this.testGenre.getAlbums());
	}
	
	@Test
	void testToString() {
		assertThat(this.testGenre.toString()
				.equals("GenreDTO [id=1, name=Pop, description=Pop music, albums=null]"));
	}
	
	@Test
	public void hashCodeTest() {		
		GenreDTO genre1 = new GenreDTO(1L,"Pop", "/pic/1", "Pop music",null);
		GenreDTO genre2 = new GenreDTO(1L,"Pop", "/pic/1", "Pop music",null);
		
		assertTrue(genre1.hashCode() == genre2.hashCode());
	}
	
	@Test
	void testEquals() {
		GenreDTO emptyGenre = new GenreDTO();
		GenreDTO fullGenre = new GenreDTO(
				this.id, 
				this.name,
				this.picture,
				this.description,
				this.testAlbums);
		
		assertThat(!this.testGenre.equals(emptyGenre));
		assertThat(this.testGenre.equals(fullGenre));
	}
	
	@AfterEach
	void teardown() {
		this.testGenre = null;
	}
	
}