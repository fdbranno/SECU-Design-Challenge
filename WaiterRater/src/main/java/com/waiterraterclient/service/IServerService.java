package com.waiterraterclient.service;

import com.waiterraterclient.dto.ServerScoreDTO;
import com.waiterraterclient.entity.Server;

import java.util.List;

public interface IServerService {

	List<Server> getAllServers();

	Server getServerById(Long serverId);

	boolean addServer(Server server);

	void updateServer(Server server);

	void deleteServer(Long serverId);

	List<Server> getServersByRestaurant(Long restaurantId);

	boolean serverExists(Long serverId);

	List<ServerScoreDTO> getTopServers(int count);
}