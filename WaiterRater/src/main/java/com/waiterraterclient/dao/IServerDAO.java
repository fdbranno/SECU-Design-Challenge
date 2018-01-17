package com.waiterraterclient.dao;

import com.waiterraterclient.dto.ServerScoreDTO;
import com.waiterraterclient.entity.Server;

import java.util.List;

public interface IServerDAO {

    List<Server> getAllServers();
    
	Server getServerById(Long serverId);

	List<Server> getServersByRestaurant(Long restaurantId);
    
	void addServer(Server server);
    
	void updateServer(Server server);
    
	void deleteServer(Long serverId);

	boolean serverExists(Long serverId);

	List<ServerScoreDTO> getTopServers(int count);
} 