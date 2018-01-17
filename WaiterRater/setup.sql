-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.7.20 - MySQL Community Server (GPL)
-- Server OS:                    Win64
-- HeidiSQL Version:             9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for secu
CREATE DATABASE IF NOT EXISTS `secu` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `secu`;

-- Dumping structure for table secu.restaurants
CREATE TABLE IF NOT EXISTS `restaurants` (
  `restaurant_id` bigint(24) NOT NULL,
  `restaurant_status` bit(1) DEFAULT NULL,
  `restaurant_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`restaurant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table secu.restaurants: ~3 rows (approximately)
/*!40000 ALTER TABLE `restaurants` DISABLE KEYS */;
INSERT INTO `restaurants` (`restaurant_id`, `restaurant_status`, `restaurant_name`) VALUES
	(1, b'1', 'Olive Garden'),
	(2, b'1', 'Red Lobster'),
	(3, b'1', 'Applebees');
/*!40000 ALTER TABLE `restaurants` ENABLE KEYS */;

-- Dumping structure for table secu.reviews
CREATE TABLE IF NOT EXISTS `reviews` (
  `review_review_attribute` bigint(24) NOT NULL,
  `review_visit` bigint(24) NOT NULL,
  `review_score` int(11) DEFAULT NULL,
  PRIMARY KEY (`review_review_attribute`,`review_visit`),
  KEY `FK_reviews_visits` (`review_visit`),
  KEY `FK_reviews_review_attributes` (`review_review_attribute`),
  CONSTRAINT `FK_reviews_review_attributes` FOREIGN KEY (`review_review_attribute`) REFERENCES `review_attributes` (`review_attribute_id`),
  CONSTRAINT `FK_reviews_visits` FOREIGN KEY (`review_visit`) REFERENCES `visits` (`visit_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table secu.reviews: ~54 rows (approximately)
/*!40000 ALTER TABLE `reviews` DISABLE KEYS */;
INSERT INTO `reviews` (`review_review_attribute`, `review_visit`, `review_score`) VALUES
	(1, 1, 5),
	(1, 2, 3),
	(1, 3, 5),
	(1, 4, 4),
	(1, 5, 3),
	(1, 6, 4),
	(1, 7, 4),
	(1, 8, 2),
	(1, 9, 2),
	(1, 10, 5),
	(1, 11, 5),
	(1, 12, 4),
	(1, 13, 4),
	(1, 14, 4),
	(1, 15, 4),
	(1, 16, 5),
	(1, 17, 5),
	(1, 18, 4),
	(2, 1, 4),
	(2, 2, 4),
	(2, 3, 3),
	(2, 4, 4),
	(2, 5, 2),
	(2, 6, 3),
	(2, 7, 5),
	(2, 8, 2),
	(2, 9, 1),
	(2, 10, 5),
	(2, 11, 5),
	(2, 12, 3),
	(2, 13, 4),
	(2, 14, 5),
	(2, 15, 5),
	(2, 16, 5),
	(2, 17, 3),
	(2, 18, 3),
	(3, 1, 4),
	(3, 2, 4),
	(3, 3, 5),
	(3, 4, 4),
	(3, 5, 2),
	(3, 6, 3),
	(3, 7, 4),
	(3, 8, 2),
	(3, 9, 1),
	(3, 10, 5),
	(3, 11, 4),
	(3, 12, 3),
	(3, 13, 4),
	(3, 14, 5),
	(3, 15, 3),
	(3, 16, 5),
	(3, 17, 3),
	(3, 18, 4);
/*!40000 ALTER TABLE `reviews` ENABLE KEYS */;

-- Dumping structure for table secu.review_attributes
CREATE TABLE IF NOT EXISTS `review_attributes` (
  `review_attribute_id` bigint(24) NOT NULL,
  `review_attribute_attribute` varchar(255) DEFAULT NULL,
  `review_attribute_status` bit(1) DEFAULT NULL,
  PRIMARY KEY (`review_attribute_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table secu.review_attributes: ~3 rows (approximately)
/*!40000 ALTER TABLE `review_attributes` DISABLE KEYS */;
INSERT INTO `review_attributes` (`review_attribute_id`, `review_attribute_attribute`, `review_attribute_status`) VALUES
	(1, 'Kindness', b'1'),
	(2, 'Responsiveness', b'1'),
	(3, 'Accuracy', b'1');
/*!40000 ALTER TABLE `review_attributes` ENABLE KEYS */;

-- Dumping structure for table secu.servers
CREATE TABLE IF NOT EXISTS `servers` (
  `server_id` bigint(24) NOT NULL,
  `server_first_name` varchar(255) DEFAULT NULL,
  `server_last_name` varchar(255) DEFAULT NULL,
  `server_restaurant` bigint(24) NOT NULL,
  `server_status` bit(1) DEFAULT NULL,
  PRIMARY KEY (`server_id`),
  KEY `FK_servers_restaurants` (`server_restaurant`),
  CONSTRAINT `FK_servers_restaurants` FOREIGN KEY (`server_restaurant`) REFERENCES `restaurants` (`restaurant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table secu.servers: ~9 rows (approximately)
/*!40000 ALTER TABLE `servers` DISABLE KEYS */;
INSERT INTO `servers` (`server_id`, `server_first_name`, `server_last_name`, `server_restaurant`, `server_status`) VALUES
	(1, 'Tom', 'Hanks', 1, b'1'),
	(2, 'Morgan', 'Freeman', 1, b'1'),
	(3, 'Tracy', 'Morgan', 1, b'1'),
	(4, 'Robert', 'DeNiro', 2, b'1'),
	(5, 'Megan', 'Fox', 2, b'1'),
	(6, 'Jennifer', 'Aniston', 2, b'1'),
	(7, 'Kanye', 'West', 3, b'1'),
	(8, 'Tupac', 'Shakir', 3, b'1'),
	(9, 'Tim', 'McGraw', 3, b'1');
/*!40000 ALTER TABLE `servers` ENABLE KEYS */;

-- Dumping structure for table secu.visits
CREATE TABLE IF NOT EXISTS `visits` (
  `visit_id` bigint(24) NOT NULL,
  `visit_server` bigint(24) NOT NULL,
  `visit_comment` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`visit_id`),
  KEY `server` (`visit_server`),
  CONSTRAINT `FK_visits_servers` FOREIGN KEY (`visit_server`) REFERENCES `servers` (`server_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table secu.visits: ~7 rows (approximately)
/*!40000 ALTER TABLE `visits` DISABLE KEYS */;
INSERT INTO `visits` (`visit_id`, `visit_server`, `visit_comment`) VALUES
	(1, 1, 'Great Job!'),
	(2, 1, 'Very polite.'),
	(3, 2, 'Awesome work'),
	(4, 2, 'Kudos'),
	(5, 3, 'Could have been better'),
	(6, 3, 'Not the best experience'),
	(7, 4, 'Loved it'),
	(8, 4, 'Fantastic job'),
	(9, 5, 'just terrible'),
	(10, 5, 'I\'ll definitely be coming back.'),
	(11, 6, 'super'),
	(12, 6, '9/10'),
	(13, 7, 'Food was great, service was eh'),
	(14, 7, 'superb work'),
	(15, 8, 'very responsive server'),
	(16, 8, 'just okay'),
	(17, 9, 'paid great attention to our needs'),
	(18, 9, 'I don\'t always tip so well but they deserved it');
/*!40000 ALTER TABLE `visits` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
