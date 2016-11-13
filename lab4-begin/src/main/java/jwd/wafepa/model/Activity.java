package jwd.wafepa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Models a physical activity.
 * 
 *
 */
@Entity // oba paketa koriste javax.persistence za povezivanje sa bazom -> ovo je znak da cemo koristiti bazu
@Table(name="tblActivity")  // ova klasa ce biti mapirana na tabelu;   name -> koristimo za tabelu
public class Activity {
	
	@Id 	// oznaka da je ovo primarni kljuc
	@Column
	@GeneratedValue  // ovo je auto increment u bazi
	private Long id;
	@Column
	private String name;
	@Column(name="admin_comment")  // ako ovo ne uradimo, uzece nazive promenljivih i iskoristice neke konvencije baze (za nasu bazu, se ne mora koristiti ovo, jer ce takav biti naziv kolone
	private String adminComment="test";
	

	public Activity() {
		super();
	}

	public Activity(String name) {
		super();
		this.name = name;
	}

	public Activity(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	/**
	 * 
	 * @return Activity identifier.
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * Sets activity identifier.
	 * @param id new identifier
 	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * 
	 * @return Name of the activity.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * 
	 * @param name New name of the activity.
	 */
	public void setName(String name) {
		this.name = name;
	}

	public String getAdminComment() {
		return adminComment;
	}

	public void setAdminComment(String adminComment) {
		this.adminComment = adminComment;
	}
	
	
}
