package jwd.wafepa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Address {

	@Id
	@GeneratedValue
	@Column
	private Long id;

	@Column
	private String streat;

	@Column
	private String number;

	@ManyToOne(fetch=FetchType.EAGER)
	private User user;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStreat() {
		return streat;
	}

	public void setStreat(String streat) {
		this.streat = streat;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		if( this.user !=null   
				&& !this.user.equals(user) ){
			this.user.getAddresses().remove(this);
		}
		this.user = user;
		if (user !=null && !user.getAddresses().contains(this)) {
			user.getAddresses().add(this);
		}
	}

}
