begin
    for r in (select 'drop table ' || table_name || ' cascade constraints' cmd from user_tables order by table_name)
        loop
            execute immediate (r.cmd);
        end loop;
end;
/



create table Users
(
    email    varchar(100)
        constraint pkEmailUser primary key,
    nif      integer
        constraint ukNifUser unique
        constraint ckNifUser check (nif >= 10000000 and nif <= 999999999),
    name     varchar(50)
        constraint nnNameUser not null,

    password varchar(100)
        constraint nnPasswordUsers not null
);

create table Administrator
(
    Useremail varchar(100)
        constraint pkEmailAdministrator primary key
);


create table Scooter
(
    ID         integer,

    pharmacyID integer
        constraint nnPharmacyIDScooter not null,
    qrCode     integer
        constraint nnQrCodeScooter not null
        constraint ckQrCodeScooter check (qrCode = 1 or qrCode = 0), --1 significa que ha qrcode de 0 significa que não ha qrcode
    CONSTRAINT pkScooter primary key (id, pharmacyID)


);

create table Drone
(
    ID         integer,

    pharmacyID integer
        constraint nnPharmacyIDDrone not null,

    constraint pkDrone primary key (id, pharmacyID)
);

create table Vehicle
(
    id                    integer,

    batteryCapacity       float
        constraint nnBatteryCapacity not null,
    pharmacyID            integer
        constraint nnPharmacyIDVehicle not null,
    state                 integer
        constraint nnStateScooter not null,
    maxPayload            float
        constraint nnMaxPayloadScooter not null,
    battery               float
        constraint ckBatteryScooter check (battery >= 0 and battery <= 100),
    lastStartChargingDate date,
    CONSTRAINT pkVehicle primary key (id, pharmacyid)

);

create table VehicleState
(

    id          integer generated always as identity primary key,
    designation varchar(100)
        constraint nnDesignationVehicleState not null
);


create table Client
(
    Useremail   varchar(100)
        constraint pkEmailClient primary key,
    coordinates varchar(50)
        constraint nnCoordinatesClient not null,
    credits     integer default 0
        constraint nnCreditsClient not null

);

create table BasketProducts
(
    userEmail varchar(100),
    barcode   integer,
    quantity  integer
        constraint nnQuantityBasketProducts not null
        constraint ckQuantityBasketProducts check (quantity >= 1),
    CONSTRAINT pkBasketProducts primary key (userEmail, barcode)

);

create table Address
(
    coordinates varchar(50)
        constraint pkCoordinatesAddress primary key,
    street      varchar(100)
        constraint nnStreetAddress not null,
    postalCode  varchar(20)
        constraint nnPostalCodeAddress not null,
    doorNumber  integer
        constraint nnDoorNumberAddress not null
);

create table MapPoint
(
    coordinates varchar(100)
        constraint pkCoordinatesMapPoint primary key,
    altitude    float
        constraint nnAltitudeMapPoint not null,
    designation varchar(100)
        constraint nnDesignationMapPoint not null
);

create table Path
(
    id            integer generated always as identity primary key,
    point1        varchar(100)
        constraint nnPoint1Path not null,
    point2        varchar(100)
        constraint nnPoint2Path not null,
    conectionType integer
        constraint ckConectionTypePath check (conectionType >= 1 and conectionType <= 3)
);



create table Courier
(
    nif               integer
        constraint ckNifCourier check (nif >= 100000000 and nif <= 999999999)
        constraint pkNifCourier primary key,
    niss              char(11)
        constraint ukNiss unique,
    name              varchar(100)
        constraint nnNameCourier not null,
    email             varchar(100)
        constraint nnEmailCourier not null,
    state             integer
        constraint nnStateCourier not null,
    scooterPharmacyID integer
        constraint nnScooterPharmacyID not null,
    scooterVehicleID  integer
        constraint nnScooterVehicleid NOT NULL

);

create table CourierState
(
    id          integer generated always as identity primary key,
    designation varchar(100)
        constraint nnDesignationCourierState not null

);

create table OrderState
(
    id          integer
        constraint pkIDoRDERsTATE primary key,
    designation varchar(50)
        constraint nnDesignationOrderState not null
);

