CREATE TABLE `customer` (
  `customer_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `last_updated_by` varchar(25) DEFAULT NULL,
  `updated` datetime NOT NULL,
  `dob` datetime NOT NULL,
  `email` varchar(50) NOT NULL,
  `first_name` varchar(25) NOT NULL,
  `last_name` varchar(25) NOT NULL,
  `password` varchar(100) NOT NULL,
  `customercol` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`customer_id`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;


CREATE TABLE `customer_order` (
  `order_number` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `last_updated_by` varchar(25) DEFAULT NULL,
  `updated` datetime NOT NULL,
  `order_status` varchar(25) NOT NULL,
  `order_status_reason` varchar(200) DEFAULT NULL,
  `order_total` bigint(20) NOT NULL,
  `customer_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`order_number`),
  KEY `FKf9abd30bhiqvugayxlpq8ryq9` (`customer_id`),
  CONSTRAINT `FKf9abd30bhiqvugayxlpq8ryq9` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=790 DEFAULT CHARSET=utf8;

 CREATE TABLE `customer_order_detail` (
  `line_item_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created` datetime NOT NULL,
  `last_updated_by` varchar(25) DEFAULT NULL,
  `updated` datetime NOT NULL,
  `price` int(11) NOT NULL,
  `product_code` varchar(25) NOT NULL,
  `product_desc` varchar(50) NOT NULL,
  `quantity` int(11) NOT NULL,
  `order_number` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`line_item_id`),
  KEY `FKovdiqxj2s9jx8b2ksdls7issw` (`order_number`),
  CONSTRAINT `FKovdiqxj2s9jx8b2ksdls7issw` FOREIGN KEY (`order_number`) REFERENCES `customer_order` (`order_number`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
