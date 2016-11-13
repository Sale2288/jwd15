package jwd.wafepa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jwd.wafepa.model.Activity;

// Prvi parametar: tip entiteta za koji je zaduzen repozitorijum
// Drugi parametar: tip entiteta koji ce biti koristen za Primarni kljuc
@Repository
public interface ActivityRepository 
	extends JpaRepository<Activity, Long> {
	
	// ono cemu pristupamo, mora da bude bean (izmedju ostalog, da ima gettere i settere
	// :name znaci da je to imenovani parametar
	/*@Query("select a from Activity a where a.name = :name") // 'Activity a' je nas entitet koji cemo selektovati
	List<Activity> findByName(@Param("name") String name);*/
	
	
	List<Activity> findByNameContaining(String name);

}
