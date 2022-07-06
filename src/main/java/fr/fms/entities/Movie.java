package fr.fms.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movie implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotNull
	@NotBlank
	@Size(min = 2)
	private String title;
	
	@NotNull
	@NotBlank
	@Size(min = 2)
	private String description;
	
	@NotNull
	@NotBlank
	private String poster;
	
	@NotNull
	@NotBlank
	private String duration;
	
	@ManyToMany(mappedBy = "movies", fetch = FetchType.LAZY)
	private Collection<Theater> theaters;

	@Override
	public String toString() {
		return "Movie [id=" + id + ", title=" + title + ", description=" + description + ", poster=" + poster
				+ ", duration=" + duration + ", theaters=" + theaters + "]";
	}
	
	
}
