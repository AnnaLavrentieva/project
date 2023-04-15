insert into persons (person_id,password,role)
values ('Slava Ukraine','$2a$12$rK7xLwpcIDeSzy4chgps/uNBT1oZk9H48ry0qBOPIac4klV5SLajK','ADMIN');
insert into persons (person_id,password,role)
values ('Ann Lav','$2a$12$rK7xLwpcIDeSzy4chgps/uNBT1oZk9H48ry0qBOPIac4klV5SLajK','MANAGER');
insert into persons (person_id,password,role)
values ('Eva Javelina','$2a$12$rK7xLwpcIDeSzy4chgps/uNBT1oZk9H48ry0qBOPIac4klV5SLajK','ANALYST');
insert into persons (person_id,password,role)
values ('Orest Hymars','$2a$12$rK7xLwpcIDeSzy4chgps/uNBT1oZk9H48ry0qBOPIac4klV5SLajK','MANAGER');

insert into warehouses (warehouse_id)
values ('Main');
insert into warehouses (warehouse_id)
values ('Assembly');
insert into warehouses (warehouse_id)
values ('Repair');
insert into warehouses (warehouse_id)
values ('Destruction');
insert into warehouses (warehouse_id)
values ('Subunit-A');
insert into warehouses (warehouse_id)
values ('Subunit-B');

insert into ware_groups (group_id)
values ('Generators');
insert into ware_groups (group_id)
values ('Antennas');
insert into ware_groups (group_id)
values ('Batteries');
insert into ware_groups (group_id)
values ('Radios');
insert into ware_groups (group_id)
values ('Drones');

insert into invoices (invoice_id,invoice_number,invoice_date,invoice_type,sum)
values ('9464e544-ce1c-4397-8bf7-71371d147487','35-85','2023-04-10','OFFICIAL','3091200.00');
insert into invoices (invoice_id,invoice_number,invoice_date,invoice_type,sum)
values ('bc4b11e1-1443-4efb-99bc-167f495137ad','35-86','2023-04-11','OFFICIAL','2794999.55');
insert into invoices (invoice_id,invoice_number,invoice_date,invoice_type,sum)
values ('2fb64ec3-1d9d-4cda-aa0d-87f20dab744e','35-87','2023-04-12','OFFICIAL','509400.00');
insert into invoices (invoice_id,invoice_number,invoice_date,invoice_type,sum)
values ('01f8b044-2153-43eb-986c-cfde29fffe07','777-66','2023-04-13','SPONSORED','0.0');
insert into invoices (invoice_id,invoice_number,invoice_date,invoice_type,sum)
values ('d6d72d59-36ce-4def-9224-25664409afa0','','2023-03-31','UNREGISTERED','0.0');

insert into wares (ware_id,ware_name,serial_number,inventory_number,production_year,deployment_date,
amount,price,invoice_id,person_id,group_id,warehouse_id,ware_type)
values ('ac77aef8-61ce-4050-adcf-e25044258f18','Field Shortwave Radio Station Hytera s14.88',
'','23423444445','2022','2023-04-10','10','115000.00','9464e544-ce1c-4397-8bf7-71371d147487',
'Slava Ukraine','Radios','Main','item');
insert into ware_movement_records (record_id,record_date,movement,ware_id,person_id,warehouse_id)
values ('8654903d-54d0-40f8-8ff0-5f47328b4ec7','2023-04-10 11:10:25','ARRIVAL',
'ac77aef8-61ce-4050-adcf-e25044258f18','Slava Ukraine','Main');

insert into wares (ware_id,ware_name,serial_number,inventory_number,production_year,deployment_date,
amount,price,invoice_id,person_id,group_id,warehouse_id,ware_type)
values ('b80cd8f9-242e-4524-8ee3-de8b94c2964a','Frequency generator set 2.4 MHz',
'','111123213','2021','2023-04-10','1','10000.00','9464e544-ce1c-4397-8bf7-71371d147487',
'Slava Ukraine','Generators','Main','item');
insert into ware_movement_records (record_id,record_date,movement,ware_id,person_id,warehouse_id)
values ('f0e07f38-f5d6-4bb1-93ef-6b981c0aaa4b','2023-04-10 11:10:25','ARRIVAL',
'b80cd8f9-242e-4524-8ee3-de8b94c2964a','Slava Ukraine','Main');

insert into wares (ware_id,ware_name,serial_number,inventory_number,production_year,deployment_date,
amount,price,invoice_id,person_id,group_id,warehouse_id,ware_type)
values ('01c403b1-e309-4d96-a128-3cb12cbad5f0','Portable Radio Station Harris Falcon III RF-7800H-MP',
'76895-AQ','23423444446','2023','2023-04-10','34','56800.00','9464e544-ce1c-4397-8bf7-71371d147487',
'Slava Ukraine','Radios','Main','item');
insert into ware_movement_records (record_id,record_date,movement,ware_id,person_id,warehouse_id)
values ('2a09394d-46de-4e68-893b-f7c4a843bea5','2023-04-10 11:10:25','ARRIVAL',
'01c403b1-e309-4d96-a128-3cb12cbad5f0','Slava Ukraine','Main');


