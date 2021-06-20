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
 * Caminho: E:/GITHUB 1190402/Licenciatura/2ºANO/1ºSEMESTRE/LAPR3/DataBase/updateCourier.sql
 * Ficheiro:  updateCourier.sql
 * Última Edição: 18/05/21, 19:15
 * Nome da Classe: updateCourier.sql
 */

create or replace procedure updateCourier(pName Courier.name%TYPE,
                                      pEmail Courier.email%TYPE,
                                      pNif Courier.nif%type,
                                      pNiss Courier.niss%type,
                                      pState Courier.state%TYPE,
                                      pPharmacyId Courier.scooterPharmacyID%type,
                                      pVehicleId Courier.scooterVehicleID%type)
    IS
    vCountAux INTEGER;
BEGIN
    if pName is not null then
        UPDATE Courier SET name=pName WHERE nif = pNif and scooterPharmacyID=pPharmacyId;
    end if;
    
    if pEmail is not null then
        UPDATE Courier SET email=pEmail WHERE nif = pNif and scooterPharmacyID=pPharmacyId;
    end if;
    
    if pNif is not null then
        UPDATE Courier SET nif=pNif WHERE nif = pNif and scooterPharmacyID=pPharmacyId;
    end if;
    
    if pNiss is not null then
        UPDATE Courier SET niss=pNiss WHERE niss = pNiss and scooterPharmacyID=pPharmacyId;
    end if;
    
    if pState is not null then
        UPDATE Courier SET state=pState WHERE nif = pNif and scooterPharmacyID=pPharmacyId;
    end if;
    
    if pPharmacyId is not null then
        UPDATE Courier SET scooterPharmacyID=pPharmacyId WHERE nif = pNif;
    end if;
    
    if pVehicleId is not null then
        UPDATE Courier SET scooterVehicleID=pVehicleId WHERE nif = pNif and scooterPharmacyID=pPharmacyId;
    end if;
    
    

END;
/
commit;
