create or replace procedure updateOrdersDeliveryID(p_orderID int, p_deliveryID int)
    is

begin

    update ORDERS set IDDELIVERY = p_deliveryID where ID = p_orderID;
    update orders set ORDERSTATE = 2 where id = p_orderID; /*
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
 * Caminho: E:/GITHUB 1190402/Licenciatura/2ºANO/1ºSEMESTRE/LAPR3/DataBase/updateOrdersDeliveryID.sql
 * Ficheiro:  updateOrdersDeliveryID.sql
 * Última Edição: 18/05/21, 19:15
 * Nome da Classe: updateOrdersDeliveryID.sql
 */ --coloca a encomenda em estado de distribuição.
end;
/
commit;