insert into wares (ware_id,ware_name,serial_number,inventory_number,production_year,deployment_date,
amount,price,invoice_id,person_id,group_id,warehouse_id,ware_type)
values ('af1df68b-160c-46c2-ae71-97f8097fb57e','Quadcopter Autel EVO II Pro 102000192',
'34567800','46457','2022','2023-04-12','20','61500.00','bc4b11e1-1443-4efb-99bc-167f495137ad',
'Ann Lav','Drones','Subunit-A','item');
insert into ware_movement_records (record_id,record_date,movement,ware_id,person_id,warehouse_id)
values ('6c29a94a-e472-491f-8fdc-6cea12140e7d','2023-04-12 14:15:42','ARRIVAL',
'af1df68b-160c-46c2-ae71-97f8097fb57e','Slava Ukraine','Main');
insert into ware_movement_records (record_id,record_date,movement,ware_id,person_id,warehouse_id)
values ('dc3ffb29-62b5-4614-8987-ce61eab50908','2023-04-12 17:17:33','TRANSFER',
'af1df68b-160c-46c2-ae71-97f8097fb57e','Ann Lav','Subunit-A');

insert into wares (ware_id,ware_name,serial_number,inventory_number,production_year,deployment_date,
amount,price,invoice_id,person_id,group_id,warehouse_id,ware_type)
values ('0ee67488-f763-4300-9e4c-590d22e39fdd','Drone with Thermal Camera Parrot Anafi USA',
'567489/1','432423425/C','2022','2023-04-12','45','6999.99','bc4b11e1-1443-4efb-99bc-167f495137ad',
'Ann Lav','Drones','Subunit-A','item');
insert into ware_movement_records (record_id,record_date,movement,ware_id,person_id,warehouse_id)
values ('04dbd7d9-78be-4a38-931a-fcc68fda5d59','2023-04-12 14:15:42','ARRIVAL',
'0ee67488-f763-4300-9e4c-590d22e39fdd','Slava Ukraine','Main');
insert into ware_movement_records (record_id,record_date,movement,ware_id,person_id,warehouse_id)
values ('7d8471bc-af51-4c07-97f8-39aef124c51b','2023-04-12 17:17:33','TRANSFER',
'0ee67488-f763-4300-9e4c-590d22e39fdd','Ann Lav','Subunit-A');

insert into wares (ware_id,ware_name,serial_number,inventory_number,production_year,deployment_date,
amount,price,invoice_id,person_id,group_id,warehouse_id,ware_type)
values ('46c57873-1202-4406-a758-47b2196650b1','Parrot Anafi USA drone thermal camera',
'658493IO','46458','2021','2023-04-12','5','250000.00','bc4b11e1-1443-4efb-99bc-167f495137ad',
'Ann Lav','Drones','Subunit-A','item');
insert into ware_movement_records (record_id,record_date,movement,ware_id,person_id,warehouse_id)
values ('3c38db7f-960f-457b-9e34-931d3c827c49','2023-04-12 14:15:42','ARRIVAL',
'46c57873-1202-4406-a758-47b2196650b1','Slava Ukraine','Main');
insert into ware_movement_records (record_id,record_date,movement,ware_id,person_id,warehouse_id)
values ('321ad34c-eeee-464d-8969-4ad21d89a4d7','2023-04-12 17:17:33','TRANSFER',
'46c57873-1202-4406-a758-47b2196650b1','Ann Lav','Subunit-A');


insert into wares (ware_id,ware_name,serial_number,inventory_number,production_year,deployment_date,
amount,price,invoice_id,person_id,group_id,warehouse_id,ware_type)
values ('85f35ad9-0e94-4a80-bdb9-04d246a63bc4','Directional Antenna 4Hawks Raptor SR Antenna',
'','432423426/D','2022','2023-04-12','25','1200.00','2fb64ec3-1d9d-4cda-aa0d-87f20dab744e',
'Orest Hymars','Antennas','Subunit-B','item');
insert into ware_movement_records (record_id,record_date,movement,ware_id,person_id,warehouse_id)
values ('d54210bf-516f-426b-ad20-20706143965f','2023-04-12 15:34:12','ARRIVAL',
'85f35ad9-0e94-4a80-bdb9-04d246a63bc4','Slava Ukraine','Main');
insert into ware_movement_records (record_id,record_date,movement,ware_id,person_id,warehouse_id)
values ('9f57876b-8c46-4906-9d06-cb36aedfab69','2023-04-12 19:15:26','TRANSFER',
'85f35ad9-0e94-4a80-bdb9-04d246a63bc4','Orest Hymars','Subunit-B');

