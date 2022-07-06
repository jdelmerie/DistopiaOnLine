package fr.fms.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.fms.entities.Room;

public interface RoomRepository extends JpaRepository<Room, Long> {

}
