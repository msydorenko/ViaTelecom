delete from viatelecom.spr_roles;
delete from viatelecom.spr_services;
delete from viatelecom.spr_addresses;
delete from viatelecom.spr_contacts;
delete from viatelecom.spr_bills;
delete from viatelecom.users;
delete from viatelecom.tariffs;
delete from viatelecom.user_orders;
INSERT INTO viatelecom.spr_roles (name) VALUE ('admin'); 
INSERT INTO viatelecom.spr_roles (name) VALUE ('client'); 

INSERT INTO viatelecom.spr_addresses (country, city, street, house, flat) VALUE ('Ukkraine', 'Kharkiv', 'Gromova', '31', '26');
INSERT INTO viatelecom.spr_addresses (country, city, street, house, flat) VALUE ('Ukkraine', 'Kharkiv', 'Artema', '11', '5');

INSERT INTO viatelecom.spr_contacts (phone_number, email) VALUE ('+380958761234', 'email1@gmail.com');
INSERT INTO viatelecom.spr_contacts (phone_number, email) VALUE ('+380639872345', 'email2@gmail.com');

INSERT INTO viatelecom.spr_bills (number, value) VALUE ('380958761234', '0');
INSERT INTO viatelecom.spr_bills (number, value) VALUE ('380639872345', '0');


INSERT INTO viatelecom.users (login, password, first_name, last_name, blocked, spr_bills_id, spr_addresses_id, spr_contacts_id, spr_roles_id)
VALUE ('+380951111111', 'rIUdEeWuO1dborx/pJOb8BZ6yThmjceXwi8z/YUWJBs=$vfy/qneJ0orCPFzhkjFaPabSCRxA1OaJtdMUf0JFTj0=', 'John', 'Bank', '0', '1', '1', '1', '1');

INSERT INTO viatelecom.users (login, password, first_name, last_name, blocked, spr_bills_id, spr_addresses_id, spr_contacts_id, spr_roles_id)
VALUE ('+380952222222', 'Q+4501mIH5nHbCYoF88E6NUskBV9G+i3DwO4F5tlHzs=$s0kY6/jw6khOmSthuS0A3xyAMxXtCe6BLSjXHCUi/WA=', 'Ann', 'Smit', '0', '2', '2', '2', '2');

INSERT INTO viatelecom.spr_services (name) VALUE ('Internet');
INSERT INTO viatelecom.spr_services (name) VALUE ('TV');
INSERT INTO viatelecom.spr_services (name) VALUE ('Telephone');

INSERT INTO viatelecom.tariffs (spr_services_id, name, price, description) VALUE ('2', 'Econom75', '75', 'Service with a minimum number of TV channels.');
INSERT INTO viatelecom.tariffs (spr_services_id, name, price, description) VALUE ('2', 'FullHappy', '200', 'Includes channels sport+, fishing+, ЕМ1000, lottery.');
INSERT INTO viatelecom.tariffs (spr_services_id, name, price, description) VALUE ('2', 'KidsPlanet', '50', 'More than 20 channels of cartoons for your children.');
INSERT INTO viatelecom.tariffs (spr_services_id, name, price, description) VALUE ('2', 'TV2000', '100', 'You can find a show or movie for every taste.');
INSERT INTO viatelecom.tariffs (spr_services_id, name, price, description) VALUE ('2', 'FamilyTV', '100', 'Entertainment for the whole family.');
INSERT INTO viatelecom.tariffs (spr_services_id, name, price, description) VALUE ('2', 'DiscaveryTV', '100', 'The service package contains many educational channels for creativity, travel, education.');
INSERT INTO viatelecom.tariffs (spr_services_id, name, price, description) VALUE ('3', 'Phone', '50', ' Fixed-line subscribers.');
INSERT INTO viatelecom.tariffs (spr_services_id, name, price, description) VALUE ('1', 'Internet+', '250', 'Internet service with maximum speed.');
INSERT INTO viatelecom.tariffs (spr_services_id, name, price, description) VALUE ('1', 'Internet100', '200', 'Internet service with speed 100mb/sec.');
INSERT INTO viatelecom.tariffs (spr_services_id, name, price, description) VALUE ('1', 'Internet50', '150', 'Internet service with speed 50mb/sec.');

