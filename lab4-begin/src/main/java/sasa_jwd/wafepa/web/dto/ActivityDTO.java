package sasa_jwd.wafepa.web.dto;

public class ActivityDTO {

	private Long id;
	private String name;
	
	
	public ActivityDTO() {
		super();
	}

	public ActivityDTO(String name) {
		super();
		this.name = name;
	}

	public ActivityDTO(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
