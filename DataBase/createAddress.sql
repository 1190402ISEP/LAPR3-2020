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
 * Caminho: E:/GITHUB 1190402/Licenciatura/2ºANO/1ºSEMESTRE/LAPR3/DataBase/createAddress.sql
 * Ficheiro:  createAddress.sql
 * Última Edição: 18/05/21, 19:15
 * Nome da Classe: createAddress.sql
 */

create or replace procedure createAddress( a_coordinates address.coordinates%type,
 a_street address.street%type,a_postalcode address.postalcode%type,
 a_doornumber address.doornumber%type )
    is
    v_coordinates address.coordinates%type;
begin

    insert into ADDRESS( coordinates, street, postalcode, doornumber )
    values ( a_coordinates, a_street, a_postalcode, a_doornumber );

    SELECT coordinates into v_coordinates
    from ADDRESS
    where coordinates = a_coordinates;

end;
/
commit;