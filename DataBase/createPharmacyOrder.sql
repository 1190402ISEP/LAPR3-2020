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
 * Caminho: E:/GITHUB 1190402/Licenciatura/2ºANO/1ºSEMESTRE/LAPR3/DataBase/createPharmacyOrder.sql
 * Ficheiro:  createPharmacyOrder.sql
 * Última Edição: 18/05/21, 19:15
 * Nome da Classe: createPharmacyOrder.sql
 */

create or replace procedure createPharmacyOrder(u_orderid Orders.id%type, u_pharmacyid Orders.requestPharmacy%type)
    is

begin

    insert into PharmacyOrder (id, PharmacyID)
    values (u_orderid, u_pharmacyid);
    
end;
/
commit;