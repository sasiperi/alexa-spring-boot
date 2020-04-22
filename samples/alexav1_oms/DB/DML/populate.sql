/**** Customer *************** */

INSERT INTO `ad_6c8971fa5f71c05`.`customer` (`customer_id`, `created`, `last_updated_by`, `updated`, `dob`, `email`, `first_name`, `last_name`, `password`) VALUES ('1', now(), 'sasi', now(), '01-02-1999', 'a@b.com', 'Sasi', 'Peri', 'sp123');
INSERT INTO `ad_6c8971fa5f71c05`.`customer` (`customer_id`, `created`, `last_updated_by`, `updated`, `dob`, `email`, `first_name`, `last_name`, `password`) VALUES ('2', now(), 'sasi', now(), now(), 'b@c.com', 'kala', 'peri', 'kp123');

INSERT INTO `customer` (`customer_id`, `created`, `last_updated_by`, `updated`, `dob`, `email`, `first_name`, `last_name`, `password`) VALUES ('1', now(), 'sasi', now(), '01-02-1999', 'a@b.com', 'Sasi', 'Peri', '$2a$10$IJ7KSbJdcvY/DjYzJioIsecYO7kQq6k7JJPj1KDdeOHjV.Z66sa.a');
INSERT INTO customer` (`customer_id`, `created`, `last_updated_by`, `updated`, `dob`, `email`, `first_name`, `last_name`, `password`) VALUES ('2', now(), 'sasi', now(), now(), 'b@c.com', 'kala', 'peri', '$2a$10$OToDAGW0V08lqulhgDilwuptT0Hq3JrvKQ5Vg..RhgnjLkxHJCvQ6');


/* *********    */
//702016
insert into customer_order (order_number, created, last_updated_by, updated, order_status, order_status_reason, order_total, customer_id)
values ('123',now(),'sasi','2017-02-01' ,'SHIPPED','This order is shipped on 2017-Feb-20', 200,1);

insert into customer_order (order_number, created, last_updated_by, updated, order_status, order_status_reason, order_total, customer_id)
values ('555','2017-03-01' ,'sasi',now(),'SHIPPED','This order is shipped on 2017-March-10', 150,1);

insert into customer_order (order_number, created, last_updated_by, updated, order_status, order_status_reason, order_total, customer_id)
values ('568',now(),'sasi',now(),'PENDING','This order is waiting to for inurance verification',10,1);

insert into customer_order (order_number, created, last_updated_by, updated, order_status, order_status_reason, order_total, customer_id)
values ('789','2017-02-28','sasi',now(),'CANCELLED','This order was cancelled by customer on 2017-March-05',600,2);




/* *********    */
insert into customer_order_detail (line_item_id, created, last_updated_by, updated, price, product_code, product_desc, quantity, order_number)
values (1,now(),'sasi',now(),50,'5253676','Similac Sensitive Ready to Feed Bottle',2,123);
insert into customer_order_detail (line_item_id, created, last_updated_by, updated, price, product_code, product_desc, quantity, order_number)
values (2,now(),'sasi',now(),100,'75869353','Enfamil Gentlease Powder',1,123);

insert into customer_order_detail (line_item_id, created, last_updated_by, updated, price, product_code, product_desc, quantity, order_number)
values (3,now(),'sasi',now(),150,'ZPL12000','Cardinal Health Essentials Lancng Device',1,555);

insert into customer_order_detail (line_item_id, created, last_updated_by, updated, price, product_code, product_desc, quantity, order_number)
values (4,now(),'sasi',now(),10,'70022136','OneTouch Delica Lancet',5,568);

insert into customer_order_detail (line_item_id, created, last_updated_by, updated, price, product_code, product_desc, quantity, order_number)
values (5,now(),'sasi',now(),10,'70022136','OneTouch Delica Lancet',10,789);
insert into customer_order_detail (line_item_id, created, last_updated_by, updated, price, product_code, product_desc, quantity, order_number)
values (6,now(),'sasi',now(),100,'75869353','Enfamil Gentlease Powder',1,789);
insert into customer_order_detail (line_item_id, created, last_updated_by, updated, price, product_code, product_desc, quantity, order_number)
values (7,now(),'sasi',now(),400,'ZPL12000','Cardinal Health Essentials Lancng Device',1,789);
