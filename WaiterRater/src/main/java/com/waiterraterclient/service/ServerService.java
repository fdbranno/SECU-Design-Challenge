package com.waiterraterclient.service;

import com.waiterraterclient.dao.IServerDAO;
import com.waiterraterclient.dto.ServerScoreDTO;
import com.waiterraterclient.entity.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServerService implements IServerService {
	@Autowired
	private IServerDAO serverDAO;
	
	@Override
	public Server getServerById(Long serverId) {
		Server obj = serverDAO.getServerById(serverId);
		return obj;
	}

	@Override
	public List<Server> getServersByRestaurant(Long restaurantId) {
		List<Server> obj = serverDAO.getServersByRestaurant(restaurantId);
		return obj;
	}

	@Override
	public List<Server> getAllServers(){
		return serverDAO.getAllServers();
	}
	
	@Override
	public synchronized boolean addServer(Server server){
        if (serverDAO.serverExists(server.getServerId())) {
    	    return false;
        } else {
			serverDAO.addServer(server);
			return true;
        }

	}
	
	@Override
	public void updateServer(Server server) {
		serverDAO.updateServer(server);
	}
	
	@Override
	public void deleteServer(Long serverId) {
		serverDAO.deleteServer(serverId);
	}
	
	@Override
	public boolean serverExists(Long serverId) {
		return serverDAO.serverExists(serverId);
	}

	@Override
	public List<ServerScoreDTO> getTopServers(int count) { return serverDAO.getTopServers(count); }
}