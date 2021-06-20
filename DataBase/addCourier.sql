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
 * Caminho: E:/GITHUB 1190402/Licenciatura/2ºANO/1ºSEMESTRE/LAPR3/DataBase/addCourier.sql
 * Ficheiro:  addCourier.sql
 * Última Edição: 18/05/21, 19:15
 * Nome da Classe: addCourier.sql
 */

create or replace procedure addCourier(pName Courier.name%TYPE,
                                      pEmail Courier.email%TYPE,
                                      pNif Courier.nif%TYPE, 
                                      pNiss courier.niss%TYPE,                                    
                                      pState Courier.state%TYPE,
                                      pPharmacyId Courier.scooterPharmacyID%type,
                                      pVehicleId Courier.scooterVehicleID%type)
    IS
    vAdded INTEGER;
BEGIN

    INSERT INTO Courier (name,email,nif,niss,state,scooterPharmacyID,scooterVehicleID) VALUES (pName,pEmail,pNif,pNiss,pState,pPharmacyId,pVehicleId);

    SELECT count(*) INTO vAdded FROM Courier where nif=pNif;

    if vAdded is null then
            raise_application_error(-20008, 'Courier not added!');
    end if;

END;