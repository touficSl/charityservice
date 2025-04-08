INSERT INTO `settings` (`adminkey`, `isdefault`) VALUES ('6OdJ-WAqAHAUAHKRu9o3KzUup57gKslumqImeJqrts', b'1');



--- AUTH

INSERT INTO `authorization` (`user_role`, `api`) VALUES ('Admin', '/api/request/list');
INSERT INTO `authorization` (`user_role`, `api`) VALUES ('GRCAdmin', '/api/request/list');

INSERT INTO `menu_authorization` (`menu_auth_id`, `api`, `isget`) VALUES ('managerequests', '/api/request/list', b'1');
INSERT INTO `menu_authorization` (`menu_auth_id`, `api`, `ispost`, `isupdate`) VALUES ('managerequests', '/api/request/list', b'1', b'1');

INSERT INTO `menu` (`id`, `lang`, `name`, `href`, `icon`, `order`, `auth_id`, `parent_id`) VALUES ('managerequestsen', 'en', 'Requests', 'managerequests', 'fa fa-folder-o', 400, 'managerequests', 'manageen');
INSERT INTO `menu` (`id`, `lang`, `name`, `href`, `icon`, `order`, `auth_id`, `parent_id`) VALUES ('managerequestsar', 'ar', 'الطلبات', 'managerequests', 'fa fa-folder-o', 400, 'managerequests', 'managear');

INSERT INTO `menu_role` (`menu_id`, `user_role`) VALUES ('managerequestsen', 'Admin');
INSERT INTO `menu_role` (`menu_id`, `user_role`) VALUES ('managerequestsar', 'Admin');

UPDATE `menu` SET `isget` = b'1', `ispost` = b'1', `isupdate` = b'1', `isdelete` = b'1', `isconfiguration` = b'1' WHERE (`id` = 'managerequestsen');
UPDATE `menu` SET `isget` = b'1', `ispost` = b'1', `isupdate` = b'1', `isdelete` = b'1', `isconfiguration` = b'1' WHERE (`id` = 'managerequestsar');