create table Orders
(
    id              integer
        constraint pkIDOrders primary key,
    orderDate       date
        constraint nnOrderDateOrder not null,
    orderState      integer
        constraint nnOrderStateOrder not null,

    idDelivery      integer,
    requestPharmacy integer
        constraint nnRequestPharmacyidOrder not null,


    totalWeight     float
        constraint nnTotalWeightOrders not null
);

create table ClientOrder
(
    id               integer
        constraint pkIDClientOrder primary key,
    nif              integer
        constraint ckNIFClientOrder check (nif >= 100000000 and nif <= 999999999),
    usedCredits      integer
        constraint nnUsedCreditsClientOrder not null,
    useremail        varchar(100)
        constraint nnUseremailClientOrder not null,
    CreditCardnumber char(16)
        constraint nncCreditCardNumberClientOrder not null
);

create table PharmacyOrder
(
    id         integer
        constraint pkIDPharmacyOrder primary key,
    pharmacyID integer
        constraint nnPharmacyIDPharmacyOrder not null
);


create table Delivery
(
    idDelivery        integer generated always as identity primary key,
    nif               integer,
    dronePharmacyID   integer,
    droneID           integer,
    scooterPharmacyID integer,
    scooterID         integer,
    distanceMeters    float
        constraint nnDeliveryDistance not null,
    timeMinutes       float
        constraint nnTimeMinutesDelivery not null,
    energyCostWH      float
        constraint nnEnergyCostDelivery not null
);

create table Invoice
(
    orderId            integer
        constraint pkOrderIDInvoice primary key,
    totalPrice         float
        constraint nnTotalPrinceInvoice not null,
    deliveryFee        float
        constraint nnDeliveryFeeInvoice not null,
    productsTotalPrice float
        constraint nnProductsTotalPrice not null,
    totalWithDiscount  float
        constraint nnTotalWithDiscount not null

);

create table ProductPerOrder
(
    barcode  integer,
    orderID  integer,
    quantity integer
        constraint ckQuantityProductPerOrder check (quantity >= 1),
    Constraint pkProductPerOrder primary key (barcode, orderID),
    IVA      float
        constraint nnIVAProdutPerOrder not null

);

create table Product
(
    id         integer
        constraint pkIdProduct primary key,
    name       varchar(100)
        constraint nnNameProduct not null,
    stock      integer
        constraint nnStockProduct not null,
    price      float
        constraint nnPriceProduct not null,
    weight     float
        constraint nnWeightProduct not null,
    IVA        float
        constraint nnIVAProduct not null,
    Pharmacyid integer
        constraint nnPharmacyidProduct not null,
    barCode    char(10)
        constraint nnBarCodeProduct not null
);



create table Pharmacy
(
    id          integer
        constraint pkIDFarmacia primary key,
    designation varchar(100)
        constraint nnDesignationPharmacy not null,
    address     varchar(50)
        constraint nnAddressPharmacy not null,
    owner       varchar(100)
        constraint nnOwnerPharmacy not null
);

create table PharmacyOwner
(
    UserEmail varchar(100)
        constraint pkUserEmailPharmacyOwner primary key
);

create table Park
(
    id                integer
        constraint pkIDPark primary key,
    maxCapacity       integer
        constraint nnMaxCapacity not null,
    pharmacyID        integer
        constraint nnPharmacyIDPark not null,
    parkTypeID        integer
        constraint nnParkTypeIDPark not null,
    currentOccupation integer default 0
        constraint nncurrentOccupation not null,
    TotalVoltage      integer
);

CREATE TABLE ParkType
(
    id          integer
        constraint pkIDParkType primary key,
    designation varchar(100)
        constraint nnParkTypeDesignation not null
);


create table ParkPlace
(
    id                integer
        constraint pkidParkPlace primary key,
    Parkid            integer
        constraint nnParkidParkPlace not null,
    chargingStationID integer,
    isAvailable       integer default 1
        constraint ckIsAavilable check (isAvailable = 1 or isAvailable = 0)
);

create table ChargingStation
(
    id integer
        constraint pkIDChargingStation primary key
);



create table CreditCard
(
    cardNumber   char(16)
        constraint pkCardNumberCreditCard primary key,
    email        varchar(100)
        constraint nnEmailCreditCard not null,
    cvv          integer
        CONSTRAINT ckCVVCreditCard check (cvv >= 100 and cvv <= 999),
    expirateDate date
        constraint nnExpirateDateCreditCard not null
        constraint ckExpirateDate check (to_char(expirateDate, 'dd') = 1),
    creationDate date
        constraint nnCreationDateCreditCard not null
);



