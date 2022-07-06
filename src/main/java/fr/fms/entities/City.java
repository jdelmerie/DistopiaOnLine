package fr.fms.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
public class City implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotNull
	@NotBlank
	@Size(min = 2, max = 100)
	private String name;
	
	@NotNull
	@NotBlank
	@Size(min = 5, max = 5)
	private String zipcode;
	
	@OneToMany(mappedBy = "city")
	private List<Theater> theaters; //une ville est liée à plusieurs ciné

	public City(@NotNull @NotBlank @Size(min = 2) String name, @NotNull @NotBlank @Size(min = 5) String zipcode) {
		this.name = name;
		this.zipcode = zipcode;
	}

	
	
	

}