insert into wares (ware_id,ware_name,serial_number,inventory_number,production_year,deployment_date,
amount,price,invoice_id,person_id,group_id,warehouse_id,ware_type)
values ('15de50b1-7661-4525-a0c1-859da77b4fc5','Directional Antenna 4Hawks Raptor SR Antenna',
'','432423426/D','2022','2023-04-12','35','1200.00','2fb64ec3-1d9d-4cda-aa0d-87f20dab744e',
'Eva Javelina','Antennas','Repair','item');
insert into ware_movement_records (record_id,record_date,movement,ware_id,person_id,warehouse_id)
values ('377fcd86-3f7d-47e1-b310-2eed5a776771','2023-04-12 15:34:12','ARRIVAL',
'15de50b1-7661-4525-a0c1-859da77b4fc5','Slava Ukraine','Main');
insert into ware_movement_records (record_id,record_date,movement,ware_id,person_id,warehouse_id)
values ('025bda2f-0d7e-4752-b263-8211563f2ea2','2023-04-12 19:15:26','TRANSFER',
'15de50b1-7661-4525-a0c1-859da77b4fc5','Orest Hymars','Subunit-B');
insert into ware_movement_records (record_id,record_date,movement,ware_id,person_id,warehouse_id)
values ('e0897e63-e0fa-45ab-a10d-05e4e934b33b','2023-04-15 10:10:46','REPAIR',
'15de50b1-7661-4525-a0c1-859da77b4fc5','Eva Javelina','Repair');

insert into wares (ware_id,ware_name,serial_number,inventory_number,production_year,deployment_date,
amount,price,invoice_id,person_id,group_id,warehouse_id,ware_type)
values ('f45b1404-5f9d-4e16-ba1c-0ab7682da3c1','Yagi Antenna ASPOR T1727 900/1700-2700 MHz 21 dB',
'','432423427/F','2022','2023-04-12','12','36450.00','2fb64ec3-1d9d-4cda-aa0d-87f20dab744e',
'Orest Hymars','Antennas','Subunit-B','item');
insert into ware_movement_records (record_id,record_date,movement,ware_id,person_id,warehouse_id)
values ('2b88a364-0967-4d00-a1c0-c9391a8de3e0','2023-04-12 15:34:12','ARRIVAL',
'f45b1404-5f9d-4e16-ba1c-0ab7682da3c1','Slava Ukraine','Main');
insert into ware_movement_records (record_id,record_date,movement,ware_id,person_id,warehouse_id)
values ('1b37a8fd-99b5-45e3-a389-58c93d9d3255','2023-04-12 19:15:28','TRANSFER',
'f45b1404-5f9d-4e16-ba1c-0ab7682da3c1','Orest Hymars','Subunit-B');


insert into wares (ware_id,ware_name,serial_number,inventory_number,production_year,deployment_date,
amount,price,invoice_id,person_id,group_id,warehouse_id,ware_type)
values ('50b71ecd-7ea8-4175-bd80-5708f5386ace','Parrot Anafi USA drone thermal camera',
'658494IO','46459','2022','2023-04-13','7','0.0','01f8b044-2153-43eb-986c-cfde29fffe07',
'Eva Javelina','Antennas','Subunit-A','item');
insert into ware_movement_records (record_id,record_date,movement,ware_id,person_id,warehouse_id)
values ('f5077dcd-adda-4a92-86aa-a191145a76e6','2023-04-13 15:34:12','ARRIVAL',
'50b71ecd-7ea8-4175-bd80-5708f5386ace','Slava Ukraine','Main');
insert into ware_movement_records (record_id,record_date,movement,ware_id,person_id,warehouse_id)
values ('b2cd8cf0-fa70-4163-9c8f-9ab12345faa7','2023-04-14 19:15:28','TRANSFER',
'50b71ecd-7ea8-4175-bd80-5708f5386ace','Eva Javelina','Subunit-A');

insert into wares (ware_id,ware_name,serial_number,inventory_number,production_year,deployment_date,
amount,price,invoice_id,person_id,group_id,warehouse_id,ware_type)
values ('daa87ede-a2aa-4219-a7ae-d4111648e712','28V battery',
'','213123/1','2023','2023-04-13','7','0.0','01f8b044-2153-43eb-986c-cfde29fffe07',
'Slava Ukraine','Batteries','Main','item');
insert into ware_movement_records (record_id,record_date,movement,ware_id,person_id,warehouse_id)
values ('0479c208-7d57-450e-aeb1-f92824420ac7','2023-04-13 15:34:12','ARRIVAL',
'daa87ede-a2aa-4219-a7ae-d4111648e712','Slava Ukraine','Main');