ALTER TABLE Administrator
    ADD CONSTRAINT fkAdministratorUseremail FOREIGN KEY (Useremail) REFERENCES Users (email);


ALTER TABLE Client
    ADD CONSTRAINT fkClientUseremail FOREIGN KEY (Useremail) REFERENCES Users (email);
ALTER TABLE Client
    ADD CONSTRAINT fkClientCoordinates FOREIGN KEY (coordinates) REFERENCES Address (coordinates);

ALTER TABLE BasketProducts
    ADD CONSTRAINT fkBasketProductsUserEmail FOREIGN KEY (userEmail) REFERENCES Client (Useremail);



ALTER TABLE Orders
    ADD CONSTRAINT fkOrderOrderState FOREIGN KEY (orderState) REFERENCES OrderState (id);
ALTER TABLE ClientOrder
    ADD CONSTRAINT fkClientOrderUseremail FOREIGN KEY (useremail) REFERENCES Client (Useremail);
ALTER TABLE Orders
    ADD CONSTRAINT fkOrderIdDelivery FOREIGN KEY (idDelivery) REFERENCES Delivery (idDelivery);
ALTER TABLE Orders
    ADD CONSTRAINT fkOrderRequestPharmacyid FOREIGN KEY (requestPharmacy) REFERENCES Pharmacy (id);
ALTER TABLE ClientOrder
    ADD CONSTRAINT fkClientOrderCreditCardNumber FOREIGN KEY (CreditCardNumber) REFERENCES CreditCard (cardNumber);

alter table ClientOrder
    add constraint fkOrderIDClientOrder foreign key (id) references Orders (id);

alter table PharmacyOrder
    add constraint fkOrderIDPharmacyOrder foreign key (id) references ORDERS (ID);
alter table PharmacyOrder
    add constraint fkpharmacyIDPharmacyOrder foreign key (pharmacyID) references PHARMACY (ID);



ALTER TABLE Delivery
    ADD CONSTRAINT fkDeliveryCourierNif FOREIGN KEY (nif) REFERENCES Courier (nif);

alter table Delivery
    add constraint fkDeliveryDrone foreign key (droneID, dronePharmacyID) references Drone (id, pharmacyid);

alter table Delivery
    add constraint fkDeliveryScooter foreign key (scooterID, scooterPharmacyID) references Scooter (id, pharmacyid);

ALTER TABLE Invoice
    ADD CONSTRAINT fkInvoiceOrderId FOREIGN KEY (orderId) REFERENCES Orders (id);


ALTER TABLE ProductPerOrder
    ADD CONSTRAINT fkProductPerOrderOrderId FOREIGN KEY (orderID) REFERENCES Orders (id);

ALTER TABLE Product
    ADD CONSTRAINT fkProductPharmacyid FOREIGN KEY (Pharmacyid) REFERENCES Pharmacy (id);

ALTER TABLE Pharmacy
    ADD CONSTRAINT fkPharmacyAddress FOREIGN KEY (address) REFERENCES Address (coordinates);
ALTER TABLE Pharmacy
    ADD CONSTRAINT fkPharmacyOwner FOREIGN KEY (owner) REFERENCES PharmacyOwner (UserEmail);

ALTER TABLE PharmacyOwner
    ADD CONSTRAINT fkPharmacyOwnerUserEmail FOREIGN KEY (UserEmail) REFERENCES Users (email);

ALTER TABLE Park
    ADD CONSTRAINT fkParkPharmacyID FOREIGN KEY (pharmacyID) REFERENCES Pharmacy (id);

alter table park
    add constraint fkParkTypePark foreign key (parkTypeID) references ParkType (id);

ALTER TABLE ParkPlace
    ADD CONSTRAINT fkParkPlaceParkid FOREIGN KEY (Parkid) REFERENCES Park (id);
ALTER TABLE ParkPlace
    ADD CONSTRAINT fkParkPlaceChargingStationID FOREIGN KEY (ChargingStationID) REFERENCES ChargingStation (id);


ALTER TABLE CreditCard
    ADD CONSTRAINT fkCreditCardEmail FOREIGN KEY (email) REFERENCES Client (Useremail);

