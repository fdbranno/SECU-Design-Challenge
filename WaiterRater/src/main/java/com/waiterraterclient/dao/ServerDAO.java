package com.waiterraterclient.dao;

import com.waiterraterclient.dto.ServerScoreDTO;
import com.waiterraterclient.entity.Server;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Repository
public class ServerDAO implements IServerDAO {
	@PersistenceContext	
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public Server getServerById(Long serverId) {
		String hql = "FROM Server as s WHERE s.serverId = :sid";
		List<Server> servers = entityManager.createQuery(hql).setParameter("sid", serverId)
		              .getResultList();
		return servers.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Server> getServersByRestaurant(Long restaurantId) {
		String hql = "FROM Server as s WHERE s.restaurant.restaurantId = :rid";
		List<Server> servers = entityManager.createQuery(hql).setParameter("rid", restaurantId)
				.getResultList();
		return servers;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Server> getAllServers() {
		String hql = "FROM Server as s ORDER BY s.serverId";
		return (List<Server>) entityManager.createQuery(hql).getResultList();
	}	
	
	@Override
	public void addServer(Server server) {
		entityManager.persist(server);
	}
	
	@Override
	public void updateServer(Server server) {
		Server s = getServerById(server.getServerId());
		s.setFirstName(server.getFirstName());
		s.setLastName(server.getLastName());
		s.setRestaurant(server.getRestaurant());
		s.setStatus(server.getStatus());
		entityManager.flush();
	}
	
	@Override
	public void deleteServer(Long serverId) {
		entityManager.remove(getServerById(serverId));
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean serverExists(Long serverId) {
		String hql = "FROM Server as s WHERE s.serverId = :sid";
		List<Server> servers = entityManager.createQuery(hql).setParameter("sid", serverId)
				.getResultList();
		return servers.size() > 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ServerScoreDTO> getTopServers(int count) {
		String sql = "SELECT (AVG(scores)/5)*100 AS score, firstName, lastName " +
				"FROM " +
				"(SELECT SUM(review_score) / COUNT(review_attribute_id) AS scores, server_first_name AS firstName, server_last_name AS lastName " +
				"FROM reviews " +
				"JOIN servers " +
				"JOIN review_attributes " +
				"JOIN visits " +
				"JOIN restaurants " +
				"WHERE visit_id = review_visit " +
				"AND visit_server = server_id " +
				"AND review_attribute_id = review_review_attribute " +
				"GROUP BY visit_id, visit_server, review_attribute_id) AS tbl " +
				"GROUP BY firstName, lastName " +
				"ORDER BY score DESC LIMIT ?";
		List<Object[]> rows = (List<Object[]>)entityManager.createNativeQuery(sql).setParameter(1, count).getResultList();

		List<ServerScoreDTO> results = new ArrayList<ServerScoreDTO>();

		for (Object[] row : rows) {
			ServerScoreDTO dto = new ServerScoreDTO();
			dto.setLastName((String) row[2]);
			dto.setFirstName((String) row[1]);
			dto.setScore((BigDecimal) row[0]);
			results.add(dto);
		}

		return results;
	}
}