insert into wares (ware_id,ware_name,serial_number,inventory_number,production_year,deployment_date,
amount,price,invoice_id,person_id,group_id,warehouse_id,ware_type)
values ('78fde18d-f307-417e-ab97-266bf0d069cd','DJI Mavic 3 drone',
'','','2022','2023-03-31','6','0.0','d6d72d59-36ce-4def-9224-25664409afa0',
'Ann Lav','Drones','Subunit-B','item');
insert into ware_movement_records (record_id,record_date,movement,ware_id,person_id,warehouse_id)
values ('38230b32-7c31-4d89-b779-b554f030555c','2023-03-31 23:58:10','ARRIVAL',
'78fde18d-f307-417e-ab97-266bf0d069cd','Slava Ukraine','Main');
insert into ware_movement_records (record_id,record_date,movement,ware_id,person_id,warehouse_id)
values ('ab1223c8-d460-4e6f-95a6-0393517c28f6','2023-03-31 23:59:59','TRANSFER',
'78fde18d-f307-417e-ab97-266bf0d069cd','Ann Lav','Subunit-B');

insert into wares (ware_id,ware_name,serial_number,inventory_number,production_year,deployment_date,
amount,price,invoice_id,person_id,group_id,warehouse_id,ware_type)
values ('4dbe3ffe-fd26-4040-81c3-c062d7d98d2f','Motorola DP100500 UHF radio',
'','','2022','2023-03-31','5','0.0','d6d72d59-36ce-4def-9224-25664409afa0',
'Ann Lav','Radios','Subunit-B','item');
insert into ware_movement_records (record_id,record_date,movement,ware_id,person_id,warehouse_id)
values ('2e431194-21a8-468a-b28f-583d120a6edc','2023-03-31 23:58:10','ARRIVAL',
'4dbe3ffe-fd26-4040-81c3-c062d7d98d2f','Slava Ukraine','Main');
insert into ware_movement_records (record_id,record_date,movement,ware_id,person_id,warehouse_id)
values ('3640f2a5-d089-4425-b6a4-7efae3e42f87','2023-03-31 23:59:59','TRANSFER',
'4dbe3ffe-fd26-4040-81c3-c062d7d98d2f','Ann Lav','Subunit-B');

insert into wares (ware_id,ware_name,serial_number,inventory_number,production_year,deployment_date,
amount,price,invoice_id,person_id,group_id,warehouse_id,ware_type)
values ('3adf1fd8-7aa3-46ef-b344-1e983b86dcbf','Motorola DP100500 UHF radio',
'','','2022','2023-03-31','5','0.0','d6d72d59-36ce-4def-9224-25664409afa0',
'Slava Ukraine','Radios','Main','item');
insert into ware_movement_records (record_id,record_date,movement,ware_id,person_id,warehouse_id)
values ('8228aeab-9576-4213-9d7c-dc0be832bf14','2023-03-31 23:58:10','ARRIVAL',
'3adf1fd8-7aa3-46ef-b344-1e983b86dcbf','Slava Ukraine','Main');

insert into wares (ware_id,ware_name,serial_number,inventory_number,production_year,deployment_date,
amount,price,invoice_id,person_id,group_id,warehouse_id,ware_type)
values ('497bf5e8-ca37-4fe1-892c-97d6ae15ec61','Frequency generator set 2.4 MHz',
'','','2022','2023-03-31','2','0.0','d6d72d59-36ce-4def-9224-25664409afa0',
'Slava Ukraine','Generators','Main','item');
insert into ware_movement_records (record_id,record_date,movement,ware_id,person_id,warehouse_id)
values ('dda775ae-eeb7-4343-8e2b-a645e999fa0c','2023-03-31 23:58:10','ARRIVAL',
'497bf5e8-ca37-4fe1-892c-97d6ae15ec61','Slava Ukraine','Main');

insert into wares (ware_id,ware_name,serial_number,inventory_number,production_year,deployment_date,
amount,price,invoice_id,person_id,group_id,warehouse_id,ware_type)
values ('20f8c3fe-2753-40d1-9fd7-22005f104a81',' Antenna, frequency 2.4 MHz',
'','','2022','2023-03-31','5','0.0','d6d72d59-36ce-4def-9224-25664409afa0',
'Slava Ukraine','Antennas','Main','item');
insert into ware_movement_records (record_id,record_date,movement,ware_id,person_id,warehouse_id)
values ('4819499d-6115-41cb-a03b-64d7ecf168b1','2023-03-31 23:58:10','ARRIVAL',
'20f8c3fe-2753-40d1-9fd7-22005f104a81','Slava Ukraine','Main');