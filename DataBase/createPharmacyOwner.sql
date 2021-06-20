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
 * Caminho: E:/GITHUB 1190402/Licenciatura/2ºANO/1ºSEMESTRE/LAPR3/DataBase/createPharmacyOwner.sql
 * Ficheiro:  createPharmacyOwner.sql
 * Última Edição: 18/05/21, 19:15
 * Nome da Classe: createPharmacyOwner.sql
 */

create or replace procedure createPharmacyOwner(u_email PharmacyOwner.UserEmail%type, u_NIF users.NIF%type, u_name users.name%type, u_password users.password%type)

    is

begin

    insert into users (email, NIF, name, password)
    values (u_email, u_NIF, u_name, u_password);

    insert into PharmacyOwner(UserEmail)
    values (u_email);

end;
/
commit;