Alter table Scooter
    add constraint fkScooter foreign key (id, pharmacyID) references Vehicle (id, pharmacyID);

ALTER table Vehicle
    add constraint fkVehiclePharmacyID foreign key (pharmacyID) references Pharmacy (id);
ALTER table Vehicle
    add constraint fkStateIDVehicle foreign key (state) references VehicleState (id);

alter table drone
    add constraint fkDrone foreign key (id, pharmacyID) references Vehicle (id, pharmacyID);



ALTER TABLE Courier
    add constraint fkStateCourer Foreign key (state) references CourierState (id);

ALTER TABLE COURIER
    ADD CONSTRAINT fkScooterCourier foreign key (scooterPharmacyID, scooterVehicleID) references Scooter (pharmacyID, ID);



ALTER TABLE Address
    add constraint fkCoordinatesAddress Foreign key (coordinates) references MapPoint (COORDINATES);

ALTER TABLE Path
    add constraint fkPoint1Path Foreign key (point1) references MapPoint (COORDINATES);
ALTER TABLE Path
    add constraint fkPoint2Path Foreign key (point2) references MapPoint (COORDINATES);



commit;

--inserts iniciais importantes

INSERT INTO VehicleState (designation)
VALUES ('AVAILABLE'); --1

INSERT INTO VehicleState (DESIGNATION)
VALUES ('UNAVAILABLE'); --2


INSERT INTO VehicleState (DESIGNATION)
VALUES ('BROKEN');--3


INSERT INTO VehicleState (DESIGNATION)
VALUES ('CHARGING');--4

INSERT INTO CourierState (designation)
VALUES ('AVAILABLE');

INSERT INTO CourierState (designation)
VALUES ('UNAVAILABLE');

insert into ParkType (id, designation)
values (1, 'Scooter');
insert into ParkType (id, designation)
values (2, 'Drone');



insert into ORDERSTATE (id, designation)
values (1, 'In process');
insert into ORDERSTATE (id, designation)
values (2, 'In destribution');
insert into ORDERSTATE (id, designation)
values (3, 'Canceled');
insert into ORDERSTATE (id, designation)
values (4, 'Delivered');
insert into ORDERSTATE (id, designation)
values (5, 'Waiting for order-back');

commit;

--deletes

        DELETE
        FROM PHARMACYORDER;
        DELETE
        FROM CLIENTORDER;
        DELETE
        FROM PRODUCTPERORDER;
        DELETE
        FROM INVOICE;
        DELETE
        FROM ORDERS;
        --DELETE FROM ORDERSTATE;
        DELETE
        FROM DELIVERY;
        DELETE
        FROM COURIER;
        --DELETE FROM COURIERSTATE;
        DELETE
        FROM SCOOTER;
        DELETE
        FROM DRONE;
        DELETE
        FROM VEHICLE;
        DELETE
        FROM PARKPLACE;
        DELETE
        FROM CHARGINGSTATION;
        DELETE
        FROM PARK;
        --DELETE FROM PARKTYPE;
        DELETE
        FROM CREDITCARD;
        DELETE
        FROM BASKETPRODUCTS;
        DELETE
        FROM CLIENT;
        DELETE
        FROM PRODUCT;
        DELETE
        FROM PHARMACY;
        DELETE
        FROM PHARMACYOWNER;
        DELETE
        FROM ADDRESS;
        /*
 * António Daniel Barbosa Fernandes, [17.05.21 19:26]
 * Copyright (c) 2021.
 *
 * Programador: António Daniel Barbosa Fernandes
 *
 * UserName: anton
 * ----------------
 * INFORMAÇÕES:
 * Nome do Projeto: GITHUB 1190402
 * Módulo: GITHUB 1190402
 * Caminho: E:/GITHUB 1190402/Licenciatura/2ºANO/1ºSEMESTRE/LAPR3/DataBase/DataBase_Tables.sql
 * Ficheiro:  DataBase_Tables.sql
 * Última Edição: 18/05/21, 19:15
 * Nome da Classe: DataBase_Tables.sql
 */

        --DELETE FROM VEHICLESTATE;
        DELETE
        FROM ADMINISTRATOR;
        DELETE
        FROM USERS;
        DELETE
        FROM PATH;
        DELETE
        FROM MAPPOINT;
        commit;


