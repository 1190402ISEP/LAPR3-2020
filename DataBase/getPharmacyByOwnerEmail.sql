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
 * Caminho: E:/GITHUB 1190402/Licenciatura/2ºANO/1ºSEMESTRE/LAPR3/DataBase/getPharmacyByOwnerEmail.sql
 * Ficheiro:  getPharmacyByOwnerEmail.sql
 * Última Edição: 18/05/21, 19:15
 * Nome da Classe: getPharmacyByOwnerEmail.sql
 */

create or replace function getPharmacyByOwnerEmail(p_email users.email%type) return int
is
v_id int;
begin

    select id into v_id from PHARMACY where OWNER = p_email;

    return v_id;
exception
    when no_data_found then
        return null;
end;
/
commit;