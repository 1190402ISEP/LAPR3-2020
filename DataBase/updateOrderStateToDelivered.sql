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
 * Caminho: E:/GITHUB 1190402/Licenciatura/2ºANO/1ºSEMESTRE/LAPR3/DataBase/updateOrderStateToDelivered.sql
 * Ficheiro:  updateOrderStateToDelivered.sql
 * Última Edição: 18/05/21, 19:15
 * Nome da Classe: updateOrderStateToDelivered.sql
 */

create or replace procedure updateOrderStateToDelivered(p_deliveryID int)
    is
begin
    update ORDERS set ORDERSTATE = 4 where IDDELIVERY = p_deliveryID;

end;
/
commit;