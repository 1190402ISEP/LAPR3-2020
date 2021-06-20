create or replace function getClientEmailByOrder(p_orderID Orders.id%TYPE) return users.email%type
  is
    v_client_email users.email%type;

BEGIN
     select UserEmail into v_client_email
     from ClientOrder
     where id = p_orderID;

     return v_client_email;

END;
/

commit;

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
 * Caminho: E:/GITHUB 1190402/Licenciatura/2ºANO/1ºSEMESTRE/LAPR3/DataBase/getClientEmailByOrder.sql
 * Ficheiro:  getClientEmailByOrder.sql
 * Última Edição: 18/05/21, 19:15
 * Nome da Classe: getClientEmailByOrder.sql
 */

/*declare
    v_possivel_email users.email%type;
begin
        v_possivel_email := getClientEmailByOrder(1);
       dbms_output.put_line('email da order: ' || v_possivel_email);   
         
END;*/

