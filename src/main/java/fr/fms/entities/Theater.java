package fr.fms.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.DecimalMin;
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
public class Theater implements Serializable {

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
	@Size(min = 5, max = 100)
	private String address;

	@DecimalMin("1")
	@NotNull
	private int numberOfRooms = 1;

	@ManyToOne
	@NotNull
	private City city; // plusieurs ciné sont reliés à une seule ville

	@OneToMany(mappedBy = "theater")
	private List<Room> rooms; // un ciné est liée à plusieurs salles

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	private List<Movie> movies = new ArrayList<Movie>();

	@Override
	public String toString() {
		return "Theater [id=" + id + ", name=" + name + ", address=" + address + ", numberOfRooms=" + numberOfRooms
				+ ", city=" + city + ", rooms=" + rooms + ", movies=" + movies + "]";
	}

}
