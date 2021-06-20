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
 * Caminho: E:/GITHUB 1190402/Licenciatura/2ºANO/1ºSEMESTRE/LAPR3/DataBase/createPharmacy.sql
 * Ficheiro:  createPharmacy.sql
 * Última Edição: 18/05/21, 19:15
 * Nome da Classe: createPharmacy.sql
 */

create or replace function createPharmacy(o_email pharmacyOwner.userEmail%type,
                                          a_address address.coordinates%type,
                                          o_designation pharmacy.designation%type) return pharmacy.id%type
    is
    v_pharmacyID pharmacy.id%type;
begin

    select count(*) + 1 into v_pharmacyID from PHARMACY;

    insert into PHARMACY(id, designation, address, owner)
    values (v_pharmacyID, o_designation, a_address,o_email);


    return v_pharmacyID;
end;
/
commit;