package com.waiterraterclient.controller;

import com.waiterraterclient.dto.ServerScoreDTO;
import com.waiterraterclient.entity.Server;
import com.waiterraterclient.service.IServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api")
public class ServerController {
	@Autowired
	private IServerService serverService;

	///Get server by Id
	@GetMapping("server/{server_id}")
	public ResponseEntity<Server> getServerById(@PathVariable("server_id") Long serverId) {
		Server server = serverService.getServerById(serverId);
		return new ResponseEntity<Server>(server, HttpStatus.OK);
	}

	//Get all servers
	@GetMapping("servers")
	public ResponseEntity<List<Server>> getAllServers() {
		List<Server> servers = serverService.getAllServers();
		return new ResponseEntity<List<Server>>(servers, HttpStatus.OK);
	}

	//Add server
	@PostMapping("server")
	public ResponseEntity<Void> addServer(@RequestBody Server server, UriComponentsBuilder builder) {
		boolean flag = serverService.addServer(server);
		if (flag == false) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		HttpHeaders headers = new HttpHeaders();
		Map<String, String> vars = new HashMap<String, String>() {{
			put("server_id", String.valueOf(server.getServerId()));
		}};
		headers.setLocation(builder.path("/server/{server_id}").buildAndExpand(vars).encode().toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	//Update server
	@PutMapping("server")
	public ResponseEntity<Server> updateServer(@RequestBody Server server) {
		serverService.updateServer(server);
		return new ResponseEntity<Server>(server, HttpStatus.OK);
	}

	//Delete server
	@DeleteMapping("server/{server_id}")
	public ResponseEntity<Void> deleteServer(@PathVariable("server_id") Long serverId) {
		serverService.deleteServer(serverId);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	///Get servers by restaurant
	@GetMapping("servers/restaurant/{restaurant_id}")
	public ResponseEntity<List<Server>> getServersByRestaurant(@PathVariable("restaurant_id") Long restaurantId) {
		List<Server> servers = serverService.getServersByRestaurant(restaurantId);
		return new ResponseEntity<List<Server>>(servers, HttpStatus.OK);
	}

	///Get top servers and their scores
	@GetMapping("servers/top/count/{count}")
	public ResponseEntity<List<ServerScoreDTO>> getServersByRestaurant(@PathVariable("count") int count) {
		List<ServerScoreDTO> servers = serverService.getTopServers(count);
		return new ResponseEntity<List<ServerScoreDTO>>(servers, HttpStatus.OK);
	}
}  