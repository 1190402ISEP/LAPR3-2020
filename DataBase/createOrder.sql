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
 * Caminho: E:/GITHUB 1190402/Licenciatura/2ºANO/1ºSEMESTRE/LAPR3/DataBase/createOrder.sql
 * Ficheiro:  createOrder.sql
 * Última Edição: 18/05/21, 19:15
 * Nome da Classe: createOrder.sql
 */

create or replace procedure createOrder(p_email users.email%type, p_cardNumber creditcard.cardnumber%type,
                                        p_nif users.nif%type, p_orderID out orders.id%type,
                                        p_used_credits clientorder.usedcredits%type)
    is

    v_orderID orders.id%type;
    v_count   integer;
begin


    SELECT count(*) + 1 into v_orderID from ORDERS;


    insert into ORDERS(ID, orderDate, orderState, TOTALWEIGHT, REQUESTPHARMACY)
    values (v_orderID, sysdate, 1, 0, 1);


    insert into CLIENTORDER(id, nif, usedcredits, useremail, creditcardnumber)
    values (v_orderID, p_nif, p_used_credits, p_email, p_cardNumber);

    p_orderID := v_orderID;

    delete from BASKETPRODUCTS where USEREMAIL = p_email;
end;
/
commit;